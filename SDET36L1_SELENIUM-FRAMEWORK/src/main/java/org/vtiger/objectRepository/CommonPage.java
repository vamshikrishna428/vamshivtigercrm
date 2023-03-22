package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tyss.genericUtility.WebDriverUtility;

public class CommonPage {
	@FindBy(xpath = "//a[.='Contacts']")
	private WebElement contactLink;
	@FindBy(xpath = "//a[.='Sign Out']")
	private WebElement logoutLink;
	@FindBy(xpath = "//a[@name='Campaigns']")
	private WebElement campaignLink;
	@FindBy(xpath = "//a[.='More']")
	private WebElement moreLink;
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminstrationIcon;
	@FindBy(xpath="//a[.='Organizations']")
	private WebElement organizationLink;
	@FindBy(xpath="//a[.='Products']")
	private WebElement productLink;


	public CommonPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	public void clickContactAction() {
		contactLink.click();
	}
	public void logoutAction(WebDriverUtility webDriverUtility) {
		webDriverUtility.mouseHoverToElement(adminstrationIcon);
		logoutLink.click();
	}
	public void clickCampaignAction() {
		campaignLink.click();
	}
	public void mousehoverToMoreLinkAction(WebDriverUtility webDriverUtility) {
		webDriverUtility.mouseHoverToElement(moreLink);
	}
	public void clickOrganizationAction() {
		organizationLink.click();
	}
	public void clickProductAction() {
		productLink.click();
	}
}
