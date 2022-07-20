Feature: More from collections

#  Env: us_pre_purchase



  Scenario Outline: Customize all widget #SB_BUS_PW_8
    Given Description: "<Testcase>"
    Given open page "/admin/apps/boost-upsell/cross-sell/product-widgets"
    And wait page "Product widgets" show
    And open page Customize of widget "<Widget>"
    And reset widget's settings to default as "<KEY>"
      | KEY |
      | 000 |
    And customize widget as "<KEY>"
      | KEY | Header                       | Font header            | Font product name | Font product price         | Number of products | Max product per slide | Is on add to cart button | Call to action text        | Place widget |
      | 001 | More from collection neckale | 26px>bold,italic,right | 13px>bold,right   | 17px>bold,italic,underline | 6                  | 4                     | false                    | 18px>bold,underline,italic |              |
    Then open shop on storefront
    And verify widget's settings as "<KEY>"
      | KEY | Collection name | Place widget                                                     | Widget name                  | Is show | Font header            | Font product name | Font product price         | Number of products | Max product per slide | Is on add to cart button | Call to action text        |
      | 000 | dress           | MC Vintage Elvish Dress,MC White Lace Dress,Simple Cold Dresses  | More from                    |         | 25px>center            | 14px>left         | 16px>left                  | 10                 | 6                     | true                     | 16px                       |
      | 000 |                 | Hats                                                             | More from                    | false   |                        |                   |                            |                    |                       |                          |                            |
      | 001 | neckale         | Real Clover Necklace,Real Dandelion Necklace,Sea Turtle Necklace | More from collection neckale |         | 26px>bold,italic,right | 13px>bold,right   | 17px>bold,italic,underline | 5                  | 4                     | false                    | 18px>bold,underline,italic |
    Examples:
      | KEY | Widget                             | Testcase                               |
      | 000 | Products from the same collections | Verify default of More from collection |
      | 001 | Products from the same collections | Customize More from collection         |
