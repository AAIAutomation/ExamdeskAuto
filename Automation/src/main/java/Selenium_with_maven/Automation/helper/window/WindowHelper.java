package Selenium_with_maven.Automation.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

// Let's say you have opened 10 windows and have to nevigate at 6 then use this method
public class WindowHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
		
	}

	public void SwitchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();
	}

	public void SwitchTotWindow(int index) {
		log.info("switching to parent window...");
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String Window : windows) {
			if (i == index) {
				driver.switchTo().window(Window);
			} else {
				i++;
			}

		}
	}

	public void CloseAllTabsAndSwitchedToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainwindow = driver.getWindowHandle();
		for (String Window : windows) {
			if (!Window.equalsIgnoreCase(mainwindow)) {
				driver.close();
			}

		}
		driver.switchTo().window(mainwindow);

	}

}
