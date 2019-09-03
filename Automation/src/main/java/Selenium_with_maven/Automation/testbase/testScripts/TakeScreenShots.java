package Selenium_with_maven.Automation.testbase.testScripts;

import org.testng.annotations.Test;

import Selenium_with_maven.Automation.testbase.TestBase;

public class TakeScreenShots extends TestBase{
	
	@Test
	public void testScreen() {
//		
		driver.get("https://www.google.com");
		System.out.println("check screenshot method");
      	captureScreen("firstscreen1", driver);
	}

}
