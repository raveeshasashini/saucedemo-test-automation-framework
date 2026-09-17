package com.saucedemo.constants;

/**
 * Decoupled test data – credentials, product fixtures, error messages,
 * and checkout details used across the test suite.
 * <p>
 * Keeping data separate from test logic makes tests easier to maintain
 * and eliminates hardcoded magic strings.
 */
public final class TestData {

    // ── Login Credentials ───────────────────────────────────────────
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    public static final String ERROR_USER = "error_user";
    public static final String VISUAL_USER = "visual_user";

    public static final String VALID_PASSWORD = "secret_sauce";

    // Invalid credentials for negative tests
    public static final String INVALID_USERNAME = "invalid_user_xyz";
    public static final String INVALID_PASSWORD = "wrong_password_123";

    // ── Error Messages ──────────────────────────────────────────────
    public static final String ERR_USERNAME_REQUIRED =
            "Epic sadface: Username is required";
    public static final String ERR_PASSWORD_REQUIRED =
            "Epic sadface: Password is required";
    public static final String ERR_CREDENTIALS_MISMATCH =
            "Epic sadface: Username and password do not match any user in this service";
    public static final String ERR_LOCKED_OUT =
            "Epic sadface: Sorry, this user has been locked out.";

    // ── Checkout Data ───────────────────────────────────────────────
    public static final String CHECKOUT_FIRST_NAME = "John";
    public static final String CHECKOUT_LAST_NAME = "Doe";
    public static final String CHECKOUT_POSTAL_CODE = "10001";

    // ── Product Data ────────────────────────────────────────────────
    public static final String PRODUCT_1_NAME = "Sauce Labs Backpack";
    public static final String PRODUCT_2_NAME = "Sauce Labs Bike Light";
    public static final double PRODUCT_1_PRICE = 29.99;
    public static final double PRODUCT_2_PRICE = 9.99;

    // ── Confirmation Page ───────────────────────────────────────────
    public static final String CONFIRMATION_HEADER = "Thank you for your order!";
    public static final String CONFIRMATION_TEXT =
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    private TestData() {
        // utility class – no instantiation
    }
}
