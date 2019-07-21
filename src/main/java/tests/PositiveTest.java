package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import facebookPages.RegistrationPage;

import reusableLib.BaseMethods;

/**
 * @author Urmila
 * This class is used to automate the registration page
 *
 */
public class PositiveTest extends BaseMethods {
	/**
	 * setup the browser and excel sheet before test
	 */
	@BeforeTest
	public void setup() {
		browserName = Browser.CHROME;
		dataSheetName = "Sheet1";
	}
	/**
	 * Calling dataprovider with its name
	 *
	 */
	@Test(dataProvider="ExcelData")
	public void PositiveTestForRegPage(String firstname,String surname,String email,String password,String day,String month,String year,String gender) {
		new RegistrationPage(driver)
		.enterFirstName(firstname)
		.enterSurName(surname)
		.enterEmailOrMobileNumber(email)
		.enterNewPassword(password)
		.enterDateOfBirth(day,month,year)
		.enterGender(gender)
		.submitRegistration()
		.verifyHeader();

	}

}
