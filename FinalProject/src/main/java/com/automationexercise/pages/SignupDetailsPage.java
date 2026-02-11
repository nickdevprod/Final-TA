package com.automationexercise.pages;

import com.automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupDetailsPage extends BasePage {

    private final By passwordInput = By.id("password");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By addressInput = By.id("address1");
    private final By countrySelect = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipInput = By.id("zipcode");
    private final By mobileInput = By.id("mobile_number");
    private final By createAccountButton = By.xpath("//button[contains(text(),'Create Account')]");
    private final By accountCreatedText = By.xpath("//b[contains(text(),'Account Created!')]");
    private final By continueButton = By.linkText("Continue");

    public SignupDetailsPage(WebDriver driver) {
        super(driver);
    }

    public SignupDetailsPage fillBasicDetails(String password) {
        type(passwordInput, password);
        type(firstNameInput, "Nikoloz");
        type(lastNameInput, "Sigua");
        type(addressInput, "Some test street 1");
        type(stateInput, "Test state");
        type(cityInput, "Test city");
        type(zipInput, "12345");
        type(mobileInput, "1234567890");
        return this;
    }

    public SignupDetailsPage submitAccount() {
        click(createAccountButton);
        waitAndFind(accountCreatedText);
        return this;
    }

    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }
}

