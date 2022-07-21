Feature: Cancel order from hive-Pbase
#crosspanda_sync_order_pbase

  Scenario: Verify cancel order befor approved order
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And cancel order on hive-pbase
      | Order name  | Buyer | Seller |
      | @OrderName@ | true  | true   |
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>0 item |                                       |        |               |               |                 |
    Then close browser

  Scenario: Verify cancel order after approved order
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    And cancel order on hive-pbase
      | Order name  | Line item Name>Quantity                            | Buyer | Seller |
      | @OrderName@ | Beverage Mug - POD - Beverage Mug / white / 11oz>2 | true  | true   |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>0 item |                                       |        |               |               |                 |
    Then close browser

  Scenario: Verify order refund quantity lineitem after approved order
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    And refund order pod on hive-pbase
      | Order name  | Line item Name>Quantity                            | Buyer | Seller |
      | @OrderName@ | Beverage Mug - POD - Beverage Mug / white / 11oz>1 | true  | true   |
    Then login to crosspanda dashboard
    And user navigate to "All Orders" screen in CrossPanda
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity            | Status           | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / White / 11oz>PB-AP-BeverageMug-white-11oz-880900>1 item | Beverage Mug>11oz / White / Beverage Mug>>1 item | Ready to fulfill |               |               |                 |
    Then close browser