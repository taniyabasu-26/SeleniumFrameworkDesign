package Automation.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Automation.SeleniumFrameworkDesign.Resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;

	//calling the object of ExtentReportNG class
	ExtentReports extent = ExtentReportNG.getReportObject();
	
	//creating the object of ThreadLocal class
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();
	
	// Called when any test method starts
	public void onTestStart(ITestResult result) {
		//create entry of TestCases
		test = extent.createTest(result.getMethod().getMethodName());	
		//setting the object to ThreadLocal class
		extentTest.set(test);
	}

    // Called when a test method succeeds
	public void onTestSuccess(ITestResult result) {
		//declare success message for passed test cases
		extentTest.get().log(Status.PASS, "Test Passed");
	}

    // Called when a test method fails
    public void onTestFailure(ITestResult result) {
    	//getting the error message
    	extentTest.get().fail(result.getThrowable());
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//attach screenshot of the failed test cases
    	String filePath = null ;
    	try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {			
			e.printStackTrace();
		}    			
    	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    // Called when a test method is skipped
    public void onTestSkipped(ITestResult result) {}

    // Called when a test method fails but is within success percentage
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    // Called when a test fails due to a timeout
    public void onTestFailedWithTimeout(ITestResult result) {}

    // Called before any test starts (test tag in XML)
    public void onStart(ITestContext context) {}

    // Called after all tests are run (test tag in XML)
    public void onFinish(ITestContext context) {
    	extent.flush();
    }
	

}
