package com.saucedemo.utils;

import com.saucedemo.config.Config;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for capturing and saving screenshots during test execution.
 * Primarily used to record failure states for bug evidence.
 */
public final class ScreenshotUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private ScreenshotUtil() {
        // utility class – no instantiation
    }

    /**
     * Take a screenshot and save it to the screenshots directory.
     *
     * @param driver   the active WebDriver session
     * @param testName a descriptive name for the screenshot file
     * @return the absolute path of the saved screenshot file
     */
    public static String capture(WebDriver driver, String testName) {
        try {
            Path dir = Paths.get(Config.SCREENSHOT_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            String timestamp = LocalDateTime.now().format(FORMATTER);
            String filename = testName + "_" + timestamp + ".png";
            Path destination = dir.resolve(filename);

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("📸 Screenshot saved: " + destination.toAbsolutePath());
            return destination.toAbsolutePath().toString();

        } catch (IOException e) {
            System.err.println("⚠️ Failed to save screenshot: " + e.getMessage());
            return "";
        }
    }
}
