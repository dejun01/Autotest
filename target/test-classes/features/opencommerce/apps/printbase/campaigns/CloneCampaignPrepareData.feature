@dashboardCloneProduct @dashboard
Feature: Clone Campaigns Prepare Data
#clone_campaign

  Background: Delete all product in des shop
    Given user login to secondShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    Then Delete all campaigns
    And quit browser
    Given user login to firstShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And select "2" products
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser


  Scenario: Clone campaigns from source shop to another shop with action Skip the product #SB_PRO_CP_69 #SB_PRO_CP_70 #SB_PRO_CP_110
    Given user login to firstShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And select "2" products
    And Import campaign to another shop
      | Action           |
      | Skip the product |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    Then verify import products status is "Completed" with "2" products
    And quit browser


  Scenario: Clone campaigns from source shop to another shop with action Keep both products #SB_PRO_CP_111
    Given user login to firstShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And select "2" products
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    Then verify import products status is "Completed" with "4" products
    And quit browser


  Scenario: Clone campaigns from source shop to another shop with action Override the existing products #SB_PRO_CP_109
    Given user login to firstShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And select "2" products
    And Import campaign to another shop
      | Action                         |
      | Override the existing products |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    Then verify import products status is "Completed" with "2" products


