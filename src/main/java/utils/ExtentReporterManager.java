package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentReporterNG configures and returns an ExtentReports instance.
 * It defines the HTML report path, report name, and other system info.
 */
public class ExtentReporterManager {

    /**
     * Creates and returns an ExtentReports object with HTML output configuration.
     * 
     * @return ExtentReports instance to be used in test listener
     */
    public static ExtentReports getReportObject() {

        // Define path for the HTML report
        String path = System.getProperty("user.dir") + "//reports//index.html";

        // Create SparkReporter (HTML generator) with the defined path
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");  // Visible in HTML report
        reporter.config().setDocumentTitle("Test Results");         // HTML <title>

        // Create ExtentReports instance and attach the reporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Optional: add system/environment info
        extent.setSystemInfo("Tester", "Abhishek");

        return extent;
    }
}
