package com.hudl.steps.pages;


import com.hudl.testng.base.ProjectSpecificMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends ProjectSpecificMethods {
	public LoginPage() {
		verifyTitle("Log In");
	}
	
	@Given("Enter the email {string}")
	public LoginPage enterUserName(String data) {
		clearAndType(locateElement("id","email"), data);
		reportStep("The username "+data+ " is entered","Pass");
		return this;
		
	}
	
	@Given("Enter the password {string}")
	public LoginPage enterPassword(String data) {
		clearAndType(locateElement("id","password"), data);
		reportStep("The username "+data+ " is entered","Pass");
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
		System.out.println("//a[text()="+link+"]");
		click(locateElement("xpath","//*[text()='"+link+"']"));
		reportStep("The "+link+ " is clicked","Pass");
	}
	
	@Then("The user should navigate to the desired page {string}")
	public void desiredPage(String url) {
		verifyUrl(url);
		reportStep("The desired page is obatined","Pass");
	}

}
