package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Setup;

public class SearchBuses {
	
	WebDriver driver;
	WebElement e;
	Actions actions;
	WebElement f;
	WebDriverWait wait;
     
	
	@FindBy(xpath="//a[@href='https://www.makemytrip.com/bus-tickets/']")WebElement busesBtn;
	
	@FindBy(xpath="//label[@class='lbl_input makeFlex column latoBold']")WebElement fromLabel;
	@FindBy(xpath="//input[@placeholder='From']")WebElement departureCity;
	@FindBy(id="react-autowhatever-1-section-0-item-0")WebElement selectDepartureCity;
	
	@FindBy(xpath="//label[@for='toCity']")WebElement toLabel;
	@FindBy(xpath="//input[@placeholder='To']")WebElement destinationCity;
	@FindBy(xpath="//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div/p/span")WebElement selectDestinationCity;
	
	@FindBy(xpath="//div[@class='dayPickerHeader dayToFromCont blackText']")WebElement traveldate;
	@FindBy(xpath="//div[@class='DayPicker-Caption']/child::div")List<WebElement> my;
	@FindBy(xpath = "//div[@class='DayPicker-Months']/child::div[2]/child::div[3]/child::div/child::div")
    List<WebElement> dates2;

    @FindBy(xpath = "//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")
    WebElement nextClick;
    
	@FindBy(xpath = "//div[@class='DayPicker-Months']/child::div[1]/child::div[3]/child::div/child::div")List<WebElement> dates1;
	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[3]/div[3]/div[6]")WebElement day;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div/div[2]/p/button")WebElement searchBtn;
	
	
	public SearchBuses(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//handle a frame
	
	public void handleFrame() throws InterruptedException
	{
		Thread.sleep(5000);
		f=driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));
        driver.switchTo().frame(f);
        driver.findElement(By.xpath("//i[@class='wewidgeticon we_close']")).click();
	}
	
	
	//launch a application
	public void launchApp()
	{
		driver.get("https://www.makemytrip.com");
	}
	
	//search buses
	public void buses() throws InterruptedException
	{	
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		Thread.sleep(5000);
		
		wait.until(ExpectedConditions.elementToBeClickable(busesBtn)).click();
	}
	
	//Give Departure City
	public void from(String cityfrom) throws InterruptedException
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.elementToBeClickable(fromLabel)).click();
		
		Thread.sleep(1000);
		departureCity.sendKeys(cityfrom);
		
		Thread.sleep(1000);
		selectDepartureCity.click();
		
	}
	
	//Give Destination City
	public void to(String toCity) throws InterruptedException
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		destinationCity.sendKeys(toCity);
		
		wait.until(ExpectedConditions.elementToBeClickable(selectDestinationCity)).click();
		
		Thread.sleep(1000);
		
	}
	
	//Select a Date
	public void selectDate(String date) throws InterruptedException
	{
		String dmy[] = date.split("-");
        String d = dmy[0];
        String m = dmy[1];
        String y = dmy[2];
        
        while (true)
        {
            Thread.sleep(2000);
            String monthyear1 = my.get(0).getText();
            String monthyear2 = my.get(1).getText();
            
            String arr[] = monthyear1.split(" ");
            String month = arr[0];
            String year = arr[1];
            
            String arr2[] = monthyear2.split(" ");
            String month2 = arr2[0];
            String year2 = arr2[1];
            
            if (month.equalsIgnoreCase(m) && year.endsWith(y))
            {
                for (int i = 0; i < dates1.size(); i++) 
                {
                    if (dates1.get(i).getText().contains(d)) 
                    {
                        dates1.get(i).click();
                        break;
                    }
                }
                break;
            }
          else if (month2.equalsIgnoreCase(m) && year2.endsWith(y)) 
          {
                for (int i = 0; i < dates2.size(); i++) 
                {
                    if (dates2.get(i).getText().contains(d)) 
                    {
                        dates2.get(i).click();
                        break;
                    }
                }
                break;
            }
            else 
            {
                Thread.sleep(3000);
                nextClick.click();
            }
        }   
			
	}

	//Search availabke buses
	public void search_button()
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
	}
	

}
