package com.hudl.steps.pages;


import com.hudl.testng.base.ProjectSpecificMethods;

import io.cucumber.java.en.Given;

public class WebsitePage extends ProjectSpecificMethods {
	
	
	@Given("The user is on the Hudl website page")
	public WebsitePage hudlPageCheck() {
		verifyUrl(websiteUrl);
		reportStep("The user landed on the Hudl website page","Pass");
		return this;
		
	}
	
	@Given("The user navigates to the login page")
	public LoginPage clickLoginButton() {
		click(locateElement("xpath","//a[text()='Log in']"));
		reportStep("Navigated to the login page","Pass");
		return new LoginPage();
	}

}
