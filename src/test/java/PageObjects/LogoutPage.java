package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestBase.BaseClass;

public class LogoutPage {
	WebDriver driver;
	
	//locators
	public final String dashboard_locator = "//span[@class='oxd-userdropdown-tab']";
	public final String logoutbtn_locator = "//a[normalize-space()='Logout']";
	public final String logintxt_locator = "//h5[normalize-space()='Login']";
	
	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickDashboardIcon() {
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dashboard_locator))).click();
		
	}
	public void ClickLogoutButton() {
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(logoutbtn_locator))).click();
	}
	
	public boolean VerifyLogout() {
		try {
			BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(logintxt_locator)));
			return true;
		}	
		catch (Exception e) {
			return false;
		}
	}

}
