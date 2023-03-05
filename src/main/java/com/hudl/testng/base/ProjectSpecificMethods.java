package com.hudl.testng.base;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.hudl.selenium.basemethods.BaseMethods;

public class ProjectSpecificMethods extends BaseMethods{
	
	@Parameters({"browser"})
	@BeforeMethod
	public void initiateApp(String browser) {
		startApp(browser, "https://www.hudl.com/en_gb/");
		
	}
	
	@AfterMethod
	public void closeApp() {
		quit();
	}

}
