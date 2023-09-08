package stepDefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SelectGiftCards;
import utils.Screenshot;
import utils.Setup;

public class GiftcardsStepDefinition {
	
	WebDriver driver=Setup.chromeDriver();
	
	SelectGiftCards cards;
	
	
//------------------------------------Scenario 1 - Select Gift Cards-------------------------------------------------------	
	@Given("User is on the homepage of MakeMyTrip application")
	public void user_is_on_the_homepage_of_make_my_trip_application() {
	    
		cards=new SelectGiftCards(driver);
		
		cards.launchApp();
	}
	@When("the user clicks on Giftcards")
	public void the_user_clicks_on_giftcards() throws InterruptedException {
	    
		cards.handleFrame();
		cards.selectGiftCard();
	}
	@And("User selects category")
	public void user_selects_category() throws InterruptedException {
	    
		cards.selectCategory();
	}
	@And("User selects card")
	public void user_selects_card() throws InterruptedException {
	    
		cards.selectCard();
	}
	@And("User selects amount")
	public void user_selects_amount() {
	   
		cards.selectAmount();
	}
	@And("User enters name for gift card")
	public void user_enters_name_for_gift_card() throws IOException, InterruptedException {
	    
		cards.entername();
	}
	@And("User enters mobile number for gift card")
	public void user_enters_mobile_number_for_gift_card() throws IOException, InterruptedException {
	   
		cards.enterNumber();
	}
	@And("User enters email for gift card")
	public void user_enters_email_for_gift_card() throws IOException, InterruptedException {
	    
		cards.enterEmail();
	}
	@And("User clicks on button for gift card")
	public void user_clicks_on_button_for_gift_card() throws InterruptedException {
	    
		cards.clickBtn();
	}
	@Then("Next page should be displayed")
	public void next_page_should_be_displayed() throws IOException {
		
		Assert.assertEquals(
				driver.getTitle(), 
				"MakeMyTrip - #1 Travel Website for Flight Booking, Airline Tickets"
				);
		
		Screenshot.captureScreen(driver);
		
		Setup.teardown();
	}
	

}
