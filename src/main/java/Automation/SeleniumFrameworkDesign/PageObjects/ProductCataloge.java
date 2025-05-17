package Automation.SeleniumFrameworkDesign.PageObjects;

import Automation.AbstructComponents.AbstructComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductCataloge extends AbstructComponents {
	
	WebDriver driver;	
	
	//creating the constructor of the class
	public ProductCataloge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//declaring the locators for products list
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	//declaring the locator for loader
	@FindBy(css=".ng-animating")
	WebElement loader;
	
		
	//declaring the locator to add to cart
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	//declaring the locator for toast message
	By toastMsg = By.id("toast-container");
	
	//get the product list
	public List<WebElement> getProductsList() {		
		return products;
	}
	
	//declaring the actions to add product to cart
		public WebElement getProductByName(String productName) {
			WebElement myProduct = getProductsList().stream().filter(product-> 
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			return myProduct;			
		}
		
		public void addProductToCart(String productName) {
			WebElement myProduct = getProductByName(productName);
			if (myProduct == null) {
		        throw new RuntimeException("Product with name '" + productName + "' not found.");
		    }
		    
			myProduct.findElement(addToCart).click();
			elementToApper(toastMsg);
			elementToDisapper(loader);			
		}
		
		
		

	
}
