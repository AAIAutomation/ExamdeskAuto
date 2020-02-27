package DashboardPackage;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import LoginPackage.Login;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.helper.assertion.AssertHelper;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.testbase.TestBase;

public class Click_on_all_the_links {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Click_on_all_the_links.class);
	WaitHelper waithelper;

	@FindBy(xpath = "//a[@href='administrator/evaluation']")
	WebElement pendingevaluation;
	@FindBy(xpath = "//a[@href='administrator/result']")
	WebElement pendingresults;
	@FindBy(xpath = "//a[text()='Dashboard']")
	WebElement homepage;
	@FindBy(xpath = "//a[@href='administrator/manage/reports']")
	WebElement testreports;
	@FindBy(xpath = "/html/body/div[6]/div/h1/text()")
	WebElement textoftestevaluation;
	@FindBy(xpath = "/html/body/div[6]/aside/section[1]/h1")
	WebElement resultpage;
	@FindBy(xpath = "//div[@class='p-0 col-md-12 col-sm-12']//*[text()='Test Reports']")
	WebElement testreport;

	public Click_on_all_the_links(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		new TestBase().getNevigationScreen(driver);
	}

	@BeforeTest
	public void LogintoApp() {
		try {
			new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	public void clickOnLinkPendingEval() {
		pendingevaluation.click();
		String Actual = "Test Evaluation";
		String Expected = textoftestevaluation.getText();
		int i =AssertHelper.VerifyText(Actual, Expected);
		log.info(i);
		homepage.click();
		log.info("successfullyClickOnPendingEval");
	}
	
	@Test(priority = 2)
	public void clickOnPendingResult(){
		pendingresults.click();
		String Actual = "Manage Results";
		String Expected = resultpage.getText();
		int i= AssertHelper.VerifyText(Actual, Expected);
		log.info(i);
		homepage.click();
		log.info("successfullyClickOnPendingResult");
	}
	
	@Test(priority = 3)
	public void clickOnReport() {
		testreports.click();
		String Actual ="Test Reports";
		String Expected = testreport.getText();
		int i= AssertHelper.VerifyText(Actual, Expected);
		log.info(i);
		homepage.click();
		log.info("successfullyClickOnReport");
	}

}
