package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Level_01_Register_DRY {
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
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Tam Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Hoang");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("anhht.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Tam Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Hoang");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAdress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-logout")).click();
	}
	
	@Test
	public void TC_04_Register_Existed_Email() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Tam Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Hoang");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Tam Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Hoang");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAdress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_05_Register_Invalid_Confirm_Password() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Tam Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Hoang");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAdress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("1234567");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
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
