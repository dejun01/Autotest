Feature: Analytics verify total sales
  #env =sbase_analytics_total_salse


  Scenario: Verify total sales after create order
    Given user login to shopbase dashboard
    When user navigate to "Analytics" screen
    And get Total sales
    Given open shop on storefront
    Then checkout successfully via stripe with cart "t-shirt>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And redirect to shopbase dashboard
    When user navigate to "Analytics" screen
    And Verify Total sales after created order
