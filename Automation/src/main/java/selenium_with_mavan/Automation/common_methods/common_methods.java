package selenium_with_mavan.Automation.common_methods;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import DashboardPackage.Click_on_all_the_links;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;

public class common_methods {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Click_on_all_the_links.class);

	public void mousehover(WebElement we, WebElement willclickonit) {
		Actions mouse = new Actions(driver);
		mouse.moveToElement(we).perform();
		new WaitHelper(driver).WaitForElementClickable(we, 100, 100);
		willclickonit.click();
	}

	public String switchmethod(String months) {

		switch (months) {
		case "Jan":

			return "-01-";
		case "Feb":

			return "-02-";
		case "Mar":

			return "-03-";
		case "Apr":

			return "-04";
		case "May":

			return "-05-";
		case "Jun":

			return "-06-";
		case "Jul":

			return "-07-";
		case "Aug":

			return "-08-";
		case "Sep":

			return "-09-";
		case "Oct":

			return "-10-";
		case "Nov":

			return "-11-";
		case "Dec":

			return "-12-";
		default:

			log.info("months not avaliable for this number");
		}
		return "11111111";
	}
}
