package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Setup {
	
	public static WebDriver driver;
	
	public static WebDriver chromeDriver()
	{
//		WebDriverManager.edgedriver().setup();
//		driver=new EdgeDriver();
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
			
		driver.manage().window().maximize();
		
		return driver;
	}

	
	public static void teardown()
	{
		driver.close();
	}

}
