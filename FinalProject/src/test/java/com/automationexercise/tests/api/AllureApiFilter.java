package com.automationexercise.tests.api;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureApiFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(requestSpec.getMethod())
                .append(" ")
                .append(requestSpec.getURI())
                .append("\n\nHeaders:\n");
        requestSpec.getHeaders().forEach(h ->
                requestBuilder.append(h.getName()).append(": ").append(h.getValue()).append("\n"));

        if (requestSpec.getBody() != null) {
            requestBuilder.append("\nBody:\n")
                    .append(String.valueOf(requestSpec.getBody()))
                    .append("\n");
        }

        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("Status: ")
                .append(response.getStatusCode())
                .append(" ")
                .append(response.getStatusLine())
                .append("\n\nHeaders:\n");
        response.getHeaders().forEach(h ->
                responseBuilder.append(h.getName()).append(": ").append(h.getValue()).append("\n"));
        responseBuilder.append("\nBody:\n").append(response.asPrettyString()).append("\n");

        Allure.addAttachment("API request", "text/plain", requestBuilder.toString());
        Allure.addAttachment("API response", "text/plain", responseBuilder.toString());

        return response;
    }
}

