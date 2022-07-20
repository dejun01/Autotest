Feature: Checkout setting
#env = setting_checkout_plusbase

  Scenario: Verify when click on button Customize checkout SB_RPLS_RSC_1 SB_RPLS_RSC_2
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen
    Then click on button "Customize checkout" and verify
    And close browser

#Setting Layout checkoutFlow
  Scenario: Verify when click on One Page Checkout SB_RPLS_RSC_5
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen
    When click "One Page Checkout" radio button and Save
    Given open plusbase on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Clip On Silicone Strainer:1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And close browser
    Given user login to plusbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail after created
    And close browser

  Scenario: Verify when click on 3-Steps Checkout SB_RPLS_RSC_6
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen
    When click "3-step Checkout" radio button and Save
    Given open plusbase on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Clip On Silicone Strainer:1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And close browser
    Given user login to plusbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail after created
    And close browser
# setting Footer
  Scenario: Verify when not click Show footer during checkout SB_RPLS_RSC_8
    Given user login to plusbase dashboard
    When user navigate to "Settings" screen
    And navigate to "Checkout" section in Settings page
    And Setting checkout footer
      | Setting                     | status |
      | Show footer during checkout | check  |
    Given open plusbase on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Clip On Silicone Strainer:1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And Verify display footer
    And close browser

  Scenario: Verify when click Show footer during checkout SB_RPLS_RSC_8
    Given user login to plusbase dashboard
    When user navigate to "Settings" screen
    And navigate to "Checkout" section in Settings page
    And Setting checkout footer
      | Setting                     | status  |
      | Show footer during checkout | uncheck |
    Given open plusbase on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Clip On Silicone Strainer:1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And Verify not display footer
    And close browser

    #Setting customer account
  Scenario Outline: Verify when setting Accounts customer on checkout SB_RPLS_RSC_11
    Given user login to plusbase dashboard
    And user navigate to "Settings" screen
    And Click to "Checkout" section at Settings screen
    When click radio button by "<KEY>"
      | KEY | Setting               |
      | 1   | Accounts are optional |
      | 2   | Accounts are disabled |
    Given open plusbase on storefront
    Then verify header by "<KEY>"
      | KEY | Setting               |
      | 1   | Accounts are optional |
      | 2   | Accounts are disabled |
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Clip On Silicone Strainer:1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And close browser
    Examples: <KEY>
      | KEY |
      | 1   |
      | 2   |
