package com.hudl.steps.pages;


import com.hudl.testng.base.ProjectSpecificMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends ProjectSpecificMethods {
	public LoginPage() {
		verifyTitle("Log In");
	}
	
	@Given("Enter the valid email")
	public LoginPage enterUserName() {
		clearAndType(locateElement("id","email"), usermail);
		reportStep("The username "+usermail+ " is entered","Pass");
		return this;	
	}
	
	@Given("Enter the valid password")
	public LoginPage enterPassword() {
		clearAndType(locateElement("id","password"), password);
		reportStep("The username "+password+ " is entered","Pass");
		return this;	
	}
	
	@Given("Enter the invalid email {string}")
	public LoginPage enterInvalidUserName(String email) {
		clearAndType(locateElement("id","email"), email);
		reportStep("The username "+email+ " is entered","Pass");
		return this;	
	}
	
	@Given("Enter the invalid password {string}")
	public LoginPage enterInvalidPassword(String pwd) {
		clearAndType(locateElement("id","password"), pwd);
		reportStep("The username "+pwd+ " is entered","Pass");
		return this;	
	}
	
	@When("Click on the login")
	public HomePage clickLogin() {
		click(locateElement("xpath","//button[text()='Log In']"));
		reportStep("The login button is clicked","Pass");
		return new HomePage();
	}
	
	@Given("The login page should display error with fail status")
	public LoginPage userCredentialsCheck() {
		getElementText(locateElement("xpath","//p[contains(text(),' recognize that email')]"));
		reportStep("The error message is obatined when invalid credentials are provided","Pass");
		return this;
	}
	
	@Given("Clicks on the link {string}")
	public void clickLink(String link) {
		click(locateElement("xpath","//*[text()='"+link+"']"));
		reportStep("The "+link+ " is clicked","Pass");
	}
	
	@Then("The user should navigate to the desired page {string}")
	public void desiredPage(String url) {
		verifyUrl(url);
		reportStep("The desired page is obatined","Pass");
	}
	
	@Given("Clicks on the back arrow in the login page")
	public WebsitePage backArrowToWebsitePage() {
		click(locateElement("xpath","//*[text()='Sign up']/preceding::a"));
		reportStep("The user landed on the Hudl website page","Pass");
		return new WebsitePage();
	}
	
	@Given("The user clicks on the Hudl logo in the login page")
	public WebsitePage logoClickToWebsitePage() {
		click(locateElement("xpath","//*[text()='Sign up']/following::a"));
		reportStep("The user landed on the Hudl website page","Pass");
		return new WebsitePage();
	}

}
