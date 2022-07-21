@presetShopTemplatePrintBase
Feature: Preset shop template print base
#prod_pbase_createshop
  @Createshop
  Scenario Outline: Create shop and verify all campaign
    When login to shopbase dashboard
    Then verify message "Please enter a store name" if store name is empty
    And create a shop with name "@shop-printbase-@"
    Given Input information merchant
      | First name | Last name | Store country | Your personal location | Phone number | Business           | Print base |
      | linda      | ms        | Vietnam       | Vietnam                | 0984533888   | shopbase@beeketing | true       |
    And user navigate to "Campaigns>All campaigns" screen
    And click to button "Use Sample Campaigns"
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name                    | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | PrintBase multiple 2D products 6 | LIVE   | true                | true                     |
      | 1   | PrintBase multiple 2D products 5 | LIVE   | true                | true                     |
      | 1   | PrintBase multiple 2D products 4 | LIVE   | true                | true                     |
      | 1   | PrintBase multiple 2D products 3 | LIVE   | true                | true                     |
      | 1   | PrintBase multiple 2D products 2 | LIVE   | true                | true                     |
      | 1   | PrintBase multiple 2D products 1 | LIVE   | true                | true                     |
      | 1   | PrintBase Blanket B106           | LIVE   | true                | true                     |
      | 1   | PrintBase Blanket B105           | LIVE   | true                | true                     |
      | 1   | PrintBase Blanket B104           | LIVE   | true                | true                     |
      | 1   | PrintBase Blanket B103           | LIVE   | true                | true                     |
      | 1   | PrintBase Blanket B102           | LIVE   | true                | true                     |
      | 1   | PrintBase Blanket B101           | LIVE   | true                | true                     |
      | 1   | PrintBase Quilt Q106             | LIVE   | true                | true                     |
      | 1   | PrintBase Quilt Q105             | LIVE   | true                | true                     |
      | 1   | PrintBase Quilt Q104             | LIVE   | true                | true                     |
      | 1   | PrintBase Quilt Q103             | LIVE   | true                | true                     |
      | 1   | PrintBase Quilt Q102             | LIVE   | true                | true                     |
      | 1   | PrintBase Quilt Q101             | LIVE   | true                | true                     |
    And quit browser
    Examples:
      | KEY |
      | 1   |

