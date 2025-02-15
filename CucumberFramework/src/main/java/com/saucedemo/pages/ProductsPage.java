package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames; // List of product name elements

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices; // List of product price elements

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton; // Cart button element

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons; // List of "Add to Cart" button elements

    /**
     * Constructor to initialize the ProductsPage.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }

    /**
     * Verifies if the Products page is displayed by checking the current URL.
     *
     * @return True if the URL contains "inventory.html", otherwise false.
     */
    public boolean isProductsPageDisplayed() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    /**
     * Clicks the cart button to navigate to the cart page.
     */
    public void clickCartButton() {
        cartButton.click();
    }

    /**
     * Returns the cart button WebElement.
     *
     * @return The cart button WebElement.
     */
    public WebElement getCartButton() {
        return cartButton;
    }

    /**
     * Adds the two most expensive products to the cart.
     * This method sorts the products by price in descending order and clicks the "Add to Cart" button
     * for the top two most expensive products.
     */
    public void addTwoMostExpensiveProductsToCart() {
        // Create a list of Product objects to store name, price, and addToCartButton
        List<Product> products = new ArrayList<>();

        // Iterate through the product names and prices to build the product list
        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i).getText();
            String priceText = productPrices.get(i).getText().replace("$", "").trim(); // Remove "$" and trim spaces
            double price = Double.parseDouble(priceText);

            // Add product info to the list
            products.add(new Product(name, price, addToCartButtons.get(i)));
        }

        // Sort the products list in descending order by price
        Collections.sort(products, Comparator.comparingDouble(Product::getPrice).reversed());

        // Add the two most expensive products to the cart
        for (int i = 0; i < 2; i++) {
            products.get(i).getAddToCartButton().click();
        }
    }

    /**
     * Inner class to represent a product with its name, price, and "Add to Cart" button.
     */
    private static class Product {
        private String name; // Product name
        private double price; // Product price
        private WebElement addToCartButton; // "Add to Cart" button for the product

        /**
         * Constructor to initialize a Product object.
         *
         * @param name            The name of the product.
         * @param price           The price of the product.
         * @param addToCartButton The "Add to Cart" button WebElement for the product.
         */
        public Product(String name, double price, WebElement addToCartButton) {
            this.name = name;
            this.price = price;
            this.addToCartButton = addToCartButton;
        }

        /**
         * Returns the price of the product.
         *
         * @return The price of the product.
         */
        public double getPrice() {
            return price;
        }

        /**
         * Returns the "Add to Cart" button WebElement for the product.
         *
         * @return The "Add to Cart" button WebElement.
         */
        public WebElement getAddToCartButton() {
            return addToCartButton;
        }
    }
}
