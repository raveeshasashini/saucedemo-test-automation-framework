package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.constants.TestData;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Task 2: Login & Logout Test Suite
 * Covers positive, negative, edge-case authentication scenarios,
 * and user session termination (logout).
 */
public class LoginTest extends BaseTest {

    @Test(description = "Positive: Valid login with standard_user redirects to inventory page")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        ProductsPage productsPage = loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        productsPage.waitForPageLoaded();

        Assert.assertTrue(productsPage.getCurrentUrl().contains(Config.INVENTORY_URL),
                "User was not redirected to inventory page");
        Assert.assertEquals(productsPage.getPageTitle(), "Products",
                "Inventory page header does not match 'Products'");
        Assert.assertTrue(productsPage.getProductCount() > 0,
                "Product inventory is empty");
    }

    @Test(description = "Negative: Login with invalid credentials displays error message")
    public void testInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(TestData.INVALID_USERNAME, TestData.INVALID_PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error banner is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), TestData.ERR_CREDENTIALS_MISMATCH,
                "Error message does not match expected credentials mismatch error");
    }

    @Test(description = "Negative: Login with empty username displays required error")
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login("", TestData.VALID_PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error banner is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), TestData.ERR_USERNAME_REQUIRED,
                "Error message does not match expected username required error");
    }

    @Test(description = "Negative: Login with empty password displays required error")
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(TestData.STANDARD_USER, "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error banner is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), TestData.ERR_PASSWORD_REQUIRED,
                "Error message does not match expected password required error");
    }

    @Test(description = "Negative: Locked-out user displays locked out error message")
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(TestData.LOCKED_OUT_USER, TestData.VALID_PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error banner is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), TestData.ERR_LOCKED_OUT,
                "Error message does not match expected locked-out error");
    }

    @Test(description = "Positive: User can successfully log out and return to the login page")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        ProductsPage productsPage = loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        productsPage.waitForPageLoaded();

        LoginPage loggedOutPage = productsPage.logout();

        Assert.assertTrue(loggedOutPage.isLoginButtonDisplayed(),
                "Login button is not displayed after logout");
        Assert.assertTrue(loggedOutPage.isUsernameInputDisplayed(),
                "Username input is not displayed after logout");
        String currentUrl = loggedOutPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.equals(Config.BASE_URL) || currentUrl.equals(Config.BASE_URL + "/") || currentUrl.startsWith(Config.BASE_URL),
                "User was not redirected back to login page. Actual URL: " + currentUrl);
    }
}
