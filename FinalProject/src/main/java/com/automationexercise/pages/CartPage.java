package com.automationexercise.pages;

import com.automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartTableRows = By.cssSelector("#cart_info_table tbody tr");
    private final By firstCartDeleteButton = By.cssSelector("#cart_info_table tbody tr td.cart_delete a");
    private final By proceedToCheckoutButton = By.xpath("//a[contains(text(),'Proceed To Checkout')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemsCount() {
        List<WebElement> rows = driver.findElements(cartTableRows);
        return rows.size();
    }

    public CartPage removeFirstItem() {
        click(firstCartDeleteButton);
        return this;
    }

    public CheckoutPage clickProceedToCheckout() {
        click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
}

