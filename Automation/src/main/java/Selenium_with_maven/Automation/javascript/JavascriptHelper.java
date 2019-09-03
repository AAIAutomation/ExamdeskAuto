package Selenium_with_maven.Automation.javascript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.Frame.FrameHelper;

public class JavascriptHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);

	public JavascriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScripHeper has been initilised");
	}

	// Method to execute java script using single argument
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return executeScript(script);
	}
//using multiple argument
	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return executeScript(script, args);
	}

	public void ScrollToElement(WebElement element) {
		log.info("scroll to webelemenet...");
		executeScript("window.scrollTo(arguments[0], arguments[1])", element.getLocation().x, element.getLocation().y);
	}

	public void ScrollToElementAndClick(WebElement element) {
		ScrollToElement(element);
		element.click();
		log.info("element is clicked " + element.toString());
	}

	// we can use either above two method for scroll or below this two methods
	// We will use java script method here.
	public void ScrollIntoView(WebElement element) {
		log.info("scroll till web element");
		executeScript("argument[0].scrollIntoView()", element);
	}

	public void ScrollIntoViewAndClick(WebElement element) {
		ScrollIntoView(element);
		element.click();
		log.info("element is clicked" + element.toString());
	}

	public void ScrollDownVertically() {
		log.info("scrolling vertically down...");
		executeScript("window.scrollTo(0, document.body.scroll.Hight)");
	}

	public void ScrollUpVertically() {
		log.info("scrolling vertically up...");
		executeScript("window.scrollTo(0, -document.body.scroll.Hight)");
	}
	
	public void ScrollDownByPixel(int pixel) {
		executeScript("window.scrollBy(0," +pixel +")");
	}
	public void ScrollUpByPixel(int pixel) {
		executeScript("window.scrollBy(0,-" +pixel +")");
	}
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
	
	//when you unable to click using selenium click then use this java script executor.
	public void ClickElementByJavaScriptExecutors(WebElement element) {
		executeScript("arguments[0].click();",element);
	}
}
