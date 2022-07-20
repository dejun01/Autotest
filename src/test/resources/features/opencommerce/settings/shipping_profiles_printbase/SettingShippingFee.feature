Feature: Config shipping profile on dashboard
  #env = sbase_shipping_profile_setting_printbase

  Background: Delete all shipping zone
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And navigate to "Shipping" section in Settings page

#  Scenario: Config shipping rules for General profile
#    And navigate to Manage rates screen of General Profile shipping
#    And delete all shipping zone in General Profile shipping
#    And creat profile with name "General Profile" then create zone
#      | Zone name | Countries              |
#      | EU        | Canada, United Kingdom |
#    And add rates for "EU" zone
#      | Rate name         | Price | Additional condition      |
#      | Standard shipping | 8$    | Based on item quantity:8$ |
#    And click Save changes button

  Scenario: Config shipping rules for Custom profile
    And delete all Custom Profile
    And creat profile with name "setting_EU_Product-setting" then create zone
      | Zone name | Countries  |
      | EU        | Canada     |
    And add rates for "EU" zone
      | Rate name         | Price | Additional condition         |
      | Standard shipping | 8.99$ | Based on item quantity:7.99$ |
    And add product rules for Custom Profile is "Product-setting"

    And click Save changes button

    And user navigate to "Products>All products" screen
    And edit information of product on dashboard "Product for setting"
      | Edit item | New Value                 |
      | SKU       | PB-Product-setting-012345 |

    And open shop on storefront
    And add products "Product for setting>2" to cart then navigate to checkout page
    And input Customer information
      | Email                      | First Name | Last Name | Address     | City       | State | Country | Zip code   | Phone      |
      | @mailinator.girl-viet.com@ | David      | Murphy    | 201 Wood St | Whitehorse | Yukon | Canada  | ON K1P 5R7 | 2056289809 |
    Then verify shipping base on Profile rules
      | Base rules        | Price  |
      | Standard shipping | $16.98 |
    ## shipping rate = 8.99(first item) + 7.99 (additional item)







