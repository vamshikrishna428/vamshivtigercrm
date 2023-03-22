package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    public ProductsPage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement createProductBtn;
    @FindBy(xpath = "//input[@name='productname']")
	private WebElement productNameTextbox;
    @FindBy(xpath = "//input[@class='crmbutton small save']")
	private WebElement saveBtn;
    @FindBy(xpath = "//span[@id='dtlview_Product Name']")
	private WebElement actualProductName;
    
    public void createProductAction(String expectedProductName) {
    	createProductBtn.click();
    	productNameTextbox.sendKeys(expectedProductName);
    	saveBtn.click();
    }
    public String getActualProductName() {
    	return actualProductName.getText();
    	
    }
}
