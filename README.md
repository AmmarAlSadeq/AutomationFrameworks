# Automation Frameworks Assignment

## Overview

This repository contains three automation frameworks implemented in Java:

1. **Cucumber Framework** (for UI testing using Selenium and Cucumber)
2. **Appium Framework** (for mobile automation using Appium)
3. **REST Assured Framework** (for API testing using REST Assured)

All frameworks are designed to be executed using Maven (`mvn`), and they generate Extent Reports for test execution results.

## Frameworks Details

### 1. UI Automation Framework (Selenium + Cucumber)

**Features:**
- Implemented using Java, Selenium, and Cucumber.
- Supports Chrome and Firefox (browser selection via a configuration file).
- Uses Page Object Model (POM) for test structure.
- URL is configurable from a properties file.
- Supports parallel execution.
- Uses data-driven testing with an external data file.

**Test Scenarios:**
- ✅ Scenario 1: Login with invalid credentials and verify error messages.
- ✅ Scenario 2: Login with valid credentials, add products to the cart, complete checkout, and verify order details.

**Execution:**
- You can Run all UI tests with Maven using **mvn test** command after changing directory to CucumbreFramework path:
- or Manually run tests from `CucumberTestRunner` file.

---

### 2. Mobile Automation Framework (Appium)

**Features:**
- Implemented using Java and Appium.
- Covers positive and negative login scenarios.
- Asserts successful login.
- Uses the Sauce Labs sample Android app for testing.
- Uses TestNG for execution.
- Uses Page Object Model (POM) design pattern.

**Test Scenarios:**
- ✅ Scenario 1: Login with valid credentials and assert successful login.
- ✅ Scenario 2: Login with invalid credentials and verify error messages.

**Execution:**
- You can Run all mobile tests with Maven using **mvn test** command after changing directory to AppiumFramework path:
- or Manually run tests from `testng.xml` file.

---

### 3. API Automation Framework (REST Assured)

**Features:**
- Implemented using Java and REST Assured.
- Covers basic API operations: GET, POST, PATCH, DELETE.
- Uses a demo API: Simple Books API.
- Uses TestNG for execution.
- Generates detailed Extent Reports for API results.
- Uses Page Object Model (POM) design pattern.

**Execution:**
- You can Run all mobile tests with Maven using **mvn test** command after changing directory to RestAssuredFramework path:
- or Manually run tests from `testng.xml` file.

---

## Reports

Each framework generates an Extent Report after execution, you can find the report in `reports` folder
**Note**: Cucumber Framework generates two reports, Cucumber HTML report and Extent Report
---

## Additional Features

- ✔ Supports parallel execution for UI tests.
- ✔ Uses data-driven testing with external data files.
- ✔ Uses configurable properties for browser and URLs.
- ✔ Generates detailed reports for all test executions.
- ✔ Implements best practices in automation design patterns.
- ✔ Uses Page Object Model (POM) for better maintainability.

---

## Repository URL

📌 [GitHub Repository](https://github.com/AmmarAlSadeq/AutomationFrameworks)

---

## Contributing

If you have suggestions or improvements, feel free to fork the repository and submit a pull request! 🚀

---

## Author

📌 This project is created by **Ammar Alsadeq**.