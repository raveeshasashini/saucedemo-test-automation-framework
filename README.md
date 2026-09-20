# SauceDemo QA Automation Framework (Selenium WebDriver & Java)

> Enterprise-grade automated End-to-End (UI) and REST API test framework for [SauceDemo](https://www.saucedemo.com), engineered using **Java 17**, **Selenium WebDriver 4.29**, **TestNG 7.10**, **Maven**, and **Postman** adhering to the **Page Object Model (POM)** design pattern.

---

## 📌 Framework Choice & Engineering Rationale

For this QA automation assignment, I chose **Selenium WebDriver with Java, TestNG, and Maven**:
1. **Industry Benchmark & Ecosystem**: Selenium WebDriver with Java remains the most widely adopted enterprise test automation stack worldwide. It integrates seamlessly with CI/CD systems, enterprise cloud grids (Sauce Labs, BrowserStack), and reporting tools.
2. **Page Object Model (POM)**: Enforces strict separation between web element locators, user actions, and test assertions. When UI layouts or DOM selectors change, updates are confined to a single Page Object class without altering test logic.
3. **Robust TestNG Engine**: TestNG provides fine-grained test management, flexible suite execution via `testng.xml`, descriptive `@Test` annotations, priority ordering, assertions, and built-in HTML/XML Surefire reporting.
4. **Decoupled Test Data & Configuration**: All target URLs, timeouts, headless toggles, credentials, error message constants, and product fixtures are centralized in `Config.java` and `TestData.java`, eliminating hardcoded magic strings.
5. **Postman API Test Suite**: Comprehensive automated API test collection and environment for `https://reqres.in`, validating HTTP status codes, headers, response schemas, and payload bodies with automated JavaScript test scripts.
6. **Automatic Defect Evidence**: Integrated screenshot utility (`ScreenshotUtil.java`) that automatically captures timestamped browser screenshots upon test failure.

---

## 🏗 Project Architecture & Structure

```
saucedemo-qa-automation/
├── .github/
│   └── workflows/
│       └── test.yml                     # Task 5 (Bonus B): CI pipeline on push/PR
├── pom.xml                              # Maven build file with dependencies & plugins
├── testng.xml                           # TestNG test suite runner configuration
├── BUG_REPORT.md                        # Task 4: Problem_user defect report with screenshots
├── postman/                             # Task 5 (Bonus A): Postman API test collection & environment
│   ├── ReqRes_API_Tests.postman_collection.json
│   └── ReqRes_API_Environment.postman_environment.json
├── screenshots/
│   └── problem_user_bugs/               # Defect evidence screenshots for BUG_REPORT.md
│       ├── BUG-01_dog_images_inventory.png
│       ├── BUG-02_add_to_cart_failure.png
│       ├── BUG-03_checkout_lastname_blocker.png
│       ├── BUG-04_sort_dropdown_unresponsive.png
│       └── BUG-05_item_detail_behavior.png
├── src/
│   ├── main/java/com/saucedemo/
│   │   ├── config/
│   │   │   └── Config.java              # Centralized environment URLs & timeouts
│   │   ├── constants/
│   │   │   └── TestData.java            # Decoupled credentials, items & error strings
│   │   ├── pages/                       # Page Object Model implementations
│   │   │   ├── BasePage.java            # Shared explicit wait helpers & utilities
│   │   │   ├── LoginPage.java           # Login page locators & user actions
│   │   │   ├── ProductsPage.java        # Inventory catalog, cart & burger menu
│   │   │   ├── CartPage.java            # Cart verification, remove & checkout
│   │   │   ├── CheckoutStepOnePage.java # Customer information form
│   │   │   ├── CheckoutStepTwoPage.java # Order summary, tax & total calculations
│   │   │   └── CheckoutCompletePage.java# Order dispatch confirmation screen
│   │   └── utils/
│   │       ├── DriverFactory.java       # Chrome WebDriver factory (headless/headed)
│   │       └── ScreenshotUtil.java      # Screenshot capture on test failure
│   └── test/java/com/saucedemo/tests/
│       ├── BaseTest.java                # Test lifecycle & automatic failure screenshot
│       ├── SanityTest.java              # Task 1: Environment sanity / smoke test
│       ├── LoginTest.java               # Task 2: Comprehensive login & logout suite
│       └── PurchaseE2ETest.java         # Task 3: Full E2E purchase flow & calculations
└── README.md                            # Complete setup and execution documentation
```

---

## ⚙️ Prerequisites

- **Java Development Kit (JDK)**: JDK 17 or higher (`java -version`)
- **Apache Maven**: 3.8.x or 3.9.x (`mvn -version`)
- **Google Chrome**: Latest stable version installed locally
- **Postman**: Desktop app or Newman CLI (for running API tests)
- **IDE (Recommended)**: IntelliJ IDEA (Community or Ultimate Edition)

---

## 💻 Opening & Running in IntelliJ IDEA (Step-by-Step)

If you or your team use **IntelliJ IDEA**, follow these exact steps for a 1-click import and execution:

### Step 1: Clone and Open in IntelliJ IDEA
1. Clone the repository:
   ```bash
   git clone https://github.com/raveeshasashini/saucedemo-test-automation-framework.git
   ```
2. Launch IntelliJ IDEA.
3. Click **Open** (or go to `File` ➔ `Open...`).
4. Browse to and select the `saucedemo-test-automation-framework` root folder (the folder containing `pom.xml`).
5. Click **Trust Project** when prompted.

### Step 2: Configure Project SDK (JDK 17+)
1. Open Project Structure: press `Ctrl + Alt + Shift + S` (or `File` ➔ `Project Structure...`).
2. Under **Project Settings** ➔ **Project**:
   - **SDK**: Select **JDK 17** (or higher). If none is listed, click `Add SDK` ➔ `Download JDK...` ➔ choose Version 17 (e.g., Eclipse Temurin or Amazon Corretto).
   - **Language level**: Select `17 - Sealed types, always-strict floating-point` (or `SDK default`).
3. Click **Apply** and **OK**.

### Step 3: Sync Maven Dependencies
1. Open the **Maven Tool Window** on the right sidebar (or `View` ➔ `Tool Windows` ➔ `Maven`).
2. Click the **Reload All Maven Projects** button (🔄 circular arrows at the top-left of the Maven pane).
3. IntelliJ will automatically resolve and index all dependencies (Selenium 4.29, TestNG 7.10, SLF4J).

### Step 4: Running Tests in IntelliJ (3 Easy Ways)

#### Option A: Run Full Suite via `testng.xml` (Recommended)
1. In the Project Explorer, locate `testng.xml` at the root directory.
2. **Right-click `testng.xml`** ➔ Click **`Run '...\testng.xml'`** (or press `Ctrl + Shift + F10`).
3. IntelliJ will launch the suite using its native TestNG runner, showing a real-time test tree with pass/fail badges, elapsed time, and logs.

#### Option B: Run Individual Test Classes or Single Test Methods
1. In the Project Explorer, expand `src/test/java/com/saucedemo/tests/`:
   - `SanityTest.java` — Task 1: Environment sanity / smoke test
   - `LoginTest.java` — Task 2: Authentication & logout suite
   - `PurchaseE2ETest.java` — Task 3: Full end-to-end checkout & tax calculation flow
2. Click the green **Play (▶)** gutter icon:
   - Next to the **Class name** to run all tests in that class.
   - Next to any **individual `@Test` method** to run just that single test scenario.

#### Option C: Run via Maven Tool Window
1. Open the **Maven** sidebar on the right.
2. Expand `saucedemo-qa-automation` ➔ `Lifecycle`.
3. Double-click **`test`** (or right-click `test` ➔ `Run 'saucedemo-qa-automation [test]'`).

### Step 5: Watch Tests Live in Browser (Headed Mode in IntelliJ)
By default, tests run in **headless** mode for high speed and CI compatibility. To watch Chrome open visibly on your screen:
1. In the top toolbar, click the dropdown next to the green Play button and select **Edit Configurations...** (or `Run` ➔ `Edit Configurations...`).
2. In the left panel, select your TestNG configuration (e.g., `testng.xml` or `PurchaseE2ETest`).
3. In the **Environment variables** field, enter:
   ```
   HEADLESS=false
   ```
4. Click **Apply** and **OK**.
5. Click **Run (▶)** — Chrome will now launch visibly and execute the interactions on your screen!

### 💡 IntelliJ Troubleshooting Tips
- **TestNG Plugin**: The TestNG plugin is bundled with IntelliJ by default. If `@Test` is not highlighted, verify it in `Settings` (`Preferences` on macOS) ➔ `Plugins` ➔ `Installed` ➔ ensure **TestNG** is enabled.
- **Red squiggly lines on imports**: Right-click `pom.xml` ➔ `Maven` ➔ `Reload Project`.

---

## 🚀 Running via Command Line (Terminal / CLI)

If you prefer executing from the terminal (Windows PowerShell, CMD, macOS, or Linux):

### 1. Compile and Verify Build
```bash
mvn clean compile test-compile
```

### 2. Run Full Test Suite (All UI Tasks via TestNG)
```bash
mvn clean test
```

### 3. Run by Specific Task / Test Class
```bash
# Task 1: Sanity / Smoke Tests
mvn test -Dtest=SanityTest

# Task 2: Login & Logout Flow (Positive, Negative, Validations, Locked Out)
mvn test -Dtest=LoginTest

# Task 3: End-to-End Purchase Flow & Calculations
mvn test -Dtest=PurchaseE2ETest
```

### 4. Visual Headed Mode (Watch Browser Execution in Terminal)
```bash
# Windows PowerShell
$env:HEADLESS="false"; mvn test -Dtest=PurchaseE2ETest

# Windows CMD
set HEADLESS=false && mvn test -Dtest=PurchaseE2ETest

# Linux / macOS
HEADLESS=false mvn test -Dtest=PurchaseE2ETest
```

---

## 📮 API Testing with Postman (Task 5 - Bonus Option A)

The automated REST API test suite targets `https://reqres.in` and is located in the [`postman/`](./postman) directory:
- **Collection**: `postman/ReqRes_API_Tests.postman_collection.json`
- **Environment**: `postman/ReqRes_API_Environment.postman_environment.json`

### Endpoints Covered:
1. `GET /api/users?page=2` — Verify paginated user list (Status 200, schema & array assertions)
2. `POST /api/users` — Create new user (Status 201, verify returned name, job, id, and timestamp)
3. `PUT /api/users/2` — Update user details (Status 200, verify updated job and timestamp)
4. `DELETE /api/users/2` — Delete user (Status 204 No Content)
5. `GET /api/users/23` — Negative test: User not found (Status 404, error response validation)

### How to Run in Postman (Desktop App)
1. Open the **Postman** desktop application.
2. Click the **Import** button in the top-left corner.
3. Drag and drop or browse to select both:
   - `postman/ReqRes_API_Tests.postman_collection.json`
   - `postman/ReqRes_API_Environment.postman_environment.json`
4. In the top-right environment selector, choose **ReqRes Environment**.
5. Select the **ReqRes API Tests** collection from the sidebar, click the **Run** (or `...` ➔ `Run collection`) button.
6. Click **Run ReqRes API Tests** — all 5 test requests and assertions will run and pass!

### How to Run via Newman CLI (Optional)
If Newman (Postman CLI runner) is installed:
```bash
npx newman run postman/ReqRes_API_Tests.postman_collection.json -e postman/ReqRes_API_Environment.postman_environment.json
```

---

## 📊 Test Reporting & Diagnostics

- **Surefire Test Reports**: After every test execution, detailed HTML and XML reports are generated in:
  `target/surefire-reports/index.html`
  `target/surefire-reports/emailable-report.html`
  Open `index.html` in any browser to review test execution timelines, parameters, passed/failed statuses, and failure stack traces.
- **Automated Failure Screenshots**: If any UI test encounters an assertion or runtime failure, `ScreenshotUtil` automatically captures a full-page PNG screenshot with timestamp in `screenshots/`.

---

## 🎯 Coverage Breakdown

| Task | Scope | Scenarios Covered |
|---|---|---|
| **Task 1: Setup** | Sanity | Application availability, page URL & title verification ("Swag Labs"), login form inputs and button presence |
| **Task 2: Login** | Positive & Negative | Valid credentials (`standard_user`), invalid credentials, empty username validation, empty password validation, locked-out user (`locked_out_user`), sidebar menu logout & redirect |
| **Task 3: E2E Purchase** | Complete Flow | Multi-item cart additions, badge counter dynamic update, cart item inspection, customer checkout form input, tax (8%) and subtotal calculation verification, order completion & Pony Express dispatch banner |
| **Task 4: Bug Hunt** | Exploration | 5 documented defects in [`BUG_REPORT.md`](./BUG_REPORT.md) found using `problem_user`, complete with Bug IDs, Severity, Priority, Steps to Reproduce, Actual vs. Expected results, and embedded screenshot evidence |
| **Task 5: Bonus A** | REST API | Postman collection & environment for `https://reqres.in`: GET paginated users, POST create user, PUT update user, DELETE user (204), and GET 404 user not found |
| **Task 5: Bonus B** | CI Pipeline | Automated GitHub Actions workflow (`.github/workflows/test.yml`) executing on push/PR with JDK 17, headless Chrome, Maven TestNG runner, and Surefire report artifact archiving |

---

## 🎁 Bonus Work Details

Both bonus options were implemented:
1. **Option A — REST API Automation (`postman/ReqRes_API_Tests.postman_collection.json`)**:
   Implemented with a modular **Postman Collection & Environment**, validating status codes (200, 201, 204, 404), JSON schema attributes, and response body payloads against `https://reqres.in` using automated JavaScript test assertions.
2. **Option B — GitHub Actions CI (`.github/workflows/test.yml`)**:
   Automated workflow running on Ubuntu, configuring JDK 17, headless Google Chrome, executing `mvn clean test`, and uploading Surefire test reports as downloadable build artifacts.

---

## 💡 What I'd Do Differently With More Time

1. **Cross-Browser & Parallel Execution**: Configure TestNG parallel execution (`parallel="tests"` or `parallel="methods"`) with cross-browser testing across Firefox and Edge.
2. **Allure Reporting**: Integrate Allure Framework TestNG adapter for interactive reporting dashboards, historical trend charts, and automatic screenshot attachments.
3. **Data-Driven Testing (TestNG DataProviders)**: Implement `@DataProvider` methods backed by CSV/JSON files to test multiple user personas and product permutations dynamically.
4. **Dockerized Selenium Grid**: Provide a `docker-compose.yml` to spin up isolated Selenium Grid Hub and Chrome/Firefox node containers for fully reproducible local or CI execution.
