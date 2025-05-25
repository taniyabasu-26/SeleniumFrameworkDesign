package Automation.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() {
		//creating the object of ExtentSparkReporter class to create the report and configure the report
				String path = System.getProperty("user.dir")+"\\reports\\index.html"; 
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				//configuring the report
				reporter.config().setReportName("Automation Testing");
				reporter.config().setDocumentTitle("Test Results");
				
				//Attach the report using ExtentReports class object
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Taniya");
				return extent;
	}
	

}
