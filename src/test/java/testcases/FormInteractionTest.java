package testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.DemoFormPage;
import testcomponents.BaseTest;

/**
 * This test case automates interaction with the demo request form on Entrata's
 * website.
 * 
 * It uses data-driven testing by loading input values (like first name, last
 * name, email, role) from a JSON file, fills the form, and validates the data
 * was entered correctly.
 */
public class FormInteractionTest extends BaseTest {

	/**
	 * Test to interact with the demo request form and validate the input.
	 * 
	 * Steps: 1. Navigate to the demo form. 2. Fill it with input data from JSON. 3.
	 * Verify that first and last names match expected values.
	 * 
	 * @param input HashMap containing keys: firstName, lastName, email, role
	 */
	@Test(dataProvider = "getData")
	public void interactWithDemoRequestForm(HashMap<String, String> input) {
		DemoFormPage dmp = new DemoFormPage(driver);

		// Fill form using test data
		dmp.fillDemoForm(input.get("firstName"), input.get("lastName"), input.get("email"), input.get("role"));

		// Assertion to validate correct data entry
		Assert.assertTrue(dmp.verfiyEnteredData(input.get("firstName"), input.get("lastName")),
				"Entered name does not match expected values");
	}

	/**
	 * Data provider method that reads input data from a JSON file and returns it in
	 * the form of an Object[][] for TestNG parameterization.
	 * 
	 * @return Object[][] with multiple HashMaps of form input data
	 * @throws IOException if file reading fails
	 */
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> lst = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\testresources\\PurchaseOrder.json");

		return new Object[][] { { lst.get(0) } };
	}
}
