Feature: Sync Order to CrossPanda 02
#crosspanda_sync_orders_to_app
  Scenario: Verify order in app after fulfillment quantity of lineitem
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Colorful Candy Polymer:A>2;Charms for Slime Addition:6 Pieces Mix Color>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                           | Product nameOdoo>Variant>SKU>Quantity                        | Status           | Shipping name                   | Shipping cost               | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>14:29#A>2 items                                      | Colorful Candy Polymer>A>Clay-A>2 items                      | Ready to fulfill | Yun Express Standard shipping   | $18.08 / 8-14 business days |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>14:29#6 Pieces Mix Color>2 items | Charms for Slime Addition>6 Pieces Mix Color>6-Color>2 items | Ready to fulfill | Yun Express Electronic shipping | $25.91 / 8-12 business days |                 |
    Then redirect to shopbase dashboard
    And user navigate to "Orders" screen
    And fulfill order on Shopbase dashboard
      | Order number | Product:Variant>Quantity                                                  | Tracking number |
      | @OrderName@  | Colorful Candy Polymer:A>1;Charms for Slime Addition:6 Pieces Mix Color>1 |                 |
    Given redirect to crosspanda
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                          | Product nameOdoo>Variant>SKU>Quantity                       | Status           | Shipping name                   | Shipping cost               | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item                                      | Colorful Candy Polymer>A>Clay-A>1 item                      | Ready to fulfill | Yun Express Standard shipping   | $11.04 / 8-14 business days |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>14:29#6 Pieces Mix Color>1 item | Charms for Slime Addition>6 Pieces Mix Color>6-Color>1 item | Ready to fulfill | Yun Express Electronic shipping | $12.45 / 8-12 business day  |                 |
    And fulfill order from CrossPanda
      | Order number |
      | @OrderName@  |
    And switch to tab "Sent fulfillment request"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                          | Product nameOdoo>Variant>SKU>Quantity                       | Status                   | Shipping name                   | Shipping cost               | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item                                      | Colorful Candy Polymer>A>Clay-A>1 item                      | Sent fulfillment request | Yun Express Standard shipping   | $11.04 / 8-14 business days |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>14:29#6 Pieces Mix Color>1 item | Charms for Slime Addition>6 Pieces Mix Color>6-Color>1 item | Sent fulfillment request | Yun Express Electronic shipping | $12.45 / 8-12 business day  |                 |
    Then close browser


  Scenario: Verify order in app after fulfillment all lineitem
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Colorful Candy Polymer:A;Charms for Slime Addition:6 Pieces Mix Color"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                          | Product nameOdoo>Variant>SKU>Quantity                       | Status           | Shipping name                   | Shipping cost               | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item                                      | Colorful Candy Polymer>A>Clay-A>1 item                      | Ready to fulfill | Yun Express Standard shipping   | $11.04 / 8-14 business days |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>14:29#6 Pieces Mix Color>1 item | Charms for Slime Addition>6 Pieces Mix Color>6-Color>1 item | Ready to fulfill | Yun Express Electronic shipping | $12.45 / 8-12 business day  |                 |
    Then redirect to shopbase dashboard
    And user navigate to "Orders" screen
    And fulfill order on Shopbase dashboard
      | Order number | Product:Variant>Quantity                     | Tracking number |
      | @OrderName@  | Charms for Slime Addition:1 Piece Randomly>1 |                 |
    Given redirect to crosspanda
    Given user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                          | Product nameOdoo>Variant>SKU>Quantity                       | Status           | Shipping name                   | Shipping cost               | Tracking number |
      | false   | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item                                      | Colorful Candy Polymer>A>Clay-A>1 item                      | Ready to fulfill | Yun Express Standard shipping   | $11.04 / 8-14 business days |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>14:29#6 Pieces Mix Color>1 item | Charms for Slime Addition>6 Pieces Mix Color>6-Color>1 item | Ready to fulfill | Yun Express Electronic shipping | $12.45 / 8-12 business day  |                 |
    And fulfill order from CrossPanda
      | Order number |
      | @OrderName@  |
    And switch to tab "Sent fulfillment request"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                                          | Product nameOdoo>Variant>SKU>Quantity                       | Status                   | Shipping name                   | Shipping cost               | Tracking number |
      | false   | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item                                      | Colorful Candy Polymer>A>Clay-A>1 item                      | Ready to fulfill         | Yun Express Standard shipping   | $11.04 / 8-14 business days |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>14:29#6 Pieces Mix Color>1 item | Charms for Slime Addition>6 Pieces Mix Color>6-Color>1 item | Sent fulfillment request | Yun Express Electronic shipping | $12.45 / 8-12 business day  |                 |
    Then close browser

  Scenario: Verify order in app after fulfillment all order
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Colorful Candy Polymer:A"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity  | Status           | Shipping name                 | Shipping cost               | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item | Colorful Candy Polymer>A>Clay-A>1 item | Ready to fulfill | Yun Express Standard shipping | $11.04 / 8-14 business days |                 |
    Then redirect to shopbase dashboard
    And user navigate to "Orders" screen
    And fulfill order on Shopbase dashboard
      | Order number | Product:Variant>Quantity   | Tracking number |
      | @OrderName@  | Colorful Candy Polymer:A>1 |                 |
    Given redirect to crosspanda
    Given user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity  | Status           | Shipping name                 | Shipping cost               | Tracking number |
      | false   | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item | Colorful Candy Polymer>A>Clay-A>1 item | Ready to fulfill | Yun Express Standard shipping | $11.04 / 8-14 business days |                 |
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity  | Status           | Shipping name                 | Shipping cost               | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>14:29#A>0 item | Colorful Candy Polymer>A>Clay-A>0 item | Ready to fulfill | Yun Express Standard shipping | $11.04 / 8-14 business days |                 |
    Then close browser





