package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.constants.TestData;
import com.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseE2ETest extends BaseTest {

    @Test(description = "Cart: Add and remove product updates badge count correctly")
    public void testAddAndRemoveItemFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        ProductsPage productsPage = loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        productsPage.waitForPageLoaded();

        // Add item
        productsPage.addProductToCartByName(TestData.PRODUCT_1_NAME);
        productsPage.waitForCartBadgeCount(1);
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "Cart badge count is not 1");

        // Remove item from products page
        productsPage.removeProductFromCartByName(TestData.PRODUCT_1_NAME);
        Assert.assertEquals(productsPage.getCartBadgeCount(), 0, "Cart badge should be 0 after removal");
    }

    @Test(description = "Negative: Checkout step one requires first name")
    public void testCheckoutFormValidation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        ProductsPage productsPage = loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        productsPage.waitForPageLoaded();

        productsPage.addProductToCartByName(TestData.PRODUCT_1_NAME);
        CartPage cartPage = productsPage.goToCart();
        CheckoutStepOnePage stepOne = cartPage.clickCheckout();

        // Attempt continue with empty fields
        stepOne.clickContinue();

        Assert.assertTrue(stepOne.isErrorDisplayed(), "Error banner should be displayed");
        Assert.assertTrue(stepOne.getErrorMessage().contains("First Name is required"),
                "Error message does not mention First Name is required");
    }
}
