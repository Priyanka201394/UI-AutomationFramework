package utils;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public abstract class Reporter extends AbstractTestNGCucumberTests{
	
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public String testcase , testdescription , author;
	public String excelFileName;
	
	@BeforeSuite
	public void beginReport() {
		reporter = new ExtentHtmlReporter("./reports/result.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeClass
	public void report() {
		test = extent.createTest(testcase, testdescription);
		
	}
	public abstract long takeSnap();
    public void reportStep(String dec, String status ) {
    	MediaEntityModelProvider img = null;
		if(true && !status.equalsIgnoreCase("INFO")){
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						("./../reports/images/"+takeSnap()+".jpg").build();
			} catch (IOException e) {
				
			}
		}
    	if(status.equalsIgnoreCase("Pass")) {
    		test.pass(dec, img);
    	} else if(status.equalsIgnoreCase("Fail")) {
    		test.fail(dec, img); 
    	}
    }
    
    @AfterSuite
   public void endReport(){
	   extent.flush();
   }
	

}
