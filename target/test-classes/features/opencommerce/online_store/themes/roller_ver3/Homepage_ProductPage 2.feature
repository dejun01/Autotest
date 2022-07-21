Feature: Homepage - Product page

#  sbase_roller_ver3
#  Env:
#  sbase_rollerver3_productpage
#  prod_sbase_rollerver3_productpage
#  prodtest_sbase_rollerver3_productpage
#  staging_sbase_rollerver3_productpage

## product:   Product test

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme
    And open preview page "Product"

  Scenario Outline: Remove block
    When remove block in "Product" section "<KEY>"
      | KEY | Block name          |
      | 1   | Product Description |
      | 1   | Additional Tab      |
      | 1   | Page                |
    And save change setting
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Product" section
      | Block name          |
      | Product Description |
      | Additional Tab      |
      | Page                |
    And save change setting

  Scenario Outline: Verify Product page
    When setting Product page "<KEY>"
      | KEY | Magnify product images by hovering on desktop | Enable product gallery popup | Show breadcrumb link | Show vendor | Show SKU | Show price savings | Show collections | Show types | Show tags | Show social media share icons | Show Sticky button | Display on   | Description position     |
      | 1   | true                                          | false                        | true                 | false       | true     | false              | true             | false      | true      | false                         | true               | All          | Description on the right |
      | 2   | false                                         | true                         | false                | true        | false    | true               | false            | true       |           | true                          | false              | Mobile Phone | Description below        |
    And setting block "Product Description" in "Product" section "<KEY>"
      | KEY | Heading     | Collapse this tab by default |
      | 1   |             | true                         |
      | 2   | Description | false                        |
    And setting block "Additional Tab" in "Product" section "<KEY>"
      | KEY | Heading           | Text                                                               | Collapse this tab by default |
      | 1   |                   | If you're not 100% satisfied, let us know and we'll make it right. | true                         |
      | 2   | Return & Warranty |  | false                        |
    And setting block "Page" in "Product" section "<KEY>"
      | KEY | Content page | Collapse this tab by default |
      | 1   | FAQs         | true                         |
      | 2   | Contact Us   | false                        |
    And save change setting
    Given open page "/products/product-test?sbase_debug=1"
    When verify Product page "<KEY>"
      | KEY | Magnify product images by hovering on desktop | Enable product gallery popup | Show breadcrumb link | Show vendor | Show SKU | Show price savings | Show collections | Show types | Show tags | Show social media share icons | Show Sticky button | Display on | Description position |
      | 1   | true                                          | false                        | true                 | false       | true     | false              | true             | false      | true      | false                         | true               | true       | true                 |
      | 2   | false                                         | true                         | false                | true        | false    | true               | false            | true       |           | true                          | false              | false      | false                |
    And verify block "Product Description" in Product page "<KEY>"
      | KEY | Description position     | Heading     | Collapse this tab by default |
      | 1   | Description on the right |             | true                         |
      | 2   | Description below        | Description | false                        |
    And verify block "Additional Tab" in Product page "<KEY>"
      | KEY | Description position     | Heading           | Text                                                               | Collapse this tab by default |
      | 1   | Description on the right |                   | If you're not 100% satisfied, let us know and we'll make it right. | true                         |
      | 2   | Description below        | Return & Warranty |                                                                    | false                        |
    Examples:
      | KEY |
      | 1   |
      | 2   |




