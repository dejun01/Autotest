@dashboardProduct @dashboard
Feature: Mapping product
#env: pbase_mapping_products

  Background: Login dashboard
    Given user login to shopbase dashboard by API
    Given Delete all products by API
    And user navigate to "Products>All products" screen

  @dashboardProductMapping
  Scenario: Add new product mapping without option #SB_PRH_MP_88 #SB_PRH_MP_89
    And Add a new product with data
      | Title           | Description                                                            |
      | product mapping | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And Click map product in PrintHub and verify block option
    And quit browser


  @dashboardProductMapping
  Scenario: Add new product mapping with one option #SB_PRH_MP_90
    Given Add a new product with data
      | Title           | Description                                                            |
      | product mapping | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    Then Add a new product variant with data
      | Option set | Option value | Created variant status |
      | Color      | Red          | enable                 |
    And Click map product in PrintHub and verify define option
      | What we offer | We should map with |
      | Base product  | Default option     |
      | Color         | Color              |
      | Size          | Default option     |
    And quit browser


  @dashboardProductMapping
  Scenario: Add new product mapping with two option #SB_PRH_MP_91
    Given Add a new product with data
      | Title           | Description                                                            |
      | product mapping | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Color      | Red          |
      | Size       | M            |
    And Click map product in PrintHub and verify define option
      | What we offer | We should map with |
      | Base product  | Default option     |
      | Color         | Color              |
      | Size          | Size               |
    And quit browser


  @dashboardProductMapping
  Scenario: Add new product mapping with three option #SB_PRH_MP_92 #SB_PRH_MP_93 #SB_PRH_MP_94 #SB_PRH_MP_95 #SB_PRH_MP_96 #SB_PRH_MP_97 #SB_PRH_MP_98 #SB_PRH_MP_99 #SB_PRH_MP_100 #SB_PRH_MP_101 #SB_PRH_MP_102 #SB_PRH_MP_103 #SB_PRH_MP_104 #SB_PRH_MP_105 #SB_PRH_MP_106 #SB_PRH_MP_107 #SB_PRH_MP_107 #SB_PRH_MP_109 #SB_PRH_MP_110
    Given Add a new product with data
      | Title           | Description                                                            |
      | product mapping | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Color      | Red          |
      | Size       | S,M,L,XL     |
      | Style      | Boy,Girl     |
    And Change define variant but have no save in PrintHub
      | What we offer | We should map with |
      | Base product  | Color              |
      | Color         | Size               |
    And Click map product in PrintHub and verify define option
      | What we offer | We should map with |
      | Base product  | Style              |
      | Color         | Color              |
      | Size          | Size               |
    And Upload artwork with product mapping "Unisex Hoodie"
      | Front or back     | Name image                 | Error message                    | Element visible |
      | add-artwork-front | phub/image/120MB.png       | Your artwork is too large.       | false           |
      | add-artwork-back  | image_dpi_lesser_150.png   | Min dimensions: 2100px x 2400px. | false           |
      | add-artwork-front | Logo1.jpg                  | We only support .PNG file.       | false           |
      | add-artwork-front | file_artwork_correctly.png |                                  | true            |
    Then Verify auto map option
      | Option value | Option value map |
      | Red          | Red              |
      | S            | S                |
      | M            | M                |
      | L            | L                |
      | XL           | XL               |
    And Verify varriants
      | Name product    | Total variants | Total Product  | Option value | Total variants mapped         |
      | product mapping | 0/8 variants   | Products (0/2) | Boy          | 0/4 available mapped variants |
      |                 |                |                | Girl         | 0/4 available mapped variants |
    Then Mapping product in order with base product "Unisex Hoodie"
      | Front or back     | Name image                 |
      | add-artwork-front | file_artwork_correctly.png |
    Then Verify auto map option
      | Option value | Option value map |
      | Red          | Red              |
      | S            | S                |
      | M            | M                |
      | L            | L                |
      | XL           | XL               |
    And Verify total available and unvailable
      | Tab         | Value           | Message                                  |
      | Available   | Available (0)   | There are no mapped available variants   |
      | Unavailable | Unavailable (0) | There are no mapped unavailable variants |
    And quit browser


  @dashboardProductMapping
  Scenario: Add new product mapping and verify count option checkbox #SB_PRH_MP_111 #SB_PRH_MP_113 #SB_PRH_MP_115 #SB_PRH_MP_108
    Given Add a new product with data
      | Title        | Description                                                            |
      | product test | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Color      | Red          |
      | Size       | S,M,L,XL     |
      | Style      | Boy,Girl     |
    And click mapping product in PrintHub
    Then Mapping product in order with base product "Beverage Mug"
      | Front or back     | Name image                 |
      | add-artwork-front | file_artwork_correctly.png |
    And Select all option value
      | Option value |
      | Red          |
      | S            |
      | M            |
      | L            |
      | XL           |
    And Verify total available and unvailable
      | Tab         | Value           | Message                                |
      | Available   | Available (0)   | There are no mapped available variants |
      | Unavailable | Unavailable (4) |                                        |
    Then Change value option
      | Option value | Change option mapped |
      | Red          | white                |
      | S            | 11oz                 |
      | M            | 11oz                 |
      | L            | 11oz                 |
      | XL           | 11oz                 |
    And Verify total available and unvailable
      | Tab         | Value           | Message                                  |
      | Available   | Available (4)   |                                          |
      | Unavailable | Unavailable (0) | There are no mapped unavailable variants |
    And Verify varriants
      | Name product | Total variants | Total Product  | Option value | Total variants mapped         |
      | product test | 4/8 variants   | Products (1/2) | Boy          | 4/4 available mapped variants |
      |              |                |                | Girl         | 0/4 available mapped variants |
    And Verify count total product mapped
      | Name product | Option value | Total variants mapped                                       |
      | product test | Boy,Girl     | 4/4 available mapped variants,0/4 available mapped variants |
    And user navigate to "Products" screen
    And user navigate to "Products>All products" screen
    When Search product "product test" on All product screen
    Then verify the product "product test" has been mapped
    Given open shop on storefront
    Then checkout successfully via stripe with cart "product test"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then redirect to shopbase dashboard
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab              | Variants     | SKU                               | Cost     | Status           | Product cost | Shipping |
      | Awaiting Payment | white / 11oz | SKU: PH-AP-BeverageMug-white-11oz | 4.49 x 1 | Awaiting Payment | 4.49         | 6.49     |
    And quit browser


  Scenario: Mapping product and verify order after map with base 2 sided #SB_PRH_MP_112 #SB_PRH_MP_114 #SB_PRH_MP_116
    Given Add a new product with data
      | Title          | Description                                                            |
      | product Hoodie | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And Add a new product variant with multi option set
      | Option set | Option value |
      | Color      | Red          |
      | Size       | S,M,L,XL     |
      | Style      | Boy,Girl     |
    And click mapping product in PrintHub
    Then Mapping product in order with base product "Unisex Hoodie"
      | Front or back     | Name image                 |
      | add-artwork-front | file_artwork_correctly.png |
      | add-artwork-back  | file_artwork_correctly.png |
    And Select all option value
      | Option value |
      | Red          |
      | S            |
      | M            |
      | L            |
      | XL           |
    Then Change value option
      | Option value | Change option mapped |
      | Red          | White                |
      | S            | S                    |
      | M            | M                    |
      | L            | L                    |
      | XL           | XL                   |
    Given open shop on storefront
    Then checkout successfully via stripe with cart "product Hoodie"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Then redirect to shopbase dashboard
    And user navigate to "Apps" screen
    Then Select app "Print Hub" on Apps list
    And user navigate to "Manage Orders" screen
    Then verify order detail after mapped or charge
      | Tab              | Variants  | SKU                             | Cost      | Status           | Product cost | Shipping |
      | Awaiting Payment | White / S | SKU: PH-AP-UnisexHoodie-White-S | 14.49 x 1 | Awaiting Payment | 14.49        | 6.99     |
    And quit browser
