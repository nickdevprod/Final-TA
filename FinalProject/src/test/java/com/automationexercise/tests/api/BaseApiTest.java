package com.automationexercise.tests.api;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    protected static final String BASE_URL = "https://automationexercise.com";

    @BeforeClass(alwaysRun = true)
    public void setUpApi() {
        RestAssured.baseURI = BASE_URL;
    }

    protected void attachResponse(String name, String method, String path, Response response) {
        StringBuilder sb = new StringBuilder();
        sb.append(method).append(" ").append(BASE_URL).append(path).append("\n\n");
        sb.append("Status: ").append(response.getStatusCode()).append(" ").append(response.getStatusLine()).append("\n\n");
        sb.append("Headers:\n");
        response.getHeaders().forEach(h ->
                sb.append(h.getName()).append(": ").append(h.getValue()).append("\n"));
        sb.append("\nBody:\n").append(response.asPrettyString()).append("\n");
        Allure.addAttachment(name, "text/plain", sb.toString());
    }
}



