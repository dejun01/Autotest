Feature: Create new campaign editor with base 3D on stag
# staging_pbase_new_campaign_editor_3D

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Create new campaign editor with base 3D #SB_PRB_ECP_687 #SB_PRB_ECP_688
    Given Description: "<Testcase>"
    And user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product          | Category       |
      | 01  | AutoAOP Hoodie   | All Over Print |
      | 02  | AutoBeverage Mug | Drinkware      |
      | 03  | AutoQuilt        | Home & Living  |
      | 04  | AutoAOP T-Shirt  | All Over Print |
      | 04  | AutoQuilt        | Home & Living  |
      | 04  | AutoBeverage Mug | Drinkware      |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name  | Layer value | Font | Color | Location layer | Size layer | Rotate layer | Opacity layer | Front or back |
      | 01  | AutoAOP Hoodie   | Text       |             | Test value  | 210  |       | 0>500          | 1300>300   | 96.44        | 80            | Front         |
      | 01  | AutoAOP Hoodie   | Image      | 39.png      |             |      |       |                |            |              |               | Back          |

      | 02  | AutoBeverage Mug | Text       |             | Test value  | 210  |       |                |            |              |               |               |

      | 03  | AutoQuilt        | Text       |             | Test value  | 210  |       |                |            |              |               |               |

      | 04  | AutoAOP T-Shirt  | Image      | f03.png     |             |      |       |                |            |              |               | Front         |
      | 04  | AutoAOP T-Shirt  | Text       |             |             | 210  |       |                |            |              |               | Back          |
      | 04  | AutoQuilt        | Image      | artwork.png |             |      |       |                |            |              |               | Back          |
      | 04  | AutoBeverage Mug | Text       |             |             |      |       |                |            |              |               |               |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product        | Variant                             | Sale price | Compare at price |
      | 01  | AutoAOP Hoodie | AutoAOP Hoodie - All over print - M | 30.88      | 40.88            |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                                  | Price |
      | 01  | PB-AOP-AutoAOPHoodie-Alloverprint-M  | 30.88 |

      | 02  | PB-AP-AutoBeverageMug-white-15oz     |       |

      | 03  | PB-AOP-AutoQuilt-Alloverprint-Single |       |
      | 03  | PB-AOP-AutoQuilt-Alloverprint-Queen  |       |
      | 03  | PB-AOP-AutoQuilt-Alloverprint-King   |       |

      | 04  | PB-AP-AutoBeverageMug-black-15oz     |       |
      | 04  | PB-AP-AutoBeverageMug-white-15oz     |       |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Style | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> |       |       | M    | 30.88      | 40.88            |
    And quit browser
    Examples:
      | KEY | Testcase             | Campaign name        | Description          | Tags        |
      | 01  | Campaign 3D two side | Campaign 3D two side | Campaign 3D two side | ladies,test |
      | 02  | Campaign Beverage    | Campaign Beverage    | Campaign Beverage    | ladies,test |
      | 03  | Campaign 3D one size | Campaign 3D 1 size   | Campaign 3D 1 size   | ladies,test |
      | 04  | Campaign 3D mix      | Campaign mix         | Campaign mix         | ladies,test |


  Scenario Outline: Verify render mockup after launch camp on stag #SB_PRB_test_77 #SB_PRB_test_78 #SB_PRB_test_79 #SB_PRB_test_80 #SB_PRB_test_81 #SB_PRB_test_82
    Given Description: "<Testcase>"
    And user login to shopbase dashboard by API
    When user navigate to "Campaigns" screen
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And verify result after implement action as "<KEY>"
      | KEY | Label name | Campaign name   |
      | 01  | Available  | <Campaign name> |
      | 02  | Available  | <Campaign name> |
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    Then verify image product detail page "<KEY>"
      | KEY | Image expected           | Total Image | Percent Image |
      | 01  | /Stag_3D-mockup1.png     | 2 / 500     | 0.5           |
      | 01  | /Stag_3D-mockup2.png     |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup1.png  | 24 / 500    | 0.5           |
      | 02  | /Stag_3Dmix_mockup2.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup3.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup4.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup5.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup6.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup7.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup8.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup9.png  |             | 0.5           |
      | 02  | /Stag_3Dmix_mockup10.png |             | 0.5           |
    And open product details on Storefront from product detail in dashboard
    Then verify multi image product page "<KEY>"
      | KEY | Image expected           | Total Image | Percent Image |
      | 01  | /Stag_3D-mockup1.png     | 2           | 0.5           |
      | 01  | /Stag_3D-mockup2.png     | 2           | 0.5           |
      | 02  | /Stag_3Dmix_mockup1.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup2.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup3.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup4.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup5.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup6.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup7.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup8.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup9.png  | 24          | 0.5           |
      | 02  | /Stag_3Dmix_mockup10.png | 24          | 0.5           |
    And verify total mockup on SF as "<KEY>"
      | KEY | Campaign Name   | Total Mockup |
      | 01  | <Campaign name> | 2            |
      | 02  | <Campaign name> | 24           |
    And quit browser
    Examples:
      | KEY | Testcase             | Campaign name        | Description          | Tags        |
      | 01  | Campaign 3D two side | Campaign 3D two side | Campaign 3D two side | ladies,test |
      | 02  | Campaign 3D mix      | Campaign mix         | Campaign mix         | ladies,test |
