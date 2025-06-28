package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastnameTextField;

	@FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
	private WebElement selectOrgIcon;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebElement getLastnameTextField() {
		return lastnameTextField;
	}

	public WebElement getSelectOrgIcon() {
		return selectOrgIcon;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

}
