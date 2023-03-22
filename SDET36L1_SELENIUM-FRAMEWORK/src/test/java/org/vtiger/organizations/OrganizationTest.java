package org.vtiger.organizations;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.tyss.genericUtility.BaseClassVTiger;
import org.vtiger.objectRepository.OrganizationsPage;

public class OrganizationTest extends BaseClassVTiger {
	@Test
	public void CreateOrganizationTest() {
		String expectedOrganizationName =excelUtility.getDataFromExcel("organization",2,1)+randomNumber;
		commonpage.clickOrganizationAction();
		OrganizationsPage organizationsPage=new OrganizationsPage(driver);
		organizationsPage.createOrganizationAction(expectedOrganizationName);
		String actualOrganizationName=organizationsPage.getOrganizationName();
		Assert.assertEquals(actualOrganizationName, expectedOrganizationName);
	}
}
