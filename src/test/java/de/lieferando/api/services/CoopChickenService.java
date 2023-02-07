package de.lieferando.api.services;

import de.lieferando.api.response.ChickenResponse;
import io.restassured.RestAssured;

public class CoopChickenService extends ApiService {

    /**
     * Method to facilitate chicken API
     *
     */
    public ChickenResponse feedChicken() {
        return RestAssured.given()
                .spec(specification)
                .post(Endpoints.FEED_CHICKEN)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ChickenResponse.class);
    }

    /**
     * Method to facilitate collect eggs API
     *
     */
    public ChickenResponse collectEggs() {
        return RestAssured.given()
                .spec(specification)
                .post(Endpoints.COLLECT_EGGS)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ChickenResponse.class);
    }

    /**
     * Method to facilitate count eggs API
     *
     */
    public ChickenResponse countCollectedEggs() {
        return RestAssured.given()
                .spec(specification)
                .post(Endpoints.COUNT_EGGS)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ChickenResponse.class);
    }
}
