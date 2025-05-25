package Automation.PageObjects;

import Automation.AbstructComponents.AbstructComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends AbstructComponents {
	
	WebDriver driver;	
	
	//creating the constructor of the class
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//declaring the locators for cart products list
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	//declaring the locator for checkout button
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
		
	//declaring the locator to add to cart
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	//declaring the locator for toast message
	By toastMsg = By.id("toast-container");
	
	//get the cart product list
	public Boolean verifyProductDisplay(String productName) {
			Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));				
			return match;			
		}
	
	//go to checkout page
	public CheckoutPage goToCheckout() {
		checkout.click();
		//creating the object of CheckoutPage class
		return new CheckoutPage(driver);
	}
		
				
		

	
}
