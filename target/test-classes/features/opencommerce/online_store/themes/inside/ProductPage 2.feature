Feature: Product page theme inside
#  staging_sbase_inside_product_page
#  prod_sbase_inside_product_page
#  dev_sbase_inside_product_page

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"

  Scenario Outline: Verify Feature product and Product page
    And change Feature product settings as "<KEY>"
      | KEY | Product name | Product image alignment | Show full product description |
      | 1   | B1          | Left                    | false                         |
      | 2   | B1          | Right                   | true                          |
      | 3   | B1          | Right                   | true                          |
    And change Product page settings as "<KEY>"
      | KEY | Enable product breadcrumb | Show vendor | Show SKU | Show collections | Show types | Show tags | Show social media share icons | Show Sticky button |
      | 1   | false                     | false       | false    | false            | false      | false     | false                         | false              |
      | 2   | true                      | true        | true     | true             | true       | true      | true                          | true               |
      | 3   | true                      | true        | true     | true             | true       | true      | true                          | true               |
    And change Product grid setting as "<KEY>"
      | KEY | Show price savings | Sale type  | Options style | Enable variant group swatches | Enable color swatches | Show quantity box | Show quantity box and Add to cart button | Show quantity box in the same line | Description position           | Show trust badge image |
      | 1   | false              | Amount     | Dropdown      | false                         | false                 | false             | true                                     | false                              | Description in the right       | true                   |
      | 2   | true               | Percentage | Buttons       | false                         | false                 | true              | true                                     | false                              | Description below product form | false                  |
      | 3   | true               | Percentage | Buttons       | true                          | true                  | true              | false                                    | true                               |                                | true                   |
    Then open shop on storefront
    And verify Feature product settings as "<KEY>"
      | KEY | Product name | Image position | Show full product description | Show price savings | Sale type  | Options style | Enable variant group swatches | Enable color swatches | Show quantity box | Show quantity box and Add to cart button | Show quantity box in the same line | Description position           | Show trust badge image |
      | 1   | B1          | Right          | false                         | false              | Amount     | Dropdown      | false                         | false                 | false             | true                                     | false                              | Description in the right       | true                   |
      | 2   | B1          | Right          | true                          | true               | Percentage | Buttons       | false                         | false                 | true              | true                                     | false                              | Description below product form | false                  |
      | 3   | B1         | Right          | true                          | true               | Percentage | Buttons       | true                          | true                  | true              | false                                    | true                               |                                | true                   |
    And verify product page setting as "<KEY>"
      | KEY | Product | Enable product breadcrumb | Show vendor | Show SKU | Show collections | Show types | Show tags | Show social media share icons | Show Sticky button | Show price savings | Sale type  | Options style | Enable variant group swatches | Enable color swatches | Show quantity box | Show quantity box and Add to cart button | Show quantity box in the same line | Tab position                   | Show trust badge image |
      | 1   | B1     | false                     | false       | false    | false            | false      | false     | false                         | false              | false              | Amount     | Dropdown      | false                         | false                 | false             | true                                     | false                              | Description in the right       | true                   |
      | 2   | B1     | true                      | true        | true     | true             | true       | true      | true                          | true               | true               | Percentage | Buttons       | false                         | false                 | true              | true                                     | false                              | Description below product form | false                  |
      | 3   | B1     | true                      | true        | true     | true             | true       | true      | true                          | true               | true               | Percentage | Buttons       | true                          | true                  | true              | false                                    | true                               |                                | true                   |
   And quit browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |