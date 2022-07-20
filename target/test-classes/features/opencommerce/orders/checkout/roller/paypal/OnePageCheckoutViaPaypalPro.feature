Feature: Roller - One Page Checkout with paypal pro
  #env = sbase_onepage_checkout_paypal_pro
  Background:
    Given clear all data

  Scenario: Checkout success by Paypal Pro #SB_CHE_PPP_9
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000002 |                 | 01/23        | 113 | Visa      | yes | 1234         |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout success by Paypal Pro with post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000002 |                 | 01/23        | 113 | Visa      | yes | 1234         |
    And order product "Bikini" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout success by Paypal Pro without post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000002 |                 | 01/23        | 113 | Visa      | yes | 1234         |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 3. Failed Authentication
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status |
      | Paypal-Pro      | PayPal Pro     | 4000000000000028 |                 | 01/23        | 113 | Visa      | yes | 1234         | cancel     |
    And close browser

  Scenario: Checkout by Paypal Pro in case 4. Attempts/Non-Participating
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status |
      | Paypal-Pro      | PayPal Pro     | 4000000000000101 |                 | 01/23        | 113 | Visa      | no  | 1234         | cancel     |
    And close browser

  Scenario: Checkout by Paypal Pro in case 5. Timeout
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000044 |                 | 01/23        | 113 | Visa      | no  | 1234         |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 6. Not Enrolled
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000051 |                 | 01/23        | 113 | Visa      | no  | 1234         |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 7. Unavailable
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000069 |                 | 01/23        | 113 | Visa      | no  | 1234         |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 8. Merchant Not Active
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000077 |                 | 01/23        | 113 | Visa      | no  | 1234         |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 9. cmpi_lookup error
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000000000000085 |                 | 01/23        | 113 | Visa      | no  | 1234         |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 12. Bypassed Authentication
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | PayPal Pro     | 4000990000000004 |                 | 01/23        | 113 | Visa      | no  | 1234         |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 10. cmpi_authenticate error
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status              | Error message                                                                                                                         |
      | Paypal-Pro      | PayPal Pro     | 4000000000000093 |                 | 01/23        | 113 | Visa      | yes | 1234         | cmpi_authenticate error | General Error (Processor error - An error occurred during authorization. If these errors persist, contact merchant service provider). |
    And close browser

  Scenario: Checkout by Paypal Pro in case 11. Authentication Unavailable
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status |
      | Paypal-Pro      | PayPal Pro     | 4000000000000036 |                 | 01/23        | 113 | Visa      | yes | 1234         | try again  |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout by Paypal Pro in case 2. Failed Signature
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password | 3Ds status       | Error message                                         |
      | Paypal-Pro      | PayPal Pro     | 4000000000000010 |                 | 01/23        | 113 | Visa      | yes | 1234         | Failed signature | 3D-Authentication failed. Cannot authorise this card. |
    And close browser

  Scenario: Verify input data on Payment method
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    When verify payment method information with "Paypal-Pro"
      | Case                     | Card Number      | MM/YY | CVV | Expected                                   |
      | Card number are empty    |                  | 11/22 | 111 | Your card number is invalid                |
      | Expired date are empty   | 4000000000000002 |       | 111 | Your cards expiration year is invalid      |
      | CVV are empty            | 4000000000000002 | 11/22 |     | Your cvv is invalid                        |
      | Card number is not exist | 2222222222222222 |       |     | Your card number is invalid                |
      | Expired date in the past | 4000000000000002 | 01/11 | 111 | Your card's expiration year is in the past |
      | CVV has invalid format   | 4000000000000002 | 01/23 | 11  | Your cvv is invalid                        |
    And close browser
