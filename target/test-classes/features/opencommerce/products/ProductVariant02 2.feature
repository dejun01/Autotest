@dashboardProduct @dashboard
Feature: Product Variant 02

  Background: Login dashboard
    Given clear all data
    Given user login to shopbase dashboard by API

  @dashboardDuplicateVariantWith1OptionSet
  Scenario: Duplicate variant with 1 option set #SB_PRO_PV_22 #SB_PRO_PV_30
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value   | Created variant status |
      | Color      | Red A, Green A | enable                 |
    Then Duplicate variant with 1 option set
      | Variant duplicate | Option set | New option value |
      | Red A             | Color      | Red A            |
      | Red A             | Color      | Blue A           |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Red A   | 10    | A123456B | 100       |
      | Blue A  | 10    | A123456B | 100       |
      | Green A | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label  | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Color: Red A   | Red A   | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Color: Green A | Green A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Color: Blue A  | Blue A  | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "3" created variants display correctly on product variant detail page
      | Option  | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Red A   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Blue A  | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Green A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardDuplicateVariantWith2OptionSet
  Scenario: Duplicate variant with 2 option set #SB_PRO_PV_23
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Description                                                            | Product type | Vendor | Tags | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with multi option set
      | Option set | Option value   |
      | Size       | S, M           |
      | Color      | Red A, Green A |
    Then Duplicate variant with multi option set
      | Variant duplicate 1 | Variant duplicate 2 | Option set | Option value new | Message                                  |
      | S, Red A            |                     | Size       | L                | Duplicating variants in Size L completed |
      | S, Red A            | S, Green A          | Size       | S                | Failed duplicating 2 variant             |
      | S, Red A            | L, Red A            | Color      | Green A          | Failed duplicating 1 variant             |
    And Information of created variants display correctly on product detail page
      | Variant    | Price | SKU      | Inventory |
      | S, Red A   | 10    | A123456B | 100       |
      | S, Green A | 10    | A123456B | 100       |
      | M, Red A   | 10    | A123456B | 100       |
      | M, Green A | 10    | A123456B | 100       |
      | L, Red A   | 10    | A123456B | 100       |
      | L, Green A | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label           | Variant   | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags |
      | Size: S; Color: Red A   | S,Red A   | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: S; Color: Green A | S,Green A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: M; Color: Red A   | M,Red A   | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: M; Color: Green A | M,Green A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: L; Color: Red A   | L,Red A   | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: L; Color: Green A | L,Green A | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1 |
    And Switch to the first tab
    And Information of "6" created variants display correctly on product variant detail page
      | Option     | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | S; Red A   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | S; Green A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Red A   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Green A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | L; Red A   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | L; Green A | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  @dashboardDuplicateVariantWith3OptionSet
  Scenario: Duplicate variant with 3 option set #SB_PRO_PV_21 #SB_PRO_PV_7
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title         | Product type | Vendor | Tags | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Shirt        | Uniqlo | tag1 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with multi option set
      | Option set | Option value   |
      | Size       | S, M           |
      | Color      | Red A, Green A |
      | Material   | Cotton         |
    Then Duplicate variant with multi option set
      | Variant duplicate 1 | Variant duplicate 2 | Variant duplicate 3 | Variant duplicate 4 | Option set | Option value new | Message                                              |
      | S, Red A, Cotton    | S, Green A, Cotton  | M, Red A, Cotton    | M, Green A, Cotton  | Material   | Polyester        | Duplicating variants in Material Polyester completed |
    And Information of created variants display correctly on product detail page
      | Variant               | Price | SKU      | Inventory |
      | S, Red A, Cotton      | 10    | A123456B | 100       |
      | S, Green A, Cotton    | 10    | A123456B | 100       |
      | M, Red A, Cotton      | 10    | A123456B | 100       |
      | M, Green A, Cotton    | 10    | A123456B | 100       |
      | S, Red A, Polyester   | 10    | A123456B | 100       |
      | S, Green A, Polyester | 10    | A123456B | 100       |
      | M, Red A, Polyester   | 10    | A123456B | 100       |
      | M, Green A, Polyester | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label                                | Variant             | Price      | Compare at price | Vendor | SKU      | Product type | Tags |
      | Size: S; Color: Red A; Material: Cotton      | S,Red A,Cotton      | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: S; Color: Green A; Material: Cotton    | S,Green A,Cotton    | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: M; Color: Red A; Material: Cotton      | M,Red A,Cotton      | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: M; Color: Green A; Material: Cotton    | M,Green A,Cotton    | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: S; Color: Red A; Material: Polyester   | S,Red A,Polyester   | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: S; Color: Green A; Material: Polyester | S,Green A,Polyester | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: M; Color: Red A; Material: Polyester   | M,Red A,Polyester   | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
      | Size: M; Color: Green A; Material: Polyester | M,Green A,Polyester | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1 |
    And Switch to the first tab
    And Information of "8" created variants display correctly on product variant detail page
      | Option                | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | S; Red A; Cotton      | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | S; Green A; Cotton    | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Red A; Cotton      | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Green A; Cotton    | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | S; Red A; Polyester   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | S; Green A; Polyester | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Red A; Polyester   | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | M; Green A; Polyester | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
