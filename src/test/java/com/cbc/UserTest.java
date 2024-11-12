package com.cbc;

import com.cbc.utils.RestAssuredUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.io.File;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {


    @Test
    public void getSingleUserTest(){
        Response response = RestAssuredUtils.
                getBaseRequest().
                pathParam("userId",2).
                when().
                get("/users/{userId}")
                . then().
                 statusCode(200).
                extract().response();
        // Print the response body
        System.out.println("Response: " + response.asString());

        // Assertions to verify the response data
       assertEquals(response.jsonPath().getString("data.email"), "janet.weaver@reqres.in");
       assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
       assertEquals(response.jsonPath().getString("data.last_name"), "Weaver");

    }

    //get all
    @Test
    public void getAllUsersTest(){
        Response response=
                RestAssuredUtils.
                        getBaseRequest()
                        .queryParam("page",2)
                        .when().
                        get("/users").
                        then().
                        statusCode(200).
                        extract().response();
        System.out.println(response.asString());
        // Assert that the page number in the response is 2
        assertThat(response.jsonPath().getInt("page"))
                .as("Check page number")
                .isEqualTo(2);
    }


    @Test
    public void CreateUserTest(){
        String body ="{\n" +
                "    \"name\": \"Abdirahamn\",\n" +
                "    \"job\": \"Abdi\"\n" +
                "}";
        Response response = RestAssuredUtils.
                getBaseRequest()
                .when()
                .body(body).
                post("/users").
                thenReturn();

        System.out.println(response.asString());
        assertEquals(response.statusCode(), 201);


    }

    @Test
    public void UpdateUserTest(){
        String body ="{\n" +
                "    \"name\": \"Abdirahamn\",\n" +
                "    \"job\": \"Abdi\"\n" +
                "}";
        Response response = RestAssuredUtils.
                getBaseRequest()
                .pathParam("UserId",2)
                .when()
                .body(body).
                patch("/users/{UserId}").
                thenReturn();

        System.out.println(response.asString());
        assertEquals(response.statusCode(), 200);

    }

    @Test
    public void DeleteUserTest(){
       Response response = RestAssuredUtils.
               getBaseRequest().
               pathParam("UserId",2).
               when().
               delete("/users/{UserId}").
               thenReturn();

       System.out.println(response.asString());
       assertEquals(response.statusCode(), 204);
    }


}
