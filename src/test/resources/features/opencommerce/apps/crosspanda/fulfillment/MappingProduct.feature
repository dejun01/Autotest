Feature: Mapping product
#crosspanda_mapping_product

  Scenario Outline: Mapping product invalid
    Given Description: "<Test case>"
    Given open shop on storefront
    And create order on Shopbase as "<KEY>"
      | KEY | Product name:Variant>Quantity | Email                | Last name | First name | Phone number | Country | Address                                         | City   | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | Doesnt select option:S>2      | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
      | 2   | doesnt select value:S,Red>2   | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
      | 3   | doesnt select value:S,Red>2   | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
      | 4   | Less option:S>2               | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity         | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | 1   | true    |              | Doesnt select option>S>S>2 items            |                                       | To order |               |               |                 |
      | 2   | true    |              | doesnt select value>S / Red>SRED>2 items    |                                       | To order |               |               |                 |
      | 3   | true    |              | doesnt select value>S / Red>SRED>2 items    |                                       | To order |               |               |                 |
      | 4   | true    |              | Less option>S / Black / Viet Nam>SB>2 items |                                       | To order |               |               |                 |
    And verify customer infor of order CrossPanda "<KEY>"
      | KEY | Order number | Name        | Phone      | Country | Address                                         | Apartment | City   | State  | Zipcode |
      | 1   |              | Sioux Kelly | 3123076918 | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | 2533      | Colima | Colima | 28160   |
      | 2   |              | Sioux Kelly | 3123076918 | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | 2533      | Colima | Colima | 28160   |
      | 3   |              | Sioux Kelly | 3123076918 | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | 2533      | Colima | Colima | 28160   |
      | 4   |              | Sioux Kelly | 3123076918 | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | 2533      | Colima | Colima | 28160   |
    And map order's product with product purchased on CrossPanda as "<KEY>"
      | KEY | Order number | Product nameSB>Variant           | Odoo product    | Odoo option>Odoo option value | Msg                                                                                               |
      | 1   |              | Doesnt select option>S           | Handmade Beauty |                               | Option value can’t be blank!                                                                      |
      | 2   |              | doesnt select value>S / Red      | Handmade Beauty | Size;Color                    | Value of option Size can’t be blank                                                               |
      | 3   |              | doesnt select value>S / Red      | Handmade Beauty | Size>XL;Size                  | Your options are duplicate. Please select another value!                                          |
      | 4   |              | Less option>S / Black / Viet Nam | Handmade Beauty | Size>XS,XL;Color>Blue         | The number of Product mapping options cannot be less than the number of Platform product options. |
    And switch to tab "To order"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity         | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | 1   | true    |              | Doesnt select option>S>S>2 items            |                                       | To order |               |               |                 |
      | 2   | true    |              | doesnt select value>S / Red>SRED>2 items    |                                       | To order |               |               |                 |
      | 3   | true    |              | doesnt select value>S / Red>SRED>2 items    |                                       | To order |               |               |                 |
      | 4   | true    |              | Less option>S / Black / Viet Nam>SB>2 items |                                       | To order |               |               |                 |
    Examples:
      | KEY | Test case                                                                                             |
      | 1   | Mapping product when user doesn't select option product Odoo                                          |
      | 2   | Mapping product when user doesn't select option value product Odoo                                    |
      | 3   | Mapping product when user select duplicate option product Odoo                                        |
      | 4   | Mapping product when the number of Product SB options be more than the number of Odoo product options |

  Scenario: Precondition mapping
    Given login to crosspanda dashboard
    Then user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And check data mapping product then mapping product
      | Product                | Odoo product                            | Odoo option>Odoo option value | Msg |
      | M001 - Limited Edition | Charms for Slime Colorful Candy Polymer | Color>A,B                     |     |
    And verify information order of CrossPanda
      | Is show | Order number | Product nameSB>Variant>SKU>Quantity   | Product nameOdoo>Variant>SKU>Quantity                   | Status           | Shipping name         | Shipping cost               | Tracking number |
      | true    | ByProduct    | M001 - Limited Edition>Small>S>1 item | Charms for Slime Colorful Candy Polymer>A>Clay-A>1 item | Ready to fulfill | Mexico Express Packet | $8.29 / 10-30 business days |                 |
    Then close browser

  Scenario Outline: Mapping product success
    Given Description: "<Test case>"
    Given open shop on storefront
    And create order on Shopbase as "<KEY>"
      | KEY | Product name:Variant>Quantity                                  | Email                | Last name | First name | Phone number | Country | Address                                         | City   | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | H001 - Limited Edition:Pink>1                                  | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
      | 2   | M001 - Limited Edition:Small>1;M001 - Limited Edition:Medium>1 | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
      | 3   | T003 - Limited Edition:Black Heather,S>1                       | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
      | 4   | T003 - Limited Variant>1                                       | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
    Then login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity                | Product nameOdoo>Variant>SKU>Quantity                   | Status           | Shipping name         | Shipping cost               | Tracking number |
      | 1   | true    |              | H001 - Limited Edition>Pink>P>1 item               | 6pcs/Lot Charms for Slime Addition>Pink>pinkC>1 item    | Ready to fulfill | Mexico Express Packet | $6.74 / 10-30 business days |                 |
      | 2   | true    |              | M001 - Limited Edition>Small>S>1 item              | Charms for Slime Colorful Candy Polymer>A>Clay-A>1 item | Ready to fulfill | Mexico Express Packet | $8.29 / 10-30 business days |                 |
      | 2   | true    |              | M001 - Limited Edition>Medium>M>1 item             | Charms for Slime Colorful Candy Polymer>B>Clay-B>1 item | Ready to fulfill | Mexico Express Packet | $8.29 / 10-30 business days |                 |
      | 3   | true    |              | T003 - Limited Edition>Black Heather / S>SB>1 item | Non-Variant>Default Title>Non-V>1 item                  | Ready to fulfill | Mexico Express Packet | $3.65 / 10-30 business days |                 |
      | 4   | true    |              | T003 - Limited Variant>Default Title>NV>1 item     | Sparkly Boho Evening Dress>Sky Blue / 2>SKB>1 item      | Ready to fulfill | Mexico Express Packet | $3.65 / 10-30 business days |                 |
    And map order's product with product purchased on CrossPanda as "<KEY>"
      | KEY | Order number | Product nameSB>Variant       | Odoo product    | Odoo option>Odoo option value | Msg |
      | 2   |              | M001 - Limited Edition>Small | Handmade Beauty | Size>XS;Color>Blue            |     |
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity   | Product nameOdoo>Variant>SKU>Quantity | Status           | Shipping name         | Shipping cost                | Tracking number |
      | 2   | true    |              | M001 - Limited Edition>Small>S>1 item | Handmade Beauty>XS / Blue>XSB>1 item  | Ready to fulfill | Mexico Express Packet | $10.61 / 10-30 business days |                 |
    And switch to tab "To order"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity    | Product nameOdoo>Variant>SKU>Quantity | Status   | Shipping name | Shipping cost | Tracking number |
      | 2   | true    |              | M001 - Limited Edition>Medium>M>1 item |                                       | To order |               |               |                 |
    And map order's product with product purchased on CrossPanda as "<KEY>"
      | KEY | Order number | Product nameSB>Variant        | Odoo product                            | Odoo option>Odoo option value | Msg |
      | 2   |              | M001 - Limited Edition>Medium | Charms for Slime Colorful Candy Polymer | Color>A,B                     |     |
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity    | Product nameOdoo>Variant>SKU>Quantity                   | Status           | Shipping name         | Shipping cost               | Tracking number |
      | 2   | true    |              | M001 - Limited Edition>Small>S>1 item  | Charms for Slime Colorful Candy Polymer>A>Clay-A>1 item | Ready to fulfill | Mexico Express Packet | $8.29 / 10-30 business days |                 |
      | 2   | true    |              | M001 - Limited Edition>Medium>M>1 item | Charms for Slime Colorful Candy Polymer>B>Clay-B>1 item | Ready to fulfill | Mexico Express Packet | $8.29 / 10-30 business days |                 |
    Examples:
      | KEY | Test case                                                 |
      | 1   | Verify new order after product is mapped                  |
      | 2   | Change mapping                                            |
      | 3   | Mapping product with product Odoo non-variant             |
      | 4   | Mapping product non-variant with product Odoo has variant |
