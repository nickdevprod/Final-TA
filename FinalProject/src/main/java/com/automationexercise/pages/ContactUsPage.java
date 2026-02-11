package com.automationexercise.pages;

import com.automationexercise.core.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {

    private final By getInTouchTitle = By.xpath("//h2[contains(text(),'Get In Touch')]");
    private final By nameInput = By.name("name");
    private final By emailInput = By.name("email");
    private final By subjectInput = By.name("subject");
    private final By messageTextarea = By.id("message");
    private final By submitButton = By.name("submit");
    private final By successAlert = By.cssSelector(".status.alert-success");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return waitAndFind(getInTouchTitle).isDisplayed();
    }

    public ContactUsPage fillForm(String name, String email, String subject, String message) {
        type(nameInput, name);
        type(emailInput, email);
        type(subjectInput, subject);
        type(messageTextarea, message);
        return this;
    }

    public ContactUsPage submitForm() {
        click(submitButton);
        return this;
    }

    public boolean isSuccessMessageVisible() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {
        }
        return waitAndFind(successAlert).isDisplayed();
    }
}

