Feature: Create new campaign editor
#   pbase_new_campaign_editor_custom
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Create new campaign base product
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product               | Category |
      | 01  | 3/4 Sleeve Raglan Tee | Apparel  |
      | 02  | Baby One Piece        | Apparel  |
    And get position base product in editor
    And add layer base product as "<KEY>"
      | KEY | Product               | Layer type | Layer name                     | Front or back |
      | 01  | 3/4 Sleeve Raglan Tee | Image      | 34 Sleeve Raglan Tee_Front.psd | Front         |
      | 01  | 3/4 Sleeve Raglan Tee | Image      | 34 Sleeve Raglan Tee_Back.psd  | Back          |

      | 02  | Baby One Piece        | Image      | Baby One Piece.psd             |               |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
#    And search product or campaign or orders "<Campaign name>" at list page in dashboard
#    And open product details in dashboard or editor campaign "<Campaign name>"
#    And verify product information in dashboard
#      | Tags   | Description   |
#      | <Tags> | <Description> |
#    And verify variant information of product or campaign details in dashboard as "<KEY>"
#      | KEY | SKU                                 |
#      | 01  | PB-AP-3/4SleeveRaglanTee-Red-XS     |
#      | 02  | PB-AP-BabyOnePiece-PatriotBlue-0~3M |
#    And open product details in dashboard or editor campaign "<Campaign name>"
#    Then verify product information from pod on storefront as "<KEY>"
#      | KEY | Product name    | Color       | Size |
#      | 01  | <Campaign name> | Red         | XS   |
#      | 02  | <Campaign name> | PatriotBlue | 0~3M |
    And quit browser
    Examples:
      | KEY | Testcase                         | Campaign name         | Description           | Tags |
      | 01  | Create Cam 3/4 Sleeve Raglan Tee | 3/4 Sleeve Raglan Tee | 3/4 Sleeve Raglan Tee | test |
      | 02  | Create Cam AOP Sweatshirt        | AOP Sweatshirt        | AOP Sweatshirt        | test |









