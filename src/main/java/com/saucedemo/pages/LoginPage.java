package com.saucedemo.pages;

import com.saucedemo.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage – Page Object for the SauceDemo login screen.
 * URL: https://www.saucedemo.com
 */
public class LoginPage extends BasePage {

    // ── Locators ────────────────────────────────────────────────────
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ── Navigation ──────────────────────────────────────────────────
    public void open() {
        driver.get(Config.BASE_URL);
        waitForPageLoaded();
    }

    /** Wait for login page elements to be visible. */
    public void waitForPageLoaded() {
        findVisible(usernameInput);
        findVisible(loginButton);
    }

    // ── Actions ─────────────────────────────────────────────────────

    /** Type a username into the username field. */
    public void enterUsername(String username) {
        typeText(usernameInput, username);
    }

    /** Type a password into the password field. */
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    /** Click the Login button. */
    public void clickLogin() {
        click(loginButton);
    }

    /** Complete the full login flow in one call. */
    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductsPage(driver);
    }

    /** Return the text of the error banner. */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /** Check whether the error banner is visible. */
    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    /** Check whether the login button is visible. */
    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }

    /** Check whether the username input field is visible. */
    public boolean isUsernameInputDisplayed() {
        return isDisplayed(usernameInput);
    }

    /** Check whether the password input field is visible. */
    public boolean isPasswordInputDisplayed() {
        return isDisplayed(passwordInput);
    }

    /** Get the value attribute of the login button. */
    public String getLoginButtonValue() {
        return findVisible(loginButton).getAttribute("value");
    }
}
