Feature: Bulk update 01
#env: sbase_dashboard_product_bulk_update_01
  Background: Navigate to "Products>All products" screen
    Given clear all data
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen

  Scenario: Case 01 - All conditions - Product tag - is equal to "new" - Change product description to ""  #SB_PRO_BU_1
    Given Delete all products
    When Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor | Tags                  | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate01_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3, new | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate01_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3      | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field       | Comparator  | Value |
      | Product tag | is equal to | new   |
    And Execute an action bulk update for product "Change product description to"
      | Value |
      |       |
    And quit browser


  Scenario: Verify Case 1 #SB_PRO_BU_2
    When Search product "BulkUpdate01_01" on All product screen
    And Open detail product of product "BulkUpdate01_01"
    And Information of created product "BulkUpdate01_01" display correctly
      | Description | Product type | Vendor | Tags                  | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      |             | Shirt        | Uniqlo | tag1, tag2, tag3, new | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate01_01" on storefront
      | Description | Price      | Compare at price | Vendor | SKU      | Product type | Tags                  |
      |             | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3, new |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate01_02" on All product screen
    And Open detail product of product "BulkUpdate01_02"
    And Information of created product "BulkUpdate01_02" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate01_02" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And quit browser


  Scenario: Case 02 - All conditions - Product title - is equal to "BulkUpdate02_T-Shirt" - Change product description to "Description Updated" #SB_PRO_BU_3
    Given Delete all products
    Given Add a new product with data
      | Title                | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate02_01      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate02_T-Shirt | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator  | Value                |
      | Product title | is equal to | BulkUpdate02_T-Shirt |
    And Execute an action bulk update for product "Change product description to"
      | Value               |
      | Description Updated |
    And quit browser


  Scenario: Verify Case 2 #SB_PRO_BU_4
    When Search product "BulkUpdate02_01" on All product screen
    And Open detail product of product "BulkUpdate02_01"
    And Information of created product "BulkUpdate02_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate02_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate02_T-Shirt" on All product screen
    And Open detail product of product "BulkUpdate02_T-Shirt"
    And Information of created product "BulkUpdate02_T-Shirt" display correctly
      | Description         | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Description Updated | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate02_T-Shirt" on storefront
      | Description         | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Description Updated | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And quit browser


  Scenario: Case 04 - All conditions - Product vendor - starts with "Acme" - Change product vendor to "Vendor Updated" #SB_PRO_BU_5
    Given Delete all products
    Given Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor       | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate04_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo       | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate04_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Acme Markets | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field          | Comparator  | Value |
      | Product vendor | starts with | Acme  |
    And Execute an action bulk update for product "Change product vendor to"
      | Value          |
      | Vendor Updated |
    And quit browser


  Scenario: Verify Case 4 #SB_PRO_BU_6
    When Search product "BulkUpdate04_01" on All product screen
    And Open detail product of product "BulkUpdate04_01"
    And Information of created product "BulkUpdate04_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate04_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate04_02" on All product screen
    And Open detail product of product "BulkUpdate04_02"
    And Information of created product "BulkUpdate04_02" display correctly
      | Description                                                            | Product type | Vendor         | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Vendor Updated | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate04_02" on storefront
      | Description                                                            | Price      | Compare at price | Vendor         | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Vendor Updated | A123456B | Shirt        | tag1, tag2, tag3 |
