package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario_2 {

	public static void main(String[] args) {
		// Step-1 :- Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step-2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step-3 :- Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step-4 :- Click on Create Organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Random
		Random r = new Random();
		int randomValue = r.nextInt(1000);
		
		// Step-5 :- Create Organization with Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("ORG"+"-"+randomValue);

		// Step-6 :- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgName.contains("ORG"+"-"+randomValue)) {
			System.out.println(orgName + "---- Passed");
		} else {
			System.out.println(orgName + "---- Failed");
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
