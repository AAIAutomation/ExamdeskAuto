package DashboardPackage;

import org.testng.annotations.Test;

import LoginPackage.Login;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.testbase.TestBase;

public class Run_ongoing_tests extends TestBase {

	@Test(description="check ongoing tests")
	public void Ongoing_Testsq() throws InterruptedException {
		driver.get("https://test999.examdesk.co");
		Thread.sleep(1000);
		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Ongoing_Tests totalstudent = new Ongoing_Tests(driver);
		totalstudent.currentlyAttemptedCountMockTest();
		totalstudent.currentlyAttemptedOnlineExam();
		totalstudent.testSubmittedCountMockTest();
		totalstudent.testSubmittedCountOnlineExam();
		totalstudent.testDateCountMockTest();
		totalstudent.testDateCountOnlineTest();
		totalstudent.inactiveTest();
		System.out.println("Dashboard test cases pass");
	}
}
