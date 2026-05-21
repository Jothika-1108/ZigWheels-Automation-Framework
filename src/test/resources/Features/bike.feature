Feature: Identify Upcoming Honda Bikes

  Scenario: Get upcoming Honda bikes under 4 lakh
    Given user navigates to ZigWheels website
    When user clicks on upcoming bikes
    And user selects Honda manufacturer
    Then user should get list of bikes under 4 lakh


  Scenario: Get popular models in Chennai used cars
    Given user navigates to ZigWheels website
    When user navigates to used cars in Chennai
    Then user should extract all popular car models


  Scenario: Login with invalid Google account
    Given user navigates to ZigWheels website
    When user clicks on login
    And user tries to login with email "jothi@.com"
    Then error message should be displayed
