Feature: Setting Shipping PlusBase
  #env =setting_shipping_plusbase

  Scenario: Verify setting shipping success
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page
    And Setting shipping zone and verify
      | shipping | free shipping rate setting | free shipping rate after setting |
      | DOMESTIC | 80                         | 80                               |
      | DOMESTIC | 69                         | 70                               |
      | DOMESTIC | -70                        | 70                               |
      | DOMESTIC | abc                        | 70                               |
      | DOMESTIC | !$%^&*                     | 70                               |
    And close browser

  Scenario: Verify checkout order after setting shipping rate
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page
    And Setting shipping zone and verify
      | shipping | free shipping rate setting | free shipping rate after setting |
      | DOMESTIC | 90                         | 90                               |
    Given open plusbase on storefront
    And add products "Knitted skirt>10" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address           | Apartment | City         | Country       | State    | Zip code | Phone      |
      | @mailtothis.com@ | Super      | Tester    | 100 Wilshire Blvd | 100       | Santa Monica | United States | New York | 10001    | 2025550141 |
    And choose shipping method ""
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV | Payment Gateway |
      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Stripe          |
    Then verify shipping free
      | Base rules | Price |
      | Shipping   | Free  |
    And verify thank you page
    Given close browser






