package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * CheckoutStepOnePage – Page Object for the checkout information form.
 * URL: https://www.saucedemo.com/checkout-step-one.html
 */
public class CheckoutStepOnePage extends BasePage {

    // ── Locators ────────────────────────────────────────────────────
    private final By firstNameInput  = By.id("first-name");
    private final By lastNameInput   = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton  = By.id("continue");
    private final By cancelButton    = By.id("cancel");
    private final By errorMessage    = By.cssSelector("h3[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    // ── Actions ─────────────────────────────────────────────────────

    /** Wait for the checkout step one page to be fully loaded. */
    public void waitForPageLoaded() {
        waitForUrlContains("checkout-step-one.html");
        findVisible(firstNameInput);
    }

    public void enterFirstName(String name) {
        typeText(firstNameInput, name);
    }

    public void enterLastName(String name) {
        typeText(lastNameInput, name);
    }

    public void enterPostalCode(String code) {
        typeText(postalCodeInput, code);
    }

    /** Fill in all three checkout fields at once. */
    public void fillCheckoutInfo(String first, String last, String postal) {
        enterFirstName(first);
        enterLastName(last);
        enterPostalCode(postal);
    }

    public CheckoutStepTwoPage clickContinue() {
        click(continueButton);
        return new CheckoutStepTwoPage(driver);
    }

    public CartPage clickCancel() {
        click(cancelButton);
        return new CartPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }
}
