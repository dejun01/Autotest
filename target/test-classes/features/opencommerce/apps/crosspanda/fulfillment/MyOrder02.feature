Feature: My Orders 02

  Background: Clear all data
    Given clear all data
#staging_crosspanda_my_order
  Scenario: OCP_1.3 - Verify delete import order
    Given login user to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file with Product identity is "Product name"
      | Data                                                                                                                                                                              |
      | @Date@,,,,,,,@OrderProductMappedImported@,Product mapped - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,, |
      | @Date@,,,,,,,@OrderProductUnmapImported@,Product unmap - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,,   |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    Then Verify status of Order on CrossPanda
      | Order name                  | Status   |
      | @OrderProductUnmapImported@ | To order |
    And switch to tab "Ready to fulfill"
    Then Verify status of Order on CrossPanda
      | Order name                   | Status           |
      | @OrderProductMappedImported@ | Ready to fulfill |
    Then Verify status of Order on Odoo
      | Order name                   | Customer               | Status    |
      | @OrderProductMappedImported@ | autoqa1@crosspanda.com | Quotation |
      | @OrderProductUnmapImported@  | autoqa1@crosspanda.com | Quotation |
    Then Verify info of Order on Odoo
      | Order name                   | Product name                                                                                                                | Description                    | Ordered Qty |
      | @OrderProductMappedImported@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) | Product mapped - Default Title | 2.000       |
      | @OrderProductUnmapImported@  | DEFAULT TITLE                                                                                                               | Product unmap - Default Title  | 2.000       |
    And clear search order on CrossPanda
    And switch to tab "All Orders"
    Then delete all order imported in CrossPanda
    And switch to tab "To order"
    Then Verify status of Order on CrossPanda
      | Order name                  | Status     |
      | @OrderProductUnmapImported@ | Not exists |
    And switch to tab "Ready to fulfill"
    Then Verify status of Order on CrossPanda
      | Order name                   | Status     |
      | @OrderProductMappedImported@ | Not exists |
    Then Verify status of Order on Odoo
      | Order name                   | Customer               | Status     |
      | @OrderProductMappedImported@ | autoqa1@crosspanda.com | Not exists |
      | @OrderProductUnmapImported@  | autoqa1@crosspanda.com | Not exists |

  Scenario: OCP_1.4, OCP_1.5 - Verify delete order sync from ShopBase
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Product unmap>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file with Product identity is "Product name"
      | Data                                                                                                                                                                            |
      | @Date@,,,,,,,@OrderProductUnmapImported@,Product unmap - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,, |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And Select paging "100 / page"
    And Search order "Product unmap" on CrossPanda
    Then Select order
      | Order name  |
      | @OrderName@ |
    And "Unable" to select action Delete orders
    Then Deselect order
      | Order name  |
      | @OrderName@ |
    Then Select order
      | Order name                  |
      | @OrderProductUnmapImported@ |
    And "Able" to select action Delete orders
    Then Deselect order
      | Order name                  |
      | @OrderProductUnmapImported@ |
    Then Select order
      | Order name                  |
      | @OrderName@                 |
      | @OrderProductUnmapImported@ |
    And "Able" to select action Delete orders
    Then Select action "Delete orders" on My orders screen
    Then Verify status of Order on CrossPanda
      | Order name                  | Status     |
      | @OrderName@                 | To order   |
      | @OrderProductUnmapImported@ | Not exists |
    Then Verify status of Order on Odoo
      | Order name                  | Customer               | Status     |
      | @OrderName@                 | autoqa1@crosspanda.com | Quotation  |
      | @OrderProductUnmapImported@ | autoqa1@crosspanda.com | Not exists |
    Then Verify info of Order on Odoo
      | Order name  | Product name  | Description                   | Ordered Qty |
      | @OrderName@ | DEFAULT TITLE | Product unmap - Default Title | 2.000       |

  Scenario: OCP_1.6, OCP_1.7  - Verify delete import order has some line item, one of them is fulfilled
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file with Product identity is "Product name"
      | Data                                                                                                                                                                              |
      | @Date@,,,,,,,@OrderProductMappedImported@,Product mapped - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,, |
      | @Date@,,,,,,,@OrderProductMappedImported@,Product unmap - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,,  |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    Then Fulfill order in CrossPanda
      | Order name                   |
      | @OrderProductMappedImported@ |
    Then Select order
      | Order name                   |
      | @OrderProductMappedImported@ |
    And "Able" to select action Delete orders
    Then Select action "Delete orders" on My orders screen
    And switch to tab "To order"
    Then Verify status of Order on CrossPanda
      | Order name                   | Status     |
      | @OrderProductMappedImported@ | Not exists |
    And switch to tab "Sent fulfillment request"
    Then Verify status of Order on CrossPanda
      | Order name                   | Status                   |
      | @OrderProductMappedImported@ | Sent fulfillment request |
    Then Verify status of Order on Odoo
      | Order name                   | Customer               | Status      |
      | @OrderProductMappedImported@ | autoqa1@crosspanda.com | Sales Order |
    Then Verify info of Order on Odoo
      | Order name                   | Product name                                                                                                                | Description                    | Ordered Qty |
      | @OrderProductMappedImported@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) | Product mapped - Default Title | 2.000       |

  Scenario: OCP_1.8 - Import - Mapping product Available stock >= Quantity order
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file with Product identity is "Product name"
      | Data                                                                                                                                                                              |
      | @Date@,,,,,,,@OrderProductMappedImported@,Product mapped - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,, |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then Fulfill order in CrossPanda
      | Order name                   |
      | @OrderProductMappedImported@ |
    And switch to tab "Sent fulfillment request"
    Then Verify status of Order on CrossPanda
      | Order name                   | Status                   |
      | @OrderProductMappedImported@ | Sent fulfillment request |
    Then Verify status of Order on Odoo
      | Order name                   | Customer               | Status      |
      | @OrderProductMappedImported@ | autoqa1@crosspanda.com | Sales Order |
    Then Verify info of Order on Odoo
      | Order name                   | Product name                                                                                                                | Description                    | Ordered Qty |
      | @OrderProductMappedImported@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) | Product mapped - Default Title | 2.000       |
    Then Verify status of Delivery Order on Odoo
      | Order name                   | Customer               | Status |
      | @OrderProductMappedImported@ | autoqa1@crosspanda.com | Ready  |
    Then Verify info of Delivery Order on Odoo
      | Order name                   | Product name                                                                                                                | Customer order number | Shipping Code | Tracking number | Initial Demand | Reserved | Done  |
      | @OrderProductMappedImported@ | Awesome T Shirts For Guys O-Neck Short Sleeve Regular Mens Top Quality Men Playboi Carti Hip-Hop Black Tee Shirt (Black, L) |                       | THPHR         |                 | 2.000          | 2.000    | 0.000 |

  Scenario: OCP_1.9 - Import - Mapping product Available stock = 0, Incomming >= Quantity order + Awaiting stock
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file with Product identity is "Product name"
      | Data                                                                                                                                                                                   |
      | @Date@,,,,,,,@OrderProductMappedImportedAS@,Product mapped AS - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,, |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then Fulfill order in CrossPanda
      | Order name                     |
      | @OrderProductMappedImportedAS@ |
    And switch to tab "Awaiting stock"
    Then Verify status of Order on CrossPanda
      | Order name                     | Status         |
      | @OrderProductMappedImportedAS@ | Awaiting stock |
    Then Verify status of Order on Odoo
      | Order name                     | Customer               | Status      |
      | @OrderProductMappedImportedAS@ | autoqa1@crosspanda.com | Sales Order |
    Then Verify info of Order on Odoo
      | Order name                     | Product name                                                                                                                                           | Description                       | Ordered Qty |
      | @OrderProductMappedImportedAS@ | Men clothes  Anime Naruto Akatsuki 3D Print Men and women T-shirt Summer Casual Harajuku Tops Brand Funny Tee  Boys Sweatshirt (DT-701, Asian size  L) | Product mapped AS - Default Title | 2.000       |
    Then Verify status of Delivery Order on Odoo
      | Order name                     | Customer               | Status  |
      | @OrderProductMappedImportedAS@ | autoqa1@crosspanda.com | Waiting |
    Then Verify info of Delivery Order on Odoo
      | Order name                     | Product name                                                                                                                                           | Customer order number | Shipping Code | Tracking number | Initial Demand | Reserved | Done  |
      | @OrderProductMappedImportedAS@ | Men clothes  Anime Naruto Akatsuki 3D Print Men and women T-shirt Summer Casual Harajuku Tops Brand Funny Tee  Boys Sweatshirt (DT-701, Asian size  L) |                       | THPHR         |                 | 2.000          | 0.000    | 0.000 |

  Scenario: OCP_1.10 - Import - Mapping product Available stock = 0, Incomming < Quantity order + Awaiting stock
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file with Product identity is "Product name"
      | Data                                                                                                                                                                                 |
      | @Date@,,,,,,,@OrderProductMappedImportedF@,Product mapped F - Default Title,2,A123456B,,Shop Base,100 Wilshire Blvd,,Santa Monica,90401,California,United States,2025550147,,null,,, |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then Fulfill order in CrossPanda
      | Order name                    |
      | @OrderProductMappedImportedF@ |
    And switch to tab "Failed"
    Then Verify status of Order on CrossPanda
      | Order name                    | Status |
      | @OrderProductMappedImportedF@ | Failed |
    Then Verify status of Order on Odoo
      | Order name                    | Customer               | Status    |
      | @OrderProductMappedImportedF@ | autoqa1@crosspanda.com | Quotation |
    Then Verify info of Order on Odoo
      | Order name                    | Product name                                                                                                                               | Description                      | Ordered Qty |
      | @OrderProductMappedImportedF@ | Itachi Uchiha T Shirt Men's Ninja T-shirt Naruto Brother Tshirts Amazing Popular Logo Tops Black Tees 100% Cotton Clothes Japan (black, L) | Product mapped F - Default Title | 2.000       |
#    Then Verify status of Delivery Order on Odoo
#      | Order name                    | Customer               | Status  |
#      | @OrderProductMappedImportedF@ | autoqa1@crosspanda.com | Waiting |
#    Then Verify info of Delivery Order on Odoo
#      | Order name                    | Product name                                                                                                                               | Customer order number | Shipping Code | Tracking number | Initial Demand | Reserved | Done  |
#      | @OrderProductMappedImportedF@ | Itachi Uchiha T Shirt Men's Ninja T-shirt Naruto Brother Tshirts Amazing Popular Logo Tops Black Tees 100% Cotton Clothes Japan (black, L) |                       | THPHR         |                 | 2.000          | 0.000    | 0.000 |