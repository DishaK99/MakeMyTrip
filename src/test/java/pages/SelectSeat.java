package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectSeat {
	
	WebDriver driver;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	WebElement element;
	
    @FindBy(xpath="//span[@class='listingSprite acIcon appendRight11']")WebElement AC;
	
	@FindBy(xpath="//span[@class='listingSprite sleeperIcon appendRight11']")WebElement seatType;
	
	@FindBy(xpath="//*[@id=\"busList\"]/div[1]/div/div[2]/ul/li[5]/div[2]/div[5]")WebElement pickupTime;
	
	@FindBy(xpath="//*[@id=\"busList\"]/div[1]/div/div[2]/ul/li[8]/div[2]/div[1]")WebElement dropTime;
	
	
	@FindBy(id="bus_39_MMTCC1744_MMTCC1599_01-12-2023_5000001847170537202")WebElement select_bus;
	
	@FindBy(xpath="//*[@id=\"busList\"]/div[2]/div[3]/div[1]/div[3]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/div[5]/div/li/span[1]")WebElement select_seat;
	
	@FindBy(xpath="//*[@id=\"busList\"]/div[2]/div[3]/div[1]/div[3]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/div[7]/div/li/span[1]")WebElement select_seat1;
	
	@FindBy(xpath="//*[@id=\"busList\"]/div[2]/div[3]/div[1]/div[3]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/div[6]/div/li/span[1]")WebElement select_seat2;
	
	@FindBy(xpath="//*[@id=\"busList\"]/div[2]/div[3]/div[1]/div[3]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/div[11]/div/li/span[1]")WebElement select_seat3;
	
	@FindBy(xpath="//span[@class='font12 deepskyBlueText pointer fare-breakup makeRelative pushRight']")WebElement fare_details;
	
	@FindBy(xpath="//span[text()='Continue']")WebElement continueBtn;
	
	//Constructor
	public SelectSeat(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Launch an application
	public void launchApp()
	{
		driver.get("https://www.makemytrip.com/bus/search/Pune/Mumbai/01-12-2023?from_code=MMTCC1744&to_code=MMTCC1599");
	}
	
	//choose a filter for AC type
	public void filter_buses() throws InterruptedException
	{
		Thread.sleep(5000);
		AC.click();
	}
	
	//Select a type for seat
	public void select_seat_type()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.elementToBeClickable(seatType)).click();
	}
	
	//select pickup time
	public void pickup_time() throws InterruptedException
	{
		action=new Actions(driver);
		js = (JavascriptExecutor) driver;
		element = pickupTime; 

		js.executeScript("window.scrollBy(0, 600);");
		Thread.sleep(2000);
		action.moveToElement(element).click().build().perform();
		
	}
	
	//select drop time
	public void drop_time() throws InterruptedException
	{
		action=new Actions(driver);
		js = (JavascriptExecutor) driver;
		element = dropTime; 
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
		Thread.sleep(2000);
		action.moveToElement(element).click().build().perform();
		
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, 200);");
	}
	
	//select a bus
	public void selectBus() throws InterruptedException
	{
		js=(JavascriptExecutor) driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		js.executeScript("window.scrollTo(0,200)");

		wait.until(ExpectedConditions.elementToBeClickable(select_bus)).click();
	}
	
	//select a seat
	public void selectSeat() 
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));

		wait.until(ExpectedConditions.elementToBeClickable(select_seat)).click();
	}
	
	public void selectSeat1() 
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));

		wait.until(ExpectedConditions.elementToBeClickable(select_seat3)).click();
	}
	
	//click  on continue
	public void confirmSeat() throws InterruptedException
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		action=new Actions(driver);
		js=(JavascriptExecutor) driver;
		
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(200,200)");
		
		Thread.sleep(3000);
		action.moveToElement(fare_details).build().perform();
	
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	}
	
	
	
	

}
