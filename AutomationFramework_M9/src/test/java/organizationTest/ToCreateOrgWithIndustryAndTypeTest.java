package organizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.CreatingNewOrganizationPage;
import elementRepository.HomePage;
import elementRepository.OrganizationInformationPage;
import elementRepository.OrganizationsPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateOrgWithIndustryAndTypeTest extends BaseClass {

	@Test(groups = "Regression")
	public void toCreateOrgWithIndustryAndType_004() throws EncryptedDocumentException, IOException {
		HomePage homePage = new HomePage(driver);
		homePage.getOrganizationsLink().click();
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.getCreateOrganizationIcon().click();
		CreatingNewOrganizationPage newOrganizationPage = new CreatingNewOrganizationPage(driver);
		ExcelFileUtility excelFileUtil = new ExcelFileUtility();
		
		JavaUtility javaUtil = new JavaUtility();
		
		String ORGNAME = excelFileUtil.excelFileData("Organization", 1, 2) + javaUtil.toGenerateRandomNumbers();
		newOrganizationPage.getOrganizationNameTextField().sendKeys(ORGNAME);
		WebDriverUtility driverUtility = new WebDriverUtility();
		driverUtility.toHandleDropdown(newOrganizationPage.getIndustryDropDown(), "Energy");
		driverUtility.toHandleDropdown(newOrganizationPage.getTypeDropDown(), "Customer");
		newOrganizationPage.getSaveButton().click();
		OrganizationInformationPage informationPage = new OrganizationInformationPage(driver);
		String orgName = informationPage.getHeaderElement().getText();
		Assert.assertTrue(orgName.contains(ORGNAME));
	}
}
