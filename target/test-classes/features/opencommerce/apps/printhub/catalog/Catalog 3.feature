Feature: Catalog screen Print Base
#  enviromment stag: print_hub_catalog
#  shop: au-ph-catalog.stag.myshopbase.net
#  user.name: shopbase@beeketing.net
#  user.pwd:123456
  Scenario Outline: Check show price with shipping of Campaign on Print Base
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And  select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
    And switch to "Apparel" tab
    And verify catalog screen
      | Show price with shipping | Shipping address | Shipping cost |
      | <isShipping>             | <address>        | <Price>       |
    And close browser
    Examples:
      | isShipping | address       | Price | Testcase                                         |
      | false      |               | 0     | Don't select show price with shipping            |
      | true       | US            | 4.99  | Verify show price with shipping is US            |
      | true       | International | 8.99  | Verify show price with shipping is International |

  Scenario: Verify download template success
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And  select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
    When download template campaign as "01"
      | KEY | Title          |
      | 01  | Kid Sweatshirt |
    Then verify download template success
    And close browser