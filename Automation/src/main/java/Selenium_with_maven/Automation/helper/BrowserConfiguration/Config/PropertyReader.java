package Selenium_with_maven.Automation.helper.BrowserConfiguration.Config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.uiframework.proctur.eLearn.logger.resource.ResourceHelper;

import Selenium_with_maven.Automation.helper.BrowserConfiguration.BrowserType;

public class PropertyReader implements ConfigReader {
	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
		try {
			String filepath = ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\Config.properties");
			FileInputStream file = new FileInputStream(new File(filepath));
			OR = new Properties();
			OR.load(file);
//			whenever required new config file copy paste above 4 line and only  name of properties file
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImpliciteWait() {
		return  Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExpliciteWait() {
		return  Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return  Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browsertype"));
	}
	public String getusername() {
		return OR.getProperty(("username"));
	}
	public String getpassword() {
		return OR.getProperty(("password"));
	}
	public String getURL() {
		return OR.getProperty("url");
	}
	
	public String getsuperadminusername() {
		return OR.getProperty(("superadminusername"));
	}
	
	public String getsuperadminpassword() {
		return OR.getProperty(("superadminpassword"));
	}

}
