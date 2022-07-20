Feature: Create new campaign editor with base 3D
#pbase_duplicate_editor

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Precondition >> Create new campaign editor with base 2D
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                         | Category |
      | 01  | Unisex Tank,Crewneck Sweatshirt | Apparel  |
      | 02  | Unisex Tank                     | Apparel  |
    And choose color for product in editor Dashboard as "<KEY>"
      | KEY | Product     | Color     |
      | 01  | Unisex Tank | White,Red |
    And add new layer as "<KEY>"
      | KEY | Product             | Layer type | Layer name | Font | Location | Rotate | Opacity | Front or back |
      | 01  | Unisex Tank         | Text       |            | 300  | 300>1000 | 30     | 80      | Front         |
      | 01  | Unisex Tank         | Image      | 39.png     |      |          |        |         | Back          |
      | 01  | Crewneck Sweatshirt | Text       |            | 300  | 300>1000 | 30     | 80      | Front         |
      | 02  | Unisex Tank         | Image      | 39.png     |      |          |        |         | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.002     | 40               |
    And input price for base in tab pricing
      | Product   | Sale price   | Compare at price   |
      | <Product> | <Sale price> | <Compare at price> |
    And click to button Launch campaign
    And quit browser
    Examples:
      | KEY | Testcase                | Campaign name     | Description | Tags | Product     | Sale price | Compare at price |
      | 01  | create cam 2D full side | Campaign origin   | 2D full     | test |             |            |                  |
      | 02  | create cam 2D one side  | Campaign One side | 2D one side | test | Unisex Tank | 30.00      | 60               |

  Scenario Outline: Duplicate keep artwork from origin campaign edited price #SB_PRB_DKA_83 #SB_PRB_DKA_84 #SB_PRB_DKA_113
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name   | Is keep artwork |
      | <Campaign origin> | <Campaign name> | true            |
    And verify layer and base products editor as "<KEY>"
      | KEY | Product             | Layer              | Font | Size | Location | Rotate | Opacity |
      | 01  | Unisex Tank         | Text layer 1>Front |      | 300  | 300>1000 | 30     | 80      |
      | 01  | Crewneck Sweatshirt | 39>Back            |      |      |          |        |         |
    And click to button "Continue"
    And verify config in Pricing tab of editor detail as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare price | isShow |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40.00         | true   |
      | 02  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40.00         | true   |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 02  | Unisex Tank | Unisex Tank - White - M | 35         | 65               |
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-S | 30    |
      | 02  | PB-AP-UnisexTank-White-S | 30    |
      | 02  | PB-AP-UnisexTank-White-M | 35    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Style       | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> | Unisex Tank | White | S    | 30.00      | 40.00            |
      | 02  | <Campaign name> | Unisex Tank | White | S    | 30.00      | 40.00            |
      | 02  | <Campaign name> | Unisex Tank | White | M    | 35.00      | 65.00            |
    And quit browser
    Examples:
      | KEY | Testcase                             | Campaign origin | Campaign name | Description  | Tags |
      | 01  | Origin campaign edited price variant | Campaign origin | Duplicate 01  | Duplicate 01 | test |
      | 02  | Duplicate edit price                 | Campaign origin | Duplicate 02  | Duplicate 02 | test |

  Scenario Outline: Duplicate keep artwork add more or remove side with base 2D  #SB_PRB_DKA_87 #SB_PRB_DKA_98
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name   | Is keep artwork |
      | <Campaign origin> | <Campaign name> | true            |
    And delete layer editor as "<KEY>"
      | KEY | Product     | Layer | Front or back |
      | 01  | Unisex Tank | 39    | Back          |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Front or back |
      | 02  | Unisex Tank | Image      | 39.png     | Back          |
    And click to button "Continue"
    And verify config in Pricing tab of editor detail as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare price | isShow |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40.00         | true   |
      | 02  | Unisex Tank | Unisex Tank - White - S | 30.00      | 60.00         | true   |
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-S | 30    |
      | 02  | PB-AP-UnisexTank-White-S | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Style       | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> | Unisex Tank | White | S    | 30.00      | 40.00            |
      | 02  | <Campaign name> |             | White | M    | 30.00      | 60.00            |
    And quit browser
    Examples:
      | KEY | Testcase             | Campaign origin   | Campaign name          | Description  | Tags |
      | 01  | Delete layer in side | Campaign origin   | Duplicate delete layer | delete layer | test |
      | 02  | Add layer for side   | Campaign One side | Duplicate add layer    | add layer    | test |

  Scenario Outline: Duplicate keep artwork with case add custom option #SB_PRB_DKA_100 #SB_PRB_DKA_101 #SB_PRB_DKA_104
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name   | Is keep artwork |
      | <Campaign origin> | <Campaign name> | true            |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name  |
      | 01  | Text field | Text layer 1 | Custom text  |
      | 01  | Image      | 39           | Custom Image |
    And click to button "Continue"
    And verify config in Pricing tab of editor detail as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare price | isShow |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40.00         | true   |
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
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-S | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type  | Custom name  | Value Input   |
      | 01  | Text  | Custom text  | Test text     |
      | 01  | Image | Custom Image | Campaign1.png |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Campaign origin | Campaign name           | Description             | Tags |
      | 01  | Campaign origin | Duplicate custom option | Duplicate custom option | test |


  Scenario Outline: Duplicate dont keep artwork #SB_PRB_DKA_107 #SB_PRB_DKA_105
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name   | Is keep artwork |
      | <Campaign origin> | <Campaign name> | false           |
    And verify list layer and custom option null
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Front or back |
      | 01  | Unisex Tank | Text       | Front         |
      | 01  | Unisex Tank | Text       | Back          |
    And click to button "Continue"
    And verify config in Pricing tab of editor detail as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare price | isShow |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40.00         | true   |
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
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-S | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Style       | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> | Unisex Tank | White | S    | 30.00      | 40.00            |
    And quit browser
    Examples:
      | KEY | Testcase                         | Campaign origin | Campaign name          | Description | Tags |
      | 01  | dont keep but side = side orrgin | Campaign origin | Duplicate dont keep 01 | Duplicate   | test |
