package base;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import libraries.HTMLReport;
import utilities.DataProviderObject;
import utilities.PropertyFileUtil;

public class BaseClass extends HTMLReport{
	
	public WebDriver driver; // 123
	public String propFileName = "Environment";
	public String sBrowser =  PropertyFileUtil.readDataFromPropertyFile(propFileName, "Browser");// Chrome,Edge,Firefox
	public String sURL = PropertyFileUtil.readDataFromPropertyFile(propFileName, "URL");
	public String excelFileName = "";
	public String testName,testDescription,testModule;

	@BeforeSuite
	public void reportInit() {
		startReport();
	}
	
	@AfterSuite
	public void bindReport() {
		endReport();
	}
	
	@BeforeClass
	public void invokeBrowser() {
		switch (sBrowser.toLowerCase()) {
		case "chrome":
			System.out.println("User option is "+sBrowser+",So invoking chrome browser");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.out.println("User option is "+sBrowser+",So invoking edge browser");
			driver = new EdgeDriver();
			break;
		case "firefox":
			System.out.println("User option is "+sBrowser+",So invoking firefox browser");
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("User option is wrong "+sBrowser+",So invoking the default chrome browser");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(sURL);
		startTestCase(testName, testDescription);
		startTestCase(testModule);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	@DataProvider(name="ExcelData")
	public Object[][] excelData() throws Exception {
		Object[][] values = utilities.DataProviderObject.getValue(excelFileName);
		return values;
	}
	
	@Override
	public String takeScreenshot() {
		String sPath = System.getProperty("user.dir")+"/snap/img"+System.currentTimeMillis()+".png";
		TakesScreenshot oShot = (TakesScreenshot)driver;
		File osrc = oShot.getScreenshotAs(OutputType.FILE);
		File dis = new File(sPath);
		try {
			FileUtils.copyFile(osrc, dis); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sPath;
	}
	
}
