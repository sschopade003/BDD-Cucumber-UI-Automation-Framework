package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationUtilities.Utilities;
import PageConstant.HomeConstant;

public class HomePage implements HomeConstant {

	WebDriver driver;
	Utilities utilities;
	int timeoutInSeconds = 10;

	/**
	 * 
	 * @author Sandip S Chopade
	 *
	 *         HomePage class with Web Elements to work with corresponding web page
	 */

	public HomePage(WebDriver driver) {
		this.driver = driver;
		utilities = new Utilities(driver);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-backpack']")
	WebElement addToCartForBackpack;

	@FindBy(xpath = "//*[@id='shopping_cart_container']//a")
	WebElement shoppingCart;

	@FindBy(xpath = "//*[@id='remove-sauce-labs-backpack']")
	WebElement removeItemFromCart;

	@FindBy(xpath = "//*[@id='continue-shopping']")
	WebElement continueShoppingBtn;

	public void addItemToCart() {
		utilities.waitForPageLoad(driver);
		utilities.waitForElementToBeVisible(addToCartForBackpack, timeoutInSeconds);
		utilities.waitForElementEnabled(addToCartForBackpack, timeoutInSeconds);
		Utilities.clickWithExplicitWait(driver, addToCartForBackpack);
	}

	public void goToCart() {
		utilities.waitForElementToBeVisible(shoppingCart, timeoutInSeconds);
		utilities.waitForElementEnabled(shoppingCart, timeoutInSeconds);
		Utilities.clickWithExplicitWait(driver, shoppingCart);
	}

	public void removeItemFromCart() {
		utilities.waitForElementToBeVisible(removeItemFromCart, timeoutInSeconds);
		utilities.waitForElementEnabled(removeItemFromCart, timeoutInSeconds);
		Utilities.clickWithExplicitWait(driver, removeItemFromCart);
	}

	public void continueShopping() {
		utilities.waitForElementToBeVisible(continueShoppingBtn, timeoutInSeconds);
		utilities.waitForElementEnabled(continueShoppingBtn, timeoutInSeconds);
		Utilities.clickWithExplicitWait(driver, continueShoppingBtn);
	}

	public boolean verifyTitle() {
		return driver.getTitle().equalsIgnoreCase(HOME_PAGE_TITLE);
	}
}
