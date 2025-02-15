package com.saucedemo.stepsDefinitions;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.testUtils.WebBaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import java.util.Properties;
import static org.junit.Assert.*;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private Properties properties;

    // Constructor initializes WebDriver, Properties, and LoginPage
    public LoginSteps() {
        driver = WebBaseClass.getDriver();  // Get the thread-local WebDriver instance
        this.properties = WebBaseClass.getProperties();  // Load properties for configuration
        loginPage = new LoginPage(driver);  // Initialize LoginPage with the WebDriver
    }

    // Step definition for visiting the login page
    @Given("I visit the login page")
    public void iVisitTheLoginPage() {
        String baseUrl = properties.getProperty("baseUrl");  // Get base URL from properties
        driver.get(baseUrl);  // Navigate to the login page
    }

    // Step definition for logging in with a username and password
    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);  // Enter the provided username
        loginPage.enterPassword(password);  // Enter the provided password
        loginPage.clickLoginButton();  // Click the login button
    }

    // Step definition for verifying the error message
    @Then("I should see the {string} error message")
    public void iShouldSeeTheErrorMessage(String expectedResult) {
        String errorMessage = loginPage.getErrorMessage();  // Get the error message from the page
        assertEquals(expectedResult, errorMessage);  // Assert that the error message matches the expected result
    }
}
