Feature: Manage reviews

#  Env:
#  staging_sbase_review3
#  prod_sbase_review3
#  dev_sbase_review3
#  prod_sbase_review3_svtest_sfnext

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


  Scenario: Delete all reviews #SB_SF_RV_13
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "All reviews" screen
    When user delete all reviews
    Then verify deleted all reviews
    And open page "/pages/all-reviews"
    And verify review deleted on SF

  Scenario: Import reviews from CSV file
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Imports" screen
    And import reviews from a spreadsheet into Product Reviews with cvs file is "Review.csv"
    And verify review import on manage reviews in cvs file is "Review.csv"

  Scenario Outline: Filter reviews on dashboard #SB_SF_RV_6 #SB_SF_RV_8
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    When user navigate to "All reviews" screen
    And choose filter reviews in dashoard as "<KEY>"
      | KEY | Option      | value                    |
      | 1   | Status      | Publish                  |
      | 2   | Status      | Unpublished              |
      | 2   | Rating      | 1                        |
      | 2   | Photo       | With photos              |
      | 3   | Rating      | 1                        |
      | 3   | Featured    | Not featured             |
      | 3   | Photo       | Without photos           |
      | 3   | Review type | Product review           |
      | 4   | Rating      | 5                        |
      | 4   | Review type | Store review             |
      | 4   | Source      | Import                   |
      | 5   | Rating      | 3                        |
      | 5   | Product     | Outdoor Waterproof Socks |
    And click done button
    Then verify list review after filter as "<KEY>"
      | KEY | Review show                                                                                                                                                                                         | Review don't show                                                                                                                                                                                   |
      | 1   | Review 1 test,Review 2,Review 3,Review 4,Review 5,Review 6,Review 7,Review 8,Review 9,Review 10,Review 11,Review 12,Review 13,Review 14,Review 15,Review 16,Review 17,Review 18,Review 19,Review 20 |                                                                                                                                                                                                     |
      | 2   |                                                                                                                                                                                                     | Review 1 test,Review 2,Review 3,Review 4,Review 5,Review 6,Review 7,Review 8,Review 9,Review 10,Review 11,Review 12,Review 13,Review 14,Review 15,Review 16,Review 17,Review 18,Review 19,Review 20 |
      | 3   | Review 14                                                                                                                                                                                           | Review 1 test,Review 2,Review 3,Review 4,Review 5,Review 6,Review 7,Review 8,Review 9,Review 10,Review 11,Review 12,Review 13,Review 15,Review 16,Review 17,Review 18,Review 19,Review 20           |
      | 4   | Review 20                                                                                                                                                                                           | Review 1 test,Review 2,Review 3,Review 4,Review 5,Review 6,Review 7,Review 8,Review 9,Review 10,Review 11,Review 12,Review 13,Review 14,Review 15,Review 16,Review 17,Review 18,Review 19           |
      | 5   | Review 9                                                                                                                                                                                            | Review 1 test,Review 2,Review 3,Review 4,Review 5,Review 6,Review 7,Review 8,Review 10,Review 11,Review 12,Review 13,Review 14,Review 15,Review 16,Review 17,Review 18,Review 19,Review 20          |
    Examples:
      | KEY | Description                                                  |
      | 1   | Filter review with status                                    |
      | 2   | Filter review with (status & Rating & Photo)                 |
      | 3   | Filter review with (Rating & Featured & Photo & Review type) |
      | 4   | Filter review with (Rating & Review type & Source)           |
      | 5   | Filter review with (Rating & Product)                        |

  Scenario Outline: Action to reviews #SB_SF_RV_9 #SB_SF_RV_10 #SB_SF_RV_11 #SB_SF_RV_12
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    When user navigate to "All reviews" screen
    And action to reviews as "<KEY>"
      | KEY | Review        | Action            |
      | 1   | Review 1 test | Unpublish         |
      | 2   | Review 1 test | Publish           |
      | 3   | Review 1 test | Set as Featured   |
      | 4   | Review 1 test | Unset as Featured |
      | 5   | Review 4      | Delete            |
    Then open shop on storefront
    Then verify review on product page as "<KEY>"
      | KEY | Product                                     | Review        | Show review | Featured | Type            |
      | 1   | Outdoor Waterproof Socks                    | Review 1 test | false       | false    | Product reviews |
      | 2   | Outdoor Waterproof Socks                    | Review 1 test | true        | false    | Product reviews |
      | 3   | Outdoor Waterproof Socks                    | Review 1 test | true        | true     | Product reviews |
      | 4   | Outdoor Waterproof Socks                    | Review 1 test | true        | False    | Product reviews |
      | 5   | MC Two Piece Floral Print Ruffle Bikini Set | Review 4      | false       | false    | Product reviews |
    Examples:
      | KEY | Description                  |
      | 1   | Unpublish review             |
      | 2   | Publish review               |
      | 3   | Set as Featured for review   |
      | 4   | Unset as Featured for review |
      | 5   | Delete a review              |
