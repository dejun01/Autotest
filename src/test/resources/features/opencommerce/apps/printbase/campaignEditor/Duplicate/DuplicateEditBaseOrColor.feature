Feature: Duplicate
#   pbase_duplicate_editor

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Precondition >> Create new campaign editor with base 2D
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                         | Category |
      | 01  | Unisex Tank,Crewneck Sweatshirt | Apparel  |
    And choose color for product in editor Dashboard as "<KEY>"
      | KEY | Product     | Color     |
      | 01  | Unisex Tank | White,Red |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Update layer name | Font | Color | Location | Rotate | Opacity | Front or back |
      | 01  | Unisex Tank | Text       |            | Test layer        | 300  |       | 300>1000 | 30     | 80      | Front         |
      | 01  | Unisex Tank | Image      | 39.png     |                   |      |       |          |        |         | Back          |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.002     | 40               |
    And click to button Launch campaign
    And quit browser
    Examples:
      | KEY | Testcase                | Campaign name   | Description | Tags |
      | 01  | create cam 2D full side | Campaign origin | 2D full     | test |


  Scenario Outline: Duplicate keep artwork with case add more / remove base or variant #SB_PRB_DKA_99 #SB_PRB_DKA_85 #SB_PRB_DKA_111 #SB_PRB_DKA_88 #SB_PRB_DKA_112
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name   | Is keep artwork |
      | <Campaign origin> | <Campaign name> | true            |
    When add more or remove products to campaign as "<KEY>"
      | KEY | Product             | Category       | Add more | Remove |
      | 01  | Long Sleeve Tee     | Apparel        | true     |        |
      | 01  | Crewneck Sweatshirt | Apparel        |          | true   |
      | 02  | AOP Hoodie          | All Over Print | true     |        |
    And choose color for product in editor Dashboard as "<KEY>"
      | KEY | Product     | Color           |
      | 03  | Unisex Tank | White,Red,Black |
      | 04  | Unisex Tank | White           |
    And add new layer as "<KEY>"
      | KEY | Product    | Layer type | Layer name | Update layer value | Font | Color | Location layer | Size layer | Rotate layer | Opacity layer | Front or back |
      | 02  | AOP Hoodie | Text       |            | Test layer         | 210  |       | 0>500          | 1300>300   | 96.44        | 80            | Front         |
      | 02  | AOP Hoodie | Image      | 39.png     |                    |      |       |                |            |              |               | Back          |
    And verify layer and base products editor as "<KEY>"
      | KEY | Product         | Layer            | Font | Size | Location | Rotate | Opacity |
      | 01  | Unisex Tank     | Test layer>Front |      | 300  | 300>1000 | 30     | 80      |
      | 01  | Long Sleeve Tee | Test layer>Front |      | 300  | 300>1000 | 30     | 80      |
    And click to button "Continue"
    And input product price for campaign editor as "<KEY>"
      | KEY | Product         | Variant                         | Sale price | Compare at price |
      | 01  | Long Sleeve Tee | Long Sleeve Tee - White - S     | 40         | 60               |
      | 02  | AOP Hoodie      | AOP Hoodie - All over print - M | 30.88      | 40.88            |
    And verify config in Pricing tab of editor detail as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare price | isShow |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40            | true   |
      | 03  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40            | true   |
      | 03  | Unisex Tank | Unisex Tank - Black - S |            |               | true   |
      | 03  | Unisex Tank | Unisex Tank - Red - S   |            |               | true   |
      | 04  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40            | true   |
      | 04  | Unisex Tank | Unisex Tank - Red - S   |            |               | false  |
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
      | KEY | SKU                             | Price |
      | 01  | PB-AP-UnisexTank-White-S        | 30    |
      | 01  | PB-AP-LongSleeveTee-White-S     | 40    |
      | 02  | PB-AOP-AOPHoodie-Alloverprint-M | 30.88 |
      | 03  | PB-AP-UnisexTank-White-S        | 30    |
      | 03  | PB-AP-UnisexTank-Red-S          |       |
      | 03  | PB-AP-UnisexTank-Black-S        |       |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Style           | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> | Unisex Tank     | White | S    | 30         | 40               |
      | 01  | <Campaign name> | Long Sleeve Tee | White | S    | 40         | 60               |
    And quit browser
    Examples:
      | KEY | Testcase               | Campaign origin | Campaign name                 | Description                  | Tags |
      | 01  | add and remove base 2D | Campaign origin | Duplicate add and remove base | add and remove base 2D       | test |
      | 02  | add more base 3D       | Campaign origin | Duplicate add base 3D         | add more base 3D             | test |
      | 03  | add color for base     | Campaign origin | Duplicate add color           | Duplicate add color for base | test |
      | 04  | delete color for base  | Campaign origin | Duplicate delete color        | Duplicate add color for base | test |
