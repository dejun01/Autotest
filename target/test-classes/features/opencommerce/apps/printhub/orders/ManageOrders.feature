Feature: As a merchant, I want to manage orders of my store. Orders need have status and info orders correctly
  #shop: au-ph-manage-orders.stag.myshopbase.net
  #acc: shopbase@beeketing.net, pass: 123456
  #environment: staging_print_hub_manage_orders

  Scenario Outline: Verify status of orders when hold sup
    Given Description: "<Testcase>"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Multiple product:Unisex T-shirt,White,S>1;POD Multil:Beach Shorts,S>1"
      | Email                   | First Name | Last Name | Address          | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane | United States | Los Angeles | 90001    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab              |
      | 2   | Awaiting Payment |
    And search order Print Hub
    And click to button in order detail as "<KEY>"
      | KEY | Button | Product        |
      | 2   | Hold   | Unisex T-shirt |
    And verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Status line      | Product name     | Lineitems                         | SKU                                  | Is sync |
      | 2   | On Hold          | Multiple product | Unisex T-shirt / White / S        | PH-AP-UnisexT-shirt-White-S          | false   |
      | 2   | Awaiting Payment | POD Multil       | Beach Shorts / All over print / S | PH-AOP-AOPBeachShorts-Alloverprint-S | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 2   | 8.99         | 6.99           |
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab     |
      | 2   | On Hold |
    And verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Status line      | Product name     | Lineitems                         | SKU                                  | Is sync |
      | 2   | On Hold          | Multiple product | Unisex T-shirt / White / S        | PH-AP-UnisexT-shirt-White-S          | true    |
      | 2   | Awaiting Payment | POD Multil       | Beach Shorts / All over print / S | PH-AOP-AOPBeachShorts-Alloverprint-S | false   |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 2   | 4.99         | 4.99           |
    Examples: <Key>
      | KEY | Status    | Testcase             |
      | 2   | Partially | Orders when hold sup |


  Scenario Outline: Verify can cancel sub in status hold
    Given Description: "<Testcase>"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Multiple product:Unisex T-shirt,White,S>1;POD Multil:Beach Shorts,S>1"
      | Email                   | First Name | Last Name | Address          | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane | United States | Los Angeles | 90001    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab              |
      | 2   | Awaiting Payment |
      | 2   | Awaiting Payment |
    And search order Print Hub
    And click to button in order detail as "<KEY>"
      | KEY | Button | Product        |
      | 2   | Hold   | Unisex T-shirt |
      | 2   | Cancel | Beach Shorts   |
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab     |
      | 2   | On Hold |
    And click to button in order detail as "<KEY>"
      | KEY | Button | Product        |
      | 2   | Cancel | Unisex T-shirt |
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab       |
      | 2   | Cancelled |
    And verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Status line | Product name     | Lineitems                         | SKU                                  | Is sync |
      | 2   | Cancelled   | Multiple product | Unisex T-shirt / White / S        | PH-AP-UnisexT-shirt-White-S          | true    |
      | 2   | Cancelled   | POD Multil       | Beach Shorts / All over print / S | PH-AOP-AOPBeachShorts-Alloverprint-S | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 2   | 13.98        | 11.98          |
    Examples: <Key>
      | KEY | Status    | Testcase                              |
      | 2   | Cancelled | Orders when cancel sup in status hold |

  Scenario Outline: Verify status of orders when cancel sup
    Given Description: "<Testcase>"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Multiple product:Unisex T-shirt,White,S>1;POD Multil:Beach Shorts,S>1"
      | Email                   | First Name | Last Name | Address          | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane | United States | Los Angeles | 90001    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab              |
      | 3   | Awaiting Payment |
    And search order Print Hub
    And click to button in order detail as "<KEY>"
      | KEY | Button | Product        |
      | 3   | Cancel | Unisex T-shirt |
    And verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Status line      | Product name     | Lineitems                         | SKU                                  | Is sync |
      | 3   | Cancelled        | Multiple product | Unisex T-shirt / White / S        | PH-AP-UnisexT-shirt-White-S          | false   |
      | 3   | Awaiting Payment | POD Multil       | Beach Shorts / All over print / S | PH-AOP-AOPBeachShorts-Alloverprint-S | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 3   | 8.99         | 6.99           |
    And switch to tab on Manage Orders of Print Hub as "<KEY>"
      | KEY | Tab       |
      | 3   | Cancelled |
    And verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Status line      | Product name     | Lineitems                         | SKU                                  | Is sync |
      | 3   | Cancelled        | Multiple product | Unisex T-shirt / White / S        | PH-AP-UnisexT-shirt-White-S          | true    |
      | 3   | Awaiting Payment | POD Multil       | Beach Shorts / All over print / S | PH-AOP-AOPBeachShorts-Alloverprint-S | false   |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 3   | 4.99         | 4.99           |
    Examples: <Key>
      | KEY | Status    | Testcase               |
      | 3   | Partially | Orders when cancel sup |

  Scenario Outline: Verify status of Orders sync to Print Hub app when orders have products not belong Print Hub
    Given Description: "<Testcase>"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Sponge>2;Multiple product:Unisex T-shirt,White,S>1>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And user login to shopbase dashboard
    And user navigate to "apps/print-hub/manage-orders" screen by url
    And search order Print Hub
    Then verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Product name | Lineitems     | SKU         | Is sync |
      | 1   | Sponge       | Default Title | WTX80309486 | true    |
    And switch to "Awaiting Payment" tab on Manage Orders of Print Hub
    Then verify information lineitems of orders with status "<Status>" Print Hub as "<KEY>"
      | KEY | Product name     | Lineitems                  | SKU                         | Is sync |
      | 1   | Multiple product | Unisex T-shirt / White / S | PH-AP-UnisexT-shirt-White-S | true    |
    And verify information order in Print Hub as "<KEY>"
      | KEY | Product cost | Shipping price |
      | 1   | 20.48        | 11.98          |
    Examples: <Key>
      | KEY | Status    | Testcase                                      |
      | 1   | Partially | Orders contains products not belong Print Hub |

    