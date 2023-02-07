package de.lieferando.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Sources("classpath:config.properties")
public interface TestConfiguration extends Config {

    @DefaultValue("chrome")
    @Key("selenium.browser")
    String browser();

    @Key("webapp.url")
    String webAppBaseUrl();

    @Key("api.clientid")
    String getClientId();

    @Key("api.clientsecret")
    String getClientSecret();

    @Key("api.redirectUri")
    String getRedirectUri();

    @Key("api.baseUrl")
    String getApiBaseUrl();

    @Key("api.user.id")
    String getUserId();

    @Key("api.user.email")
    String getUserEmail();

    @Key("api.user.password")
    String getUserPassword();

}
