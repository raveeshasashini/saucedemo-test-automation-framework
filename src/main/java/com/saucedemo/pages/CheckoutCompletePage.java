package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * CheckoutCompletePage – Page Object for the final order completion screen.
 * URL: https://www.saucedemo.com/checkout-complete.html
 */
public class CheckoutCompletePage extends BasePage {

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By completeHeader = By.cssSelector(".complete-header");
    private final By completeText = By.cssSelector(".complete-text");
    private final By completeContainer = By.id("checkout_complete_container");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    // ── Actions & Queries ───────────────────────────────────────────────────

    /** Wait for the checkout complete page to be fully loaded. */
    public void waitForPageLoaded() {
        waitForUrlContains("checkout-complete.html");
        findVisible(completeHeader);
    }

    public String getConfirmationHeader() {
        return getText(completeHeader);
    }

    public String getConfirmationText() {
        return getText(completeText);
    }

    public boolean isBackHomeButtonDisplayed() {
        return isDisplayed(backHomeButton);
    }

    public boolean isOrderCompleteContainerDisplayed() {
        return isDisplayed(completeContainer);
    }

    public ProductsPage clickBackHome() {
        click(backHomeButton);
        return new ProductsPage(driver);
    }
}
