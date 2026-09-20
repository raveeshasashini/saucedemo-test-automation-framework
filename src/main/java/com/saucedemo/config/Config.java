package com.saucedemo.config;

/**
 * Centralized configuration for the SauceDemo test suite.
 * URLs, timeouts, and browser options are managed here.
 */
public final class Config {

    // ── SauceDemo URLs ──────────────────────────────────────────────
    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String INVENTORY_URL = BASE_URL + "/inventory.html";
    public static final String CART_URL = BASE_URL + "/cart.html";
    public static final String EXPECTED_TITLE = "Swag Labs";

    // ── Browser settings ────────────────────────────────────────────
    public static boolean isHeadless() {
        String headless = System.getProperty("headless", System.getenv("HEADLESS"));
        if (headless == null) return true; // headless by default
        return !"false".equalsIgnoreCase(headless);
    }

    // ── Timeouts (seconds) ──────────────────────────────────────────
    public static final int EXPLICIT_WAIT_SECONDS = 15;
    public static final int PAGE_LOAD_TIMEOUT_SECONDS = 60;

    // ── Paths ───────────────────────────────────────────────────────
    public static final String SCREENSHOT_DIR = "screenshots";

    private Config() {
        // utility class – no instantiation
    }
}
