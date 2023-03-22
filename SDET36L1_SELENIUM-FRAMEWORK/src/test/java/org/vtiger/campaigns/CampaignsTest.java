package org.vtiger.campaigns;

import org.testng.annotations.Test;
import org.tyss.genericUtility.BaseClassVTiger;
import org.vtiger.objectRepository.CampaignInformationPage;
import org.vtiger.objectRepository.CampaignsPage;
import org.vtiger.objectRepository.CommonPage;

public class CampaignsTest extends BaseClassVTiger {
	@Test
	public void CreateCampaignTest() {
		String expectedCampaignName =excelUtility.getDataFromExcel("campaign", 2, 1)+randomNumber;
		CommonPage commonPage=new CommonPage(driver);
		commonPage.mousehoverToMoreLinkAction(webDriverUtility);
		commonPage.clickCampaignAction();
		CampaignsPage campaignPage=new CampaignsPage(driver);
		campaignPage.createNewCampaignAction(expectedCampaignName);
		CampaignInformationPage campaignInformationPage=new CampaignInformationPage(driver);
		String actualCampaignName =campaignInformationPage.getCampaignName();
		if(actualCampaignName.equals(expectedCampaignName))
			javaUtility.print("Campaign name is matching-->Testcase Passed");
		else
			javaUtility.print("Campaign name is not matching-->Testcase Failed");
		
	}
	
	public void CreateCampaignWithProductTest() {
		String expectedProductName=excelUtility.getDataFromExcel("campaign", 4, 1)+randomNumber;
		String expectedCampaignName=excelUtility.getDataFromExcel("campaign", 4, 2)+randomNumber;
		
		
	}

}
