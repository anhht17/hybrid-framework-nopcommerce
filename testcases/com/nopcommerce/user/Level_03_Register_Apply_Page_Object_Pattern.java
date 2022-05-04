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
import pageObjectNopcommerce.RegisterPageObject;

public class Level_03_Register_Apply_Page_Object_Pattern {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, emailAdress, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		
		firstName = "TamAnh";
		lastName = "Hoang";
		emailAdress = "tamanh" + generateFakeNumber() + "@gmail.com";
		password = "123456";
	}
	
	@Test
	public void Register_01_Empty_Data() {
		homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}
	
	@Test
	public void Register_02_Invalid_Email() {
		homePage.clickToRegisterLink();

		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox("anhht.com");
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	
	@Test
	public void Register_03_Success() {
		homePage.clickToRegisterLink();		
		
		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAdress);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		registerPage.clickToLogoutLink();
	}
	
	@Test
	public void Register_04_Existed_Email() {
		homePage.clickToRegisterLink();			
		
		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAdress);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}
	
	@Test
	public void Register_05_Password_Less_Than_6_Characters() {
		homePage.clickToRegisterLink();			
		
		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAdress);
		registerPage.inputPasswordTextbox("1234");
		registerPage.inputConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void Register_06_Invalid_Confirm_Password() {
		homePage.clickToRegisterLink();			
		
		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox(emailAdress);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox("2123456");
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
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
