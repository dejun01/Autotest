@dashboard @dashboardVariant
Feature: PrintBase - Show specific description for variant

#    Background: Login dashboard
#      Given clear all data
#      Given user login to shopbase dashboard by API

#    Scenario: Show description template when that update in Hive pagep
#      Given login to hive-pbase
#      And go to PBase Product menu
#      Then select product with title "AOP Multi Piece Zip Hoodie"
#      When update description with in Hive page content "This is description"
#      Given user login to shopbase dashboard
#      When user navigate to "Catalog" screen
#      And choose update description item of any product
#      Then verify description template is displayed as description in Hive


#    Scenario: Show specific description on store front
#      Given user login to shopbase dashboard by API
#      When user navigate to "Online Store>Preferences" screen
#      And user setting Product description "Show full description of all variants"
#      When user navigate to "Campaign" screen
#      And search product or campaign or orders "<Campaign name>" at list page in dashboard
#      And open product details in dashboard or editor campaign "<Campaign name>"
#      And open product details on Storefront from product detail in dashboard as "<KEY>"
#        | KEY |
#        | 01  |
#      Then verify product information from pod on storefront as "<KEY>"
#        | KEY | Product name    | Color | Size | Style       | Sale price | Compare at price |
#        | 01  | <Campaign name> | White | M    | Unisex Tank | 30.35      | 40               |
#      Then verify description of variant
#      And Switch to the first tab
#      When user navigate to "Online Store>Preferences" screen
#      And user setting Product description "Show specific description according to the selected variant"
#      When user navigate to "Campaign" screen
#      And search product or campaign or orders "<Campaign name>" at list page in dashboard
#      And open product details in dashboard or editor campaign "<Campaign name>"
#      And open product details on Storefront from product detail in dashboard as "<KEY>"
#        | KEY |
#        | 01  |
#      Then verify description of variant
#      Examples:
#        | KEY | Campaign name        | Description          | Tags        |
#        | 01  | Campaign 3D two side | Campaign 3D two side | ladies,test |

  Scenario: Update SEO description when update base description
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Preferences" screen
    And user setting Product description "Show full description of all variants"
    When user navigate to "Catalog" screen
    And user update base description
      | Base name              | base description                |
      | CUT AND SEW ZIP HOODIE | CUT AND SEW ZIP HOODIE update 1 |
      | BEACH SHORTS           | BEACH SHORTS update 1           |
    When user navigate to "Campaign" screen
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    Then user verify SEO description is displayed exactly