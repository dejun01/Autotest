Feature: Roller - Checkout flow with discount
#  sbase_roller_checkout
  Background: open shop on storefront storefront
    Given clear all data
    Given open shop on storefront

  Scenario: SB_SF_CHE_6.2: Verify checkout when discount invalid

    And add products "Necklace" to cart then navigate to checkout page
    When user enter invalid discount code
      | Discount    | Expected                                                   |
      | NOT_EXISTED | Unable to find a valid discount matching the code entered. |
      | Expired     | Unable to find a valid discount matching the code entered. |
      | Not started | Unable to find a valid discount matching the code entered. |
      | DISABLED    | Unable to find a valid discount matching the code entered. |
    Then input Customer information
      | Email            | First Name | Last Name | Address | City | Country | State | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 HN  | HN   | Vietnam |       | 12345    | 0964565456 | true  | success  |
    And choose shipping method ""
    When user enter invalid discount code
      | Discount          | Expected                                                                |
      | FREESHIP_US_3ITEM | FREESHIP_US_3ITEM discount code isn’t valid for the items in your cart. |


  Scenario Outline: Verify checkout flow when user checkout with discount code #SB_DC_14 #SB_DC_13

    When add products "<Product name>" to cart then navigate to checkout page
    And apply discount code for all items as "<KEY>"
      | KEY | Discount code | Discount value | Discount type |
      | 1   | FIXED_AMOUNT  | 5              | Fixed amount  |
      | 2   | FREE_SHIPPING | 0              | Free shipping |
      | 3   | DISCOUNT_10   | 10             | Percentage    |
      | 4   | FIXED_AMOUNT  | 5              | Fixed amount  |
      | 4   | FREE_SHIPPING | 0              | Free shipping |
      | 4   | DISCOUNT_10   | 10             | Percentage    |
    And checkout by Stripe successfully
    Examples:
      | KEY | Product name |
      | 1   | Necklace     |
      | 2   | Necklace     |
      | 3   | Necklace     |
      | 4   | Necklace     |


  Scenario Outline: Verify checkout flow when user checkout with discount buy X get Y #SB_DC_16

    When add products "<Product name>" to cart then navigate to checkout page
    Given apply discount by line item as "<KEY>"
      | KEY | Product name | Quantity discounted | Discount code    | Discount type | Discount value |
      | 1   | Lamp         | 2                   | Discount_XY_30   | Percentage    | 30             |
      | 1   | Necklace     | 2                   |                  |               |                |
      | 2   | Lamp         | 1                   | Discount_XY_Free | Free          | 0              |
      | 2   | Necklace     | 1                   |                  |               |                |
      | 3   | Lamp         | 1                   | buyXgetX_FREE    | Free          | 0              |
      | 3   | Necklace     | 1                   |                  |               |                |
      | 4   | Lamp         | 2                   | buyXgetX_FREE    | Free          | 0              |
      | 4   | Necklace     | 2                   |                  |               |                |
      | 5   | Lamp         | 1                   | Discount_XY_30   | Percentage    | 30             |
      | 5   | Necklace     | 2                   |                  |               |                |
    And checkout by Stripe successfully
    Examples:
      | KEY | Product name      |
      | 1   | Necklace>2;Lamp>2 |
      | 2   | Necklace;Lamp     |
      | 3   | Necklace;Lamp     |
      | 4   | Necklace>2;Lamp>2 |
      | 5   | Necklace>2;Lamp>1 |


  Scenario: Verify code discount free shipping with both conditions "country" and "Minimum quantity of items" #SB_DC_15

    When add products "Necklace>3" to cart then navigate to checkout page
    Then input Customer information
      | Email            | First Name | Last Name | Address           | City         | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 100 Wilshire Blvd | Santa Monica | United States | California | 90401    | 2025550147 | true  | success  |
    And choose shipping method ""
    And Apply discount code "FREESHIP_US_3ITEM"
    And Verify apply discount for order
      | Discount code     | Discount value | Discount type |
      | FREESHIP_US_3ITEM | 0              | Free shipping |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And get the order information including
      | total amount | product list | discount code | discount value | shipping fee |
    Given Access to order detail by order ID
    Then verify order on dashboard with discount "Free shipping"



