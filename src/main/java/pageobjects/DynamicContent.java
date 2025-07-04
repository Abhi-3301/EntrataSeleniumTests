package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

/**
 * Page Object class for handling dynamic content like advertisement banners
 * (e.g., cookie popups or modals) on the Entrata site.
 */
public class DynamicContent extends AbstractComponent {

	WebDriver driver;

	/**
	 * Constructor initializes WebDriver and WebElements
	 * 
	 * @param driver WebDriver instance from test class
	 */
	public DynamicContent(WebDriver driver) {
		super(driver); // Allows access to reusable wait/wrapper methods from AbstractComponent
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize @FindBy WebElements
	}

	// Represents the advertisement/cookie banner modal
	@FindBy(xpath = "//div[contains(@class, 'osano-cm-window__dialog')]")
	WebElement advertisement;

	// Represents the 'close' button on the banner
	@FindBy(xpath = "//button[contains(@class, 'osano-cm-dialog__close')]")
	WebElement addClose;

	// A key tab on the homepage, used for post-banner visibility check
	@FindBy(css = "#w-tabs-1-data-w-tab-1")
	WebElement EntrataOS;

	/**
	 * Checks whether the cookie/ad banner is currently visible on the screen.
	 * 
	 * @return true if the banner is displayed; false otherwise
	 */
	public Boolean bannerIsDisplayed() {
		return advertisement.isDisplayed();
	}

	/**
	 * Clicks on the close button of the ad banner to dismiss it.
	 */
	public void closeBanner() {
		addClose.click();
	}

	/**
	 * This method indirectly verifies whether the ad banner has been closed.
	 * 
	 * Since the advertisement element may still exist in the DOM after closing
	 * (making direct visibility checks unreliable), I test clickability of a
	 * non-obstructed page element (`EntrataOS`).
	 * 
	 * If the click succeeds, it implies the banner is gone and interaction is
	 * restored.
	 * 
	 * @return true if the page is interactable (banner closed); false if the banner
	 *         is still blocking
	 */
	public Boolean bannerIsClosed() {
		try {
			EntrataOS.click(); // Try interacting with a known non-banner element
			return true; // Success means banner is gone
		} catch (Exception e) {
			return false; // Any exception implies the banner is still interfering
		}
	}
}
