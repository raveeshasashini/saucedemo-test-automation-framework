# Bug Report – SauceDemo Exploration (problem_user)

This bug report documents defects identified during exploratory testing of [SauceDemo](https://www.saucedemo.com) using the problem_user persona credentials (problem_user / secret_sauce).

**Tester:** QA Automation Intern  
**Date:** September 2026  
**Environment:** Chrome 134+ / Windows 11 / Desktop (1920x1080)  
**Target URL:** https://www.saucedemo.com  

---

## Executive Summary

The problem_user account exposes several severe defects across product discovery, inventory display, sorting, and checkout workflows.

---

## Defect Summary & Severity Matrix

| Bug ID | Title | Component | Severity | Priority | Status |
|---|---|---|---|---|---|
| **BUG-01** | All product cards display identical fallback dog image | Inventory (/inventory.html) | High | P2 | Open |
| **BUG-02** | "Add to cart" action fails for selected products | Inventory & Cart | Critical | P1 | Open |
| **BUG-03** | Last Name input field rejects input (Purchase Blocker) | Checkout Step One (/checkout-step-one.html) | **Blocker** | **P1** | Open |
| **BUG-04** | Inventory sorting dropdown has no effect on product ordering | Product Catalog Sorting | Medium | P3 | Open |
| **BUG-05** | Product detail page renders corrupted image/state | Product Details (/inventory-item.html) | High | P2 | Open |
