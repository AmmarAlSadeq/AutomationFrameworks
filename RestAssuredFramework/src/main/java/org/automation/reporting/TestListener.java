package org.automation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener class implements ITestListener to integrate with TestNG and ExtentReports.
 * It captures test events (start, success, failure) and logs them in the ExtentReport.
 */
public class TestListener implements ITestListener {

    // ExtentReports instance for generating the report
    private ExtentReports extent = ExtentManager.getInstance();

    // ThreadLocal to handle ExtentTest instances for parallel execution
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Called when a test method starts.
     * Creates a new ExtentTest instance for the test method and stores it in ThreadLocal.
     *
     * @param result The result of the test method.
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);  // Store the ExtentTest instance in ThreadLocal
    }

    /**
     * Called when a test method succeeds.
     * Logs the test status as PASS in the ExtentReport.
     *
     * @param result The result of the test method.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");  // Log the test as passed
    }

    /**
     * Called when a test method fails.
     * Logs the test status as FAIL in the ExtentReport and includes the exception details.
     *
     * @param result The result of the test method.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        extentTest.log(Status.FAIL, "API Test Method Failed: " + result.getMethod().getMethodName());  // Log the test as failed
        extentTest.log(Status.FAIL, result.getThrowable());  // Log the exception details
    }

    /**
     * Called when all tests finish.
     * Flushes the ExtentReports instance to generate the final report.
     *
     * @param context The test context containing all test information.
     */
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // Generate the final ExtentReport
    }
}
