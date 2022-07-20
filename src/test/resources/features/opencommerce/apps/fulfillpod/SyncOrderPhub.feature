Feature: Sync order Phub
  #crosspanda_sync_order_phub

  Scenario: Verify information order Phub is sync on CrossPanda
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Tracker Bottle;Unisex T-shirt:White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    When call api to create next payment or charge payment Print Hub
    When call api to create next payment or charge payment Print Hub
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                                                               | Product nameOdoo>Variant>SKU>Quantity | Status           | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Unisex T-shirt>Unisex T-shirt / White / S>PH-AP-UnisexT-shirt-White-S-872775>1 item                               |                                       | Ready to fulfill |               |               |                 |
      | true    | @OrderName@  | Tracker Bottle>Tracker Bottle / All over print / One size>PH-AOP-TrackerBottle-Alloverprint-Onesize-948283>1 item |                                       | To order         |               |               |                 |
    And verify some field order pod on CrossPanda
      | Is show | Order name  | ProductName    | Variant                                    | Design code                            | Basecost | SupplierName | Shipping method | Mockup                                                                                                     | Artwork font                                                                              | Artwork back                                                                              |
      | true    | @OrderName@ | Unisex T-shirt | Unisex T-shirt / White / S                 | gildan-cotton-t-shirt-1000000119303413 |          |              |                 | https://assets.boostflow.com/phub/phub-rendered/v2/6N9P6HcYMGwR80dVec0.jpg                                 | https://pb.btdmp.com/pbase/shop-id/10053120/artworks/7c349910a79d31a30946d8d940b08d54.png | https://pb.btdmp.com/pbase/shop-id/10053120/artworks/7c349910a79d31a30946d8d940b08d54.png |
      | true    | @OrderName@ | Tracker Bottle | Tracker Bottle / All over print / One size | Tracker Bottle-1000000119239389        |          |              |                 | https://assets.boostflow.com/phub/phub-rendered/v21606544576_5fc1ecc098298nFkXm16043742045fa0cebc2ce4f.png | https://pb.btdmp.com/pbase/shop-id/10053120/artworks/7c349910a79d31a30946d8d940b08d54.png |                                                                                           |
    And close browser

  Scenario: Verify order import Phub is sync on CrossPanda
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Order import CSV" screen
    And import order to PrintHub
      | Data                                                                                                                                                                              |
      | @Date@,,,,,,,@OrderProductMappedImported@,Product mapped - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,, |
    And pay for order import phub
      | Order number |
      | #imph1001    |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                               | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Unisex Hoodie>Unisex Hoodie / Red / XL>example-direct-to-garment-product>1 item   |                                       | To order |               |               |                 |
      | true    | @OrderName@  | Unisex Hoodie>Unisex Hoodie / White / XL>example-direct-to-garment-product>1 item |                                       | To order |               |               |                 |
    And verify some field order pod on CrossPanda
      | Is show | Order name  | ProductName   | Variant                    | Design code | Basecost | SupplierName | Shipping method | Mockup | Artwork font | Artwork back |
      | true    | @OrderName@ | Unisex Hoodie | Unisex Hoodie / Red / XL   |             |          |              |                 |        |              |              |
      | true    | @OrderName@ | Unisex Hoodie | Unisex Hoodie / White / XL |             |          |              |                 |        |              |              |
    And close browser
