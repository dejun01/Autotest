Feature: Roller - Checkout on SF Test
#   prod_sbase_checkout_sftest1
  Background:
    Given clear all data

#    --------------------------------------------------------------------------------------------------------------------------------------------
#    PayPal Pro

  Scenario: Checkout success by Paypal Pro
    Given open shop on storefront
    And add products "Shirt" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS | 3DS password |
      | Paypal-Pro      | Credit Card    | 4000000000000002 |                 | 01/23        | 113 | Visa      | yes | 1234         |
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
      | Paypal-Pro      | Credit Card    | 4000000000000002 |                 | 01/23        | 113 | Visa      | yes | 1234         |
    And order product "Shirt" with custom option is "" in post purchase offer
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
      | Paypal-Pro      | Credit Card    | 4000000000000002 |                 | 01/23        | 113 | Visa      | yes | 1234         |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser