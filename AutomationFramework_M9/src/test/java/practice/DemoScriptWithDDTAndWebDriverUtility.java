package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTAndWebDriverUtility {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility propertyFileUtil = new PropertyFileUtility();
		ExcelFileUtility excelFileUtil = new ExcelFileUtility();
		WebDriverUtility driverUtil = new WebDriverUtility();

		// To read data from Property file
		String BROWSER = propertyFileUtil.propertyFileData("browser");
		String URL = propertyFileUtil.propertyFileData("url");
		String USERNAME = propertyFileUtil.propertyFileData("username");
		String PASSWORD = propertyFileUtil.propertyFileData("password");

		// To Read data from Excel file
		String LASTNAME = excelFileUtil.excelFileData("Contacts", 1, 2);

		// Launch Browser
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driverUtil.toMaximize(driver);
		driverUtil.toWaitForElement(driver);

		// Step-2 :- Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step-3 :- Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step-4 :- Click on Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step-5 :- Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// Step-6 :- Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastName.contains(LASTNAME)) {
			System.out.println(lastName + "---- Passed");
		} else {
			System.out.println(lastName + "---- Failed");
		}

		// Step-7 :- Logout of Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		driverUtil.toMouseHover(driver, logout);
		driver.findElement(By.linkText("Sign Out")).click();

		// Step-8 :- Close Application
		driver.quit();
	}
}
