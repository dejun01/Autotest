Feature: Bulk update 03
#env: sbase_dashboard_product_bulk_update_03
  Background: Navigate to "Products>All products" screen
    Given clear all data
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen

  Scenario: Case 09 - All conditions - Variant title - contains "Small" - Decrease price by amount "3" #SB_PRO_BU_13
    Given Delete all products
    Given Add a new product with data
      | Title           | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | BulkUpdate09_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
      | BulkUpdate09_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value | Created variant status |
      | Size       | Small, Big   | enable                 |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value |
      | Variant title | contains   | Small |
    And Execute an action bulk update for product "Decrease price by amount"
      | Value |
      | 3     |
    And quit browser


  Scenario: Verify Case 9 #SB_PRO_BU_14
    When Search product "BulkUpdate09_01" on All product screen
    And Open detail product of product "BulkUpdate09_01"
    And Information of created product "BulkUpdate09_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate09_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate09_02" on All product screen
    And Open detail product of product "BulkUpdate09_02"
    And Information of created product "BulkUpdate09_02" display correctly
      | Description                                                            | Product type | Vendor | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Small   | 7     | A123456B | 100       |
      | Big     | 7     | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price     | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: Small   | Small   | $7.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: Big     | Big     | $7.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option set | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Size       | Small  | 7     | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Size       | Big    | 7     | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  Scenario: Case 10 - All conditions - Product title - contains "Dress" - Only update some variants - Variant title - is equal to "small" - Change compare-at-price to "222.2" #SB_PRO_BU_15
    Given Delete all products
    Given Add a new product with data
      | Title              | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | BulkUpdate10_01    | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
      | BulkUpdate10_Dress | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value | Created variant status |
      | Size       | Small, Big   | enable                 |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value |
      | Product title | contains   | Dress |
    And Create a bulk update with variant condition is "All conditions"
      | Field         | Comparator  | Value |
      | Variant title | is equal to | small |
    And Execute an action bulk update for some variants "Change compare-at-price to"
      | Value |
      | 222.2 |
    And quit browser


  Scenario: Verify Case 10 #SB_PRO_BU_16
    When Search product "BulkUpdate10_01" on All product screen
    And Open detail product of product "BulkUpdate10_01"
    And Information of created product "BulkUpdate10_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate10_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate10_Dress" on All product screen
    And Open detail product of product "BulkUpdate10_Dress"
    And Information of created product "BulkUpdate10_Dress" display correctly
      | Description                                                            | Product type | Vendor | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Small   | 10    | A123456B | 100       |
      | Big     | 10    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: Small   | Small   | $10.00 USD | $222.20 USD      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: Big     | Big     | $10.00 USD | $20.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option set | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Size       | Small  | 10    | 222.2            | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Size       | Big    | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
    And quit browser


  Scenario: Case 11 - All conditions - Product title - contains "Dress" - Only update some variants - Variant compare-at-price - is less than "44" - Decrease compare-at-price by percentage "5" #SB_PRO_BU_17
    Given Delete all products
    Given Add a new product with data
      | Title              | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Allow overselling | Quantity | Weight |
      | BulkUpdate11_01    | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
      | BulkUpdate11_Dress | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | true              | 100      | 50     |
    Then Add a new product variant with data
      | Option set | Option value | Created variant status |
      | Size       | Small, Big   | enable                 |
    And Edit information of created variants with data
      | Old option | Price | Compare at price | Cost per item |
      | Big        | 50    | 100              | 25            |
    And user navigate to "Products>Bulk updates" screen
    And Create a bulk update with product condition is "All conditions"
      | Field         | Comparator | Value |
      | Product title | contains   | Dress |
    And Create a bulk update with variant condition is "All conditions"
      | Field                    | Comparator   | Value |
      | Variant compare-at-price | is less than | 44    |
    And Execute an action bulk update for some variants "Decrease compare-at-price by percentage"
      | Value |
      | 5     |
    And quit browser


  Scenario: Verify Case 11 #SB_PRO_BU_18
    When Search product "BulkUpdate11_01" on All product screen
    And Open detail product of product "BulkUpdate11_01"
    And Information of created product "BulkUpdate11_01" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
    Then Verify this product "BulkUpdate11_01" on storefront
      | Description                                                            | Price      | Compare at price | Vendor | SKU      | Product type | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | $10.00 USD | $20.00 USD       | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Close browser tab with index "1"
    Then Switch to the first tab
    And user navigate to "Products>All products" screen
    When Search product "BulkUpdate11_Dress" on All product screen
    And Open detail product of product "BulkUpdate11_Dress"
    And Information of created product "BulkUpdate11_Dress" display correctly
      | Description                                                            | Product type | Vendor | Tags             |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 |
    And Information of created variants display correctly on product detail page
      | Variant | Price | SKU      | Inventory |
      | Small   | 10    | A123456B | 100       |
      | Big     | 50    | A123456B | 100       |
    Then Verify all product variants on storefront
      | Variant Label | Variant | Price      | Compare at price | Description                                                            | Vendor | SKU      | Product type | Tags             |
      | Size: Small   | Small   | $10.00 USD | $19.00 USD       | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
      | Size: Big     | Big     | $50.00 USD | $100.00 USD      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Uniqlo | A123456B | Shirt        | tag1, tag2, tag3 |
    And Switch to the first tab
    And Information of "2" created variants display correctly on product variant detail page
      | Option set | Option | Price | Compare at price | Cost per item | SKU      | Barcode  | Track quantity                           | Quantity | Allow overselling | Weight | Weight unit |
      | Size       | Small  | 10    | 19               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
      | Size       | Big    | 50    | 100              | 25            | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | Yes               | 50     | lb          |
