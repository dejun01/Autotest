Feature: Config shipping profile on dashboard
  #env = sbase_shipping_profile_setting

  Background: Delete all shipping zone
    Given user login to shopbase dashboard by API
    When user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page

  Scenario: Config shipping rules for General profile
    And navigate to Manage rates screen of General Profile shipping
    And delete all shipping zone in General Profile shipping
    And creat profile with name "General Profile" then create zone
      | Zone name | Countries              |
      | EU        | Canada, United Kingdom |
    And add rates for "EU" zone
      | Rate name         | Price | Additional condition      |
      | Standard shipping | 8$    | Based on item quantity:8$ |
    And click Save changes button

  Scenario: Config shipping rules for Custom profile
    And delete all Custom Profile
#    And add exclusion rules for Custom Profile with All conditions
    And creat profile with name "Fragile product" then create zone
      | Zone name | Countries              |
      | EU        | Canada, United Kingdom |
    And add rates for "EU" zone
      | Rate name         | Price | Additional condition         |
      | Standard shipping | 8.99$ | Based on item quantity:7.99$ |
    And add product rules for Custom Profile with All conditions
      | FILTER                                           |
      | [Product SKU] [matches regex] [AP-Unisex-Hoodie] |
    And click Save changes button

    And user navigate to "Products>All products" screen
    And edit information of product on dashboard "Unisex Hoodie"
      | Edit item | New Value               |
      | SKU       | AP-Unisex-Hoodie-S-1808 |

    And open shop on storefront on storefront
    And add products "Unisex Hoodie>2" to cart then checkout
    And input Customer information
      | Email                      | First Name | Last Name | Address     | City       | State | Country | Zip code   | Phone      |
      | @mailinator.girl-viet.com@ | David      | Murphy    | 201 Wood St | Whitehorse | Yukon | Canada  | ON K1P 5R7 | 2056289809 |
    Then verify shipping base on Price rules
      | Base rules        | Price |
      | Standard shipping | 16.98 |
    ## shipping rate = 8.99(first item) + 7.99 (additional item)







