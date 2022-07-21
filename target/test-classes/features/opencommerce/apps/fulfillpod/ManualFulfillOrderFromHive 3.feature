Feature: Manual fulfill order from hive-pbase
  #crosspanda_sync_order_pbase
  Scenario: Verify order after manaul fulfill on hive-pbase
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    And manual fulfill order pod in hive-pbase
      | Order name  | Line item Name>Quantity                            | Tracking number  | Tracking url                                   |
      | @OrderName@ | Beverage Mug - POD - Beverage Mug / white / 11oz>1 | YT19382054348734 | https://t.17track.net/en#nums=YT19382054348734 |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>0 item |                                       |        |               |               |                 |
    Then close browser

  Scenario: Verify order after manaul fulfill lineitem on hive-pbase
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz;Beverage Mug - POD:White,15oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    And manual fulfill order pod in hive-pbase
      | Order name  | Line item Name>Quantity                            | Tracking number  | Tracking url                                   |
      | @OrderName@ | Beverage Mug - POD - Beverage Mug / white / 11oz>1 | YT19382054348734 | https://t.17track.net/en#nums=YT19382054348734 |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>0 item |                                       |        |               |               |                 |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 15oz>PB-AP-BeverageMug-white-15oz-310524>1 item |                                       |        |               |               |                 |
    Then close browser

  Scenario: Verify order after manaul fulfill lineitem on hive-pbase (Sent fulfillment request)
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz;Beverage Mug - POD:White,15oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And fulfill order POD from CrossPanda
    And switch to tab "Sent fulfillment request"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>1 item |                                       |        |               |               |                 |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 15oz>PB-AP-BeverageMug-white-15oz-310524>1 item |                                       |        |               |               |                 |
    Then login to hive-pbase
    And manual fulfill order pod in hive-pbase
      | Order name  | Line item Name>Quantity                            | Tracking number  | Tracking url                                   |
      | @OrderName@ | Beverage Mug - POD - Beverage Mug / white / 11oz>1 | YT19382054348734 | https://t.17track.net/en#nums=YT19382054348734 |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Sent fulfillment request"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status | Shipping name | Shipping cost | Tracking number |
      | false   | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>1 item |                                       |        |               |               |                 |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 15oz>PB-AP-BeverageMug-white-15oz-310524>1 item |                                       |        |               |               |                 |
    Then close browser

