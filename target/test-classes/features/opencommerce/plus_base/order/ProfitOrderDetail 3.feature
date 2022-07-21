#plusbase_dashboard
Feature: Profit order detail

  Background: open shop on storefront plusbase
    Then user login to shopbase dashboard
    And user navigate to "balance" screen by url
    And get balance before checkout order
    Given open shop on storefront

  Scenario: 1. Verify profit order has shipping fee
    Given checkout of order fulfillment successfully via stripe with cart "(Test product)Plus Size Cut Out - Adjusted= 0"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then redirect to shopbase dashboard
    Then user navigate to "orders" screen by url
    And verify order with fulfillment status = "Authorized"
      | Product                                       | SKU                         | Quantity |
      | (Test product)Plus Size Cut Out - Adjusted= 0 | PB-AP-UnisexT-shirt-White-S | 1        |
    And verify paid by customer of order
      | Quantity |
      | 1        |
    And verify your profit of order with Is adjusted = "false"
    And user navigate to "balance" screen by url
    And verify balance of order with status = "Paid"
    And close browser

  Scenario Outline: 2. Verify profit order has shipping fee, discount code # freeship
    Given checkout of order fulfillment successfully via stripe with cart "(Test product)Plus Size Cut Out - Adjusted= 0"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount   |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | <Discount> |
    Then redirect to shopbase dashboard
    Then user navigate to "orders" screen by url
    And verify order with fulfillment status = "Authorized"
      | Product                                       | SKU                         | Quantity |
      | (Test product)Plus Size Cut Out - Adjusted= 0 | PB-AP-UnisexT-shirt-White-S | 1        |
    And verify paid by customer of order
      | Quantity | Discount name  | Freeship   |
      | 1        | Offer Discount | <Freeship> |
    And verify your profit of order with Is adjusted = "false"
    And user navigate to "balance" screen by url
    And verify balance of order with status = "Paid"
    And close browser

    Examples:
      | Discount    | Freeship |
      | Discount 30 | no       |
      | Freeship    | yes      |

  Scenario Outline: 4. Verify profit order has adjusted
    Given checkout of order fulfillment successfully via stripe with cart "<Product>"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then redirect to shopbase dashboard
    Then user navigate to "orders" screen by url
    And verify order with fulfillment status = "Authorized"
      | Product   | SKU                         | Quantity |
      | <Product> | PB-AP-UnisexT-shirt-White-S | 1        |
    And verify paid by customer of order
      | Quantity |
      | 1        |
    And verify your profit of order with Is adjusted = "true"
    And user navigate to "balance" screen by url
    And verify balance of order with status = "Paid"
    And close browser

    Examples:
      | Product                                                          |
      | (Test product)Beige Dress for Women 2021 Summer - Adjusted dương |
      | (Test product)Black Dress for Women 2021 Summer - Adjusted âm    |

  Scenario: Verify profit order has PPC
    Given checkout of order fulfillment successfully via stripe with cart "(Test product)Plus Size Cut Out - Adjusted= 0"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | post purchase             | Is plusbase |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | (Test product)PP discount | true        |
    Then redirect to shopbase dashboard
    Then user navigate to "orders" screen by url
    And verify order with fulfillment status = "Authorized"
      | Product                                       | SKU                                     | Quantity |
      | (Test product)Plus Size Cut Out - Adjusted= 0 | PB-AP-UnisexT-shirt-White-S             | 1        |
      | (Test product)PP discount                     | PB-AOP-SherpaBlanket-Alloverprint-Large | 1        |
    And verify paid by customer of order
      | Quantity |
      | 2        |
    And verify your profit of order with Is adjusted = "false"
    And user navigate to "balance" screen by url
    And verify balance of order with status = "Paid"
    And close browser