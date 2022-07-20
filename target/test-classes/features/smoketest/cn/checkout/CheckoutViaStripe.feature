Feature: Roller - Checkout via Stripe
#  sbase_smoke_checkout_stripe_paypal

  Background: clear all data
    Given clear all data

  Scenario: Checkout via Stripe with adding item from post-purchase
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And order product "Bikini" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Then Verify transaction details of corresponding gateway after checkout successfully
      | User name                                                                                                   |
      | sk_test_51H0MCwCap6eoiy2dx0VCnttsOD7NFu2TY9MQiY5I54pRjJrCbTje8pcZut4hkf0jE8bHk2rhe5N2ZvGbsBIfj5x500p7EsqEVq |
    Given user login to shopbase dashboard
    And user navigate to "订单" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser