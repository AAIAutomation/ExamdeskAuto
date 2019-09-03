package Selenium_with_maven.Automation.helper.BrowserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiframework.proctur.eLearn.logger.resource.ResourceHelper;

public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--test-type");
		options.addArguments("--disabe-popup-blocking");
		System.out.println("new test from");
		
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		options.merge(capabilities);
//		ChromeDriver driver = new ChromeDriver(options);
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		options.setCapability(ChromeOptions.CAPABILITY, chrome);
		//Linux
		if(System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return options;
		
	}
	
	public WebDriver getChromeDriver(ChromeOptions cap) {
		
	 {
			if(System.getProperty("os.name").contains("Mac")) {
				System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\Drivers\\chromedriver.exe"));
				return new ChromeDriver(cap);
			}
			else if(System.getProperty("os.name").contains("Window")){
				System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\Drivers\\chromedriver.exe"));
				return new ChromeDriver(cap);
			}
	}
		return null;
	}
}
