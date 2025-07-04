package testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * Listeners class implements ITestListener interface to handle test events:
 * onTestStart, onTestSuccess, onTestFailure, etc. It also integrates with
 * ExtentReports to log test results with screenshots.
 */
public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test; // Holds current test instance
	ExtentReports extent = utils.ExtentReporterManager.getReportObject(); // Shared report object

	// Thread-safe object to avoid issues in parallel execution
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	/**
	 * Called when each test starts. Creates a new ExtentTest entry and assigns it
	 * to the current thread.
	 */
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // Set it for the current thread
	}

	/**
	 * Called when a test passes. Logs PASS status in the report.
	 */
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	/**
	 * Called when a test fails. Captures screenshot and logs failure with exception
	 * and screenshot in report.
	 */

	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());
		String filePath = null;
		try {
			// Dynamically get the WebDriver instance from the failed test class
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Capture screenshot and get file path
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Attach screenshot to ExtentReport
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	/**
	 * Called if a test is skipped.
	 */
	public void onTestSkipped(ITestResult result) {
		extentTest.get().skip(result.getThrowable()); // Log the reason
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//NOT IMPLEMENTED
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result); // Treat as normal failure
	}

	public void onStart(ITestContext context) {
		//NOT IMPLEMENTED
	}

	/**
	 * Called after all tests finish. Flushes the ExtentReport to generate the final
	 * HTML report.
	 */
	public void onFinish(ITestContext context) {
		extent.flush(); // Write all logs to the report
	}
}
