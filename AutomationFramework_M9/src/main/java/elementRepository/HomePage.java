package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;

	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutElement;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getLogoutElement() {
		return logoutElement;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}

}
