package facebookPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import reusableLib.BaseMethods;

/**
 * @author Urmila
 * 
 * This class contains all the webelements and actions for the registration confirmation page 
 *
 */
public class RegistrationConfirmationPage extends BaseMethods{

	/**
	 * This is a constructor that initializes the current webdriver
	 * @param driver
	 */
	public RegistrationConfirmationPage(RemoteWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	/**
	 * Stores all the webelements present in the registration confirmation page 
	 * 
	 * This uses @FindBy method of pagefactory
	 */
	@FindBy(how=How.XPATH, using="(//h2[text()='Enter the code from your email'])[1]") 
	private WebElement header;

	/**
	 * This method verifies if the registration is successful by asserting the header of the page.
	 */
	public void verifyHeader() {
		Assert.assertEquals(header.isDisplayed(), true);
		if(header.isDisplayed()) {
			System.out.println("Registration successful");
		}
	}
}
