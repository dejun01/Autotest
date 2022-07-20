@shippingsetting @shippingItemBasedRate2 @shippingmatchMultigroup
#env = sbase_shipping3
Feature: Setting Shipping zones Item Based rate rule

  Background: Navigate to setting shipping zones page
    Given user login to shopbase dashboard
    Then user navigate to "Settings" screen
    Then navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    And add shipping zones with country and Price Based rate
      | Zone name        | Countries | Price based rate |
      | Vietnam shipping | Vietnam   | Standard VN,,,,5 |

  Scenario: Calculate shipping price when product is not match any shipping item bases rate and shop have no all product rule
    And add item based rate with condition for zone "Vietnam shipping"
      | Rate Name | Group Name | Condition item | Condition | Keywords | Free rate | First item | Each additional item |
      | Rule4     | groupMatch | Product SKU    | contains  | rule4    | False     | 5          | 4                    |
    Given user navigate to "Products>All products" screen
    Then edit information of product on dashboard
      | Product name | Edit item | New Value |
      | Wallet       | SKU       | rule4@    |
      | Watch        | SKU       | holla@    |
    And open shop on storefront
    And add products "Watch>2;Wallet>2" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                  | First Name | Last Name | Address | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | oanhntk@mailtothis.com | Jone       | Doe       | XA DAN  | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Rule4      | 17    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

  Scenario: Calculate shipping price when product in cart match 2 rules which have first item is equal - get rule which has higher additional price
    And add item based rate with condition for zone "Vietnam shipping"
      | Rate Name | Group Name | Condition item | Condition | Keywords | Free rate | First item | Each additional item |
      | Rule3     | groupMatch | Product SKU    | contains  | rule3    | False     | 7          | 3                    |
      | Rule4     | groupMatch | Product SKU    | contains  | rule4    | False     | 5          | 4                    |
      | Rule5     | groupMatch | Product SKU    | contains  | rule5    | False     | 7          | 1                    |
    Given user navigate to "Products>All products" screen
    Then edit information of product on dashboard "Wallet"
      | Edit item | New Value |
      | SKU       | rule4@    |
    Then edit information of product on dashboard "Watch"
      | Edit item | New Value   |
      | SKU       | rule3rule5@ |
    And open shop on storefront
    And add products "Watch>2;Wallet>2" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                  | First Name | Last Name | Address | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | oanhntk@mailtothis.com | Jone       | Doe       | XA DAN  | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Rule3      | 18    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

  Scenario: Calculate shipping price when product match free shipping and shipping #0
    And add item based rate with condition for zone "Vietnam shipping"
      | Rate Name | Group Name | Condition item | Condition | Keywords | Free rate | First item | Each additional item |
      | Rule2     | groupMatch | Product SKU    | contains  | rule2    | False     | 6          | 2                    |
      | Rule5     | groupMatch | Product SKU    | contains  | rule5    | False     | 7          | 1                    |
      | FreeShip  | groupMatch | Product SKU    | contains  | freeship | True      |            |                      |
    Given user navigate to "Products>All products" screen
    Then edit information of product on dashboard "Wallet"
      | Edit item | New Value |
      | SKU       | rule5@    |
    Then edit information of product on dashboard "Watch"
      | Edit item | New Value      |
      | SKU       | rule2freeship@ |
    And open shop on storefront
    And add products "Watch>2;Wallet>2" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                  | First Name | Last Name | Address | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | oanhntk@mailtothis.com | Jone       | Doe       | XA DAN  | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Rule5      | 12    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page


  Scenario: Calculate shipping price when 1 product match item based rate with fee #0 and 1 product match item based rate free ship
    And add item based rate with condition for zone "Vietnam shipping"
      | Rate Name | Group Name | Condition item | Condition | Keywords | Free rate | First item | Each additional item |
      | Rule5     | groupMatch | Product SKU    | contains  | rule5    | False     | 7          | 1                    |
      | FreeShip  | groupMatch | Product SKU    | contains  | freeship | True      |            |                      |
    Given user navigate to "Products>All products" screen
    Then edit information of product on dashboard "Wallet"
      | Edit item | New Value |
      | SKU       | rule5@    |
    Then edit information of product on dashboard "Watch"
      | Edit item | New Value |
      | SKU       | freeship@ |
    And open shop on storefront
    And add products "Watch>2;Wallet>2" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                  | First Name | Last Name | Address | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | oanhntk@mailtothis.com | Jone       | Doe       | XA DAN  | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Rule5      | 8     |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page
    And close browser