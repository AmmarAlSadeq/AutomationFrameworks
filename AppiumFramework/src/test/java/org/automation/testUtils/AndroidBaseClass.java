package org.automation.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.automation.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

/**
 * Base class for Android test automation.
 * This class handles the setup and teardown of the Appium server and AndroidDriver.
 */
public class AndroidBaseClass extends AppiumUtils {

    public AndroidDriver driver; // AndroidDriver instance for interacting with the app
    public AppiumDriverLocalService service; // Appium server instance

    /**
     * Configures and starts the Appium server and initializes the AndroidDriver.
     * This method runs before each test class.
     *
     * @throws IOException If the configuration file cannot be read.
     */
    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {
        // Load configuration properties from the config.properties file
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//automation//resources//config.properties");
        properties.load(fileInputStream);

        // Read configuration values
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        String androidDeviceName = properties.getProperty("androidDeviceName");
        String appPackage = properties.getProperty("appPackage");
        String appActivity = properties.getProperty("appActivity");

        // Start the Appium server
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        // Configure UiAutomator2 options for the AndroidDriver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("deviceName", androidDeviceName); // Set the device name
        options.setCapability("appPackage", appPackage); // Set the app package name
        options.setCapability("appActivity", appActivity); // Set the app activity name
        options.setCapability("app", System.getProperty("user.dir") + "//src//main//java//org//automation//resources//Android.SauceLabs.Mobile.Sample.app.2.7.1.apk"); // Set the path to the APK file
        options.setCapability("fullReset", true); // Perform a full reset of the app

        // Initialize the AndroidDriver
        driver = new AndroidDriver(service.getUrl(), options);
        System.out.println("Appium Server URL: " + service.getUrl());

        // Set implicit wait for the driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Stops the Appium server and quits the AndroidDriver.
     * This method runs after each test class.
     *
     * @throws InterruptedException If the thread is interrupted during execution.
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        driver.quit(); // Quit the AndroidDriver
        service.stop(); // Stop the Appium server
    }
}