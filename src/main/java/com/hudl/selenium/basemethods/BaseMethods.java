package com.hudl.selenium.basemethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hudl.selenium.interfaces.BrowserActions;
import com.hudl.selenium.interfaces.ElementActions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Reporter;

public class BaseMethods extends Reporter implements BrowserActions , ElementActions  {
	public String usermail,websiteUrl,password,headlessBrowser , browser;
	public BaseMethods() {
		Properties prop = new Properties();
		try {
			
			prop.load(new FileInputStream(new File("./src/main/java/config.properties")));
			usermail = prop.getProperty("useremail");
			websiteUrl = prop.getProperty("WebsiteUrl");
			password = prop.getProperty("password");
			headlessBrowser = prop.getProperty("Headless");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static RemoteWebDriver driver;


	public RemoteWebDriver startApp(String browser,  String url , String headless) {
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.silentOutput", "true");
				if(headless.equals("true")) {
					ChromeOptions opt = new ChromeOptions();
					opt.addArguments("--headless");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(opt);
				}else {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				}
			} else if(browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.silentOutput", "true");
				if(headless.equals("true")) {
					EdgeOptions opt = new EdgeOptions();
					opt.addArguments("--headless");
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver(opt);
				}else {
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
				}
			}
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (Exception e) {
			reportStep("The Browser Could not be Launched. Hence Failed", "fail");
			throw new RuntimeException();
		} 
		return driver;
	}

	@Override
	public WebElement locateElement(String locatorType, String value) {
		try {
			switch(locatorType.toLowerCase()) {
			case "id": return driver.findElement(By.id(value));
			case "name": return driver.findElement(By.name(value));
			case "class": return driver.findElement(By.className(value));
			case "link": return driver.findElement(By.linkText(value));
			case "xpath": return driver.findElement(By.xpath(value));
			}
			
		}catch(NoSuchElementException e) {
			reportStep("The Element with locator: "+locatorType+" is not found with value: "+value, "fail");
			throw new RuntimeException();
		}
		return null;
	}

	public boolean verifyUrl(String url) {
		if(driver.getCurrentUrl().equals(url)) {
			reportStep("The url: "+url+" matched successfully","Pass" );
			return true;
		}else {
			System.out.println("The url: "+url+ " did not match");
		}
		return false;
	}

	public boolean verifyTitle(String title) {
		if(driver.getTitle().equals(title)) {
			reportStep("The Page title: "+title+ " is matched ","Pass");
		}else {
			reportStep("The Page title: "+title+ " did not match","Fail");
		}
		return false;
	}

	public void close() {
		driver.close();
		
	}

	public void quit() {
		driver.quit();
		
	}

	public void click(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement until = wait.until(ExpectedConditions.elementToBeClickable(ele));
			String text = until.getText();
			ele.click();
			reportStep("The element " +text+ " is clicked","Pass");
		}catch(StaleElementReferenceException e) {
			reportStep("The element "+ele+ " is not clickable","Fail");
			throw new RuntimeException();
		}
		
	}

	public void type(WebElement ele, String data) {
		try {
			if(ele.isDisplayed()) {
				ele.sendKeys(data);
				reportStep("The data "+data+ " is typed","Pass");
			}
		}catch(NoSuchElementException e) {
			reportStep("The element "+ele+ " no found","Fail");
			throw new RuntimeException();
			}
		}
		

	public void clear(WebElement ele) {
		try {
			ele.clear();
			reportStep("The element "+ele+ " is cleared","Pass");
		}catch(ElementNotInteractableException e) {
			reportStep("The element "+ele+ " is not interactable","Fail");
			throw new RuntimeException();
		}
		
	}

	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The data "+data+ " is cleared and entered","Pass");
		}catch(NoSuchElementException e) {
			reportStep("The element "+ele+ " is not found","Fail");
			throw new RuntimeException();
		}
		
	}

	public String getElementText(WebElement ele) {
		String text="";
		try {
			text = ele.getText();
			reportStep("The text "+text+ " obtained","Pass");
		}catch(NoSuchElementException e) {
			reportStep("The element "+ele+ " is not found","Fail");
			throw new RuntimeException();
		}
		return text;
	}

	public String getTypedText(WebElement ele) {
		String attribute="";
		try {
			attribute = ele.getAttribute("value");
			reportStep("The atribute "+attribute+ " obtained","Pass");
		}catch(NoSuchElementException e) {
			reportStep("The element "+ele+ " is not found","Fail");
			throw new RuntimeException();
		}
		return attribute;
	}

	public boolean verifyDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				reportStep("The element "+ele+" is visible","pass");
				return true;
			} else {
				reportStep("The element "+ele+" is not visible","fail");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return false;

	}

	public boolean verifyDisappeared(WebElement ele) {
		return false;
	}

	public boolean verifyEnabled(WebElement ele) {
		try {
			if(ele.isEnabled()) {
				reportStep("The element "+ele+" is enabled","pass");
			}else {
				reportStep("The element "+ele+" is not enabled","pass");
			}
		}catch(WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}

	public boolean verifySelected(WebElement ele) {
		try {
			if(ele.isSelected()) {
				reportStep("The element "+ele+" is selected","pass");
			}else {
				reportStep("The element "+ele+" is not selected","pass");
			}
		}catch(WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	
	}

	public boolean verifyExactText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().equals(expectedText)) {
				reportStep("The expected text is same as the actual text "+expectedText,"Pass");
			}else {
				reportStep("The expected text is not same as the actual text "+expectedText,"Fail");
			}
		}catch(WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}

	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().contains(expectedText)) {
				reportStep("The expected text contains the actual text "+expectedText,"Pass");
			}else {
				reportStep("The expected text does not contain the actual text "+expectedText,"Fail");
			}
		}catch(WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}


	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if(ele.getAttribute(value).equals(attribute)) {
				reportStep("The expected attribute is same as the actual text "+attribute,"Pass");
			}else {
				reportStep("The expected attribute is not same as the actual text "+attribute,"Fail");
			}
		}catch(WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}

	
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L)+10000000L;
		try {
			File source = driver.getScreenshotAs(OutputType.FILE);
			File destination = new File("./reports/images/"+number+".jpg");
			FileUtils.copyFile(source, destination);
		}catch(IOException e) {
			System.out.println("The snapshot could not be taken");
		}catch(WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return number;
	}
	
	public void hoverAction(WebElement ele) {
		Actions builder = new Actions(driver);
		try {
			builder.moveToElement(ele).perform();
			reportStep("Hovered over element "+ele,"pass");
		}catch(NoSuchElementException e) {
			reportStep("The element "+ele+ " is not found","Fail");
		}
		
		
	}

}
