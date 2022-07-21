@dashboard @customerAccounts
Feature: Customer Accounts

  ##evd: sbase_notification

  Background:
    Given clear all data

  @customerAccountsOptional
  Scenario: SB_DAS_SET_3.25 - Verify login field in SF Header and Checkout (Customer information) when accounts are optional
    Given user login to shopbase dashboard by API
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen
    When click "Accounts are optional" radio button and Save
    And close browser
    And open shop on storefront
    Then verify header when "have" login field
    When add products "Lamp" to cart then checkout
    And verify customer infomation in checkout when have login field
    And close browser


  @customerAccountsDisabled
  Scenario: SB_DAS_SET_3.23 - Verify login field in SF Header and Checkout (Customer information) when accounts are disabled
    Given user login to shopbase dashboard by API
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen
    When click "Accounts are disabled" radio button and Save
    And close browser
    And open shop on storefront
    Then verify header when "have not" login field
    When add products "Lamp" to cart then checkout
    And verify customer infomation in checkout when haven't login field
    And close browser


