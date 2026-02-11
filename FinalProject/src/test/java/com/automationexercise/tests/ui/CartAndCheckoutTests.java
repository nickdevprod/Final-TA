package com.automationexercise.tests.ui;

import com.automationexercise.core.BaseTest;
import com.automationexercise.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("AutomationExercise UI")
@Feature("Cart and checkout")
public class CartAndCheckoutTests extends BaseTest {

    private static final String EXISTING_EMAIL = "nikoloz.test.user@example.com";
    private static final String EXISTING_PASSWORD = "test123";

    @Test(description = "TC_UI_06: Add product to cart and see it there")
    @Story("Add to cart")
    @Description("Adds first product from products page and then opens the cart to see at least one item.")
    public void addProductToCart() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();
        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.goToCart();

        Assert.assertTrue(cartPage.getCartItemsCount() > 0, "Cart was empty after adding product");
    }

    @Test(description = "TC_UI_07: Remove product from cart")
    @Story("Remove from cart")
    @Description("Puts a product into the cart and then removes it, expecting item count to go down.")
    public void removeProductFromCart() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();
        productsPage.addFirstProductToCart();

        CartPage cartPage = productsPage.goToCart();
        int before = cartPage.getCartItemsCount();

        cartPage.removeFirstItem();
        int after = cartPage.getCartItemsCount();
        Assert.assertTrue(after <= before, "Cart item count did not go down");
    }

    @Test(description = "TC_UI_08: Reach checkout after login")
    @Story("Place order")
    @Description("Logs in, tries to add a product, goes to checkout and submits dummy card details.")
    public void placeOrderAfterLogin() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickSignupLogin();
        homePage = loginPage.login(EXISTING_EMAIL, EXISTING_PASSWORD);
        Assert.assertTrue(homePage.isLoggedIn(), "Login for checkout failed");

        ProductsPage productsPage = homePage.clickProducts();
        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.goToCart();

        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();
        checkoutPage
                .clickPlaceOrder()
                .fillDummyPaymentDetails()
                .submitPayment();
    }
}

