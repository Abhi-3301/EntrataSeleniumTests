package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import abhishek.TestComponents.BaseTest;
import pageobjects.DynamicContent;

public class DynamicContentTest extends BaseTest {

	/**
	 * Test: Verify Cookie Consent Banner appears and can be closed. Purpose: To
	 * test dynamic content visibility and interaction
	 */
	@Test
	public void verifyCookieBannerAppearsAndCloses() {

		DynamicContent dc = new DynamicContent(driver);

		// Step 1: Verify banner is initially displayed
		Assert.assertTrue(dc.bannerIsDisplayed(), "Cookie banner is not displayed on page load.");

		// Step 2: Close the banner
		dc.closeBanner();

		// Step 3: Verify banner is no longer displayed (or has been successfully closed
		Assert.assertTrue(dc.bannerIsClosed(), "Cookie banner did not close properly.");
	}
}