package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver; // WebDriver instance for browser interaction

    // Locators for login page elements
    private By usernameField = By.id("user-name"); // Username input field
    private By passwordField = By.id("password"); // Password input field
    private By loginButton = By.id("login-button"); // Login button
    private By errorMessage = By.xpath("//*[@data-test=\"error\"]"); // Error message element

    /**
     * Constructor to initialize the LoginPage.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Enters the provided username into the username field.
     *
     * @param username The username to be entered.
     */
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Enters the provided password into the password field.
     *
     * @param password The password to be entered.
     */
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    /**
     * Retrieves the error message displayed on the login page.
     *
     * @return The text of the error message.
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    /**
     * Performs the login action by entering the username, password, and clicking the login button.
     *
     * @param username The username to be entered.
     * @param password The password to be entered.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}