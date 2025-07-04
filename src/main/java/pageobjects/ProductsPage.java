package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

/**
 * Page Object class for the Products page on Entrata.
 * Handles navigation to and verification of the ProspectPortal product page.
 */
public class ProductsPage extends AbstractComponent {

	WebDriver driver;

	/**
	 * Constructor to initialize WebDriver and PageFactory elements.
	 * 
	 * @param driver WebDriver instance passed from test class
	 */
	public ProductsPage(WebDriver driver) {
		super(driver); // Use AbstractComponent methods (like wait, hover)
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize @FindBy elements
	}

	// Web elements related to the navigation flow

	/** Top navigation 'Products' menu */
	@FindBy(xpath = "//div[contains(text(),'Products')]")
	WebElement products;

	/** Submenu item for 'ProspectPortal' under Products */
	@FindBy(xpath = "//div[normalize-space()='ProspectPortal']")
	WebElement prospect_Portal;

	/** Header element on the ProspectPortal landing page */
	@FindBy(tagName = "h1")
	WebElement ProspectPortal;

	/**
	 * Navigates from the Products menu to the ProspectPortal product page.
	 * 
	 * Steps:
	 * 1. Wait until 'Products' menu is visible
	 * 2. Hover over the 'Products' element
	 * 3. Wait for and click on 'ProspectPortal' from the dropdown
	 */
	public void navigateToProductsPage() {
		waitForWebElementToAppear(products);       // Wait for 'Products' to load
		hoverOverElement(products);                // Hover using reusable method in AbstractComponent

		waitForWebElementToAppear(prospect_Portal); // Wait for the sub-item to appear
		prospect_Portal.click();                    // Click to go to ProspectPortal page
	}

	/**
	 * Verifies whether the current URL contains a specific product keyword.
	 * Used for confirming correct navigation.
	 * 
	 * @param product The expected keyword (e.g., "prospectportal")
	 * @return true if URL contains the keyword
	 */
	public Boolean verifyProductsURL(String product) {
		return driver.getCurrentUrl().contains(product);
	}

	/**
	 * Returns the current page title. Can be used in test assertions.
	 * 
	 * @return Title of the current page
	 */
	public String titleCheck() {
		return driver.getTitle();
	}
}
