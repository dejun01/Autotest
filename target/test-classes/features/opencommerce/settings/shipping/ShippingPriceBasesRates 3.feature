@shippingsetting @shippingzonePriceBase
  #env = sbase_shipping4
Feature: Setting Shipping zones Price base rules

  Background: Navigate to setting shipping zones page
    Given user login to shopbase dashboard
#    Then user navigate to "Products>All products" screen
#    And edit information of product on dashboard "T-shirt"
#      | Edit item | New Value |
#      | Price     | 10        |
    Then user navigate to "Settings" screen
    Then navigate to "Shipping" section in Settings page
    And delete all existed shipping zone


  Scenario: Verify shipping prices in different Shipping zones are applied correctly
    And add shipping zones with country and Price Based rate
      | Zone name        | Countries     | Price based rate  |
      | China shipping   | China         | Standard CN,,,,5  |
      | Vietnam shipping | Vietnam       | Standard VN,,,,10 |
      | US shipping      | United States | Standard US,,,,15 |
    And open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
#    checkout VN
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address          | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | 130, 360, Xa Dan | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules  | Price |
      | Standard VN | 10    |
    And close browser
#    checkout CN
    And open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address | Apartment | City    | Country | State | Zip code | Phone        | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | Beijing | 814       | Beijing | China   | Anhui | 100000   | 954-358-6292 | false | success  |
    And verify shipping base on Price rules
      | Base rules  | Price |
      | Standard CN | 5     |
    And close browser
#    checkout US
    And open shop on storefront
    And add products "T-shirt" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                    | First Name | Last Name | Address            | Apartment | City        | Country       | State      | Zip code | Phone      | Saved | Expected |
      |      | pricebase@mailtothis.com | Jone       | Doe       | 814 Mission Street | 814       | Los Angeles | United States | California | 90001    | 4047957606 | false | success  |
    And verify shipping base on Price rules
      | Base rules  | Price |
      | Standard US | 15    |