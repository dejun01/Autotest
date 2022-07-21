Feature: Roller - Checkout on SF Test
#   prod_sbase_checkout_sftest1
  Background:
    Given clear all data

#    --------------------------------------------------------------------------------------------------------------------------------------------
#    PayPal

  Scenario: Verify checkout flow when use express checkout
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    And checkout by Paypal express success with email "buyer@shopbase.com" and password "123456@a"
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via paypal express without post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    And checkout by Paypal express success with email "buyer@shopbase.com" and password "123456@a"
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via paypal express with post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    And checkout by Paypal express success with email "buyer@shopbase.com" and password "123456@a"
    And order product "Shirt" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Verify checkout flow when using normal paypal
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | standard  |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Verify checkout flow when using normal paypal with post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | standard  |
    And order product "Shirt" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Verify checkout flow when using normal paypal without post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | standard  |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser