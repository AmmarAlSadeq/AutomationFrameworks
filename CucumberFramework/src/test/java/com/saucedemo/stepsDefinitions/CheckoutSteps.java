package com.saucedemo.stepsDefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.testUtils.WebBaseClass;
import com.saucedemo.utils.WebUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.*;

public class CheckoutSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    // Constructor initializes WebDriver and page objects
    public CheckoutSteps() {
        driver = WebBaseClass.getDriver();  // Get the thread-local WebDriver instance
        loginPage = new LoginPage(driver);  // Initialize LoginPage
        productsPage = new ProductsPage(driver);  // Initialize ProductsPage
        cartPage = new CartPage(driver);  // Initialize CartPage
        checkoutPage = new CheckoutPage(driver);  // Initialize CheckoutPage
    }

    // Step definition for navigating to the Sauce Demo website
    @Given("I visit the sauce demo")
    public void iVisitTheSauceDemo() {
        String baseUrl = WebBaseClass.getProperties().getProperty("baseUrl");  // Get base URL from properties
        driver.get(baseUrl);  // Navigate to the base URL
    }

    // Step definition for logging in with valid credentials
    @When("I login with valid username {string} and password {string}")
    public void iLoginWithValidUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);  // Perform login using provided credentials
    }

    // Step definition for verifying the Products page is displayed
    @Then("I should see the Products page")
    public void iShouldSeeTheProductsPage() {
        assertTrue(productsPage.isProductsPageDisplayed());  // Assert that the Products page is displayed
    }

    // Step definition for adding the two most expensive products to the cart
    @When("I add the two most expensive products to the cart")
    public void iAddTheTwoMostExpensiveProductsToTheCart() {
        productsPage.addTwoMostExpensiveProductsToCart();  // Add the two most expensive products to the cart
    }

    // Step definition for clicking the cart button
    @And("I click on the cart button")
    public void iClickOnTheCartButton() {
        WebUtils.scrollPage(driver, "up");  // Scroll the page up for better visibility

        // Wait until the cart button is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize explicit wait
        WebElement cartButton = productsPage.getCartButton();  // Get the cart button element
        wait.until(ExpectedConditions.visibilityOf(cartButton));  // Wait until the cart button is visible

        productsPage.clickCartButton();  // Click the cart button
    }

    // Step definition for verifying the Cart page displays the selected products
    @Then("I should see the Cart page with the selected products")
    public void iShouldSeeTheCartPageWithTheSelectedProducts() {
        assertTrue(cartPage.areSelectedProductsDisplayed());  // Assert that the selected products are displayed
    }

    // Step definition for clicking the Checkout button
    @When("I click on the Checkout button")
    public void iClickOnTheCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize explicit wait
        WebElement checkoutButton = productsPage.getCartButton();  // Get the Checkout button element
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));  // Wait until the Checkout button is clickable
        cartPage.clickCheckoutButton();  // Click the Checkout button
    }

    // Step definition for verifying the Checkout page is displayed
    @Then("I should see the Checkout page")
    public void iShouldSeeTheCheckoutPage() {
        assertTrue(checkoutPage.isCheckoutPageDisplayed());  // Assert that the Checkout page is displayed
    }

    // Step definition for filling the checkout form with user details
    @When("I fill the checkout form with {string} {string} {string}")
    public void iFillTheCheckoutFormWith(String firstName, String lastName, String zipCode) {
        checkoutPage.fillCheckoutForm(firstName, lastName, zipCode);  // Fill the form with provided details
    }

    // Step definition for clicking the Continue button
    @And("I click on the Continue button")
    public void iClickOnContinueButton() {
        checkoutPage.clickContinueButton();  // Click the Continue button
    }

    // Step definition for verifying the Overview page is displayed
    @Then("I should see the Overview page")
    public void iShouldSeeTheOverviewPage() {
        assertTrue(checkoutPage.isOverviewPageDisplayed());  // Assert that the Overview page is displayed
    }

    // Step definition for verifying the total amount before taxes
    @And("I should verify the items total amount before taxes")
    public void iShouldVerifyTheItemsTotalAmountBeforeTaxes() {
        String totalAmount = checkoutPage.getItemsTotalAmount();  // Get the total amount
        assertNotNull(totalAmount);  // Assert that the total amount is not null
    }

    // Step definition for verifying the current URL matches the expected URL
    @And("I should verify the URL matches {string}")
    public void iShouldVerifyTheURLMatches(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();  // Get the current URL
        assertEquals(expectedUrl, currentUrl);  // Assert that the current URL matches the expected URL
    }

    // Step definition for clicking the Finish button
    @When("I click on the Finish button")
    public void iClickOnTheOverviewButton() {
        checkoutPage.clickFinishButton();  // Click the Finish button
    }

    // Step definition for verifying the "Thank You" and "Order Dispatched" messages
    @Then("I should see the {string} and {string} messages")
    public void iShouldSeeTheThankYouAndOrderDispatchedMessages(String message1, String message2) {
        assertTrue(checkoutPage.isThankYouMessageDisplayed());  // Assert that the "Thank You" message is displayed
        assertTrue(checkoutPage.isOrderDispatchedMessageDisplayed());  // Assert that the "Order Dispatched" message is displayed
    }
}
