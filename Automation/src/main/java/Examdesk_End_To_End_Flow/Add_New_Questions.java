package Examdesk_End_To_End_Flow;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import DashboardPackage.Ongoing_Tests;
import Selenium_with_maven.Automation.helper.dropdown.DropdownHelper;
import Selenium_with_maven.Automation.helper.excel.ExcelHelper;
import Selenium_with_maven.Automation.helper.wait.WaitHelper;

public class Add_New_Questions {

	private static WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Add_New_Questions.class);

	// goto questions from dashboard
	@FindBy(xpath = "//*[text()='Question ']")
	WebElement buttonquestion;
	@FindBy(xpath = "//a[@href='https://test999.examdesk.co/administrator/question/addQuestion']")
	WebElement addquestion;

	// Add questions locators
	@FindBy(xpath = "//select[@id='selStandard']")
	WebElement selectstandard;
	@FindBy(xpath = "//select[@id='selSubject']")
	WebElement selectsubject;
	@FindBy(xpath = "//select[@name='difficulty_id']")
	WebElement selectdifficulty;
	@FindBy(xpath = "//select[@name='language_id']")
	WebElement selectlanguage;
	@FindBy(xpath = "//input[@name='correct_marks']")
	WebElement correctmarks;
	@FindBy(xpath = "//textarea[@id='question']")
	WebElement questiontext;
	@FindBy(xpath = "//select[@id='question_type_trigger']")
	WebElement selectquestiontype;
	@FindBy(xpath = "//button[text()='Save Question']")
	WebElement savequestion;
	@FindBy(xpath = "//textarea[@name = 'feedback_sc']")
	WebElement feedback;

	// single/Multiple choice options
	@FindBy(xpath = "//textarea[@id='options_sc_1']")
	WebElement firstoption;
	@FindBy(xpath = "//textarea[@id='options_sc_2']")
	WebElement secondoption;
	@FindBy(xpath = "//textarea[@id='options_sc_3']")
	WebElement thirdoption;
	@FindBy(xpath = "//textarea[@id='options_sc_4']")
	WebElement fourthoption;
	@FindBy(xpath = "//*[@name ='sc_correct_option' and @value='2']")
	WebElement correctanswer;
	@FindBy(xpath = "//textarea[@id = 'feedback_mc']")
	WebElement mc_feedback;

	// subjective autoevaluate
	@FindBy(xpath = "//input[@type='radio' and @id='auto_eval']")
	WebElement autoevaluate;
	@FindBy(xpath = "//select[@id ='ans_data_type']")
	WebElement answerdatatype;
	@FindBy(xpath = "//select[@id ='is_ans_limit']")
	WebElement answerlimit;
	@FindBy(xpath = "//textarea[@placeholder ='Write your answer here for auto evaluate']")
	WebElement autoevaluateanswer;

	// multiple choice correct options
	@FindBy(xpath = "//input[@type='checkbox' and @name='mc_correct_option[]' and @value='1']")
	WebElement multiplecorrectoptions1;
	@FindBy(xpath = "//input[@type='checkbox' and @name='mc_correct_option[]' and @value='2']")
	WebElement multiplecorrectoptions2;
	@FindBy(xpath = "//textarea[@id='options_mc_1']")
	WebElement multiplechoicefirstoption;
	@FindBy(xpath = "//textarea[@id='options_mc_2']")
	WebElement multiplechoicesecondoption;
	@FindBy(xpath = "//textarea[@id='options_mc_3']")
	WebElement multiplechoicethirdoption;
	@FindBy(xpath = "//textarea[@id='options_mc_4']")
	WebElement multiplechoicefourthoption;

	// true/false
	@FindBy(xpath = "//input[@name='options_tf']")
	WebElement truefalsecorrectoptions;
	@FindBy(xpath = "//textarea[@id = 'feedback_tf']")
	WebElement tf_feedback;

	// Match Matrix options
	@FindBy(xpath = "//textarea[@name ='options_mm_right[1]']")
	WebElement matchmatrixoption1;
	@FindBy(xpath = "//textarea[@name ='options_mm_right[2]']")
	WebElement matchmatrixoption2;
	@FindBy(xpath = "//textarea[@name ='options_mm_right[3]']")
	WebElement matchmatrixoption3;
	@FindBy(xpath = "//textarea[@name ='options_mm_right[4]']")
	WebElement matchmatrixoption4;
	@FindBy(xpath = "//textarea[@name ='options_mm_left[1]']")
	WebElement matchmatrixoption5;
	@FindBy(xpath = "//textarea[@name ='options_mm_left[2]']")
	WebElement matchmatrixoption6;
	@FindBy(xpath = "//textarea[@name ='options_mm_left[3]']")
	WebElement matchmatrixoption7;
	@FindBy(xpath = "//textarea[@name ='options_mm_left[4]']")
	WebElement matchmatrixoption8;

	// Match Matrix correct options
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mm_correct_option[1][]' and @value ='2']")
	WebElement firstrow;
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mm_correct_option[2][]' and @value ='2']")
	WebElement secondrow;
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mm_correct_option[3][]' and @value ='2']")
	WebElement thirdrow;
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mm_correct_option[4][]' and @value ='2']")
	WebElement fourthrow;
	@FindBy(xpath = "//textarea[@id = 'feedback_mm']")
	WebElement mm_feedback;

	// MAtch the following options
	@FindBy(xpath = "//*[@id='options_mf_left_1']")
	WebElement matchthefollowingoption1;
	@FindBy(xpath = "//*[@id='options_mf_left_2']")
	WebElement matchthefollowingoption2;
	@FindBy(xpath = "//*[@id='options_mf_left_3']")
	WebElement matchthefollowingoption3;
	@FindBy(xpath = "//*[@id='options_mf_left_4']")
	WebElement matchthefollowingoption4;
	@FindBy(xpath = "//*[@id='options_mf_right_1']")
	WebElement matchthefollowingoption5;
	@FindBy(xpath = "//*[@id='options_mf_right_2']")
	WebElement matchthefollowingoption6;
	@FindBy(xpath = "//*[@id='options_mf_right_3']")
	WebElement matchthefollowingoption7;
	@FindBy(xpath = "//*[@id='options_mf_right_4']")
	WebElement matchthefollowingoption8;

	// match the following correct options
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mf_correct_option[1]' and @value ='2']")
	WebElement mf_firstrow;
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mf_correct_option[2]' and @value ='2']")
	WebElement mf_secondrow;
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mf_correct_option[3]' and @value ='2']")
	WebElement mf_thirdrow;
	@FindBy(xpath = "//tbody/tr//th[text()='1']//following::td/input[@name='mf_correct_option[4]' and @value ='2']")
	WebElement mf_fourthrow;
	@FindBy(xpath = "//textarea[@id = 'feedback_mf']")
	WebElement mf_feedback;

	//subjective type manual and auto evaluation
	@FindBy(xpath = "//input[@id ='manual_eval']")
	WebElement selectmanualeval;
	@FindBy(xpath = "//input[@id ='auto_eval']")
	WebElement selectautoeval;
	@FindBy(xpath ="//textarea[@id ='feedback_subjective']")
	WebElement givefeedback;
	@FindBy(xpath ="//textarea[@name='answer']")
	WebElement answer;
	
	public Add_New_Questions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		WaitHelper wait = new WaitHelper(driver);
