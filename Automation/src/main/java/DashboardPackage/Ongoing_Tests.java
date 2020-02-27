package DashboardPackage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import LoginPackage.Login;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.helper.alert.AlertHelper;
import Selenium_with_maven.Automation.helper.assertion.AssertHelper;
import Selenium_with_maven.Automation.helper.database.ApplicationDBquery;
import Selenium_with_maven.Automation.helper.dropdown.DropdownHelper;
import Selenium_with_maven.Automation.helper.pageObject.LoginPage;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.testbase.TestBase;
import selenium_with_mavan.Automation.common_methods.common_methods;

public class Ongoing_Tests {

	private static WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Ongoing_Tests.class);
	String Test_ID;
	int callthefunctionsandcomparecurrentlyattemptingcount;
	// ongoing test dashboard xpath
	@FindBy(xpath = "//*[text()='Attempting']")
	List<WebElement> totalongoingTestondashboard;
	@FindBy(xpath = "//*[contains(text(),'Ends At')]")
	List<WebElement> dashboarddates;
	@FindBy(xpath = "//p[@class='single-line-ellipsis m-0 item-test-title']")
	List<WebElement> dashboardtestname;
	@FindBy(xpath = "//*[contains(text(),'Ends At')]//following::p[1]")
	List<WebElement> dashboardattemptingcount;
	@FindBy(xpath = "//*[contains(text(),'Ends At')]//following::p[2]")
	List<WebElement> dashboardsubmittedcount;

	// unused test type
	@FindBy(xpath = "//*[@id=\"attribution\"]")
	WebElement unusedtesttype;
	// ongoing test insidepage xpath

	@FindBy(xpath = "//*[text()='Test ']")
	WebElement test;
	@FindBy(xpath = "//a[@href='https://test999.examdesk.co/administrator/manage/tests']")
	WebElement managetests;
	@FindBy(xpath = "//*[@name='status_id']")
	WebElement teststatus;
	@FindBy(xpath = "//*[@name='test_type_id']")
	WebElement testtype;
	@FindBy(xpath = "//*[text()='Search']")
	WebElement search;
	@FindBy(xpath = "//*[text()='Published' and @class='lead-text']")
	List<WebElement> publishedtests;
	@FindBy(xpath = "//a[text()='Dashboard']")
	WebElement homepage;
	@FindBy(xpath = "//*[text()='No Tests Found']")
	WebElement notestfound;
	@FindBy(xpath = "//div[contains(text(),'items')]")
	WebElement totaltests;

	// validate date

	@FindBy(xpath = "//p[@class='lead-text']//following::p[1]/span[2]")
	List<WebElement> dateinsidepage;

	// Verify currently attempting count getting from testbed.
	// Expected count need to check from db and actual count wil check from
	// dashboard for a perticular test.
	// For this create one function which will take test_id and give the currently
	// attempting count.
	// use above dashboard attempting count for the dashboard each test.
	// get the testid's then pass it to database package then will get the values

	@FindBy(xpath = "//*[contains(text(),'Ends At')]//following::p[2]")
	List<WebElement> dashboardsubmittedstudentcount;
	@FindBy(xpath = "//div[@class='col-md-3 col-sm-3 col-xs-3 p-0 m-auto']/p")
	List<WebElement> insidetestid;
	@FindBy(xpath = "//button[@class='MS-right right-arrow']")
	WebElement buttonrightclick;

