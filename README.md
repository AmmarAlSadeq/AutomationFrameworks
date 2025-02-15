# Automation Frameworks Assignment

This repository contains three automation frameworks implemented in Java:

- **Cucumber Framework** (for UI testing using Selenium and Cucumber)
- **Appium Framework** (for mobile automation using Appium)
- **REST Assured Framework** (for API testing using REST Assured)

All frameworks are designed to be executed using **Maven** (`mvn test`), and they generate **Extent Reports** for test execution results.

---

## Table of Contents

1. [Overview](#overview)
2. [Frameworks Details](#frameworks-details)
    - [UI Automation Framework (Selenium + Cucumber)](#ui-automation-framework-selenium--cucumber)
    - [Mobile Automation Framework (Appium)](#mobile-automation-framework-appium)
    - [API Automation Framework (REST Assured)](#api-automation-framework-rest-assured)
3. [Reports](#reports)
4. [How to Run All Tests](#how-to-run-all-tests)
5. [Additional Features](#additional-features)
6. [Repository URL](#repository-url)
7. [Contributing](#contributing)

---

## Overview

This repository contains the implementation of three different automation frameworks for testing:

- **UI Automation** using **Selenium** and **Cucumber**
- **Mobile Automation** using **Appium**
- **API Automation** using **REST Assured**

All frameworks are integrated with **Maven**, and generate **Extent Reports** for detailed test execution insights.

---

## Frameworks Details

### 1. UI Automation Framework (Selenium + Cucumber)

#### Features:
- Implemented using Java, Selenium, and Cucumber.
- Supports **Chrome** and **Firefox** (browser selection via a configuration file).
- Uses **Page Object Model** (POM) for test structure.
- URL is configurable from a properties file.
- Supports **parallel execution**.
- Uses **data-driven testing** with an external data file.

#### Test Scenarios:
- ✅ **Scenario 1**: Login with invalid credentials and verify error messages.
- ✅ **Scenario 2**: Login with valid credentials, add products to the cart, complete checkout, and verify order details.

#### Execution:
- Run all UI tests with Maven:
  ```bash
  mvn test
