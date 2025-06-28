package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import elementRepository.HomePage;
import elementRepository.LoginPage;

public class BaseClass {

	PropertyFileUtility propertyFileUtil = new PropertyFileUtility();
	ExcelFileUtility excelFileUtil = new ExcelFileUtility();
	WebDriverUtility driverUtility = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver; // For Listener

	@BeforeSuite(groups = { "Smoke", "Regression" })
	public void beforeSuiteConfig() {
		Reporter.log("Database Connection Established...", true);
	}

//	@Parameters("browser")
//	@BeforeTest
	@BeforeClass(groups = { "Smoke", "Regression" })
	public void beforeClassConfig(/*String BROWSER */) throws IOException {
		String BROWSER = propertyFileUtil.propertyFileData("browser");
		String URL = propertyFileUtil.propertyFileData("url");
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		sDriver = driver; // For Listener

		Reporter.log("Browser got Launched", true);
		driverUtility.toMaximize(driver);
		Reporter.log("Browser got Maximized", true);
		driverUtility.toWaitForElement(driver);
		driver.get(URL);
	}

	@BeforeMethod(groups = { "Smoke", "Regression" })
	public void beforeMethodConfig() throws IOException {
		String USERNAME = propertyFileUtil.propertyFileData("username");
		String PASSWORD = propertyFileUtil.propertyFileData("password");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getUsernameTextField().sendKeys(USERNAME);
		loginPage.getPasswordTextField().sendKeys(PASSWORD);
		loginPage.getLoginButton().click();
		Reporter.log("Login Successfull.", true);
	}

	@AfterMethod(groups = { "Smoke", "Regression" })
	public void afterMethodConfig() {
		HomePage homePage = new HomePage(driver);
		driverUtility.toMouseHover(driver, homePage.getLogoutElement());
		homePage.getSignoutLink().click();
		Reporter.log("Logout Successfull.", true);
	}

	@AfterClass(groups = { "Smoke", "Regression" })
	public void afterClassConfig() {
		driver.quit();
		Reporter.log("Browser got Closed.", true);
	}

	@AfterSuite(groups = { "Smoke", "Regression" })
	public void afterSuiteConfig() {
		Reporter.log("Database Connection Disconnected...", true);
	}
}
