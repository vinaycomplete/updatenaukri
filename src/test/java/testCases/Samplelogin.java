package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyNaukriPage;
import pageObjects.MyProfile;
import testBase.BaseClass;

public class Samplelogin extends BaseClass {

	@Test
	void myLogin() throws InterruptedException {
		HomePage hm=new HomePage(driver);
		hm.clicklnkLogin();
		hm.setEmail("vinaychvk17@gmail.com");
		hm.setPassword("Vinay@0809");
		hm.clickLogin();
		
		MyProfile mp=new MyProfile(driver);
		mp.clickProfile();
		mp.clickEdit();
		Thread.sleep(1000);
		mp.clickSave();
		Thread.sleep(1000);
		MyNaukriPage mnp = new MyNaukriPage(driver);
		mnp.clickIcon();
		mnp.clickLogout();
		
		
	}
}
