Feature: My Orders replace and replace all order
#crosspanda_replace_product

  Background: Clear all data
    Given clear all data

#  Scenario: Create new order before replace
#    Given login to crosspanda dashboard
#    And user navigate to "My orders" screen in CrossPanda
#    And switch to tab "Ready to fulfill"
#    And map order's product with product purchased on CrossPanda
#      | Order number | Product nameSB>Variant          | Odoo product                     | Odoo option>Odoo option value | Msg |
#      | @ByProduct@  | Polarized Fishing Glasses>Black | Haimeikang Thick Velvet Headband | Color>Black                   |     |
#    Given open shop on storefront
#    Then checkout successfully via stripe with cart "Polarized Fishing Glasses"
#      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    Given login to crosspanda dashboard
#    And user navigate to "My orders" screen in CrossPanda
#    And switch to tab "Ready to fulfill"
#    And verify information of the order
#      | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity          | Status           |
#      | Polarized Fishing Glasses>Black>>1 item | Haimeikang Thick Velvet Headband>Black>>1 item | Ready to fulfill |

  Scenario: Replace product in order CrossPanda
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And map order's product with product purchased on CrossPanda
      | Order number | Product nameSB>Variant          | Odoo product                     | Odoo option>Odoo option value | Msg |
      | @ByProduct@  | Polarized Fishing Glasses>Black | Haimeikang Thick Velvet Headband | Color>Black                   |     |
    Given open shop on storefront
    ##1
    Then checkout successfully via stripe with cart "Polarized Fishing Glasses"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    ##2
    Then checkout successfully via stripe with cart "Polarized Fishing Glasses"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    ##2
    Then replace product in order with product "SingleRaod Ver2>S>United States"
      | Order number |
      | @OrderName@  |
    ##2
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity     | Status           |
      | Polarized Fishing Glasses>Black>>1 item | SingleRaod Ver2>S / United States>>1 item | Ready to fulfill |
    ##1
    And verify information of the product variants in another order
      | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity          | Status           |
      | Polarized Fishing Glasses>Black>>1 item | Haimeikang Thick Velvet Headband>Black>>1 item | Ready to fulfill |

  Scenario: Create new order after replace and change mapping
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And map order's product with product purchased on CrossPanda
      | Order number | Product nameSB>Variant          | Odoo product                     | Odoo option>Odoo option value | Msg |
      | @ByProduct@  | Polarized Fishing Glasses>Black | Haimeikang Thick Velvet Headband | Color>Black                   |     |
    Given open shop on storefront
    ##3
    Then checkout successfully via stripe with cart "Polarized Fishing Glasses"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity          | Status           |
      | Polarized Fishing Glasses>Black>>1 item | Haimeikang Thick Velvet Headband>Black>>1 item | Ready to fulfill |
    ##3
    Then Change mapping in order with product
      | Order number | Product name                                          | Variant of product mapping |
      | @OrderName@  | New Dress Girl Kids Children Clothing Cotton Colorful | Color>WAQZ0009;Kid Size>3T |
    ##3
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity                                       | Status           |
      | Polarized Fishing Glasses>Black>>1 item | New Dress Girl Kids Children Clothing Cotton Colorful>WAQZ0009 / 3T>>1 item | Ready to fulfill |
    ##2
    And verify information of the product variants in another order
      | Product nameSB>Variant>SKU>Quantity     | Product nameOdoo>Variant>SKU>Quantity     | Status           |
      | Polarized Fishing Glasses>Black>>1 item | SingleRaod Ver2>S / United States>>1 item | Ready to fulfill |

  Scenario: Replace all before
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then replace product in all order with product "Haimeikang Thick Velvet Headband>Black"
      | Order number | Product name SB       |
      | @ByProduct@  | Bicycle Helmets Matte |
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Bicycle Helmets Matte"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity          | Status           |
      | Bicycle Helmets Matte>1>>1 item     | Haimeikang Thick Velvet Headband>Black>>1 item | Ready to fulfill |

  Scenario: Replace all order and change mapping
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Bicycle Helmets Matte"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then replace product in all order with product "Baby Socks Floor Non-slip Cotton Cartoon>6M"
      | Order number | Product name SB       |
      | @ByProduct@  | Bicycle Helmets Matte |
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity                                | Status           |
      | Bicycle Helmets Matte>1>>1 item     | Baby Socks Floor Non-slip Cotton Cartoon>lion with bell / 6M>>1 item | Ready to fulfill |
    Then Change mapping in order with product
      | Order number | Product name                                          | Variant of product mapping |
      | @OrderName@  | New Dress Girl Kids Children Clothing Cotton Colorful | Color>WAQZ0009;Kid Size>3T |
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity                                | Status           |
      | Bicycle Helmets Matte>1>>1 item     | Baby Socks Floor Non-slip Cotton Cartoon>lion with bell / 6M>>1 item | Ready to fulfill |

  Scenario: Create new order after replace product in all order
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Bicycle Helmets Matte"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information of the order
      | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity                                | Status           |
      | Bicycle Helmets Matte>1>>1 item     | Baby Socks Floor Non-slip Cotton Cartoon>lion with bell / 6M>>1 item | Ready to fulfill |

#  Scenario: Verify data mapping of the order in the tab Awaiting stock, Sent fulfillment request, Shipped
#    Given open shop on storefront
#    Then checkout successfully via stripe with cart "Bicycle Helmets Matte"
#      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    Given login to crosspanda dashboard
#    And user navigate to "My orders" screen in CrossPanda
#    And switch to tab "Ready to fulfill"
#    Then Change mapping in order with product
#      | Product name                                          | Variant of product mapping |
#      | New Dress Girl Kids Children Clothing Cotton Colorful | Color>WAQZ0009;Kid Size>3T |
#    Then Fulfill order in CrossPanda
#      | Order name  |
#      | @OrderName@ |
#    And switch to tab "Failed"
#    And verify information of the order
#      | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity                                | Status |
#      | Bicycle Helmets Matte>1>>1 item     | Baby Socks Floor Non-slip Cotton Cartoon>lion with bell / 6M>>1 item | Failed |
#    And switch to tab "Sent fulfillment request"
#    And verify information of the order
#      | Start Time       | End Time         | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity          | Status                   |
#      | 2020/09/10 00:00 | 2020/10/15 00:00 | Bicycle Helmets Matte>1>>1 item     | Haimeikang Thick Velvet Headband>Black>>1 item | Sent fulfillment request |