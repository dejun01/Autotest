Feature:Check water mask of Shopbase
#   sbase_water_mask
  #pro:au-sbase-water-mark.onshopbase.com
  Scenario: Delete all product live
    When  Delete all products by API

  Scenario Outline:Check water mask when create campaign
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Watermark" screen
    And setting water mask as "<KEY>"
      | KEY | Type                           | Style         | Watermark |
      | 01  |                                |               | false     |
      | 02  | text>pbase_water_mask          | Spatial Mark  | true      |
      | 03  | image>/phub/watermask/logo.png | Symbol Strips | true      |
    And wait 5 second
    And user navigate to "Products" screen
    And Add a new product with data
      | Title         | Description                       | Image                |
      | @NameProduct@ | Made from 100% heavyweight cotton | \phub\big_size02.jpg |

    And user navigate to "Products" screen
    When Search product "@NameProduct@" on All product screen
    And Open detail product of product "@NameProduct@"
    And open product details on Storefront from product detail in dashboard
    Then verify image product page "<KEY>"
      | KEY | Image expected                           | Image actual |
      | 01  | /phub/watermask/po_No_water_mark.png     | Watermark    |
      | 02  | /phub/watermask/po_SpatialMark.png       | Watermark    |
      | 03  | /phub/watermask/po_ImageSymbolStrips.png | Watermark    |
    And quit browser
    Examples:
      | KEY | Testcase    |
#      | 01  | NoMark            |
      | 02  | SpatialMark |
#      | 03  | ImageSymbolStrips |
