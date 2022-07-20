Feature: Roller - Checkout with Paypal Standard
  #Env: sbase_smoke_checkout_spay_paypal
  #Gateway is activated by Key
  #Use to checkout via Paypal standard + Express in 3 Pages

  Background:
    Given clear all data

  Scenario: Verify checkout flow when using normal paypal with post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550141 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment method | Payment gateway | Paypal account     | Password | Card type |
      | Paypal         | Paypal          | buyer@shopbase.com | 123456@a | standard  |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And verify thank you page then get all information
    Given user login to shopbase dashboard
    And user navigate to "订单" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser