Feature: Payment Rotation
#  sbase_checkout_payment_rotation

  Background:
    Given clear all data
    And get list of active payment account by API

  Scenario: Checkout via Third-party successfully #SB_CHE_PR_2
    Given open shop on storefront
      #go shopping the first time
    And add products "Thong" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Rotation | Stripe card number | PayPal Pro card number | Cardpay card number | Cardholder name   | Expired Date | CVV |
      | Credit Card    | yes      | 4242424242424242   | 4000000000000002       | 4000000000000077    | Shopbase Rotation | 01/23        | 113 |
    And get the order information including
      | payment account list |
      #go shopping the second time
    And add products "Thong>2" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Rotation | Stripe card number | PayPal Pro card number | Cardpay card number | Cardholder name   | Expired Date | CVV |
      | Credit Card    | yes      | 4242424242424242   | 4000000000000002       | 4000000000000077    | Shopbase Rotation | 01/23        | 113 |
    And get the order information including
      | payment account list |
      #go shopping the third time
    And add products "Thong>3" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Rotation | Stripe card number | PayPal Pro card number | Cardpay card number | Cardholder name   | Expired Date | CVV |
      | Credit Card    | yes      | 4242424242424242   | 4000000000000002       | 4000000000000077    | Shopbase Rotation | 01/23        | 113 |
    And get the order information including
      | payment account list |
    Then verify payment rotation after checkout

  Scenario: Checkout flow when using standard paypal rotation #SB_CHE_PR_1
    Given open shop on storefront
     #go shopping the first time
      And add products "Thong" to cart then navigate to checkout page
    And checkout by Paypal express success with email "buyer@shopbase.com" and password "123456@a"
    And get the order information including
      | payment account list |
     #go shopping the second time
    And add products "Thong" to cart then navigate to checkout page
    And checkout by Paypal express success with email "buyer@shopbase.com" and password "123456@a"
    And get the order information including
      | payment account list |
    Then verify payment rotation after checkout

