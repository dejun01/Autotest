#staging_crosspanda_cancel_fulfillment

Feature: Cancel fulfillment order CrossPanda

  Scenario Outline: Verify cancel fulfillment 1 lineitem when order in Sent fulfillment request
    Given open shop on storefront
    Given create order on Shopbase as "<KEY>"
      | KEY | Product name:Variant>Quantity | Email                | Last name | First name | Phone number | Country | Address                                         | City   | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | new summer Che:Black>2        | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And fulfill order from CrossPanda as "<KEY>"
      | KEY | Order number |
      | 1   |              |
    Then switch to tab "Sent fulfillment request"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity            | Product nameOdoo>Variant>SKU>Quantity             | Status                   | Shipping name         | Shipping cost               | Tracking number |
      | 1   | true    |              | new summer Che>free size / black>#SumB>2 items | New Summer Sexy Che>Free Size / black>FRS>2 items | Sent fulfillment request | Mexico Express Packet | $3.65 / 10-30 business days |                 |
    Then cancel fulfillment order "<KEY>" from CrossPanda
      | KEY | Order number | Product name   | Variant |
      | 1   |              | new summer Che | Black   |
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity            | Product nameOdoo>Variant>SKU>Quantity             | Status           | Shipping name         | Shipping cost               | Tracking number |
      | 1   | true    |              | new summer Che>free size / black>#SumB>2 items | New Summer Sexy Che>Free Size / black>FRS>2 items | Ready to fulfill | Mexico Express Packet | $3.65 / 10-30 business days |                 |
    Then verify status of Order on Odoo as "<KEY>"
      | KEY | Order number | Customer               | Status    |
      | 1   |              | autoqa1@crosspanda.com | Quotation |
    And verify status DO out on Odoo as "<KEY>"
      | KEY | Order number | Customer               | Status    |
      | 1   |              | autoqa1@crosspanda.com | Cancelled |
    Examples:
      | KEY |
      | 1   |

  Scenario Outline: Verify cancel fulfillment 1 lineitem when order in Awaiting stock
    Given open shop on storefront
    Given create order on Shopbase as "<KEY>"
      | KEY | Product name:Variant>Quantity     | Email               | Last name | First name | Phone number | Country       | Address             | City   | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | Cairbull Cycling Helmet:White,M>1 | Mary@mailtothis.com | Mary      | Cornelius  | 303-821-7608 | United States | 56  McKinley Avenue | Denver | 2533      | Colorado              | 80221           |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And fulfill order from CrossPanda as "<KEY>"
      | KEY | Order number |
      | 1   |              |
    Then switch to tab "Awaiting stock"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity         | Product nameOdoo>Variant>SKU>Quantity              | Status         | Shipping name                 | Shipping cost              | Tracking number |
      | 1   | true    |              | Cairbull Cycling Helmet>white / M>WM>1 item | New Summer Sexy Che>Free Size / white1>FSW1>1 item | Awaiting stock | Yun Express Standard shipping | $9.49 / 8-14 business days |                 |
    Then cancel fulfillment order "<KEY>" from CrossPanda
      | KEY | Order number | Product name            | Variant   |
      | 1   |              | Cairbull Cycling Helmet | white / M |
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity         | Product nameOdoo>Variant>SKU>Quantity              | Status           | Shipping name                 | Shipping cost              | Tracking number |
      | 1   | true    |              | Cairbull Cycling Helmet>white / M>WM>1 item | New Summer Sexy Che>Free Size / white1>FSW1>1 item | Ready to fulfill | Yun Express Standard shipping | $9.49 / 8-14 business days |                 |
    Then verify status of Order on Odoo as "<KEY>"
      | KEY | Order number | Customer               | Status    |
      | 1   |              | autoqa1@crosspanda.com | Quotation |
    And verify status DO out on Odoo as "<KEY>"
      | KEY | Order number | Customer               | Status    |
      | 1   |              | autoqa1@crosspanda.com | Cancelled |
    Examples:
      | KEY |
      | 1   |

  Scenario Outline: Verify cancel fulfillment multi lineitems
    Given open shop on storefront
    Given create order on Shopbase as "<KEY>"
      | KEY | Product name:Variant>Quantity                                       | Email                | Last name | First name | Phone number | Country       | Address                                         | City   | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | M001 - Limited Edition:Small>1;M001 - Limited Edition:Medium>1      | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico        | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
      | 2   | Cairbull Cycling Helmet:White,M>1;Cairbull Cycling Helmet:White,L>1 | Mary@mailtothis.com  | Mary      | Cornelius  | 303-821-7608 | United States | 56  McKinley Avenue                             | Denver | 2533      | Colorado              | 80221           |
    Given login to crosspanda dashboard
    Then switch to tab "Ready to fulfill"
    And fulfill order from CrossPanda as "<KEY>"
      | KEY | Order number |
      | 1   |              |
      | 2   |              |
    Then switch to tab "<Sent fulfillment request>"
    Then cancel fulfillment order "<KEY>" from CrossPanda
      | KEY | Order number | Product name | Variant |
      | 1   |              |              |         |
    Then switch to tab "<Awaiting stock>"
    Then cancel fulfillment order "<KEY>" from CrossPanda
      | KEY | Order number | Product name            | Variant   |
      | 2   |              | Cairbull Cycling Helmet | white / L |
    Then switch to tab "Ready to fulfill"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity | Status           | Shipping name | Shipping cost | Tracking number |
      | 1   | true    |              |                                     |                                       | Ready to fulfill |               |               |                 |
      | 2   | true    |              |                                     |                                       | Ready to fulfill |               |               |                 |
    And switch to tab "Sent fulfillment request"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity | Status           | Shipping name | Shipping cost | Tracking number |
      | 1   | true    |              |                                     |                                       | Ready to fulfill |               |               |                 |
    Then verify status of Order on Odoo as "<KEY>"
      | KEY | Order number | Customer               | Status    |
      | 1   |              | autoqa1@crosspanda.com | Quotation |
    And verify status DO out on Odoo as "<KEY>"
      | KEY | Order number | Customer               | Status    |
      | 1   |              | autoqa1@crosspanda.com | Cancelled |
    Examples:
      | KEY |
      | 1   |
      | 2   |

  Scenario Outline: Verify button Cancel fulfillment with order get tracking number
    Given create order on Shopbase as "<KEY>"
      | KEY | Product name:Variant>Quantity | Email                | Last name | First name | Phone number | Country | Address                                         | City   | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | new summer Che:Black>2        | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And fulfill order from CrossPanda as "<KEY>"
      | KEY | Order number |
      | 1   |              |
    Then switch to tab "Awaiting stock"
    And get tracking number as "<KEY>"
      | KEY | Order number |
      | 1   |              |
    And verify button Cancel fulfillment as "<KEY>"
      | KEY | Product name | Variant |
      | 1   |              |         |
    Examples:
      | KEY |
      | 1   |

  Scenario Outline: Verify cancel fulfillment with order has line Shipped
    Given create order on Shopbase as "<KEY>"
      | KEY | Product name:Variant>Quantity                                              | Email                | Last name | First name | Phone number | Country | Address                                         | City   | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | Swiss Tech 7 in 1 EDC Key>1;100Pcs/set Pro Disposable Blue:United States>1 | Kelly@mailtothis.com | Kelly     | Sioux      | 3123076918   | Mexico  | REPUBLICA DE GUATEMALA 1463, PABLO SILVA GARCIA | Colima | 2533      | Colima                | 28160           |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And fulfill order from CrossPanda as "<KEY>"
      | KEY | Order number |
      | 1   |              |
    Then done DO buyer on Odoo "<KEY>"
      | KEY | Product name | Owner |
      | 1   |              |       |
    Then cancel fulfillment order "<KEY>" from CrossPanda
      | KEY | Order number | Product name            | Variant   |
      | 2   |              | Cairbull Cycling Helmet | white / L |
    And switch to tab "Shipped"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity | Status  | Shipping name | Shipping cost | Tracking number |
      | 1   | true    |              |                                     |                                       | Shipped |               |               |                 |
    And switch to tab "Ready to fulfill"
    And verify information order of CrossPanda as "<KEY>"
      | KEY | Is show | Order number | Product nameSB>Variant>SKU>Quantity | Product nameOdoo>Variant>SKU>Quantity | Status           | Shipping name | Shipping cost | Tracking number |
      | 1   | true    |              |                                     |                                       | Ready to fulfill |               |               |                 |
    Examples:
      | KEY |
      | 1   |












