package org.automation.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;

/**
 * Utility class for common Appium-related operations.
 * This class provides methods for starting the Appium server, reading JSON data,
 * formatting amounts, generating ExtentReports, and more.
 */
public abstract class AppiumUtils {
    public AppiumDriverLocalService service; // Appium server instance
    public static ExtentReports extentReport; // ExtentReports instance for reporting

    /**
     * Reads JSON data from a file and converts it into a list of HashMaps.
     *
     * @param jsonPath The path to the JSON file.
     * @return A list of HashMaps containing the JSON data.
     * @throws IOException If the file cannot be read.
     */
    public List<HashMap<String, String>> getJsonData(String jsonPath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonPath));
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    /**
     * Starts the Appium server with the specified IP address and port.
     *
     * @param ipAddress The IP address to bind the Appium server to.
     * @param port      The port to run the Appium server on.
     * @return The started AppiumDriverLocalService instance.
     */
    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        // Initialize Appium Service
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(System.getProperty("user.home") + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)  // Allows overriding sessions
                .withTimeout(Duration.ofSeconds(60))
                .build();

        service.start();
        return service;
    }

    /**
     * Configures and returns an ExtentReports object for generating test reports.
     *
     * @return The configured ExtentReports instance.
     */
    public static ExtentReports getReporterObject() {
        // Set up report configurations
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//src//reports//extent-report.html");
        reporter.config().setReportName("Sauce Lab Automation Report");
        reporter.config().setDocumentTitle("Sauce Lab Automation Report");

        extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Senior QA Engineer", "Ammar AlSadeq");

        return extentReport;
    }

    /**
     * Pauses the execution for the specified number of milliseconds.
     *
     * @param milliseconds The time to wait in milliseconds.
     */
    public static void staticWait(long milliseconds) {
        try {
            // Wait for the given time in milliseconds
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // Log or handle exception as needed
            System.err.println("Thread was interrupted during sleep: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}