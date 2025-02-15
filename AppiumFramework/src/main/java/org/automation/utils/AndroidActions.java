package org.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;

/**
 * Utility class for performing Android-specific actions using Appium.
 * This class provides methods for gestures like long press, swipe, and drag-and-drop.
 */
public class AndroidActions extends AppiumUtils {
    AndroidDriver driver; // AndroidDriver instance for interacting with the device

    /**
     * Constructor to initialize the AndroidActions class.
     *
     * @param driver The AndroidDriver instance to use for performing actions.
     */
    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Performs a long press gesture on the specified element.
     *
     * @param element The WebElement to perform the long press on.
     */
    public void longPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), // Element ID for the gesture
                "duration", 2000 // Duration of the long press in milliseconds
        ));
    }

    /**
     * Performs a swipe gesture on the specified element in the given direction.
     *
     * @param element   The WebElement to swipe.
     * @param direction The direction of the swipe (e.g., "left", "right", "up", "down").
     */
    public void swipeToElementAction(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), // Element ID for the gesture
                "direction", direction, // Direction of the swipe
                "percent", 0.75 // Percentage of the swipe (75% of the element's size)
        ));
    }

    /**
     * Performs a drag-and-drop gesture on the specified element to the given coordinates.
     *
     * @param element           The WebElement to drag.
     * @param dropXCoordinates  The X-coordinate to drop the element at.
     * @param dropYCoordinates  The Y-coordinate to drop the element at.
     */
    public void dragAndDropAction(WebElement element, int dropXCoordinates, int dropYCoordinates) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), // Element ID for the gesture
                "endX", dropXCoordinates, // X-coordinate to drop the element
                "endY", dropYCoordinates // Y-coordinate to drop the element
        ));
    }
}
