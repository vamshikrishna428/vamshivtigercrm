package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	@FindBy(xpath = "//img[@alt='Create Campaign...']")
	private WebElement 	createCampaignBtn;
	@FindBy(xpath = "//input[@name='campaignname']")
	private WebElement 	campaignNameTextField;
	@FindBy(xpath = "//input[@class='crmButton small save']")
	private WebElement saveButton;
	public CampaignsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void clickingCreateCampaignAction() {
		createCampaignBtn.click();
	}
	public void createNewCampaignAction(String expectedCampaignName) {
		createCampaignBtn.click();
		campaignNameTextField.sendKeys(expectedCampaignName);
		saveButton.click();

	}
}
