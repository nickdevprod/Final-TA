package com.automationexercise.pages;

import com.automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By placeOrderButton = By.xpath("//a[contains(text(),'Place Order')]");
    private final By nameOnCardInput = By.name("name_on_card");
    private final By cardNumberInput = By.name("card_number");
    private final By cvcInput = By.name("cvc");
    private final By expiryMonthInput = By.name("expiry_month");
    private final By expiryYearInput = By.name("expiry_year");
    private final By payAndConfirmButton = By.id("submit");
    private final By successMessage = By.xpath("//*[contains(text(),'Your order has been placed successfully')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickPlaceOrder() {
        click(placeOrderButton);
        return this;
    }

    public CheckoutPage fillDummyPaymentDetails() {
        type(nameOnCardInput, "Test User");
        type(cardNumberInput, "4111111111111111");
        type(cvcInput, "123");
        type(expiryMonthInput, "12");
        type(expiryYearInput, "2030");
        return this;
    }

    public CheckoutPage submitPayment() {
        click(payAndConfirmButton);
        return this;
    }

    public boolean isOrderSuccessVisible() {
        return waitAndFind(successMessage).isDisplayed();
        }
}

