package Selenium_with_maven.Automation.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

public class AssertHelper {

	private static Logger log = LoggerHelper.getLogger(AssertHelper.class);

	public static int VerifyText(String s1, String s2) {
		log.info("verifying text:" + s1 + "with" + s2);
		Assert.assertEquals(s1, s2);
		return 0;
	}
	
	public static int VerifyNumber(int n1, int n2) {
		log.info("verifying text:" + n1 + "with" + n2);
		Assert.assertEquals(n1, n2);
		return 0;
	}
	
	public static void makeTrue() {
		log.info("making script PASS...");
		Assert.assertTrue(true);
	}

	public static void makeTrue(String message) {
		log.info("making script PASS..." + message);
		Assert.assertTrue(true, message);
	}

	public static void makeFalse() {
		log.info("making script FAIL...");
		Assert.assertTrue(false);
	}

	public static void makeFalse(String message) {
		log.info("making script FAIL..." + message);
		Assert.assertTrue(true, message);
	}

	public static void VerifyNullObject(String s1) {
		log.info("VErify object is null...");
		Assert.assertNull(s1);
	}

	public static void VerifyNotNullObject(String s1) {
		log.info("VErify object is not null...");
		Assert.assertNotNull(s1);
	}

}
