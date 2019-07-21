package reusableLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelDataProvider;

/**
 * @author Urmila
 * 
 *This is a super class and contains all the reusable methods
 */
public class BaseMethods {
	public RemoteWebDriver driver;
	public String URL, browser;
	public Browser browserName;
	public String dataSheetName;
	public enum Browser{CHROME, FIREFOX, EDGE}

	/**
	 * constructor that initializes properties file
	 */
	public  BaseMethods() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/java/resources/global.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = prop.getProperty("URL");
		browser=prop.getProperty("Browser");
	}
	/**
	 * Opens webDriver and navigate to application url
	 * @param browser
	 * @return webDriver
	 */
	public WebDriver OpenBrowser(Browser browser) {
		if(browser == browser.CHROME) {
			WebDriverManager.chromedriver().version("74.0.3729.6").setup();
			driver = new ChromeDriver();
		}else if(browser == browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}
	/**
	 * Opens up given browser before the test method
	 */
	@BeforeMethod
	public void beforeMethod(){
		OpenBrowser(browserName);		
	}
	/**
	 * Closes all opened browser after test is complete
	 * 
	 */
	@AfterMethod
	public void afterMethod(){
		closeBrowser();
	}

	/**
	 * This method enters data into the textbox
	 * 
	 * @param element Webelement to which data to be entered
	 * @param data what value should be entered into the webElemnt
	 */
	public void typeIntoTextBox(WebElement element, String data) {
		try {
			element.clear();
			element.sendKeys(data);

		} catch (InvalidElementStateException e) {
			e.printStackTrace();
		}catch(WebDriverException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method selects a value from the dropdown based on the visible text
	 * 
	 * @param select Dropdown webelemnt for which value should be selected
	 * @param visibleTest value that should be selected from the dropdown based on visible text
	 */
	public void dropdownSelectByVisibleText(WebElement select, String visibleTest)
	{
		Select dropdown=new Select(select);
		dropdown.selectByVisibleText(visibleTest);
	}
	/**
	 * Retrieves data from excel and stores in two dimensional object as the data type may not be string always. Remember to assign as many column
	 * values as parameters in @Test method
	 * @return data Object that holds data from the excel sheet
	 */
	@DataProvider(name="ExcelData")
	public Object[][] dataProvider() {
		String[][] data = ExcelDataProvider.getExcelData("TestData");
		return data;

	}

	/**
	 * Method that closes browser instance
	 * 
	 */
	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
