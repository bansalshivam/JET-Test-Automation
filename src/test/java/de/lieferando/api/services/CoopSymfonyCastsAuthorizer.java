package de.lieferando.api.services;

import de.lieferando.configuration.TestConfiguration;
import de.lieferando.utility.LoggerUtility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CoopSymfonyCastsAuthorizer implements Authorizer {

    final LoggerUtility log = new LoggerUtility(LogManager.getLogger(getClass()));
    private final TestConfiguration apiConfig = ConfigFactory.create(
            TestConfiguration.class, System.getProperties());
    private final CookieFilter cookieFilter = new CookieFilter();

    /**
     * Base method to authorize.
     *
     */
    @Override
    public RequestSpecification authorize() {
        log.infoMSG("Authorizing for Coop Service.");
        RequestSpecification spec = new RequestSpecBuilder()
                .log(LogDetail.URI)
                .addFilter(cookieFilter)
                .build();
        setCookies(spec);
        login(spec);
        initializeAuth(spec);
        log.infoMSG("Done with Request Specification.");
        return spec;
    }

    /**
     * Method to set the cookies.
     *
     */
    private void setCookies(RequestSpecification specification) {
        log.infoMSG("Setting Cookies.");
        RestAssured.given()
                .spec(specification)
                .filter(cookieFilter)
                .expect().statusCode(200)
                .when().get(Endpoints.COOP_LOGIN);
    }

    /**
     * Method to perform login.
     *
     */
    private void login(RequestSpecification specification) {
        log.infoMSG("Performing Login");
        RestAssured.given()
                .spec(specification)
                .filter(cookieFilter)
                .multiPart("_username", apiConfig.getUserEmail())
                .multiPart("_password", apiConfig.getUserPassword())
                .expect().statusCode(302)
                .when().post(Endpoints.LOGIN_CHECK);
    }

    /**
     * Method to initialize authentication.
     *
     */
    private void initializeAuth(RequestSpecification specification) {
        Response authResponse = authorize(EnumSet.copyOf(Arrays.stream(Scope.values()).collect(Collectors.toSet())));
        try {
            String locationHeader = authResponse.getHeader("Location");
            String code = locationHeader.split("code=")[1].replace("&", "");
            Response tokenResponse = fetchToken(code, specification);
            String token = tokenResponse.jsonPath().getString("access_token");
            specification.header("Authorization", "Bearer " + token)
                    .basePath("/api/" + apiConfig.getUserId());
        } catch (Exception e) {
            log.errorMSG("Error during authorization code extraction!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Method to fetch token.
     *
     */
    private Response fetchToken(String code, RequestSpecification specification) {
        log.infoMSG("Perform request to get token");
        return RestAssured.given().spec(specification)
                .multiPart("client_id", apiConfig.getClientId())
                .multiPart("client_secret", apiConfig.getClientSecret())
                .multiPart("grant_type", "client_credentials")
                .multiPart("code", code)
                .expect().statusCode(200)
                .when().post(Endpoints.ACCESS_TOKEN);
    }

    /**
     * Main method to authorize.
     *
     */
    private Response authorize(Set<Scope> scopes) {
        List<String> permissions = scopes.stream().map(Scope::getProfile).collect(Collectors.toList());
        String scope = String.join(" ", permissions);
        log.infoMSG("Request to authorize with scope " + scope);
        return RestAssured.given()
                .filter(cookieFilter)
                .redirects().follow(false)
                .queryParam("client_id", apiConfig.getClientId())
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", apiConfig.getRedirectUri())
                .queryParam("scope", scope)
                .queryParam("authorize", 1)
                .expect().statusCode(302)
                .when()
                .get(Endpoints.AUTHORIZATION);
    }

    private enum Scope {
        BARN("barn-unlock"),
        TOILETSEAT("toiletseat-down"),
        CHICKEN_FEED("chickens-feed"),
        EGGS_COLLECT("eggs-collect"),
        EGGS_COUNT("eggs-count"),
        PROFILE("profile");

        private final String profile;

        Scope(String profile) {
            this.profile = profile;
        }

        public String getProfile() {
            return profile;
        }
    }
}
