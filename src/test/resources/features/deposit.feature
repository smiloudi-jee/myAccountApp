Feature: US 1
Scenario: In order to save money
    Given I create an account as a new client
    Given As a bank client 111111
    When I want to make a deposit of 1234.50 in my account
    Then the deposit is done


