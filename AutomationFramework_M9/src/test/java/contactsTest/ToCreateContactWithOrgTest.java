package contactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInformationPage;
import elementRepository.ContactsPage;
import elementRepository.CreatingNewContactPage;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactWithOrgTest extends BaseClass {

	@Test(groups = "Smoke")
	public void toCreateContactWithOrg_005() throws EncryptedDocumentException, IOException {
		HomePage homePage = new HomePage(driver);
		homePage.getContactsLink().click();
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getCreateContactIcon().click();
		ExcelFileUtility excelFileUtil = new ExcelFileUtility();
		String LASTNAME = excelFileUtil.excelFileData("Contacts", 1, 2);
		CreatingNewContactPage newContactPage = new CreatingNewContactPage(driver);
		newContactPage.getLastnameTextField().sendKeys(LASTNAME);
		newContactPage.getSelectOrgIcon().click();
		WebDriverUtility driverUtility = new WebDriverUtility();
		driverUtility.toSwichWindow(driver, "Accounts&action"); // Switch driver control to new window
		driver.findElement(By.linkText("QSP")).click();
		driverUtility.toSwichWindow(driver, "Contacts&action"); // Switch back driver control to main window
		newContactPage.getSaveButton().click();
		ContactInformationPage informationPage = new ContactInformationPage(driver);
		String lastname = informationPage.getHeaderElement().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));
	}
}
