Feature: Bulk update 04
#env: sbase_dashboard_product_bulk_update_04
  Background: Navigate to "Products>All products" screen
    Given clear all data
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen

  Scenario: Case 05 - All conditions - Product price - is greater than "50" - Add tags "tag3" #SB_PRO_BU_27
    Given Delete all products
    And Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate05_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 51    | 100              | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate05_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator      | Value |
      | Product price | is greater than | 50    |
    And Execute an action bulk update for product "Add tags"
      | Value |
      | tag3  |
    And quit browser


  Scenario: Verify Case 5 #SB_PRO_BU_28
    When Search product "BulkUpdate05_01" on All product screen
    And Open detail product of product "BulkUpdate05_01"
    And Information of created product "BulkUpdate05_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 51    | 100              | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate05_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $51.00 USD | $100.00 USD      | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate05_02" on All product screen
    And Open detail product of product "BulkUpdate05_02"
    And Information of created product "BulkUpdate05_02" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate05_02" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And quit browser


  Scenario: Case 07 - All conditions - Weight - is equal to "1100" - Change price to "85" #SB_PRO_BU_25
    Given Delete all products
    Given Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate07_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 1000   |
      | BulkUpdate07_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 100              | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 1100   |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field  | Comparator  | Value |
      | Weight | is equal to | 1100  |
    And Execute an action bulk update for product "Change price to"
      | Value |
      | 85    |
    And quit browser


  Scenario: Verify Case 7 #SB_PRO_BU_26
    When Search product "BulkUpdate07_01" on All product screen
    And Open detail product of product "BulkUpdate07_01"
    And Information of created product "BulkUpdate07_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 1000   |
    Then Verify this product "BulkUpdate07_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate07_02" on All product screen
    And Open detail product of product "BulkUpdate07_02"
    And Information of created product "BulkUpdate07_02" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 85    | 100              | 5             | A123456B | B654321A | 100      | 1100   |
    Then Verify this product "BulkUpdate07_02" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $85.00 USD | $100.00 USD      | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And quit browser


  Scenario: Case 12 - All conditions - Product title - contains "Dress" - Only update some variants - Variant compare-at-price - is equal to "100.5" - Round/ Beautify price "Lower 0.49" #SB_PRO_BU_23
    Given Delete all products
    Given Add a new product with data
      | Title              | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate12_01    | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate12_Dress | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value       | Created variant status |
      | Size       | Small, Medium, Big | enable                 |
    And Edit information of created variants with data
      | Old option | Price | Compare at price | Cost per item |
      | Small      | 23.39 | 100.5            | 15            |
      | Big        | 23.89 | 100.5            | 15            |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value |
      | Product title | contains   | Dress |
    And Create a bulk update with variant condition is "All conditions"
      | Field                    | Comparator  | Value |
      | Variant compare-at-price | is equal to | 100.5 |
    And Execute an action bulk update for some variants "Round/ Beautify price"
      | Value      |
      | Lower 0.49 |
    And quit browser


  Scenario: Verify Case 12 #SB_PRO_BU_24
    When Search product "BulkUpdate12_01" on All product screen
    And Open detail product of product "BulkUpdate12_01"
    And Information of created product "BulkUpdate12_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate12_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate12_Dress" on All product screen
    And Open detail product of product "BulkUpdate12_Dress"
    And Information of created product "BulkUpdate12_Dress" display correctly
      | Description                                                            | Product type | Vendor | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Small   | 22.49 | A123456B | 100       |
      | Medium  | 10    | A123456B | 100       |
      | Big     | 23.49 | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: Small   | Small   | $22.49 USD | $100.50 USD      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: Medium  | Medium  | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: Big     | Big     | $23.49 USD | $100.50 USD      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "3" created variants display correctly on product variant detail page
      | Option set | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Weight | Weight unit |
      | Size       | Small  | 22.49 | 100.5            | 15            | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | lb          |
      | Size       | Medium | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | lb          |
      | Size       | Big    | 23.49 | 100.5            | 15            | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | lb          |
