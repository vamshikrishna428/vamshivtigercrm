package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage {
	@FindBy(xpath = "//span[@id='dtlview_Campaign Name']")
	private WebElement 	verifycampaignNameTextField;


	public CampaignInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public String getCampaignName() {
		return verifycampaignNameTextField.getText().trim();
	}

}
