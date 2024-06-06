package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestBase.BaseClass;

public class AddCustomerPage {
	WebDriver driver;
	
	//locators
	public String pim_locator = "//span[normalize-space()='PIM']";
	public String addemp_icon = "//i[@class='oxd-icon bi-plus oxd-button-icon']";
	public String fname_locator = "//input[@placeholder='First Name']";
	public String lname_locator = "//input[@placeholder='Last Name']";
	public String savebtn_locator = "//button[normalize-space()='Save']";
	public String searchfirstname = "//input[@placeholder='Type for hints...']";
	public String personaldtls_locator = "//h6[normalize-space()='Personal Details']";
	
	public String searchbtn_locator = "//button[normalize-space()='Search']";
	public String fnameverify_locator = "div[class='oxd-table-card'] div:nth-child(2) div:nth-child(1)";
	public String selemcount_locator = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']";
	
	
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void NavigateToPIM() {
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pim_locator))).click();
	}
	
	public void ClickAddEmployeeIcon() {
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addemp_icon))).click();
	}
	
	public void SetFirstName(String name) {
		BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fname_locator))).sendKeys(name);
	}
	public void SetLastName(String name) {
		BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lname_locator))).sendKeys(name);
	}
	public void SaveEmployee() {
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(savebtn_locator))).click();
	}
	public boolean PersonalDetailsPage() {
		try {
			BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(personaldtls_locator)));
			return true;
		}
			
		catch (Exception e) {
			return false;
		}
	}
	public void SetSearchFirstName(String name) {
		List<WebElement> first_name = BaseClass.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(searchfirstname)));
		BaseClass.actions.moveToElement(first_name.get(0)).click().sendKeys(name).perform();
	}
	public void ClickSearchButton(){
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchbtn_locator))).click();
	}
			
	public String FirstNameVerify() {
		return BaseClass.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fnameverify_locator))).getAttribute("textContent");
	}
	public List<WebElement> RecordsEmployeeSearchResults() {
		List<WebElement> records = BaseClass.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selemcount_locator)));
		return records;
	}
	
	
	

}
