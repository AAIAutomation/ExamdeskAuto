package Selenium_with_maven.Automation.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.testbase.TestBase;

public class VerificationHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);

	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
	}
	// In selenium if any element dispayed then getting true and if not true then
	// getting exception.

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is Displayed" + element.getText());
			TestBase.extentLog("element is Displayed" + element.getText());
			return true;
		} catch (Exception e) {
			log.error("element is not Displayed", e.getCause());
			TestBase.extentLog("element is not Displayed"+e.getCause());
			return false;
		}
	}

	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is present" + element.getText());
			TestBase.extentLog("element is present");
			return false;
		} catch (Exception e) {
			log.error("element is not present...");
			TestBase.extentLog("element is present");
			return true;
		}
	}

	public String getText(WebElement element) {
		if(element == null) {
			log.info("webelement is null");
			TestBase.extentLog("webelement is null");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status) {
			log.info("element text is ..."+element.getText());
			TestBase.extentLog("element text is ..."+element.getText());
			return element.getText();
		}
		else {
			return null;
		}
	}
	public String getTitle() {
		log.info("page Title is: "+driver.getTitle());
		TestBase.extentLog("page Title is: "+driver.getTitle());
		return driver.getTitle();
	}

}
