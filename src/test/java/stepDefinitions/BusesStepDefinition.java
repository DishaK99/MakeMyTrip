package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchBuses;
import pages.SelectSeat;
import pages.TravellerDetails;
import utils.Screenshot;
import utils.Setup;

public class BusesStepDefinition {
	
	WebDriver driver=Setup.chromeDriver();
	
	SearchBuses search;
	SelectSeat seat;
	TravellerDetails details;
	
	FileInputStream fis;
	Workbook w;
	Sheet s;
	Cell c;
	
	
//--------------------------------------------Scenario 1 - Search Buses---------------------------------------------------------------
	@Given("User is on the homepage of MakeMyTrip")
	public void user_is_on_the_homepage_of_make_my_trip() {
		search=new SearchBuses(driver);
		
		search.launchApp();
	}
	@When("the user clicks on Buses")
	public void the_user_clicks_on_buses() throws InterruptedException {
		
		search.handleFrame();
		search.buses();
	    
	}
	@And("^User enters (.*) city$")
	public void user_enters_city(String departure) throws InterruptedException {
	    
		search.from(departure);
		
	}
	@When("^User enters destination city (.*)$")
	public void user_enters_destination_city(String destination) throws InterruptedException {
	   search.to(destination);
	}
	@And("^User selects travelling (.*)$")
	public void user_selects_travelling(String date) throws InterruptedException {
		
		search.selectDate(date);
	}
	@And("User clicks on serach button")
	public void user_clicks_on_serach_button() {
	    
		search.search_button();
	}
	@Then("Available Buses should be displayed")
	public void available_buses_should_be_displayed() throws IOException {
		
		Assert.assertEquals(
				driver.getTitle(), 
				"Online Bus Ticket Booking, Book Confirmed Reservation Tickets @ MakeMyTrip"
				);
		
		Screenshot.captureScreen(driver);
		
		Setup.teardown();
	}
	
	
//--------------------------------------------Scenario 2 - Select Seat------------------------------------------------------	
	@Given("User is on the available buses page")
	public void user_is_on_the_available_buses_page() {
		
		seat=new SelectSeat(driver);
		
		seat.launchApp();
	}
		
