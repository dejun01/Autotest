Feature: Roller - Checkout via Spay
#  sbase_smoke_checkout_spay_paypal

  Background:
    Given clear all data

  Scenario: Checkout via Spay with adding item from post-purchase
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And apply discount code
      | Discount code | Discount value | Discount type |
      | DISCOUNT_50   | 50             | Percentage    |
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | ShopBase Payments | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Bikini" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Spay successfully with 3DS
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway   | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS |
      | ShopBase Payments | Credit Card    | 4000002500003155 | Shopbase        | 11/22        | 113 | Visa      | yes |
    And verify thank you page then get all information
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser