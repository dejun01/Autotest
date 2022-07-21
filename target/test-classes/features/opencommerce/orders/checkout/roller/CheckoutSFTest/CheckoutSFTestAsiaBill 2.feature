Feature: Roller - Checkout via Asia-Bill
#  prod_sbase_checkout_sftest1
  Background:
    Given clear all data

#    --------------------------------------------------------------------------------------------------------------------------------------------
#    AsiaBill


  Scenario: Checkout via Asia-Bill successfully
    Given open shop on storefront
    And add products "Bikini" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Card Issuing Bank |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/2023      | 100 | Visa      | OCG Bank          |
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Asia-Bill without adding post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Card Issuing Bank |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/2023      | 100 | Visa      | OCG Bank          |
    And order product "" with custom option is "" in post purchase offer
    And get the order information including
      | order number | order id | total amount | customer name | customer email | product list | shipping fee |
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Asia-Bill with adding post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then navigate to checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Card Issuing Bank |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/2023      | 100 | Visa      | OCG Bank          |
    And order product "Bikini" with custom option is "" in post purchase offer
    And complete payment for post purchase item
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout via Asia-Bill when cancelling payment
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Card Issuing Bank | Cancel |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/2023      | 100 | Visa      | OCG Bank          | Yes    |
    And close browser

  Scenario: Checkout via Asia-Bill successfully when cancelling payment for post purchase item
    Given open shop on storefront
    And add products "Post Purchase" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | ShopBase   | Auto      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Card Issuing Bank |
      | Asia-Bill       | Asia-Bill      | 4000020951595032 | Shop Base       | 01/2023      | 100 | Visa      | OCG Bank          |
    And order product "Bikini" with custom option is "" in post purchase offer
    Then cancel payment for post-purchase item then verify checkout detail
    And verify thank you page then get all information
    Given user login to shopbase dashboard by API
    Given Access to order detail by order ID
    Then verify order detail on dashboard
    And close browser