Feature: Limit quota campaign for clone campaign
 #sbase_limit_clone_campaign
  Background:
    Given user login to secondShop dashboard
    And user navigate to "Products>All products" screen
    Then Delete all products
    And quit browser


  Scenario: Check quota campaign clone campaign with action Keep both products #SB_PRB_LCC_4
    Given user login to firstShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And select all product with action
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser
    Given user login to secondShop dashboard
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "2999" products
    And quit browser



  Scenario: Check quota clone campaign with action Override the existing products #SB_PRB_LCC_4 #SB_PRB_LCC_8
    Given user login to firstShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And select all product with action
    And Import campaign to another shop
      | Action                         |
      | Override the existing products |
    And quit browser
    Given user login to secondShop dashboard
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "2999" products
    And quit browser


  Scenario: Clone campaign when x > quota shop #SB_PRB_LCC_5 #SB_PRB_LCC_6
    Given user login to firstShop dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And select all product with action
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser
    Given user login to secondShop dashboard
    And user navigate to "Products>All products" screen
    Then verify import products status is "Paused" with "2" products
    And quit browser



























