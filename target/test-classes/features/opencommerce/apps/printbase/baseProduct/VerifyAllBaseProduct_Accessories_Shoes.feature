Feature: Create new campaign editor
#    pbase_verify_base_product
#  Scenario: Delete all product live
#    When delete all campaigns by API

  Scenario Outline: Create new campaign base product
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                    | Category    |
      | 01  | Sleep Mask                 | Accessories |
      | 02  | Heart Necklace With Box    | Accessories |
      | 03  | Mouse Pad                  | Accessories |
      | 04  | Wooden Keyring Gift        | Accessories |
      | 05  | Large Mouse Pad            | Accessories |
      | 06  | Face Mask                  | Accessories |
      | 07  | Fabric Mask (with filters) | Accessories |
      | 08  | Cotton Face Mask           | Accessories |
      | 09  | Neck Gaiter New            | Accessories |
      | 10  | Low Top Sneakers Black     | Shoes       |
      | 11  | Slip On Shoes              | Shoes       |
      | 12  | YZY Shoes                  | Shoes       |
      | 13  | Leather Boots New          | Shoes       |
      | 14  | Low Top Shoes              | Shoes       |
    And get position base product in editor
    And add layer base product as "<KEY>"
      | KEY | Product                    | Layer type | Layer name                     |
      | 01  | Sleep Mask                 | Image      | Sleep Mask.psd                 |
      | 02  | Heart Necklace With Box    | Image      | Heart Necklace With Box.psd    |
      | 03  | Mouse Pad                  | Image      | Mouse Pad.psd                  |
      | 04  | Wooden Keyring Gift        | Image      | Wooden Keyring Gift.psd        |
      | 05  | Large Mouse Pad            | Image      | Large Mouse Pad.psd            |
      | 06  | Face Mask                  | Image      | Face Mask.psd                  |
      | 07  | Fabric Mask (with filters) | Image      | Fabric Mask (with filters).psd |
      | 08  | Cotton Face Mask           | Image      | Cotton Face Mask.psd           |
      | 09  | Neck Gaiter New            | Image      | Neck Gaiter New.psd            |
      | 10  | Low Top Sneakers Black     | Image      | Low Top Sneakers Black.psd     |
      | 11  | Slip On Shoes              | Image      | Slip On Shoes.psd              |
      | 12  | YZY Shoes                  | Image      | YZY Shoes.psd                  |
      | 13  | Leather Boots New          | Image      | Leather Boots New.psd          |
      | 14  | Low Top Shoes              | Image      | Low Top Shoes.psd              |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
#    And open product details in dashboard or editor campaign "<Campaign name>"
#    And verify product information in dashboard
#      | Tags   | Description   |
#      | <Tags> | <Description> |
#    And verify variant information of product or campaign details in dashboard as "<KEY>"
#      | KEY | SKU                                                      |
#      | 01  | PB-AOP-SleepMask-Alloverprint-Onesize                    |
#      | 02  | PB-AOP-HeartNecklaceWithBox-Alloverprint-Onesize         |
#      | 03  | PB-AOP-MousePad-Alloverprint-Onesize                     |
#      | 04  | PB-AOP-WoodenKeyringGift-Alloverprint-Onesize            |
#      | 05  | PB-AOP-LargeMousePad-Alloverprint-Onesize                |
#      | 06  | PB-AOP-FaceMask-Alloverprint-1pcs                        |
#      | 07  | PB-AOP-FabricMask(withfilters)-Alloverprint-1pcs+1filter |
#      | 08  | PB-AOP-CottonFaceMask-Alloverprint-Onesize               |
#      | 09  | PB-AOP-NeckGaiterNew-Alloverprint-1pcs                   |
#      | 10  | PB-AOP-LowTopSneakersBlack-Alloverprint-Woman5           |
#      | 11  | PB-AOP-SlipOnShoes-Alloverprint-Woman5                   |
#      | 12  | PB-AOP-YZY Shoes-Alloverprint-Woman5                     |
#      | 13  | PB-AOP-LeatherBootsNew-Alloverprint-35                   |
#      | 14  | PB-AOP-LowTopShoes-Alloverprint-Woman5                   |
#    And open product details in dashboard or editor campaign "<Campaign name>"
#    Then verify product information from pod on storefront as "<KEY>"
#      | KEY | Product name    | Color          | Size             |
#      | 01  | <Campaign name> | All over print | One size         |
#      | 02  | <Campaign name> | All over print | One size         |
#      | 03  | <Campaign name> | All over print | One size         |
#      | 04  | <Campaign name> | All over print | One size         |
#      | 05  | <Campaign name> | All over print | One size         |
#      | 06  | <Campaign name> | All over print | 1pcs             |
#      | 07  | <Campaign name> | All over print | 1 pcs + 1 filter |
#      | 08  | <Campaign name> | All over print | One size         |
#      | 09  | <Campaign name> | All over print | 1pcs             |
#      | 10  | <Campaign name> | All over print | Woman 5          |
#      | 11  | <Campaign name> | All over print | Woman 5          |
#      | 12  | <Campaign name> | All over print | Woman 5          |
#      | 13  | <Campaign name> | All over print | 35               |
#      | 14  | <Campaign name> | All over print | Woman 5          |

    And quit browser
    Examples:
      | KEY | Campaign name              | Description                | Tags |
#      | 01  | Sleep Mask                 | Sleep Mask                 | test |
#      | 02  | Heart Necklace With Box    | Heart Necklace With Box    | test |
#      | 03  | Mouse Pad                  | Mouse Pad                  | test |
#      | 04  | Wooden Keyring Gift        | Wooden Keyring Gift        | test |
#      | 05  | Large Mouse Pad            | Large Mouse Pad            | test |
#      | 06  | Face Mask                  | Face Mask                  | test |
#      | 07  | Fabric Mask (with filters) | Fabric Mask (with filters) | test |
      | 08  | Cotton Face Mask           | Cotton Face Mask           | test |
      | 09  | Neck Gaiter New            | Neck Gaiter New            | test |
      | 10  | Low Top Sneakers Black     | Low Top Sneakers Black     | test |
      | 11  | Slip On Shoes              | Slip On Shoes              | test |
      | 12  | YZY Shoes                  | YZY Shoes                  | test |
      | 13  | Leather Boots New          | Leather Boots New          | test |
      | 14  | Low Top Shoes              | Low Top Shoes              | test |


