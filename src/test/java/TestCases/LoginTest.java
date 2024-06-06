package TestCases;

import TestBase.BaseClass;
import PageObjects.LoginPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseClass {
	public String username = "Admin";
	public String password = "admin123";
	public WebDriver driver = BaseClass.driver;
	public LoginPage lpg = new LoginPage(driver);
	
	@Test(description = "Verify Login Functionality With Invalid Credentials", priority = 1, enabled = true)
	public void loginTestWithInvalidCredentials() {
		BaseClass.logger.info("Starting Login Procedure");
		
		BaseClass.logger.info("Providing invalid login credentials");
		
		BaseClass.logger.info("Inserting username");
		lpg.setUserName(username);
		
		BaseClass.logger.info("Entering Password");
		lpg.setPassword("invalid");
		
		BaseClass.logger.info("Clicking submit button");
		lpg.do_login();
		
		BaseClass.logger.info("Validating unsuccessfull login");
		
		BaseClass.logger.info("Comparing error message");
		String errtxt = lpg.get_errortxt();
		Assert.assertEquals(errtxt, "Invalid credentials","Test case unsuccessful");
	}
	
	@Test(description ="Verify Login Functionality With valid Credentials", priority = 2, enabled = true)
	public void loginTestWithValidCredentials() {
		BaseClass.logger.info("Providing valid login credentials");
		
		BaseClass.logger.info("Inserting username");
		lpg.setUserName(username);
		
		BaseClass.logger.info("Entering Password");
		lpg.setPassword(password);
		
		BaseClass.logger.info("Clicking submit button");
		lpg.do_login();
		
		BaseClass.logger.info("Validating Successfull login");
		if(lpg.verify_dashboard() == true) {
			Assert.assertTrue(true);
		}
		else Assert.assertTrue(false);
	}
	public static void login() {
		BaseClass.logger.info("Starting Login Procedure");
		
		BaseClass.logger.info("Inserting username and password credeintials");
		WebElement username = BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='oxd-input oxd-input--active' and @name='username' ]")));
		BaseClass.actions.moveToElement(username).click().sendKeys("Admin").sendKeys(Keys.TAB).sendKeys("admin123").perform();
		
		BaseClass.logger.info("Clicking submit button");
		BaseClass.driver.findElement(By.xpath("//button[@type='submit']")).submit();
//		BaseClass.js.executeScript("arguments[0].scrollIntoView(true);", submit);
//		BaseClass.js.executeScript("arguments[0].click();", submit);
	}
	

}
