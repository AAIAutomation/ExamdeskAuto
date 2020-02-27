package Selenium_with_maven.Automation.pageObject.DashboardPage;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.helper.assertion.VerificationHelper;
import Selenium_with_maven.Automation.helper.dropdown.DropdownHelper;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;
import Selenium_with_maven.Automation.pageObject.LoginPage;
import Selenium_with_maven.Automation.testbase.TestBase;

public class Dashboard extends TestBase {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	WaitHelper waithelper;

	@FindBy(xpath = "//a[@href='administrator/evaluation']")
	WebElement EvaluateButtonLink;
	@FindBy(xpath = "//a[@href='administrator/result']")
	WebElement ResultButtonLink;
	@FindBy(xpath = " //a[text()='Evaluate' and @class='btn btn-default btn-xs']")
	List<WebElement> ExceptedPendingEvaluationCount;
	@FindBy(xpath = "//*[@id=\"dashboard\"]/div[1]/div[31]/div[1]/div[2]/div[1]/p")
	WebElement ActualPendingEvaluationCount;
	@FindBy(xpath = "//*[text()='Publish All']")
	List<WebElement> ExpectedPendinResultCount;
	@FindBy(xpath = "//*[contains(text(),'10 Tests')]")
	WebElement ActualPendinResultCount;
	@FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[8]/a")
	WebElement mouseHoverTestExamdeskButton;
	@FindBy(xpath = "//a[@href='https://test999.examdesk.co/administrator/account']")
	WebElement clickOnAccountButton;
	@FindBy(xpath = "//*[@href='https://test999.examdesk.co/administrator/sms']")
	WebElement clickOnSMSButton;
	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div[2]/div[1]/div/p[1]")
	WebElement totalSMSAllocated;
	@FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div[2]/div[2]/div/p[1]")
	WebElement remeaningSmsBalance;
	@FindBy(xpath = "//*[contains(text(),'Package List')]")
	WebElement ExceptedTotalStudentPresent;
	@FindBy(xpath = "//*[@id=\"dashboard\"]/div[2]/div[3]/div/div[3]/span[2]")
	WebElement ActualTotalStudentPresent;
	@FindBy(xpath = "//*[@class='btn btn-xs btn-default']")
	WebElement ExpectedTotalPackageCount;
	@FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[5]/a")
	WebElement clickOnPackageButton;
	@FindBy(xpath = "//*[contains(@class,'next')]/child::a")
	WebElement clickOnNextButton;
	@FindBy(xpath = "//*[@id='bs-example-navbar-collapse-1']/ul/li[3]/a")
	WebElement MouseHoverOnTests;
	@FindBy(xpath = "//*[@href='https://examdesk.co/administrator/result']")
	WebElement clickOnResultButton;
	@FindBy(xpath = "//*[@id=\"data_table_length\"]/label/select")
	WebElement RecordsPerPage;
	@FindBy(xpath = "//*[@alt='Test Series']")
	WebElement HomePage;
	@FindBy(xpath = "")
	WebElement IterateOverAllRowOfResult;
	@FindBy(xpath = "//*[@id=\"data_table\"]/tbody/tr[1]/td[5]")
	WebElement GetValueForResultGenerated;
	@FindBy(xpath="//*[@href='https://test999.examdesk.co/administrator/test/testList']")
	WebElement clickontestlistpage;
	@FindBy(xpath="//*[contains(@class,'next disabled')]")
	WebElement disabledNext;

