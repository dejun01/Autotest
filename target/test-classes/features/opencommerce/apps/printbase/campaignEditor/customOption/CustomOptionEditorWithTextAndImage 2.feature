Feature: Create new campaign custom option
  #pbase_new_campaign_editor_custom

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Custom option with base 2D with image and text #SB_PRB_ECP_643 #SB_PRB_ECP_647 #SB_PRB_ECP_648 #SB_PRO_DFPP_773 #SB_PRO_DFPP_814 #SB_PRO_DFPP_815 #SB_PRO_DFPP_816 #SB_PRO_DFPP_842 #SB_PRB_ECP_660
    Given  Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                         | Category |
      | 01  | Unisex Tank                     | Apparel  |
      | 02  | Unisex Tank,Crewneck Sweatshirt | Apparel  |
      | 03  | Unisex Tank,Crewneck Sweatshirt | Apparel  |
      | 04  | Unisex Tank,Crewneck Sweatshirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Font | Front or back |
      | 01  | Unisex Tank | Text       |            | 210  | Front         |
      | 01  | Unisex Tank | Text       |            | 210  | Back          |
      | 02  | Unisex Tank | Text       |            | 300  | Front         |
      | 02  | Unisex Tank | Image      | 39.png     |      | Back          |
      | 03  | Unisex Tank | Image      | 39.png     |      | Back          |
      | 04  | Unisex Tank | Text       |            | 300  | Front         |
      | 04  | Unisex Tank | Image      | Bulk3.png  |      | Back          |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type     | Layer name   | Custom name    | Font    | Placeholder | Max length | Default value | Text       | Folder or Group | Thumbnail or Clipart names | Value clipart   |
      | 01  | Text field     | Text layer 1 | Custom text    | Raleway | Input text  | 10         | text          |            |                 |                            |                 |
      | 01  | Text area      | Text layer 2 | Custom  area   | Raleway | Input text  | 10         | area          |            |                 |                            |                 |
      | 02  | Image          | 39           | Custom Image   |         |             |            |               | Test image |                 |                            |                 |
      | 03  | Picture choice | 39           | Picture choice |         |             |            |               |            | Folder          | Thumbnail                  | ClipartFolder01 |
      | 04  | Picture choice | Bulk3        | Picture choice |         |             |            |               |            | Folder          | Thumbnail                  | ClipartFolder01 |
      | 04  | Text field     | Text layer 1 | Custom text    | Raleway | Input text  | 10         | Default       |            |                 |                            |                 |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags |
      | <Campaign name> | Custom option | test |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - M | 30         | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Description   | Tags |
      | Custom option | test |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-M | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Style | Sale price | Compare at price |
      | 01  | <Campaign name> | White | M    |       | 30         | 40               |

    Then verify show custom option on store front as "<KEY>"
      | KEY | Style               | Type           | Custom name    | Value Input   | Crop Image |
      | 01  |                     | Text           | Custom text    | Test text     |            |
      | 02  | Crewneck Sweatshirt | Image          | Custom Image   | Campaign1.png | true       |
      | 03  | Crewneck Sweatshirt | Picture choice | Picture choice | BD_1          |            |
      | 04  | Unisex Tank         | Text           | Custom text    | Test text     |            |
      | 04  |                     | Picture choice | Picture choice | BD_1          |            |

    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Testcase                                      | Campaign name             |
      | 01  | Create option text with layer text            | Custom_Text_With_Text     |
      | 02  | Create option image with layer image          | Custom_Image_With_Image   |
      | 03  | Create option image with layer picture choice | Custom_Picture_With_Image |
      | 04  | Create full option with layer text&image      | Custom_Full               |

