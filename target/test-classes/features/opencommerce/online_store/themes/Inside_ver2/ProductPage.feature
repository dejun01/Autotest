Feature: Product section theme Inside v2

  #Env insidev2_ProductPage

  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme


  Scenario Outline: Remove block #SB_OLS_THE_ISN_400
    When remove block in "Product" section "<KEY>"
      | KEY | Block name          |
      | 1   | Time countdown      |
      | 1   | Product countdown   |
      | 1   | Quantity discount   |
      | 1   | Bundles             |
      | 1   | Accessories         |
      | 1   | Collections         |
      | 1   | Social Share        |
      | 1   | Product description |
      | 1   | Collapse tab        |
      | 1   | Text                |
      | 1   | Product types       |
      | 1   | Product tags        |
      | 1   | Vendor              |
      | 1   | Product SKU         |
    And save change setting
    Examples:
      | KEY |
      | 1   |


  Scenario: Add block #SB_OLS_THE_ISN_400
    When add block in "Product" section
      | Name block          |
      | Time countdown      |
      | Product countdown   |
      | Quantity discount   |
      | Bundles             |
      | Accessories         |
      | Collections         |
      | Social Share        |
      | Product description |
      | Collapse tab        |
      | Text                |
      | Product types       |
      | Product tags        |
      | Vendor              |
      | Product SKU         |
    And save change setting


  Scenario Outline: Verify blocks: Product setting, Price, Variant selector, Buy buttons #SB_OLS_THE_ISN_399 #SB_OLS_THE_ISN_398 #SB_OLS_THE_ISN_397 #SB_OLS_THE_ISN_396 #SB_OLS_THE_ISN_395 #SB_OLS_THE_ISN_394 #SB_OLS_THE_ISN_393 #SB_OLS_THE_ISN_392
    And setting section Product "<KEY>"
      | KEY | Setting by                 | Description position     | Sticky button desktop | Breadcrumb |
      | 1   | Customize                  | Description on the right | true                  | true       |
      | 2   | Customize                  | Description on the right | false                 | false      |
      | 3   | Settings by theme settings |                          | false                 | true       |
    And setting block "Price" in Product section "<KEY>"
      | KEY | Setting by                 | Sale type  | Price savings |
      | 1   | Customize                  | Amount     | true          |
      | 2   | Customize                  | Percentage | false         |
      | 3   | Settings by theme settings |            |               |
    And setting block "Variant Selector" in Product section "<KEY>"
      | KEY | Setting by                 | Variant style | Hide selector when option has one value | Link available combination | Variant group swatches | Color swatches |
      | 1   | Customize                  | Buttons       | true                                    | true                       | true                   | false          |
      | 2   | Customize                  | Dropdown      | true                                    | true                       | true                   | true           |
      | 3   | Settings by theme settings |               |                                         |                            |                        |                |
    And setting block "Buy Buttons" in Product section "<KEY>"
      | KEY | Setting by                 | Quantity selector | Add to card button | Quantity & Add to card is same line | Dynamic checkout button | Buy with Paypal |
      | 1   | Customize                  | true              | true               | true                                | true                    | true            |
      | 2   | Customize                  | false             | false              | false                               | false                   | false           |
      | 3   | Settings by theme settings |                   |                    |                                     |                         |                 |
    Then setting Product in Theme Settings "<KEY>"
      | KEY | Description position           | Sale type | Price savings | Variant style | Hide selector when option has one value | Link available combination | Variant group swatches | Color swatches | Quantity selector | Add to card button | Quantity & Add to card is same line | Dynamic checkout button | Buy with Paypal |
      | 3   | Description below product form | Amount    | true          | Dropdown      | false                                   | false                      | false                  | false          | true              | true               | false                               | false                   | true            |
    And save change setting

    Given open page "/products/product-1"
    And verify Product setting as "<KEY>"
      | KEY | Description Position           | Sticky button desktop | Breadcrumb |
      | 1   | Description on the right       | true                  | true       |
      | 2   | Description on the right       | false                 | false      |
      | 3   | Description below product form | false                 | true       |
    And verify "Price" block as "<KEY>"
      | KEY | Sale type  | Price savings |
      | 1   | Amount     | true          |
      | 2   | Percentage | false         |
      | 3   | Amount     | true          |
    And verify "Variant Selector" block as "<KEY>"
      | KEY | Variant style | Hide selector when option has one value | Variant group swatches | Color swatches |
      | 1   | Buttons       | true                                    | true                   | false          |
      | 2   | Dropdown      | true                                    | true                   | true           |
      | 3   | Dropdown      | false                                   | false                  | false          |
    And verify "Buy Buttons" block as "<KEY>"
      | KEY | Quantity selector | Add to card button | Quantity & Add to card is same line | Dynamic checkout button | Buy with Paypal |
      | 1   | true              | true               | true                                | true                    | true            |
      | 2   | false             | false              | false                               | false                   | false           |
      | 3   | true              | true               | false                               | false                   | true            |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |