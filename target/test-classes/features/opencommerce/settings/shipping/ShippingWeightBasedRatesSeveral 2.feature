@shippingsetting @shippingWeightBaseRate
#env = sbase_shipping5

Feature: Verify Weight Base Rate on ShopBase
  Background: reset data
    Given clear all data

  Scenario: Precondition- Setup several price based rule in a Shipping zone
    Given user login to shopbase dashboard
    Then user navigate to "Settings" screen
    Then navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    Given add shipping zones with country and Weight based rate
      | Zone name        | Countries | Weight based rate |
      | Vietnam shipping | Vietnam   | Standard VN,,,,5  |
    And add weight based rate for shipping zone "Vietnam shipping"
      | Rate name         | Min order weight | Max order weight | Free rate | Rate amount |
      | VN Freeship       |                  |                  | true      |             |
      | VN Min order free | 20               |                  | true      |             |
      | VN Min order      | 20               |                  | false     | 10          |
      | VN Max order free |                  | 50               | true      |             |
      | VN Max order      |                  | 50               | false     | 15          |
      | VN Min Max free   | 20               | 50               | true      |             |
      | VN Min Max        | 20               | 50               | false     | 20          |

  Scenario Outline:Verify several price based rule in a Shipping zone
    And open shop on storefront
    And add products "<Product>" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                      | First Name | Last Name | Address          | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | thuntt_john@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules as "<KEY>"
      | KEY | Base rules        | Price |
      | 1   | Standard VN       | 5     |
      | 1   | VN Freeship       | 0     |
      | 1   | VN Max order free | 0     |
      | 1   | VN Max order      | 15    |
      | 2   | Standard VN       | 5     |
      | 2   | VN Freeship       | 0     |
      | 2   | VN Min order free | 0     |
      | 2   | VN Min order      | 10    |
      | 2   | VN Max order free | 0     |
      | 2   | VN Max order      | 15    |
      | 2   | VN Min Max free   | 0     |
      | 2   | VN Min Max        | 20    |
      | 3   | Standard VN       | 5     |
      | 3   | VN Freeship       | 0     |
      | 3   | VN Min order free | 0     |
      | 3   | VN Min order      | 10    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    And close browser
    Examples:
      | KEY | Product   | Case           |
      | 1   | T-shirt   | No min price   |
      | 2   | T-shirt>2 | match min max  |
      | 3   | T-shirt>5 | over max price |