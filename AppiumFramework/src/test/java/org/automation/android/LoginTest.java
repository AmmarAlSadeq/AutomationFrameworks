package org.automation.android;

import org.automation.pageObjects.android.ProductsPage;
import org.automation.testUtils.AndroidBaseClass;
import org.automation.pageObjects.android.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Test class for login functionality in the Android app.
 * This class contains test cases for both valid and invalid login scenarios.
 */
public class LoginTest extends AndroidBaseClass {
    private LoginPage loginPage; // Page Object for the Login page
    private ProductsPage productsPage; // Page Object for the Products page

    /**
     * DataProvider method to fetch test data from a JSON file.
     *
     * @return A 2D array of test data.
     * @throws IOException If the JSON file cannot be read.
     */
    @DataProvider
    public Object[][] getData() throws IOException {
        // Read test data from the JSON file
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//org//automation//testData//saucelab.json");
        return new Object[][]{{data.get(0)}}; // Return the first set of test data
    }

    /**
     * Test case for valid login.
     * This test verifies that a user can log in with valid credentials and is redirected to the Products page.
     *
     * @param data A HashMap containing test data (username, password).
     * @throws InterruptedException If the thread is interrupted during execution.
     */
    @Test(priority = 1, dataProvider = "getData")
    public void validLoginTest(HashMap<String, String> data) throws InterruptedException {
        // Prepare the login credentials for positive test case
        String username = data.get("validUsername"); // Valid username for login
        String password = data.get("validPassword"); // Valid password for login

        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        // Perform login
        loginPage.login(username, password);

        // Assert that the login is successful and the Products page is displayed
        Assert.assertTrue(productsPage.isCartIconDisplayed());
    }

    /**
     * Test case for invalid login.
     * This test verifies that a user cannot log in with invalid credentials and an error message is displayed.
     *
     * @param data A HashMap containing test data (username, password, error message).
     */
    @Test(priority = 2, dataProvider = "getData")
    public void invalidLoginTest(HashMap<String, String> data) {
        // Initialize Page Objects
        loginPage = new LoginPage(driver);

        // Perform logout if needed (to ensure a clean state)
        loginPage.performLogoutIfNeeded();

        // Prepare the login credentials for negative test case
        String username = data.get("invalidUsername"); // Invalid username for login
        String password = data.get("invalidPassword"); // Invalid password for login

        // Perform login
        loginPage.login(username, password);

        // Assert that the error message is displayed for invalid login
        Assert.assertEquals(loginPage.getErrorMessage(), data.get("invalidCredentialsMessage"));
    }
}
