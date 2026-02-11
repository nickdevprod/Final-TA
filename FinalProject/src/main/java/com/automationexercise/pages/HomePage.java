package com.automationexercise.pages;

import com.automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By homeSlider = By.id("slider");
    private final By signupLoginLink = By.linkText("Signup / Login");
    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By contactUsLink = By.linkText("Contact us");
    private final By cartLink = By.xpath("//a[@href='/view_cart']");
    private final By loggedInAsLabel = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By logoutLink = By.linkText("Logout");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageVisible() {
        return waitAndFind(homeSlider).isDisplayed();
    }

    public LoginPage clickSignupLogin() {
        click(signupLoginLink);
        return new LoginPage(driver);
    }

    public ProductsPage clickProducts() {
        click(productsLink);
        return new ProductsPage(driver);
    }

    public ContactUsPage clickContactUs() {
        click(contactUsLink);
        return new ContactUsPage(driver);
    }

    public CartPage clickCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public boolean isLoggedIn() {
        return waitAndFind(loggedInAsLabel).isDisplayed();
    }

    public void logout() {
        click(logoutLink);
    }
}

