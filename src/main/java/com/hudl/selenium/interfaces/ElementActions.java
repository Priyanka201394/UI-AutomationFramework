 package com.hudl.selenium.interfaces;

import org.openqa.selenium.WebElement;



public interface ElementActions {
	
	/**
	 * This method will click the element and take snap
	 * @param ele  - The Webelement (button/link/element) to be clicked
	 */
	void click(WebElement ele);
	/**
	 * This method will enter the value in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered
	 * @param data  - The data to be sent to the webelement
	 */
	 void type(WebElement ele, String data);
	/**
	 * This method will clear the value in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered	 
	 */
	 void clear(WebElement ele);
	/**
	 * This method will clear and type the value in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered
	 * @param data  - The data to be sent to the webelement		 
	 */
	 void clearAndType(WebElement ele,String data);
	/**
	 * This method will get the visible text of the element
	 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
	 */
	 String getElementText(WebElement ele);	
	/**
	 * This method will get the text of the element textbox
	 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
	 * @see locateElement method in Browser Class
	 */
	 String getTypedText(WebElement ele);

	/**
	 * This method will verify if the element is visible in the DOM
	 * @param ele   - The Webelement to be checked
	 */
	boolean verifyDisplayed(WebElement ele);
	/**
	 * This method will checking the element to be invisible
	 * @param ele   - The Webelement to be checked
	 */
	 boolean verifyDisappeared(WebElement ele);	
	/**
	 * This method will verify if the input element is Enabled
	 * @param ele   - The Webelement (Radio button, Checkbox) to be verified
	 */
	boolean verifyEnabled(WebElement ele);	
	
	/**
	 * This method will verify if the element (Radio button, Checkbox) is selected
	 * @param ele   - The Webelement (Radio button, Checkbox) to be verified
	 */
	boolean verifySelected(WebElement ele);
	
	/**
	 * This method will verify exact given text with actual text on the given element
	 * @param ele   - The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 */
	 boolean verifyExactText(WebElement ele, String expectedText);
	
	/**
	 * This method will verify given text contains actual text on the given element
	 * @param ele   - The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 */
	 boolean verifyPartialText(WebElement ele, String expectedText);
	 /**
	 * This method will verify exact given attribute's value with actual value on the given element
	 * @param ele   - The Webelement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 */
	 boolean verifyExactAttribute(WebElement ele, String attribute, String value);
	 
	 
	 void hoverAction(WebElement ele);
	 
		
		
}
