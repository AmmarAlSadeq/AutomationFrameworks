package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    private WebDriver driver; // WebDriver instance for browser interaction

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems; // List of cart item elements

    @FindBy(id = "checkout")
    private WebElement checkoutButton; // Checkout button element

    /**
     * Constructor to initialize the CartPage.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }

    /**
     * Verifies if the cart page is displayed by checking the current URL.
     *
     * @return True if the URL contains "cart.html", otherwise false.
     */
    public boolean isCartPageDisplayed() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    /**
     * Verifies if the selected products are displayed in the cart.
     *
     * @return True if the cart contains items, otherwise false.
     */
    public boolean areSelectedProductsDisplayed() {
        return !cartItems.isEmpty();
    }

    /**
     * Clicks the checkout button to proceed to the checkout page.
     */
    public void clickCheckoutButton() {
        checkoutButton.click();
    }
}