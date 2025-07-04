package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractcomponents.AbstractComponent;

/**
 * Page Object class for handling footer-related interactions.
 * Scrolls to and clicks the "Privacy" link and handles window switch.
 */
public class Footer extends AbstractComponent {

    WebDriver driver;

    public Footer(WebDriver driver) {
        super(driver); // Allows access to wait methods
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Web element for the "Privacy" link in the footer
    @FindBy(xpath = "//a[text()='Privacy']")
    WebElement privacy;

    // By locator used for explicit wait
    By priv = By.xpath("//a[text()='Privacy']");

    // Element expected to be present in the Privacy Policy page
    @FindBy(tagName = "h1")
    WebElement privacy_policy;

    /**
     * Scrolls to the bottom, waits for the footer link, clicks on "Privacy",
     * and switches to the new browser tab/window.
     *
     * @return WebDriver instance switched to the new window
     */
    public WebDriver clickOnkFooter() throws InterruptedException {
        // Scroll to the bottom of the page using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Wait until the "Privacy" link is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(priv));

        // Click the privacy link
        privacy.click();

        // Store current window handle
        String originalWindow = driver.getWindowHandle();

        // Switch to the newly opened tab/window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        return driver;
    }

    /**
     * Verifies that the URL of the privacy page contains the expected string.
     *
     * @param purl    Expected substring in URL (e.g., "privacy")
     * @param driver  WebDriver (switched to new tab)
     * @return true if URL matches expected value
     */
    public Boolean verifyPrivacyPageURL(String purl, WebDriver driver) {
        return driver.getCurrentUrl().contains(purl);
    }

    /**
     * Returns the title of the privacy page for validation.
     *
     * @param driver WebDriver (switched to new tab)
     * @return title of the page
     */
    public String verifyTitle(WebDriver driver) {
        return driver.getTitle();
    }
}
