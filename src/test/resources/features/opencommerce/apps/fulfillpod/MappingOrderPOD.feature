Feature: Mapping Order POD
#mapping_pod
  Scenario: Verify order isn't mapped product
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz;Beverage Mug - POD:Black,15oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>1 item |                                       | To order |               |               |                 |
    And verify some field order pod on CrossPanda
      | Is show | Order name  | ProductName        | Variant                     | Design code                   | Basecost | SupplierName | Shipping method | Mockup                                                                                                     | Artwork font                                                                        | Artwork back |
      | true    | @OrderName@ | Beverage Mug - POD | Beverage Mug / black / 15oz | beverage-mug-1000000115703845 |          |              |                 | https://assets.boostflow.com/phub/phub-rendered/v21605346911_5fafa65f51df5EUFz215929050655ef1cd69228a5.png | https://pb.btdmp.com/pbase/shop-id/10120560/artworks/new_mug825236041AVhVpCuPwR.png |              |
    Then close browser

  Scenario: Verify order is mapped product
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz;Beverage Mug - POD:Black,15oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity | Status           | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage Mug / white / 11oz>PB-AP-BeverageMug-white-11oz-880900>1 item |                                       | Ready to fulfill |               |               |                 |
    And verify some field order pod on CrossPanda
      | Is show | Order name  | ProductName        | Variant                     | Design code                   | Basecost | SupplierName | Shipping method | Mockup                                                                                                     | Artwork font                                                                        | Artwork back |
      | true    | @OrderName@ | Beverage Mug - POD | Beverage Mug / black / 15oz | beverage-mug-1000000115703845 |          |              |                 | https://assets.boostflow.com/phub/phub-rendered/v21605346911_5fafa65f51df5EUFz215929050655ef1cd69228a5.png | https://pb.btdmp.com/pbase/shop-id/10120560/artworks/new_mug825236041AVhVpCuPwR.png |              |
    Then close browser

  Scenario: Verify order after change mapping product
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz;Beverage Mug - POD:Black,15oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And mapping product for order POD on CrossPanda
      | Order name  | Product | Odoo product | Odoo option>Odoo option value | Msg |
      | @OrderName@ |         |              |                               |     |
    And verify orders include product is mapped
      | Product nameSB>Variant>SKU>Quantity   | Product nameOdoo>Variant>SKU>Quantity                   | Status           | Shipping name         | Shipping cost               | Tracking number |
      | M001 - Limited Edition>Small>S>1 item | Charms for Slime Colorful Candy Polymer>A>Clay-A>1 item | Ready to fulfill | Mexico Express Packet | $8.29 / 10-30 business days |                 |
