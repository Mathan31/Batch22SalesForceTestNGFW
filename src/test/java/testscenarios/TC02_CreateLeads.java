package testscenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import libraries.FakerDataFactory;
import pages.LoginPage;

public class TC02_CreateLeads extends BaseClass{
	
	@BeforeTest
	public void testScenarioSetup() {
		excelFileName = "TC02";
		authors = "Kashmira";
		category = "Sanity";
		testName = "Salesforce Leads Creation";
		testDescription = "Validate all the mandatory fields and create a new lead";
		testModule = "Lead Creations";
		
	}
	
	@Test(priority = 1,dataProvider = "ExcelData")
	public void createSalesLeadWithmandatoryFields(String userName,String password) {
		boolean result = new LoginPage(driver,node)
		.enterUserName(userName)
		.enterPassword(password)
		.clickOnLogin()
		.verifyHomeElement()
		.clickOnAppLauncher()
		.clickOnViewAll()
		.clickOnSales()
		.clickOnLeadsLink()
		.clickOnNewButton()
		.enterLastName(FakerDataFactory.getLastName())
		.enterCompanyName(FakerDataFactory.getCompanyName())
		.clickAndSelectLeadStatus()
		.clickOnSaveButton()
		.clickUserImg()
		.clickLogout()
		.verifyLoginElements();
		
		Assert.assertTrue(result);
	}

}
