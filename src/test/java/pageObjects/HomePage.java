package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@id='login_Layer']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[contains(@placeholder,'Enter your active Email ID / Username')]")
	WebElement txtUsername;
	
	@FindBy(xpath="//input[@placeholder='Enter your password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement btnLogin;
	
	public void clicklnkLogin() {
		lnkLogin.click();
	}
	
	public void setEmail(String email) {
		txtUsername.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
}
