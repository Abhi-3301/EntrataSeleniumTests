package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

/**
 * Page Object class for Entrata Home Page.
 * Includes methods to verify presence of header links and navigate to home.
 */
public class HomePage extends AbstractComponent {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);  // Inherit utilities like waits from AbstractComponent
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize all @FindBy elements
    }

    // Web elements representing top navigation bar items
    @FindBy(xpath = "//div[contains(text(),'Products')]")
    WebElement products;

    @FindBy(xpath = "(//div[contains(text(),'Solutions')])[1]")
    WebElement solutions;

    @FindBy(xpath = "//div[text() = 'Resources']")
    WebElement resources;

    @FindBy(partialLinkText = "Company News")
    WebElement company_news;

    /**
     * Opens the Entrata homepage.
     */
    public void goTo() {
        driver.get("https://www.entrata.com/");
    }

    /**
     * Returns the current page title.
     * Useful for title assertion in tests.
     */
    public String verifyTitle() {
        return driver.getTitle();
    }

    /**
     * Checks if "Products" header is displayed.
     */
    public Boolean verifyHeaderProducts() {
        return products.isDisplayed();
    }

    /**
     * Checks if "Solutions" header is displayed.
     */
    public Boolean verifyHeaderSolutions() {
        return solutions.isDisplayed();
    }

    /**
     * Checks if "Resources" header is displayed.
     */
    public Boolean verifyHeaderResources() {
        return resources.isDisplayed();
    }

    /**
     * Checks if "Company News" header is displayed.
     */
    public Boolean verifyHeaderCompanyNews() {
        return company_news.isDisplayed();
    }
}
