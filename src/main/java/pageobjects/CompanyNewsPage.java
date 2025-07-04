package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

/**
 * Page Object for the 'Company News' section.
 * Inherits utility methods from AbstractComponent.
 */
public class CompanyNewsPage extends AbstractComponent {

    WebDriver driver;

    /**
     * Constructor initializes the WebDriver and WebElements
     * @param driver - WebDriver passed from the test class
     */
    public CompanyNewsPage(WebDriver driver) {
        super(driver); // Enables shared wait/util methods
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement for the "Company News" link (uses partial link text)
    @FindBy(partialLinkText = "Company News")
    WebElement company_news;

    /**
     * Navigates to the Company News page by clicking on the corresponding link.
     */
    public void navigateToCompanyNewsPage() {
        company_news.click();
    }

    /**
     * Verifies if the current URL contains the expected keyword.
     * @param keyword - expected keyword like "press" or "news"
     * @return true if URL contains the keyword
     */
    public Boolean verifyCompanyNewsPageURL(String keyword) {
        return driver.getCurrentUrl().contains(keyword);
    }

    /**
     * Returns the current page title.
     * Useful for asserting correct navigation.
     * @return page title
     */
    public String titleCheck() {
        return driver.getTitle();
    }
}
