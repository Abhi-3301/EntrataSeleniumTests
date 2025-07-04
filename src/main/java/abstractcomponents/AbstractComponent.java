package abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * AbstractComponent serves as a reusable base class for all Page Object
 * classes. It provides common utility methods like waiting for elements and
 * hovering, which promote code reuse and simplify maintenance.
 */
public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	/**
	 * Constructor initializes WebDriver and WebDriverWait instance. PageFactory is
	 * also initialized here to allow element initialization in child classes.
	 *
	 * @param driver WebDriver instance passed from child page classes
	 */
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 5-second explicit wait

		// Initializes all @FindBy annotated elements in the current class or subclass
		PageFactory.initElements(driver, this);
	}

	/**
	 * Waits until the given WebElement is visible on the page. Useful before
	 * performing actions like click, hover, or sendKeys to avoid timing issues.
	 *
	 * @param findBy WebElement to wait for
	 */
	public void waitForWebElementToAppear(WebElement findBy) {
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	/**
	 * Moves the mouse cursor to the center of the specified WebElement. Commonly
	 * used to hover over dropdowns or menus to trigger visibility of child
	 * elements.
	 *
	 * @param element The WebElement to hover over
	 */
	public void hoverOverElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform(); // Moves the mouse to the element
	}
}
