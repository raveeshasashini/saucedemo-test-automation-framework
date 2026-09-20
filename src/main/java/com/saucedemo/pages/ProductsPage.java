package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductsPage – Page Object for the SauceDemo inventory / products listing.
 * URL: https://www.saucedemo.com/inventory.html
 */
public class ProductsPage extends BasePage {

    // ── Locators ────────────────────────────────────────────────────
    private final By pageTitle     = By.cssSelector("span.title");
    private final By productItems  = By.cssSelector("div.inventory_item");
    private final By productNames  = By.cssSelector("div.inventory_item_name");
    private final By productPrices = By.cssSelector("div.inventory_item_price");
    private final By cartBadge     = By.cssSelector("span.shopping_cart_badge");
    private final By cartLink      = By.cssSelector("a.shopping_cart_link");
    private final By burgerMenu    = By.id("react-burger-menu-btn");
    private final By logoutLink    = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // ── Actions ─────────────────────────────────────────────────────

    /** Return the page header text (e.g. "Products"). */
    public String getPageTitle() {
        return getText(pageTitle);
    }

    /** Return the number of products displayed. */
    public int getProductCount() {
        return findAll(productItems).size();
    }

    /** Click "Add to cart" for a product identified by its visible name. */
    public void addProductToCartByName(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        click(By.id(buttonId));
    }

    /** Click "Remove" for a product identified by its visible name. */
    public void removeProductFromCartByName(String productName) {
        String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
        click(By.id(buttonId));
    }

    /** Return the number shown on the cart badge, or 0 if badge is not present. */
    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(badges.get(0).getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /** Wait for the cart badge count to equal an expected value. */
    public void waitForCartBadgeCount(int expectedCount) {
        wait.until(d -> getCartBadgeCount() == expectedCount);
    }

    /** Wait for the products page to be fully loaded. */
    public void waitForPageLoaded() {
        find(pageTitle);
        find(productItems);
    }

    /** Click the cart icon to navigate to the cart page. */
    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    /** Open the side menu and click Logout. */
    public LoginPage logout() {
        click(burgerMenu);
        // Wait for the sidebar animation to complete and link to be clickable
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        try {
            logout.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
        }
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        return loginPage;
    }

    /** Return a list of every product name on the page. */
    public List<String> getAllProductNames() {
        List<WebElement> elements = findAll(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText());
        }
        return names;
    }

    /** Return a list of every product price string on the page. */
    public List<String> getAllProductPrices() {
        List<WebElement> elements = findAll(productPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement el : elements) {
            prices.add(el.getText());
        }
        return prices;
    }
}
