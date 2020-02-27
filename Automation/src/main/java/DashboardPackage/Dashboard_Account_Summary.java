package DashboardPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeTest;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import LoginPackage.Login;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.helper.assertion.AssertHelper;
import Selenium_with_maven.Automation.helper.dropdown.DropdownHelper;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.javascript.Scrollonpage;
import selenium_with_mavan.Automation.common_methods.common_methods;

public class Dashboard_Account_Summary {

	private static WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Ongoing_Tests.class);

	// these are the global paths
	@FindBy(xpath = "//*[@href='https://test999.examdesk.co/administrator/dashboard' and @class ='logo' ]")
	WebElement homepage;

	// Dashboard xpaths for testcount
	@FindBy(xpath = "//h3[text() = 'Account Summary']")
	WebElement heading;
	@FindBy(xpath = "//p[text() = 'Test Limit']")
	WebElement testlimit;
	@FindBy(xpath = "//p[text() = 'Test Limit']//following::p[1]")
	WebElement testdata;

	// inside/expected paths testcount
	@FindBy(xpath = "//*[text()='Accounts']")
	WebElement superadminaccounts;
	@FindBy(xpath = "//*[@type='text']")
	WebElement superadminclickonsearch;
	@FindBy(xpath = "//*[@href='https://test999.examdesk.co/superadmin/account/editAccount/14']")
	WebElement superadmintestbedaccount;
	@FindBy(xpath = "//*[@name='max_test_limit']")
	WebElement superadminscrolltoemement;
	@FindBy(xpath = "//*[@href=\"https://test999.examdesk.co/administrator/manage/reports\"]")
	WebElement buttonstudent;
	@FindBy(xpath = "//div[contains(text(),'items')]")
	WebElement totaltests;
	@FindBy(xpath = "//select[@name = 'test_type_id']")
	WebElement testtype;
	@FindBy(xpath = "//button[text()= 'Search']")
	WebElement buttonsearch;

	// dashboard sms count

	@FindBy(xpath = "//*[contains(text(),'SMS Count')]")
	WebElement smscount;
	@FindBy(xpath = "//*[contains(text(),'SMS Count')]//following::p[1]")
	WebElement dashboardsmsdata;

	// inside/expected paths SMScount
	@FindBy(xpath = "//*[text() =' Test Examdesk ']")
	WebElement testexamdesk;
	@FindBy(xpath = "//a[@href ='https://test999.examdesk.co/administrator/account']")
	WebElement buttonaccount;
	@FindBy(xpath = "//p[text()='SMS']")
	WebElement buttonsms;
	@FindBy(xpath = "//*[text()='Total SMS Allocated']//parent::div/p[1]")
	WebElement totalSMSallocated;
	@FindBy(xpath = "//*[text()='Total SMS Used']//parent::div/p[1]")
	WebElement totalsmsused;

	// dashboard student count

	@FindBy(xpath = "//a[@href ='https://test999.examdesk.co/administrator/manage/students' and @class='info-line']//following::p[1]")
	WebElement dashboardstudent;

	// inside/expected path students count
	@FindBy(xpath = "//a[@href ='https://test999.examdesk.co/administrator/manage/students' and @class='info-line']")
	WebElement linkstudent;
	@FindBy(xpath = "//div[contains(text(),'items')]")
	WebElement studentcount;

	// dashboard pending evaluation
	@FindBy(xpath = "//*[contains(text(),'Pending Evaluations')]//following::p[1]/span")
	WebElement pendingevaluation;
	@FindBy(xpath = "//a[@href ='https://test999.examdesk.co/administrator/evaluation' and @class ='info-line']")
	WebElement linkpendingtestevaluation;

	// inside/expected pending test evaluation count
	@FindBy(xpath = "//a[text()='Evaluate']")
	List<WebElement> buttoncountevaluate;

	@BeforeTest
	public String getExpectedTotalTestLimitCount() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		driver.get("https://test999.examdesk.co");
		new Login(driver).loginToApp(ObjectReader.reader.getsuperadminusername(),
				ObjectReader.reader.getsuperadminpassword());
		superadminaccounts.click();
		superadminclickonsearch.sendKeys("test.proctur@gmail.com");
		superadmintestbedaccount.click();
		new Scrollonpage(driver).ScrollToElement(superadminscrolltoemement);
		String testlimit = superadminscrolltoemement.getAttribute("value");
		driver.close();
		return testlimit;
	}

	public int dashboardVerifyTestCount() throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(500, TimeUnit.SECONDS);
		String[] arrOfStr = testdata.getText().split("/");
		log.info("used tests count is " + arrOfStr[0]);
		log.info("total tests count is" + arrOfStr[1]);

		String total_tests = arrOfStr[0];
		String used_tests = arrOfStr[1];
		// total used tests count
		buttonstudent.click();
		new Scrollonpage(driver).ScrollToElement(totaltests);
		int totalmocktestreport = Integer.parseInt(totaltests.getText());
		new Scrollonpage(driver).ScrollToElement(testtype);
		Thread.sleep(1000);
		new DropdownHelper(driver).selectUsingValue(testtype, "2");
		buttonsearch.click();
		new Scrollonpage(driver).ScrollToElement(totaltests);
		int totalpracticetestreport = Integer.parseInt(totaltests.getText());
		new Scrollonpage(driver).ScrollToElement(testtype);
		Thread.sleep(1000);
		new DropdownHelper(driver).selectUsingValue(testtype, "4");
		buttonsearch.click();
		new Scrollonpage(driver).ScrollToElement(testtype);
		int totalonlinetestreport = Integer.parseInt(totaltests.getText());
		int totalusedtests = totalmocktestreport + totalpracticetestreport + totalonlinetestreport;
		log.info("Total used tests is" + totalusedtests);
		try {
			new AssertHelper();
			AssertHelper.VerifyNumber(Integer.parseInt(used_tests), totalusedtests);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("total count of used tests and actual tests is pass");
		String expectedtotaltests = getExpectedTotalTestLimitCount();
		new AssertHelper();
		AssertHelper.VerifyNumber(Integer.parseInt(total_tests), Integer.parseInt(expectedtotaltests));
		return 0;
	}

	public int verifySMSCount() {

		String[] dsashboardsms = smscount.getText().split("/");
		log.info("total consumed sms" + dsashboardsms[0]);
		log.info("total allocated sms" + dsashboardsms[1]);

		String usedsms = dsashboardsms[0];
		String allocatedsms = dsashboardsms[1];

		new common_methods().mousehover(testexamdesk, buttonaccount);
		new WaitHelper(driver).PageLoadTime(3000, TimeUnit.MILLISECONDS);
		buttonsms.click();
		String totalSMSallocateds = totalSMSallocated.getText();
		log.info("expected total SMS Allocated is" + totalSMSallocateds);
		String totalSMSused = totalsmsused.getText();
		log.info("Expected total SMS used is" + totalSMSused);
		AssertHelper.VerifyText(usedsms, totalSMSused);
		log.info("Both count of both used sms is correct" + usedsms + " = " + totalSMSused);
		AssertHelper.VerifyText(allocatedsms, totalSMSallocateds);
		log.info("Both count of allocated sms is same " + allocatedsms + " = " + totalSMSallocateds);
		return 0;
	}

	public int verifyStudentCount() {

		String dsashboardstudentcount = dashboardstudent.getText();
		log.info("total student count is on dashboard is" + dsashboardstudentcount);
		linkstudent.click();
		new Scrollonpage(driver).ScrollToElement(studentcount);
		String expectedtotalstudentcount = studentcount.getText();
		log.info("Expected student count is " + expectedtotalstudentcount);

		AssertHelper.VerifyText(dsashboardstudentcount, expectedtotalstudentcount);
		log.info("Both student count is equal " + dsashboardstudentcount + " = " + dsashboardstudentcount);
		return 0;
	}

	public void verifyPendingEvaluation() {

	}

}
