@checkoutFlowRoller @checkout
Feature: Roller - Checkout Flow
  #env = sbase_roller_checkout

  Background:
    Given clear all data

  Scenario: SB_SF_CHE_5.9 - Verify checkout when product check "Allow customers to purchase this product when it's out of stock"
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    And edit product "Watch" have quantity is "1" and allow customers to purchase this product when it's out of stock is "true"
    Given open shop on storefront
    And add products "Watch>3" to cart then checkout
    And checkout by Stripe successfully
    Then verify product is "Watch" have 3 items in order
    And verify quantity of product "Watch" in dashboard is "-2" by API
    And close browser


  Scenario: SB_SF_CHE_5.12 - Verify checkout when inventory policy of product equal Don't track inventory
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    And edit product "Wallet" have Inventory policy is "Don't track inventory"
    When open shop on storefront
    And add products "Wallet>3" to cart then checkout
    And input Customer information
      | Email            | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      | @mailtothis.com@ | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | true  | success  |
    And choose shipping method "International Shipping"
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    Then verify product is "Wallet" have 3 items in order
    And close browser

  Scenario: Checkout successfully in case - shipping and billing addresses are not the same #SB_CHE_CHEN_3 #SB_CHE_CHEN_5
    Given open shop on storefront
    And add products "Thong" to cart then checkout
    And verify css of checkout page
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input billing address
      | First Name | Last Name | Address       | Country       | City    | Zip code | State    | Phone      |
      | Buyer      | Nguyen    | W Webster Ave | United States | Chicago | 60613    | Illinois | 2025550150 |
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And verify thank you page then get all information
    And open mailbox of buyer with subject
      | Notification       | Subject                          | Content                      |
      | Order confirmation | Order {{order_number}} confirmed | Thank you for your purchase! |

  Scenario: Checkout successfully in case - total amount = 0 #SB_CHE_CHEN_8
    Given open shop on storefront
    And add products "Bikini" to cart then checkout
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And verify thank you page then get all information
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser

  Scenario: Checkout successfully in case - using discount and total amount = 0 #SB_CHE_CHEN_7
    Given open shop on storefront
    And add products "Thong" to cart then checkout
    And Apply discount code "SALE100"
    When input Customer information
      | Email            | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | @mailtothis.com@ | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And verify thank you page then get all information
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Given click on order name in unfulfilled orders list
    Then verify order detail on dashboard
    And close browser
