Feature: Roller - Checkout via Asia-Bill
#  sbase_checkout_asiaBill
  Background:
    Given clear all data

  Scenario: Checkout via Asia-Bill successfully
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/23        | 100 |
    And verify thank you page then get all information

    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Asia-Bill without adding post purchase item #SB_CHE_ASB_6
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/23        | 100 |
    And order product "" with custom option is "" in post purchase offer
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Asia-Bill with adding post purchase item #SB_CHE_ASB_7
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/23        | 100 |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Asia-Bill when cancelling payment #SB_CHE_ASB_9
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Cancel |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/23        | 100 | Yes    |
    And close browser

  Scenario: Checkout via Asia-Bill successfully when cancelling payment for post purchase item #SB_CHE_ASB_8
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/23        | 100 |
    And order product "Bikini" with custom option is "" in post purchase offer
    Then cancel payment for post-purchase item then verify checkout detail
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser