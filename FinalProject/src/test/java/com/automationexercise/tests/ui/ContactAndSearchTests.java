package com.automationexercise.tests.ui;

import com.automationexercise.core.BaseTest;
import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("AutomationExercise UI")
@Feature("Contact and search")
public class ContactAndSearchTests extends BaseTest {

    @Test(description = "TC_UI_09: Submit 'Contact Us' form")
    @Story("Contact us")
    @Description("Fills the contact form with some basic text and expects success message.")
    public void sendContactUsMessage() {
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = homePage.clickContactUs();
        Assert.assertTrue(contactUsPage.isLoaded(), "Contact us page did not load");

        contactUsPage
                .fillForm("Nikoloz", "nikoloz.contact@example.com",
                        "Just testing", "This is a simple contact form test.")
                .submitForm();

        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(), "Contact form success message not shown");
    }

    @Test(description = "TC_UI_10: Search products by keyword")
    @Story("Search product")
    @Description("Searches for 'shirt' and expects that some products are returned.")
    public void searchProductsByKeyword() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();
        Assert.assertTrue(productsPage.isProductsPageVisible(), "Products page did not open");

        productsPage.searchFor("shirt");
        int numberOfResults = productsPage.getNumberOfVisibleProducts();

        Assert.assertTrue(numberOfResults > 0, "Expected at least one product in search results");
    }
}

