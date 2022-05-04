package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectNopcommerce.HomePageObject;
import pageObjectNopcommerce.LoginPageObject;
import pageObjectNopcommerce.PageGeneratorManager;
import pageObjectNopcommerce.RegisterPageObject;

public class Level_06_Login_Apply_Page_Generator_Manager extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, validEmail, invalidEmail, notFoundEmail, password, wrongPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		driver.get("https://demo.nopcommerce.com/");
		
		firstName = "TamAnh";
		lastName = "Hoang";
		invalidEmail = "hoang@gmail@123";
		notFoundEmail = "tamanh" + generateFakeNumber() + "@gmail123.com";
		validEmail = "tamanh" + generateFakeNumber() + "@gmail.com";
		password = "123456";
		wrongPassword = "1234567";
		
		homePage.clickToRegisterLink();		
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox(validEmail);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		registerPage.clickToLogoutLink();
		loginPage = PageGeneratorManager.getLoginPage(driver);
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
}
