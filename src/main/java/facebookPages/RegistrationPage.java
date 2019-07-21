package facebookPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import reusableLib.BaseMethods;

/**
 * @author Urmila
 * 
 * This class contains all the webelements and actions for the registration page 
 *
 */

public class RegistrationPage extends BaseMethods {

	/**
	 * This is a constructor that initializes the current webdriver
	 * @param driver 
	 */
	public RegistrationPage(RemoteWebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);

	}
	/**
	 * Stores all the webelements present in the registration page 
	 * 
	 * This uses @FindBy method of pagefactory
	 */
	@FindBy(how=How.NAME, using="firstname") 
	private WebElement firstName;
	@FindBy(how=How.NAME, using="lastname") 
	private WebElement surName;
	@FindBy(how=How.XPATH, using="//div[contains(text(),'Mobile number or email address')]/following-sibling::input") 
	private WebElement mobileOrEmail;
	@FindBy(how=How.XPATH,using="//div[text()='New password']/following-sibling::input")
	private WebElement newPassword;
	@FindBy(how=How.XPATH,using="//div[text()='Re-enter email address']/following-sibling::input")
	private WebElement reenterEmail;
	@FindBy(how=How.ID, using="day") 
	private WebElement day;
	@FindBy(how=How.ID, using="month") 
	private WebElement month;
	@FindBy(how=How.ID, using="year") 
	private WebElement year;
	@FindBy(how=How.XPATH, using="//label[text()='Female']/preceding-sibling::input") 
	private WebElement genderFemale;
	@FindBy(how=How.XPATH, using="//label[text()='Male']/preceding-sibling::input") 
	private WebElement genderMale;
	@FindBy(how=How.XPATH, using="//label[text()='Custom']/preceding-sibling::input") 
	private WebElement genderCustom;

	@FindBy(how=How.NAME, using="websubmit") 
	private WebElement submit;

	/**
	 * This method enters data into the firstname field
	 * @param firstnameValue
	 * @return
	 */
	public RegistrationPage enterFirstName(String firstnameValue) {
		typeIntoTextBox(firstName, firstnameValue);
		return this;
	}
	/**
	 * This method enters data into the surname field
	 * @param surnameValue
	 * @return
	 */
	public RegistrationPage enterSurName(String surnameValue) {
		typeIntoTextBox(surName, surnameValue);
		return this;
	}

	/**
	 * This method enters data into the email field
	 * @param emailValue
	 * @return
	 */
	public RegistrationPage enterEmailOrMobileNumber(String emailValue) {
		typeIntoTextBox(mobileOrEmail, emailValue);
		if(reenterEmail.isDisplayed()) {
			typeIntoTextBox(reenterEmail, emailValue);
		}
		return this;
	}

	/**
	 * This method enters data into the password field
	 * @param passwordValue
	 * @return
	 */
	public RegistrationPage enterNewPassword(String passwordValue) {
		typeIntoTextBox(newPassword, passwordValue);
		return this;
	}

	/**
	 * This method selects date of birth from the dropdown
	 * @param dayValue
	 * @param monthValue
	 * @param yearValue
	 * @return
	 */
	public RegistrationPage enterDateOfBirth(String dayValue,String monthValue,String yearValue) {
		dropdownSelectByVisibleText(day,dayValue);
		dropdownSelectByVisibleText(month,monthValue);
		dropdownSelectByVisibleText(year,yearValue);
		return this;
	}

	/**
	 * This method chooses a gender value from a radio button group
	 * @param genderValue
	 * @return
	 */
	public RegistrationPage enterGender(String genderValue) {
		if(genderValue.equalsIgnoreCase("F")) {
			genderFemale.click();
		}
		else if(genderValue.equalsIgnoreCase("M")) {
			genderMale.click();
		}
		else if(genderValue.equalsIgnoreCase("C")) {
			genderCustom.click();
		}
		return this;
	}

	/**
	 * This method submits the registration form
	 * @return returns next page which the registration confirmation page
	 */
	public RegistrationConfirmationPage submitRegistration() {
		submit.click();
		return new RegistrationConfirmationPage(driver);
	}
}
