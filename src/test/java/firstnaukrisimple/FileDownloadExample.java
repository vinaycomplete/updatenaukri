package firstnaukrisimple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileDownloadExample {
    public static void main(String[] args) throws Exception {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Define download directory
        String downloadFilepath = "path/to/download/directory";
        
        // Set Chrome preferences for downloading files
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilepath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Initialize WebDriver with options
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the page with the download link
            driver.get("https://example.com/download-page");

            // Watch service to monitor the download directory
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = new File(downloadFilepath).toPath();
            path.register(watchService, ENTRY_CREATE);

            // Trigger the file download
            driver.findElement(By.id("downloadButton")).click();

            // Wait for a new file to be created in the directory
            WatchKey key = watchService.poll(30, TimeUnit.SECONDS); // wait up to 30 seconds
            if (key != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_CREATE) {
                        Path downloadedFilePath = path.resolve((Path) event.context());
                        System.out.println("Downloaded file: " + downloadedFilePath.getFileName());

                        // Optionally, wait until the file is fully downloaded
                        File downloadedFile = downloadedFilePath.toFile();
                        waitForDownloadCompletion(downloadedFile);
                    }
                }
                key.reset();
            }

        } finally {
            // Close the browser
            driver.quit();
        }
    }

    // Method to wait for file download completion
    public static void waitForDownloadCompletion(File file) {
        int attempts = 0;
        while (file.exists() && file.getName().endsWith(".crdownload")) {
            try {
                // Sleep for a short time before checking again
                Thread.sleep(500);
                attempts++;
                if (attempts > 120) { // Timeout after 60 seconds (120 * 500ms)
                    throw new RuntimeException("File download did not complete within the expected time.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Download completed: " + file.getName());
    }
}
