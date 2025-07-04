package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractcomponents.AbstractComponent;

/**
 * Page Object for handling interactions with the 'Book a Demo' form.
 */
public class DemoFormPage extends AbstractComponent {

	WebDriver driver;

	public DemoFormPage(WebDriver driver) {
		super(driver); // Inherit wait methods from AbstractComponent
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
	}

	// Locators for page elements

	@FindBy(css = "#w-tabs-1-data-w-tab-1")
	WebElement EntrataOS;

	@FindBy(xpath = "//div[text()='Book a demo']")
	WebElement book_a_demo;

	@FindBy(id = "FirstName")
	WebElement First_Name;

	@FindBy(id = "LastName")
	WebElement Last_Name;

	@FindBy(id = "Email")
	WebElement email;

	@FindBy(id = "Unit_Count__c")
	WebElement manage_units;

	@FindBy(id = "Title")
	WebElement title;

	// Used for explicit wait
	By f_name = By.id("FirstName");

	/**
	 * Fills the 'Book a Demo' form with provided data. Handles popups, waits for
	 * form visibility, and enters details.
	 */
	public void fillDemoForm(String fname, String lname, String email_id, String role) {

		DynamicContent dc = new DynamicContent(driver);
		// Close advertisement popup if displayed
		if (dc.bannerIsDisplayed()) {
			dc.closeBanner();
		}

		EntrataOS.click(); // Click Entrata OS tab
		book_a_demo.click(); // Click Book a Demo button

		// Wait for the form field to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(f_name));

		// Fill form fields
		First_Name.sendKeys(fname);
		Last_Name.sendKeys(lname);
		email.sendKeys(email_id);

		// Select 1st index (2nd item) from the dropdown
		Select se = new Select(manage_units);
		se.selectByIndex(1);

		title.sendKeys(role); // Job role or designation
	}

	/**
	 * Verifies whether the entered data (fname and lname) matches the input fields.
	 * 
	 * @param fname First name to verify
	 * @param lname Last name to verify
	 * @return true if both match; false otherwise
	 */
	public Boolean verfiyEnteredData(String fname, String lname) {
		return First_Name.getAttribute("value").equalsIgnoreCase(fname)
				&& Last_Name.getAttribute("value").equalsIgnoreCase(lname);
	}
}
