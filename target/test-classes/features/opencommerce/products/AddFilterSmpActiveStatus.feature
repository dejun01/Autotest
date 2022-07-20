Feature: Add filter SMP activating status

  Scenario Outline: Verify filter SMP/SPAY status
    Given Description: "<Testcase>"
    Given login to hive sbase
    And user navigate to "TOS violation review>Search products" on hive page
    And click to Expand filter options in hive sbase
    And choose platform type of shop as "<KEY>"
      | KEY | Platform type |
      | 01  | ShopBase      |
      | 01  | PrintBase     |
      | 01  | PlusBase      |
      | 02  | SMP           |
      | 02  | SPAY          |
    Then verify show Payment status as "<KEY>"
      | KEY | Show  |
      | 01  | false |
      | 02  | true  |
    And verify list Payment status as "<KEY>"
      | KEY | Status   |
      | 02  | active   |
      | 02  | deactive |
      | 02  | all      |
    Examples:
      | KEY | Testcase                      |
      | 01  | Ignore filter Spay/SMP status |
      | 02  | Show filter Spay/SMP status   |


  Scenario: Check shop active smp
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

  Scenario: Check shop deactive smp
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



