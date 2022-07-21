Feature: Checkout user upsale
# env = prod_checkout_use_discount
  Background: open shop storefront
    Given clear all data
    Given open shop on storefront

  Scenario: SB_SF_CHE_6.2: Verify checkout when discount invalid
    When add products "Necklace" to cart then navigate to checkout page
    When user enter invalid discount code
      | Discount    | Expected                                                   |
      | NOT_EXISTED | Unable to find a valid discount matching the code entered. |
      | Expired     | Unable to find a valid discount matching the code entered. |
      | Not started | Unable to find a valid discount matching the code entered. |
      | DISABLED    | Unable to find a valid discount matching the code entered. |


  Scenario Outline: Verify checkout flow when user checkout with discount code
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


  Scenario Outline: Verify checkout flow when user checkout with discount buy X get Y on bassy theme
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
