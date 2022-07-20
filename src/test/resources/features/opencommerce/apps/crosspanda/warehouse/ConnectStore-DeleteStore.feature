# crosspanda_connect_store

Feature: Connect Store and Delete store

  Scenario Outline: Connect Store
    Given Description: "<Test case>"
    Given login to crosspanda dashboard
    And user connect store with platform "<KEY>"
      | KEY | Platform |
      | 1   | ShopBase |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify order after connect store "<KEY>"
      | KEY | Store |
      | 1   |       |
    When open shop on storefront
    Given create order on Shopbase as "<KEY>"
      | KEY | Product name   | Email                     | Last name | First name | Phone number | Country       | Address            | City    | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | Autoama Dess>2 | crosspanda@mailtothis.com | Cross     | Panda      | 7777777777   | United States | 814 Mission Street | Sanford | 136       | California            | 90001           |
    Given login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify order "<KEY>" is synced to CrossPanda
      | KEY | Order number |
      | 1   |              |
    Examples:
      | KEY | Test case              |
      | 1   | Connect store ShopBase |

  Scenario Outline: Delete Store
    Given Description: "<Test case>"
    Given create order on Shopbase as "<KEY>"
      | KEY | Product name   | Email                     | Last name | First name | Phone number | Country       | Address            | City    | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | Autoama Dess>2 | crosspanda@mailtothis.com | Cross     | Panda      | 7777777777   | United States | 814 Mission Street | Sanford | 136       | California            | 90001           |
    Given user login to shopbase dashboard
    Given user navigate to "Apps" screen
    And user delete store with platform "<KEY>"
      | KEY | App        |
      | 1   | CrossPanda |
    And login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify delete order on app after delete store "<KEY>"
      | KEY |Order number|
      | 1   |            |
    When open shop on storefront
    Given create order on Shopbase as "<KEY>"
      | KEY | Order key | Product name | Email                     | Last name | First name | Phone number | Country       | Address            | City    | Apartment | State/Province/Region | ZIP/Postal Code |
      | 1   | Order 1   | Autoama Dess | crosspanda@mailtothis.com | Cross     | Panda      | 7777777777   | United States | 814 Mission Street | Sanford | 136       | California            | 90001           |
    And login to crosspanda dashboard
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "All Orders"
    And verify order "<KEY>" isnot sync to CrossPanda
      | KEY | Order key | Order number |
      | 1   | Order 1   |              |
#    And verify count awaiting order after delete store "<KEY>"
#      | KEY | Product                                                                                                                          | Variant |
#      | 1   | Sparkly Boho Evening Dress Long Off Shoulder Lace Up Bling Bling Women Reflective Formal Dresses 2020 Pageant Sexy Evening Gowns | Blue,2  |
    Examples:
      | KEY | Test case    |
      | 1   | Delete store |
