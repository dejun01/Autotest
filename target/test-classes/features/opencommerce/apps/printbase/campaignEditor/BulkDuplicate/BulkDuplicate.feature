Feature: Bulk Duplicate Campaign
#  print_on_demand_bulk_duplicate_campaign
  Scenario: Delete all campaign
    When delete all campaigns by API

  Scenario Outline: AU_CCP_4.1: Push product to store #SB_PRB_BD_68
    Given Description: "<Testcase>"
    And user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 01  | Unisex T-shirt | Apparel  |
      | 02  | Unisex Tank    | Apparel  |
      | 03  | Unisex Tank    | Apparel  |
    When add more or remove products to campaign as "<KEY>"
      | KEY | Product        | Category  | Add more |
      | 01  | Unisex Tank    | Apparel   | true     |
      | 03  | Skinny Tumbler | Drinkware | true     |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Front or back |
      | 01  | Unisex T-shirt | Text       | Front         |
      | 02  | Unisex Tank    | Text       | Back          |

      | 03  | Unisex Tank    | Text       | Back          |
      | 03  | Skinny Tumbler | Text       |               |

    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description |
      | <Campaign name> | 2D          |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name   | Status | Is enable duplicate | Is enable bulk duplicate |
      | 01  | <Campaign name> | LIVE   | true                | true                     |
      | 02  | <Campaign name> | LIVE   | true                | false                    |
      | 03  | <Campaign name> | LIVE   | true                | false                    |
    And quit browser
    Examples:
      | KEY | Testcase                                                             | Campaign name           |
      | 01  | Create multiple product 2D                                           | Campaign 2D             |
      | 02  | Dont bulk duplicate with 2D campaign only back                       | Product 2D back         |
      | 03  | Dont bulk duplicate with campaign created 2D campaign 1 back and mug | Product 2D back and Mug |


  Scenario Outline: AU_CP_4.1_ Bulk duplicate campaign #SB_PRB_BD_67 #SB_PRB_BD_66 #SB_PRB_BD_65 #SB_PRB_BD_64 #SB_PRB_BD_63 #SB_PRB_BD_60 #SB_PRB_BD_58 #SB_PRB_BD_57 #SB_PRB_BD_55 #SB_PRB_BD_54 #SB_PRB_BD_52 #SB_PRB_BD_51 #SB_PRB_BD_49 #SB_PRB_BD_47 #SB_PRB_BD_59
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And implement action with campaign as "<KEY>"
      | KEY | Action name                 | Campaign name |
      | 2   | Make 1 campaign unavailable | Campaign 2D   |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name  | Campaign name |
      | 2   | Unavailable | Campaign 2D   |
    And bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name | Artwork       |
      | 1   | Campaign 2D   | Campaign1.png |
      | 2   | Campaign 2D   | BD_5.png      |
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword   |
      | 1   | Campaign1 |
      | 2   | BD_5      |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name | Status      | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Campaign1     | LIVE        | true                | true                     |
      | 2   | BD_5          | UNAVAILABLE | true                | true                     |
    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY | Campaign name |
      | 1   | Campaign1     |
      | 2   | BD_5          |
    And verify product information in dashboard as "<KEY>"
      | KEY | SKU                      | Is enable duplicate | Is enable bulk duplicate | Description |
      | 1   | PB-AP-UnisexTank-White-S | true                | true                     | 2D          |
      | 2   | PB-AP-UnisexTank-White-S | true                | true                     | 2D          |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 1   |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name | Size | Color | Style       | Get sale price | Get compare at price |
      | 1   | Campaign1    | S    | White | Unisex Tank | 30             | 40                   |
    And quit browser
    Examples:
      | KEY | Testcase                               |
      | 1   | Bulk duplicate 2D campaign available   |
      | 2   | Bulk duplicate 2D campaign unavailable |
