package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * CartPage – Page Object for the SauceDemo shopping-cart screen.
 * URL: https://www.saucedemo.com/cart.html
 */
public class CartPage extends BasePage {

    // ── Locators ────────────────────────────────────────────────────
    private final By cartItems              = By.cssSelector("div.cart_item");
    private final By itemNames              = By.cssSelector("div.inventory_item_name");
    private final By itemPrices             = By.cssSelector("div.inventory_item_price");
    private final By checkoutButton         = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ── Actions ─────────────────────────────────────────────────────

    /** Return the number of items in the cart list. */
    public int getCartItemCount() {
        return findAll(cartItems).size();
    }

    /** Return a list of product names shown in the cart. */
    public List<String> getCartItemNames() {
        find(itemNames); // wait for at least one item
        List<WebElement> elements = findAll(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText());
        }
        return names;
    }

    /** Return a list of price strings shown in the cart. */
    public List<String> getCartItemPrices() {
        List<WebElement> elements = findAll(itemPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement el : elements) {
            prices.add(el.getText());
        }
        return prices;
    }

    /** Proceed to the checkout information step. */
    public CheckoutStepOnePage clickCheckout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }

    /** Go back to the products page. */
    public ProductsPage clickContinueShopping() {
        click(continueShoppingButton);
        return new ProductsPage(driver);
    }

    /** Remove a specific product from the cart by its name. */
    public void removeItemByName(String productName) {
        String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
        click(By.id(buttonId));
    }
}
