Feature: Clone Campaign picture choice
 #clone_campaign_clipart
  Background: Delete all product in des shop
    Given user login to secondShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    Then Delete all campaigns
    And quit browser

  Scenario: Clone campaign picture choice with clipart #SB_PRB_CPA_73
    Given  user login to firstShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign "Campaign clone clipart" in dashboard
    And select "1" products
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser
    And user login to secondShop dashboard by API
    And user navigate to "Library>Cliparts" screen
    And open folder clipart detail as "<KEY>"
      | KEY | Folder   |
      | 01  | Clipart1 |
    Then verify information in clipart detail as "<KEY>"
      | KEY | Folder   | Image                      |
      | 01  | Clipart1 | BD_1,BD_2.1,BD_3,BD_4,BD_5 |
    Then verify infomation clipart folder as "<KEY>"
      | KEY | Name     | Number image |
      | 01  | Clipart1 | 5            |
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "Campaign clone clipart"
    And open product details on Storefront from product detail in dashboard
    Then verify information picture choice display as checklist on SF as "<KEY>"
      | KEY | Folder   | Image1 | Image2 | Image3 | Image4 | Image5 |
      | 01  | Clipart1 | BD_1   | BD_2.1 | BD_3   | BD_4   | BD_5   |
    And quit browser


#  Scenario: Clone campaign with media #SB_PRB_CPA_64
#    Given  user login to firstShop dashboard by API
#    And user navigate to "Campaigns>All campaigns" screen
#    And  search campaign "Campaign Media" in dashboard
#    And select "1" products
#    When Import campaign to another shop
#      | Action             |
#      | Keep both products |
#    And quit browser
#    And user login to secondShop dashboard by API
#    And user navigate to "Campaigns" screen
#    And  open product details in dashboard or editor campaign "Campaign Media"
#    And  get list and total image to screen detail
#    Then Verify media product on store front
#    And quit browser

  Scenario: Check clone campaign personalize from source shop to another shop on SF #SB_PRO_CP_123
    Given  user login to firstShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And  search campaign "Campaign personalize" in dashboard
    And select "1" products
    And Import campaign to another shop
      | Action             |
      | Keep both products |
    And quit browser
    And user login to secondShop dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "Campaign personalize"
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "01"
      | KEY | Type           | Custom name    | Value Input | Crop Image |
      | 01  | Text           | Custom text    | Test text   |            |
      | 01  | Picture choice | Picture choice | BD_5        |            |
    And verify show button Preview your design on store front "true"
    And quit browser

