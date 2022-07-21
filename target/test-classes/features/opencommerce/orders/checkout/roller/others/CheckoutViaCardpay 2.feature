Feature: Roller - Checkout via Unlimint
#  sbase_checkout_cardpay
  Background:
    Given clear all data

#  Scenario: Checkout via Unlimint without 3Ds successfully
#    Given open shop on storefront
#    And add products "Shirt" to cart then navigate to checkout page
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And choose shipping method ""
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS |
#      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes |
#    And get the order information including
#      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
#    Given user login to shopbase dashboard
#    And user navigate to "Orders" screen
#    Given click on order name in unfulfilled orders list
#    Then verify order detail on dashboard
#    And close browser

#  Scenario: Checkout via Unlimint without both 3Ds and adding post purchase item
#    Given open shop on storefront
#    And add products "Post Purchase" to cart then navigate to checkout page
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And choose shipping method ""
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS |
#      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes |
#    And order product "" with custom option is "" in post purchase offer
#    And get the order information including
#      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
#    Given user login to shopbase dashboard
#    And user navigate to "Orders" screen
#    Given click on order name in unfulfilled orders list
#    Then verify order detail on dashboard
#    And close browser

#  Scenario: Checkout via Unlimint without 3DS and with adding post purchase item
#    Given open shop on storefront
#    And add products "Post Purchase" to cart then navigate to checkout page
#    When input Customer information
#      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
#      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And choose shipping method ""
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS |
#      | Unlimint        | Cardpay        | 4000000000000077 | Shop Base       | 01/23        | 100 | Visa      | no  |
#    And order product "Shirt" with custom option is "" in post purchase offer
#    And complete payment for post purchase item
#    And get the order information including
#      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
#    Given user login to shopbase dashboard
#    And user navigate to "Orders" screen
#    Given click on order name in unfulfilled orders list
#    Then verify order detail on dashboard
#    And close browser

  Scenario: Checkout via Unlimint with 3Ds successfully
    Given open shop on storefront
    And add products "Shirt" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes | success    |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Unlimint with 3Ds and without adding post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes | success    |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Unlimint with 3Ds and with adding post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes | success    |
    And order product "Shirt" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Unlimint unsuccessfully without 3Ds and with using declined card
    Given open shop on storefront
    And add products "Shirt" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status | Error message                                                                   |
      | Unlimint        | Cardpay        | 5555555555554477 | Shop Base       | 01/23        | 100 | Visa      | no  | decline    | Oops! This card is declined by issuing bank. Please contact your bank for help. |
    And close browser

  Scenario: Checkout via Unlimint unsuccessfully with 3Ds and using declined card
    Given open shop on storefront
    And add products "Shirt" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status | Error message                                                                   |
      | Unlimint        | Cardpay        | 5555555555554444 | Shop Base       | 01/23        | 100 | Visa      | yes | decline    | Oops! This card is declined by issuing bank. Please contact your bank for help. |
    And close browser

  Scenario: Checkout via Unlimint unsuccessfully when verify 3Ds fail
    Given open shop on storefront
    And add products "Shirt" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status | Error message                                                                                                     |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes | failure    | Oops! This card is declined by 3-D Secure. Please try again with another card to complete payment for your order. |
    And close browser

  Scenario: Checkout via Unlimint unsuccessfully when clicking on Cancel button
    Given open shop on storefront
    And add products "Shirt;Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | Cancel |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes | yes    |
    And close browser

  Scenario: Checkout via Unlimint successfully when clicking on Cancel button while confirming payment for post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes | success    |
    And order product "Shirt" with custom option is "" in post purchase offer
    Then cancel payment for post-purchase item then verify checkout detail
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Unlimint successfully when verify 3Ds unsuccessfully while confirming payment for post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3Ds status |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base       | 01/23        | 100 | Visa      | yes | success    |
    And order product "Shirt" with custom option is "" in post purchase offer
    Then verify 3Ds Unlimint for post-purchase item "Shirt" unsuccessfully then verify checkout detail
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser