Feature: Share review printbase

# Env:
#  prod_pbase_import_shared_reviews
#  staging_pbase_import_shared_reviews
#  dev_pbase_import_shared_reviews

#  Theme: Inside, Roller
#  Products: PrintBase multiple 2D products 6
#  Setting: uncheck show sticky button in theme editor


  Scenario: Delete all reviews
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    Then user delete all reviews
    And verify deleted all reviews


  Scenario Outline: verify import shared reviews #SB_SF_RV_38 #SB_SF_RV_38 #SB_SF_RV_PRIPA_202 #SB_SF_RV_PRIPA_201
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Imports" screen
    Then click on Import reviews on "PrintBase shared reviews" block
    And import shared reviews "<KEY>"
      | KEY | Condition                        | Number of shared reviews | Message                                              |
      | 001 | higher than and equal to 3 stars |                          | Please input integer number                          |
      | 001 |                                  | test                     | Please input integer number                          |
      | 001 |                                  | -1                       | Number of reviews must be greater than or equal to 0 |
      | 001 |                                  | 1.5                      | Please input integer number                          |
      | 001 |                                  | 10                       |                                                      |
      | 002 | higher than and equal to 2 stars | 5                        |                                                      |
    And user navigate to "All reviews" screen
    Then verify shared reviews on All reviews page dashboard "<KEY>"
      | KEY | Review type  | Min number of star | Number of reviews by review type | Number of reviews by status | Status    |
      | 001 | Store review | 3                  | 10                               | 10                          | Published |
      | 002 | Store review | 2                  | 15                               | 15                          | Published |
    Given open shop on storefront
    Then search and select the product "PrintBase multiple 2D products 6"
    And verify shared review on SF "<KEY>"
      | KEY | Number of reviews | Min number of star | Reviews Type |
      | 001 | 10                | 3                  | Store review |
      | 002 | 15                | 2                  | Store review |
    Then close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |


  Scenario Outline: Edit shared reviews
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    When user navigate to "All reviews" screen
    Then select the first review on list
    And edit reviews as "<KEY>"
      | KEY | Name            | Rating | Title                 | Body                            | Review type    | Product                          |
      | 001 | Review 1 edited | 1      | Title review 1 edited | Very good                       | Product review | PrintBase multiple 2D products 6 |
      | 002 | Review 1        | 2      | Title review 1        | Very good,Great product quality | Store review   |                                  |
    Then open shop on storefront
    And search and select the product "PrintBase multiple 2D products 6"
    And verify change review on Product page as "<KEY>"
      | KEY | Review type    | Name            | Rating | Title                 | Body                            |
      | 001 | Product review | Review 1 edited | 1      | Title review 1 edited | Very good                       |
      | 002 | Store review   | Review 1        | 2      | Title review 1        | Very good,Great product quality |
    Then close browser
    Examples:
      | KEY | Description                           |
      | 001 | Change store review to product review |
      | 002 | Edit content of review                |
