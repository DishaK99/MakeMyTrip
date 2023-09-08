@GiftCard
Feature: Validate the Gift Card page

@SearchBuses
Scenario: Check the gift card functionality for MakeMyTrip
Given User is on the homepage of MakeMyTrip application
When the user clicks on Giftcards
And User selects category
And User selects card
And User selects amount
And User enters name for gift card
And User enters mobile number for gift card
And User enters email for gift card
And User clicks on button for gift card
Then Next page should be displayed


