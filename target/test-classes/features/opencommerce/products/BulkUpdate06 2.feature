Feature: Bulk update 06
#env: sbase_dashboard_product_bulk_update_06
  Background: Navigate to "Products>All products" screen
    Given clear all data
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen

  Scenario: Case 15 - All conditions - variant's sku - not null - Find and replace text in description "T-shirt" to "hoodies"  #SB_PRO_BU_386 #SB_PRO_BU_396
    Given Delete all products
    Given Add a new product with data
      | Title        | Description     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | BulkUpdate15 | This is T-shirt | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator |
      | variant's sku | not null   |
    And Execute an action bulk update for product "Find and replace text in description"
      | Value   | Value update |
      | T-shirt | hoodies      |
    And quit browser


  Scenario: Verify Case 15
    When Search product "BulkUpdate15" on All product screen
    And Open detail product of product "BulkUpdate15"
    And Information of created product "BulkUpdate15" display correctly
      | Description     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | This is hoodies | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate15" on storefront
      | Description     | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | This is hoodies | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    Then Verify all product variants on storefront
      | Description     | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | This is hoodies | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |


  Scenario: Case 16 - All conditions - Variant's option value - conatains - Remove product type #SB_PRO_BU_392 #SB_PRO_BU_401 #SB_PRO_BU_402
    Given Delete all products
    Given Add a new product with data
      | Title        | Description     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | BulkUpdate16 | This is T-shirt | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value | Created variant status |
      | Size       | Small, Big   | enable                 |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field                  | Comparator | Value |
      | Variant's option value | contains   | Small |
    And Execute an action bulk update for product "Remove product type"
      | Value |
      |       |
    And quit browser


  Scenario: Verify Case 16
    When Search product "BulkUpdate16" on All product screen
    And Open detail product of product "BulkUpdate16"
    And Information of created product "BulkUpdate16" display correctly
      | Description     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | This is hoodies |              | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Small   | 10    | A123456B | 100       |
      | Big     | 20    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description     | Vendor | SKU      | Product type | Tags             |
      | Size: Small   | Small   | $10.00 USD | $20.00 USD       | This is hoodies | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: Big     | Big     | $20.00 USD | $20.00 USD       | This is hoodies | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "3" created variants display correctly on product variant detail page
      | Option set | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Weight | Weight unit |
      | Size       | Small  | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | lb          |
      | Size       | Big    | 20    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | lb          |
    And quit browser


  Scenario: Case 17 - All conditions - Product title - is equal to "BulkUpdate17" - Change product type to "Shirt Updated" #SB_PRO_BU_416 #SB_PRO_BU_378 #SB_PRO_BU_399 #SB_PRO_BU_400
    Given Delete all products
    Given Add a new product with data
      | Title        | Description     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | BulkUpdate17 | This is T-shirt | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator  | Value        |
      | Product title | is equal to | BulkUpdate17 |
    And Execute an action bulk update for product "Change product type"
      | Value | Value update  |
      | Shirt | Shirt Updated |
    And quit browser


  Scenario: Verify Case 17
    When Search product "BulkUpdate17" on All product screen
    And Open detail product of product "BulkUpdate17"
    And Information of created product "BulkUpdate17" display correctly
      | Description     | Product type  | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | This is hoodies | Shirt Updated | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate17" on storefront
      | Description     | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | This is hoodies | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    Then Verify all product variants on storefront
      | Description     | Price      | Compare at price | Vendor | SKU      | Product type  | Tags             |
      | This is hoodies | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt Updated | tag1, tag2, tag3 |


  Scenario: Case 18 - All conditions - Product title -is equal to "BulkUpdate18" - Delete variant's option value contains "Small" #SB_PRO_BU_403 #SB_PRO_BU_404	#SB_PRO_BU_418 #SB_PRO_BU_419
    Given Delete all products
    Given Add a new product with data
      | Title        | Description     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | BulkUpdate18 | This is T-shirt | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value | Created variant status |
      | Size       | Small, Big   | enable                 |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator  | Value        |
      | Product title | is equal to | BulkUpdate18 |
    And Execute an action bulk update for product "Delete variant's option value"
      | Value |
      | Small |
    And quit browser


  Scenario: Verify Case 18
    When Search product "BulkUpdate18" on All product screen
    And Open detail product of product "BulkUpdate18"
    And Information of created product "BulkUpdate08" display correctly
      | Description     | Product type | Vendor | Tags             |
      | This is T-shirt | Shirt        | Uniqlo | tag1, tag2, tag3 |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Small   | 10    | A123456B | 100       |
      | Big     | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Show  | Price     | Compare at price | Description     | Vendor | SKU      | Product type | Tags             |
      | Size: Small   | Small   | false | $7.00 USD | $20.00 USD       | This is T-shirt | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: Big     | Big     | true  | $7.00 USD | $20.00 USD       | This is T-shirt | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "1" created variants display correctly on product variant detail page
      | Option set | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Size       | Big    | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser