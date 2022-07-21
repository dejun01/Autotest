Feature: Sync order Dropship

  Scenario: Verify order in app after refund quantity of lineitem
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Colorful Candy Polymer:A>2;Charms for Slime Addition:6 Pieces Mix Color>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                   | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>>2 items                     |                                       | To order |               |               |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>>2 items |                                       | To order |               |               |                 |
    And user login to shopbase dashboard
    And user navigate to "Orders" screen
    And refund order on Shopbase dashboard
      | Order number | Product:Variant>Quantity                                                  |
      | @OrderName@  | Colorful Candy Polymer:A>1;Charms for Slime Addition:6 Pieces Mix Color>1 |
    Given redirect to crosspanda
    Given user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                  | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>>1 item                     |                                       | To order |               |               |                 |
      | true    | @OrderName@  | Charms for Slime Addition>6 Pieces Mix Color>>1 item |                                       | To order |               |               |                 |
    Then close browser

  Scenario: Verify order in app after cancel order
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Colorful Candy Polymer:A>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>>2 items   |                                       | To order |               |               |                 |
    And user login to shopbase dashboard
    And user navigate to "Orders" screen
    And cancel order on Shopbase dashboard
      | Order number |
      | @OrderName@  |
    Given redirect to crosspanda
    Given user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity | Status | Shipping name | Shipping cost | Tracking number |
      | false   | @OrderName@  | Colorful Candy Polymer>A>>2 items   |                                       |        |               |               |                 |
    And switch to tab "All Orders"
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>>0 item    |                                       | To order |               |               |                 |
    Then close browser

  Scenario: Verify order in app when product is mapped
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Charms for Slime Addition:7 Pieces Mix Color>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity                   | Product nameOdoo>Variant>SKU>Quantity  | Status           | Shipping name                 | Shipping cost | Tracking number |
      | true    | @OrderName@  | Charms for Slime Addition>7 Pieces Mix Color>>2 items | Women Vintage Ruffle>Blue / S>>2 items | Ready to fulfill | Yun Express Standard shipping | $2.86         |                 |
    Then close browser
