#smoketest_fulfillPOD
Feature: Fulfill order POD

  Scenario: Fulfill order POD
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Beverage Mug - POD:White,11oz"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    And wait 5 second
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity                          | Status           | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage mug / White / 11oz>PB-AP-BeverageMug-white-11oz-680448>1 item | (Test)CUP HIGH TEMPERATURE>11oz / White / Beverage Mug>>1 item | Ready to fulfill |               |               |                 |
    And verify some field order pod on CrossPanda
      | Is show | Order name  | ProductName        | Variant                     | Design code                         | Basecost   | SupplierName       | Shipping method     | Mockup                                                                                                     | Artwork font                                                              | Artwork back |
      | true    | @OrderName@ | Beverage Mug - POD | Beverage mug / White / 11oz | beverage-mug-1000000147518916-White | $10.00 x 1 | ScalablePress-Test | Test Scalable Press | https://assets.boostflow.com/phub/phub-rendered/v21616140249_605457d927651Jreuf15929050655ef1cd69228a5.png | https://pb.btdmp.com/phub/phub-rendered/v2/new_mug878788983DAikfzWKje.png |              |
    And fulfill order from CrossPanda
      | Order name  |
      | @OrderName@ |
    And switch to tab "Sent fulfillment request"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity                          | Status                   | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage mug / White / 11oz>PB-AP-BeverageMug-white-11oz-680448>1 item | (Test)CUP HIGH TEMPERATURE>11oz / White / Beverage Mug>>1 item | Sent fulfillment request |               |               |                 |
    And create fulfillment with order SP by API
      | Order name  | carrier | Tracking Number |
      | @OrderName@ | DHLGM   | 374923840238420 |
    And switch to tab "Shipped"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                       | Product nameOdoo>Variant>SKU>Quantity                          | Status  | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Beverage Mug - POD>Beverage mug / White / 11oz>PB-AP-BeverageMug-white-11oz-680448>1 item | (Test)CUP HIGH TEMPERATURE>11oz / White / Beverage Mug>>1 item | Shipped |               |               | 374923840238420 |
    Then redirect to order detail on hive-pbase
    And verify order after fulfilled
      | Order name | Tracking number |
      |            | 374923840238420 |