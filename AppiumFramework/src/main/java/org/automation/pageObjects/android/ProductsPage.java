package org.automation.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.automation.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for the Products page in the Android app.
 * This class provides methods and elements for interacting with the Products page.
 */
public class ProductsPage extends AndroidActions {

    AndroidDriver driver; // AndroidDriver instance for interacting with the app

    /**
     * Constructor to initialize the ProductsPage.
     *
     * @param driver The AndroidDriver instance to use for interacting with the app.
     */
    public ProductsPage(AndroidDriver driver) {
        super(driver); // Call the parent class constructor
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); // Initialize WebElements using PageFactory
    }

    // Locator for the cart icon
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement cartIcon;

    /**
     * Checks if the cart icon is displayed on the Products page.
     *
     * @return True if the cart icon is displayed, otherwise false.
     */
    public boolean isCartIconDisplayed() {
        return cartIcon.isDisplayed();
    }
}