//	@FindBy(xpath = "//*[@style ='margin-left: -52%;']")
//	WebElement lastongoingtest;

	// Deactivate test testing
	@FindBy(xpath = "//button[text() ='More']")
	WebElement deactivatefirsttest;
	@FindBy(xpath = "//a[text() ='Deactivate']")
	WebElement buttondeactivate;

	public Ongoing_Tests(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		WaitHelper wait = new WaitHelper(driver);
//		wait.waitForElement(element, timeoutInSeconds);
//		new TestBase().getNevigationScreen(driver);
//		TestBase.test.log(Status.INFO, "login object has created");
//		TestBase.extentLog("online_tests object has created");
	}

	@BeforeTest
	public void loginPage() {
		try {
			new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int dashboardOngoingTestCount() {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		int ongoigTests = totalongoingTestondashboard.size();
		log.info("Total ongoing test avaliable on Dashboard" + ongoigTests);
		gotoTestPage();
		new DropdownHelper(driver).selectUsingValue(teststatus, "1");
		search.click();
		int noofpublishedtest = publishedtests.size();
		new DropdownHelper(driver).selectUsingValue(testtype, "4");
		search.click();
		int noofpublishedtest1 = publishedtests.size();
		int totalpublishedtest = noofpublishedtest + noofpublishedtest1;
		log.info("total published test we have is   " + totalpublishedtest);
		if (ongoigTests == totalpublishedtest) {
			log.info("ongoing test count is correct" + ongoigTests + "is equal  " + totalpublishedtest);
			return 0;
		}
		return 1;
	}

	public void gotoTestPage() {
		new WaitHelper(driver).SetImplicitWait(500, TimeUnit.SECONDS);
		Actions mouse = new Actions(driver);
		mouse.moveToElement(test).perform();
		managetests.click();
	}

	public String getExpectedTestName(String Test_ID) {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		String testname = null;
		ApplicationDBquery applicationquery = new ApplicationDBquery();
		try {
			testname = applicationquery.testNameForTestID(Integer.parseInt(Test_ID));
			log.info("test name for the" + "testid is" + Test_ID + "  = " + testname);
//			count.add(testname);
//			homepage.click();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testname;
	}

	public int getExpectedCurrentlyAttemptingCount(String Test_ID, int CurrentlyAttempting) {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		if (CurrentlyAttempting == 1) {
			int currentlyAttempted = 0;
			ApplicationDBquery applicationquery = new ApplicationDBquery();
			try {
				currentlyAttempted = applicationquery.test_id_from_tab_ed_test(Integer.parseInt(Test_ID));
				log.info("CurrentlyAttemptedCountfor" + "testid is" + Test_ID + "  = " + currentlyAttempted);
//			count.add(currentlyAttempted);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return currentlyAttempted;
		} else if (CurrentlyAttempting == 0) {
			int submitted = 0;
			ApplicationDBquery applicationquery = new ApplicationDBquery();
			try {
				submitted = applicationquery.testSubmittedCount(Integer.parseInt(Test_ID));
				log.info("test Submitted Count for" + "testid is" + Test_ID + "  = " + submitted);
//				count.add(currentlyAttempted);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return submitted;
		}
		return 111111111;
	}

	public int getDashboardTestIndex(String Test_ID) throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		String expectedtestname = getExpectedTestName(Test_ID);
		log.info("dashboardtestname.size()" + dashboardtestname.size());
		for (int i1 = 0; i1 < dashboardtestname.size(); i1++) {
			if (i1 <= 3) {
				String actualtestnames = dashboardtestname.get(i1).getText();
				log.info(actualtestnames);
				if (actualtestnames.equals(expectedtestname))
					return i1;
			} else {
				buttonrightclick.click();
				Thread.sleep(100);
				String actualtestnames = dashboardtestname.get(i1).getText();
				log.info(actualtestnames);
				if (actualtestnames.equals(expectedtestname))
					return i1;
			}
		}
		return 11111111;
	}

	public void gotoTestsPageAndClick(WebElement teststatus, String value1, WebElement testtype, String value2,
			int mocktest) throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		if (mocktest == 1 & testtype == unusedtesttype) {
			gotoTestPage();
			new DropdownHelper(driver).selectUsingValue(teststatus, value1);
			Thread.sleep(100);
			search.click();
		} else if (mocktest == 0) {
			gotoTestPage();
			new DropdownHelper(driver).selectUsingValue(testtype, value2);
			Thread.sleep(100);
			new DropdownHelper(driver).selectUsingValue(teststatus, value1);
			Thread.sleep(100);
			search.click();
		} else {
			log.info("you have provided wrong value please check it");
		}
	}

	public void currentlyAttemptedCountMockTest() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
		Thread.sleep(500);
		int total_mock_tests;
		String onlinetestcount = totaltests.getText();
		log.info(onlinetestcount);
		if (onlinetestcount.equals("0 of 0 items")) {
			log.info("Hey user, No online test is avaliable please wait some time");
//				throw new RuntimeException("This is thrown intentionally");
//			total_mock_tests = insidetestid.size();
//			log.info("Total Online test is " + total_mock_tests);
//			if (total_mock_tests == 0) {
////				

		} else {
			for (int i = 0; i < insidetestid.size(); i++) {
				String Test_ID = insidetestid.get(i).getText();
				log.info(Test_ID);
				int currentlyAttemptcount = getExpectedCurrentlyAttemptingCount(Test_ID, 1);
				String expectedtestname = getExpectedTestName(Test_ID);
				log.info("Expected testnajme is " + expectedtestname);
				Thread.sleep(100);
				homepage.click();
				Thread.sleep(100);
				int testIndex = getDashboardTestIndex(Test_ID);
				String actualtestnames = dashboardtestname.get(testIndex).getText();
				log.info("Actual test name on dashboard is " + actualtestnames);
				String actualattemptingcount = dashboardattemptingcount.get(testIndex).getText();
				log.info("Actual Attempting count is " + actualattemptingcount);
				String expectedattemptingcount = String.valueOf(currentlyAttemptcount);
				log.info("Excepted Attempting count is " + expectedattemptingcount);
				if (expectedtestname.equals(actualtestnames)) {
					log.info("came inside the verify text");
					AssertHelper.VerifyText(actualattemptingcount, expectedattemptingcount);
					log.info("pass");
					gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
					Thread.sleep(1000);
				}
			}
		}
	}

	public void currentlyAttemptedOnlineExam() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		gotoTestsPageAndClick(teststatus, "2", testtype, "4", 0);
		Thread.sleep(500);
		int total_mock_tests;
		String onlinetestcount = totaltests.getText();
		log.info(onlinetestcount);
		if (onlinetestcount.equals("0 of 0 items")) {
			log.info("Hey user, No online test is avaliable please wait some time");
//				throw new RuntimeException("This is thrown intentionally");
//			total_mock_tests = insidetestid.size();
//			log.info("Total Online test is " + total_mock_tests);
//			if (total_mock_tests == 0) {
////				

		} else {
			for (int i = 0; i < insidetestid.size(); i++) {
				String Test_ID = insidetestid.get(i).getText();
				log.info(Test_ID);
				int currentlyAttemptcount = getExpectedCurrentlyAttemptingCount(Test_ID, 1);
				String expectedtestname = getExpectedTestName(Test_ID);
				log.info("Expected testnajme is " + expectedtestname);
				Thread.sleep(100);
				homepage.click();
				Thread.sleep(100);
				int testIndex = getDashboardTestIndex(Test_ID);
				String actualtestnames = dashboardtestname.get(testIndex).getText();
				log.info("Actual test name on dashboard is " + actualtestnames);
				String actualattemptingcount = dashboardattemptingcount.get(testIndex).getText();
				log.info("Actual Attempting count is " + actualattemptingcount);
				String expectedattemptingcount = String.valueOf(currentlyAttemptcount);
				log.info("Excepted Attempting count is " + expectedattemptingcount);
				if (expectedtestname.equals(actualtestnames)) {
					log.info("came inside the verify text");
					AssertHelper.VerifyText(actualattemptingcount, expectedattemptingcount);
					log.info("pass");
					gotoTestsPageAndClick(teststatus, "2", testtype, "4", 0);
					Thread.sleep(1000);
				}
			}
		}
	}

	public void testSubmittedCountMockTest() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
		Thread.sleep(500);
		int total_mock_tests;
		String onlinetestcount = totaltests.getText();
		log.info(onlinetestcount);
		if (onlinetestcount.equals("0 of 0 items")) {
			log.info("Hey user, No online test is avaliable please wait some time");
//				throw new RuntimeException("This is thrown intentionally");
//			total_mock_tests = insidetestid.size();
//			log.info("Total Online test is " + total_mock_tests);
//			if (total_mock_tests == 0) {
////				

		} else {
			for (int i = 0; i < insidetestid.size(); i++) {
				String Test_ID = insidetestid.get(i).getText();
				log.info(Test_ID);
				int testsubmittedcount = getExpectedCurrentlyAttemptingCount(Test_ID, 0);
				String expectedtestname = getExpectedTestName(Test_ID);
				log.info("Expected testname is " + expectedtestname);
				Thread.sleep(100);
				homepage.click();
				Thread.sleep(100);
				int testIndex = getDashboardTestIndex(Test_ID);
				String actualtestnames = dashboardtestname.get(testIndex).getText();
				log.info("Actual test name on dashboard is " + actualtestnames);
				String actualsubmittedcount = dashboardsubmittedcount.get(testIndex).getText();
				log.info("Actual Submitted count is " + actualsubmittedcount);
				String expectedsubmittedcount = String.valueOf(testsubmittedcount);
				log.info("Excepted Submitted count is " + expectedsubmittedcount);
				if (expectedtestname.equals(actualtestnames)) {
					log.info("came inside the verify text");
					AssertHelper.VerifyText(actualsubmittedcount, expectedsubmittedcount);
					log.info("pass");
					gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
					Thread.sleep(1000);
				}
			}
		}
	}

	public void testSubmittedCountOnlineExam() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		gotoTestsPageAndClick(teststatus, "2", testtype, "4", 0);
		Thread.sleep(500);
		int total_mock_tests;
		String onlinetestcount = totaltests.getText();
		log.info(onlinetestcount);
		if (onlinetestcount.equals("0 of 0 items")) {
			log.info("Hey user, No online test is avaliable please wait some time");
//				throw new RuntimeException("This is thrown intentionally");
//			total_mock_tests = insidetestid.size();
//			log.info("Total Online test is " + total_mock_tests);
//			if (total_mock_tests == 0) {
////				

		} else {
			for (int i = 0; i < insidetestid.size(); i++) {
				String Test_ID = insidetestid.get(i).getText();
				log.info(Test_ID);
				int testsubmittedcount = getExpectedCurrentlyAttemptingCount(Test_ID, 0);
				String expectedtestname = getExpectedTestName(Test_ID);
				log.info("Expected testname is " + expectedtestname);
				Thread.sleep(100);
				homepage.click();
				Thread.sleep(100);
				int testIndex = getDashboardTestIndex(Test_ID);
				String actualtestnames = dashboardtestname.get(testIndex).getText();
				log.info("Actual test name on dashboard is " + actualtestnames);
				String actualsubmittedcount = dashboardsubmittedcount.get(testIndex).getText();
				log.info("Actual Submitted count is " + actualsubmittedcount);
				String expectedsubmittedcount = String.valueOf(testsubmittedcount);
				log.info("Excepted Submitted count is " + expectedsubmittedcount);
				if (expectedtestname.equals(actualtestnames)) {
					log.info("came inside the verify text");
					AssertHelper.VerifyText(actualsubmittedcount, expectedsubmittedcount);
					log.info("pass");
					gotoTestsPageAndClick(teststatus, "2", testtype, "4", 0);
					Thread.sleep(1000);
				}
			}
		}
	}

	public void testDateCountMockTest() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
		Thread.sleep(500);
		int total_mock_tests;
		String onlinetestcount = totaltests.getText();
		log.info(onlinetestcount);
		if (onlinetestcount.equals("0 of 0 items")) {
			log.info("Hey user, No online test is avaliable please wait some time");
//				throw new RuntimeException("This is thrown intentionally");
//			total_mock_tests = insidetestid.size();
//			log.info("Total Online test is " + total_mock_tests);
//			if (total_mock_tests == 0) {
////				

		} else {
			for (int i = 0; i < insidetestid.size(); i++) {
				String Test_ID = insidetestid.get(i).getText();
				log.info(Test_ID);
//			int currentlyAttemptcount = getExpectedCurrentlyAttemptingCount(Test_ID, 1);
				String expectedtestname = getExpectedTestName(Test_ID);
				log.info("Expected testnajme is " + expectedtestname);
				Thread.sleep(100);
				String expectedtestdate = dateinsidepage.get(i).getText();
				log.info("Expected testdate is " + expectedtestdate);
				Thread.sleep(100);
				homepage.click();
				Thread.sleep(1000);
				int testIndex = getDashboardTestIndex(Test_ID);
				String actualtestnames = dashboardtestname.get(testIndex).getText();
				log.info("Actual test name on dashboard is " + actualtestnames);
				String actualtestdate = dashboarddates.get(testIndex).getText();
				log.info("Actual Test Date is " + actualtestdate);
				String actual_date = actualtestdate.toString().substring(8, 11);
				log.info("Trim Month is" + actual_date);
				String month = new common_methods().switchmethod(actual_date);
				log.info("Month formate is " + month);
				String year = actualtestdate.toString().substring(16, 20);
				log.info("Trim year is " + year);
				String date = actualtestdate.toString().substring(12, 14);
				log.info("Trim date is " + date);
				String finalconverteddate = year + month + date;
//			String expectedtestdate = String.valueOf(currentlyAttemptcount);
				log.info("Excepted Test Date is " + finalconverteddate);
				if (expectedtestname.equals(actualtestnames)) {
					log.info("came inside the verify text");
					AssertHelper.VerifyText(finalconverteddate, expectedtestdate);
					log.info("pass");
					gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
					Thread.sleep(1000);
				}
			}
		}
	}

	public void testDateCountOnlineTest() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		gotoTestsPageAndClick(teststatus, "2", testtype, "4", 0);
		Thread.sleep(500);
		int total_mock_tests;
		String onlinetestcount = totaltests.getText();
		log.info(onlinetestcount);
		if (onlinetestcount.equals("0 of 0 items")) {
			log.info("Hey user, No online test is avaliable please wait some time");
//				throw new RuntimeException("This is thrown intentionally");
//			total_mock_tests = insidetestid.size();
//			log.info("Total Online test is " + total_mock_tests);
//			if (total_mock_tests == 0) {
////				

		} else {
			for (int i = 0; i < insidetestid.size(); i++) {
				String Test_ID = insidetestid.get(i).getText();
				log.info(Test_ID);
//			int currentlyAttemptcount = getExpectedCurrentlyAttemptingCount(Test_ID, 1);
				String expectedtestname = getExpectedTestName(Test_ID);
				log.info("Expected testnajme is " + expectedtestname);
				Thread.sleep(100);
				String expectedtestdate = dateinsidepage.get(i).getText();
				log.info("Expected testdate is " + expectedtestdate);
				Thread.sleep(100);
				homepage.click();
				Thread.sleep(1000);
				int testIndex = getDashboardTestIndex(Test_ID);
				String actualtestnames = dashboardtestname.get(testIndex).getText();
				log.info("Actual test name on dashboard is " + actualtestnames);
				String actualtestdate = dashboarddates.get(testIndex).getText();
				log.info("Actual Test Date is " + actualtestdate);
				String actual_date = actualtestdate.toString().substring(8, 11);
				log.info("Trim Month is" + actual_date);
				String month = new common_methods().switchmethod(actual_date);
				log.info("Month formate is " + month);
				String year = actualtestdate.toString().substring(16, 20);
				log.info("Trim year is " + year);
				String date = actualtestdate.toString().substring(12, 14);
				log.info("Trim date is " + date);
				String finalconverteddate = year + month + date;
//			String expectedtestdate = String.valueOf(currentlyAttemptcount);
				log.info("Excepted Test Date is " + finalconverteddate);
				if (expectedtestname.equals(actualtestnames)) {
					log.info("came inside the verify text");
					AssertHelper.VerifyText(finalconverteddate, expectedtestdate);
					log.info("pass");
					gotoTestsPageAndClick(teststatus, "2", testtype, "4", 0);
					Thread.sleep(1000);
				}
			}
		}
	}

	public void inactiveTest() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
		gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
		Thread.sleep(500);
		int total_mock_tests;
		String mocktestcount = totaltests.getText();
		log.info(mocktestcount);
		if (mocktestcount.equals("0 of 0 items")) {
			log.info("Hey user, No online test is avaliable please wait some time");
//				throw new RuntimeException("This is thrown intentionally");
//			total_mock_tests = insidetestid.size();
//			log.info("Total Online test is " + total_mock_tests);
//			if (total_mock_tests == 0) {
////				

		} else {
			for (int i = 0; i < insidetestid.size(); i++) {
				String Test_ID = insidetestid.get(i).getText();
				log.info(Test_ID);
//				int testsubmittedcount = getExpectedCurrentlyAttemptingCount(Test_ID, 0);
				String expectedtestname = getExpectedTestName(Test_ID);
				log.info("Expected testname is " + expectedtestname);
				Thread.sleep(100);
				deactivatefirsttest.click();
				Actions mouse = new Actions(driver);
				mouse.moveToElement(buttondeactivate).perform();
				Thread.sleep(1000);
				buttondeactivate.click();
				new AlertHelper(driver).acceptAlert();
				Thread.sleep(1000);
				homepage.click();
				Thread.sleep(100);
				try {
					int testIndex = getDashboardTestIndex(Test_ID);
					log.info(testIndex);
					if (testIndex == 11111111) {
						log.info("passesss");
					}
				} catch (Exception e) {
					log.info("Deactivate is passes");
				}

//				int testIndex = getDashboardTestIndex(Test_ID);
//				log.info(testIndex);
//				if(testIndex == 11111111) {
//					log.info("passesss");
//				}
//				else
////					log.info("Fail");
//				String actualtestnames = dashboardtestname.get(testIndex).getText();
//				log.info("Actual test name on dashboard is " + actualtestnames);
//				if(actualtestnames.isEmpty()) {
//				log.info("passesss");
//				String actualsubmittedcount = dashboardsubmittedcount.get(testIndex).getText();
//				log.info("Actual Submitted count is " + actualsubmittedcount);
//				String expectedsubmittedcount = String.valueOf(testsubmittedcount);
//				log.info("Excepted Submitted count is " + expectedsubmittedcount);
//				if (expectedtestname.equals(actualtestnames)) {
//					log.info("came inside the verify text");
//					AssertHelper.VerifyText(actualsubmittedcount, expectedsubmittedcount);
//					log.info("pass");
//					break;
////					gotoTestsPageAndClick(teststatus, "2", unusedtesttype, "4", 1);
////					Thread.sleep(1000);
			}
		}
	}

