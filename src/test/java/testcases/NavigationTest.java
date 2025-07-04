package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.CompanyNewsPage;
import pageobjects.ProductsPage;
import pageobjects.ResourcesPage;
import pageobjects.SolutionsPage;
import testcomponents.BaseTest;

/**
 * Test class to validate navigation and content of top-level Entrata pages:
 * Products, Solutions, Resources, and Company News.
 */
public class NavigationTest extends BaseTest {

	/**
	 * Test Case: Validate navigation to Products > ProspectPortal page. Verifies:
	 * - URL contains "products" - Title matches expected
	 */
	@Test
	public void validateProductsPage() {
		ProductsPage pp = new ProductsPage(driver);
		pp.navigateToProductsPage();

		Assert.assertTrue(pp.verifyProductsURL("products"), "Products page URL does not contain expected keyword");

		Assert.assertEquals(pp.titleCheck(), "Real Estate Property Websites that Convert | ProspectPortal by Entrata",
				"Products page title did not match");
	}

	/**
	 * Negative Test Case: Intentionally failing test to check false positive. -
	 * Expects that an invalid keyword should NOT be present in the Products page
	 * URL
	 */
	@Test
	public void verifyIncorrectProductsPageURL() {
		ProductsPage pp = new ProductsPage(driver);
		pp.navigateToProductsPage();

		// Fix: This should be assertFalse, not assertTrue
		Assert.assertTrue(pp.verifyProductsURL("invalidproduct"),
				"Invalid product keyword was unexpectedly found in the Products page URL.");
	}

	/**
	 * Test Case: Validate navigation to Solutions > Student Housing. Verifies: -
	 * URL contains "student" - Title contains "Student Housing"
	 */
	@Test
	public void validateSolutionsPage() {
		SolutionsPage sp = new SolutionsPage(driver);
		sp.navigateToSolutionsPage();

		Assert.assertTrue(sp.verifySolutionsURL("student"), "Solutions page URL does not contain expected keyword");

		Assert.assertTrue(sp.titleCheck(), "Solutions page title does not contain 'Student Housing'");
	}

	/**
	 * Test Case: Validate Resources > Partners page navigation. Verifies: - URL
	 * contains "partners" - Title is exactly "Partner Ecosystem"
	 */
	@Test
	public void validateResourcesPage() {
		ResourcesPage rp = new ResourcesPage(driver);
		rp.navigateToResourcesPage();

		Assert.assertTrue(rp.verifyPartnersURL("partners"), "Resources page URL does not contain expected keyword");

		Assert.assertEquals(rp.titleCheck(), "Partner Ecosystem", "Resources page title did not match");
	}

	/**
	 * Test Case: Validate Company > News page navigation. Verifies: - URL
	 * contains "press" - Title is as expected
	 */
	@Test
	public void validateCompanyNewsPage() {
		CompanyNewsPage cnp = new CompanyNewsPage(driver);
		cnp.navigateToCompanyNewsPage();

		Assert.assertTrue(cnp.verifyCompanyNewsPageURL("press"),
				"Company News page URL does not contain expected keyword");

		Assert.assertEquals(cnp.titleCheck(), "Entrata Receives $200 Million Investment from Blackstone",
				"Company News page title did not match");
	}

}
