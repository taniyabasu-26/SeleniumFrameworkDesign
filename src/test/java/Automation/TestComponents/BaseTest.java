package Automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.PageObjects.LoginPage;


public class BaseTest {
	public WebDriver driver;
	public LoginPage loginpage;
	
	public WebDriver initializeDriver() throws IOException {		
			
		//creating the object of Properties class
		Properties properties = new Properties();
		//creating the object of FileInputStream class and assing the path of Global Properties file 
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Automation\\Resources\\GlobalData.properties");
		//parsing the data from Global Properties file
		properties.load(file);
		String browserName = System.getProperty("browser") != null
			    ? System.getProperty("browser")
			    : properties.getProperty("browser");
		//invoking the browser
		if(browserName.contains("chrome")) {
			//creating object of ChromeOptions class to run the browser in headless mode
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();			
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			//to open the browser window in full screen for headless mode
			driver.manage().window().setSize(new Dimension(1920,1080));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			return driver;
	}
	
	//creating method to scan the json data
		public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
			
			//convert json data to string
			File file = new File(filePath);
			String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			//convert String to Hashmap
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
			return data;
					
		}
		
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ss = (TakesScreenshot)driver;
		File source = ss.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName +"index.png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\" + testCaseName +"index.html";
		
	}
	
	//invoking the browser
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		//creating the object of LoginPage class
		loginpage = new LoginPage(driver);
		//Go to the URL
		loginpage.url();
		return loginpage;
		
	}
	
	//closing the browser
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}

}
