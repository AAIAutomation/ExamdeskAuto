package testgeneralpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import LoginPackage.Login;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.javascript.Scrollonpage;
import Selenium_with_maven.Automation.testbase.TestBase;

public class Attributemethod extends TestBase{

	@Test
	public void testDashboard1() throws InterruptedException {
		driver.get("https://test999.examdesk.co");
		new Login(driver).loginToApp(ObjectReader.reader.getsuperadminusername(), ObjectReader.reader.getsuperadminpassword());
		driver.findElement(By.xpath("//*[text()='Accounts']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@type='text']")).sendKeys("test.proctur@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@href='https://test999.examdesk.co/superadmin/account/editAccount/14']")).click();
		WebElement we = driver.findElement(By.xpath("//*[@name='max_test_limit']"));
		new Scrollonpage(driver).ScrollToElement(we);
		String testlimit = we.getAttribute("value");
		System.out.println(testlimit);
		Thread.sleep(5000);
		
	}
	
//	public static void main(String[] args) throws InterruptedException {
//		Attributemethod a = new Attributemethodsss();
//		a.testDashboard1();
//	}
}
