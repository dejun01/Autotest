Feature: Catalog screen Print Base
#  print_on_demand_catalog
  Scenario Outline: Check show price with shipping of Campaign on Print Base
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    And switch to "Apparel" tab
    And verify catalog screen
      | Product   | Show price with shipping | Shipping address | Shipping cost |
      | <sProduct>| <isShipping>             | <address>        | <Price>       |
    And close browser
    Examples:
      |sProduct      | isShipping | address       | Price | Testcase                                         |
      |Unisex T-shirt| false      |               | 0     | Don't select show price with shipping            |
      |Unisex T-shirt| true       | US            | 5.49  | Verify show price with shipping is US            |
      |Unisex T-shirt| true       | International | 8.99  | Verify show price with shipping is International |


  Scenario: Verify download template success
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When download template campaign as "01"
      | KEY | Title          |
      | 01  | Kid Sweatshirt |
    Then verify download template success
    And close browser


