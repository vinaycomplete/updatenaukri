package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyNaukriPage;
import pageObjects.MyProfile;
import testBase.BaseClass;

public class TC001_UpdateProfile extends BaseClass{
	
	//public WebDriver driver;
	
	@Test
	public void verify_profile_update() {
		
	try
	{
		logger.info("******Start profile update******");
		HomePage hp=new HomePage(driver);
		
		MyProfile mp=new MyProfile(driver);
		MyNaukriPage np=new MyNaukriPage(driver);
		
		hp.clicklnkLogin();
		logger.info("Click on Login link");
		hp.setEmail("vinaychvk17@gmail.com");
		hp.setPassword("Vinay@0809");
		hp.clickLogin();		
		logger.info("Click on Login button");
		
		mp.clickProfile();
		logger.info("Clicked on View Profile  button");
		//Thread.sleep(2000);
		mp.clickEdit();
		logger.info("Clicked on Edit icon");
		mp.clickSave();
		logger.info("Clicked on Save Profile");
		
		String confmsg=mp.getConfirmationMsg();
		logger.info("Validating confirmation message");
		Assert.assertEquals(confmsg, "Today");
		np.clickIcon();
		np.clickLogout();
	}
	catch(Exception e) {
		logger.error("Test failed..");
		logger.debug("Debug logs...");
		Assert.fail();
	}
	logger.info("****End of Profile Update**********");
		
	}

}
