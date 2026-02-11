Test Automation Project – Fall 2026
Author: Nikoloz Sigua  
  


This project is a Test Automation Framework built using:

- Java 17
- Selenium WebDriver
- TestNG
- RestAssured
- Maven
- Allure Reports

 Key Features

- 10 UI Automated Test Cases
- 10 API Automated Test Cases
- Parallel Test Execution
- Allure Reporting (screenshots + API logs)
- Page Object Model (POM) Design Pattern
- Explicit waits only (no hard-coded sleeps)
- Clear separation of UI and API tests

src/test/java  
 ├── tests.ui      → UI test cases  
 └── tests.api     → API test cases  

UI Automated Test Cases

1. Verify home page is visible after opening the site  
2. Register user with valid data  
3. Login with correct credentials  
4. Login with incorrect credentials  
5. Logout user  
6. Submit Contact Us form  
7. Search products by keyword  
8. Add product to cart  
9. Remove product from cart  
10. Place order after login  


API Automated Test Cases

1. GET /productsList – Verify 200 status and product list  
2. POST /productsList – Verify 405 (Method Not Allowed)  
3. Validate product content in /productsList  
4. GET /brandsList – Verify 200 status  
5. POST /searchProduct with valid search term  
6. POST /searchProduct with empty search term – Verify 400  
7. POST /verifyLogin without email – Verify 400  
8. DELETE /verifyLogin – Verify 405  
9. POST /verifyLogin with invalid credentials – Verify 404  
10. POST /createAccount with missing required fields – Verify 400  

How to Run(kinda important)

 tests can be run by simply from testing xml or using maven(which was my choice)
 I am using headless mode because it was faster and more realiable but it can be disabled if needed
 I also include folder with screenshoots of allure reports to save time for our dear lecturer

 Thank you for reading my readme.

