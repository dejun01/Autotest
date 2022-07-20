Feature: Scan Order Checkout via Cardpay
#   sbase_scanorder_cardpay
  Background:
    Given clear all data

  Scenario: Checkout via Cardpay unsuccessfully then scan order
    Given verify scan order from previous run
    Then remove all order
    Given open shop on storefront
    And add products "Airpod" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | 3DS  | 3Ds status |
      | Unlimint        | Cardpay        | 4000000000000002 | Shop Base Block | 01/23        | 100 | Visa      | yes  | success    |
    And verify block redirect and scan order result
