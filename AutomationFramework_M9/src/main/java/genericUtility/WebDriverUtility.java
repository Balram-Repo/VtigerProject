package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of methods related to WebDriver like maximize, minimize,
 * to handle dropDown , to handle mouse and keyboard actions implicitlyWait, to
 * take Screenshot, to handle frames
 */
public class WebDriverUtility {

	/**
	 * This method is used to maximize window provided driver
	 * 
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to minimize window provided driver
	 * 
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method will wait until the element loaded in WebPage (Implicit Wait)
	 * 
	 * @param driver
	 * @param second
	 */
	public void toWaitForElement(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	/**
	 * This method will wait until the element is clickable provided with driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void elementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will wait until the element is visible provided with driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void visibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used to handle the dropDown using index provided with element
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle dropDown using value provided with element
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle dropDown using visible text provided with
	 * element
	 * 
	 * @param text
	 * @param element
	 */
	public void toHandleDropdown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method is used to handle frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to handle frame using id or name
	 * 
	 * @param driver
	 * @param id_name
	 */
	public void toHandleFrame(WebDriver driver, String id_name) {
		driver.switchTo().frame(id_name);
	}

	/**
	 * This method is used to handle frame using webElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to switch back from frame
	 * 
	 * @param driver
	 */
	public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to mouse hover to and element provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toMouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method is used to perform right click action provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * This method is used to perform double click action provided driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to perform drag and drop action provided driver, source
	 * element and target element
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}

	/**
	 * This method is used to handle alert popup by accepting it
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to handle alert popup by dismissing it
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method is used to handle alert popup and capture the popup text
	 * 
	 * @param driver
	 * @return
	 */
	public String toHandleAlertPopupAndCaptureText(WebDriver driver) {
		Alert alertPopup = driver.switchTo().alert();
		String popupMsg = alertPopup.getText();
		alertPopup.accept();
		return popupMsg;
	}

	/**
	 * This method is used to take a screenshot of an entire webPage provided with
	 * driver and screenshot name
	 * 
	 * @param driver
	 * @param fileName
	 * @return 
	 * @throws IOException
	 */
	public String toTakeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + screenshotName + ".png");
		FileHandler.copy(temp, src);
		String completePath = src.getAbsolutePath();
		return completePath;
	}

	/**
	 * This method is used to switch the drover control to desired window provided
	 * with driver and partial title
	 * 
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwichWindow(WebDriver driver, String partialTitle) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			String titleOfWebPage = driver.switchTo().window(id).getTitle();
			if (titleOfWebPage.contains(partialTitle))
				break;
		}
	}

}
