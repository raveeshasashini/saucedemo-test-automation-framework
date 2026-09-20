package com.saucedemo.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqResApiTest {

    @BeforeClass
    public void setUpApi() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test(description = "GET /api/users?page=2 - Retrieve paginated user list")
    public void testGetUsersList() {
        given()
                .queryParam("page", 2)
        .when()
                .get("/api/users")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("page", equalTo(2))
                .body("per_page", greaterThan(0))
                .body("total", greaterThan(0))
                .body("data", not(empty()))
                .body("data[0].id", notNullValue())
                .body("data[0].email", notNullValue())
                .body("data[0].first_name", notNullValue())
                .body("support.url", notNullValue());
    }

    @Test(description = "POST /api/users - Create a new user record")
    public void testCreateUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }
}
