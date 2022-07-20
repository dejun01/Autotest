Feature: Scan Order Checkout via Asia-Bill
#  sbase_scanorder_asiaBill
  Background:
    Given clear all data

  Scenario: Checkout via Asia-Bill unsuccessfully then scan order #SB_CHE_ASB_10
    Given verify scan order from previous run
    Then remove all order
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base Block | 01/23        | 100 |
    And verify block redirect and scan order result