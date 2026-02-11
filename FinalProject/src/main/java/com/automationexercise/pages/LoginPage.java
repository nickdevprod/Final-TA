package com.automationexercise.pages;

import com.automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By signupNameInput = By.name("name");
    private final By signupEmailInput = By.xpath("//form[@action='/signup']/input[@name='email']");
    private final By signupButton = By.xpath("//form[@action='/signup']//button");

    private final By loginEmailInput = By.xpath("//form[@action='/login']/input[@name='email']");
    private final By loginPasswordInput = By.xpath("//form[@action='/login']/input[@name='password']");
    private final By loginButton = By.xpath("//form[@action='/login']//button");
    private final By loginError = By.xpath("//p[contains(text(),'Your email or password is incorrect')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public SignupDetailsPage startSignup(String name, String email) {
        type(signupNameInput, name);
        type(signupEmailInput, email);
        click(signupButton);
        return new SignupDetailsPage(driver);
    }

    public HomePage login(String email, String password) {
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
        return new HomePage(driver);
    }

    public boolean isLoginErrorVisible() {
        return waitAndFind(loginError).isDisplayed();
    }
}

