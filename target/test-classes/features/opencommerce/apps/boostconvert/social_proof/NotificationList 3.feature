Feature: Notification List

#  Env:
#  prod_copt_notification
#  staging_copt_notification

#  Theme: Roller, Inside
#  Shipping zone: United States
#  Payment method
#  Product: Candles, silver plated cute cat ring, socks6, Hats, Mug, Socks, Pineapple Earrings, Bracelet, Slicer
#  Collection: Coasters


  Scenario: Delete notification #SB_SF_BC_2
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Social Proof>Notification list" screen
    And delete all sales notification of Copt
    Given confirm list sales notification have 0 notification

  Scenario: Verify Notification sync #SB_SF_BC_1 #SB_SF_BC_3 #SB_SF_BC_4 #SB_SF_BC_5 #SB_SF_BC_6 #SB_SF_BC_7 #SB_SF_BC_8 #SB_SF_BC_9 #SB_SF_BC_10 #SB_SF_BC_11 #SB_SF_BC_12
    Given count sales notification on Copt
    Given open shop on storefront
    When add multiple products "Candles" to cart then checkout
    And input Customer information
      | Email                         | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | testershopbase@mailtothis.com | QA         | Shopbase  | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | QA Sbase        | 11/22        | 113 |
    And verify thank you page
    And verify sales notification synced on Copt by API

  Scenario Outline: Verify Message setting for Sales Notification #SB_SF_BC_13 #SB_SF_BC_14 #SB_SF_BC_15 #SB_SF_BC_16 #SB_SF_BC_17
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Social Proof>Pop types" screen
    And user turn "<Status of Setting>" "Sales notifications"
    And turn "<Status of notification>" sales notification'status of product "Candles" by API
    When change setting sales notifications of BoostConvert as "<KEY>"
      | KEY | Title                                                    | Product name                        | Time                | Trigger            | Custom pages                                           |
      | 1   | {{first_name}} {{last_name}} in {{location}} purchased a | {{product_link}}, {{product_title}} | {{random_time_ago}} | On all pages       |                                                        |
      | 2   | My customer in Hanoi purchased a                         | {{product_title}}                   | {{time_ago}}        | On all pages       |                                                        |
      | 3   | My customer in {{location}} purchased a                  | {{product_link}}, {{product_title}} | {{random_time_ago}} | On pages I specify | /products/silver-plated-cute-cat-ring,/products/socks6 |
    And open shop on storefront
    Then verify sales notifications of BoostConvert on Shopbase page as "<KEY>"
      | KEY | Page                                  | is show | Title                                                    | Product name | Time                | Customer first name | Customer last name | Customer country | Customer city |
      | 1   |                                       | true    | {{first_name}} {{last_name}} in {{location}} purchased a | Candles      | {{random_time_ago}} | QA                  | Shopbase           | United States    | Los Angeles   |
      | 1   | Hats                                  | true    | {{first_name}} {{last_name}} in {{location}} purchased a | Candles      | {{random_time_ago}} | QA                  | Shopbase           | United States    | Los Angeles   |
      | 1   | /pages/faqs                           | true    | {{first_name}} {{last_name}} in {{location}} purchased a | Candles      | {{random_time_ago}} | QA                  | Shopbase           | United States    | Los Angeles   |
      | 1   | /collections/all                      | true    | {{first_name}} {{last_name}} in {{location}} purchased a | Candles      | {{random_time_ago}} | QA                  | Shopbase           | United States    | Los Angeles   |
      | 2   | /collections/all                      | true    | My customer in Hanoi purchased a                         | Candles      | {{time_ago}}        | QA                  | Shopbase           | United States    | Los Angeles   |
      | 2   | /products/silver-plated-cute-cat-ring | true    | My customer in Hanoi purchased a                         | Candles      | {{time_ago}}        | QA                  | Shopbase           | United States    | Los Angeles   |
      | 2   | Mug                                   | true    | My customer in Hanoi purchased a                         | Candles      | {{time_ago}}        | QA                  | Shopbase           | United States    | Los Angeles   |
      | 2   | /pages/faqs                           | true    | My customer in Hanoi purchased a                         | Candles      | {{time_ago}}        | QA                  | Shopbase           | United States    | Los Angeles   |
      | 3   | /products/silver-plated-cute-cat-ring | true    | My customer in {{location}} purchased a                  | Candles      | {{random_number}}   | QA                  | Shopbase           | United States    | Los Angeles   |
      | 3   | Socks                                 | true    | My customer in {{location}} purchased a                  | Candles      | {{random_number}}   | QA                  | Shopbase           | United States    | Los Angeles   |
      | 3   | Mug                                   | false   | My customer in {{location}} purchased a                  | Candles      | {{random_number}}   | QA                  | Shopbase           | United States    | Los Angeles   |
      | 4   |                                       | false   |                                                          |              |                     |                     |                    |                  |               |
      | 4   | /collections/all                      | false   |                                                          |              |                     |                     |                    |                  |               |
      | 4   | /pages/faqs                           | false   |                                                          |              |                     |                     |                    |                  |               |
      | 4   | Mug                                   | false   |                                                          |              |                     |                     |                    |                  |               |
      | 5   |                                       | false   |                                                          |              |                     |                     |                    |                  |               |
      | 5   | /collections/all                      | false   |                                                          |              |                     |                     |                    |                  |               |
      | 5   | Hats                                  | false   |                                                          |              |                     |                     |                    |                  |               |
      | 6   |                                       | false   |                                                          |              |                     |                     |                    |                  |               |
      | 6   | /collections/all                      | false   |                                                          |              |                     |                     |                    |                  |               |
      | 6   | /pages/faqs                           | false   |                                                          |              |                     |                     |                    |                  |               |
    Examples:
      | KEY | Status of Setting | Status of notification |
      | 1   | on                | on                     |
      | 2   | on                | on                     |
      | 3   | on                | on                     |
      | 4   | on                | off                    |
      | 5   | off               | on                     |
      | 6   | off               | off                    |


  Scenario Outline: Add custom notification
    Given count sales notification on Copt
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Social Proof>Notification list" screen
    When create custom notifications of BoostCovert as "<KEY>"
      | KEY | Product                     | Location                  | Data Location                             | Customer notification | Number notification created |
      | 01  | Products>Pineapple Earrings | Manually select locations | Hanoi,Vietnam                             | false                 | 1                           |
      | 02  | Collections>Coasters        | Manually select locations | Hanoi,Vietnam>San Francisco,United States | false                 |                             |
      | 03  | Products>Bracelet,Slicer    | Random locations          | Canada,Peru,Brazil                        | false                 | 6                           |
      | 04  | All products                | Random locations          | United Kingdom                            | true                  |                             |
    And verify sales notification synced on Copt by API

    Examples:
      | KEY |
      | 01  |
      | 02  |
      | 03  |
      | 04  |



