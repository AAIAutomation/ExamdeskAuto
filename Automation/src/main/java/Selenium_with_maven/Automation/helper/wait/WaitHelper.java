package Selenium_with_maven.Automation.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

public class WaitHelper {
    
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}
	/**
	 * This is ImplicitWait Method
	 * @param timeout
	 * @param unit
	 */
	public void SetImplicitWait (int timeout, TimeUnit unit) {
		log.info("Implicit wait has been set to " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
		log.info("check its coming inside impliciate wait method inside waithelper.");
//	   driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	/**
	 * This will help us to get WebDriverWait object
	 * @param TimeOutInSeconds
	 * @param PollingEveryInMillisec
	 * @return
	 */
	private WebDriverWait getWait(int TimeOutInSeconds, int PollingEveryInMillisec) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(PollingEveryInMillisec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	/**
	 * This Method will help us to make sure element is visible
	 * @param element
	 * @param timeOutInSeconds
	 * @param PollingEveryInMillisec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds, int PollingEveryInMillisec) {
		log.info("waiting for :"+element+" for :" + timeOutInSeconds +"seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, PollingEveryInMillisec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	/**
	 * This will make sure element is clickable
	 * @param element
	 * @param timeOutInSeconds
	 * @param PollingEveryInMillisec
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds, int PollingEveryInMillisec) {
		log.info("waiting for :"+element.toString()+" for :" + timeOutInSeconds +"seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is Clickable now");
	}
     /**
      * This method will make sure invisibility of element
      * @param element
      * @param timeOutInSeconds
      * @return
      */
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		//wait till element get disappeared from the screen.
		log.info("waiting for :"+element.toString()+" for :" + timeOutInSeconds +"seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisible now");
		return status;
	}
    /**
     * This method will wait for frame ToBeAvailableAndSwitchToIt
     * @param element
     * @param timeOutInSeconds
     */
	public void waitframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		// wait till element get disappeared from the screen.
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + "seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame to be avalible and switched");
			
	}
	
	public void PageLoadTime(long timeout, TimeUnit unit) {
		log.info("wait for page to load for :" +timeout+ "And" +unit);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
	}
	
	//This is make sure element is cllickabe
	
	public void waitForElement(WebElement element, int timeoutInSeconds) {
		log.info("wait for :" +element.toString()+"for:" +timeoutInSeconds + "seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now....");
	}
}