	public Dashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		WaitHelper wait = new WaitHelper(driver);
//		wait.waitForElement(element, timeoutInSeconds);
		new TestBase().getNevigationScreen(driver);
//		TestBase.test.log(Status.INFO, "login object has created");
		TestBase.extentLog("login object has created");
	}

	public void checkLinkForEvaluationAndResult() throws InterruptedException {
		new LoginPage(driver).loginToApplication(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		EvaluateButtonLink.click();
		log.info("Successfully clicked on evaluation link.");
		driver.navigate().back();
		Thread.sleep(1000);
		ResultButtonLink.click();
		log.info("Successfully clicked on result link.");
		TestBase.extentLog("successfuly clicked on result and evaluation link.");
//		HomePage.click();
	}

//	public int CountOnPage(List<WebElement> expectedPendinResultCount) {
//		int i=0;
//		{
//			for(WebElement resultCount:ExpectedPendinResultCount) {
//				log.info("first page count is:" +resultCount);
//				i = i+Integer.parseInt(resultCount.getSize().toString());
//				log.info("value of i is "+i);
//			}
////			clickOnNextButton.click();
////		}while((clickOnNextButton.isEnabled()));
//		}
//		log.info("Expected Total number of result for published"+ i);
//		TestBase.extentLog("Expected Total number of result for published"+ i);
//		return i;

	// goto main mouse hover then next then click.
//	public void MouseHoverToAnyWebElementAndClick(WebElement hover1, WebElement hover2) {
//		Actions actions = new Actions(driver);
//		actions.clickAndHold(hover1);
//		waithelper.SetImplicitWait(50, TimeUnit.SECONDS);
//		actions.moveToElement(hover2);
//		actions.click();
//		log.info("move to core element and clicked.");
//	}

	public void select100Pagenation(WebElement recordsPerPage) {
		new DropdownHelper(driver).selectUsingVisibleText(recordsPerPage, "100");
		log.info("100 records per page are present.");
	}

	public Object genericMethodToClickOnNextAndTakeCount(WebElement clickOnNext, WebElement recordsPerPage) {
		int i =1;
		select100Pagenation(recordsPerPage);
		List<WebElement> we = driver.findElements(By.xpath("//*[@class='pagination']//following::li"));
		log.info("total buton present is:"+we.size());
		if (clickOnNextButton.isDisplayed()) {
			for(i=1;i<we.size();i++)
			 {
				clickOnNext.click();
			 }
		log.info("total pages is" + i);
		}
		if (i <= 1) {
			log.info("check its return 0 or not");
			return 0;
		} else if (i > 1) {
			i = i * 100;
			return i - 200;

		}
		return "some error has occured.";
	}

	public void exceptedResultCount(String actualno) throws InterruptedException {
		log.info("Driver still on resut page");
//		int totalresutcount=0;
		int remeaningresultcount = 0;
//		new LoginPage(driver).loginToApplication(ObjectReader.reader.getusername(),ObjectReader.reader.getpassword());
//		Actions a = new Actions(driver);
//		a.moveToElement(MouseHoverOnTests).perform();;
//		Thread.sleep(1000);
//		a.moveToElement(clickOnResultButton);
		Integer totalresutcount = (Integer) genericMethodToClickOnNextAndTakeCount(clickOnNextButton, RecordsPerPage);
		log.info("total records count is" + totalresutcount);
		remeaningresultcount = CountOnPage(ExpectedPendinResultCount);
		log.info("total count of result is " + remeaningresultcount);
		int expected = (totalresutcount + remeaningresultcount);
		log.info("total Result avalible on page." + expected);
		HomePage.click();
//		try {
//			driver.findElement(By.xpath("//*[contains(text(),'" + actualno + " Tests')]"));
//		} catch (NoSuchElementException e) {
//
//			log.info("Please check once because This is not an actual no." + actualno);
//		}
//		driver.findElement(By.xpath("//*[contains(text(),'"+actualno+" Tests')]")).click();
//		log.info("element is visibleed");
		if (driver.findElement(By.xpath("//*[contains(text(),'" + actualno + " Tests')]")) != null) {
			log.info("yes. This is Correct Actual no.");
			String ResultCount = actualno.toString();
			if (expected == Integer.parseInt(ResultCount)) {
				log.info("count coming same");
			} else {
				log.info("count mismatch");
			}
		}
		else {
		log.info("Please check once because This is not an actual no." + actualno);
		}
	}
//		int actual = Integer.parseInt(ActualPendinResultCount.getAttribute("text()"));
//		log.info("actual value is:"+ actual);
//		if(expected==actual) {
//			log.info("count coming same");
//		}
//		else {
//			log.info("count mismatch");
//		}
//		
	

	private int CountOnPage(List<WebElement> expectedPendinResultCount2) {
		int i = 0;
		for (WebElement wwe : expectedPendinResultCount2) {

			log.info("total element found is:" + wwe.getText());
			i++;
		}
		return i;
	}

	public int getExpectedCountForPendingResult(int noofrows) {
		int Total = 0;
		ResultButtonLink.click();
		for (int i = 1; i <= noofrows; i++) {
//			driver.findElement(By.xpath("//*[@id='data_table']/tbody/tr["+i+"]"));
			String we = driver.findElement(By.xpath("//*[@id=\"data_table\"]/tbody/tr["+i+"]/td[5]")).getText();
			log.info("The REsult Generated test is" + we);
			int Value = Integer.parseInt(we);
			Total = Total + Value;
		}
		log.info("The total result pending from test is:" + Total);
		return Total;
	}

	public void compareExpectedAndActualPendingResultCount(int totalnoofrows, String actualpendingresultcount) {

		if (driver.findElement(By.xpath(
				"//*[contains(text(),'" + actualpendingresultcount + "') and @class='info-heading m-0']")) != null) {

			log.info("Right. Its a correct Actual pending result count and xpath has found!");
			int ExpectedCount = getExpectedCountForPendingResult(totalnoofrows);
			log.info("ExpectedCount"+ExpectedCount);
			int ActualCount = Integer.parseInt(actualpendingresultcount);
			log.info("ActualCount"+ActualCount);
			Assert.assertEquals(ActualCount, ExpectedCount);
			log.info("Both pending result count has matched.");
		}
//		log.info("Please provide correct value its mismatch");

	}
	
	public void ExpectedCountOfTests(String Actualtestcount) throws InterruptedException {
//		new LoginPage(driver).loginToApplication(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Actions a = new Actions(driver);
				a.moveToElement(MouseHoverOnTests).perform();
				Thread.sleep(1000);
				clickontestlistpage.click();
				
//		a.moveToElement(MouseHoverOnTests).perform();
//		Thread.sleep(1000);
//		a.moveToElement(clickontestlistpage).click().perform();
	
		Integer totalresutcount = (Integer) genericMethodToClickOnNextAndTakeCount(clickOnNextButton, RecordsPerPage);
		log.info("total records count is" + totalresutcount);
		List<WebElement> we = driver.findElements(By.xpath("//*[text()='More']"));
		log.info("The count on last page got is:"+we.size());
		int totalexpected = totalresutcount+ we.size();
		log.info("the total entry of test pressent in this account is:"+totalexpected);
		HomePage.click();
		Thread.sleep(100);
		if(driver.findElement(By.xpath("//*[contains(text(),'Total Test' )and //text()='"+Actualtestcount+"']//following::span[1]")) != null); 
		int Actual = Integer.parseInt(Actualtestcount);
		Assert.assertEquals(Actual, totalexpected);
		log.info("Both expected and actual");
		log.info("Test has passed");
		}
//		{
//			log.info("please send correct test count");
//		}
}
	
	

