package com.saucedemo.utils;

import com.saucedemo.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory for creating and managing Chrome WebDriver instances.
 * Supports headless and headed modes via the HEADLESS environment variable.
 */
public final class DriverFactory {

    static {
        // Suppress verbose Selenium, ChromeDriver and DevTools CDP warnings in console
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.chromium.ChromiumDriver").setLevel(Level.OFF);
    }

    private DriverFactory() {
        // utility class – no instantiation
    }

    /**
     * Build and return a configured Chrome WebDriver instance.
     */
    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        if (Config.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080",
                "--disable-gpu",
                "--disable-extensions",
                "--disable-background-networking",
                "--log-level=3",
                "--silent"
        );

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(Config.PAGE_LOAD_TIMEOUT_SECONDS));
        driver.manage().timeouts().scriptTimeout(
                Duration.ofSeconds(Config.PAGE_LOAD_TIMEOUT_SECONDS));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        return driver;
    }

    /**
     * Safely quit the driver and close all browser windows.
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
