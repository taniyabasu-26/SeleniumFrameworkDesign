package Automation.PageObjects;

import Automation.AbstructComponents.AbstructComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstructComponents {
	
	WebDriver driver;	
	
	//creating the constructor of the class
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//declaring the locators for country dropdown
	@FindBy(css="[placeholder='Select Country']")
	private	 WebElement country;
	
	//declaring the locator for selected country
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	private WebElement selectCountry;
	
	//declaring the locator for submit button
	@FindBy(css=".action__submit")
	private WebElement submit;	
	
	//declaring the locator for the country list 
	By result =  By.cssSelector(".ta-results");
		
	//action to select the country
	public void selectCountry(String countryName) {
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();		
		elementToApper(result);
		selectCountry.click();
		}
	
	//placing the order
	public SubmitPage submitOrder() {
		submit.click();
		//creating the object of SubmitPage class
		return new SubmitPage(driver);
	}

	
}
