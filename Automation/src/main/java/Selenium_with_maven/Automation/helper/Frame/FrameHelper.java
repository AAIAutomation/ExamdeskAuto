package Selenium_with_maven.Automation.helper.Frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

public class FrameHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);

	/**
	 * Initialized driver
	 * 
	 * @param driver
	 */
	public FrameHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * this method will switched frame based on frame index
	 * 
	 * @param frameindex
	 */
	public void SwitchToFrameIndex(int frameindex) {
		driver.switchTo().frame(frameindex);
		log.info("Switched to" + frameindex + "frame");
	}

	/**
	 * this method will switched frame based on frame name
	 * 
	 * @param framename
	 */
	public void SwitchToFrameName(String framename) {
		driver.switchTo().frame(framename);
		log.info("Switched to" + framename + "frame");
	}

	/**
	 * this method will switched frame based on frame web element
	 * 
	 * @param element
	 */
	public void SwitchToFrameWebelement(WebElement element) {
		driver.switchTo().frame(element);
		log.info("Switched to frame");
	}

}

