package Selenium_with_maven.Automation.helper.excel;

import org.testng.annotations.DataProvider;

import Selenium_with_maven.Automation.testbase.TestBase;

public class LoginTestWithDataDrivenAproach extends TestBase {

	@DataProvider(name = "TestData")
	public Object[][] testdata() {
		Object[][] data = getExcelData("TestData.xlsx", "Login");
		return data;
	}
}
