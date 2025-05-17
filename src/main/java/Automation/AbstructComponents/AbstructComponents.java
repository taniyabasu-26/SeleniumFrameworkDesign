package Automation.AbstructComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.SeleniumFrameworkDesign.PageObjects.CartPage;
import Automation.SeleniumFrameworkDesign.PageObjects.OrderPage;

public class AbstructComponents {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//creating the constructor for the parent class-AbstructComonents class 
	public AbstructComponents(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
	}
	
	//declaring the locator for cart button 
		@FindBy(css="[routerlink*='cart']")
		WebElement cartHeader;
		//declaring the locator for order button
		@FindBy(css="[routerlink*='myorders']")
		WebElement orderHeader;

	//creating method for the elements to be visible
	public void elementToApper(By findBy) {
		//giving explicite wait to load the pages	
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	//creating method for the WebElements to be visible
	public void webElementToApper(WebElement findBy) {
		//giving explicite wait to load the pages	
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	//creating method for the elements to be invisible
	public void elementToDisapper(WebElement spinner){
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}
	
	//declaring the actions to go to cart page
	public CartPage goToCartPage() {
		cartHeader.click();
		//creating the object of CartPage class
		return new CartPage(driver);
	}
	
	//declaring the actions to go to orders page
		public OrderPage goToOrderPage() {
			orderHeader.click();
			//creating the object of CartPage class
			return new OrderPage(driver);
		}

}
