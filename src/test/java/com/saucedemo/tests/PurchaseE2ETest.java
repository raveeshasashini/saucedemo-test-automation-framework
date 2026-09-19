package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.constants.TestData;
import com.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Task 3: End-to-End Purchase Test Suite
 * Tests the complete e-commerce user journey:
 * adding items, cart validation, checkout steps, pricing/tax calculations,
 * and order confirmation.
 */
public class PurchaseE2ETest extends BaseTest {

    @Test(description = "E2E: Full purchase flow from login to order confirmation")
    public void testCompletePurchaseFlow() {
        // 1. Log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        ProductsPage productsPage = loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        productsPage.waitForPageLoaded();

        // 2. Add 2 products to the cart
        productsPage.addProductToCartByName(TestData.PRODUCT_1_NAME);
        productsPage.waitForCartBadgeCount(1);
        productsPage.addProductToCartByName(TestData.PRODUCT_2_NAME);
        productsPage.waitForCartBadgeCount(2);

        Assert.assertEquals(productsPage.getCartBadgeCount(), 2,
                "Cart badge count did not update to 2");

        // 3. Navigate to Cart
        CartPage cartPage = productsPage.goToCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 2,
                "Cart does not contain exactly 2 items");

        List<String> cartItemNames = cartPage.getCartItemNames();
        Assert.assertTrue(cartItemNames.contains(TestData.PRODUCT_1_NAME),
                "Cart is missing " + TestData.PRODUCT_1_NAME);
        Assert.assertTrue(cartItemNames.contains(TestData.PRODUCT_2_NAME),
                "Cart is missing " + TestData.PRODUCT_2_NAME);

        // 4. Proceed to Checkout Step One
        CheckoutStepOnePage stepOne = cartPage.clickCheckout();
        Assert.assertTrue(stepOne.getCurrentUrl().contains("checkout-step-one.html"),
                "Did not navigate to checkout step one");

        // 5. Fill customer details and continue
        stepOne.fillCheckoutInfo(
                TestData.CHECKOUT_FIRST_NAME,
                TestData.CHECKOUT_LAST_NAME,
                TestData.CHECKOUT_POSTAL_CODE
        );
        CheckoutStepTwoPage stepTwo = stepOne.clickContinue();
        Assert.assertTrue(stepTwo.getCurrentUrl().contains("checkout-step-two.html"),
                "Did not navigate to checkout step two overview");

        // 6. Verify Checkout Step Two Summary
        Assert.assertEquals(stepTwo.getItemCount(), 2,
                "Overview does not show 2 items");

        double expectedSubtotal = TestData.PRODUCT_1_PRICE + TestData.PRODUCT_2_PRICE;
        double actualSubtotal = stepTwo.getSubtotalValue();
        Assert.assertEquals(actualSubtotal, expectedSubtotal, 0.01,
                "Subtotal does not match sum of item prices");

        double tax = stepTwo.getTaxValue();
        double total = stepTwo.getTotalValue();
        Assert.assertEquals(total, actualSubtotal + tax, 0.01,
                "Total price does not equal subtotal + tax");

        // 7. Finish Order
        CheckoutCompletePage completePage = stepTwo.clickFinish();
        Assert.assertTrue(completePage.getCurrentUrl().contains("checkout-complete.html"),
                "Did not navigate to checkout complete page");

        Assert.assertEquals(completePage.getConfirmationHeader(), TestData.CONFIRMATION_HEADER,
                "Order confirmation header text mismatch");
        Assert.assertEquals(completePage.getConfirmationText(), TestData.CONFIRMATION_TEXT,
                "Order confirmation body text mismatch");
        Assert.assertTrue(completePage.isBackHomeButtonDisplayed(),
                "Back Home button is not displayed on complete page");
    }

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
