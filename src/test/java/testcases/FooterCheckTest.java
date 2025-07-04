package testcases;

// Import TestNG assertion and test annotation classes
import org.testng.Assert;
import org.testng.annotations.Test;

// Import base test class and footer page object
import abhishek.TestComponents.BaseTest;
import pageobjects.Footer;

/**
 * Test class to verify functionality and robustness of the footer's "Privacy
 * Policy" link.
 */
public class FooterCheckTest extends BaseTest {

	/**
	 * Positive Test Case: Verifies that the "Privacy Policy" link in the footer
	 * works correctly.
	 * 
	 * Steps: 1. Click on the "Privacy Policy" link 2. Switch to the new window/tab
	 * 3. Verify the page title is "Privacy Policy" 4. Check that the URL contains
	 * the keyword "privacy"
	 */
	@Test
	public void verifyFooterPrivacyPolicyLink() throws InterruptedException {

		Footer ft = new Footer(driver); // Initialize Footer Page Object

		// Click the link and switch driver context to new window/tab
		driver = ft.clickOnkFooter();

		// Assert page title
		Assert.assertEquals(ft.verifyTitle(driver), "Privacy Policy",
				"Page title mismatch after clicking on Privacy Policy");

		// Assert that the URL contains 'privacy'
		Assert.assertTrue(ft.verifyPrivacyPageURL("privacy", driver),
				"URL does not contain expected keyword 'privacy'");
	}

	/**
	 * Negative Test Case: Simulates failure if the Privacy Policy URL does not
	 * contain an incorrect keyword. Useful for robustness check.
	 */
	@Test
	public void testBrokenPrivacyLink() throws InterruptedException {

		Footer footer = new Footer(driver); // Initialize Footer Page Object

		driver = footer.clickOnkFooter(); // Click and switch to new tab

		// This should fail intentionally if the keyword "wrong-privacy" is NOT found
		Assert.assertTrue(footer.verifyPrivacyPageURL("wrong-privacy", driver),
				"Unexpected URL match for incorrect keyword — test should fail if 'wrong-privacy' is found");
	}
}
