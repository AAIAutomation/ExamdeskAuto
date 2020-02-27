package Selenium_with_maven.Automation.pageObject;

import org.testng.annotations.Test;

import Selenium_with_maven.Automation.pageObject.DashboardPage.Dashboard;
import Selenium_with_maven.Automation.testbase.TestBase;

public class testScripts extends TestBase{

	public static String Actualtestcount="455";
	public static int Actualresultcountint=10;
	public static String Actualresultcount="10";
	public static String Actualpendingresultcount="11";
	
	@Test(description="check login and dashboard")
	public void testDashboard() throws InterruptedException {
		driver.get("https://test999.examdesk.co");
		Dashboard dashboard = new Dashboard(driver);
		dashboard.checkLinkForEvaluationAndResult();
		System.out.println("evaluation and result pass");
		dashboard.exceptedResultCount(Actualresultcount);
		System.out.println("All is Well  test");
		dashboard.compareExpectedAndActualPendingResultCount(Actualresultcountint, Actualpendingresultcount);
		System.out.println("All Pass Aniket");
		dashboard.ExpectedCountOfTests(Actualtestcount);
		System.out.println("Test count also passed.");
	}
}
