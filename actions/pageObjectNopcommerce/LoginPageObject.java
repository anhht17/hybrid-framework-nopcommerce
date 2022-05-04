package pageObjectNopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIsNopcommerce.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void inputToEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, invalidEmail);	
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, LoginPageUI.UNCCESSFUL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNCCESSFUL_ERROR_MESSAGE);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementsVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);	
	}	
}
