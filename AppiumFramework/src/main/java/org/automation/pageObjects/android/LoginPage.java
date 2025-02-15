package org.automation.pageObjects.android;

import org.automation.utils.AndroidActions;
import org.automation.utils.AppiumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

/**
 * Page Object class for the Login page in the Android app.
 * This class provides methods and elements for interacting with the Login page.
 */
public class LoginPage extends AndroidActions {

    AndroidDriver driver; // AndroidDriver instance for interacting with the app

    /**
     * Constructor to initialize the LoginPage.
     *
     * @param driver The AndroidDriver instance to use for interacting with the app.
     */
    public LoginPage(AndroidDriver driver) {
        super(driver); // Call the parent class constructor
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // Initialize WebElements using PageFactory
    }

    // Locators for Login page elements
    @AndroidFindBy(accessibility = "test-Username")
    private WebElement userNameField; // Username input field

    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordField; // Password input field

    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton; // Login button

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
    private WebElement errorMessage; // Error message displayed on invalid login

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement menuIcon; // Menu icon for logout

    @AndroidFindBy(accessibility = "test-LOGIN")
    private List<WebElement> loginButtons; // List of login buttons (used to check if the user is logged in)

    /**
     * Logs into the application using the provided username and password.
     *
     * @param username The username to enter.
     * @param password The password to enter.
     */
    public void login(String username, String password) {
        userNameField.sendKeys(username); // Enter username
        passwordField.sendKeys(password); // Enter password
        loginButton.click(); // Click the login button
    }

    /**
     * Performs logout actions if the user is logged in.
     * If the login button is displayed, it clears the username and password fields.
     * Otherwise, it taps on the menu icon and performs a tap gesture to log out.
     */
    public void performLogoutIfNeeded() {
        if (loginButtons.size() > 0) {
            // If the login button is present, clear the username and password fields
            userNameField.clear();
            passwordField.clear();
        } else {
            // Click the menu icon to open the menu
            menuIcon.click();
            AppiumUtils.staticWait(2000); // Wait for the menu to open

            // Define the coordinates for the tap gesture
            int x = 164; // X coordinate
            int y = 1299; // Y coordinate

            // Create a PointerInput object for touch actions
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            // Create a sequence of actions for the tap
            Sequence tap = new Sequence(finger, 0) // The "0" is the sequence ID
                    .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y)) // Move to the coordinates
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Press down
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release

            // Perform the tap action twice (to ensure the logout action is triggered)
            driver.perform(Collections.singletonList(tap));
            driver.perform(Collections.singletonList(tap));
        }
    }

    /**
     * Retrieves the error message displayed on the Login page.
     *
     * @return The text of the error message.
     */
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
