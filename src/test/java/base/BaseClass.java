package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import utilities.DataProviderObject;
import utilities.PropertyFileUtil;

public class BaseClass {
	

	public WebDriver driver;//null
	public String propFileName = "Environment";
	public  int browser = Integer.parseInt(PropertyFileUtil.readDataFromPropertyFile(propFileName,"Browser")); // 1 - Chrome, 2 - Edge, 3 - FF, 4 - Safari
	public String sURL = PropertyFileUtil.readDataFromPropertyFile(propFileName,"URL");
	public String excelFileName = "";
	
	@BeforeClass
	public void invokeBrowser() {
		switch (browser) {
		case 1:
			System.out.println("User option is : "+browser+ ", So invoking chrome browser");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is : "+browser+ ", So invoking edge browser");
			driver = new EdgeDriver();
			break;
		case 3:
			System.out.println("User option is : "+browser+ ", So invoking firefox browser");
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("User option is wrong : "+browser+ ", So invoking default chrome browser");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	@AfterClass	
	public  void closeBrowser() {
		//driver.close();
		driver.quit();
	}
	
	@DataProvider(name = "ExcelData")
	public Object[][] setExcelData() {
		Object[][] values = null;
		try {
			values = DataProviderObject.getValue(excelFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