//	public String actualTestName() {
////		for (int i1 = 0; i1 < dashboardtestname.size(); i1++) {
//			String actualtestnames = dashboardtestname.get(i1).getText();
////			int j = i1 +1;
////			if(j % 4 == 0 ) {
////				
////			}
////			
//			return actualtestnames;
//		}return null;

//	}

//	public int currentlyAttemptedCountMockTest() throws InterruptedException {
//		int currentlyAttempted = 0;
//		String testname = null;
//		new WaitHelper(driver).SetImplicitWait(100, TimeUnit.SECONDS);
////		new Click_on_all_the_links(driver).LogintoApp();
////		homepage.click();
//		gotoTestPage();
////		ArrayList<Object> count = new ArrayList<Object>();
//		new DropdownHelper(driver).selectUsingValue(teststatus, "2");
//		search.click();
//		Thread.sleep(5000);
//		int total_mock_tests = insidetestid.size() - 1;
//		log.info("Total mock test is " + total_mock_tests);
//		for (int i = 0; i <= insidetestid.size(); i++) {
//			String Test_ID = insidetestid.get(i).getText();
////			String Test_ID = test_id.toString();
////			test_ids.add(Test_ID);
//			log.info(Test_ID);
//			ApplicationDBquery applicationquery = new ApplicationDBquery();
//			try {
//				currentlyAttempted = applicationquery.test_id_from_tab_ed_test(Integer.parseInt(Test_ID));
//				log.info("CurrentlyAttemptedCountfor" + "testid is" + Test_ID + "  = " + currentlyAttempted);
////				count.add(currentlyAttempted);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				testname = applicationquery.testNameForTestID(Integer.parseInt(Test_ID));
//				log.info("test name for ther" + "testid is" + Test_ID + "  = " + testname);
////				count.add(testname);
//				homepage.click();
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		
//			for (int i1 = 0; i1 < dashboardtestname.size(); i1++) {
//				if (i1 <= 3) {
//					String actualtestnames = dashboardtestname.get(i1).getText();
//					log.info(actualtestnames);
//					if(actualtestnames.equals("testname"))
//						return i1;
//				} else {
//
//					buttonrightclick.click();
//					String actualtestnames = dashboardtestname.get(i1).getText();
//					log.info(actualtestnames);
//					if(actualtestnames.equals("testname"))
//						return i1;
//				}
//			   if()
//			String actualattemptingcount = dashboardattemptingcount.get(indexoftestname).getText();
////			String actualattemptingcount = actualattemptingcount1.toString();
//			String expectedattemptingcount = String.valueOf(currentlyAttempted);
//			log.info("Excepted Attempting count is " + expectedattemptingcount);
//			if (testname == actualtestnames) {
//				log.info("came inside the verify text");
//				AssertHelper.VerifyText(actualattemptingcount1, expectedattemptingcount);
//				log.info("pass");
//			}
//			log.info("fail");
////		return count;
//				// this will my seperate method
//
////				log.info(text);
////				if(text.equals(testname)) {
////					System.out.println("index " +i1);
////				}
//
//			}
//		}
//	}
//				int j = i1+1;
//				log.info(dashboardtestname.size());
//				String actualtestnames = dashboardtestname.get(i1).getText();
//				if (j % 4 == 0) {
////					for (int k = 0; k <=3; k++) {
////					if(wrong.isEnabled())
//							buttonrightclick.click();
//							String actualtestnames = dashboardtestname.get(i1).getText();
////					}
////				}
//				
////				log.info("Actual test name is " + actualtestnames);
//				String actualtestnames = dashboardtestname.get(indexoftestname).toString();
//				log.info("Actual test name is "+ dashboardtestname.get(indexoftestname));
////				String actualtestnames = actualtestname.toString();
//				String actualattemptingcount1 = dashboardattemptingcount.get(indexoftestname).getText();
////				String actualattemptingcount = actualattemptingcount1.toString();
//				String expectedattemptingcount = String.valueOf(currentlyAttempted);
//				log.info("Excepted Attempting count is " + expectedattemptingcount);
//				if (testname == actualtestnames) {
//					log.info("came inside the verify text");
//					AssertHelper.VerifyText(actualattemptingcount1, expectedattemptingcount);
//					log.info("pass");
//				}
//				log.info("fail");
////			return count;
//			}
//		}

