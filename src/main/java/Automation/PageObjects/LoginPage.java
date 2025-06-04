package Automation.PageObjects;

import Automation.AbstructComponents.AbstructComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstructComponents {
	
	WebDriver driver;	
	
	//creating the constructor of the class
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//declaring the locators for login
	@FindBy(id="userEmail")
	private WebElement userEmail;
	@FindBy(id="userPassword")
	private WebElement userPassword;
	@FindBy(id="login")
	private WebElement submit;
	
	//declaring locator for Login Error Message
	@FindBy(css="[class*='flyInOut']")
	private WebElement errorMsg;
	
	//setting the URL
	public void url() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	//declaring the actions for login
	public ProductCataloge loginActions(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		//creating the object of ProductCataloge class
		return new ProductCataloge(driver);
	}
	public String getLoginErrorMsg() {
		webElementToApper(errorMsg);
		return errorMsg.getText();
		
	}

		
}
