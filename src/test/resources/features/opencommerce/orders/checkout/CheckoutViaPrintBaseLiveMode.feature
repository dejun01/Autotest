Feature: Checkout with Printbase Livemode
#  sbase_pbase_checkout_livemode

  Background:
    Given clear all data

  Scenario: Check checkout via Printbase Stripe SPay successfully
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
#    And choose shipping method "Standard shipping"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page

  Scenario: Check checkout via Printbase Stripe SPay successfully with adding post-purchase
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method "Standard shipping"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Shopbase Auto" with custom option is "" in post purchase offer
    And verify thank you page

  Scenario: Check checkout via Printbase PayPal successfully
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    And checkout by Paypal express success with email "buyer@shopbase.com" and password "123456@a"
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page

  Scenario: Check checkout via Printbase PayPal successfully with adding post-purchase
    Given open shop on storefront
    And add products "Shopbase Auto>2" to cart then navigate to checkout page
    And checkout by Paypal express success with email "buyer@shopbase.com" and password "123456@a"
    And order product "Shopbase Auto" with custom option is "" in post purchase offer
    And verify thank you page

