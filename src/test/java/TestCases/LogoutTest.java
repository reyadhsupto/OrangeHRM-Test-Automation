package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestCases.LoginTest;
import TestBase.BaseClass;
import PageObjects.LogoutPage;

public class LogoutTest extends BaseClass{
	
	public WebDriver driver = BaseClass.driver;
	public LogoutPage lpg = new LogoutPage(driver);
	
	@Test(description = "Verify Logout Functionality", priority = 1, enabled = true)
	public void logoutTest() {
		BaseClass.logger.info("Starting Testcase Logout Test");
		
		BaseClass.logger.info("Logging in first");
		LoginTest.login();
		
		BaseClass.logger.info("Clicking dashboard dropdown");
		lpg.ClickDashboardIcon();
		
		BaseClass.logger.info("Clicking logout option");
		lpg.ClickLogoutButton();
		
		BaseClass.logger.info("Validating logout functionality");
		boolean loggedout = lpg.VerifyLogout();
		Assert.assertTrue(loggedout);
		
		
		
		
	}

}
