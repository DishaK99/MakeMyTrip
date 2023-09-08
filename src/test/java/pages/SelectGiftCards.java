package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectGiftCards {
	
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	WebElement f;
	
	FileInputStream fis;
	  Properties pro;
	
	@FindBy(xpath="//span[@class='chNavIcon appendBottom2 chSprite chGiftcards']")WebElement giftCard;
	
	@FindBy(xpath="//label[@for='Loved Ones']")WebElement lo;
	
	@FindBy(xpath="//p[text()='Loved Ones']")WebElement lovedOnes;
	
	@FindBy(xpath="//p[text()='Luxury Hotels Gift Card']")WebElement card;
	
	@FindBy(xpath="//li[text()='₹10,000']")WebElement amount;
	
	@FindBy(xpath="//input[@name='senderName']")WebElement name;
	
	@FindBy(xpath="//input[@name='senderMobileNo']")WebElement mobNumber;
	
	@FindBy(xpath="//input[@name='senderEmailId']")WebElement email;
	
	@FindBy(xpath="//button[@class='prime__btn font16 prime__btn__text']")WebElement buyNowBtn;
	
	//Constructor
	public SelectGiftCards(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Launch a Application
	public void launchApp()
	{
		driver.get("https://www.makemytrip.com/");
	}
	
	//handle a frame
	
		public void handleFrame() throws InterruptedException
		{
			Thread.sleep(5000);
			f=driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));
	        driver.switchTo().frame(f);
	        driver.findElement(By.xpath("//i[@class='wewidgeticon we_close']")).click();
		}
	
	//Select a GiftCards
	public void selectGiftCard() throws InterruptedException
	{
		Thread.sleep(5000);
		giftCard.click();
		 Thread.sleep(5000);
	}
	
	//Switch between windows
	public void handleWindow()
	{
		Set<String> handles = driver.getWindowHandles();
		// Switch to the new tab
		for (String handle : handles)		
		{
			driver.switchTo().window(handle);
		    // Check if this is the tab you want to interact with based on the title 
		    if (driver.getTitle().equals("Gift Cards - Buy Gift Card Online, Gift Vouchers | MakeMyTrip.com"))
		    {
				 driver.switchTo().window(handle);
				    	
			 }
		}
	}
	
	//Select a category
	public void selectCategory() throws InterruptedException
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));

			handleWindow();
			
			Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(lovedOnes)).click();
	}
	
	//Select a card
	public void selectCard() throws InterruptedException
	{		
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		js=(JavascriptExecutor) driver;
		
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,300)");
		
		Thread.sleep(3000);
		card.click();
		
		Thread.sleep(5000);
	}
	
	//select a amount
	public void selectAmount()
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		//handleWindow();
		wait.until(ExpectedConditions.elementToBeClickable(amount)).click();
	}
	
	//Enter a name
	public void entername() throws IOException, InterruptedException
	{
		fis=new FileInputStream("C:\\Users\\DDILIPKA\\Desktop\\Testing\\Selenium\\MakeMyTrip\\src\\test\\java\\pages\\Giftcards.properties");
		pro=new Properties();
		pro.load(fis);
		
		//handleWindow();
		js.executeScript("arguments[0].scrollIntoView(true);", name);
		
		wait.until(ExpectedConditions.elementToBeClickable(name));
		
		name.sendKeys(pro.getProperty("travellerName"));
		
		Thread.sleep(2000);
	}
	
	//enter a mobile number
	public void enterNumber() throws IOException, InterruptedException
	{		
		fis=new FileInputStream("C:\\Users\\DDILIPKA\\Desktop\\Testing\\Selenium\\MakeMyTrip\\src\\test\\java\\pages\\Giftcards.properties");
		pro=new Properties();
		pro.load(fis);
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.elementToBeClickable(mobNumber));
		mobNumber.sendKeys(pro.getProperty("contactNo"));
		
		Thread.sleep(2000);
	}
	
	//enter an email
	public void enterEmail() throws IOException, InterruptedException
	{		
		fis=new FileInputStream("C:\\Users\\DDILIPKA\\Desktop\\Testing\\Selenium\\MakeMyTrip\\src\\test\\java\\pages\\Giftcards.properties");
		pro=new Properties();
		pro.load(fis);
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.elementToBeClickable(email));
		email.sendKeys(pro.getProperty("travellerEmail"));
		
		Thread.sleep(2000);
	}
	
	//click for checkout
	public void clickBtn() throws InterruptedException
	{		
        wait=new WebDriverWait(driver,Duration.ofSeconds(60));
        
       wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn)).click();;
	
       Thread.sleep(2000);
	}
	
}
