Feature: Create new campaign editor
#   pbase_new_campaign_editor_custom
#  Scenario: Delete all product live
#    When delete all campaigns by API

  Scenario Outline: Create new campaign base product
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                | Category    |
      | 01  | Shining Tumbler 20oz   | Drinkware   |
      | 02  | Tracker Bottle         | Drinkware   |
      | 03  | New Tumbler 30oz       | Drinkware   |
      | 04  | Two-sided Mug New      | Drinkware   |
      | 05  | Tumbler 20oz           | Drinkware   |
      | 06  | Skinny Tumbler         | Drinkware   |
      | 07  | iPhone 12 Case         | Phone Cases |
      | 08  | iPhone 12 Pro Case     | Phone Cases |
      | 09  | iPhone 12 Pro Max Case | Phone Cases |
      | 10  | iPhone 11 Case         | Phone Cases |
      | 11  | iPhone 11 Pro Case     | Phone Cases |
      | 12  | iPhone 11 Pro Max Case | Phone Cases |
    And get position base product in editor
    And add layer base product as "<KEY>"
      | KEY | Product                | Layer type | Layer name                 | Front or back |
      | 01  | Shining Tumbler 20oz   | Image      | Shining Tumbler 20oz .psd  |               |
      | 02  | Tracker Bottle         | Image      | Tracker Bottle.psd         |               |
      | 03  | New Tumbler 30oz       | Image      | New Tumbler 30oz.psd       |               |
      | 04  | Two-sided Mug New      | Image      | Two-sided Mug New.psd      |               |
      | 05  | Tumbler 20oz           | Image      | Tumbler 20oz.psd           |               |
      | 06  | Skinny Tumbler         | Image      | Skinny Tumbler.psd         |               |
      | 07  | iPhone 12 Case         | Image      | iPhone 12 Case.psd         |               |
      | 08  | iPhone 12 Pro Case     | Image      | iPhone 12 Pro Case.psd     |               |
      | 09  | iPhone 12 Pro Max Case | Image      | iPhone 12 Pro Max Case.psd |               |
      | 10  | iPhone 11 Case         | Image      | iPhone 11 Case.psd         |               |
      | 11  | iPhone 11 Pro Case     | Image      | iPhone 11 Pro Case.psd     |               |
      | 12  | iPhone 11 Pro Max Case | Image      | iPhone 11 Pro Max Case.psd |               |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                                            |
      | 01  | PB-AOP-ShiningTumbler20oz-Alloverprint-Onesize |
      | 02  | PB-AOP-TrackerBottle-Alloverprint-Onesize      |
      | 03  | PB-AOP-NewTumbler30oz-Alloverprint-30oz        |
      | 04  | PB-AP-Two-sidedMugNew-White-11oz               |
      | 05  | PB-AOP-Tumbler20oz-Alloverprint-20oz           |
      | 06  | PB-AOP-SkinnyTumbler-Alloverprint-17oz         |
      | 07  | PB-AOP-iPhone12Case-Alloverprint-Onesize       |
      | 08  | PB-AOP-iPhone12ProCase-Alloverprint-Onesize    |
      | 09  | PB-AOP-iPhone12ProMaxCase-Alloverprint-Onesize |
      | 10  | PB-AOP-iPhone11Case-Alloverprint-Onesize       |
      | 11  | PB-AOP-iPhone11ProCase-Alloverprint-Onesize    |
      | 12  | PB-AOP-iPhone11ProMaxCase-Alloverprint-Onesize |
    And open product details in dashboard or editor campaign "<Campaign name>"
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color          | Size     |
      | 01  | <Campaign name> | All over print | One size |
      | 02  | <Campaign name> | All over print | One size |
      | 03  | <Campaign name> | All over print | 30oz     |
      | 04  | <Campaign name> | White          | 11oz     |
      | 05  | <Campaign name> | All over print | 20oz     |
      | 06  | <Campaign name> | All over print | 17oz     |
      | 07  | <Campaign name> | All over print | One size |
      | 08  | <Campaign name> | All over print | One size |
      | 09  | <Campaign name> | All over print | One size |
      | 10  | <Campaign name> | All over print | One size |
      | 11  | <Campaign name> | All over print | One size |
      | 12  | <Campaign name> | All over print | One size |
    And quit browser
    Examples:
      | KEY | Testcase                          | Campaign name          | Description            | Tags |
      | 01  | Create Cam Shining Tumbler 20oz   | Shining Tumbler 20oz   | Shining Tumbler 20oz   | test |
      | 02  | Create Cam Tracker Bottle         | Tracker Bottle         | Tracker Bottle         | test |
      | 03  | Create Cam New Tumbler 30oz       | New Tumbler 30oz       | New Tumbler 30oz       | test |
      | 04  | Create Cam Two-sided Mug New      | Two-sided Mug New      | Two-sided Mug New      | test |
      | 05  | Create Cam Tumbler 20oz           | Tumbler 20oz           | Tumbler 20oz           | test |
      | 06  | Create Cam Skinny Tumbler         | Skinny Tumbler         | Skinny Tumbler         | test |
      | 07  | Create Cam iPhone 12 Case         | iPhone 12 Case         | iPhone 12 Case         | test |
      | 08  | Create Cam iPhone 12 Pro Case     | iPhone 12 Pro Case     | iPhone 12 Pro Case     | test |
      | 09  | Create Cam iPhone 12 Pro Max Case | iPhone 12 Pro Max Case | iPhone 12 Pro Max Case | test |
      | 10  | Create Cam iPhone 11 Case         | iPhone 11 Case         | iPhone 11 Case         | test |
      | 11  | Create Cam iPhone 11 Pro Case     | iPhone 11 Pro Case     | iPhone 11 Pro Case     | test |
      | 12  | Create Cam iPhone 11 Pro Max Case | iPhone 11 Pro Max Case | iPhone 11 Pro Max Case | test |


