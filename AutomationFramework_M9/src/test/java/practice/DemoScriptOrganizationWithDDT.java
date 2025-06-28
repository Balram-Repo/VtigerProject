package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptOrganizationWithDDT {

	public static void main(String[] args) throws IOException {
		// Read data from Property file
		FileInputStream propertyFile = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties properties = new Properties();
		properties.load(propertyFile);
		String BROWSER = properties.getProperty("browser");
		String URL = properties.getProperty("url");
		String USERNAME = properties.getProperty("username");
		String PASSWORD = properties.getProperty("password");

		// Read data from Excel File
		FileInputStream excelFile = new FileInputStream(".\\src\\test\\resources\\testDataM9.xlsx");
		Workbook wb = WorkbookFactory.create(excelFile);
		String ORGNAME = wb.getSheet("Organization").getRow(1).getCell(2).toString();

		// Launch Browser
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step-2 :- Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step-3 :- Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step-4 :- Click on Create Organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Random
		Random r = new Random();
		int randomValue = r.nextInt(1000);

		// Step-5 :- Create Organization with Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME + "-" + randomValue);

		// Step-6 :- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgName.contains(ORGNAME + "-" + randomValue)) {
			System.out.println(orgName + "---- Passed");
		} else {
			System.out.println(ORGNAME + "---- Failed");
		}

		// Step-7 :- Logout of Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step-8 :- Close Application
		driver.quit();

	}
}
