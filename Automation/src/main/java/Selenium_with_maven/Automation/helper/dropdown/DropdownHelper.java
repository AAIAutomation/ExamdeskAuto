package Selenium_with_maven.Automation.helper.dropdown;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

public class DropdownHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropdownHelper.class);
	
	public DropdownHelper(WebDriver driver) {
		this.driver = driver;
		log.info("dropdown Helper Object Created...");
	}
	
	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("select using value and value is"+value);
		select.selectByValue(value);
	}
	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("select using value and value is"+index);
		select.selectByIndex(index);
	}
	public void selectUsingVisibleText(WebElement element, String VisibleText) {
		Select select = new Select(element);
		log.info("select using value and value is"+VisibleText);
		select.selectByVisibleText(VisibleText);
	}
	public void deselectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("deselect using value and value is"+value);
		select.deselectByValue(value);
	}
	public void deselectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("deselect using value and value is"+index);
		select.deselectByIndex(index);
	}
		public List<String> getAllDropdownData(WebElement element){
			
			Select select = new Select(element);
			List<WebElement> elementList = select.getOptions();
			List<String> valueList = new LinkedList<String>();
			for(WebElement ele: elementList) {
				log.info(ele.getText());
				valueList.add(ele.getText());
			}
			return valueList;
		}
}

