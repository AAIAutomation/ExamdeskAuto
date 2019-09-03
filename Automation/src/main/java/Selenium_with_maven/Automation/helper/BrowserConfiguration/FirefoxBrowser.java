package Selenium_with_maven.Automation.helper.BrowserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiframework.proctur.eLearn.logger.resource.ResourceHelper;

public class FirefoxBrowser {

		public FirefoxOptions getFirefoxOptions() {
			
			DesiredCapabilities firefox	= DesiredCapabilities.firefox();
			
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(true);
			firefox.setCapability(FirefoxDriver.PROFILE, profile);
			firefox.setCapability("marionette", true);
			
			FirefoxOptions	firefoxoption = new FirefoxOptions(firefox);
			//Linux
			if(System.getProperty("os.name").contains("Linux")) {
				firefoxoption.addArguments("--headless", "window-size=1024, 768", "---no-sandbox");
			}
			return firefoxoption;
		}
		public WebDriver getFirefoxDriver(FirefoxOptions cap) {
			
			if(System.getProperty("os.name").contains("window")) {
				System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\Drivers\\geckodriver.exe"));
			}
			return null;
		}
}
