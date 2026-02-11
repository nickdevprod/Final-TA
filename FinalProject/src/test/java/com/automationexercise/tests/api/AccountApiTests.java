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
@Feature("Account")
public class AccountApiTests extends BaseApiTest {

    @Test(description = "TC_API_07: POST /createAccount with missing fields")
    @Story("Create account - validation")
    @Description("Verify that POST /api/createAccount with missing required fields returns 200 with code 400 in body")
    public void createAccount_withMissingFields_shouldReturn400() {
        Response response = given()
                .formParam("name", "Nikoloz")
                .when()
                .post("/api/createAccount");

        attachResponse("POST /createAccount (missing fields)", "POST", "/api/createAccount", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 400"));
    }

    @Test(description = "TC_API_08: GET /brandsList returns 200")
    @Story("Brands list - positive")
    @Description("Verify that GET /api/brandsList returns 200 and contains brands in body")
    public void getBrandsList_shouldReturn200() {
        Response response = given()
                .when()
                .get("/api/brandsList");

        attachResponse("GET /brandsList", "GET", "/api/brandsList", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 200"))
                .body(containsString("\"brands\""));
    }

    @Test(description = "TC_API_09: POST /searchProduct with valid term")
    @Story("Search product - positive")
    @Description("Verify that POST /api/searchProduct with a valid product name returns 200 and products in body")
    public void searchProduct_withValidName_shouldReturnResults() {
        Response response = given()
                .formParam("search_product", "shirt")
                .when()
                .post("/api/searchProduct");

        attachResponse("POST /searchProduct (valid term)", "POST", "/api/searchProduct", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 200"))
                .body(containsString("\"products\""));
    }

    @Test(description = "TC_API_10: POST /searchProduct with empty term")
    @Story("Search product - validation")
    @Description("Current behaviour: empty search still returns 200 and full products list")
    public void searchProduct_withEmptyName_shouldReturnAllProducts() {
        Response response = given()
                .formParam("search_product", "")
                .when()
                .post("/api/searchProduct");

        attachResponse("POST /searchProduct (empty term)", "POST", "/api/searchProduct", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 200"))
                .body(containsString("\"products\""));
    }
}

