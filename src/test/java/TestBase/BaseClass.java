package TestBase;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	public String baseUrl="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public static WebDriver driver;
	public static final Logger logger = LoggerFactory.getLogger(BaseClass.class);
	public static Actions actions;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	
	public static void initializeActions(WebDriver webDriver) {
        actions = new Actions(webDriver);
    }
	public static void initializeWebdriverWait(WebDriver webDriver) {
		wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
	}
	
	public static void initializeJavascriptExecutor(WebDriver webDriver) {
		js = (JavascriptExecutor) driver;
	}
//	public BaseClass(WebDriver browser) {
//		this.driver = browser;
//	}
	
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
		
		logger.info("Lauching Chromedriver");
		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		
		logger.info("browsing to url");
		driver.get(baseUrl);
		initializeActions(driver);
		initializeWebdriverWait(driver);
	}
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);	
		driver.close();
		driver.quit();
		logger.info("Quitting browser");
	}

}
