package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
 public OrganizationsPage(WebDriver driver) {
	 PageFactory.initElements(driver, this);
 }
 
 @FindBy(xpath="//img[@alt='Create Organization...']")
 private WebElement createOrganizationBtn;
 @FindBy(xpath="//input[@name='accountname']")
 private WebElement organizationNameTextbox;
 @FindBy(xpath="//input[@class='crmbutton small save']")
 private WebElement saveBtn;
 @FindBy(xpath="//span[@id='dtlview_Organization Name']")
 private WebElement actualOrganizationName;
 
 public void createOrganizationAction(String expectedOrganizationName) {
	 createOrganizationBtn.click();
	 organizationNameTextbox.sendKeys(expectedOrganizationName);
	 saveBtn.click();
 }
 
 public String getOrganizationName() {
	 return actualOrganizationName.getText();
 }
 
}
