package Selenium_with_maven.Automation.pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.helper.assertion.VerificationHelper;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.testbase.TestBase;

public class VerifyStudentStats {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	WaitHelper waithelper;
	public int ActualCount ;
	
	@FindBy(xpath="//*[contains(@role,'alert' )]//following::tr")
	List<WebElement> ExceptedTotalStudentCount;	
//	@FindBy(xpath="//*[contains(text(),'Total Student' )and //text()='9']//following::span[1]")
//	WebElement ActualTotalStudentCount;
	@FindBy(xpath="//*[@href='https://test999.examdesk.co/administrator/student']")
	WebElement ClickOnStudentButton;
	@FindBy(xpath="//*[@href='https://test999.examdesk.co/administrator/package/packageList']")
	WebElement ClickOnPackageButton;
	@FindBy(xpath="//*[@id=\"data_table\"]/tbody//following::tr[1]")
	WebElement ExceptedPackageCount;
	@FindBy(xpath = "//*[@alt='Test Series']")
	WebElement HomePage;
//	@FindBy(xpath="//*[text()='Total Packages' and //text()='69']//following::span[1]")
	
	
	
	
	public VerifyStudentStats(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		WaitHelper wait = new WaitHelper(driver);
//		wait.waitForElement(element, timeoutInSeconds);
		new TestBase().getNevigationScreen(driver);
//		TestBase.test.log(Status.INFO, "login object has created");
		TestBase.extentLog("login object has created");
	}
	
	public void VerifyTotalStudentCount(String actualcount) {
		
		
		new LoginPage(driver).loginToApplication(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		ClickOnStudentButton.click();
		log.info("Total Excepted Student Count is:"+ExceptedTotalStudentCount.size());
		int Exceptedcount = ExceptedTotalStudentCount.size()+1;
		try {
			HomePage.click();
		if(driver.findElement(By.xpath("//*[contains(text(),'Total Student' )and //text()='"+actualcount+"']//following::span[1]")) != null) {
			 ActualCount = Integer.parseInt(actualcount);
		}
		else {
			log.info("Please provide correct student count from dashboard");
		}
			Assert.assertEquals(ActualCount, Exceptedcount);
			log.info("both count is same");
		}
		catch(Exception e) {
			log.info("something went wrong");
			e.printStackTrace();
		}
		finally {
			driver.close();
		}
	
	}

		public void verifyGuestUserCount(String actualvalue) {
			
			int noofguestuser = 0;
			new LoginPage(driver).loginToApplication(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
			for(int i=1; i<=ActualCount;i++) {
			WebElement match = driver.findElements(By.xpath("//*[@id=\"data_table\"]/tbody/tr["+i+"]/td[4]")).get(i);
			String Studenttype = match.toString();
			if(Studenttype=="Guest User") {
				noofguestuser = noofguestuser + 1;
			}
			else {
				log.info("no guest user avaliable");
			}
			
			}
			
			
			
		}
		
}
	
	
	