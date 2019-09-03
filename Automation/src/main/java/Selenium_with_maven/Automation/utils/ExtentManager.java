package Selenium_with_maven.Automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import jdk.internal.org.jline.utils.Log;

public class ExtentManager {

	public static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent == null) {
			System.out.println("anikt");
			return CreateInstance("test-output//extent.html");
			
		}
		else {
			return extent;
		}
	}
	
	public static ExtentReports CreateInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("uft-8");
		htmlReporter.config().setReportName("Automation Report");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}
}
