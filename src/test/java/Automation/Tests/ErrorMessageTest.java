package Automation.Tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.PageObjects.CartPage;
import Automation.PageObjects.CheckoutPage;
import Automation.PageObjects.LoginPage;
import Automation.PageObjects.ProductCataloge;
import Automation.PageObjects.SubmitPage;
import Automation.TestComponents.BaseTest;
import Automation.TestComponents.RetryTest;

public class ErrorMessageTest extends BaseTest {	
	
	//to re-run the failed test case
	@Test(groups= {"ErrorHandleing"}, retryAnalyzer = RetryTest.class)
	public void loginErrorCheck() throws IOException {	
		
		String email = "rajiv009@yomail.com";
		String password = "Rajiv@123";
		//String countryName = "india";*/
		
		//logging with invalid email and password
		loginpage.loginActions(email, password);
		//Verifying the error message
		Assert.assertEquals("Incorrect email or password.", loginpage.getLoginErrorMsg());
	}
	
	@Test
	public void productErrorCheck() {
		
		String email = "taniya09@yomail.com";
		String password = "Tani@2613";
		String productName = "ADIDAS ORIGINAL";
		
		ProductCataloge productCataloge = loginpage.loginActions(email, password);
		//getting the list of the products
		List<WebElement> products = productCataloge.getProductsList();
		//adding products to cart
		productCataloge.addProductToCart(productName);
		//going to cart page
		CartPage cartPage = productCataloge.goToCartPage();		
		//checking the added product is correct or not
		Boolean match = cartPage.verifyProductDisplay("FliKart");			
		Assert.assertFalse(match);
		
		
		
		/*/logging to the site
		
		//going to checkout page		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		//selecting the country
		checkoutPage.selectCountry(countryName);
		//submitting the order
		SubmitPage submitPage = checkoutPage.submitOrder();
		//verifying the confirmation message
		String message = submitPage.getConfirmationMsg();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));*/
		
	}

}
