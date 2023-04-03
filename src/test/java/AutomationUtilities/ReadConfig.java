package AutomationUtilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author Sandip S Chopade
 *
 *         ReadConfig class help to read properties file
 */
public class ReadConfig {

	Properties properties;
	String path = "src/main/resource/Config.properties";

	public ReadConfig() {
		try {
			properties = new Properties();

			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBrowser() {
		String value = properties.getProperty("browser");

		if (value != null)
			return value;
		else
			throw new RuntimeException("url not specified in config file.");
	}

	public String getUsername() {
		String value = properties.getProperty("username");

		if (value != null)
			return value;
		else
			throw new RuntimeException("username is not specified in config file.");
	}

	public String getPassword() {
		String value = properties.getProperty("password");

		if (value != null)
			return value;
		else
			throw new RuntimeException("password is not specified in config file.");
	}

	public String getBaseURL() {
		String value = properties.getProperty("baseURL");

		if (value != null)
			return value;
		else
			throw new RuntimeException("Base URL is not specified in config file.");
	}

}
