package org.vtiger.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.tyss.genericUtility.BaseClassVTiger;
import org.vtiger.objectRepository.ContactsPage;

public class ContactsTest extends BaseClassVTiger {
	@Test
	public void CreateContactTest() {
		String expectedLastName =excelUtility.getDataFromExcel("contact",2,1)+randomNumber;
		commonpage.clickContactAction();
		ContactsPage contactsPage=new ContactsPage(driver);
		contactsPage.createContact(expectedLastName);
		String actualLastName =contactsPage.getActualContactLastname();
		Assert.assertEquals(actualLastName, expectedLastName);
		
		
	}
}
