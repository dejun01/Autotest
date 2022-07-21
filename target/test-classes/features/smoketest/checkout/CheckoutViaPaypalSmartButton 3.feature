Feature: Roller - Checkout with Paypal smart button
  #Env: sbase_smoke_checkout_paypal_smart_button
  #Gateway is activated by PCP flow
  #Use to checkout via Paypal smart button + Express in 1 Pages

  Background:
    Given clear all data

  Scenario: Checkout by Paypal smart button - paypal with postpurchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type | Paypal smart button |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | express   | yes                 |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And verify thank you page then get all information
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser


  Scenario: Checkout by Paypal smart button - debit or credit card without post purchase item
    And open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Card number      | Expired Date | CVV | Card type | Paypal smart button |
      | Paypal         | Paypal          | 4170439564464028 | 02/22        | 923 | credit    | yes                 |
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

