@dashboardProduct @dashboard
Feature: Product Feed

  Scenario: Verify Google Merchant Method setting
    Given user login to shopbase dashboard
    When user navigate to "Products>Product feeds" screen
    And add a new product feed with data
      | Feed name            | All products or just some of them? | Collection name      | All variations or just some of them?     | Condition | Key word | Product title                                    | Product title preference | Product description preference | Submit products as custom products | Default brand name |
      | Collection check GMC | Products from selected collections | Collection test feed | Export only first variation of a product |           |          | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
    When user navigate to "Products>Product feeds" screen
    When setting GMC for product feed
      | Feed name    | Is enable GMC |
      | All products | true          |
    Then verify GMC method of all feed after setting
      | Feed name            | Is enable GMC |
      | Collection check GMC | false         |
    When setting GMC for product feed
      | Feed name    | Is enable GMC |
      | All products | false         |
    Then verify GMC method of all feed after setting
      | Feed name            | Is enable GMC |
      | Collection check GMC | true          |

  Scenario: Verify product feed display exactly
    Given user login to shopbase dashboard
    When user navigate to "Products>Product feeds" screen
    And add a new product feed with data
      | Feed name                                                                  | All products or just some of them? | Collection name      | All variations or just some of them?                                       | Condition   | Key word    | Product title                                    | Product title preference | Product description preference | Submit products as custom products | Default brand name |
      | Exclude SKU contains NM                                                    | Products from selected collections | Collection test feed | Export all variations of a product                                         | contains    | NM          | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | Exclude SKU starts with SB                                                 | Products from selected collections | Collection test feed | Export all variations of a product                                         | starts with | SB          | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | Exclude SKU ends with BS                                                   | Products from selected collections | Collection test feed | Export all variations of a product                                         | ends with   | BS          | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | Exclude SKU equal TB0704HNMBS                                              | Products from selected collections | Collection test feed | Export all variations of a product                                         | is equal to | TB0704HNMBS | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | Export only first variation of a product                                   | Products from selected collections | Collection test feed | Export only first variation of a product                                   |             |             | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | Export only first variation of each value in the first option of a product | Products from selected collections | Collection test feed | Export only first variation of each value in the first option of a product |             |             | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | SKU contains 704HNS                                                        | Products from selected collections | Collection test feed | Export only variations of a product matching                               | contains    | 704HNS      | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | SKU starts with TB                                                         | Products from selected collections | Collection test feed | Export only variations of a product matching                               | starts with | TB          | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | SKU ends with BH                                                           | Products from selected collections | Collection test feed | Export only variations of a product matching                               | ends with   | BH          | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
      | SKU equal TB0704HNMBH                                                      | Products from selected collections | Collection test feed | Export only variations of a product matching                               | is equal to | TB0704HNMBH | Do not add variant title at the end of the title | Default product title        | Default product description        | No                                 | SamSam             |
    When user navigate to "Products>Product feeds" screen
    Then verify file product feed display exactly
      | Feed name                                                                  | Expected   |
      | Exclude SKU contains NM                                                    | feed1.csv  |
      | Exclude SKU starts with SB                                                 | feed2.csv  |
      | Exclude SKU ends with BS                                                   | feed3.csv  |
      | Exclude SKU equal TB0704HNMBS                                              | feed4.csv  |
      | Export only first variation of a product                                   | feed5.csv  |
      | Export only first variation of each value in the first option of a product | feed6.csv  |
      | SKU contains 704HNS                                                        | feed7.csv  |
      | SKU starts with TB                                                         | feed8.csv  |
      | SKU ends with BH                                                           | feed9.csv  |
      | SKU equal TB0704HNMBH                                                      | feed10.csv |


  Scenario: Verify product feed display exactly on production
    Given user login to shopbase dashboard
    When user navigate to "Products>Product feeds" screen
    And add a new product feed with data
      | Feed name                                                                  | All products or just some of them? | Collection name      | All variations or just some of them?                                       | Condition   | Key word    | Product title                                    | Product title preference | Product description preference | Submit products as custom products | Default brand name |
      | Exclude SKU contains NM                                                    | Products from selected collections | Collection test feed | Export all variations of a product                                         | contains    | NM          | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | Exclude SKU starts with SB                                                 | Products from selected collections | Collection test feed | Export all variations of a product                                         | starts with | SB          | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | Exclude SKU ends with BS                                                   | Products from selected collections | Collection test feed | Export all variations of a product                                         | ends with   | BS          | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | Exclude SKU equal TB0704HNMBS                                              | Products from selected collections | Collection test feed | Export all variations of a product                                         | is equal to | TB0704HNMBS | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | Export only first variation of a product                                   | Products from selected collections | Collection test feed | Export only first variation of a product                                   |             |             | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | Export only first variation of each value in the first option of a product | Products from selected collections | Collection test feed | Export only first variation of each value in the first option of a product |             |             | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | SKU contains 704HNS                                                        | Products from selected collections | Collection test feed | Export only variations of a product matching                               | contains    | 704HNS      | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | SKU starts with TB                                                         | Products from selected collections | Collection test feed | Export only variations of a product matching                               | starts with | TB          | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | SKU ends with BH                                                           | Products from selected collections | Collection test feed | Export only variations of a product matching                               | ends with   | BH          | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
      | SKU equal TB0704HNMBH                                                      | Products from selected collections | Collection test feed | Export only variations of a product matching                               | is equal to | TB0704HNMBH | Do not add variant title at the end of the title | SEO product title        | SEO product description        | No                                 | SamSam             |
    When user navigate to "Products>Product feeds" screen
    Then verify file product feed display exactly
      | Feed name                                                                  | Expected       |
      | Exclude SKU contains NM                                                    | feed1prod.csv  |
      | Exclude SKU starts with SB                                                 | feed2prod.csv  |
      | Exclude SKU ends with BS                                                   | feed3prod.csv  |
      | Exclude SKU equal TB0704HNMBS                                              | feed4prod.csv  |
      | Export only first variation of a product                                   | feed5prod.csv  |
      | Export only first variation of each value in the first option of a product | feed6prod.csv  |
      | SKU contains 704HNS                                                        | feed7prod.csv  |
      | SKU starts with TB                                                         | feed8prod.csv  |
      | SKU ends with BH                                                           | feed9prod.csv  |
      | SKU equal TB0704HNMBH                                                      | feed10prod.csv |


  Scenario: Delete all product feed
    Given user login to shopbase dashboard
    When user navigate to "Products>Product feeds" screen
    When delete all product feed





