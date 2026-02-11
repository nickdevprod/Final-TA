package com.automationexercise.core;

import com.automationexercise.listeners.TestListener;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected final String BASE_URL = "https://automationexercise.com";

    @BeforeMethod(alwaysRun = true)
    @Step("Open browser and go to home page")
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        driver.get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    @Step("Close browser window")
    public void tearDown(ITestResult result) {
        DriverManager.quitDriver();
    }
}


