package com.hudl.steps.pages;

import com.hudl.testng.base.ProjectSpecificMethods;

import io.cucumber.java.en.Given;

public class HomePage extends ProjectSpecificMethods {
	

	@Given("The Homepage should be displayed")
	public HomePage verifyPage() {
		verifyUrl("https://www.hudl.com/home");
		return this;
	}
	
	@Given("User must logout")
	public WebsitePage logout() {
		hoverAction(locateElement("xpath","//a[@id='notifications-link']/parent::div/following-sibling::div"));
		click(locateElement("xpath","(//span[text()='Log Out'])[1]"));
		return new WebsitePage();
	}
}
