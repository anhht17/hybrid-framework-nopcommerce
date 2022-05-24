package pageUIsNopcommerce;

public class MyAccountPageUI {
	public static final String FIRSTNAME_TEXTBOX = "xpath=//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "xpath=//input[@id='LastName']";
	public static final String BIRTHDAY = "xpath=//select[@name='DateOfBirthDay']";
	public static final String BIRTHMONTH = "xpath=//select[@name='DateOfBirthMonth']";
	public static final String BIRTHYEAR = "xpath=//select[@name='DateOfBirthYear']";
	public static final String EMAIL_TEXTBOX = "xpath=//input[@id='Email']";
	public static final String CHANGE_PASSWORD_TAB = "xpath=//a[text()='Change password']";
	public static final String OLD_PASSWORD_TEXTBOX = "xpath=//input[@id='OldPassword']";
	public static final String NEW_PASSWORD_TEXTBOX = "xpath=//input[@id='NewPassword']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "xpath=//input[@id='ConfirmNewPassword']";
	public static final String CHANGE_PASSWORD_BUTTON = "xpath=//button[text()='Change password']";
    public static final String SAVE_INFOR_BUTTON = "xpath=//button[@id='save-info-button']";
    
    public static final String FIRSTNAME_ERROR_MESSAGE = "xpath=//span[@id='FirstName-error']";
    public static final String LASTNAME_ERROR_MESSAGE = "xpath=//span[@id='LastName-error']";
    public static final String EMAIL_ERROR_MESSAGE = "xpath=//span[@id='Email-error']";
    public static final String OLD_PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='OldPassword-error']";
    public static final String NEW_PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='NewPassword-error']";
    public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='ConfirmNewPassword-error']";
}