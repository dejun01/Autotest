Feature: Settings - Product setting

#  sbase_roller_ver3
#  Env:
#  prod_sbase_rollerver3_productsetting
#  prodtest_sbase_rollerver3_productsetting
#  staging_sbase_rollerver3_productsetting

## product:   Product test

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme

  Scenario Outline: Verify Product Setting
    When change product setting "<KEY>"
      | KEY | Show price savings | Options style        | Enable variant group swatches | Enable color swatches | Hide selector when option has one value | Show quantity box | Show quantity box in the same line | Link product options                                                        | Select variants by clicking their images | Show trust badge image | Show sale banners |
      | 1   | Saving price       | Select dropdown list | false                         | true                  | false                                   | false             | true                               | Link product options, only show available combination                       | false                                    | false                  | true              |
      | 2   | Saving percentage  | Buttons              | true                          | false                 | true                                    | true              | false                              | Make all choices in all drop-downs available, even out of stock combination | true                                     | true                   | false             |
    And save change setting
    Then open shop on storefront
    When verify product setting on storefront "<KEY>"
      | KEY | Show price savings | Options style        | Enable variant group swatches | Enable color swatches | Hide selector when option has one value | Show quantity box | Show quantity box in the same line | Link product options                                                        | Select variants by clicking their images | Show trust badge image | Show sale banners |
      | 1   | Saving price       | Select dropdown list | false                         | true                  | false                                   | false             | true                               | Link product options, only show available combination                       | false                                    | false                  | true              |
      | 2   | Saving percentage  | Buttons              | true                          | false                 | true                                    | true              | false                              | Make all choices in all drop-downs available, even out of stock combination | true                                     | true                   | false             |
    Examples:
      | KEY |
      | 1   |
      | 2   |




