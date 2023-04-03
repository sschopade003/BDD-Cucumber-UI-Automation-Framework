package PageObject;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import AutomationUtilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Sandip S Chopade
 *
 *         Base class for initiating browser and start execution with base url
 */
public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;
	public static ReadConfig readConfig;

	public static WebDriver initializeDriver() {
		readConfig = new ReadConfig();

		String browserName = readConfig.getBrowser();

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.get(readConfig.getBaseURL());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
}
