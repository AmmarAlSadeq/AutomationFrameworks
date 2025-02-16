package org.automation;

import io.restassured.RestAssured;
import org.automation.utils.APIUtils;
import org.automation.utils.Helper;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * BaseClass serves as the foundation for all test classes.
 * It includes common setup, teardown, and data provider methods.
 */
public class BaseClass {

    /**
     * DataProvider method to fetch test data from a JSON file.
     * @return A 2D array of test data for TestNG.
     * @throws IOException If the JSON file cannot be read.
     */
    @DataProvider
    public Object[][] getData() throws IOException {
        // Fetch test data from the JSON file located in the testData directory
        List<HashMap<String, String>> data = Helper.getJsonData(System.getProperty("user.dir") + "//src//test//java//org//automation//testData//SimpleBooks.json");
        return new Object[][]{{data.get(0)}}; // Return the first set of test data
    }

    /**
     * Setup method to initialize test suite configurations.
     * This runs once before any test methods in the class.
     */
    @BeforeClass
    public void setUp() {
        // Enable relaxed HTTPS validation for RestAssured
        RestAssured.useRelaxedHTTPSValidation();
        System.out.println("Starting the test suite...");
    }

    /**
     * Teardown method to clean up after the test suite.
     * This runs once after all test methods in the class.
     */
    @AfterClass
    public void tearDown() {
        System.out.println("Tearing down the test suite");
    }
}