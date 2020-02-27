package Selenium_with_maven.Automation.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import jdk.internal.org.jline.utils.Log;

public class ApplicationDBquery {
	private static Logger log = LoggerHelper.getLogger(ApplicationDBquery.class);
	int is_submitted;
	int is_expired;
	int currentlyattemptingcount = 0;
	int submittedtestcount = 0;

	public int test_id_from_tab_ed_test(int test_id) throws NumberFormatException, SQLException {

		String dbQuery = "SELECT is_submitted,is_expired from tab_ed_test_attempts WHERE test_id=" + test_id;
		ResultSet rs = DatabaseHelper.getResultSet(dbQuery);
		while (rs.next()) {
			int is_submitted = Integer.parseInt(rs.getString("is_submitted"));
			int is_expired = Integer.parseInt(rs.getString("is_expired"));
			System.out.println("is_submitted is" + is_submitted);
			System.out.println("is_expired is" + is_expired);
			if (is_submitted == 0 && is_expired == 0) {
				currentlyattemptingcount++;
			}
			
		}
//		return Arrays.asList(is_submitted, is_expired);	
		return currentlyattemptingcount;
//			return 1111111111;
	}

	public int testSubmittedCount(int test_id) throws NumberFormatException, SQLException {

		String dbQuery = "SELECT is_submitted,is_expired from tab_ed_test_attempts WHERE test_id=" + test_id;
		ResultSet rs1 = DatabaseHelper.getResultSet(dbQuery);
		while (rs1.next()) {
			int is_submitted = Integer.parseInt(rs1.getString("is_submitted"));
			int is_expired = Integer.parseInt(rs1.getString("is_expired"));
			System.out.println("is_submitted is" + is_submitted);
			System.out.println("is_expired is" + is_expired);
			if(is_submitted ==0 && is_expired ==1 || is_submitted ==1 && is_expired ==0 || is_submitted==1 && is_expired==1) {
				submittedtestcount++;
			}
		}
		return submittedtestcount;
	 }
	
	
	public String testNameForTestID(int test_id) throws SQLException {
		
		String dbQuery = "SELECT test_name FROM tab_ed_tests WHERE test_id=" +test_id;	
		 ResultSet rs = DatabaseHelper.getResultSet(dbQuery);
		 while(rs.next()) {
			String testname = rs.getString("test_name");
			log.info("Test_name is"+"  "+testname);
			return testname;
		 }
		 return null;
	}

	public static void main(String[] args) throws NumberFormatException, SQLException {
		ApplicationDBquery applicationquery = new ApplicationDBquery();
		int currentlyatt = applicationquery.test_id_from_tab_ed_test(1430);
		System.out.println(currentlyatt);
		applicationquery.testNameForTestID(1430);
		int a = applicationquery.testSubmittedCount(1430);
		System.out.println("test_submittes_count is"+ a);
	}
}
