Feature: Hold order in CrossPanda

  Background:
    Given clear all data
    Given open shop on storefront

  Scenario: Create new order
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And map order's product with product purchased on CrossPanda
      | Order number | Product nameSB>Variant | Odoo product                       | Odoo option>Odoo option value | Msg |
      | @ByProduct@  | Face Lifting 3 in 1    | 6pcs/Lot Charms for Slime Addition | Color>6 Pieces Mix Color      |     |


#    Then checkout successfully via stripe with cart "Face Lifting 3 in 1"
#      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    Given login to crosspanda dashboard
#    And user navigate to "My orders" screen in CrossPanda
#    And switch to tab "Ready to fulfill"
#    Then Change mapping in order with product
#      | Product name                       | Variant of product mapping |
#      | 6pcs/Lot Charms for Slime Addition | Color>6 Pieces Mix Color   |


  Scenario: Hold order
    And add products "Face Lifting 3 in 1" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | John Doe        | 12/30        | 111 | Test Gateway    |
    And verify thank you page then get all information
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then hold order in CrossPanda with action "Hold orders"
    And switch to tab "On hold"
    Then Change mapping in order with product
      | Product name                       | Variant of product mapping |
      | 6pcs/Lot Charms for Slime Addition | Color>6 Pieces Mix Color   |
    Then verify order display
    Then replace product in order with product "Charms for Slime Colorful Candy Polymer"
    Then verify order display
    Then Fulfill order in CrossPanda
      | Order name  |
      | @OrderName@ |
    And switch to tab "Sent fulfillment request"
    Then verify order display


  Scenario: mark as fulfill order
    And add products "Face Lifting 3 in 1" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | John Doe        | 12/30        | 111 | Test Gateway    |
    And verify thank you page then get all information
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then mask as fulfill order in CrossPanda with action "Mark as fulfilled"
    And switch to tab "Shipped"
    Then verify order display


  Scenario: Hold order with two lineitem
    And add products "100Pcs/set Pro Disposable Blue;6 in 1 Stainless Steel SWISS TECH" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | John Doe        | 12/30        | 111 | Test Gateway    |
    And verify thank you page then get all information
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then hold order in CrossPanda with action "Hold orders"
    And switch to tab "On hold"
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity                     | Product nameOdoo>Variant>SKU>Quantity                                | Status           |
      | 100Pcs/set Pro Disposable Blue>United States>>1 item    | Fashion Retro Round Sunglasses>White>W>1 item                        | Ready to fulfill |
      | 6 in 1 Stainless Steel SWISS TECH>Default Title>>1 item | 6pcs/Lot Charms for Slime Addition>6 Pieces Mix Color>6Pieces>1 item | Ready to fulfill |


  Scenario: Hold order with three lineitem and verify hold order
    And add products "100Pcs/set Pro Disposable Blue;6 in 1 Stainless Steel SWISS TECH;72W UV Lamp LED Nail Lamp" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | John Doe        | 12/30        | 111 | Test Gateway    |
    And verify thank you page then get all information
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then hold order in CrossPanda with action "Hold orders"
    And switch to tab "On hold"
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity                     | Product nameOdoo>Variant>SKU>Quantity                                | Status           |
      | 100Pcs/set Pro Disposable Blue>United States>>1 item    | Fashion Retro Round Sunglasses>White>W>1 item                        | Ready to fulfill |
      | 6 in 1 Stainless Steel SWISS TECH>Default Title>>1 item | 6pcs/Lot Charms for Slime Addition>6 Pieces Mix Color>6Pieces>1 item | Ready to fulfill |
    And switch to tab "To order"
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity                 | Product nameOdoo>Variant>SKU>Quantity | Status   |
      | 72W UV Lamp LED Nail Lamp>72W TWO HAND LAMP>>1 item |                                       | To order |