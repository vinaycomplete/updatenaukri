package firstnaukrisimple;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IrelandFileDownload {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String downloadFilepath=System.getProperty("user.dir")+"\\xlfiles";
		System.out.println(downloadFilepath);
		
		String URL="https://www.ireland.ie/en/india/newdelhi/services/visas/processing-times-and-decisions/#Decisions";
		WebDriverManager.chromedriver().setup();
		
		// Setup a custom download directory
		Map<String,Object> prefs=new HashMap<>();
		prefs.put("download.default_directory", downloadFilepath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true); // To disable Safe Browsing warnings
		
        
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		//Initialize the chrome driver with the configured options
		WebDriver driver=new ChromeDriver(options);
		
		try {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		// Watch service to monitor the download directory
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = new File(downloadFilepath).toPath();
        path.register(watchService, ENTRY_CREATE);
        
		
        // Trigger the file download
		WebElement lnkex=driver.findElement(By.xpath("//b[contains(text(),'Visa decisions made from 1 January 2024 to')]"));
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", lnkex);
		Thread.sleep(2000);
		Path downloadedFilePath=null;
		
		// Wait for a new file to be created in the directory
        WatchKey key = watchService.poll(30, TimeUnit.SECONDS); // wait up to 30 seconds
        if (key != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == ENTRY_CREATE) {
                    downloadedFilePath = path.resolve((Path) event.context());
                    //System.out.println("Downloaded file: " + downloadedFilePath.getFileName());
                    /*
                    // Optionally, wait until the file is fully downloaded
                    File downloadedFile = downloadedFilePath.toFile();
                    System.out.println("Download completed: " + file.getName());
                    */
                }
            }
            key.reset();
        }
        System.out.println("Downloaded file: " + downloadedFilePath.getFileName());
        File downloadedFile = downloadedFilePath.toFile();
        System.out.println("Download completed: " + downloadedFile.getName());
		
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		finally {
		driver.quit();
		}
		
		
	}
		
	

}
