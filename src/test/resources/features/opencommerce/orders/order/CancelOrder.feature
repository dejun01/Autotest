Feature: Cancel Order
#env = sbase_dashboard

  Background:
    Given clear all data

  Scenario: Verify cancel an order successfully #SB_ORD_43 #SB_ORD_44 #SB_ORD_57 #SB_ORD_58 #SB_ORD_59
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    And get quantity of products "Racket" before cancelling
    Given open shop on storefront
    And add products "Racket>5" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Super      | Tester    | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Stripe          |
    And verify thank you page then get all information
    Given redirect to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then open cancel popup to verify information is displayed correctly
    And cancel fully order and restock items
    And user navigate to "Products>All products" screen
    Then get quantity of products "Racket" after cancelling or refunding
    And close browser

  Scenario Outline: Verify popup cancel order of an refunded order #SB_ORD_40 #SB_ORD_41 #SB_ORD_45 #SB_ORD_47
    Given open shop on storefront
    When add products "Racket>4" to cart then checkout
    And apply discount code
      | Discount code | Discount value | Discount type |
      | DISCOUNT_50   | 50             | Percentage    |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Super      | Tester    | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Stripe          |
    And verify thank you page then get all information
    And close browser
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And refund order with "<Refunded item>", "<Restock item>", "<Refund shipping>" and "<Reason for refund>"
    And open cancel popup to verify information is displayed correctly
    And close browser
    Examples:
      | Refunded item | Restock item | Refund shipping | Reason for refund  |
      | Racket>3      | true         | $7.00           | Damaged in transit |