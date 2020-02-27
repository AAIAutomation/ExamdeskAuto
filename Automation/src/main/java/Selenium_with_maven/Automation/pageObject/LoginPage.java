package Selenium_with_maven.Automation.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.assertion.AssertHelper;
import Selenium_with_maven.Automation.helper.assertion.VerificationHelper;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.testbase.TestBase;

public class LoginPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	WaitHelper waithelper;
	
	@FindBy(xpath= "//*[@id='userid']")
	WebElement username;
	@FindBy(xpath ="//*[@id='password']")
	WebElement password;
	@FindBy(xpath= "//button[@type='submit']")
	WebElement submit;
	@FindBy(xpath= "//*[@id='bs-example-navbar-collapse-1']/ul/li[8]/ul/li[3]/a")
	WebElement clickOnLogout;
	@FindBy(xpath = "/html/body/div[2]/div[2]/form/div[3]/div/p")
	WebElement ExpectedLogoutMessage;
	public String ActualLogoutMessage = "Logged Out Successfully.";
	@FindBy(xpath= "//*[@href='https://test999.examdesk.co/administrator/login']")
	WebElement gotologinpage;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		WaitHelper wait = new WaitHelper(driver);
//		wait.waitForElement(element, timeoutInSeconds);
		new TestBase().getNevigationScreen(driver);
//		TestBase.test.log(Status.INFO, "login object has created");
		TestBase.extentLog("login object has created");
	}
	
	public void givingUsername(String username) {
		this.username.sendKeys(username);
		log.info("send username");
//		TestBase.test.log(Status.INFO, "added username in the text filed.");
		TestBase.extentLog("send username");
		
	}
	public void givingPassword(String password) {
		this.password.sendKeys(password);
		log.info("send password");
		TestBase.extentLog("send password");
	}
	
	public void clickOnSubmitButton() {
		submit.click();
		log.info("submitted successfully");
		TestBase.extentLog("submitted successfully");
	}
	
	public void clickOnLogoutButton() {
		log.info("click on logout successfully");
		clickOnLogout.click();
		TestBase.extentLog("click on logout successfully");
	}
	
	public void VerifyMessageAfterLogout(String LogoutMessage) {
		AssertHelper.VerifyText(ExpectedLogoutMessage.toString(), ActualLogoutMessage);
	}
	
	public void loginToApplication(String username, String password) {
		givingUsername(username);
		givingPassword(password);
		clickOnSubmitButton();
		log.info("Login Successfully");
		TestBase.extentLog("Login Successfully");
	}
	public void loginToApplicationAndLogout(String username, String password) {
		gotologinpage.click();
		givingUsername(username);
		givingPassword(password);
		clickOnSubmitButton();
		log.info("Login and Logout Successfully");
		TestBase.extentLog("Login and Logout Successfully");
	}
}
