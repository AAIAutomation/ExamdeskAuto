package Selenium_with_maven.Automation.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.utils.FileUtil;
import com.google.common.io.Files;
import com.uiframework.proctur.eLearn.logger.LoggerHelper;
import com.uiframework.proctur.eLearn.logger.resource.ResourceHelper;

import Selenium_with_maven.Automation.helper.BrowserConfiguration.BrowserType;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.ChromeBrowser;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.FirefoxBrowser;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.PropertyReader;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.utils.ExtentManager;

public class TestBase {

	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public File reportDirectory;
	WaitHelper waithelper;

	@BeforeSuite

	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}

	@BeforeTest
	public void beforeTest() {
//		ConfigReader reader = new PropertyReader();
//		 reader is a interface and property reader is a class
//		OR
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("\\src\\main\\resources\\ScreenShots"));
		log.info("destination file");
		SetUpDriver(ObjectReader.reader.getBrowserType());
	}

	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getSimpleName());

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + "test started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagepath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagepath);
		
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + "is PASS");
			String imagepath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagepath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		extent.flush();
	}
	
	public WebDriver getBrowserObject(BrowserType bType) {

		try {
			switch (bType) {
			case Chrome:
				// This is Another way to create object of another class.
				ChromeBrowser chrome = new ChromeBrowser();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);

			case Firefox:
				FirefoxBrowser firefox = new FirefoxBrowser();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);

			default:
				throw new Exception("driver not fount " + bType.name());

			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	public void SetUpDriver(BrowserType bType) {
		log.info("bType is " + bType);
		driver = getBrowserObject(bType);
		log.info("browser object got ");	
//		log.info("Initilized web driver:" + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);

		wait.SetImplicitWait(ObjectReader.reader.getExpliciteWait(), TimeUnit.SECONDS);
		wait.PageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("test to check its coming inside setup driver maximize");
	}

	public String captureScreen(String fileName, WebDriver driver) {
		if (driver == null) {
			log.info("driver is null");
			return null;
		}
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			destFile = new File(reportDirectory + "/" + fileName + "_" + dateFormatter.format(calender.getTime()) + ".png");
			FileUtils.copyFile(screFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height = '100' width = '100'/></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
	public void getNevigationScreen(WebDriver driver) {
		log.info("capturing ui nevigation screen...");
		String screen = captureScreen("", driver);
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public static void extentLog(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}
	
//	public void MouseHoverToAnyWebElementAndClick(WebElement hover1, WebElement hover2) {
//		Actions actions = new Actions(driver);
//		actions.moveToElement(hover1).perform();
//		log.info("click on first hover");
//		waithelper.SetImplicitWait(50, TimeUnit.SECONDS);
//		actions.moveToElement(hover2).perform();
//		log.info("click on second hover");
//		actions.click();
//		log.info("move to core element and clicked.");
//	}
}