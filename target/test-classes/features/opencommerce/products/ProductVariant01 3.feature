@dashboardProduct @dashboard
Feature: Product Variant 01
#sbase_dashboard_product

  Background: Login dashboard
    Given clear all data
    Given user login to shopbase dashboard by API

  @dashboardProductAddVariantForm
  Scenario: Add new variant on form #SB_PRO_SP_87 #SB_PRO_SP_88 #SB_PRO_PV_2 #SB_PRO_PV_3
    Given user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value   | Created variant status |
      |            |                | unable                 |
      |            | Red A, Green A | unable                 |
      | Color      |                | unable                 |
      | Color      | Red A, Green A | enable                 |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Red A   | 10    | A123456B | 100       |
      | Green A | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label  | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Color: Red A   | Red A   | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Color: Green A | Green A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option  | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Red A   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Green A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardProductAddNewOtherVariant
  Scenario: Add new other variant #SB_PRO_SP_100 #SB_PRO_PV_4 #SB_PRO_PV_5 #SB_PRO_PV_6
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Product type | Vendor | Tags | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value   | Created variant status |
      | Color      | Red A, Green A | enable                 |
    Then Add an other product variant with data
      | Option | Save ability |
      | Blue A | able         |
      |        | unable       |
    Then user navigate to "Products>All products" screen
    When Search product "@NameProduct@" on All product screen
    And Open detail product of product "@NameProduct@"
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Red A   | 10    | A123456B | 100       |
      | Green A | 10    | A123456B | 100       |
      | Blue A  | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label  | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags |
      | Color: Red A   | Red A   | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
      | Color: Green A | Green A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
      | Color: Blue A  | Blue A  | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
    And Switch to the first tab
    And Information of "3" created variants display correctly on product variant detail page
      | Option  | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Red A   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Green A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Blue A  | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardProductEditVariant
  Scenario: Edit variant #SB_PRO_PV_12 #SB_PRO_PV_13 #SB_PRO_PV_14 #SB_PRO_PV_15 #SB_PRO_PV_245 #SB_PRO_PV_246 #SB_PRB_VGM_345 #SB_PRB_VGM_346 #SB_PRB_VGM_347 #SB_PRB_VGM_348 #SB_PRB_VGM_352 #SB_PRB_VGM_357 #SB_PRB_VGM_361 #SB_PRB_VGM_364 #SB_PRB_VGM_365 #SB_PRB_VGM_367 #SB_PRB_VGM_369 #SB_PRB_VGM_372 #SB_PRB_VGM_376 #SB_PRB_VGM_379 #SB_PRB_VGM_380 #SB_PRB_VGM_383 #SB_PRB_VGM_386 #SB_PRB_VGM_389 #SB_PRB_VGM_393 #SB_PRB_VGM_396
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | false             | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value           | Created variant status |
      | Color      | Red A, Green A, Blue A | enable                 |
    And Edit information of created variants with data
      | Old option | Option        | Image url                                                                                                 | Iamge           | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Red A      | Red Updated   | https://www.youtube.com/watch?v=_s9Ir-5RHs4&ab_channel=Thi%C3%AAnnhi%C3%AAnt%C6%B0%C6%A1i%C4%91%E1%BA%B9p |                 | 12    | 22               | 7             | A123456B | B654321A | 100      | 50     |
      | Green A    | Green Updated |                                                                                                           | Video_chuan.mp4 | 11    | 21               | 6             | A123456B | B654321A | 100      | 50     |
      | Blue A     | Blue Updated  | https://24hstore.vn/upload_images/images/2019/11/14/anh-gif-2-min.gif                                     |                 | 13    | 23               | 8             | A123456B | B654321A | 0        | 50     |
    Then Verify all product variants on storefront
      | Variant Label        | Variant       | Total media | Show  | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Color: Red Updated   | Red Updated   | 4           | true  | $12.00 USD | $22.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Color: Green Updated | Green Updated | 4           | true  | $11.00 USD | $21.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      |                      | Blue Updated  | 4           | false |            |                  |                                                                        |        |          |              |                  |
    And Switch to the first tab
    And Information of "3" created variants display correctly on product variant detail page
      | Option        | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Red Updated   | 12    | 22               | 7             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | No                | 50     | lb          |
      | Green Updated | 11    | 21               | 6             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | No                | 50     | lb          |
      | Blue Updated  | 13    | 23               | 8             | A123456B | B654321A | ShopBase tracks this product's inventory | 0        | No                | 50     | lb          |
    And quit browser


  @dashboardProductDeleteVariant
  Scenario: Delete variant #SB_PRO_SP_89 #SB_PRO_PV_19
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value           | Created variant status |
      | Color      | Red A, Green A, Blue A | enable                 |
    And Delete variant with data
      | Option  |
      | Green A |
    Then user navigate to "Products>All products" screen
    When Search product "@NameProduct@" on All product screen
    And Open detail product of product "@NameProduct@"
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Red A   | 10    | A123456B | 100       |
      | Blue A  | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Color: Red A  | Red A   | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Color: Blue A | Blue A  | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    Then Verify variant has been deleted on storefront
      | Variant |
      | Green A |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Red A  | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Blue A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardProductReorderVariant
  Scenario: reorder variant #SB_PRO_PV_25 #SB_PRO_PV_27 #SB_PRO_PV_29 #SB_PRO_PV_23
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Image     | Price | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Logo1.jpg | 10    | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | S, M, L, XL  |
    Then Duplicate variant with 1 option set
      | Variant duplicate | Option set | New option value |
      | XL                | Size       | XXL              |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | S       | 10    | A123456B | 100       |
      | M       | 10    | A123456B | 100       |
      | L       | 10    | A123456B | 100       |
      | XL      | 10    | A123456B | 100       |
      | XXL     | 10    | A123456B | 100       |
    Then verify group variant display
      | Check group |
      | true        |
      | false       |


  @dashboardProductDeleteVariant
  Scenario: Check limit variant #SB_PRO_PD_43 #SB_PRO_PD_44
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title              |
      | Test limit variant |
    Then Add a new product variant with multi option set
      | Option set | Option value                                                           | Message                                                                               |
      | Color      | Red, Green, Blue, Lavender, Black, Salmon, Indigo, Orange, Coral, Gold |                                                                                       |
      | Size       | 1, 2, 3, 4, 5, 6, 7, 8, 9, 10                                          |                                                                                       |
      | Style      | A, B, C, D, E, F                                                       | You can't have more than 100 variants. Please ensure that total variant is under 100. |