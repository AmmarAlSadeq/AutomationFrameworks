package com.saucedemo.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WebUtils {
    /**
     * Scrolls the webpage up or down by a fixed amount of pixels (250px).
     * This method uses JavaScript to perform the scrolling action.
     *
     * @param driver    The WebDriver instance controlling the browser.
     * @param direction The direction to scroll. Valid values are "up" or "down" (case-insensitive).
     * @throws IllegalArgumentException If an invalid direction is provided.
     */
    public static void scrollPage(WebDriver driver, String direction) {
        JavascriptExecutor js = (JavascriptExecutor) driver;  // Cast WebDriver to JavascriptExecutor

        // Check the direction and scroll accordingly
        if (direction.equalsIgnoreCase("down")) {
            js.executeScript("window.scrollBy(0, 250);");  // Scroll down by 250 pixels
        } else if (direction.equalsIgnoreCase("up")) {
            js.executeScript("window.scrollBy(0, -250);");  // Scroll up by 250 pixels
        } else {
            throw new IllegalArgumentException("Invalid direction. Use 'up' or 'down'.");  // Handle invalid direction
        }
    }
}
