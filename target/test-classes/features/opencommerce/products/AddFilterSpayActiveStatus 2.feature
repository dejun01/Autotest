Feature: Add filter SPAY activating status

  Scenario: Check shop active SPAY
    Given user login to shopbase dashboard by API
    Then user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And click to button "Switch to ShopBase Payments"
    And user navigate to "Products>All products" screen
    And Delete all products
    And Add a new product with data
      | Title      |
      | product 01 |
      | product 02 |
      | product 03 |
      | product 04 |
      | product 05 |
    And quit browser
    Given login to hive sbase
    And user navigate to "TOS violation review>Search products" on hive page
    And click to Expand filter options in hive sbase
    And input Created form and to in hive
    And choose filter product
      | Platform type | Store ID | Payment status |
      | SMP           | @shopID@ | active         |

    And click to button "Filter"
    And verify "5" products in Search products
    And quit browser

  Scenario: Check shop deactive SPAY
    Given user login to shopbase dashboard by API
    Then user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And open page deactive shopbase payments
    And click to button "Deactivate"
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title      |
      | product 06 |
      | product 07 |
      | product 08 |
    And quit browser
    Given login to hive sbase
    And user navigate to "TOS violation review>Search products" on hive page
    And click to Expand filter options in hive sbase
    And input Created form and to in hive
    And choose filter product
      | Platform type | Store ID | Payment status |
      | SMP           | @shopID@ | deactive       |
    And click to button "Filter"
    And verify "3" products in Search products
    And quit browser



