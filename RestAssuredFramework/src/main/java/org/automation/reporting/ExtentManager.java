package org.automation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentManager class is responsible for managing the ExtentReports instance.
 * It ensures a single instance of ExtentReports is created and reused across the test execution.
 */
public class ExtentManager {

    // Static instance of ExtentReports to ensure singleton behavior
    private static ExtentReports extent;

    /**
     * Returns the singleton instance of ExtentReports.
     * If the instance does not exist, it creates one.
     *
     * @return The ExtentReports instance.
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();  // Create a new instance if it doesn't exist
        }
        return extent;
    }

    /**
     * Creates and configures a new instance of ExtentReports.
     * Attaches an ExtentSparkReporter to generate an HTML report.
     *
     * @return The configured ExtentReports instance.
     */
    private static ExtentReports createInstance() {
        // Configure ExtentSparkReporter to generate the report in the "target" directory
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "//src//reports//extent-report.html");

        // Create a new ExtentReports instance and attach the Spark reporter
        extent = new ExtentReports();
        extent.attachReporter(spark);

        return extent;
    }
}
