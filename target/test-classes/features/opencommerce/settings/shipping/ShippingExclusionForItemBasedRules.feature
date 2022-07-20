#environment = sbase_shipping_exclusion
  @shippingExclusion
Feature: Allow user to exclude line items from Item based rules

  Background: Setup shipping rule on ShopBase dashboard
    Given user login to shopbase dashboard
#    And user navigate to "Settings" screen
#    And navigate to "Shipping" section in Settings page
#    And delete all existed shipping zone
    And clear all data

  Scenario: Cart including 1 excluded item and belong to 1 group, show warning message for excluded item
    """add shipping zones with country and Price Based rate
      | Zone name | Countries     |
      | DOMESTIC  | United States |
    add item based rate with condition for zone "DOMESTIC"
      | Rate Name         | GROUP    | FILTER                                          | EXCLUSION                                       | Free rate | First item | Each additional item |
      | All products      | standard |                                                 | [Product SKU] [matches regex] [AP-UnisexHoodie] | False     | 8          | 8                    |
      | Standard shipping | standard | [Product SKU] [matches regex] [AP-UnisexHoodie] |                                                 | False     | 6.99       | 2.49                 |
      | Standard shipping | standard | [Product SKU] [matches regex] [AOP-Tumbler30oz] |                                                 | False     | 6.99       | 6.99                 |"""
    And user navigate to "Products>All products" screen
    And edit information of product on dashboard "Unisex Hoodie"
      | Edit item | New Value              |
      | SKU       | AP-UnisexHoodie-S-1808 |
    And open shop on storefront
    And add products "Unisex Hoodie" to cart then navigate to checkout page
    And input Customer information
      | Email                      | First Name | Last Name | Address     | City        | State      | Country       | Zip code | Phone      |
      | @mailinator.girl-viet.com@ | David      | Murphy    | 201 Wood St | Los Angeles | California | United States | 90064    | 2056289809 |
    Then verify the message along with non shippable products is displayed
      | Product not shippable | Message                                                                                                                |
      | Unisex Hoodie         | The following items don’t ship to your location. Please replace them with another products and place your order again. |
    And close browser

  Scenario: Cart including 1 excluded item and 1 valid item and belong to 1 group, show warning message for excluded item
    """add shipping zones with country and Price Based rate
      | Zone name | Countries |
      | UK    | United Kingdom    |
    add item based rate with condition for zone "UK"
      | Rate Name         | GROUP    | FILTER                                          | EXCLUSION                                       | Free rate | First item | Each additional item |
      | All products      | standard |                                                 | [Product SKU] [matches regex] [AP-UnisexHoodie] | False     | 8          | 8                    |
      | Standard shipping | standard | [Product SKU] [matches regex] [AP-Shirt]        |                                                 | False     | 6.99       | 2.49                 |
      | Standard shipping | standard | [Product SKU] [matches regex] [AOP-Tumbler30oz] |                                                 | False     | 6.99       | 6.99                 |"""
    And user navigate to "Products>All products" screen
    And edit information of product on dashboard "Unisex Hoodie"
      | Edit item | New Value              |
      | SKU       | AP-UnisexHoodie-S-1808 |
    And open shop on storefront
    And add products "Unisex Hoodie>1;Shirt>1" to cart then navigate to checkout page
    And input Customer information
      | Email                      | First Name | Last Name | Address     | City       | Country        | Zip code   | Phone      |
      | @mailinator.girl-viet.com@ | David      | Murphy    | 201 Wood St | Lazonby    | United Kingdom | CA101AA    | 2056289809 |
    Then verify the message along with non shippable products is displayed
      | Product not shippable | Message                                                                                                                |
      | Unisex Hoodie         | The following items don’t ship to your location. Please replace them with another products and place your order again. |
    And close browser

  Scenario: Cart including 2 excluded items, show warning message for all excluded items
    """add shipping zones with country and Price Based rate
      | Zone name | Countries |
      | CANADA    | Canada    |
    add item based rate with condition for zone "CANADA"
      | Rate Name         | GROUP    | FILTER                                   | EXCLUSION                                  | Free rate | First item | Each additional item |
      | All products      | express  |                                          | [Product SKU] [matches regex] [AP-Canvas]  | False     | 9.99       | 8.99                 |
      | Express shipping  | express  | [Product SKU] [matches regex] [AP-Shirt] |                                            | False     | 8.99       | 6.99                 |
      | All products      | standard |                                          | [Product SKU] [matches regex] [AP-Tumbler] | False     | 8          | 8                    |
      | Standard shipping | standard | [Product SKU] [matches regex] [AP-Shirt] |                                            | False     | 6.99       | 2.49                 |"""
    And user navigate to "Products>All products" screen
    And edit information of product on dashboard "Canvas"
      | Edit item | New Value     |
      | SKU       | AP-Canvas-100 |
    And edit information of product on dashboard "Tumbler"
      | Edit item | New Value      |
      | SKU       | AP-Tumbler-110 |
    And open shop on storefront
    And add products "Canvas>1;Tumbler>1" to cart then navigate to checkout page
    And input Customer information
      | Email                      | First Name | Last Name | Address     | City       | State | Country | Zip code   | Phone      |
      | @mailinator.girl-viet.com@ | David      | Murphy    | 201 Wood St | Whitehorse | Yukon | Canada  | ON K1P 5R7 | 2056289809 |
    Then verify the message along with non shippable products is displayed
      | Product not shippable | Message                                                                                                                |
      | Canvas, Tumbler       | The following items don’t ship to your location. Please replace them with another products and place your order again. |
    And close browser

  Scenario: Cart including 1 item belong 2 groups, the item is excluded from 1 group still shipping rate with remaining group
    """add shipping zones with country and Price Based rate
      | Zone name | Countries |
      | AU    | Australia    |
    add item based rate with condition for zone "AU"
      | Rate Name         | GROUP    | FILTER                                   | EXCLUSION                                       | Free rate | First item | Each additional item |
      | All products      | express  |                                          | [Product SKU] [matches regex] [AP-UnisexHoodie] | False     | 9.99       | 8.99                 |
      | Express shipping  | express  | [Product SKU] [matches regex] [AP-Shirt] |                                                 | False     | 8.99       | 6.99                 |
      | All products      | standard |                                          |                                                 | False     | 8          | 8                    |
      | Standard shipping | standard | [Product SKU] [matches regex] [AP-Shirt] |                                                 | False     | 6.99       | 2.49                 |"""
    And user navigate to "Products>All products" screen
    And edit information of product on dashboard "Unisex Hoodie"
      | Edit item | New Value              |
      | SKU       | AP-UnisexHoodie-S-1808 |
    And edit information of product on dashboard "Shirt"
      | Edit item | New Value       |
      | SKU       | AP-Shirt-M-1996 |
    And open shop on storefront
    And add products "Unisex Hoodie>1;Shirt>1" to cart then navigate to checkout page
    And input Customer information
      | Email                      | First Name | Last Name | Address     | City       | State                        | Country   | Zip code | Phone      |
      | @mailinator.girl-viet.com@ | David      | Murphy    | 201 Wood St | Parkes     | Australian Capital Territory | Australia | 2600     | 2056289809 |
    Then verify shipping base on Price rules
      | Base rules        | Price |
      | Standard shipping | 14.99 |
    ## shipping rate = 6.99(first item) + 8 (additional item)
    And close browser
