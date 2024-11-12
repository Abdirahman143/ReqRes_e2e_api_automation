package com.cbc.utils;

import com.cbc.config.Endpoints;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

    public static RequestSpecification getBaseRequest(){
        return RestAssured.
                given()
                .baseUri(Endpoints.BASE_URL)
                .header("Content-Type", "application/json");
    }
    public static void setUp() {
        RestAssured.baseURI = Endpoints.BASE_URL;
    }
}
