package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjectNopcommerce.HomePageObject;
import pageObjectNopcommerce.LoginPageObject;
import pageObjectNopcommerce.RegisterPageObject;

public class Level_03_Login_Apply_Page_Object_Pattern_I {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, validEmail, invalidEmail, notFoundEmail, password, wrongPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
		firstName = "TamAnh";
		lastName = "Hoang";
		invalidEmail = "hoang@gmail@123";
		notFoundEmail = "tamanh" + generateFakeNumber() + "@gmail123.com";
		validEmail = "tamanh" + generateFakeNumber() + "@gmail.com";
		password = "123456";
		wrongPassword = "1234567";
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();		
		registerPage = new RegisterPageObject(driver);
		
		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox(validEmail);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		registerPage.clickToLogoutLink();
		loginPage = new LoginPageObject(driver);
	}
	
	@Test
	public void Login_01_Empty_Data() {
		homePage.clickToLoginLink();
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Login_04_Existing_Email_And_Empty_Password() {
		homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_And_Wrong_Password() {
		homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(wrongPassword);
		loginPage.clickToLoginButton();
		
			Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_Success() {
		homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
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
