package TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AddCustomerPage;
import TestBase.BaseClass;
import TestCases.LoginTest;

public class AddEmployeeTest extends BaseClass {
	//variables
	public final String fname= "Reyad";
	public final String lname= "HassanS";
	
	public WebDriver driver = BaseClass.driver;
	public AddCustomerPage apg = new AddCustomerPage(driver);
	
	@Test(description = "Testing Add Employee Functionality", priority = 1, enabled = true)
	public void addEmployee() {
		BaseClass.logger.info("Starting executing add Employee test case");
		
		BaseClass.logger.info("Logging in first");
		LoginTest.login();
		
		BaseClass.logger.info("Navigating to employee management page");
		apg.NavigateToPIM();
		
		BaseClass.logger.info("Click Add Employee Icon");
		apg.ClickAddEmployeeIcon();
		
		BaseClass.logger.info("Entering First Name of new employee");
		apg.SetFirstName(fname);
		
		BaseClass.logger.info("Entering Last Name of new employee");
		apg.SetLastName(lname);
		
		BaseClass.logger.info("Saving new employee");
		apg.SaveEmployee();
		
		BaseClass.logger.info("Validating Creating of new employee");
		boolean flag = apg.PersonalDetailsPage();
		Assert.assertTrue(flag, "Add Employee operation not successful");
		
	}
	
	@Test(description = "Testing Search Employee Functionality", priority = 2, enabled = true)
	public void searchEmployeeByFirstName() {
		BaseClass.logger.info("Starting executing Search Employee test case");
		
		BaseClass.logger.info("Navigating to employee management page");
		apg.NavigateToPIM();
		
		logger.info("Entering first Name in search employee module");
		apg.SetSearchFirstName(fname);
		
		BaseClass.logger.info("Clicking Serch Button");
		apg.ClickSearchButton();
		
		BaseClass.logger.info("Validating Creating of new employee by first name match and size of list");
		String result_fname = apg.FirstNameVerify();
		List<WebElement> records = apg.RecordsEmployeeSearchResults();
		if(result_fname == fname && records.size()>0) {
			Assert.assertTrue(true, "Search Employee operation not successful");
		}
		else {
			Assert.assertTrue(false);
		}
		
		
	}

}
