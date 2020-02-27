package Selenium_with_maven.Automation.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.assertion.VerificationHelper;

public final class AlertHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	public AlertHelper(WebDriver driver) {
		this.driver= driver;
		log.info("Alert Helper object is created...");
	}
	
	public Alert getAlert() {
		log.info("alert test: " +driver.switchTo().alert().getText());
		return driver.switchTo().alert();
		
	}
	public void acceptAlert() {
		getAlert().accept();
		log.info("clicked on ok");
	}
	public void dismissAlert() {
		getAlert().dismiss();
		log.info("clicked on cancel");
	}
	public String getAlertText() {
		String text = getAlert().getText();
		log.info("alert text =:"+text);
		return text;
	}
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("alert is present");
			return true;
		}
		catch(NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}
	
	public void acceptAlertIfPresent() {
		if(isAlertPresent()) {
			acceptAlert();
		}
		else
		{
			log.info("Aleert is present");
		}	
	}
	public void dismissAlertIfPresent() {
		if(isAlertPresent()) {
			dismissAlert();
		}
		else
		{
			log.info("Alert is not present");
		}
	}
	public void acceptPrompt(String text) {
		if(isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
		}
	}
	
}	
