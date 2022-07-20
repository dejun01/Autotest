Feature:Check validate editor
#  pbase_new_campaign_editor_3D


  Scenario Outline: Check  with layer text
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
#    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product      | Category  |
      | 01  | Beverage Mug | Drinkware |
    Then veriry noti  product base in editor as "<KEY>"
      | KEY | Product | Message noti                                        | is Noti |
      | 01  |         | Please add the layer or complete the required field | true    |
    And input layer and verify layer as "<KEY>"
      | KEY | Product    | Layer type | Layer name | Layer value | Font | Color | Location layer | Size layer | Rotate layer | Opacity layer | Front or back |
      | 01  | AOP Hoodie | Text       |            | Test value  | 210  |       | 0>500          | 1300>300   | 96.44        | 80            | Front         |


    And add new layer as "<KEY>"
      | KEY | Product       | Layer type | Layer name | Layer value | Font | Color | Location layer | Size layer | Rotate layer | Opacity layer | Front or back |
      | 01  | AOP Hoodie    | Text       |            | Test value  | 210  |       | 0>500          | 1300>300   | 96.44        | 80            | Front         |
      | 01  | AOP Hoodie    | Image      | 39.png     |             |      |       |                |            |              |               | Back          |

      | 02  | Square Canvas | Text       |            |             | 210  |       |                |            |              |               |               |

      | 03  | Quilt         | Text       |            | Test value  | 210  |       |                |            |              |               |               |

      | 04  | Square Canvas | Text       |            |             | 210  |       |                |            |              |               |               |
      | 04  | Quilt         | Text       |            | Test value  | 210  |       |                |            |              |               |               |
      | 04  | Beverage Mug  | Image      | 39.png     |             |      |       |                |            |              |               |               |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product    | Variant                         | Sale price | Compare at price |
      | 01  | AOP Hoodie | AOP Hoodie - All over print - M | 30.88      | 40.88            |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                              | Price |
      | 01  | PB-AOP-AOPHoodie-Alloverprint-M  | 30.88 |

      | 02  | SquareCanvas-White-8x8in         |       |

      | 03  | PB-AOP-Quilt-Alloverprint-Single |       |
      | 03  | PB-AOP-Quilt-Alloverprint-Queen  |       |
      | 03  | PB-AOP-Quilt-Alloverprint-King   |       |

      | 04  | PB-AP-BeverageMug-black-15oz     |       |
      | 04  | PB-AP-BeverageMug-white-15oz     |       |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Style | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> |       |       | M    | 30.88      | 40.88            |
    And quit browser
    Examples:
      | KEY | Testcase             | Campaign name        | Description          | Tags        |
      | 01  | Campaign 3D two side | Campaign 3D two side | Campaign 3D two side | ladies,test |
      | 02  | Campaign canvas      | Campaign canvas      | Campaign canvas      | ladies,test |
      | 03  | Campaign 3D one size | Campaign 3D 1 size   | Campaign 3D 1 size   | ladies,test |
      | 04  | Campaign 3D mix      | Campaign mix         | Campaign mix         | ladies,test |
