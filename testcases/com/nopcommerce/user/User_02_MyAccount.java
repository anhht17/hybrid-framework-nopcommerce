package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import  static org.testng.Assert.assertEquals;
import  static org.testng.Assert.assertTrue;
import  static org.testng.Assert.assertFalse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObjectNopcommerce.HomePageObject;
import pageObjectNopcommerce.MyAccountPageObject;
import pageObjectNopcommerce.PageGeneratorManager;
import pageObjectNopcommerce.RegisterPageObject;

public class User_02_MyAccount extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, validEmail, password, newPassword, wrongNewPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private MyAccountPageObject myAccountPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		driver.get("https://demo.nopcommerce.com/");
		
		firstName = "TamAnh";
		lastName = "Hoang";

		validEmail = "tamanh" + generateFakeNumber() + "@gmail.com";
		password = "123456";
		newPassword = "1234567";
		wrongNewPassword = "123123123";
	
		homePage.clickToRegisterLink();		
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		registerPage.inputFirstnameTextbox(firstName);
		registerPage.inputLastnameTextbox(lastName);
		registerPage.inputEmailTextbox(validEmail);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		myAccountPage = PageGeneratorManager.getMyAccount(driver);
	}
	
	public void TC_01_Update_Customer_Infor_Successfully() {
		myAccountPage.clickToMyAccountButton();
		
		myAccountPage.inputToFirstNameTextbox("Anhhh");
		myAccountPage.inputToLastNameTextbox("Tammm");
		myAccountPage.inputToEmailTextbox("anhht1111@gmail.com");
		myAccountPage.clickToSaveUpdate();
		
		verifyEquals(myAccountPage.getTextInFirstNameTextbox(), "Anhhh");
	}
	
	
	public void TC_02_Update_Address_Of_Customer_Successfully() {
	
	}
	
	@Test
	public void TC_03_Show_Error_Mesage_When_PassWord_Not_Match() {
		
		log.info("Step 01: Click Button My Account");
		myAccountPage.clickToMyAccountButton();
		
		log.info("Step 02: Click To Move Change Password Tab");
		myAccountPage.clickToMoveToChangePassWordTab();
		
		log.info("Step 03: Enter old pasword in textbox with value is '"+ password +"'");
		myAccountPage.inputToOldPasswordTextbox(password);
		
		log.info("Step 04: Enter new pasword in textbox with value is '"+ newPassword +"'");
		myAccountPage.inputToNewPasswordTextbox(newPassword);
		
		log.info("Step 05: Enter confirm pasword in textbox with value is '"+ wrongNewPassword +"'");
		myAccountPage.inputToConfirmPasswordTextbox(wrongNewPassword);
		
		log.info("Step 06: Click to save pasword");
		myAccountPage.clickToSavePassWord();
		
		log.info("Step 07: Verify message");
		verifyEquals(myAccountPage.getErrorMessageAtConfirmNewPasswordTextbox(), 
				"The new password and confirmation password do not match..");
	}
	
	
	public void TC_04_Add_Product_Successfully() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
