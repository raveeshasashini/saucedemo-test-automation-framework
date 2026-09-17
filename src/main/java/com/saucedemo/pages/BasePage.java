package com.saucedemo.pages;

import com.saucedemo.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * BasePage – Parent class for every Page Object.
 * <p>
 * Provides shared helpers so individual page classes stay thin
 * and focused on their own elements.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(Config.EXPLICIT_WAIT_SECONDS));
    }

    // ── Element helpers ─────────────────────────────────────────────

    /** Wait for an element to be located and return it. */
    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /** Wait for an element to be visible and return it. */
    protected WebElement findVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Return all matching elements (no wait). */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    /** Wait for an element to be clickable, then click it. */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /** Clear a field and type into it. */
    protected void typeText(By locator, String text) {
        WebElement element = findVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /** Return the visible text of an element. */
    protected String getText(By locator) {
        return findVisible(locator).getText();
    }

    /** Check whether an element is currently visible. */
    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ── Page-level helpers ──────────────────────────────────────────

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void open(String url) {
        driver.get(url);
    }
}
