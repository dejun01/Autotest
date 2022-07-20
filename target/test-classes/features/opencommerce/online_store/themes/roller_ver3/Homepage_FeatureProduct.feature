Feature: Homepage - Feature Product

#  sbase_roller_ver3
#  Env:
#  rollerver3_featureproduct
#  prod_rollerver3_featureproduct
#  prodtest_rollerver3_featureproduct
#  staging_rollerver3_featureproduct

## product:   Product test

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove and Add section Logo
    When remove all section "Featured Product"
    And verify show "Featured Product" section on storefront is "false"
    When add section "Featured Product"
    And save change setting

  Scenario Outline: Remove block
    When remove block in "Featured Product" section "<KEY>"
      | KEY | Block name          |
      | 1   | Product Description |
      | 1   | Additional Tab      |
      | 1   | Page                |
    And save change setting
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Featured Product" section
      | Block name          |
      | Product Description |
      | Additional Tab      |
      | Page                |
    And save change setting

  Scenario Outline: Verify Feature Product
    When setting section Featured Product "<KEY>"
      | KEY | Product                 | Show arrows on product gallery | Enable product gallery slideshow | Gallery transition | Image position | Show vendor | Show price savings | Show product content | Description position     |
      | 1   | Cairbull Cycling Helmet | false                          | false                            | Fade               | Left           | false       | false              | false                | Description on the right |
      | 2   | Cairbull Cycling Helmet | true                           | true                             | Slide              | Right          | true        | true               | true                 | Description below        |
    And setting block "Product Description" in "Featured Product" section "<KEY>"
      | KEY | Heading     | Collapse this tab by default |
      | 1   |             | true                         |
      | 2   | Description | false                        |
    And  setting block "Additional Tab" in "Featured Product" section "<KEY>"
      | KEY | Heading           | Text                                                               | Collapse this tab by default |
      | 1   |                   | If you're not 100% satisfied, let us know and we'll make it right. | true                         |
      | 2   | Return & Warranty |                                                                    | false                        |
    And  setting block "Page" in "Featured Product" section "<KEY>"
      | KEY | Content page | Collapse this tab by default |
      | 1   | FAQs         | true                         |
      | 2   | Contact Us   | false                        |
    And save change setting
    Then open shop on storefront
    When verify Featured Product section on storefront "<KEY>"
      | KEY | Product name            | Show arrows on product gallery | Enable product gallery slideshow | Gallery transition | Image position | Show vendor | Show price savings | Show product content | Tab position |
      | 1   | Cairbull Cycling Helmet | false                          | false                            | Fade               | Left           | false       | false              | false                | Right        |
      | 2   | Cairbull Cycling Helmet | true                           | true                             | Slide              | Right          | true        | true               | true                 | Below        |
    Examples:
      | KEY |
      | 1   |
      | 2   |




