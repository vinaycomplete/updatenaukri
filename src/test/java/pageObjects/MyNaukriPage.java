package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyNaukriPage extends BasePage {
	
	public MyNaukriPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//img[@alt='naukri user profile img']")
	WebElement lnkIcon;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement lnkLogout;
	
	
	public void clickIcon() {
		
		lnkIcon.click();
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}

}
