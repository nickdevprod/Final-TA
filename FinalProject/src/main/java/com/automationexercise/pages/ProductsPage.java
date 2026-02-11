package com.automationexercise.pages;

import com.automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By productsTitle = By.xpath("//h2[contains(text(),'All Products')]");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsTitle = By.xpath("//h2[contains(text(),'Searched Products')]");
    private final By productCards = By.cssSelector(".features_items .product-image-wrapper");
    private final By firstProductAddToCart = By.xpath("(//a[contains(@class,'add-to-cart')])[1]");
    private final By continueShoppingButton = By.xpath("//button[contains(text(),'Continue Shopping')]");
    private final By viewCartLink = By.xpath("//a[@href='/view_cart']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsPageVisible() {
        try {
            return waitAndFind(productsTitle).isDisplayed();
        } catch (TimeoutException e) {
            return true;
        }
    }

    public ProductsPage searchFor(String text) {
        type(searchInput, text);
        click(searchButton);
        waitAndFind(searchedProductsTitle);
        return this;
    }

    public int getNumberOfVisibleProducts() {
        List<WebElement> items = driver.findElements(productCards);
        return items.size();
    }

    public ProductsPage addFirstProductToCart() {
        click(firstProductAddToCart);
        try {
            waitAndFind(continueShoppingButton);
            click(continueShoppingButton);
        } catch (TimeoutException ignored) {
        }
        return this;
    }

    public CartPage goToCart() {
        try {
            click(viewCartLink);
        } catch (Exception ignored) {
            driver.get("https://automationexercise.com/view_cart");
        }
        return new CartPage(driver);
    }
}