//		wait.waitForElement(element, timeoutInSeconds);
//		new TestBase().getNevigationScreen(driver);
//		TestBase.test.log(Status.INFO, "login object has created");
//		TestBase.extentLog("online_tests object has created");
	}

	public void gotoQuestionPage() {
		new WaitHelper(driver).SetImplicitWait(500, TimeUnit.SECONDS);
		Actions mouse = new Actions(driver);
		mouse.moveToElement(buttonquestion).perform();
		addquestion.click();
	}

	public void addSingleChoice(String startQuestion, String option1, String option2, String option3, String option4,
			String Feedback) throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		gotoQuestionPage();
		new DropdownHelper(driver).selectUsingValue(selectstandard, "809");
		new DropdownHelper(driver).selectUsingValue(selectsubject, "2628");
		new DropdownHelper(driver).selectUsingValue(selectdifficulty, "1");
		new DropdownHelper(driver).selectUsingValue(selectlanguage, "1");
		correctmarks.sendKeys("1");
		questiontext.sendKeys(startQuestion);
		new DropdownHelper(driver).selectUsingValue(selectquestiontype, "1");
		firstoption.sendKeys(option1);
		secondoption.sendKeys(option2);
		thirdoption.sendKeys(option3);
		fourthoption.sendKeys(option4);
		feedback.sendKeys(Feedback);
		correctanswer.click();
		savequestion.click();
		Thread.sleep(4000);
	}

	public void addMultipleChoice(String startQuestion, String option1, String option2, String option3, String option4,
			String Feedback) throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		gotoQuestionPage();
		new DropdownHelper(driver).selectUsingValue(selectstandard, "809");
		new DropdownHelper(driver).selectUsingValue(selectsubject, "2628");
		new DropdownHelper(driver).selectUsingValue(selectdifficulty, "1");
		new DropdownHelper(driver).selectUsingValue(selectlanguage, "1");
		correctmarks.sendKeys("1");
		questiontext.sendKeys(startQuestion);
		new DropdownHelper(driver).selectUsingValue(selectquestiontype, "3");
		multiplechoicefirstoption.sendKeys(option1);
		multiplechoicesecondoption.sendKeys(option2);
		multiplechoicethirdoption.sendKeys(option3);
		multiplechoicefourthoption.sendKeys(option4);
		Thread.sleep(2000);
		multiplecorrectoptions1.click();
		multiplecorrectoptions2.click();
		mc_feedback.sendKeys(Feedback);
		savequestion.click();
	}

	public void addtruefalse(String startQuestion, String Feedback) throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		gotoQuestionPage();
		Thread.sleep(4000);
		new DropdownHelper(driver).selectUsingValue(selectstandard, "809");
		new DropdownHelper(driver).selectUsingValue(selectsubject, "2628");
		new DropdownHelper(driver).selectUsingValue(selectdifficulty, "1");
		new DropdownHelper(driver).selectUsingValue(selectlanguage, "1");
		correctmarks.sendKeys("1");
		questiontext.sendKeys(startQuestion);
		new DropdownHelper(driver).selectUsingValue(selectquestiontype, "5");
		truefalsecorrectoptions.click();
		tf_feedback.sendKeys(Feedback);
		Thread.sleep(4000);
		savequestion.click();
	}

	public void addMatchMatrixQuestionType(String startQuestion, String option1, String option2, String option3,
			String option4, String option5, String option6, String option7, String option8, String Feedback)
			throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		gotoQuestionPage();
		Thread.sleep(4000);
		new DropdownHelper(driver).selectUsingValue(selectstandard, "809");
		new DropdownHelper(driver).selectUsingValue(selectsubject, "2628");
		new DropdownHelper(driver).selectUsingValue(selectdifficulty, "1");
		new DropdownHelper(driver).selectUsingValue(selectlanguage, "1");
		correctmarks.sendKeys("1");
		questiontext.sendKeys(startQuestion);
		new DropdownHelper(driver).selectUsingValue(selectquestiontype, "6");
		matchmatrixoption1.sendKeys(option1);
		matchmatrixoption2.sendKeys(option2);
		matchmatrixoption3.sendKeys(option3);
		matchmatrixoption4.sendKeys(option4);
		matchmatrixoption5.sendKeys(option5);
		matchmatrixoption6.sendKeys(option6);
		matchmatrixoption7.sendKeys(option7);
		matchmatrixoption8.sendKeys(option8);
		Thread.sleep(5000);
		firstrow.click();
		secondrow.click();
		thirdrow.click();
		fourthrow.click();
		mm_feedback.sendKeys(Feedback);
		savequestion.click();
	}

	public void addMatchTheFollowingQuestionType(String startQuestion, String option1, String option2, String option3,
			String option4, String option5, String option6, String option7, String option8, String Feedback)
			throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		gotoQuestionPage();
		Thread.sleep(4000);
		new DropdownHelper(driver).selectUsingValue(selectstandard, "809");
		new DropdownHelper(driver).selectUsingValue(selectsubject, "2628");
		new DropdownHelper(driver).selectUsingValue(selectdifficulty, "1");
		new DropdownHelper(driver).selectUsingValue(selectlanguage, "1");
		correctmarks.sendKeys("1");
		questiontext.sendKeys(startQuestion);
		new DropdownHelper(driver).selectUsingValue(selectquestiontype, "7");
		matchthefollowingoption1.sendKeys(option1);
		matchthefollowingoption2.sendKeys(option2);
		matchthefollowingoption3.sendKeys(option3);
		matchthefollowingoption4.sendKeys(option4);
		matchthefollowingoption5.sendKeys(option5);
		matchthefollowingoption6.sendKeys(option6);
		matchthefollowingoption7.sendKeys(option7);
		matchthefollowingoption8.sendKeys(option8);
		Thread.sleep(5000);
		mf_firstrow.click();
		mf_secondrow.click();
		mf_thirdrow.click();
		mf_fourthrow.click();
		mf_feedback.sendKeys(Feedback);
		savequestion.click();
	}
	
	public void subjectiveTypeAutoEval(String startQuestion, String answer, String Feedback) throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		gotoQuestionPage();
		Thread.sleep(4000);
		new DropdownHelper(driver).selectUsingValue(selectstandard, "809");
		new DropdownHelper(driver).selectUsingValue(selectsubject, "2628");
		new DropdownHelper(driver).selectUsingValue(selectdifficulty, "1");
		new DropdownHelper(driver).selectUsingValue(selectlanguage, "1");
		correctmarks.sendKeys("1");
		questiontext.sendKeys(startQuestion);
		new DropdownHelper(driver).selectUsingValue(selectquestiontype, "2");
		selectautoeval.click();
		this.answer.sendKeys(answer);
		givefeedback.sendKeys(Feedback);
		Thread.sleep(4000);
		savequestion.click();
	}
	
	public void subjectiveTypeManualEval(String startQuestion, String Feedback) throws InterruptedException {
		new WaitHelper(driver).SetImplicitWait(5000, TimeUnit.SECONDS);
		gotoQuestionPage();
		Thread.sleep(4000);
		new DropdownHelper(driver).selectUsingValue(selectstandard, "809");
		new DropdownHelper(driver).selectUsingValue(selectsubject, "2628");
		new DropdownHelper(driver).selectUsingValue(selectdifficulty, "1");
		new DropdownHelper(driver).selectUsingValue(selectlanguage, "1");
		correctmarks.sendKeys("1");
		questiontext.sendKeys(startQuestion);
		new DropdownHelper(driver).selectUsingValue(selectquestiontype, "2");
		selectmanualeval.click();
		givefeedback.sendKeys(Feedback);
		Thread.sleep(4000);
		savequestion.click();
	}

}
