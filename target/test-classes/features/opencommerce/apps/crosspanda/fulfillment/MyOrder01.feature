Feature: My Orders 01

  Background: Clear all data
    Given clear all data
#staging_crosspanda_my_order
  Scenario: OCP_1.1 - Verify new order with product unmap
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product unmap>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    Then Verify status of Order on CrossPanda
      | Order name  | Status   |
      | @OrderName@ | To order |
    Then Verify status of Order on Odoo
      | Order name  | Customer               | Status    |
      | @OrderName@ | autoqa1@crosspanda.com | Quotation |
    Then Verify info of Order on Odoo
      | Order name  | Product name  | Description                   | Ordered Qty |
      | @OrderName@ | DEFAULT TITLE | Product unmap - Default Title | 2.000       |

  Scenario: OCP_1.2 - Verify new order with product mapped
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product mapped>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then Verify status of Order on CrossPanda
      | Order name  | Status           |
      | @OrderName@ | Ready to fulfill |
    Then Verify status of Order on Odoo
      | Order name  | Customer               | Status    |
      | @OrderName@ | autoqa1@crosspanda.com | Quotation |
    Then Verify info of Order on Odoo
      | Order name  | Product name                                                                                                                | Description                    | Ordered Qty |
      | @OrderName@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) | Product mapped - Default Title | 2.000       |

  Scenario: OCP_1.8 - Checkout - Mapping product Available stock >= Quantity order
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product mapped>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then Fulfill order in CrossPanda
      | Order name  |
      | @OrderName@ |
    And switch to tab "Sent fulfillment request"
    Then Verify status of Order on CrossPanda
      | Order name  | Status                   |
      | @OrderName@ | Sent fulfillment request |
    Then Verify status of Order on Odoo
      | Order name  | Customer               | Status      |
      | @OrderName@ | autoqa1@crosspanda.com | Sales Order |
    Then Verify info of Order on Odoo
      | Order name  | Product name                                                                                                                | Description                    | Ordered Qty |
      | @OrderName@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) | Product mapped - Default Title | 2.000       |
    Then Verify status of Delivery Order on Odoo
      | Order name  | Customer               | Status |
      | @OrderName@ | autoqa1@crosspanda.com | Ready  |
    Then Verify info of Delivery Order on Odoo
      | Order name  | Product name                                                                                                                | Customer order number | Shipping Code | Tracking number | Initial Demand | Reserved | Done  |
      | @OrderName@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) |                       | THPHR         |                 | 2.000          | 2.000    | 0.000 |

  Scenario: OCP_1.9 - Checkout - Mapping product Available stock = 0, Incomming >= Quantity order + Awaiting stock
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product mapped AS>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then Fulfill order in CrossPanda
      | Order name  |
      | @OrderName@ |
    And switch to tab "Awaiting stock"
    Then Verify status of Order on CrossPanda
      | Order name  | Status         |
      | @OrderName@ | Awaiting stock |
    Then Verify status of Order on Odoo
      | Order name  | Customer               | Status      |
      | @OrderName@ | autoqa1@crosspanda.com | Sales Order |
    Then Verify info of Order on Odoo
      | Order name  | Product name                                                                                                                                           | Description                       | Ordered Qty |
      | @OrderName@ | Men clothes  Anime Naruto Akatsuki 3D Print Men and women T-shirt Summer Casual Harajuku Tops Brand Funny Tee  Boys Sweatshirt (DT-701, Asian size  L) | Product mapped AS - Default Title | 2.000       |
    Then Verify status of Delivery Order on Odoo
      | Order name  | Customer               | Status  |
      | @OrderName@ | autoqa1@crosspanda.com | Waiting |
    Then Verify info of Delivery Order on Odoo
      | Order name  | Product name                                                                                                                                           | Customer order number | Shipping Code | Tracking number | Initial Demand | Reserved | Done  |
      | @OrderName@ | Men clothes  Anime Naruto Akatsuki 3D Print Men and women T-shirt Summer Casual Harajuku Tops Brand Funny Tee  Boys Sweatshirt (DT-701, Asian size  L) |                       | THPHR         |                 | 2.000          | 0.000    | 0.000 |

  Scenario: OCP_1.10 - Checkout - Mapping product Available stock = 0, Incomming < Quantity order + Awaiting stock
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product mapped F>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then Fulfill order in CrossPanda
      | Order name  |
      | @OrderName@ |
    And switch to tab "Failed"
    Then Verify status of Order on CrossPanda
      | Order name  | Status |
      | @OrderName@ | Failed |
    Then Verify status of Order on Odoo
      | Order name  | Customer               | Status    |
      | @OrderName@ | autoqa1@crosspanda.com | Quotation |
    Then Verify info of Order on Odoo
      | Order name  | Product name                                                                                                                               | Description                      | Ordered Qty |
      | @OrderName@ | Itachi Uchiha T Shirt Men's Ninja T-shirt Naruto Brother Tshirts Amazing Popular Logo Tops Black Tees 100% Cotton Clothes Japan (black, L) | Product mapped F - Default Title | 2.000       |
#    Then Verify status of Delivery Order on Odoo
#      | Order name  | Customer               | Status  |
#      | @OrderName@ | autoqa1@crosspanda.com | Waiting |
#    Then Verify info of Delivery Order on Odoo
#      | Order name  | Product name                                                                                                                                | Customer order number | Shipping Code | Tracking number | Initial Demand | Reserved | Done  |
#      | @OrderName@ | Itachi Uchiha T Shirt Men's Ninja T-shirt Naruto Brother Tshirts Amazing Popular Logo Tops Black Tees 100% Cotton Clothes Japan (black / L) |                       | THPHR         |                 | 2.000          | 0.000    | 0.000 |

  Scenario: OCP_1.11 - Checkout - Invalid shipping address
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product mapped>2"
      | Email                   | First Name | Last Name | Address         | Country | City   | Zip code | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 130 Trung Phung | Vietnam | Ha Noi | 100000   | 0923456789 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Alert"
    Then Verify status of Order on CrossPanda
      | Order name  | Status           |
      | @OrderName@ | Ready to fulfill |
    Then Verify status of Order on Odoo
      | Order name  | Customer               | Status    |
      | @OrderName@ | autoqa1@crosspanda.com | Quotation |
    Then Verify info of Order on Odoo
      | Order name  | Product name                                                                                                                | Description                    | Ordered Qty |
      | @OrderName@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) | Product mapped - Default Title | 2.000       |
    Then Verify status of Delivery Order on Odoo
      | Order name  | Customer               | Status     |
      | @OrderName@ | autoqa1@crosspanda.com | Not exists |


