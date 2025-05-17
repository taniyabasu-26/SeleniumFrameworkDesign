package Automation.SeleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Automation.SeleniumFrameworkDesign.PageObjects.CartPage;
import Automation.SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import Automation.SeleniumFrameworkDesign.PageObjects.OrderPage;
import Automation.SeleniumFrameworkDesign.PageObjects.ProductCataloge;
import Automation.SeleniumFrameworkDesign.PageObjects.SubmitPage;
import Automation.SeleniumFrameworkDesign.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	 String countryName = "india";	
	 HashMap<String, String> testInput;

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void placeOrder(HashMap<String,String> input) throws IOException {		
		
		this.testInput = input;
		//logging to the site
		ProductCataloge productCataloge = loginpage.loginActions(input.get("email"), input.get("password"));
		//getting the list of the products
		List<WebElement> products = productCataloge.getProductsList();
		//adding products to cart
		productCataloge.addProductToCart(input.get("productName"));
		//going to cart page
		CartPage cartPage = productCataloge.goToCartPage();		
		//checking the added product is correct or not
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));			
		Assert.assertTrue(match);
		//going to checkout page		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		//selecting the country
		checkoutPage.selectCountry(countryName);
		//submitting the order
		SubmitPage submitPage = checkoutPage.submitOrder();
		//verifying the confirmation message
		String message = submitPage.getConfirmationMsg();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	
	@Test(dependsOnMethods = {"placeOrder"})
	public void orderHistory() {
		//logging to the site
		ProductCataloge productCataloge = loginpage.loginActions(testInput.get("email"), testInput.get("password"));
		//going to order page
		OrderPage orderPage = productCataloge.goToOrderPage();
		//checking the ordered product is correct or not
		Assert.assertTrue(orderPage.verifyOrderDisplay(testInput.get("productName")));
				
	}	
	
	@DataProvider()
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\Automation\\SeleniumFrameworkDesign\\Datas\\dataProvider.json");
		return new Object[] [] {{data.get(0)}, {data.get(1)}};
		
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "taniya09@yomail.com");
		map.put("password", "Tani@2613");
		map.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "rajiv09@yopmail.com");
		map1.put("password", "Rajiv@123");
		map1.put("productName", "ZARA COAT 3");
		return new Object[] [] {{map},{map1}};*/
		//return new Object[] [] {{"taniya09@yomail.com","Tani@2613","ADIDAS ORIGINAL",}, {"rajiv09@yopmail.com","Rajiv@123","ZARA COAT 3",}};
	}

}
