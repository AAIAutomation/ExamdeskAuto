package Selenium_with_maven.Automation.helper.Listner;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.Frame.FrameHelper;

public class Retry implements IRetryAnalyzer {
	
	private int retryCount=0;
	private int maxRetryCount=3;
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(IRetryAnalyzer.class);

	public boolean retry(ITestResult args0) {
		if (retryCount < maxRetryCount) {
			log.info("Retrying test "+args0.getName()+ " with status "  +getResultStatusName(args0.getStatus())+" for the " + (retryCount+1)+" times.");
			retryCount++;
			return true;
		}
		return false;
	}
	public String getResultStatusName(int status) {
		String	resultName=null;
		if(status==1) {
		    resultName="SUCCESS";
		}
		if(status==2) {
			resultName="FAILURE";
		}
		if(status==3) {
			resultName="RETRY";
		}
		return resultName;
	}	

}
