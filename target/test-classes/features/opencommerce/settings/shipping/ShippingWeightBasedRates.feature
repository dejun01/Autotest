@shippingsetting @shippingWeightBaseRate
#env = sbase_shipping5

Feature: Verify Weight Base Rate on ShopBase
  Background: reset data
    Given clear all data

  Scenario: Precondition- Setup shipping weight base rate is applied correctly in different shipping zone
    Given user login to shopbase dashboard
    Then user navigate to "Settings" screen
    Then navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    Given add shipping zones with country and Weight based rate
      | Zone name        | Countries     | Weight based rate |
      | China shipping   | China         | Weight CN,,,,5    |
      | Vietnam shipping | Vietnam       | Weight VN,,,,10   |
      | US shipping      | United States | Weight US,,,,15   |

  Scenario Outline: Verify shipping weight base rate is applied correctly in different shipping zone
    And open shop on storefront
    And add products "Lamp" to cart then navigate to checkout page
    And input Customer information as "<KEY>"
      | KEY | Email                      | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone        | Saved | Expected |
      | 1   | thuntt_john@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan   | 130       | Hanoi       | Vietnam       |            | 10000    | 0944193269   | false | success  |
      | 2   | thuntt_john@mailtothis.com | Jone       | Doe       | Beijing            | 814       | Beijing     | China         | Anhui      | 100000   | 954-358-6292 | false | success  |
      | 3   | thuntt_john@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606   | false | success  |
    And verify shipping base on Price rules as "<KEY>"
      | KEY | Base rules | Price |
      | 1   | Weight VN  | 10    |
      | 2   | Weight CN  | 5     |
      | 3   | Weight US  | 15    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    And close browser
    Examples:
      | KEY | Case                      |
      | 1   | Checkout in Vietnam       |
      | 2   | Checkout in China         |
      | 3   | Checkout in United States |