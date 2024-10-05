package firstnaukrisimple;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Simplelogin {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		WebDriver d=new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		d.get("https://www.naukri.com/");
		
		d.findElement(By.xpath("//a[@id='login_Layer']")).click();
		//Thread.sleep(2000);
		d.findElement(By.xpath("//input[contains(@placeholder,'Enter your active Email ID / Username')]")).sendKeys("vinaychvk17@gmail.com");
		d.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("Vinay@0809");
		d.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		//Thread.sleep(3000);
		d.findElement(By.xpath("//a[normalize-space()='View profile']")).click();
		/*
		JavascriptExecutor js=(JavascriptExecutor)d;
		js.executeScript("arguments[0].click();",web);
		*/
		//Thread.sleep(3000);
		d.findElement(By.xpath("//em[@class='icon edit']")).click();
		Thread.sleep(1000);
		d.findElement(By.xpath("//button[@id='saveBasicDetailsBtn']")).click();
		Thread.sleep(2000);
		d.findElement(By.xpath("//img[@alt='naukri user profile img']")).click();
		d.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		//Thread.sleep(2000);
		d.quit();
		}

}
