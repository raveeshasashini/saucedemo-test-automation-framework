package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.constants.TestData;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
