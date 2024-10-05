package firstnaukrisimple;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class ExcelFileDownload {

	public static void main(String[] args) throws Exception {
		/*
		String url="https://www.ireland.ie/en/india/newdelhi/services/visas/processing-times-and-decisions/#Decisions";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
	WebElement lnkex=driver.findElement(By.xpath("//b[contains(text(),'Visa decisions made from 1 January 2024 to')]"));
		
	//lnkex.click();
	/*
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", lnkex);
	*/

	//driver.quit();
	
		String downloadFilepath=System.getProperty("user.dir")+"\\xlfiles";
		System.out.println(downloadFilepath);
		String downloadedFile=downloadFilepath+"\\20240821_NDVO_Visa_Decision.ods";
		File file=new File(downloadedFile);
		//OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(new File("path/to/your/file.ods"));
		SpreadSheet spreadSheet = SpreadSheet.createFromFile(file);
		 // Access the first sheet
        Sheet sheet = spreadSheet.getSheet(0);
        Map<Long,String> data=new HashMap<>();
        //List<String> d=new ArrayList<String>;
        for (int row = 0; row < sheet.getRowCount(); row++) {
            for (int col = 0; col < sheet.getColumnCount(); col++) {
                // Get the cell value as a string
                String value = sheet.getValueAt(col, row).toString();
                data.put((long) row, value);
                
            }
            String str="68308382";
            Boolean flag=false;
            for(Map.Entry temp:data.entrySet()) {
            	if(temp.getValue()==str) {
            		flag=true;
            		break;
            	}
            	
            }
            if(flag==true) {
            	System.out.println("Application no. found");
            }
            else {
            	System.out.println("Application no. not found");
            }
        }
        
		
		
		
				
			

	}
}
