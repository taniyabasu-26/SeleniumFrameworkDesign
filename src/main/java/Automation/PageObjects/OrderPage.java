package Automation.PageObjects;

import Automation.AbstructComponents.AbstructComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class OrderPage extends AbstructComponents {
	
	WebDriver driver;	
	
	//creating the constructor of the class
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//declaring the locators for cart products list
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	//get the cart product list
	public Boolean verifyOrderDisplay(String productName) {
			Boolean match = productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));				
			return match;			
		}	
}