	@When("User selects AC filter")
	public void user_selects_ac_filter() throws InterruptedException {
		
		seat.filter_buses();
	}
	@And("User selects seat type")
	public void user_selects_seat_type() {
	    
		seat.select_seat_type();
	}
	@And("User selects pick up time")
	public void user_selects_pick_up_time() throws InterruptedException {
	   
		seat.pickup_time();
	}
	@And("User selects drop time")
	public void user_selects_drop_time() throws InterruptedException {
	   
		seat.drop_time();
	}
	@And("User selects bus")
	public void user_selects_bus() throws InterruptedException {
	    
		seat.selectBus();
	}
	@And("User selects seat")
	public void user_selects_seat() {
	   
		seat.selectSeat();
	}
	@And("User clicks on continue button")
	public void user_clicks_on_continue_button() throws InterruptedException {
	    
		seat.confirmSeat();
	}
	@Then("Next page for booking should be displayed")
	public void next_page_for_booking_should_be_displayed() throws IOException {
	    		
		Assert.assertEquals(
				driver.getCurrentUrl(), 
				"https://www.makemytrip.com/bus/traveller"
				);
		
		Screenshot.captureScreen(driver);
		
		Setup.teardown();
	}
	
	
//--------------------------------------------Scenario 3 - Traveller Details for valid data-------------------------------------------------------
	@Given("User is on available buses page and selects the seat")
	public void user_is_on_available_buses_page_and_selects_the_seat() throws InterruptedException {
	   
		seat=new SelectSeat(driver);
		details=new TravellerDetails(driver);
		
		details.launchApp();
		seat.filter_buses();
		seat.pickup_time();
		seat.drop_time();
		seat.selectBus();
		seat.selectSeat();
		
	}
	@When("User is on traveller details page")
	public void user_is_on_traveller_details_page() throws InterruptedException {
	   
		seat.confirmSeat();
	}
	@And("User enters valid name")
	public void user_enters_valid_name() throws IOException {
		fis=new FileInputStream("C:\\Users\\DDILIPKA\\Desktop\\Testing\\Selenium\\MakeMyTrip\\ExcelData\\Traveller_Details.xlsx");
		w=new XSSFWorkbook(fis);
		s=w.getSheetAt(0);
		
		details.enterName(s.getRow(1).getCell(0).getStringCellValue());
	}
	@And("User enters valid age")
	public void user_enters_valid_age() throws InterruptedException {
	   
		long age=(long)s.getRow(1).getCell(1).getNumericCellValue();
		String travellerAge=String.valueOf(age);
		details.enterAge(travellerAge);
	}
	@And("User selects gender")
	public void user_selects_gender() {
	   
		details.selectGender();
	}
	@And("User enters valid pincode")
	public void user_enters_valid_pincode() throws InterruptedException {
	   
		long pincode=(long)s.getRow(1).getCell(2).getNumericCellValue();
		String cityPincode=String.valueOf(pincode);
		details.enterPincode(cityPincode);
	}
	@And("User selects state")
	public void user_selects_state() throws InterruptedException {
	   
		details.selectState(s.getRow(1).getCell(3).getStringCellValue());
	}
	@And("User enters address")
	public void user_enters_address() throws InterruptedException {
	    
		details.enterAddress(s.getRow(1).getCell(4).getStringCellValue());
	}
	@And("User checks the checkbox")
	public void user_checks_the_checkbox() {
	    
		details.selectCheckbox();
	}
	@And("User enters valid email")
	public void user_enters_valid_email() throws InterruptedException {
	    
		details.enterEmail(s.getRow(1).getCell(5).getStringCellValue());
	}
	@And("User enters valid mobile number")
	public void user_enters_valid_mobile_number() throws InterruptedException {
	    
		long num=(long)s.getRow(1).getCell(6).getNumericCellValue();
		String number=String.valueOf(num);
		details.enterNumber(number);
	}
	@And("User clicks on continue button for payment")
	public void user_clicks_on_continue_button_for_payment() throws InterruptedException {

		details.clickContinue();
	}
	@Then("CheckoutPage should be displayed")
	public void checkout_page_should_be_displayed() throws IOException {
	    
		Assert.assertEquals(
				driver.getTitle(), 
				"MakeMyTrip - #1 Travel Website for Flight Booking, Airline Tickets"
				);
		
		Screenshot.captureScreen(driver);
		
		Setup.teardown();
		
	}
	
	
//--------------------------------------------Scenario 4 - Traveller Details for invalid data------------------------------------------------------	
	
	@When("User selects seat from the bus")
	public void user_selects_seat_from_the_bus() {
	   
		seat.selectSeat1();
	}
	
	@And("User enters name")
	public void user_enters_name(DataTable data) throws InterruptedException {
	    
		seat.confirmSeat();
		
		List<String> cell=data.asList(String.class);
		details.enterName(cell.get(0));
	}
	@And("User enters invalid age")
	public void user_enters_invalid_age(DataTable data) throws InterruptedException {
	   
		List<String> cell=data.asList(String.class);
		details.enterAge(cell.get(0));
	}
	@And("User enters invalid pincode")
	public void user_enters_invalid_pincode(DataTable data) throws InterruptedException {
	   
		List<String> cell=data.asList(String.class);
		details.enterPincode(cell.get(0));
	}
	@And("User any selects state")
	public void user_any_selects_state(DataTable data) throws InterruptedException {
	    
		List<String> cell=data.asList(String.class);
		details.selectState(cell.get(0));
	}
	@And("User enters invalid address")
	public void user_enters_invalid_address(DataTable data) throws InterruptedException {
	    
		List<String> cell=data.asList(String.class);
		details.enterAddress(cell.get(0));
	}
	@And("User enters invalid email")
	public void user_enters_invalid_email(DataTable data) throws InterruptedException {
	    
		List<String> cell=data.asList(String.class);
		details.enterEmail(cell.get(0));
	}
	@And("User enters invalid mobile number")
	public void user_enters_invalid_mobile_number(DataTable data) throws InterruptedException {
	    
		List<String> cell=data.asList(String.class);
		details.enterNumber(cell.get(0));
	}
	@Then("Error Message should be displayed")
	public void error_message_should_be_displayed() throws IOException {
	   
        details.assertMethod();
		
        Screenshot.captureScreen(driver);
		
		Setup.teardown();

	}

}