//	public void currentlyAttemptedCountOnlineTest() throws InterruptedException {
//		int currentlyAttempted = 0;
//		String testname = null;
//		homepage.click();
//		gotoTestPage();
////		ArrayList<Object> count = new ArrayList<Object>();
//		new DropdownHelper(driver).selectUsingValue(teststatus, "4");
//		Thread.sleep(2000);
//		new DropdownHelper(driver).selectUsingValue(testtype, "2");
//		Thread.sleep(2000);
//		search.click();
//		for (int i = 0; i <= insidetestid.size(); i++) {
//			WebElement test_id = insidetestid.get(i);
//			String Test_ID = test_id.toString();
////			test_ids.add(Test_ID);
//			log.info(Test_ID);
//			ApplicationDBquery applicationquery = new ApplicationDBquery();
//			try {
//				currentlyAttempted = applicationquery.test_id_from_tab_ed_test(Integer.parseInt(Test_ID));
//				log.info("CurrentlyAttemptedCountfor" + "testid is" + Test_ID + "  = " + currentlyAttempted);
////				count.add(currentlyAttempted);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				testname = applicationquery.testNameForTestID(Integer.parseInt(Test_ID));
//				log.info("test name for ther" + "testid is" + Test_ID + "  = " + testname);
////				count.add(testname);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//			bb: for (int i1 = 0; i1 <= dashboardtestname.size(); i1++) {
//				WebElement actualtestname = dashboardtestname.get(i1);
//				String actualtestnames = actualtestname.toString();
//				WebElement actualattemptingcount1 = dashboardattemptingcount.get(i1);
//				String actualattemptingcount = actualattemptingcount1.toString();
//				String expectedattemptingcount = String.valueOf(currentlyAttempted);
//				if (testname == actualtestnames) {
//					AssertHelper.VerifyText(actualattemptingcount, expectedattemptingcount);
//					log.info("pass");
//				}
//				log.info("fail");
//				break bb;
//			}
////			return count;
//		}
//
//	}

