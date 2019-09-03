package Selenium_with_maven.Automation.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;

import Selenium_with_maven.Automation.helper.assertion.VerificationHelper;

public class DatabaseHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	public static String url ="jdbc:mysql://proctur-testbed.cfr5g7gon3pi.ap-southeast-1.rds.amazonaws.com:3306/procturtest";
	public static String drivername ="com.mysql.jdbc.Driver";
	public static String username ="procturtest";
	public static String password ="proctur123";
	public static Connection connection;
	public static DatabaseHelper instance = null;
	//public static Object getResultSet;
	
	public DatabaseHelper() {
		connection = getSiingleInstanceConnection();
	}
	public static DatabaseHelper getInstance() {
		if(instance == null) {
			instance = new DatabaseHelper();
		}
		return instance;
	}
	private Connection getSiingleInstanceConnection() {
		try {
			Class.forName(drivername);
			try {
					DriverManager.getConnection(url , username, password);
					if (connection!=  null) {
						log.info("connected to database");
					}
		     	}
			catch(SQLException e) {
				 log.error("failed to create database connection..."+e);
			}
			
		}
		catch(ClassNotFoundException e) {
			log.info("driver not found..."+e);
		}
		return connection;
	}
	public Connection getConnection() {
		return connection;
	}	
	public ResultSet getResultSet(String dbQiery) {
		instance = DatabaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("executing query"+dbQiery);
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(dbQiery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
