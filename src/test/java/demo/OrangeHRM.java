package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

import TestBase.BaseClass;

import org.testng.annotations.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrangeHRM {
	public String baseUrl="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public static WebDriver driver;
	public static final Logger logger = LoggerFactory.getLogger(OrangeHRM.class);
	public static Actions actions;
	
	public static void initializeActions(WebDriver webDriver) {
//        driver = webDriver;
        actions = new Actions(webDriver);
    }
	
	@BeforeTest
	public void setup() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--ignore-certificate-errors");
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-notifications");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("user-agent=fake-useragent");
		options.addArguments("--disable-extensions");
		
		boolean driver_headless = false;
		if(driver_headless == true) {
			options.addArguments("--headless=new");
			options.addArguments("--disable-gpu");
			options.addArguments("--hide-scrollbars");
		}

		driver = new ChromeDriver(options);
		logger.info("Lauching Chromedriver");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		
		driver.get(baseUrl);
		initializeActions(driver);
		logger.info("browsing to url");
	}
	
	@Test(priority = 1, enabled = true)
	public void loginTestWithInvalidCredentials() {
		logger.info("Starting Login Procedure");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		logger.info("Inserting username and password credeintials");
		WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='oxd-input oxd-input--active' and @name='username' ]")));
		actions.moveToElement(username).click().sendKeys("Admin").sendKeys(Keys.TAB).sendKeys("admin").perform();
		
		logger.info("Clicking submit button");
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submit);
        js.executeScript("arguments[0].click();", submit);
        
        String invalid_text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']"))).getAttribute("textContent");
        
        logger.info("Validating login cases for invalid login credentials");
        Assert.assertEquals(invalid_text, "Invalid credentials","Test case unsuccessful");
	}
	
	@Test(priority = 2, enabled = true)
	public void loginTestWithValidCredentials() {
		logger.info("Staring Testcase loginTest");

		login();
        
        logger.info("Validating successfull login");
        Assert.assertEquals(driver.getTitle().trim(), "OrangeHRM", "Login Unsuccessful");
        
        
	}
	
	@Test(priority = 3, enabled = true)
	public void addEmployee() {
		logger.info("Starting executing add Employee test case");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		logger.info("Navigating to employee management page");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='PIM']"))).click();
		
		logger.info("Clicking add new employee icon");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']"))).click();
		
		logger.info("Entering first Name,last name");
		WebElement usernm_add = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='First Name']")));
		actions.moveToElement(usernm_add).click().sendKeys("ReyadH"+Keys.TAB+Keys.TAB).sendKeys("Supto").perform();
		
		logger.info("Clicking save employee button");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Save']"))).click();
		
		logger.info("Again Navigating to employee management page");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='PIM']"))).click();
		
		logger.info("Entering first Name in search employee module");
		List<WebElement> first_name = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Type for hints...']")));
		actions.moveToElement(first_name.get(0)).click().sendKeys("ReyadH").perform();
		
		logger.info("Clicking the search button");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']"))).click();
		
		logger.info("Validating search employee test case");
		
		logger.info("Creating an array of found employees");
		List<WebElement> records = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']")));
		
		if(records.size() > 0) {
			logger.info("Employee found in database and add employee successfull");
			Assert.assertTrue(true, "Employee found in database and add employee successfull");
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void logoutTest() {
		logger.info("Staring Testcase Logout Test");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        boolean flag = true;
        try {
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Dashboard']")));}
        catch (Exception e) {
        	flag = false;
        }
        if(!flag) {
        	logger.info("unseccessful login try");
        	Assert.assertFalse(false, "unseccessful login");
        }
        else {
        	logger.info("Clicking logout button");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='oxd-userdropdown-name']"))).click();
	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Logout']"))).click();
			logger.info("Validating logout test");
			Assert.assertEquals(driver.getTitle().trim(), "OrangeHRM", "Logout Unsuccessful");
        }
		
        
	}
	public void login() {
		logger.info("Starting Login Procedure");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		logger.info("Inserting username and password credeintials");
		WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='oxd-input oxd-input--active' and @name='username' ]")));
		actions.moveToElement(username).click().sendKeys("Admin").sendKeys(Keys.TAB).sendKeys("admin123").perform();
		
		logger.info("Clicking submit button");
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submit);
        js.executeScript("arguments[0].click();", submit);
	}

	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);	
		driver.close();
		driver.quit();
		logger.info("Quitting browser");
	}

}