//	public void testSubmitedCountMockTest() {
//		int currentlyAttempted = 0;
//		String testname = null;
//		homepage.click();
//		gotoTestPage();
////		ArrayList<Object> count = new ArrayList<Object>();
//		new DropdownHelper(driver).selectUsingValue(testtype, "4");
//		search.click();
//		for (int i = 0; i <= insidetestid.size(); i++) {
//			WebElement test_id = insidetestid.get(i);
//			String Test_ID = test_id.toString();
////			test_ids.add(Test_ID);
//			log.info(Test_ID);
//			ApplicationDBquery applicationquery = new ApplicationDBquery();
//			try {
//				currentlyAttempted = applicationquery.testSubmittedCount((Integer.parseInt(Test_ID)));
//				log.info("CurrentlyAttemptedCountfor" + "testid is" + Test_ID + "  = " + currentlyAttempted);
////				count.add(currentlyAttempted);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				testname = applicationquery.testNameForTestID(Integer.parseInt(Test_ID));
//				log.info("test name for ther" + "testid is" + Test_ID + "  = " + testname);
////				count.add(testname);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//			bb: for (int i1 = 0; i1 <= dashboardtestname.size(); i1++) {
//				WebElement actualtestname = dashboardtestname.get(i1);
//				String actualtestnames = actualtestname.toString();
//				WebElement actualattemptingcount1 = dashboardattemptingcount.get(i1);
//				String actualattemptingcount = actualattemptingcount1.toString();
//				String expectedattemptingcount = String.valueOf(currentlyAttempted);
//				if (testname == actualtestnames) {
//					AssertHelper.VerifyText(actualattemptingcount, expectedattemptingcount);
//					log.info("pass");
//				}
//				log.info("fail");
//				break bb;
//			}
////			return count;
//		}
//
//	}
}
