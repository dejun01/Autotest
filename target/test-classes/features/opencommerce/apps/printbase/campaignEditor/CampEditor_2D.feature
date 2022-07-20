Feature: Create new campaign editor with base 2D
#   pbase_new_campaign_flow
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Create new campaign editor with base 2D #SB_PRB_ECP_638 #SB_PRB_ECP_643 #SB_PRB_ECP_646 #SB_PRB_ECP_647 #SB_PRB_ECP_648 #SB_PRB_ECP_681 #SB_PRB_ECP_685 #SB_PRB_ECP_686 #SB_PRB_ECP_693 #SB_PRB_ECP_694
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    And create campaign in Dashboard
    When add products to campaign as "<KEY>"
      | KEY | Product                                    | Category |
      | 01  | AutoUnisex T-Shirt,AutoCrewneck Sweatshirt | Apparel  |
      | 02  | AutoUnisex T-Shirt                         | Apparel  |
      | 03  | AutoUnisex T-Shirt                         | Apparel  |
      | 04  | AutoUnisex T-Shirt                         | Apparel  |
      | 05  | AutoUnisex T-Shirt,AutoCrewneck Sweatshirt | Apparel  |
    When add more or remove products to campaign as "<KEY>"
      | KEY | Product                 | Category | Add more | Remove |
      | 04  | AutoCrewneck Sweatshirt | Apparel  | true     |        |
      | 05  | AutoCrewneck Sweatshirt | Apparel  |          | true   |
    And choose color for product in editor Dashboard as "<KEY>"
      | KEY | Product | Color       |
      | 02  |         | Black,White |
    And add new layer as "<KEY>"
      | KEY | Product            | Layer type | Layer name  | Layer value | Font | Color | Front or back |
      | 01  | AutoUnisex T-Shirt | Text       |             | Test value  | 210  |       | Front         |
      | 01  | AutoUnisex T-Shirt | Image      | 39.png      |             |      |       | Back          |
      | 02  | AutoUnisex T-Shirt | Image      | artwork.png |             |      |       | Front         |
      | 03  | AutoUnisex T-Shirt | Text       |             |             | 210  |       | Back          |
      | 04  | AutoUnisex T-Shirt | Text       |             | Test value  | 210  |       | Front         |
      | 05  | AutoUnisex T-Shirt | Image      | 39.png      |             |      |       | Back          |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product            | Variant                        | Sale price | Compare at price |
      | 01  | AutoUnisex T-Shirt | AutoUnisex T-Shirt - White - S | 30.00      | 40               |
      | 01  | AutoUnisex T-Shirt | AutoUnisex T-Shirt - White - M | 30.346     | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                             | Price |
      | 01  | PB-AP-AutoUnisexT-Shirt-White-S | 30    |
      | 01  | PB-AP-AutoUnisexT-Shirt-White-M | 30.35 |

      | 02  | PB-AP-AutoUnisexT-Shirt-Black-M |       |
      | 02  | PB-AP-AutoUnisexT-Shirt-White-S |       |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 01  |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Style                   | Sale price | Compare at price |
      | 01  | <Campaign name> | White | M    | AutoUnisex T-Shirt      | 30.35      | 40               |
      | 01  | <Campaign name> | White | S    | AutoCrewneck Sweatshirt | 31.99      | 46.99            |
    And quit browser
    Examples:
      | KEY | Testcase                 | Campaign name        | Description          | Tags |
      | 01  | create cam 2D full side  | 2D full              | 2D full              | test |
      | 02  | create cam 2D front side | 2D front side        | 2D front side        | test |
      | 03  | create cam 2D back side  | 2D back side         | 2D back side         | test |
      | 04  | Add base                 | Campaign remove base | Campaign remove base | test |
      | 05  | Remove base              | Campaign add base    | Campaign add base    | test |

  Scenario: Verify render mockup after launch camp 2D full side
    Given Description: "create cam 2D full side"
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search product or campaign or orders "2D full" at list page in dashboard
    Then verify result after implement action as "01"
      | KEY | Label name | Campaign name |
      | 01  | Available  | 2D full       |
    And open product details in dashboard or editor campaign "2D full"
    And verify product information in dashboard
      | Tags | Description |
      | test | 2D full     |
    Then verify image product detail page "01"
      | KEY | Image expected        | Total product | Percent Image |
      | 01  | /Prod_2D_mockup1.png  | 52 / 500      | 1             |
      | 01  | /Prod_2D_mockup2.png  |               | 1             |
      | 01  | /Prod_2D_mockup3.png  |               | 1             |
      | 01  | /Prod_2D_mockup4.png  |               | 1             |
      | 01  | /Prod_2D_mockup5.png  |               | 1             |
      | 01  | /Prod_2D_mockup6.png  |               | 1             |
      | 01  | /Prod_2D_mockup7.png  |               | 1             |
      | 01  | /Prod_2D_mockup8.png  |               | 1             |
      | 01  | /Prod_2D_mockup9.png  |               | 1             |
      | 01  | /Prod_2D_mockup10.png |               | 1             |
    And open product details on Storefront from product detail in dashboard
    Then verify multi image product page "01"
      | KEY | Image expected        | Total Image | Percent Image |
      | 01  | /Prod_2D_mockup1.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup2.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup3.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup4.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup5.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup6.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup7.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup8.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup9.png  | 52          | 1             |
      | 01  | /Prod_2D_mockup10.png | 52          | 1             |
    And verify total mockup on SF as "01"
      | KEY | Campaign Name | Total Mockup |
      | 01  | 2D full       | 52           |
    And quit browser

  Scenario: Verify render mockup after launch camp 2D front side
    Given Description: "create cam 2D front side"
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search product or campaign or orders "2D front side" at list page in dashboard
    Then verify result after implement action as "02"
      | KEY | Label name | Campaign name |
      | 02  | Available  | 2D front side |
    And open product details in dashboard or editor campaign "2D front side"
    And verify product information in dashboard
      | Tags | Description   |
      | test | 2D front side |
    Then verify image product detail page "02"
      | KEY | Image expected            | Total product | Percent Image |
      | 02  | /Prod_2Dfront_mockup1.png | 20 / 500      | 1             |
      | 02  | /Prod_2Dfront_mockup2.png |               | 1             |
      | 02  | /Prod_2Dfront_mockup3.png |               | 1             |
      | 02  | /Prod_2Dfront_mockup4.png |               | 1             |
      | 02  | /Prod_2Dfront_mockup5.png |               | 1             |
    And open product details on Storefront from product detail in dashboard
    Then verify multi image product page "02"
      | KEY | Image expected            | Total Image | Percent Image |
      | 02  | /Prod_2Dfront_mockup1.png | 20          | 1             |
      | 02  | /Prod_2Dfront_mockup2.png | 20          | 1             |
      | 02  | /Prod_2Dfront_mockup3.png | 20          | 1             |
      | 02  | /Prod_2Dfront_mockup4.png | 20          | 1             |
      | 02  | /Prod_2Dfront_mockup5.png | 20          | 1             |
    And verify total mockup on SF as "02"
      | KEY | Campaign Name | Total Mockup |
      | 02  | 2D front side | 20           |
    And quit browser


  Scenario: Verify render mockup after launch camp 2D back side #SB_PRB_test_77 #SB_PRB_test_78 #SB_PRB_test_79 #SB_PRB_test_80 #SB_PRB_test_81 #SB_PRB_test_82 SB_PRB_test_234
    Given Description: " create cam 2D back side"
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search product or campaign or orders "2D back side" at list page in dashboard
    Then verify result after implement action as "03"
      | KEY | Label name | Campaign name |
      | 03  | Available  | 2D back side  |
    And open product details in dashboard or editor campaign "2D back side"
    And verify product information in dashboard
      | Tags | Description  |
      | test | 2D back side |
    Then verify image product detail page "03"
      | KEY | Image expected           | Total product | Percent Image |
      | 03  | /Prod_2Dback_mockup1.png | 4 / 500       | 0.5           |
      | 03  | /Prod_2Dback_mockup2.png |               | 0.5           |
      | 03  | /Prod_2Dback_mockup3.png |               | 0.5           |
      | 03  | /Prod_2Dback_mockup4.png |               | 0.5           |
    And open product details on Storefront from product detail in dashboard
    Then verify multi image product page "03"
      | KEY | Image expected           | Total Image | Percent Image |
      | 03  | /Prod_2Dback_mockup1.png | 4           | 0.5           |
      | 03  | /Prod_2Dback_mockup2.png | 4           | 0.5           |
      | 03  | /Prod_2Dback_mockup3.png | 4           | 0.5           |
      | 03  | /Prod_2Dback_mockup4.png | 4           | 0.5           |
    And verify total mockup on SF as "03"
      | KEY | Campaign Name | Total Mockup |
      | 03  | 2D back side  | 4            |
    And quit browser


  Scenario Outline: verify mesage limit variant campaign #SB_PRB_ECP_641 #SB_PRB_ECP_680 #SB_PRB_ECP_683 #SB_PRB_ECP_682 SB_PRB_test_234
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    And create campaign in Dashboard
    When add products to campaign as "<KEY>"
      | KEY | Product                                                                  | Category |
      | 01  | Unisex T-shirt,V-neck T-shirt,Ladies T-shirt,Unisex Tank,Long Sleeve Tee | Apparel  |
    And choose color for product in editor Dashboard as "<KEY>"
      | KEY | Product                                                                  | Color | Message                                                           |
      | 01  | Unisex T-shirt,V-neck T-shirt,Ladies T-shirt,Unisex Tank,Long Sleeve Tee | All   | Total variants: 533/500. You cannot create more than 500 variants |
    And click to button "Continue"
    And verify message error in pricing screen "<KEY>"
      | KEY | Reason        | Message Error                                                                          |
      | 01  | Limit variant | Exceed number of variants (533/500). Please remove some variants on the previous step. |
    And verify config in Pricing tab of editor detail as "<KEY>"
      | KEY | Product         | Cost  | Sale price | Compare price |
      | 01  | Unisex T-shirt  | $4.99 | $19.99     | $34.99        |
      | 01  | V-neck T-shirt  | $8.99 | $23.99     | $38.99        |
      | 01  | Ladies T-shirt  | $5.99 | $20.99     | $35.99        |
      | 01  | Unisex Tank     | $8.99 | $23.99     | $38.99        |
      | 01  | Long Sleeve Tee | $8.49 | $23.49     | $38.49        |
    Examples:
      | KEY |
      | 01  |
