package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationUtilities.Utilities;
import PageConstant.LoginConstant;

/**
 * 
 * @author Sandip S Chopade
 *
 *         Login class with Web Elements to work with corresponding web page
 */

public class LoginPage implements LoginConstant {

	WebDriver driver;
	Utilities utilities;
	int timeInSecond = 10;

	@FindBy(id = "user-name")
	WebElement usernameTextbox;

	@FindBy(id = "password")
	WebElement passwordTextbox;

	@FindBy(xpath = "//input[@id='login-button']")
	WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		utilities = new Utilities(driver);
		PageFactory.initElements(driver, this);
	}

	public void goToLoginPage() {
		driver.get("https://www.saucedemo.com/");
	}

	public void enterUsername(String username) {
		utilities.waitForElementToBeVisible(usernameTextbox, timeInSecond);
		usernameTextbox.sendKeys(username);
	}

	public void enterPassword(String password) {
		utilities.waitForElementToBeVisible(passwordTextbox, timeInSecond);
		passwordTextbox.sendKeys(password);
	}

	public void clickLoginButton() {
		utilities.waitForElementToBeClickable(loginButton, timeInSecond);
		loginButton.click();
	}

	public boolean getPageTitle() {
		return driver.getTitle().equalsIgnoreCase(HOME_PAGE_TITLE);
	}

}
