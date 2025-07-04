package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

/**
 * Page Object class for the 'Resources' section of the Entrata website.
 * Handles navigation to the 'Partners' page and validation of the resulting URL and title.
 */
public class ResourcesPage extends AbstractComponent {

    WebDriver driver;

    /**
     * Constructor to initialize WebDriver and PageFactory elements.
     *
     * @param driver WebDriver instance passed from test class
     */
    public ResourcesPage(WebDriver driver) {
        super(driver);                      // Enable access to common utilities from AbstractComponent
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize all @FindBy web elements
    }

    // Web elements related to 'Resources' and its dropdown
    @FindBy(xpath = "//div[text()='Resources']")
    WebElement resources;

    @FindBy(xpath = "//div[text()='Partners']")
    WebElement partners;

    /**
     * Navigates from the homepage to Resources > Partners page.
     *
     * Steps:
     * 1. Wait for 'Resources' menu to appear
     * 2. Hover over 'Resources'
     * 3. Wait for 'Partners' link and click it
     */
    public void navigateToResourcesPage() {
        waitForWebElementToAppear(resources);  // Wait for 'Resources' to be visible
        hoverOverElement(resources);           // Hover to reveal dropdown

        waitForWebElementToAppear(partners);   // Wait for 'Partners' link to appear
        partners.click();                      // Click to navigate
    }

    /**
     * Validates that the current URL contains the expected keyword (e.g., "partners").
     *
     * @param expectedUrlKeyword Substring expected in the current URL
     * @return true if current URL contains the expected keyword
     */
    public Boolean verifyPartnersURL(String expectedUrlKeyword) {
        return driver.getCurrentUrl().contains(expectedUrlKeyword);
    }

    /**
     * Returns the title of the current page.
     * Useful for asserting correct navigation or content.
     *
     * @return Page title as a string
     */
    public String titleCheck() {
        return driver.getTitle();
    }
}
