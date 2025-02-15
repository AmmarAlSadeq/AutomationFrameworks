package com.saucedemo.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ExtentCucumberReporter {

    private static ExtentReports extent;  // ExtentReports instance for generating reports
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();  // Thread-safe ExtentTest instance
    private static ExtentHtmlReporter htmlReporter;  // HTML reporter for ExtentReports

    // Initialize ExtentReports and ExtentHtmlReporter before each scenario
    @Before
    public void setUp(Scenario scenario) {
        if (extent == null) {  // Initialize only once to avoid creating multiple reports
            htmlReporter = new ExtentHtmlReporter("src/test/resources/reports/extentReports/extent-report.html");  // Set report file path
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);  // Attach the HTML reporter to ExtentReports
        }
        createTest(scenario.getName());  // Create a test entry for the current scenario
    }

    // Flush the ExtentReports instance after all scenarios are executed
    @After
    public void tearDown() {
        if (extent != null) {
            extent.flush();  // Write the report to the file
        }
    }

    // Create a new test entry in the report for the given scenario name
    public static void createTest(String testName) {
        if (testName != null && !testName.isEmpty()) {
            // Ensure ExtentReports is initialized before creating a test
            if (extent == null) {
                new ExtentCucumberReporter().setUp(null);  // Initialize if not already done
            }
            // Create a test entry with the scenario name
            test.set(extent.createTest(testName));
        }
    }

    // Log the status of a test step (pass, fail, or info)
    public static void logTestStatus(String status, String message) {
        ExtentTest currentTest = test.get();  // Get the current thread's test instance
        if (currentTest != null) {
            if (status.equalsIgnoreCase("pass")) {
                currentTest.pass(message);  // Log a passed step
            } else if (status.equalsIgnoreCase("fail")) {
                currentTest.fail(message);  // Log a failed step
            } else {
                currentTest.info(message);  // Log an informational message
            }
        }
    }
}
