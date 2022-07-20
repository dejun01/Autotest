Feature:Check water mask of Prinbase when duplicate and Bulk duplicate from campaign no water mark
#   pbase_water_mask_01
  #au-sbase-water-mask.onshopbase.com

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline:  Precondition >> Create campaign
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Watermark" screen
    And setting water mask as "<KEY>"
      | KEY | Watermark |
      | 01  | false     |
    And wait 5 second
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank Auto | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Front or back |
      | 01  |Unisex Tank Auto| Text       | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title      |
      | <Campaign> |
    And click to button Launch campaign
    And quit browser
    Examples:
      | KEY | Campaign        |
      | 01  | Campaign origin |

  Scenario Outline: Bulk duplicate when change watermark of campaign origin #SB_PRB_WTM_50
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Watermark" screen
    And setting water mask as "<KEY>"
      | KEY | Type                           | Watermark |
      | 01  | image>/phub/watermask/logo.png | true      |
      | 02  | text>Test water mark           | true      |
    And wait 5 second
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name     | Artwork   |
      | 01  | <Campaign origin> | BD_36.png |
      | 02  | <Campaign origin> | BD_8.png  |
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY   | Campaign name   |
      | <KEY> | <Campaign name> |
    And open product details on Storefront from product detail in dashboard
    Then verify image product page "<KEY>"
      | KEY | Image expected                                                               | Image actual |
      | 01  | /phub/watermask/Pro_BulkFromImage.png;/phub/watermask/Stag_BulkFromImage.png | Watermark    |
      | 02  | /phub/watermask/Pro_BulkFromText.png;/phub/watermask/Stag_BulkFromText.png   | Watermark    |
    And quit browser
    Examples:
      | KEY | Campaign origin   | Campaign name |
      | 01  | TextStrips        | BD_36         |
      | 02  | ImageSymbolStrips | BD_8          |

  Scenario Outline:   Duplicate when change watermark of campaign origin #SB_PRB_WTM_50
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Watermark" screen
    And setting water mask as "<KEY>"
      | KEY | Type                           | Watermark |
      | 01  | image>/phub/watermask/logo.png | true      |
      | 02  | text>Test water mark           | true      |
    And wait 5 second
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name   | Is keep artwork |
      | <Campaign origin> | <Campaign name> | false           |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name | Location | Size layer | Front or back |
      | 01  | Unisex Tank Auto | Image      | 39.png     | 212>326  | 1675>1675  | Front         |
      | 02  | Unisex Tank Auto | Image      | Image.jpg  | 0>500    | 2100>1399  | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And select product main image for campaign editor
      | Product base     | Select main color  |
      | Unisex Tank Auto | rgb(253, 253, 253) |

    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY   | Campaign name   |
      | <KEY> | <Campaign name> |
    And open product details on Storefront from product detail in dashboard
    Then verify image product page "<KEY>"
      | KEY | Image expected                                                                       | Image actual |
      | 01  | /phub/watermask/Pro_ImageSymbolStrips.png;/phub/watermask/Stag_ImageSymbolStrips.png | Watermark    |
      | 02  | /phub/watermask/Pro_TextStrips.png;/phub/watermask/Stag_TextStrips.png               | Watermark    |
    And quit browser
    Examples:
      | KEY | Campaign origin   | Campaign name           |
      | 01  | TextStrips        | DuplicateWaterMaskImage |
      | 02  | ImageSymbolStrips | DuplicateWaterMaskText  |