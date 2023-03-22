package org.vtiger.products;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.tyss.genericUtility.BaseClassVTiger;
import org.vtiger.objectRepository.ProductsPage;

public class ProductsTest extends BaseClassVTiger{
	@Test
      public void CreateProductTest() {
    	  String expectedProductName =excelUtility.getDataFromExcel("product",2,1)+randomNumber;
    	  commonpage.clickProductAction();
    	  ProductsPage productsPage=new ProductsPage(driver);
    	  productsPage.createProductAction(expectedProductName);
    	  String actualProductName=productsPage.getActualProductName();
    	  Assert.assertEquals(actualProductName, expectedProductName);
      }
}
