package org.automation.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.automation.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom TestNG listener for handling test execution events.
 * This class integrates with ExtentReports to generate detailed test reports.
 */
public class Listeners implements ITestListener {
    ExtentReports extentReport = AppiumUtils.getReporterObject(); // ExtentReports instance for generating reports
    ExtentTest extentTest; // ExtentTest instance for logging test details
    AppiumDriver driver; // AppiumDriver instance for capturing screenshots (if needed)

    /**
     * Called when a test method starts.
     * Initializes the ExtentTest instance for the current test.
     *
     * @param result The result of the test method.
     */
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getMethod().getMethodName()); // Create a new test in the report
    }

    /**
     * Called when a test method succeeds.
     * Logs the test as passed in the ExtentReport.
     *
     * @param result The result of the test method.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed"); // Log the test as passed
    }

    /**
     * Called when a test method fails.
     * Logs the test as failed in the ExtentReport and captures the exception.
     * Attempts to capture a screenshot if the driver is available.
     *
     * @param result The result of the test method.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Failed"); // Log the test as passed
        extentTest.fail(result.getThrowable()); // Log the exception

        try {
            // Retrieve the AppiumDriver instance from the test class
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e1) {
            throw new RuntimeException(e1); // Handle any exceptions
        }
    }

    /**
     * Called when all tests in a test class finish.
     * Flushes the ExtentReports instance to generate the final report.
     *
     * @param context The test context.
     */
    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush(); // Generate the final report
    }
}
