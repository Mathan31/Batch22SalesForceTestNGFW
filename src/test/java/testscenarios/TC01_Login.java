package testscenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC01_Login extends BaseClass{
	
	@BeforeTest
	public void testScenarioSetup() {
		excelFileName = "TC01";
		authors = "Anitha";
		category = "Smoke";
		testName = "Salesforce Login";
		testDescription = "Validate all the mandatory fields and login with valid and invalid credential";
		testModule = "Login";
	}
	
	@Test(priority = 1)
	public void loginFieldValidation() {
		boolean result = new LoginPage(driver,node)
		.verifyLoginElements();
		Assert.assertEquals(result, true);
	}
	
	@Test(priority = 2,dataProvider = "ExcelData")
	public void loginWithValidCredential(String userName,String password) {
		System.out.println("Username is : "+userName);
		System.out.println("Password is : "+password);
		boolean result = new LoginPage(driver,node)
		.enterUserName(userName)
		.enterPassword(password)
		.clickOnLogin()
		.verifyHomeElement()
		.clickUserImg()
		.clickLogout()
		.verifyLoginElements();
		Assert.assertEquals(result, true);
	}
	
	@Test(priority = 3)
	public void loginWithInValidCredential() {
		boolean result = new LoginPage(driver,node)
		.enterUserName("mathan@credosystemz.sanbox")
		.enterPassword("Mylearning$5")
		.clickOnLoginWithInvalidCredential()
		.validateErrorMsg();
		Assert.assertEquals(result, true);
	}


}
