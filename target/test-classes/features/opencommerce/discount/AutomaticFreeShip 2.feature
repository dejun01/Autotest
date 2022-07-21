Feature: Roller - Checkout flow - Automatics discounts free shipping can be applied and calculated correctly
#  sbase_automatic_discount_freeship
  #Pre-condition:
  #Shipping: Rest of world - Standard shipping - 20USD

  Background:
    Given clear all data
    Given user login to shopbase dashboard
    And user navigate to "Discounts>Automatic" screen
    And Delete all discounts

  Scenario: 1.Checkout with automatic discount free shipping in case discount value = 100% #SB_DC_1 #SB_DC_11

    And Create free shipping discount
      | Discount type | Discount title   | Value type | Discount value | Maximum discount amount | Maximun value |
      | Free shipping | AU_FREE_SHIPPING | %          | 100            |                         |               |
    And open shop on storefront
    And add products "X001>1" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
    And choose shipping method ""
    And Verify apply discount for order
      | Discount code    | Discount value | Discount type |
      | AU_FREE_SHIPPING | 0              | Free shipping |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | total amount | product list | discount code | discount value | shipping fee |
    Given Access to order detail by order ID
    Then verify order on dashboard with discount "Free shipping"
    And close browser


#  Scenario: 2.Checkout with automatic discount free shipping in case discount value =100%, maximum discount amount = 15 (discount > maximum discount)
#
#    And Create free shipping discount
#      | Discount type | Discount title   | Value type | Discount value | Maximum discount amount | Maximun value |
#      | Free shipping | AU_FREE_SHIPPING | %          | 100            | true                    | 15            |
#    And open shop on storefront
#    And add products "X003>1" to cart then navigate to checkout page
#    Then input Customer information
#      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
#      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
#    And choose shipping method ""
#    And Verify apply discount for order
#      | Discount code    | Discount value | Discount type |
#      | AU_FREE_SHIPPING | 15             | Shipping      |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    And get the order information including
#      | total amount | product list | discount code | discount value | shipping fee |
#    Given Access to order detail by order ID
#    Then verify order on dashboard with discount "Free shipping"
#    And close browser
#
#  Scenario: 3.Checkout with automatic discount free shipping in case discount value = 50%, maximum discount amount = 15 (discount < maximum discount)
#
#    And Create free shipping discount
#      | Discount type | Discount title   | Value type | Discount value | Maximum discount amount | Maximun value |
#      | Free shipping | AU_FREE_SHIPPING | %          | 50             | true                    | 15            |
#    And open shop on storefront
#    And add products "X003>1" to cart then navigate to checkout page
#    Then input Customer information
#      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
#      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
#    And choose shipping method ""
#    And Verify apply discount for order
#      | Discount code    | Discount value | Discount type |
#      | AU_FREE_SHIPPING | 10             | Shipping      |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    And get the order information including
#      | total amount | product list | discount code | discount value | shipping fee |
#    Given Access to order detail by order ID
#    Then verify order on dashboard with discount "Free shipping"
#    And close browser
#
#
#  Scenario: 4.Checkout with automatic discount free shipping in case discount value type = amount
#
#    And Create free shipping discount
#      | Discount type | Discount title   | Value type | Discount value | Maximum discount amount | Maximun value |
#      | Free shipping | AU_FREE_SHIPPING | USD        | 17             |                         |               |
#    And open shop on storefront
#    And add products "X003>1" to cart then navigate to checkout page
#    Then input Customer information
#      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
#      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
#    And choose shipping method ""
#    And Verify apply discount for order
#      | Discount code    | Discount value | Discount type |
#      | AU_FREE_SHIPPING | 17             | Shipping      |
#    And input payment information and complete order
#      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
#      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
#    And get the order information including
#      | total amount | product list | discount code | discount value | shipping fee |
#    Given Access to order detail by order ID
#    Then verify order on dashboard with discount "Free shipping"
#    And close browser