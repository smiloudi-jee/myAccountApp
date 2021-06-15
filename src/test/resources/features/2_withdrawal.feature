Feature: US 2
    Scenario: In order to retrieve some or all of my savings
        Given As a bank client 111111
        When I want to make a withdrawal of 60.35 from my account
        Then the withdrawal is done
