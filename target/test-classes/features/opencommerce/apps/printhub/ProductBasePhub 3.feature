Feature:Verify Product base in phub
# staging_print_hub_product_base_new
# Ladies T-shirt : (Basecost>5.99) ( Shipping US >4.99-1.99 ) (Shipping In>1.99 )


  @DashboardCatalogAndShipping
  Scenario Outline: Check show price with shipping of Campaign on Print Base sdfdsfd
    Given user login to shopbase dashboard
    And clear all data
    Then  verify the productbase in catalog as "<KEY>"
      | KEY | Catalog   | Product base | Target BaseCost   | Shipping address | Shipping cost | Price shipping | Processing Time |
      | 1   | <Catalog> | <Product>    | <Target_BaseCost> |                  | 0             | false          | <Processing>    |
      | 1   | <Catalog> | <Product>    |                   | US               | <Ship_US>     | true           |                 |
      | 1   | <Catalog> | <Product>    |                   | International    | <Ship_In>     | true           |                 |
    When add products to campaign as "<KEY>"
      | KEY | Product   | Category  |
      | 1   | <Product> | <Catalog> |
    And input data to create campaign as "<KEY>"
      | KEY | Product   | Artwork   |
      | 1   | <Product> | Bulk3.png |
    And click to tab "Description" in Campaign
    And input data to create description for campaign as "<KEY>"
      | KEY | Title           | Description | Is include product details | Tags   |
      | 1   | <Campaign name> | 2D          | false                      | ladies |
    And click to tab "Pricing" in Campaign
    And input product price for campaign as "<KEY>"
      | KEY | Product   | Variant          | Sale price | Compare at price |
      | 1   | <Product> | <Color> - <Size> | 8          | 10               |
    And click to button "Launch"
    And search campaign in dashboard with name "<Campaign name>"
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name   | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | <Campaign name> | LIVE   | true                | true                     |
    When open product details in dashboard or editor campaign as "<Product>"
    Then verify product information in dashboard as "<KEY>"
      | KEY | SKU   | Tags   | Is enable duplicate | Is enable bulk duplicate | Description |
      | 1   | <SKU> | ladies | true                | true                     | 2D          |
    Examples:
      | KEY | Catalog       | Campaign name       | Product             | Color          | Size         | SKU                                                | Target_BaseCost | Ship_US | Ship_In | Processing |
      | 1   | Home & Living | Portrait House Flag | Portrait House Flag | All over print | 12.5x18 inch | PH-AOP-LandscapeHouseFlag-Alloverprint-12.5x18inch | 5.99            | 2.99    | 4.99    | 5 ~ 7 Days |

  @DashboardCShipping
  Scenario Outline: Check show price with shipping of Campaign on Print Base
    Given Description: "<TC>"
    Given open shop on storefront
    And add products to cart then checkout as "<KEY>"
      | KEY | Product                |
      | 1   | <Campaign name>:<Size> |
      | 2   | <Campaign name>:<Size> |
      | 3   | <Campaign name>:<Size> |
      | 4   | <Campaign name>:<Size> |
      | 5   | <Campaign name>:<Size> |
      | 6   | <Campaign name>:<Size> |
      | 7   | <Campaign name>:<Size> |
      | 8   | <Campaign name>:<Size> |
    When input Customer information as "<KEY>"
      | KEY | Email                   | First Name | Last Name | Address           | Country       | City     | Zip code | State     | Phone      |
      | 1   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Metairie | 70001    | Louisiana | 2025550147 |
      | 2   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Vietnam       | Metairie | 11111    |           | 2025550147 |
      | 3   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Canada        | Alberta  | 70001    | Alberta   | 2025550147 |
      | 4   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Australia     | Victoria | 70001    | Victoria  | 2025550147 |
      | 5   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Metairie | 70001    | Louisiana | 2025550147 |
      | 6   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Vietnam       | Metairie | 11111    |           | 2025550147 |
      | 7   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Canada        | Alberta  | 70001    | Alberta   | 2025550147 |
      | 8   | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Australia     | Victoria | 70001    | Victoria  | 2025550147 |

    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And  get all information order
    And verify information in page thank
      | Shipping |
      | <Ship>   |
