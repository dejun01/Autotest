Feature: Homepage - Feature product

#  Theme inside v2
#  Env: insidev2_featureproduct


  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove all section
    When remove all section "Featured Product"
    And verify show "Featured Product" section on storefront is "false"


  Scenario: Add section and block
    When add section "Featured Product"
    And add block in "Featured Product" section
      | Block name          |
      | Product description |
      | Text                |
      | Page                |
    Then save change setting


  Scenario Outline: change setting
    Then setting section Featured Product "<KEY>"
      | KEY | Product   | Setting by                 | Description position           | Product photo position |
      | 1   | product 1 | Customize                  | Description on the right       | Left                   |
      | 2   | product 2 | Customize                  | Description below product form | Right                  |
      | 3   | product 2 | Settings by theme settings |                                |                        |
    And setting block "Price" in Featured Product section "<KEY>"
      | KEY | Setting by                 | Sale type  | Show price savings |
      | 1   | Customize                  | Amount     | true               |
      | 2   | Customize                  | Percentage | true               |
      | 3   | Settings by theme settings |            |                    |
    And setting block "Variants selector" in Featured Product section "<KEY>"
      | KEY | Setting by                 | Variant style | Hide selector when option has one value | Only show available combination | Variant group swatches | Color swatches |
      | 1   | Customize                  | Buttons       | true                                    | true                            | fail                   | fail           |
      | 2   | Customize                  | Dropdown      | fail                                    | fail                            | true                   | true           |
      | 3   | Settings by theme settings |               |                                         |                                 |                        |                |
    And setting block "Buy Buttons" in Featured Product section "<KEY>"
      | KEY | Setting by                 | Quantity selector | Add to card button | Quantity & Add to card is same line | Dynamic checkout button | Buy with Paypal |
      | 1   | Customize                  | true              | true               | true                                | true                    | true            |
      | 2   | Customize                  | false             | false              | false                               | false                   | false           |
      | 3   | Settings by theme settings |                   |                    |                                     |                         |                 |
    Then setting Product in Theme Settings "<KEY>"
      | KEY | Description position     | Product photo position | Sale type | Show price savings | Variant style | Hide selector when option has one value | Only show available combination | Variant group swatches | Color swatches |
      | 3   | Description on the right | Left                   | Amount    | true               | Buttons       | true                                    | true                            | fail                   | fail           |
    Then save change setting

    Given open shop on storefront
    Then verify Featured Product section on storefront "<KEY>"
      | KEY | Product name | Price         | isShowPriceSaving | Sale type  | Variant style | Hide selector when option has one value | Only show available combination | Variant group swatches | Color swatches |Setting by                 | Quantity selector | Add to card button | Quantity & Add to card is same line | Dynamic checkout button | Buy with Paypal |
      | 1   | product 1    | $10.00>$20.00 | true              | Amount     | Buttons       | true                                    | true                            | fail                   | fail           |Customize                  | true              | true               | true                                | true                    | true            |
      | 2   | product 2    | $20.00>$50.00 | true              | Percentage | Dropdown      | fail                                    | fail                            | true                   | true           |Customize                  | false             | false              | false                               | false                   | false           |
      | 3   | product 2    | $20.00>$50.00 | true              | Amount     | Buttons       | true                                    | true                            | fail                   | fail           |Settings by theme settings |                   |                    |                                     |                         |                 |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |