package pages;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravellerDetails {
	
	WebDriver driver;
	JavascriptExecutor js;
	Actions action;
	WebDriverWait wait;
	
	@FindBy(id="fname")WebElement travellerName;
	
	@FindBy(xpath="//input[@name='age']")WebElement travellerAge;
	
	@FindBy(xpath="//div[@class='femaleTab ']")WebElement gender;
	
	@FindBy(xpath="//input[@placeholder='Your Pincode']")WebElement pincode;

	@FindBy(xpath="//ul[@class='dropdownListWpr']/child::li")List<WebElement> allState;
	
	@FindBy(id="address_gst_info")WebElement travellerAddress;
	
	@FindBy(xpath="//p[text()='Confirm and save billing details to your profile']")WebElement saveBillingDetails;
	
	@FindBy(id="contactEmail")WebElement travellerEmail;
	
	@FindBy(id="mobileNumber")WebElement travellerNumber;
	
	@FindBy(xpath="//span[text()='Continue']")WebElement continueBtn;
	
	@FindBy(xpath="//p[text()='Age cannot be more than 120 years']")WebElement errorMsg;
	
	//Constructor
	public TravellerDetails(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Launch an application
	public void launchApp()
	{
		driver.get("https://www.makemytrip.com/bus/search/Pune/Mumbai/01-12-2023?from_code=MMTCC1744&to_code=MMTCC1599");
	}
	
	//enter a traveller name
	public void enterName(String name)
	{
		travellerName.sendKeys(name);
	}
	
	//enter a traveller age
	public void enterAge(String age) throws InterruptedException
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.elementToBeClickable(travellerAge));
		travellerAge.sendKeys(age);
		
	}
	
	//select a gender
	public void selectGender()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.elementToBeClickable(gender)).click();;
		}
	
	//enter a pincode
	public void enterPincode(String statePincode) throws InterruptedException
	{
        wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		js=(JavascriptExecutor) driver;
		
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,200)");
		
		wait.until(ExpectedConditions.elementToBeClickable(pincode)).click();
		
		Thread.sleep(2000);
		pincode.clear();
		pincode.sendKeys(statePincode);
	}
	
	//select a state
	public void selectState(String state) throws InterruptedException
	{
		for(int i=0; i<allState.size(); i++) 
		{

            if(allState.get(i).getText().contains(state)) 
            {

            	Thread.sleep(3000);
            	allState.get(i).click();

                break;

            }

        }
	}
	
	//enter an address
	public void enterAddress(String address) throws InterruptedException
	{
		
        js=(JavascriptExecutor) driver;
		
        Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,200)");
		
		Thread.sleep(3000);
		travellerAddress.sendKeys(address);
		
	}
	
	public void selectCheckbox()
	{
		action=new Actions(driver);
		
		action.moveToElement(saveBillingDetails).click().build().perform();
	}
	
	//enter an email
	public void enterEmail(String email) throws InterruptedException
	{
		Thread.sleep(2000);
		travellerEmail.sendKeys(email);
	}
	
	//enter a traveller number
	public void enterNumber(String number) throws InterruptedException
	{
		Thread.sleep(2000);
		travellerNumber.sendKeys(number);
	}
	
	//click on the button 
	public void clickContinue() throws InterruptedException
	{
		
		continueBtn.click();
		Thread.sleep(10000);
		
	}
	
	//assert method
	public void assertMethod() 
	{
	     wait= new WebDriverWait(driver,Duration.ofSeconds(60));
	     wait.until(ExpectedConditions.elementToBeClickable(errorMsg));
        
	     String text = errorMsg.getText();
	     String expectedText="Age cannot be more than 120 years";
	     Assert.assertEquals(text, expectedText, "Age cannot be more than 120 years");
 

	    }
	

}
