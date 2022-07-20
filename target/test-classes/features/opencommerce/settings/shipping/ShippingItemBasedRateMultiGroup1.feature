Feature: Setting Shipping zones Item Based rate rule
#  sbase_shipping2
  Background: Navigate to setting shipping zones page
    Given user login to shopbase dashboard
    Then user navigate to "Settings" screen
    Then navigate to "Shipping" section in Settings page
    And delete all existed shipping zone
    And add shipping zones with country and Price Based rate
      | Zone name        | Countries | Price based rate |
      | Vietnam shipping | Vietnam   | Standard VN,,,,5 |

  Scenario: Calculate first item price base on highest first item matched rule
    And add item based rate with condition for zone "Vietnam shipping"
      | Rate Name | Group Name | Condition item | Condition | Keywords | Free rate | First item | Each additional item |
      | Rule2     | groupMatch | Product SKU    | contains  | rule2    | False     | 6          | 2                    |
      | Rule1     | groupMatch | Product SKU    | contains  | rule1    | False     | 5          | 1                    |
    Given user navigate to "Products>All products" screen
    Then edit information of product on dashboard "Wallet"
      | Edit item | New Value |
      | SKU       | rule1@    |
    Then edit information of product on dashboard "Watch"
      | Edit item | New Value |
      | SKU       | rule2@    |
    And open shop on storefront
    And add products "Watch>2;Wallet>2" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                  | First Name | Last Name | Address | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | oanhntk@mailtothis.com | Jone       | Doe       | XA DAN  | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Rule2      | 10    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

  Scenario: Calculate shipping fee for additional item for highest additional item rule
    And add item based rate with condition for zone "Vietnam shipping"
      | Rate Name | Group Name | Condition item | Condition | Keywords | Free rate | First item | Each additional item |
      | Rule2     | groupMatch | Product SKU    | contains  | rule2    | False     | 6          | 2                    |
      | Rule3     | groupMatch | Product SKU    | contains  | rule3    | False     | 7          | 3                    |
      | Rule4     | groupMatch | Product SKU    | contains  | rule4    | False     | 5          | 4                    |
    Given user navigate to "Products>All products" screen
    Then edit information of product on dashboard "Wallet"
      | Edit item | New Value   |
      | SKU       | rule3rule4@ |
    Then edit information of product on dashboard "Watch"
      | Edit item | New Value |
      | SKU       | rule2@    |
    And open shop on storefront
    And add products "Watch>2;Wallet>2" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                  | First Name | Last Name | Address | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | oanhntk@mailtothis.com | Jone       | Doe       | XA DAN  | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Rule3      | 15    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

  Scenario: Calculate product which is not belong to any shipping item bases rate as all product rule
    And add item based rate with condition for zone "Vietnam shipping"
      | Rate Name  | Group Name | Condition item | Condition | Keywords | Free rate | First item | Each additional item |
      | AllProduct | groupMatch |                |           |          | False     | 8          | 8                    |
      | Rule4      | groupMatch | Product SKU    | contains  | rule4    | False     | 5          | 4                    |
    Given user navigate to "Products>All products" screen
    Then edit information of product on dashboard "Wallet"
      | Edit item | New Value |
      | SKU       | rule4@    |
    Then edit information of product on dashboard "Watch"
      | Edit item | New Value |
      | SKU       | holla@    |
    And open shop on storefront
    And add products "Watch>2;Wallet>2" to cart then navigate to checkout page
    And input Customer information
      | Case | Email                  | First Name | Last Name | Address | Apartment | City  | Country | State | Zip code | Phone      | Saved | Expected |
      |      | oanhntk@mailtothis.com | Jone       | Doe       | XA DAN  | 130       | Hanoi | Vietnam |       | 10000    | 0944193269 | false | success  |
    And verify shipping base on Price rules
      | Base rules | Price |
      | Rule4      | 25    |
    And input card information of Stripe and complete order
      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
      | Credit Card    | 4242424242424242 | John Doe        | 12/22        | 111 |
    And verify thank you page

  Scenario: Delete all shipping rule
    Given delete all existed shipping zone
    And close browser