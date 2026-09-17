package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Task 1: Sanity Tests
 * Verifies that the SauceDemo web application is alive, accessible,
 * and essential UI components render correctly.
 */
public class SanityTest extends BaseTest {

    @Test(description = "Verify that the landing page loads successfully and page title is Swag Labs")
    public void testLandingPageLoads() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        Assert.assertTrue(loginPage.getCurrentUrl().contains("saucedemo.com"),
                "URL does not contain 'saucedemo.com'");
        Assert.assertEquals(loginPage.getTitle(), Config.EXPECTED_TITLE,
                "Page title did not match expected 'Swag Labs'");
    }

    @Test(description = "Verify that login form elements are present and visible on page load")
    public void testLoginFormElementsPresent() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        Assert.assertTrue(loginPage.isUsernameInputDisplayed(),
                "Username input field is not displayed");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(),
                "Password input field is not displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                "Login button is not displayed");
        Assert.assertEquals(loginPage.getLoginButtonValue(), "Login",
                "Login button label does not match 'Login'");
    }
}
