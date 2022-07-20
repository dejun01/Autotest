Feature:  Analytics
#plusbase_analytics

  Background: Access analytics page
    Given user login to shopbase dashboard
    And user navigate to "Analytics" screen


  Scenario: Verify Total Profit
    And Verify UI Overview dashboard page
    And get profit of "Total profits"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "iPhone 11 tips"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And redirect to shopbase dashboard
    When user navigate to "Orders" screen
    And get Profit of your order
    And user navigate to "Analytics" screen
    And Verify Total profit after created order

 Scenario Outline: Verify total profit with boost upsell
    And get profit of "Total profits"
    And get the "Total profits" "Total sales via Boost Upsell"
    Given open shop on storefront
    And verify add to cart product name as "iPhone 11 tips"
    Then checkout successfully via stripe with cart "<Product name>"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop | Base | 100 Wilshire Blvd | United States | Santa Monica | 90401 | California | 2025550147 | Stripe | Credit Card | 4242424242424242 | Shopbase | 11/22 | 113 | Visa |
    And redirect to shopbase dashboard
    When user navigate to "Orders" screen
    And get Profit of your order
    And user navigate to "Analytics" screen
    And Verify Total profit with order boost upsell
    Examples:
      | Product name                                       |
      | V3 SOLIDS HELMET                                   |
      | Men's Dauntless Convertible Leather Jacket         |
      | GGWC0840 FRD                                       |
      | OHIO STATE BUCKEYES COLLEGE FOOTBALL TEAMS – SHOES |

  Scenario: Check AOP
    And Verify AOP
    And Verify AOP of "Online store"
    And Verify AOP of the order Boost Upsell




