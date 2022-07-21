Feature: Sync Order to CrossPanda 04
#crosspanda_sync_orders_to_app
  Scenario: Verify sync customer infor
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Colorful Candy Polymer:A"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity  | Status           | Shipping name                 | Shipping cost               | Tracking number |
      | true    | @OrderName@  | Colorful Candy Polymer>A>14:29#A>1 item | Colorful Candy Polymer>A>Clay-A>1 item | Ready to fulfill | Yun Express Standard shipping | $11.04 / 8-14 business days |                 |
    And verify customer infor of order CrossPanda
      | Order number | Name      | Phone      | Country       | Address           | Apartment | City         | State      | Zipcode |
      | @OrderName@  | Shop Base | 2025550147 | United States | 100 Wilshire Blvd |           | Santa Monica | California | 90401   |
    And change customer infor of order in CrossPanda
      | Order number | Name            | Phone      | Country       | Address                     | Apartment | City         | State      | Zipcode |
      | @OrderName@  | ShopBase Change | 3123076918 | United States | REPUBLICA DE GUATEMALA 1463 | 28160     | Santa Monica | California | 28160   |
    Then redirect to shopbase dashboard
    And user navigate to "Orders" screen
    Then verify customer infor of order in Shopbase
      | KEY | Order number | First name | Last name | Phone      | Address                     | Apart | City         | State      | Country       | Zipcode |
      | 1   |              | ShopBase   | Change    | 3123076918 | REPUBLICA DE GUATEMALA 1463 | 28160 | Santa Monica | California | United States | 28160   |
    Then close browser

  Scenario: Verify sync shipping method defaut of order
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
    And close browser