Feature: Limit quota campaign for clone campaign
 #pbase_limit_clone_campaign
  Background:
    Given user login to secondShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    Then Delete all campaigns
    And quit browser


  Scenario: Check quota campaign clone campaign with action Keep both products #SB_PRB_LCC_1
    Given user login to firstShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And select all product with action
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser
    Given user login to secondShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    Then verify import products status is "Completed" with "2999" products
    And quit browser


  Scenario: Check quota campaign clone campaign with action Skip the product #SB_PRB_LCC_1
    Given user login to firstShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And select all product with action
    And Import campaign to another shop
      | Action           |
      | Skip the product |
    And quit browser
    Given user login to secondShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    Then verify import products status is "Completed" with "2999" products
    And quit browser


  Scenario: Check quota clone campaign with action Override the existing products #SB_PRB_LCC_1 #SB_PRB_LCC_7
    Given user login to firstShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And select all product with action
    And Import campaign to another shop
      | Action                         |
      | Override the existing products |
    And quit browser
    Given user login to secondShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    Then verify import products status is "Completed" with "2999" products
    And quit browser


  Scenario: Clone campaign when x > quota shop #SB_PRB_LCC_2 #SB_PRB_LCC_3
    Given user login to firstShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And select all product with action
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser
    Given user login to secondShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    Then verify import products status is "Paused" with "1003" products
    And quit browser



























