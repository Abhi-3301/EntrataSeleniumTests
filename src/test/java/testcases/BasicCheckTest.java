package testcases;

// Importing required TestNG and assertion classes
import org.testng.Assert;
import org.testng.annotations.Test;

import testcomponents.BaseTest;

/**
 * Test class to perform basic UI checks on the Entrata homepage. Includes
 * validation of title and top navigation header elements.
 */
public class BasicCheckTest extends BaseTest {

	/**
	 * Verifies that the homepage title is correct and that key top navigation links
	 * ('Products', 'Solutions', 'Resources', 'Company/News') are functional.
	 */
	@Test(priority = 1)
	public void verifyHomePageTitleAndNavLinks() {

		// Navigate to the homepage
		hp.goTo();

		// ✅ Title Verification
		String expectedTitle = "Property Management Software | Entrata®";
		Assert.assertEquals(hp.verifyTitle(), expectedTitle, "Home page title did not match.");

		// ✅ Header Links Verification
		Assert.assertTrue(hp.verifyHeaderProducts(), "'Products' header link is not displayed or not clickable.");
		Assert.assertTrue(hp.verifyHeaderSolutions(), "'Solutions' header link is not displayed or not clickable.");
		Assert.assertTrue(hp.verifyHeaderResources(), "'Resources' header link is not displayed or not clickable.");
		Assert.assertTrue(hp.verifyHeaderCompanyNews(),
				"'Company/News' header link is not displayed or not clickable.");
	}

	/**
	 * Negative test to validate that a missing or intentionally broken header is
	 * correctly not visible/interactable.
	 */
	@Test(priority = 2)
	public void testMissingHeaderElement() {
		hp.goTo();

		Assert.assertFalse(hp.verifyHeaderCompanyNews(), "Header 'Company/News' should not be visible — but it was.");
	}
}
