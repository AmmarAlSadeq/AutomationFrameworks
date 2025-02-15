package com.saucedemo.testUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebBaseClass {
	// ThreadLocal ensures thread-safe WebDriver and Properties instances for parallel execution
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	private static ThreadLocal<Properties> properties = new ThreadLocal<>();

	// Initializes the WebDriver and loads properties if not already done
	public static void initializeDriver() {
		if (properties.get() == null) {
			loadProperties();
		}
		if (webDriver.get() == null) {
			startDriver();
		}
	}

	// Loads configuration properties from the config.properties file
	private static void loadProperties() {
		properties.set(new Properties());  // Initialize thread-local Properties
		try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config/config.properties")) {
			properties.get().load(fileInputStream);  // Load properties into the thread-local instance
		} catch (IOException e) {
			throw new RuntimeException("Failed to load properties file", e);
		}
	}

	// Returns the thread-local Properties instance
	public static Properties getProperties() {
		return properties.get();
	}

	// Starts the WebDriver based on the browser specified in the properties file
	private static void startDriver() {
		String browserName = properties.get().getProperty("browserName");  // Get browser name from properties

		// Validate browser name
		if (browserName == null) {
			throw new IllegalArgumentException("Browser name is not specified in the config.properties file.");
		}

		WebDriver driver = null;
		switch (browserName) {
			case "chrome":
				// Set ChromeDriver path and initialize ChromeDriver
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				break;

			case "firefox":
				// Set GeckoDriver path and initialize FirefoxDriver
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException("Invalid browser name: " + browserName);
		}

		// Configure WebDriver settings
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.set(driver);  // Store the WebDriver instance in the current thread
	}

	// Returns the thread-local WebDriver instance
	public static WebDriver getDriver() {
		return webDriver.get();
	}

	// Stops the WebDriver and cleans up the thread-local instance
	public static void stopDriver() {
		WebDriver driver = webDriver.get();
		if (driver != null) {
			driver.quit();
			webDriver.remove();  // Clear the thread-local WebDriver instance
		}
	}
}
