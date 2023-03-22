package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	 @FindBy(xpath = "//img[@alt='Create Contact...']")
	   private WebElement createContactBtn;
	 @FindBy(xpath = "//input[@name='lastname']")
	   private WebElement lastNameTextField;
	 @FindBy(xpath = "//input[@class='crmButton small save']")
	   private WebElement saveBtn;
	 @FindBy(xpath = "//span[@id='dtlview_Last Name']")
	   private WebElement actualContactLastname;
	 public ContactsPage(WebDriver driver) {
		 PageFactory.initElements(driver,this);
	 }
	 public void createContact(String expectedLastName) {
		 createContactBtn.click();
		 lastNameTextField.sendKeys(expectedLastName);
		 saveBtn.click();
	 }
	 public String getActualContactLastname() {
		return actualContactLastname.getText();
	 }
}
