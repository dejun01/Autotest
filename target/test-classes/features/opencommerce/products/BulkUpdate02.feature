Feature: Bulk update 02
#env: sbase_dashboard_product_bulk_update_02
  Background: Navigate to "Products>All products" screen
    Given clear all data
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen

  Scenario: Case 03 - All conditions - Product type - is not equal to "Apparel" - Change product vendor to ""  #SB_PRO_BU_7
    Given Delete all products
    Given Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate03_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Apparel      | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate03_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field        | Comparator      | Value   |
      | Product type | is not equal to | Apparel |
    And Execute an action bulk update for product "Change product vendor to"
      | Value |
      |       |
    And quit browser


  Scenario: Verify Case 3 #SB_PRO_BU_8
    When Search product "BulkUpdate03_01" on All product screen
    And Open detail product of product "BulkUpdate03_01"
    And Information of created product "BulkUpdate03_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Apparel      | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate03_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Apparel      | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate03_02" on All product screen
    And Open detail product of product "BulkUpdate03_02"
    And Information of created product "BulkUpdate03_02" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        |        | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate03_02" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       |        | A123456B | Shirt        | tag1, tag2, tag3 |
    And quit browser


  Scenario: Case 06 - All conditions - Compare at price - is less than "30" - Add tags "tag4" #SB_PRO_BU_9
    Given Delete all products
    Given Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate06_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 51    | 100              | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate06_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field            | Comparator   | Value |
      | Compare at price | is less than | 30    |
    And Execute an action bulk update for product "Add tags"
      | Value |
      | tag4  |
    And quit browser


  Scenario: Verify Case 6 #SB_PRO_BU_10
    When Search product "BulkUpdate06_01" on All product screen
    And Open detail product of product "BulkUpdate06_01"
    And Information of created product "BulkUpdate06_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 51    | 100              | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate06_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $51.00 USD | $100.00 USD      | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate06_02" on All product screen
    And Open detail product of product "BulkUpdate06_02"
    And Information of created product "BulkUpdate06_02" display correctly
      | Description                                                            | Product type | Vendor | Tags                   | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3, tag4 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate06_02" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags                   |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3, tag4 |
    And quit browser


  Scenario: Case 08 - All conditions - Inventory stock - is greater than "50" - Increase price by amount "100" #SB_PRO_BU_11
    Given Delete all products
    Given Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | BulkUpdate08_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 200              | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
      | BulkUpdate08_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 10       | 50     |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field           | Comparator      | Value |
      | Inventory stock | is greater than | 50    |
    And Execute an action bulk update for product "Increase price by amount"
      | Value |
      | 100   |
    And quit browser


  Scenario: Verify Case 8 #SB_PRO_BU_12
    When Search product "BulkUpdate08_01" on All product screen
    And Open detail product of product "BulkUpdate08_01"
    And Information of created product "BulkUpdate08_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 110   | 200              | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate08_01" on storefront
      | Description                                                            | Price       | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $110.00 USD | $200.00 USD      | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate08_02" on All product screen
    And Open detail product of product "BulkUpdate08_02"
    And Information of created product "BulkUpdate08_02" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 10       | 50     |
    Then Verify this product "BulkUpdate08_02" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
