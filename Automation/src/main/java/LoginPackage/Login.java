package LoginPackage;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.testbase.TestBase;

public class Login {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Login .class);
	
	@FindBy(xpath = "//*[text()='Login']")
	WebElement welcomePageLoginButton;
	
	@FindBy(xpath ="//*[@id ='userid']")
	WebElement username;
	
	@FindBy(xpath ="//*[@id='password']")
	WebElement password;
	
	@FindBy(xpath="//*[@type='submit' and @class='btn bg-examdesk btn-block']")
	WebElement login;
	
	
	public Login (WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		new TestBase().getNevigationScreen(driver);
	}
	
	public void loginToApp(String username, String password) throws InterruptedException{
		new WaitHelper(driver).SetImplicitWait(3000, TimeUnit.SECONDS);
		welcomePageLoginButton.click();
		log.info("clicked on login button");
		Thread.sleep(2000);
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		login.click();
		log.info("Admin Logged Successfully");
		
	}
	
	
	
}
