Feature: Sync Order PBase
  #crosspanda_sync_order_pbase

  Scenario: Verify sync base product
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Test AOP Sweater:All over print,S;Test AOP Sweater:All over print,M;Test AOP Sweater:All over print,L"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    Then verify base product
      | Order name  | Product          | Option>Variant                                         |
      | @OrderName@ | Test AOP Sweater | Style>Test AOP Sweater;Color>All over print;Size>S,M,L |
    And remove base product "Test AOP Sweater"
    And close browser

  Scenario: Verify sync order Pbase to XPanda
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz;Beverage Mug - POD:White,15oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    And wait 5 second
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity            | Status           | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage mug / White / 11oz>PB-AP-BeverageMug-white-11oz-880900>1 item | Beverage Mug>11oz / White / Beverage Mug>>1 item | Ready to fulfill |               |               |                 |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage mug / White / 15oz>PB-AP-BeverageMug-white-15oz-310524>1 item | Beverage Mug>15oz / White / Beverage Mug>>1 item | Ready to fulfill |               |               |                 |
    And verify some field order pod on CrossPanda
      | Is show | Order name  | ProductName        | Variant                     | Design code                   | Basecost   | SupplierName | Shipping method | Mockup                                                                                                     | Artwork font                                                                        | Artwork back |
      | true    | @OrderName@ | Beverage Mug - POD | Beverage mug / White / 15oz | beverage-mug-1000000115703845 | $11.00 x 1 | CustomCat    | DHL CustomCat   | https://assets.boostflow.com/phub/phub-rendered/v21605346906_5fafa65a36b68nrVmg15929050655ef1cd69228a5.png | https://pb.btdmp.com/pbase/shop-id/10120560/artworks/new_mug337148691DxyrFedlRJ.png |              |
      | true    | @OrderName@ | Beverage Mug - POD | Beverage mug / White / 11oz | beverage-mug-1000000115703845 | $11.00 x 1 | CustomCat    | DHL CustomCat   | https://assets.boostflow.com/phub/phub-rendered/v21605346906_5fafa65a36b68nrVmg15929050655ef1cd69228a5.png | https://pb.btdmp.com/pbase/shop-id/10120560/artworks/new_mug28222995pFaZyAFuzU.png  |              |
  


