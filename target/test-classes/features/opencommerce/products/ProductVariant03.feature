@dashboardProduct @dashboard
Feature: Product Variant 03

  Background: Login dashboard
    Given clear all data
    Given user login to shopbase dashboard by API

  @dashboardAddOptions
  Scenario: Add options #SB_PRO_SP_99 #SB_PRO_PV_24 #SB_PRO_PV_6 #SB_PRO_PV_5 #SB_PRO_PV_4
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value | Created variant status |
      | Size       | S, M         | enable                 |
    When Add another option with data
      | Option set | Option value | Message                         |
      | Color      | Red A        | Your options have been updated! |
    And Information of created variants display correctly on product detail page
      | Variant  | Price | SKU      | Inventory |
      | S, Red A | 10    | A123456B | 100       |
      | M, Red A | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: S       | S       | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: M       | M       | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option   | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | S; Red A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Red A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardEditOptions
  Scenario: Edit options #SB_PRO_PV_9
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | S, M         |
      | Color      | Red A        |
    When Edit option with data
      | Old option set | New option set | Message                         |
      | Color          | Colours        | Your options have been updated! |
    And Information of created variants display correctly on product detail page
      | Variant  | Price | SKU      | Inventory |
      | S, Red A | 10    | A123456B | 100       |
      | M, Red A | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: S       | S       | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: M       | M       | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option   | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | S; Red A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Red A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardDeleteOptions1OptionValue
  Scenario: Delete option with 1 option value #SB_PRO_PV_11 #SB_PRO_PV_10
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | S, M         |
      | Color      | Red A        |
    And Delete option with data
      | Option set | Option value | Message |
      | Color      | Red A        |         |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | S       | 10    | A123456B | 100       |
      | M       | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: S       | S       | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: M       | M       | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | S      | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M      | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardDeleteOptions2OptionValue
  Scenario: Delete option with 2 option value #SB_PRO_PV_11 #SB_PRO_PV_10
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with multi option set
      | Option set | Option value   |
      | Size       | S, M           |
      | Color      | Red A, Green A |
    And Delete option with data
      | Option set | Option value |
      | Color      | Green A      |
    And Information of created variants display correctly on product detail page
      | Variant  | Price | SKU      | Inventory |
      | S, Red A | 10    | A123456B | 100       |
      | M, Red A | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label         | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: S; Color: Red A | S,Red A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: M; Color: Red A | M,Red A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option   | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | S; Red A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Red A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And  quit browser
