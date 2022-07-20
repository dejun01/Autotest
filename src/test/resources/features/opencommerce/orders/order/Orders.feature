Feature: Order detail
#env = sbase_dashboard

  Scenario Outline: Verify the number of unfulfilled orders in sidebar menu #SB_ORD_2 #SB_ORD_10 #SB_ORD_11 #SB_ORD_8 #SB_ORD_17
    Given user login to shopbase dashboard
    Then get the number of initial unfulfilled orders in sidebar menu
    Given open shop on storefront
    Then checkout successfully via stripe with cart "t-shirt>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given redirect to shopbase dashboard
    Then user navigate to "Orders" screen
    And  verify the number of unfulfilled orders in sidebar menu after checking out
    Then click on order name in unfulfilled orders list
    And fulfill the order with "<Fulfilled Items>", "<Tracking number>", "<Shipping carrier>", "<TrackingUrl>" and "<Status after fulfilling>"
    Then verify the number of "<Unfulfilled orders>" in sidebar menu after mark as fulfilled
    And verify order fulfilled should be hidden "Cancel order" option
    And close browser
    Examples:
      | Fulfilled Items | Tracking number | Shipping carrier    | TrackingUrl | Status after fulfilling | Unfulfilled orders |
      | t-shirt>3       | testFulfill     | China EMS (ePacket) |             | Partially Fulfilled     | -1                 |
      | t-shirt>4       | testFulfill     | China EMS (ePacket) |             | Fulfilled               | -0                 |


  Scenario: Verify order detail after buyer have completed checkout successfully #SB_ORD_20 #SB_ORD_21 #SB_ORD_22 #SB_ORD_25 #SB_ORD_26 #SB_ORD_27 #SB_ORD_28 #SB_ORD_42
    Given open shop on storefront
    And add products "t-shirt>4" to cart then checkout
    And apply discount code
      | Discount code | Discount value | Discount type |
      | DISCOUNT_50   | 50             | Percentage    |
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Stripe          |
    And verify thank you page then get all information
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And verify that edit the order's information successfully
      | Case    | Notes                                 | CONTACT INFORMATION        | First name | Last name | Company   | Phone number | Address                | Apartment, suite, etc... (optional) | City      | Country   | Region         | ZIP/Postal Code | Tags           |
      | success | need to ship the order before 9:00 AM | supertester@mailtothis.com | Linh       | Nguyen    | Green tea | 62226288     | 537 Bedok North Street | 537                                 | Singapore | Singapore | Central Region | 628321          | Need to review |
    And close browser

  Scenario: Verify search function working properly #SB_ORD_9
    Given open shop on storefront
    When add products "t-shirt" to cart then checkout
    And input Customer information
      | Email                      | Phone      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code |
      | @mailinator.girl-viet.com@ | 2056289809 | Jone       | Doe       | 815 Mission Street | 815       | Los Angeles | United States | California | 90001    |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 | Stripe          |
    Then verify thank you page then get all information
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And search orders by the below mentioned criteria then verify search function working properly
      | Criteria       |
      | Order name     |
      | Customer name  |
#      | Customer email |
    And close browser


  Scenario Outline: Verify status and tracking information order of after fulfilling #SB_ORD_23 #SB_ORD_24 #SB_ORD_48 #SB_ORD_49 #SB_ORD_50 #SB_ORD_51 #SB_ORD_52 #SB_ORD_18
    Given open shop on storefront
    Then checkout successfully via stripe with cart "t-shirt>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then fulfill the order with "<Fulfilled Items>", "<Tracking number>", "<Shipping carrier>", "<TrackingUrl>" and "<Status after fulfilling>"
#    And verify that the "<Shipping carrier>", "<Tracking number>" on thank you page navigate to "<Third party tracking>"
    And close browser
    Examples:
      | Fulfilled Items | Tracking number | Shipping carrier | TrackingUrl | Status after fulfilling | Third party tracking                             |
#      | t-shirt>4       | 3011            | China Post       |             | Fulfilled               | https://​t.17track.net/en#nums={tracking_number} |
      | t-shirt>1       | 3011            | China Post       |             | Partially Fulfilled     | https://​t.17track.net/en#nums={tracking_number} |

  Scenario: Verify customer information is shown correctly #SB_ORD_19
    Given open shop on storefront
    Then checkout successfully via stripe with cart "t-shirt>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then click on the customer name link text should be redirected to customer detail page
    And click on the number of orders per customer link text should be redirected to list of orders filtered by the customer
    And close browser


    Scenario: Verify order list when click on number of order in order detail #SB_ORD_91
      Given user login to shopbase dashboard
      When user navigate to "Orders" screen
      And click on order name in All orders list
      Then click on the number of orders per customer should be redirected to list of orders filtered by the customer "Trang Doan"
      And close browser





