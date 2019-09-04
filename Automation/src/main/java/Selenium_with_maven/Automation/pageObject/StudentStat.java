package Selenium_with_maven.Automation.pageObject;

import org.testng.annotations.Test;

import Selenium_with_maven.Automation.testbase.TestBase;

public class StudentStat extends TestBase {

	public static String Actualtestcount="455";
	public static int Actualresultcountint=10;
	public static String Actualresultcount="10";
	public static String Actualpendingresultcount="11";
	
	@Test(description="check login and Student stat")
	public void testDashboard() throws InterruptedException {
		driver.get("https://test999.examdesk.co");
		VerifyStudentStats totalstudent = new VerifyStudentStats(driver);
		totalstudent.VerifyTotalStudentCount("9");
		System.out.println("student count pass");
	}
}

