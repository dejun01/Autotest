Feature: View customer's session history
# sbase_customer_session_history

  Scenario: Verify customer detail page when abandoned checkout with custom UTM value
    Given user login to shop dashboard
    And user navigate to "Customers" screen
    And search customer and click to customer detail
      | Customer email        | Customer name |
      | anhxinh@beeketing.net | ShopBase Auto |
    And click button View all sessions
    And get total sessions of customer
    Given open product with UTM and checkout
      | Product name | utm_source    | utm_medium    | utm_campaign | utm_term    | utm_content    | Quantity |
      | anhxinh      | custom_source | custom_medium | custom_camp  | custom_term | custom_content | 2        |
    When input Customer information
      | Email                 | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | anhxinh@beeketing.net | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And  quit browser
    Given user login to shop dashboard
    And user navigate to "Customers" screen
    And wait 10000 second
    And search customer by email then select
    And verify last session block
      | Source identifier | Referring site | First page visited                                                                                                                           |
      | direct            | N/A            | /products/anhxinh?utm_source=custom_source&utm_medium=custom_medium&utm_campaign=custom_camp&utm_content=custom_content&utm_term=custom_term |
    And click button View all sessions
    And get total sessions of customer
    And verify total sessions change
      | Total sessions | Activity                         | Converted to order |
      | +1             | session was direct to your store | @Not converted@    |

  Scenario: Verify customer detail page when checkout success
    Given user login to shop dashboard
    And user navigate to "Customers" screen
    And search customer and click to customer detail
      | Customer email        | Customer name    |
      | anhxinh@beeketing.net | ShopBase Auto    |
    And click button View all sessions
    And get total sessions of customer
    Given open product with UTM and checkout
      | Product name | utm_source    | utm_medium    | utm_campaign | utm_term    | utm_content    | Quantity |
      | anhxinh      | custom_source | custom_medium | custom_camp  | custom_term | custom_content | 2        |
    When input Customer information
      | Email                 | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | anhxinh@beeketing.net | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And quit browser
    Given user login to shop dashboard
    And user navigate to "Customers" screen
    And search customer by email then select
    And verify last session block
      | Source identifier | Referring site | First page visited                                                                                                                           |
      | direct            | N/A            | /products/anhxinh?utm_source=custom_source&utm_medium=custom_medium&utm_campaign=custom_camp&utm_content=custom_content&utm_term=custom_term |
    And click button View all sessions
    And get total sessions of customer
    And verify total sessions change
      | Total sessions | Activity                         | Converted to order |
      | +1             | session was direct to your store | Converted to order |

  Scenario: Verify customer detail page when abandoned checkout with custom UTM value
    Given open product with UTM and checkout
      | Product name | utm_source    | utm_medium    | utm_campaign | utm_term    | utm_content    | Quantity |
      | anhxinh      | custom_source | custom_medium | custom_camp  | custom_term | custom_content | 2        |
    When input Customer information
      | Email                 | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | anhxinh@beeketing.net | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    Given user login to shop dashboard
    And user navigate to "Customers" screen
    And search customer by email then select
    And verify UTM parameters block
      | Source        | Medium        | Campaign    | Term        | Content        |
      | custom_source | custom_medium | custom_camp | custom_term | custom_content |
    And click button View all sessions and View full sessions
    And verify UTM parameters block
      | Source        | Medium        | Campaign    | Term        | Content        |
      | custom_source | custom_medium | custom_camp | custom_term | custom_content |

  Scenario: Verify customer detail page when checkout success with custom UTM value
    Given open product with UTM and checkout
      | Product name | utm_source     | utm_medium     | utm_campaign | utm_term     | utm_content     | Quantity |
      | anhxinh      | custom_source1 | custom_medium1 | custom_camp1 | custom_term1 | custom_content1 | 2        |
    When input Customer information
      | Email                 | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | anhxinh@beeketing.net | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shop dashboard
    And user navigate to "Customers" screen
    And search customer by email then select
    And verify UTM parameters block
      | Source         | Medium         | Campaign     | Term         | Content         |
      | custom_source1 | custom_medium1 | custom_camp1 | custom_term1 | custom_content1 |
    And click button View all sessions and View full sessions
    And verify UTM parameters block
      | Source         | Medium         | Campaign     | Term         | Content         |
      | custom_source1 | custom_medium1 | custom_camp1 | custom_term1 | custom_content1 |











#  Scenario: Verify view customer's session history on customer detail page displaying correctly (has UTM value)
    #1. Verify info last session on customer detail page before

#    2. View storefront with utm value


    #3. Create a new order with utm value
#    Given open product with UTM and checkout
#      | Product name | utm_source    | utm_medium    | utm_campaign | utm_term    | utm_content    | Quantity |
#      | 1805         | custom_source | custom_medium | custom_camp  | custom_term | custom_content | 2        |
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And choose shipping method ""
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    And get the order information including
#      | customer email |
#    #4. Verify last session on customer detail page after having new sessions
#    Given customer
##    Given Access to order detail by order ID
#    Then verify last session block displaying on customer detail page
#      | Source identifier | Referring site | Source        | Medium        | Campaign    | First page visited                                                                        |
#      | Direct            | N/A            | custom_source | custom_medium | custom_camp | /products/1805?utm_source=custom_source&utm_medium=custom_medium&utm_campaign=custom_camp |
#    And verify view all sessions block
#    And verify session details block
#    And quit browser