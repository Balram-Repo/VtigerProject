package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Scenario_3 {

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

		// Step-6 :- Select "Chemicals" in industry dropdown
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select industry = new Select(industryDropdown);
		industry.selectByValue("Chemicals");

		// Step-7 :- Save and Verify
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		String organizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationName.contains("ORG"+"-"+randomValue)) {
			System.out.println(organizationName + "---- Passed");
		} else {
			System.out.println(organizationName + "---- Failed");
		}

		// Step-8 :- Close Application
		driver.quit();
	}
}
