package Selenium_with_maven.Automation.helper.BrowserConfiguration.Config;

import Selenium_with_maven.Automation.helper.BrowserConfiguration.BrowserType;


public interface ConfigReader {

	public int getImpliciteWait();
	public int getExpliciteWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getusername();
	public String getpassword();
}
