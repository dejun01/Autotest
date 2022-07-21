@shippingsetting @shippingzonePriceBase
  #env = sbase_shipping4
Feature: Setting Shipping zones Price base rules

  Scenario: Verify several price based rule in a Shipping zone
    Given user login to shopbase dashboard
#    Then user navigate to "Products>All products" screen
#    And edit information of product on dashboard "T-shirt"
#      | Edit item | New Value |
#      | Price     | 10        |
    Then user navigate to "Settings" screen
    Then navigate to "Shipping" section in Settings page
    And delete all existed shipping zone

    Given add shipping zones with country and Price Based rate
      | Zone name        | Countries | Price based rate |
      | Vietnam shipping | Vietnam   | Standard VN,,,,5 |
    And add price based rate for shipping zone "Vietnam shipping"
      | Rate name         | Min order price | Max order price | Free rate | Rate amount |
      | VN Freeship       |                 |                 | true      |             |
      | VN Min order free | 50              |                 | true      |             |
      | VN Min order      | 50              |                 | false     | 10          |
      | VN Max order free |                 | 80              | true      |             |
      | VN Max order      |                 | 80              | false     | 15          |
      | VN Min Max free   | 50              | 80              | true      |             |
      | VN Min Max        | 50              | 80              | false     | 20          |

#    No min price
    And open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address          | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules        | Price |
      | Standard VN       | 5     |
      | VN Freeship       | 0     |
      | VN Max order free | 0     |
      | VN Max order      | 15    |
    And close browser
#    match min max
    And open shop on storefront
    And add products "T-shirt>5" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address          | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules        | Price |
      | Standard VN       | 5     |
      | VN Freeship       | 0     |
      | VN Min order free | 0     |
      | VN Min order      | 10    |
      | VN Max order free | 0     |
      | VN Max order      | 15    |
      | VN Min Max free   | 0     |
      | VN Min Max        | 20    |
    And close browser
#    over max price
    And open shop on storefront
    And add products "T-shirt>9" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address          | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules        | Price |
      | Standard VN       | 5     |
      | VN Freeship       | 0     |
      | VN Min order free | 0     |
      | VN Min order      | 10    |
    And close browser

  Scenario: After apply discount in shipping method page, automatically select another shipping method when total order isnt reach min
    """And add price based rate for shipping zone "Vietnam shipping"
      | Rate name         | Min order price | Max order price | Free rate | Rate amount |
      | VN Freeship       |                 |                 | true      |             |
      | VN Min order free | 50              |                 | true      |             |
      | VN Min order      | 50              |                 | false     | 10          |
      | VN Max order free |                 | 80              | true      |             |
      | VN Max order      |                 | 80              | false     | 15          |
      | VN Min Max free   | 50              | 80              | true      |             |
      | VN Min Max        | 50              | 80              | false     | 20          |"""
    And open shop on storefront
    And add products "T-shirt>5" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address          | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    Then input discount code is "Discount10"
    And verify shipping base on Price rules
      | Base rules        | Price |
      | Standard VN       | 5     |
      | VN Freeship       | 0     |
      | VN Max order free | 0     |
      | VN Max order      | 15    |
    And verify shipping method was chosen is "VN Max order"
    And close browser

  Scenario: After apply discount in payment method page, automatically select another shipping method when total order isnt reach min
  """And add price based rate for shipping zone "Vietnam shipping"
      | Rate name         | Min order price | Max order price | Free rate | Rate amount |
      | VN Freeship       |                 |                 | true      |             |
      | VN Min order free | 50              |                 | true      |             |
      | VN Min order      | 50              |                 | false     | 10          |
      | VN Max order free |                 | 80              | true      |             |
      | VN Max order      |                 | 80              | false     | 15          |
      | VN Min Max free   | 50              | 80              | true      |             |
      | VN Min Max        | 50              | 80              | false     | 20          |"""
    And open shop on storefront
    And add products "T-shirt>5" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address          | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And choose shipping method "VN Min order free"
    And verify shipping method was chosen is "VN Min order free"
    Then input discount code is "Discount10"
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Error message                                                                                                                 |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      | The Shipping Method is updated or invalid. Please change your Shipping Method. (Error code: CHECKOUT_MISSING_SHIPPING_METHOD) |
    And close browser