package pageObjectNopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsNopcommerce.HomePageUI;
import pageUIsNopcommerce.LoginPageUI;
import pageUIsNopcommerce.MyAccountPageUI;

public class MyAccountPageObject extends BasePage{
	private WebDriver driver;
	
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePageObject clickToMyAccountButton() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, MyAccountPageUI.FIRSTNAME_TEXTBOX);
		sendkeysToElement(driver, MyAccountPageUI.FIRSTNAME_TEXTBOX, firstName);	
	}
	
	public String getTextInFirstNameTextbox() {
		waitForElementVisible(driver, MyAccountPageUI.FIRSTNAME_TEXTBOX);
		return getElementText(driver, MyAccountPageUI.FIRSTNAME_TEXTBOX);	
	}
	
	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, MyAccountPageUI.LASTNAME_TEXTBOX);
		sendkeysToElement(driver, MyAccountPageUI.LASTNAME_TEXTBOX, lastName);	
	}
	
	
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, MyAccountPageUI.EMAIL_TEXTBOX);
		sendkeysToElement(driver, MyAccountPageUI.EMAIL_TEXTBOX, email);	
	}
	
	public void clickToSaveUpdate() {
		waitForElementVisible(driver, MyAccountPageUI.SAVE_INFOR_BUTTON);
		clickToElement(driver, MyAccountPageUI.SAVE_INFOR_BUTTON);	
	}
	
	public void clickToSavePassWord() {
		waitForElementVisible(driver, MyAccountPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, MyAccountPageUI.CHANGE_PASSWORD_BUTTON);	
	}
	
	public void clickToMoveToChangePassWordTab() {
		waitForElementVisible(driver, MyAccountPageUI.CHANGE_PASSWORD_TAB);
		clickToElement(driver, MyAccountPageUI.CHANGE_PASSWORD_TAB);	
	}
	
	public void inputToOldPasswordTextbox(String oldPassword) {
		waitForElementVisible(driver, MyAccountPageUI.OLD_PASSWORD_TEXTBOX);
		sendkeysToElement(driver, MyAccountPageUI.OLD_PASSWORD_TEXTBOX, oldPassword);	
	}
	
	public void inputToNewPasswordTextbox(String newPassword) {
		waitForElementVisible(driver, MyAccountPageUI.NEW_PASSWORD_TEXTBOX);
		sendkeysToElement(driver, MyAccountPageUI.NEW_PASSWORD_TEXTBOX, newPassword);	
	}
	
	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, MyAccountPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeysToElement(driver, MyAccountPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);	
	}
	
	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, MyAccountPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getElementText(driver, MyAccountPageUI.FIRSTNAME_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, MyAccountPageUI.LASTNAME_ERROR_MESSAGE);
		return getElementText(driver, MyAccountPageUI.LASTNAME_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, MyAccountPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, MyAccountPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtOldPasswordTextbox() {
		waitForElementVisible(driver, MyAccountPageUI.OLD_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, MyAccountPageUI.OLD_PASSWORD_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtNewPasswordTextbox() {
		waitForElementVisible(driver, MyAccountPageUI.NEW_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, MyAccountPageUI.NEW_PASSWORD_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtConfirmNewPasswordTextbox() {
		waitForElementVisible(driver, MyAccountPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, MyAccountPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

}
