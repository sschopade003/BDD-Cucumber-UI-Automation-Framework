package StepDefination;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import PageObject.Base;
import PageObject.HomePage;
import PageObject.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef extends Base {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;

	@Before
	public void setup() {
		driver = Base.initializeDriver();
		log = LogManager.getLogger("StepDef");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Given("^User is on the login page$")
	public void user_is_on_login_page() {

//		loginPage.goToLoginPage();
		log.info("User is landed on log page");

	}

	@When("^User enters valid username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void user_enters_username_password(String username, String password) {
		loginPage.enterUsername(readConfig.getUsername());
		loginPage.enterPassword(readConfig.getPassword());
	}

	@When("^User clicks on Login button$")
	public void user_clicks_login_button() {
		loginPage.clickLoginButton();
	}

	@Then("^User should be redirected to the dashboard page$")
	public void user_redirected_dashboard_page() {
		Assert.assertEquals(true, loginPage.getPageTitle());
		log.info("User logged in successfully");
	}

	@Given("User is on the homepage")
	public void user_is_on_the_homepage() {
		loginPage.enterUsername(readConfig.getUsername());
		loginPage.enterPassword(readConfig.getPassword());
		loginPage.clickLoginButton();
		Assert.assertEquals(true, homePage.verifyTitle());
	}

	@When("User adds the item to the cart")
	public void user_adds_the_item_to_the_cart() {
		homePage.addItemToCart();
		log.info("Item added to cart");
	}

	@When("User goes to the cart")
	public void user_goes_to_the_cart() {
		homePage.goToCart();
	}

	@When("User removes the item from the cart")
	public void user_removes_the_item_from_the_cart() {
		homePage.removeItemFromCart();
		log.info("Item removed from cart");
	}

	@Then("User continue with shopping")
	public void user_should_see_an_empty_cart() {
		homePage.continueShopping();
		Assert.assertEquals(true, homePage.verifyTitle());
	}

	@And("Close browser")
	public void close_browser() {
		log.info("Scenario excuted successfully...");
		driver.close();
		driver.quit();
	}

	@After
	public void teardown(Scenario scenario) {
		System.out.println("Tear Down method executed..");
		if (scenario.isFailed() == true) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		driver.quit();
	}

}
