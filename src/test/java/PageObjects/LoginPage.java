package PageObjects;

import TestBase.BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
	
	WebDriver driver;
	//using page factory
	
	public final String username_locator = "//input[@placeholder='Username']";
	public final String password_locator = "//input[@placeholder='Password']";
	public final String lgn_btn_locator = "//button[normalize-space()='Login']";
	public final String invalid_text_locator = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']";
	public final String dashboard_locator = "//h6[normalize-space()='Dashboard']";
	
//	@FindBy(xpath="//input[@class='oxd-input oxd-input--active' and @name='username' ]")
//	WebElement username;
//	
//	@FindBy(xpath="//input[@placeholder='Password']")
//	WebElement password;
//	
//	@FindBy(xpath="//button[normalize-space()='Login']")
//	WebElement loginbtn;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String name) {
		BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(username_locator))).sendKeys(name);
		
	}
	
	public void setPassword(String name) {
		BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(password_locator))).sendKeys(name);
		}
		
	public void do_login() {
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lgn_btn_locator))).click();
			}
	public String get_errortxt() {
		return BaseClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(invalid_text_locator))).getAttribute("textContent");
	}
	public boolean verify_dashboard() {
		try {
			
			BaseClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dashboard_locator)));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}
