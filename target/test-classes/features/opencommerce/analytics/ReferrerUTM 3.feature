Feature: Verify Referrer UTM Analytics
#  sbase_referrer_utm_analytics

  Scenario: Verify refer custom campaign UTM value on order page displaying correctly
    #1. create a new order with utm value
    Given open product with UTM and checkout
      | Product name | utm_source    | utm_medium    | utm_campaign | Quantity |
      | 1805         | custom_source | custom_medium | custom_camp  | 2        |
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order id |
    #2. verify utm on order detail page
    Given Access to order detail by order ID
    Then verify refer block displaying on order detail page
      | Source identifier | Referring site | Source        | Medium        | Campaign    | First page visited                                                                        |
      | Direct            | N/A            | custom_source | custom_medium | custom_camp | /products/1805?utm_source=custom_source&utm_medium=custom_medium&utm_campaign=custom_camp |
    And quit browser

  Scenario: Verify refer custom campaign UTM value (no source) on order page displaying correctly
    #1. create a new order with utm value
    Given open product with UTM and checkout
      | Product name | utm_source | utm_medium    | utm_campaign | Quantity |
      | 1805         |            | custom_medium | custom_camp  | 2        |
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order id |
    #2. verify utm on order detail page
    Given Access to order detail by order ID
    Then verify refer block displaying on order detail page
      | Source identifier | Referring site | Source  | Medium        | Campaign    | First page visited                                                           |
      | Direct            | N/A            | not_set | custom_medium | custom_camp | /products/1805?utm_source=&utm_medium=custom_medium&utm_campaign=custom_camp |
    And quit browser

  Scenario: Verify refer custom campaign UTM value (no source and medium) on order page displaying correctly
    #1. create a new order with utm value
    Given open product with UTM and checkout
      | Product name | utm_source | utm_medium | utm_campaign | Quantity |
      | 1805         |            |            | custom_camp  | 2        |
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order id |
    #2. verify utm on order detail page
    Given Access to order detail by order ID
    Then verify refer block displaying on order detail page
      | Source identifier | Referring site | Source  | Medium  | Campaign    | First page visited                                              |
      | Direct            | N/A            | not_set | not_set | custom_camp | /products/1805?utm_source=&utm_medium=&utm_campaign=custom_camp |
    And quit browser

  Scenario: Verify refer custom campaign UTM no value on order page displaying correctly
    #1. create a new order with utm value
    Given open product with UTM and checkout
      | Product name | utm_source | utm_medium | utm_campaign | Quantity |
      | 1805         |            |            |              | 2        |
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | order id |
    #2. verify utm on order detail page
    Given Access to order detail by order ID
    Then verify refer block displaying on order detail page
      | Source identifier | Referring site | Source | Medium | Campaign | First page visited                                   |
      | Direct            | N/A            | direct | none   | Not set  | /products/1805?utm_source=&utm_medium=&utm_campaign= |
    And quit browser