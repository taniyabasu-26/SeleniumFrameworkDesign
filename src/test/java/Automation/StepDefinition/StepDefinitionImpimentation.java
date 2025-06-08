package Automation.StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import Automation.PageObjects.CartPage;
import Automation.PageObjects.CheckoutPage;
import Automation.PageObjects.LoginPage;
import Automation.PageObjects.ProductCataloge;
import Automation.PageObjects.SubmitPage;
import Automation.TestComponents.BaseTest;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class StepDefinitionImpimentation extends BaseTest {
	
	String countryName = "india";
	public LoginPage loginpage;
	public ProductCataloge productCataloge; 
	public SubmitPage submitPage ;
	
	@Given("I landend on Ecommerce Page")
	public void i_landend_on_Ecommerce_Page() throws IOException {
		loginpage = launchApplication();
	}
	
	@Given("^Logged in with email (.+) and password (.+)$")
	public void logged_in_with_email_and_password(String email, String password) {
		productCataloge = loginpage.loginActions(email, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCataloge.getProductsList();
		productCataloge.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCataloge.goToCartPage();				
		Boolean match = cartPage.verifyProductDisplay(productName);			
		Assert.assertTrue(match);			
		CheckoutPage checkoutPage = cartPage.goToCheckout();		
		checkoutPage.selectCountry(countryName);			
		submitPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation Page")
	public void message_is_displayed_on_Confirmation_Page(String confirmMessage) {
		String message = submitPage.getConfirmationMsg();
		Assert.assertTrue(message.equalsIgnoreCase(confirmMessage));
		driver.close();
		}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String errorMessage){
		Assert.assertEquals(errorMessage, loginpage.getLoginErrorMsg());
		driver.close();
	}
	

}
