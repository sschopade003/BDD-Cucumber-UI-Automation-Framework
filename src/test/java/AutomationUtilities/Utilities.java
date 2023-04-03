package AutomationUtilities;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

/**
 * 
 * @author Sandip S Chopade
 *
 *         Utilities class contains several methods to help in UI automation
 */

public class Utilities {

	private WebDriver driver;
	private static final int DEFAULT_TIMEOUT = 10;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private Actions actions;

	public Utilities(WebDriver driver) {
		this.driver = driver;
	}

	public void click(WebElement element, Duration waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void clickWithExplicitWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void takeScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./screenshots/" + screenshotName + ".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}

	public void attachScreenshot(Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void switchToWindow(WebDriver driver, int windowIndex) {
		Set<String> windows = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windows);
		driver.switchTo().window(windowsList.get(windowIndex));
	}

	public static void selectDropdownOption(WebElement dropdown, String optionValue) {
		Select select = new Select(dropdown);
		select.selectByValue(optionValue);
	}

	public static String getTextFromElement(WebElement element) {
		return element.getText();
	}

	public static String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static boolean isElementDisplayed(WebDriver driver, WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isElementEnabled(WebDriver driver, WebElement element) {
		try {
			return element.isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isElementSelected(WebDriver driver, WebElement element) {
		try {
			return element.isSelected();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).build().perform();
	}

	public static void switchToFrame(WebDriver driver, WebElement frame) {
		driver.switchTo().frame(frame);
	}

	public static Dimension getWindowSize(WebDriver driver) {
		return driver.manage().window().getSize();
	}

	public static void uploadFile(WebDriver driver, WebElement element, String filePath) {
		element.sendKeys(filePath);
	}

	public static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}

	public static String getRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public static void clickWithWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void selectDropdownOptionByText(WebElement element, String text) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(text);
	}

	public static String generateRandomString(int length) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			builder.append(ALPHA_NUMERIC_STRING.charAt(random.nextInt(ALPHA_NUMERIC_STRING.length())));
		}
		return builder.toString();
	}

	public static Date parseDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(dateStr);
	}

	public static String generateRandomEmail() {
		return generateRandomString(10) + "@example.com";
	}

	public static String generateRandomPhoneNumber() {
		Random random = new Random();
		int num1 = random.nextInt(900) + 100;
		int num2 = random.nextInt(643) + 100;
		int num3 = random.nextInt(9000) + 1000;
		return String.format("(%d) %d-%d", num1, num2, num3);
	}

	public List<WebElement> getWebElements(By locator) {
		try {
			List<WebElement> elements = driver.findElements(locator);
			return elements;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Methods for wait

	public WebElement waitForElementVisible(By locator, Duration waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementEnabled(WebElement locator, int waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(pageLoadCondition);
	}

	public void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeInvisible(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementToBeSelected(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	public void waitForPresenceOfElementLocated(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForTitleToBe(String title, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.titleIs(title));
	}

	public void waitForTitleToContain(String title, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void waitForUrlToBe(String url, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public void waitForUrlToContain(String url, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.urlContains(url));
	}

	public void waitForUrlToMatch(String regex, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.urlMatches(regex));
	}

	public void waitForAlertToBePresent(int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForPageToLoad(int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
	}

//	public void waitForAjaxComplete(WebDriver driver) {
//		new WebDriverWait(driver, Duration.ofSeconds(10))
//				.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active").equals(0));
//	}

// Action Class methods
	public void click(WebElement element) {
		actions.click(element).perform();
	}

	public void doubleClick(WebElement element) {
		actions.doubleClick(element).perform();
	}

	public void contextClick(WebElement element) {
		actions.contextClick(element).perform();
	}

	public void moveToElement(WebElement element) {
		actions.moveToElement(element).perform();
	}

	public void dragAndDrop(WebElement source, WebElement target) {
		actions.dragAndDrop(source, target).perform();
	}

	public void keyDown(CharSequence key) {
		actions.keyDown(key).perform();
	}

	public void keyUp(CharSequence key) {
		actions.keyUp(key).perform();
	}

	public void sendKeys(CharSequence... keysToSend) {
		actions.sendKeys(keysToSend).perform();
	}

	public void perform() {
		actions.perform();
	}
}
