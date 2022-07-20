Feature: Export number order
  #order_dashboard

  Background: Clear data
    Given clear all data

  Scenario: verify export number current store sbase #SB_ORD_86
    Given user login to firstShop dashboard
    And user navigate to "Analytics" screen
    And get total order default with "current store"
    And user navigate to "Orders" screen
    And implement export "Orders by date" orders with "current store"
    Then verify number order export
    And close browser

  Scenario: verify export number current store pbase
    Given user login to firstShop dashboard
    And user navigate to "Analytics" screen
    And get total order default with "current store"
    And user navigate to "Orders" screen
    And implement export "Orders by date" orders with "current store"
    Then verify number order export
    And close browser