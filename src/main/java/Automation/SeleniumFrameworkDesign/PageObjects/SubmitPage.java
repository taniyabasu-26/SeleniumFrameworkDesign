package Automation.SeleniumFrameworkDesign.PageObjects;

import Automation.AbstructComponents.AbstructComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitPage extends AbstructComponents {
	
	WebDriver driver;
	
	//creating the constructor of the class
		public SubmitPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		//declaring the locator for confirmation message
		@FindBy(className="hero-primary")
		WebElement confirmationMSG;
		
		public String getConfirmationMsg() {			
			return confirmationMSG.getText();
		}

}
