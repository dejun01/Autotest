#env = fulfillment_service
Feature: Import product in inventory

  Background: Access inventory page
    Given user login to shopbase dashboard
    And user navigate to "Fulfillment>Dropship>Warehouse" screen

  Scenario: Import 1 product success and click button product
    Given import to store with product "Digital Car Tire Tyre Air"
    And move to home page
    And user navigate "Products" screen
    Then verify product imported "Digital Car Tire Tyre Air"
    And close browser

  Scenario: Import multiple product product success
    Given import multiple product in store
    And move to home page
    And user navigate "Products" screen
    Then verify multiple product import
