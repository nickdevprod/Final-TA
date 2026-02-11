package com.automationexercise.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Epic("AutomationExercise API")
@Feature("Products")
public class ProductsApiTests extends BaseApiTest {

    @Test(description = "TC_API_01: GET /productsList returns all products")
    @Story("Products list - positive")
    @Description("Verify that GET /api/productsList returns 200 and a response body with products")
    public void getProductsList_shouldReturn200AndNonEmptyList() {
        Response response = given()
                .when()
                .get("/api/productsList");

        attachResponse("GET /productsList", "GET", "/api/productsList", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 200"))
                .body(containsString("\"products\""));
    }

    @Test(description = "TC_API_02: POST /productsList not allowed")
    @Story("Products list - negative")
    @Description("Verify that POST /api/productsList returns 200 with responseCode 405 in body")
    public void postProductsList_shouldReturn405() {
        Response response = given()
                .when()
                .post("/api/productsList");

        attachResponse("POST /productsList", "POST", "/api/productsList", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 405"));
    }

    @Test(description = "TC_API_03: GET /productsList contains a known product name")
    @Story("Products list - content check")
    @Description("Simple extra check that products list JSON contains 'Blue Top' product.")
    public void getProductsList_shouldContainKnownProductName() {
        Response response = given()
                .when()
                .get("/api/productsList");

        attachResponse("GET /productsList (content check)", "GET", "/api/productsList", response);

        response.then()
                .statusCode(200)
                .body(containsString("Blue Top"));
    }
}

