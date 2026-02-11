Test Automation Project – Fall 2026

This project was completed individually by Nikoloz Sigua(me) as part of the Fall 2026 semester final.


Project Overview

This is a test automation framework built using Java, Selenium, TestNG, RestAssured, Maven, and Allure.

The project includes:

10 UI automated test cases

10 API automated test cases

Parallel test execution

Allure reporting with screenshots and API logs

Page Object Model (POM) structure

UI and API tests are clearly separated in the project structure.

Automated Test Cases
UI Test Cases

The following UI scenarios are automated:

Verify home page is visible after opening the site

Register user with valid data

Login with correct credentials

Login with incorrect credentials

Logout user

Submit Contact Us form

Search products by keyword

Add product to cart

Remove product from cart

Place order after login

UI tests are located in the tests.ui package.

API Test Cases

The following API scenarios are automated:

GET /productsList – verify 200 status and product list

POST /productsList – verify 405 (method not allowed)

Validate product content in /productsList

GET /brandsList – verify 200 status

POST /searchProduct with valid search term

POST /searchProduct with empty search term (400)

POST /verifyLogin without email (400)

DELETE /verifyLogin (405)

POST /verifyLogin with invalid credentials (404)

POST /createAccount with missing required fields (400)

API tests are located in the tests.api package.

Technologies Used

Java 17

Selenium WebDriver

TestNG

RestAssured

Maven

Allure Reports

The framework follows the Page Object Model design pattern and uses explicit waits only (no hard-coded sleeps).

How to Run the Tests
Prerequisites

Make sure you have the following installed:

Java 17 (or compatible version)

Maven

Google Chrome

Allure CLI (optional, for report generation)

You can verify Maven installation with:

mvn -version

Running All Tests

From the root directory of the project, run:

mvn clean test

By default, UI tests run in headless mode (browser window is not visible).

If you want to see the browser during execution, run:

mvn clean test -Dheadless=false

If you are using IntelliJ IDEA, you can add -Dheadless=false as a VM option in the Run Configuration.

UI and API tests are configured to run in parallel (see testng.xml).

Generating the Allure Report

After running the tests, generate the report using one of the following:

Using Maven:

mvn allure:serve

Or using Allure CLI:

allure serve target/allure-results

The report will open automatically in your browser.

The Allure report includes detailed test steps, screenshots for failed UI tests, and API request/response information.