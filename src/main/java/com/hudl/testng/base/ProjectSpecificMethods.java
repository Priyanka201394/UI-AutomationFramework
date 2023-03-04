package com.hudl.testng.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.hudl.selenium.basemethods.BaseMethods;

public class ProjectSpecificMethods extends BaseMethods{
	
	@BeforeMethod
	public void initiateApp() {
		startApp("chrome", "https://www.hudl.com/en_gb/");
		
	}
	
	@AfterMethod
	public void closeApp() {
		close();
	}

}
