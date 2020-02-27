package Examdesk_End_To_End_Flow;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import LoginPackage.Login;
import Selenium_with_maven.Automation.helper.BrowserConfiguration.Config.ObjectReader;
import Selenium_with_maven.Automation.testbase.TestBase;

public class Add_Question extends TestBase {

	@DataProvider(name = "TestData")

	public Object[][] testDataSingleChoice() {
		Object[][] data = getExcelData("TestData.xlsx", "singleChoice");
		return data;
	}

	@DataProvider(name = "TestData1")

	public Object[][] testDataMultipleChoice() {
		Object[][] data = getExcelData("TestData.xlsx", "multipleChoice");
		return data;
	}
	
	@DataProvider(name = "TestData2")

	public Object[][] testTrueFalse() {
		Object[][] data = getExcelData("TestData.xlsx", "true");
		return data;
	}
	
	@DataProvider(name = "TestData3")

	public Object[][] testDataMatchMatrix() {
		Object[][] data = getExcelData("TestData.xlsx", "matchMatrix");
		return data;
	}
	
	@DataProvider(name = "TestData4")

	public Object[][] testDataMatchTheFollowing() {
		Object[][] data = getExcelData("TestData.xlsx", "matchTheFollowing");
		return data;
	}
	
	@DataProvider(name = "TestData5")

	public Object[][] testDataManualSubjectiveType() {
		Object[][] data = getExcelData("TestData.xlsx", "manualSubjectiveType");
		return data;
	}
	
	@DataProvider(name = "TestData6")

	public Object[][] testDataAutoSubjectiveType() {
		Object[][] data = getExcelData("TestData.xlsx", "autoSubjectivetype");
		return data;
	}
	
	

	@Test(priority = 1)
	public void Login() throws InterruptedException {
		driver.get("https://test999.examdesk.co");
		Thread.sleep(1000);
		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
	}

	@Test(dataProvider = "TestData", priority = 2)
	public void Add_New_QuestionsSingleChoice(String startQuestion, String option1, String option2, String option3,
			String option4, String Feedback) throws InterruptedException {
//		driver.get("https://test999.examdesk.co");
//		Thread.sleep(1000);
//		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Add_New_Questions singlechoice = new Add_New_Questions(driver);
		singlechoice.addSingleChoice(startQuestion, option1, option2, option3, option4, Feedback);
	}

	@Test(dataProvider = "TestData1", priority = 3)
	public void Add_New_QuestionsMultipleChoice(String startQuestion, String option1, String option2, String option3,
			String option4, String Feedback) throws InterruptedException {
//		driver.get("https://test999.examdesk.co");
//		Thread.sleep(1000);
//		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Add_New_Questions multiplechoice = new Add_New_Questions(driver);
		multiplechoice.addMultipleChoice(startQuestion, option1, option2, option3, option4, Feedback);
	}

	@Test(dataProvider = "TestData2", priority = 4)
	public void Add_New_QuestionsTrueFalseType(String startQuestion, String Feedback) throws InterruptedException {
//		driver.get("https://test999.examdesk.co");
//		Thread.sleep(1000);
//		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Add_New_Questions truefalse = new Add_New_Questions(driver);
		truefalse.addtruefalse(startQuestion, Feedback);
	}

	@Test(dataProvider = "TestData3", priority = 5)
	public void Add_New_QuestionsMatchMatrixType(String startQuestion, String option1, String option2, String option3,
			String option4, String option5, String option6, String option7, String option8, String Feedback)
			throws InterruptedException {
//		driver.get("https://test999.examdesk.co");
//		Thread.sleep(1000);
//		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Add_New_Questions matchmatrix = new Add_New_Questions(driver);
		matchmatrix.addMatchMatrixQuestionType(startQuestion, option1, option2, option3, option4, option5, option6, option7, option8, Feedback);
	}

	@Test(dataProvider = "TestData4", priority = 6)
	public void Add_New_QuestionsMatchTheFollowingType(String startQuestion, String option1, String option2,
			String option3, String option4, String option5, String option6, String option7, String option8,
			String Feedback) throws InterruptedException {
//		driver.get("https://test999.examdesk.co");
//		Thread.sleep(1000);
//		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Add_New_Questions matchfollowing = new Add_New_Questions(driver);
		matchfollowing.addMatchTheFollowingQuestionType(startQuestion, option1, option2, option3, option4, option5, option6, option7, option8, Feedback);
	}
	
	@Test(dataProvider = "TestData2", priority = 4)
	public void Add_New_QuestionsManualSubjectiveType(String startQuestion, String Feedback) throws InterruptedException {
//		driver.get("https://test999.examdesk.co");
//		Thread.sleep(1000);
//		new Login(driver).loginToApp(ObjectReader.reader.getusername(), ObjectReader.reader.getpassword());
		Add_New_Questions manualsubjectivetype = new Add_New_Questions(driver);
		manualsubjectivetype.addtruefalse(startQuestion, Feedback);
	}
}
