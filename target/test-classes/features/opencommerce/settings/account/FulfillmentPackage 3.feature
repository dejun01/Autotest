#sbase_fulfillment_package

@Subscription @FulfillmentPackage
Feature: Verify Fulfillment Only Package

  Background: user login to shopbase dashboard
    Given user login to shopbase dashboard

  Scenario: Change plan before
    Given user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    When go to Upgrade plan page
    And choose a package is "Basic Base"
    And quit browser

  Scenario: Calculate Refund when change to Fulfillment package #SB_SET_ACC_BILL_30
    Given user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And Calculating the payback amount of current package
    And Switch to PlusHub Service Only
    Given Navigate to Balance page
    And Filter balance history by "Invoice" with value "Pay back from changing plan"
    Then verify refund package when changing plan
      | Type | Content                     | Created date | Status  |
      | IN   | Pay back from changing plan | Just now     | Success |

