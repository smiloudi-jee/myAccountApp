Feature: AccountFeature 
  
  Scenario: US 1 => In order to save money
    Given As a bank client
    When I want to make a deposit of 200.00 in my account
    Then the deposit is done

  Scenario: US 2 => In order to retrieve some or all of my savings
    Given As a bank client
    When I want to make a withdrawal of 200.00 from my account
    Then the withdrawal is done
    
  Scenario: US 3 => In order to check my operations
    Given As a bank client
    When I want to see the history of my operations
    Then I can see history