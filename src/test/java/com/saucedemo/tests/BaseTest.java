package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.utils.DriverFactory;
import com.saucedemo.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest – sets up and tears down WebDriver for each test method.
 * Also captures a screenshot automatically whenever a test fails.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.createDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                String testName = result.getMethod().getMethodName();
                ScreenshotUtil.capture(driver, "FAIL_" + testName);
            }
            DriverFactory.quitDriver(driver);
        }
    }
}
