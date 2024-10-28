package testscenarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import libraries.FakerDataFactory;
import pages.LoginPage;

public class TC02_CreateLeads extends BaseClass{
	
	
	
	@Test(priority = 1)
	public void createSalesLeadWithmandatoryFields() {
		boolean result = new LoginPage()
		.enterUserName("mathan@credosystemz.sanbox")
		.enterPassword("Mylearning$4")
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
