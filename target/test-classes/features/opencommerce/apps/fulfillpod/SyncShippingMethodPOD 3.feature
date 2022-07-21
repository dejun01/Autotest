Feature: Shipping method mapping
  #env = shipping_method_mapping_pod


  Scenario: Verify display shipping method in list shipping method if created POD has shipping name
    Given open shop on storefront
    And checkout successfully via stripe with cart "T_shirt:White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information order on CrossPanda
      | Order number | Product nameSB-Variant>SKU>Quantity                                    | Product nameOdoo-Variant>SKU>Quantity       | Status           | Vendor         | Shipping name      | Shipping cost | Tracking number | Estimated delivery time |
      | @OrderName@  | Gildan cotton t shirt / White / sml>PB-AP-UnisexT-shirt-White-S>1 item | sml / White / Gildan cotton t shirt>>1 item | Ready to fulfill | Test CustomCat | Test POD CustomCat | $1.00         | Available       | 3                       |
    And close browser


  Scenario: Verify not display shipping method in list shipping method if create POD shipping name isEmpty
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T_shirt:White,S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to hive-pbase
    And approved order pod in hive-pbase
      | Order name  | Type  |
      | @OrderName@ | Pbase |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information order on CrossPanda
      | Order number | Product nameSB-Variant>SKU>Quantity                                    | Product nameOdoo-Variant>SKU>Quantity             | Status           | Vendor         | Shipping name | Shipping cost | Tracking number | Estimated delivery time |
      | @OrderName@  | Gildan cotton t shirt / White / sml>PB-AP-UnisexT-shirt-White-S>1 item | White / sml / Gildan ultra ladies t shirt>>1 item | Ready to fulfill | Test CustomCat |               |               |                 |                         |
    And close browser
