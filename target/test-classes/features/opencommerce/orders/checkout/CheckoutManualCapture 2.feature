Feature: Checkout manual capture
  #env = checkout_manual_capture

  Background:
    Given clear all data
    Given open shop on storefront
    And add products "Shirt" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Stripe          |
    And verify thank you page then get all information

  Scenario: Capture payment successfully in order detail #SB_SET_PMS_MC_42
    And user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail dashboard by ID
    And verify order status change from "Authorized" to "Paid" when capture payment
    Then close browser

  Scenario:  Check capture part of order #SB_SET_PMS_MC_17
    And user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail dashboard by ID
    And verify order status change from "Authorized" to "Paid" when capturing a partial order equal to "1" dollar
    Then close browser
