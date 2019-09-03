package Selenium_with_maven.Automation.TestScripts;

import org.testng.annotations.Test;

import Selenium_with_maven.Automation.helper.assertion.AssertHelper;
import Selenium_with_maven.Automation.testbase.TestBase;

public class Test11 extends TestBase  {

	@Test
	public void testLogin() {
		 AssertHelper.makeTrue();
	}
	@Test
	public void testLogin1() {
		 AssertHelper.makeFalse();
	}
	@Test
	public void testLogin2() {
		 AssertHelper.makeTrue();
	}
	@Test
	public void testLogin3() {
		 AssertHelper.makeFalse();
	}
}
