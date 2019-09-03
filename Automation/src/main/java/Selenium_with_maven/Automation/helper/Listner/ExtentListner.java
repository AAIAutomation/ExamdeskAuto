package Selenium_with_maven.Automation.helper.Listner;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.utils.ExtentManager;

public class ExtentListner implements ITestListener{
	private Logger log = LoggerHelper.getLogger(ExtentListner.class);
	

	public static ExtentReports extent;
	public static ExtentTest test;
	
	public void onTestStart(ITestResult result) {
	//	test.log(Status.INFO, result.getName()+"Started");
		Reporter.log(result.getMethod().getMethodName()+ " "+"Test Started...");
		log.info(result.getMethod().getMethodName()+ " "+"Test Started...");
	}

	public void onTestSuccess(ITestResult result) {
	//	test.log(Status.INFO, result.getName()+"pass");
		Reporter.log(result.getMethod().getMethodName()+ " "+"Test Passed...");
		log.info(result.getMethod().getMethodName()+ " "+"Test Passed...");
	}

	public void onTestFailure(ITestResult result) {
	//	test.log(Status.FAIL, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+ " "+"Test Failed..."+ result.getThrowable());
		log.info(result.getMethod().getMethodName()+ " "+"Test Failed..."+ result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		//test.log(Status.SKIP, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+ " "+"Test Skipped..."+ result.getThrowable());
		log.warn(result.getMethod().getMethodName()+ " "+"Test Skipped..."+ result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		extent = ExtentManager.getInstance();
		//test = extent.createTest(context.getName());
//		test = extent.createTest(context.getCurrentXmlTest().getName());
		Reporter.log(context.getName()+ " "+"Test has started...");
		log.info(context.getName()+ " "+"Test has started...");
		
	}

	public void onFinish(ITestContext context) {
		//extent.flush();
		Reporter.log(context.getName()+ " "+"Test Finished...");
		log.info(context.getName()+ " "+"Test Finished...");
	}

}
