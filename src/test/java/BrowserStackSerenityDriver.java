import com.google.common.base.Strings;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Properties;

public class BrowserStackSerenityDriver implements DriverSource {
	
	public static String browser = null;
	public static String os_version = null;
//	public static String resolution = null;
	public static String realMobile = null;
	public static String device = null;

	public static Properties CONFIG = null;

	public WebDriver newDriver() {

		try {
			EnvironmentVariables CONFIG = SystemEnvironmentVariables.createEnvironmentVariables();

			DesiredCapabilities caps = new DesiredCapabilities();

			realMobile = CONFIG.getProperty("br.realMobile");
			if (Strings.isNullOrEmpty(realMobile)) {
				realMobile = "false";
			}
			System.out.println(realMobile);
			if (realMobile.equals("true")) {
				browser = CONFIG.getProperty("br.browser");
				if (Strings.isNullOrEmpty(browser))
					browser = "iPhone";

				device = CONFIG.getProperty("br.device");
				if (Strings.isNullOrEmpty(device))
					device = "iPhone 8 Plus";

				os_version = CONFIG.getProperty("br.os_version");
				if (Strings.isNullOrEmpty(os_version))
					os_version = "11.0";

				caps.setCapability("browserName", browser);
				caps.setCapability("device", device);
				caps.setCapability("realMobile", "true");
				caps.setCapability("os_version", os_version);
				caps.setCapability("browserstack.safari.enablePopups", "true");

			} else {
				caps.setCapability("browser", "Chrome");
				caps.setCapability("browser_version", "62.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "8.1");
				caps.setCapability("resolution", "1024x768");
			}

			return new RemoteWebDriver(new URL("http://quanmt1:b8UfvGMNP1hJsxz1q15z@hub-cloud.browserstack.com/wd/hub"),
					caps);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public boolean takesScreenshots() {
		return true;
	}
}