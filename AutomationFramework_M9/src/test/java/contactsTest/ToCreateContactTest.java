package contactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInformationPage;
import elementRepository.ContactsPage;
import elementRepository.CreatingNewContactPage;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {

	@Test(groups = "Smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage homePage = new HomePage(driver);
		homePage.getContactsLink().click();
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getCreateContactIcon().click();
		ExcelFileUtility excelFileUtil = new ExcelFileUtility();
		String LASTNAME = excelFileUtil.excelFileData("Contacts", 1, 2);
		CreatingNewContactPage newContactPage = new CreatingNewContactPage(driver);
		newContactPage.getLastnameTextField().sendKeys(LASTNAME);
		newContactPage.getSaveButton().click();

		Assert.fail(); // To fail a Test Script
		ContactInformationPage informationPage = new ContactInformationPage(driver);
		String lastname = informationPage.getHeaderElement().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));
	}
}
