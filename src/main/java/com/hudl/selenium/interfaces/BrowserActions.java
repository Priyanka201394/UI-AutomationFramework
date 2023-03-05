package com.hudl.selenium.interfaces;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


public interface BrowserActions {
	/**
	 * This method will launch the Any browser and 
	 * maximise the browser and set the wait for 30 seconds 
	 * and load the url
	 * @param browser - This will load the specified browser
	 * @param url - This will load the specified url  
	 * @return 
	 */

	public RemoteWebDriver startApp(String browser, String url);
	
	/**
	 * This method will locates all matching element using any given locator
	 * @param locatorType  - The locator by which the element to be found
	 * @param locValue - The locator value by which the element to be found
	 */
	public WebElement locateElement(String locatorType, String value);	
	/**
	 * This method will verify browser actual url with expected
	 * @param url   - The url to be checked
	 */
	public boolean verifyUrl(String url);
	
	/**
	 * This method will verify browser actual title with expected
	 * @param title - The expected title of the browser
	 */
	public boolean verifyTitle(String title);
	/**
	 * This method will close the active browser
	 */
	public void close();
	
	/**
	 * This method will close all the browsers
	 */
	public void quit();

}
