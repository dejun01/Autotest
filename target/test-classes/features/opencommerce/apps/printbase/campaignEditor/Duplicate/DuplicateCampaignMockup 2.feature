#Feature: Duplicate camp with select mockup (đang có update từ FT)
##   pbase_duplicate_editor
#
#  Scenario: Delete all product live
#    When delete all campaigns by API
#
#  Scenario Outline: Precondition >> Create new campaign editor
#    Given Description: "<Testcase>"
#    Given user login to shopbase dashboard by API
#    And user navigate to "Catalog" screen
#    When add products to campaign as "<KEY>"
#      | KEY | Product         | Category |
#      | 01  | Unisex Hoodie y | Apparel  |
#    And choose color for product in editor Dashboard as "<KEY>"
#      | KEY | Product         | Color     |
#      | 01  | Unisex Hoodie y | White,Red |
#    And add new layer as "<KEY>"
#      | KEY | Product         | Layer type | Layer name | Font | Location | Rotate | Opacity | Front or back |
#      | 01  | Unisex Hoodie y | Text       |            | 300  | 300>1000 | 30     | 80      | Front         |
#      | 01  | Unisex Hoodie y | Image      | 39.png     |      |          |        |         | Back          |
#    And click to button "Continue"
#    And input infor to create description for campaign editor
#      | Title           | Description   | Tags   |
#      | <Campaign name> | <Description> | <Tags> |
#    And input product price for campaign editor as "<KEY>"
#      | KEY | Product         | Variant                     | Sale price | Compare at price |
#      | 01  | Unisex Hoodie y | Unisex Hoodie y - White - S | 30.002     | 40               |
#    And click to button Launch campaign
#    And quit browser
#    Examples:
#      | KEY | Testcase                | Campaign name   | Description | Tags |
#      | 01  | create cam 2D full side | Campaign mockup | 2D full     | test |
#
#  Scenario Outline: Duplicate camp with case mockup normal
#    Given Description: "<Testcase>"
#    Given user login to shopbase dashboard by API
#    And user navigate to "Campaigns" screen
#    And search campaign in dashboard with name "<Campaign origin>"
#    And duplicate campaign editor
#      | Campaign origin   | Campaign name   | Is keep artwork |
#      | <Campaign origin> | <Campaign name> | true            |
#    And verify layer and base products editor as "<KEY>"
#      | KEY | Product             | Layer              | Font | Size | Location | Rotate | Opacity |
#      | 01  | Unisex Hoodie y     | Text layer 1>Front |      | 300  | 300>1000 | 30     | 80      |
#      | 01  | Crewneck Sweatshirt | 39>Back            |      |      |          |        |         |
#    And click to button "Continue"
#    And verify config in Pricing tab of editor detail as "<KEY>"
#      | KEY | Product         | Variant                     | Sale price | Compare price | isShow |
#      | 01  | Unisex Hoodie y | Unisex Hoodie y - White - S | 30.00      | 40.00         | true   |
#    And input infor to create description for campaign editor
#      | Title           | Description   | Tags   |
#      | <Campaign name> | <Description> | <Tags> |
#    And click to button Launch campaign
#    And open product details in dashboard or editor campaign "<Campaign name>"
#    And verify product information in dashboard
#      | Tags   | Description   |
#      | <Tags> | <Description> |
#    And verify variant information of product or campaign details in dashboard as "<KEY>"
#      | KEY | SKU                      | Price |
#      | 01  | PB-AP-UnisexTank-White-S | 30    |
#    Then verify image product detail page "03"
#      | KEY | Image expected       | Total product | Percent Image |
#      | 01  | /Prod_2D_mockup1.png | 4 / 500       | 0.5           |
#      | 01  | /Prod_2D_mockup2.png |               | 0.5           |
#      | 01  | /Prod_2D_mockup3.png |               | 0.5           |
#      | 01  | /Prod_2D_mockup4.png |               | 0.5           |
#    And open product details on Storefront from product detail in dashboard
#    Then verify product information from pod on storefront as "<KEY>"
#      | KEY | Product name    | Style           | Color | Size | Sale price | Compare at price |
#      | 01  | <Campaign name> | Unisex Hoodie y | White | S    | 30.00      | 40.00            |
#      | 02  | <Campaign name> | Unisex Hoodie y | White | S    | 30.00      | 40.00            |
#      | 02  | <Campaign name> | Unisex Hoodie y | White | M    | 35.00      | 65.00            |
#    And quit browser
#    Examples:
#      | KEY | Testcase                             | Campaign origin | Campaign name         | Description           | Tags |
#      | 01  | Origin campaign edited price variant | Campaign mockup | Duplicate camp mockup | Duplicate camp mockup | test |