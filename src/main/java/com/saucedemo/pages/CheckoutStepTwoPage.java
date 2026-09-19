package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * CheckoutStepTwoPage – Page Object for the checkout overview / confirmation step.
 * URL: https://www.saucedemo.com/checkout-step-two.html
 */
public class CheckoutStepTwoPage extends BasePage {

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By cartItems = By.cssSelector(".cart_item");
    private final By itemName = By.cssSelector(".inventory_item_name");
    private final By itemPrice = By.cssSelector(".inventory_item_price");
    private final By subtotalLabel = By.cssSelector(".summary_subtotal_label");
    private final By taxLabel = By.cssSelector(".summary_tax_label");
    private final By totalLabel = By.cssSelector(".summary_total_label");
    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    // ── Actions & Queries ───────────────────────────────────────────────────

    public List<String> getItemNames() {
        List<WebElement> elements = findAll(itemName);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public int getItemCount() {
        return findAll(cartItems).size();
    }

    public String getSubtotalText() {
        return getText(subtotalLabel);
    }

    public double getSubtotalValue() {
        String text = getSubtotalText();
        // Format: "Item total: $39.98"
        return parsePrice(text);
    }

    public String getTaxText() {
        return getText(taxLabel);
    }

    public double getTaxValue() {
        String text = getTaxText();
        // Format: "Tax: $3.20"
        return parsePrice(text);
    }

    public String getTotalText() {
        return getText(totalLabel);
    }

    public double getTotalValue() {
        String text = getTotalText();
        // Format: "Total: $43.18"
        return parsePrice(text);
    }

    private double parsePrice(String text) {
        String numeric = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numeric);
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public ProductsPage clickCancel() {
        click(cancelButton);
        return new ProductsPage(driver);
    }
}
