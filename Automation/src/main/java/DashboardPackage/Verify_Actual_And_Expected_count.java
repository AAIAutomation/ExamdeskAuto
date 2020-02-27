package DashboardPackage;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;
import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.assertion.AssertHelper;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;

public class Verify_Actual_And_Expected_count {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Click_on_all_the_links.class);
	WaitHelper waithelper;

	@FindBy(xpath = "//a[@href='administrator/evaluation']")
	WebElement pendingevaluation;
	@FindBy(xpath = "//a[@href='administrator/result']")
	WebElement pendingresults;
	@FindBy(xpath = "//a[text()='Dashboard']")
	WebElement homepage;
	
	@FindBy(xpath="//*[text()='Pending Evaluations']//following::p[1]")
	WebElement pendingevalondashboard;
	@FindBy(xpath ="//*[text()='Pending Evaluations']//following::p[3]")
	WebElement attemptfromno_oftest;
	@FindBy(xpath="//*[text()='Pending Results']//following::p[1]")
	WebElement pendingresultondashboard;
	@FindBy(xpath="//*[text()='Pending Results']//following::p[3]")
	WebElement resultfromno_oftest;
	@FindBy(xpath="//*[text()='Accounts']")
	WebElement superadminaccounts;
	@FindBy(xpath="//*[@type='text']")
	WebElement superadminsearchbox;
	@FindBy(xpath="//*[@href='https://test999.examdesk.co/superadmin/account/editAccount/14']")
	WebElement superadminmanageaccount;
	@FindBy(xpath="//*[@name='max_test_limit']")
	WebElement superadminmaxtestlimit;
	@FindBy(xpath ="//*[@id=\\\"bs-example-navbar-collapse-1\\\"]/ul/li[3]/a/text()")
	WebElement test;
	@FindBy(xpath ="//div[@class='col-md-6 col-sm-6']//following::div[1]/text()")
	WebElement Report_Appeared;
	// after each of 50 iteration of this xpath click on load more 
	//or upto LoadMore button not disabled click on it and the start counting attempts of each exam.
	@FindBy(xpath="//*[text()='Remaining Tests']//following::span[1]")
	WebElement remeaningtext;
//	use substring to get the string.
//	String s="hello";  
//	System.out.println(s.substring(0,2));//he  
	@FindBy(xpath="//*[text()='Remaining SMS']//following::span[1]")
	WebElement remeaningsms;
	@FindBy(xpath="//*[text()='Guest ']/span//following::span[4]")
	WebElement totalstudentstats;
	@FindBy(xpath="//*[text()='Enrolled ']/span[1]")
	WebElement totalstudentenrolled;
	@FindBy(xpath="//*[text()='Guest ']/span[1]/")
	WebElement gueststudentstat;
	@FindBy(xpath="//*[text()='Open ']/span[1]")
	WebElement openpackages;
	@FindBy(xpath="//*[text()='Package Publish Stats']//following::span[2]")
	WebElement openpublishedpackage;
	@FindBy(xpath="//*[text()='Package Publish Stats']//following::span[4]")
	WebElement publishedstats;
	@FindBy(xpath="//*[text()='Package Publish Stats']//following::span[6]")
	WebElement totalpackages;
	
	
//	SMS Balance inside total and remeaning equals check
	
	
	@FindBy(xpath="//*[contains(text(),'Test Examdesk')]")
	WebElement gototestexamdesk;
	@FindBy(xpath="//a[@href='https://test999.examdesk.co/administrator/account']")
	WebElement gotoaccountandclick;
	@FindBy(xpath="//h2[text()='SMS Balance']//following::div[3]/p[1]")
	WebElement totalsmsallocated;
	@FindBy(xpath="//h2[text()='SMS Balance']//following::div[5]/p[1]")
	WebElement remeaningsmsbalance;
	
	
//	student list page equals to dashboard
	@FindBy(xpath="//*[contains(text(),'GUEST')]")
	WebElement gueststudent;
	@FindBy(xpath="//*[contains(text(),'ED')]")
	WebElement enrolledstudent;
	@FindBy(xpath="//*[text()='Students']")
	WebElement clickonstudent;
//	total of this two simply total student
	
//	inside Manage Packages page 
	@FindBy(xpath="//a[text()='Open']")
	WebElement insideopenpackages;
	@FindBy(xpath="//span[text()='Expired']")
	WebElement insideexpiredpakages;
	@FindBy(xpath="//span[text()='Published']")
	WebElement insidepublishedpackage;
	@FindBy(xpath="//div[contains(text(),' items')]")
	WebElement insidetotalpackages;
	@FindBy(xpath="//*[text()='Package']")
	WebElement clickonpackages;
	
	
	//inside pending evaluation page
	@FindBy(xpath="//*[text()='Evaluate']")
	List<WebElement> totaltesteval;
	
	
	
	public void Pending_Evaluations() {
		
		String actualpendingeval = pendingevalondashboard.getText();
		log.info("Actual Pending Eval is"+ actualpendingeval);
		
		pendingevaluation.click();
		int expectedtotalpendingtest = totaltesteval.size();
		String totalpendingest = String.valueOf(expectedtotalpendingtest);
		
		int i = AssertHelper.VerifyText(actualpendingeval, totalpendingest);
		log.info(i);
		
	}
}
