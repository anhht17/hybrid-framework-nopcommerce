package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Register_Apply_Base_Page_III extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAdress;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
				
		emailAdress = "tamanh" + generateFakeNumber() + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");		
		
		sendkeysToElement(driver,"//input[@id='FirstName']", "Tam Anh");
		sendkeysToElement(driver,"//input[@id='LastName']", "Hoang");
		sendkeysToElement(driver,"//input[@id='Email']", "anhht.com");
		sendkeysToElement(driver,"//input[@id='Password']", "123456");
		sendkeysToElement(driver,"//input[@id='ConfirmPassword']", "123456");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");		
		
		sendkeysToElement(driver,"//input[@id='FirstName']", "Tam Anh");
		sendkeysToElement(driver,"//input[@id='LastName']", "Hoang");
		sendkeysToElement(driver,"//input[@id='Email']", emailAdress);
		sendkeysToElement(driver,"//input[@id='Password']", "123456");
		sendkeysToElement(driver,"//input[@id='ConfirmPassword']", "123456");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		waitForElementClickable(driver, "//a[@class='ico-logout']");
		clickToElement(driver, "//a[@class='ico-logout']");
	}
	
	@Test
	public void TC_04_Register_Existed_Email() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");		
		
		sendkeysToElement(driver,"//input[@id='FirstName']", "Tam Anh");
		sendkeysToElement(driver,"//input[@id='LastName']", "Hoang");
		sendkeysToElement(driver,"//input[@id='Email']", "automationfc.vn@gmail.com");
		sendkeysToElement(driver,"//input[@id='Password']", "123456");
		sendkeysToElement(driver,"//input[@id='ConfirmPassword']", "123456");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");		
		
		sendkeysToElement(driver,"//input[@id='FirstName']", "Tam Anh");
		sendkeysToElement(driver,"//input[@id='LastName']", "Hoang");
		sendkeysToElement(driver,"//input[@id='Email']", emailAdress);
		sendkeysToElement(driver,"//input[@id='Password']", "1234");
		sendkeysToElement(driver,"//input[@id='ConfirmPassword']", "123456");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");		
		
		sendkeysToElement(driver,"//input[@id='FirstName']", "Tam Anh");
		sendkeysToElement(driver,"//input[@id='LastName']", "Hoang");
		sendkeysToElement(driver,"//input[@id='Email']", emailAdress);
		sendkeysToElement(driver,"//input[@id='Password']", "123456");
		sendkeysToElement(driver,"//input[@id='ConfirmPassword']", "1234567");
		
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
