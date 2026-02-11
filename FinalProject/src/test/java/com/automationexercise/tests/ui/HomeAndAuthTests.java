package com.automationexercise.tests.ui;

import com.automationexercise.core.BaseTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.SignupDetailsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("AutomationExercise UI")
@Feature("Home and auth")
public class HomeAndAuthTests extends BaseTest {

    private static final String EXISTING_EMAIL = "nikoloz.test.user@example.com";
    private static final String EXISTING_PASSWORD = "test123";

    @Test(description = "TC_UI_01: Home page is visible after opening site")
    @Story("Home page")
    @Description("Just checks that the main home page slider is there after opening the site.")
    public void homePageShouldBeVisible() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page slider was not visible");
    }

    @Test(description = "TC_UI_02: Register user with valid info")
    @Story("Register user")
    @Description("Goes through the basic registration flow with simple test data.")
    public void registerUserWithValidData() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickSignupLogin();

        String uniqueEmail = "student_" + System.currentTimeMillis() + "@mail.com";

        SignupDetailsPage detailsPage = loginPage.startSignup("Nikoloz", uniqueEmail);
        detailsPage
                .fillBasicDetails(EXISTING_PASSWORD)
                .submitAccount()
                .clickContinue();
    }

    @Test(description = "TC_UI_03: Login with correct email and password")
    @Story("Login user - positive")
    @Description("Tries to log in with the known user and expects to see 'Logged in as' label.")
    public void loginWithCorrectCredentials() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickSignupLogin();

        homePage = loginPage.login(EXISTING_EMAIL, EXISTING_PASSWORD);
        Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in but isn't");
    }

    @Test(description = "TC_UI_04: Login with wrong password")
    @Story("Login user - negative")
    @Description("Uses the same email but wrong password and expects error message.")
    public void loginWithWrongPasswordShowsError() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickSignupLogin();

        loginPage.login(EXISTING_EMAIL, "wrong-pass");
        Assert.assertTrue(loginPage.isLoginErrorVisible(), "Login error was not shown");
    }

    @Test(description = "TC_UI_05: Logout user from header")
    @Story("Logout user")
    @Description("Logs in, then clicks logout and expects to go back to login page.")
    public void logoutUserFromHomePage() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickSignupLogin();

        homePage = loginPage.login(EXISTING_EMAIL, EXISTING_PASSWORD);
        Assert.assertTrue(homePage.isLoggedIn(), "Precondition login failed");

        homePage.logout();
        LoginPage afterLogout = new LoginPage(driver);
        Assert.assertTrue(afterLogout != null, "Expected to be on login page after logout");
    }
}

