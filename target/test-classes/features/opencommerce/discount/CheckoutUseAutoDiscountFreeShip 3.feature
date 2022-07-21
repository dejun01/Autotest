Feature: Roller - Checkout flow with automatic discounts free shipping
#  sbase_checkout_automatic_discount_freeship

#Pre-condition:
#Setting shipping for country = United States, amount = 20USD
#Create Automatic discount free shipping: AU_FREE_SHIPPING (Country = Vietnam, Minimum purchase amount = 20USD)
#Discount code: CODE1(5%), CODE2 (40USD)
#Upsell_Quantity discount: QD (product: Running Waist Bag; 1 item-5% sale off on each product; 4 item-10$ sale off on each product)

  Background: open shop on storefront storefront
    Given clear all data
    Given open shop on storefront

  Scenario: 1.Verify apply automatic discount free shipping #SB_DC_4

    When add products "ONITSUKA MEXICO 66 Low>1" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address | City | Country | State | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 HN  | HN   | Vietnam |       | 12345    | 0964565456 | true  | success  |
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
    Then verify order on dashboard with discount "free shipping"
    And close browser

  Scenario: 2.Don't apply automatic discount free shipping when country don't matching with discount condition #SB_DC_5

    When add products "ONITSUKA MEXICO 66 Low>1" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
    And choose shipping method ""
    And Verify apply discount for order
      | Discount code | Discount value | Discount type |
      |               | 0              |               |
    And close browser

  Scenario: 3.Don't apply automatic discount free shipping when Subtotal < Minimum purchase amount #SB_DC_6

    When add products "Running Shorts Mens>1" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address | City | Country | State | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 HN  | HN   | Vietnam |       | 12345    | 0964565456 | true  | success  |
    And choose shipping method ""
    And Verify apply discount for order
      | Discount code | Discount value | Discount type |
      |               | 0              |               |
    And close browser

  Scenario: 4.Apply free shipping discount when free shipping discount amount > manual discount amount

    When add products "Running Waist Bag>2" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address | City | Country | State | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 HN  | HN   | Vietnam |       | 12345    | 0964565456 | true  | success  |
    And choose shipping method ""
    And Apply discount code "CODE1"
    And Verify apply discount for order
      | Discount code    | Discount value | Discount type |
      | AU_FREE_SHIPPING | 0              | Free shipping |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | total amount | product list | discount code | discount value | shipping fee |
    Given Access to order detail by order ID
    Then verify order on dashboard with discount "free shipping"
    And close browser

  Scenario: 5.Don't apply free shipping discount when free shipping discount amount < code discount amount #SB_DC_7

    When add products "Cow Leather Climbing Shoes>2" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address | City | Country | State | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 HN  | HN   | Vietnam |       | 12345    | 0964565456 | true  | success  |
    And choose shipping method ""
    And Verify apply discount for order
      | Discount code    | Discount value | Discount type |
      | AU_FREE_SHIPPING | 0              | Free shipping |
    And apply discount code
      | Discount code | Discount value | Discount type |
      | CODE2         | 40             | Fixed amount  |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | total amount | product list | discount code | discount value | shipping fee |
    Given Access to order detail by order ID
    Then verify order on dashboard with discount "CODE2"
    And close browser

  Scenario: 6.Apply free shipping discount when offer discount < free shipping discount #SB_DC_8

    When add products "Running Shorts Mens>2" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address | City | Country | State | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 HN  | HN   | Vietnam |       | 12345    | 0964565456 | true  | success  |
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
    Then verify order on dashboard with discount "free shipping"
    And close browser

  Scenario: 7.Apply offer discount when offer discount > free shipping discount

    When add products "Running Shorts Mens>5" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address | City | Country | State | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 HN  | HN   | Vietnam |       | 12345    | 0964565456 | true  | success  |
    And choose shipping method ""
    And Verify apply discount for order
      | Discount code  | Discount value | Discount type     |
      | OFFER DISCOUNT | 50             | Quantity discount |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | total amount | product list | discount code | discount value | shipping fee |
    Given Access to order detail by order ID
    Then verify order on dashboard with discount "Offer Discount"
    And close browser