#    Then user login to shopbase dashboard
#    And user navigate to "Apps" screen
#    And select app "Print Hub" on Apps list
#    And user navigate to "Manage Orders" screen
#    And switch to "Awaiting Payment" tab on Manage Orders of Print Hub
#    And search order Print Hub
#    And open order detail on admin shop
#    And verify information lineitems of orders with status "Awaiting Payment" Print Hub as "<KEY>"
#      | KEY | Status           | Product name    | Lineitems | SKU   | Is sync |
#      | 1   | Awaiting Payment | <Campaign name> |           | <SKU> | true    |
#    And verify information order in Print Hub as "<KEY>"
#      | KEY | Product cost      | Shipping price |
#      | 1   | <Target_BaseCost> | <Ship>         |
#      | 2   | <Target_BaseCost> | <Ship>         |
#      | 3   | <Target_BaseCost> | <Ship>         |
#      | 4   | <Target_BaseCost> | <Ship>         |
    And quit browser
    Examples:
      | KEY | TC        | Campaign name  | Product              | Color          | Size   | SKU                                   | Target_BaseCost | Ship   |
      | 1   | US        | Star Ornament| Landscape House Flag | All over print | 1 pcs  | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $4.99  |
      | 2   | Vietnam   | Star Ornament| Landscape House Flag | All over print | 1 pcs  | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $6.99  |
      | 3   | Canada    |Star Ornament| Landscape House Flag | All over print | 1 pcs  | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $7.99  |
      | 4   | Australia |Star Ornament | Landscape House Flag | All over print | 1 pcs  | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $11.99 |
      | 5   | US        | Medium Sticker | Landscape House Flag | All over print | 10 pcs | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $6.99  |
      | 6   | Vietnam   | Medium Sticker | Landscape House Flag | All over print | 10 pcs | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $8.99  |
      | 7   | Canada    | Medium Sticker | Landscape House Flag | All over print | 10 pcs | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $9.99  |
      | 8   | Australia | Medium Sticker | Landscape House Flag | All over print | 10 pcs | PH-AOP-LargeSticker-Alloverprint-1pcs | 5.99            | $13.99 |


  @dashboardProductMapping
  Scenario Outline:: Add new product mapping and verify count option checkbox
    Given clear all data
    And user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title             | Message                           |
      | @product mapping@ | Product was created successfully! |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Color      | Red          |
      | Size       | S,M,L,XL     |
      | Style      | Boy,Girl     |
    And click mapping product
    And Upload artwork with product mapping "<Product> $<Target_BaseCost>"
      | Front or back     | Name image | Error message | Element visible |
      | add-artwork-front | Bulk3.png  |               | true            |
    And Select all option value
      | Option value |
      | Red          |
      | S            |
    Then Change value option
      | Option value | Change option mapped |
      | Red          | <Color>              |
      | S            | <Size>               |
    And Verify total available and unvailable
      | Tab         | Value           | Message                                  |
      | Available   | Available (1)   |                                          |
      | Unavailable | Unavailable (0) | There are no mapped unavailable variants |
    And verify variant after map
      | Option value | Option value map |
      | Red          | <Color>          |
      | S            | <Size>           |
    And Verify varriants
      | Name product      | Total variants | Total Product  | Option value | Total variants mapped         |
      | @product mapping@ | 1/8 variants   | Products (1/2) | Boy          | 1/4 available mapped variants |
      |                   |                |                | Girl         | 0/4 available mapped variants |
    And Verify count total product mapped
      | Name product      | Option value | Total variants mapped                                       |
      | @product mapping@ | Boy,Girl     | 1/4 available mapped variants,0/4 available mapped variants |
    And user navigate to "Products>All products" screen
    When Search product "@product mapping@" on All product screen
    Then verify the product "@product mapping@" has been mapped
    Examples:
      | Product          | Color          | Size | Target_BaseCost |
      | Landscape Poster | All over print | S    | 4.49            |

  @dashboardOrderMapping
  Scenario Outline: Create new order mapping product 1213
    Given clear all data
    And user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title          | Price | Message                           |
      | @product_test@ | 10.1  | Product was created successfully! |
    Given open shop on storefront
    And add products "@product test@" to cart then checkout
    When input Customer information
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 |
    And choose shipping method ""
    And input payment information and complete order
      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And  get all information order
    Then user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    And search order Print Hub
    And Mapping product in order
    Then Mapping product in order with base product "<Product> $<Target_BaseCost>"
      | Front or back     | Name image |
      | add-artwork-front | Bulk3.png  |
    And Select all option value
      | Option value  |
      | Default color |
      | Default size  |
    Then Change value option
      | Option value  | Change option mapped |
      | Default color | <Color>              |
      | Default size  | <Size>               |
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab               | Variants         | SKU        | Cost   | Status            | Product cost   | Shipping   | Total Cost   |
      | Awaiting Shipment | <Color> / <Size> | SKU: <SKU> | <Cost> | Awaiting Shipment | <Product_cost> | <Shipping> | <Total_cost> |
    And wait 20 second
    And call api to create next payment or charge payment Print Hub
    And wait 10 second
    And call api to create next payment or charge payment Print Hub
    Then verify order detail after mapped or charge
      | Tab               | Variants         | SKU        | Cost   | Status            | Product cost   | Shipping   | Total Cost   |
      | Awaiting Shipment | <Color> / <Size> | SKU: <SKU> | <Cost> | Awaiting Shipment | <Product_cost> | <Shipping> | <Total_cost> |
    Examples:
      | Product          | SKU                                  | Color          | Size | Target_BaseCost | Shipping | Cost       | Product_cost | Total_cost |
      | Landscape Poster | PB-AOP-PortraitPoster-Alloverprint-S | All over print | S    | 4.49            | $6.99    | $11.48 x 1 | $4.49        | $16.48     |


