package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import elementRepository.ContactInformationPage;
import elementRepository.ContactsPage;
import elementRepository.CreatingNewContactPage;
import elementRepository.HomePage;
import elementRepository.LoginPage;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTAndGUAndPom {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility propertyFileUtil = new PropertyFileUtility();
		ExcelFileUtility excelFileUtil = new ExcelFileUtility();
		WebDriverUtility driverUtility = new WebDriverUtility();

		// Read Data from Property file
		String BROWSER = propertyFileUtil.propertyFileData("browser");
		String URL = propertyFileUtil.propertyFileData("url");
		String USERNAME = propertyFileUtil.propertyFileData("username");
		String PASSWORD = propertyFileUtil.propertyFileData("password");

		// Read data from Excel file
		String LASTNAME = excelFileUtil.excelFileData("Contacts", 1, 2);

		// Test Script
		// Step 1 :- Launch Browser
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driverUtility.toMaximize(driver);
		driverUtility.toWaitForElement(driver);
		driver.get(URL);

		// Step-2 :- Login to application with valid credentials
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getUsernameTextField().sendKeys(USERNAME);
		loginPage.getPasswordTextField().sendKeys(PASSWORD);
		loginPage.getLoginButton().click();

		// Step-3 :- Navigate to contacts link
		HomePage homePage = new HomePage(driver);
		homePage.getContactsLink().click();

		// Step-4 :- Click on Create contact look up image
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getCreateContactIcon().click();

		// Step-5 :- Create contact with mandatory fields
		CreatingNewContactPage newContactPage = new CreatingNewContactPage(driver);
		newContactPage.getLastnameTextField().sendKeys(LASTNAME);

		// Step-6 :- Save and Verify
		newContactPage.getSaveButton().click();
		ContactInformationPage informationPage = new ContactInformationPage(driver);
		String lastname = informationPage.getHeaderElement().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "----Passed");
		} else {
			System.out.println(lastname + "----Failed");
		}

		// Step-7 :- Logout of Application
		driverUtility.toMouseHover(driver, homePage.getLogoutElement());
		homePage.getSignoutLink().click();

		// Step-8 :- Close Application
		driver.quit();
	}
}
