package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyProfile extends BasePage {

	public MyProfile(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[normalize-space()='View profile']")
	WebElement btnProfile;
	
	@FindBy(xpath="//em[contains(@class,'icon edit')]")
	WebElement imgEdit;
	
	@FindBy(xpath="//button[@id='saveBasicDetailsBtn']")
	WebElement btnSave;
	
	@FindBy(xpath="//span[text()='Today']")
	WebElement msgConfirmation;
	
	public void clickProfile() {
		btnProfile.click();
	}
	
	public void clickEdit() {
		wait.until(ExpectedConditions.elementToBeClickable(imgEdit)).click();
	}
	
	public void clickSave() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
	}
	
	public String getConfirmationMsg() {
		try {
		return (msgConfirmation.getText());
		} catch(Exception e) {
			return (e.getMessage());
		}
	}
	
}
