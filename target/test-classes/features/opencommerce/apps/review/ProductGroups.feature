Feature: Product groups

#  Env:
#  staging_sbase_review_productgroup
#  prod_sbase_review_productgroup
#  dev_sbase_review_productgroup
#  prod_sbase_review_productgroup_svtest_sfnext

#  Theme: Inside, Roller
#  Setting: uncheck show sticky button in theme editor
#  Products:
  #  Outdoor Waterproof Socks: collection "Collection test review"
  #  Nautical Anchor Bracelet: collection "Collection test review"
  #  MC Two Piece Floral Print Ruffle Bikini Set
  #  Silver Plated Cute Cat Ring: tag "silver", "white"
  #  MoonStone Cat Necklace: tag "silver"
  #  Push Up Flower Print High Waist Bikini Set: vendor "shopbase test"
  #  V-neck T-shirt: vendor "shopbase test"
  #  Real Clover Necklace: tag "white"

  Scenario: Delete all product group #SB_SF_RV_37
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Product groups" screen
    When delete all product group
    Then verify groups number equals 0

  Scenario: Add product group #SB_SF_RV_34 #SB_SF_RV_35 #SB_SF_RV_36
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Product groups" screen
    When add product group
      | Title            | Group by   | Value                  | Activate |
      | Group collection | Collection | Collection test review | true     |
      | Group tag        | Tag        | silver                 | true     |
      | Group vendor     | Vendor     | shopbase test          | true     |
    Then open shop on storefront
    Then check review with groups of products
      | Product 1                                  | Product 2                | Review show                                               |
      | Outdoor Waterproof Socks                   | Nautical Anchor Bracelet | Review 7,Review 8,Review 9,Review 17                      |
      | Silver Plated Cute Cat Ring                | MoonStone Cat Necklace   | Review 3,Review 5,Review 13,Review 14,Review 15,Review 16 |
      | Push Up Flower Print High Waist Bikini Set | V-neck T-shirt           | Review 2,Review 10,Review 11,Review 12,Review 18          |

  Scenario: Edit product group
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Product groups" screen
    When edit product group
      | Name group       | Title       | Group by | Value |
      | Group collection | Group tag 2 | Tag      | white |
    Then open shop on storefront
    Then check review with groups of products
      | Product 1                   | Product 2            | Review show                                               |
      | Silver Plated Cute Cat Ring | Real Clover Necklace | Review 3,Review 5,Review 13,Review 14,Review 15,Review 16 |

  Scenario Outline: Action to product group
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Product groups" screen
    When action product group "<KEY>"
      | KEY | Group name  | Action     |
      | 1   | Group tag 2 | Deactivate |
      | 2   | Group tag 2 | Activate   |
    Then open shop on storefront
    Then verify review with product group on storefront "<KEY>"
      | KEY | Product 1                   | Product 2            | Review of product 1                                       | Review show on product page 2 |
      | 1   | Silver Plated Cute Cat Ring | Real Clover Necklace | Review 3,Review 5,Review 13,Review 14,Review 15,Review 16 | false                         |
      | 2   | Silver Plated Cute Cat Ring | Real Clover Necklace | Review 3,Review 5,Review 13,Review 14,Review 15,Review 16 | true                          |
    Examples:
      | KEY | Description              |
      | 1   | Deactivate product group |
      | 2   | Activate product group   |
