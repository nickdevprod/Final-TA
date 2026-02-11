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
@Feature("Login")
public class LoginApiTests extends BaseApiTest {

    private static final String VALID_EMAIL = "nikoloz.test.user@example.com";
    private static final String VALID_PASSWORD = "test123";

    @Test(description = "TC_API_04: POST /verifyLogin with invalid credentials")
    @Story("Verify login - negative")
    @Description("Verify that POST /api/verifyLogin with wrong password returns 200 with code 404 in body")
    public void verifyLogin_withInvalidCredentials_shouldReturn404() {
        Response response = given()
                .formParam("email", VALID_EMAIL)
                .formParam("password", "wrong-password")
                .when()
                .post("/api/verifyLogin");

        attachResponse("POST /verifyLogin (invalid password)", "POST", "/api/verifyLogin", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 404"))
                .body(containsString("\"message\": \"User not found!\""));
    }

    @Test(description = "TC_API_05: POST /verifyLogin without email")
    @Story("Verify login - validation")
    @Description("Verify that POST /api/verifyLogin without email returns 200 with code 400 in body")
    public void verifyLogin_withoutEmail_shouldReturn400() {
        Response response = given()
                .formParam("password", VALID_PASSWORD)
                .when()
                .post("/api/verifyLogin");

        attachResponse("POST /verifyLogin (missing email)", "POST", "/api/verifyLogin", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 400"));
    }

    @Test(description = "TC_API_06: DELETE /verifyLogin not allowed")
    @Story("Verify login - method not allowed")
    @Description("Verify that DELETE /api/verifyLogin returns 200 with code 405 in body")
    public void deleteVerifyLogin_shouldReturn405() {
        Response response = given()
                .when()
                .delete("/api/verifyLogin");

        attachResponse("DELETE /verifyLogin", "DELETE", "/api/verifyLogin", response);

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 405"));
    }
}

