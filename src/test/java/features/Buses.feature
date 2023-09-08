@BusTicketBooking
Feature: Validate the bus ticket booking page

@SearchBuses
Scenario Outline: Check the search buses functionality for MakeMyTrip
Given User is on the homepage of MakeMyTrip 
When the user clicks on Buses
And User enters <departure> city
And User enters destination city <destination> 
And User selects travelling <date>
And User clicks on serach button
Then Available Buses should be displayed

Examples:
|departure|destination|date|
|pun|bom|1-December-2023|

@SelectSeat
Scenario: Check the filter functionality for available buses
Given User is on the available buses page
When User selects AC filter
And User selects seat type
And User selects pick up time
And User selects drop time
And User selects bus
And User selects seat 
And User clicks on continue button
Then Next page for booking should be displayed

@TravellerDetailsForValidData
Scenario: Check traveller details functionality for valid data
Given User is on available buses page and selects the seat
When User is on traveller details page
And User enters valid name
And User enters valid age
And User selects gender
And User enters valid pincode
And User selects state
And User enters address
And User checks the checkbox
And User enters valid email
And User enters valid mobile number
And User clicks on continue button for payment
Then CheckoutPage should be displayed

@TravellerDetailsForInvalidData
Scenario: Check traveller details functionality for invalid data
Given User is on available buses page and selects the seat
When User selects seat from the bus
And User enters name
|ABC|
And User enters invalid age
|200|
And User selects gender
And User enters invalid pincode
|400708|
And User any selects state
|Maharashtra|
And User enters invalid address
|Mumbai123|
And User checks the checkbox
And User enters invalid email
|Traveller.com|
And User enters invalid mobile number
|12345|
And User clicks on continue button for payment
Then Error Message should be displayed








