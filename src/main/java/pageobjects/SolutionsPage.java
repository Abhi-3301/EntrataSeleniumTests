package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

/**
 * Page Object class representing the "Solutions" section of Entrata's website.
 * Handles navigation to the "Student Housing" solution and validation of URL and title.
 */
public class SolutionsPage extends AbstractComponent {

	WebDriver driver;

	/**
	 * Constructor to initialize driver and page elements.
	 *
	 * @param driver WebDriver instance
	 */
	public SolutionsPage(WebDriver driver) {
		super(driver);                              // Enables use of wait and hover from AbstractComponent
		this.driver = driver;
		PageFactory.initElements(driver, this);     // Initialize all @FindBy annotated elements
	}

	// Locators for the Solutions menu and Student submenu
	@FindBy(xpath = "(//div[contains(text(),'Solutions')])[1]")
	WebElement solutions;

	@FindBy(xpath = "(//div[contains(text(),'Student')])[2]")
	WebElement student;

	/**
	 * Navigates to the "Student Housing" solution page.
	 *
	 * Steps:
	 * 1. Wait for the "Solutions" menu to be visible
	 * 2. Hover over "Solutions" to display the submenu
	 * 3. Wait for the "Student" option and click it
	 */
	public void navigateToSolutionsPage() {
		waitForWebElementToAppear(solutions);    // Wait for Solutions menu
		hoverOverElement(solutions);             // Hover to reveal submenus

		waitForWebElementToAppear(student);      // Wait for Student submenu
		student.click();                         // Click to navigate
	}

	/**
	 * Validates that the current URL contains the specified keyword.
	 *
	 * @param keyword Substring expected in the URL (e.g., "student")
	 * @return true if current URL contains the keyword
	 */
	public Boolean verifySolutionsURL(String keyword) {
		return driver.getCurrentUrl().contains(keyword);
	}

	/**
	 * Verifies that the page title contains "Student Housing".
	 * Useful to confirm that correct page has loaded.
	 *
	 * @return true if title contains "Student Housing"
	 */
	public Boolean titleCheck() {
		return driver.getTitle().contains("Student Housing");
	}
}
