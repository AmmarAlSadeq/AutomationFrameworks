package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver; // WebDriver instance for browser interaction

    // Web elements for the checkout form
    @FindBy(id = "first-name")
    private WebElement firstNameField; // First name input field

    @FindBy(id = "last-name")
    private WebElement lastNameField; // Last name input field

    @FindBy(id = "postal-code")
    private WebElement zipCodeField; // ZIP code input field

    @FindBy(id = "continue")
    private WebElement continueButton; // Continue button

    @FindBy(id = "finish")
    private WebElement finishButton; // Finish button

    // Web elements for the overview page
    @FindBy(className = "summary_total_label")
    private WebElement totalAmountLabel; // Total amount label

    @FindBy(className = "complete-header")
    private WebElement thankYouMessage; // "Thank You" message element

    @FindBy(className = "complete-text")
    private WebElement orderDispatchedMessage; // "Order Dispatched" message element

    /**
     * Constructor to initialize the CheckoutPage.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }

    /**
     * Verifies if the checkout page is displayed by checking the current URL.
     *
     * @return True if the URL contains "checkout-step-one.html", otherwise false.
     */
    public boolean isCheckoutPageDisplayed() {
        return driver.getCurrentUrl().contains("checkout-step-one.html");
    }

    /**
     * Verifies if the overview page is displayed by checking the current URL.
     *
     * @return True if the URL contains "checkout-step-two.html", otherwise false.
     */
    public boolean isOverviewPageDisplayed() {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }

    /**
     * Fills the checkout form with the provided first name, last name, and ZIP code.
     *
     * @param firstName The first name to be entered.
     * @param lastName  The last name to be entered.
     * @param zipCode   The ZIP code to be entered.
     */
    public void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
    }

    /**
     * Clicks the Continue button to proceed to the next step.
     */
    public void clickContinueButton() {
        continueButton.click();
    }

    /**
     * Clicks the Finish button to complete the checkout process.
     */
    public void clickFinishButton() {
        finishButton.click();
    }

    /**
     * Retrieves the total amount of items before taxes from the overview page.
     *
     * @return The text of the total amount label.
     */
    public String getItemsTotalAmount() {
        return totalAmountLabel.getText();
    }

    /**
     * Verifies if the "Thank You" message is displayed on the order confirmation page.
     *
     * @return True if the message is displayed and matches the expected text, otherwise false.
     */
    public boolean isThankYouMessageDisplayed() {
        return thankYouMessage.isDisplayed() && thankYouMessage.getText().equals("Thank you for your order!");
    }

    /**
     * Verifies if the "Order Dispatched" message is displayed on the order confirmation page.
     *
     * @return True if the message is displayed and contains the expected text, otherwise false.
     */
    public boolean isOrderDispatchedMessageDisplayed() {
        return orderDispatchedMessage.isDisplayed() && orderDispatchedMessage.getText().contains("Your order has been dispatched");
    }
}