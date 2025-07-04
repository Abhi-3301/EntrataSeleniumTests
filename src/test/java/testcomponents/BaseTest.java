package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageobjects.HomePage;

public class BaseTest {

	public WebDriver driver;
	public HomePage hp;

	/**
	 * Initializes the WebDriver based on browser config. Supports Chrome (with
	 * optional headless), Edge, and Firefox.
	 * 
	 * @return WebDriver instance
	 * @throws IOException for reading properties file
	 */
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();

		// Load browser config from GlobalData.properties
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\testresources\\GlobalData.properties");
		prop.load(fis);

		// Prefer system property browser, fallback to GlobalData.properties
		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// CHROME setup
		if (browsername.contains("chrome")) {
			ChromeOptions co = new ChromeOptions();

			// Optional headless mode
			if (browsername.contains("headless"))
				co.addArguments("headless");

			driver = new ChromeDriver(co);
			driver.manage().window().setSize(new Dimension(1440, 900)); // Set full screen manually

		} else if (browsername.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else {
			driver = new ChromeDriver(); // Default fallback
		}

		// Common settings
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	/**
	 * Launches the application before each test method.
	 * 
	 * @return HomePage instance for reuse in test classes
	 * @throws IOException if WebDriver setup fails
	 */
	@BeforeMethod(alwaysRun = true)
	public HomePage launchApplication() throws IOException {

		driver = initializeDriver();
		hp = new HomePage(driver);
		hp.goTo(); // Open the home page URL
		return hp;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap - Jackson Databind

		return new ObjectMapper().readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});

	}

	/**
	 * Captures screenshot in case of test failure.
	 * 
	 * @param testCaseName name of the test
	 * @param driver       WebDriver instance
	 * @return path to the saved screenshot
	 * @throws IOException if writing file fails
	 */
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String file_path = System.getProperty("user.dir") + "\\src\\test\\java\\screenshots\\" + testCaseName + ".png";
		FileUtils.copyFile(src, new File(file_path));
		return file_path;
	}

	/**
	 * Quits the driver after every test method.
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
