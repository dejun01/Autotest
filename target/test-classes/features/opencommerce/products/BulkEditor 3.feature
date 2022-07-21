@dashboardProduct @dashboard
Feature: Product bulk editor
#  sbase_dashboard_product_bulk_editor
  Background: Login dashboard
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    Given Delete all products

  Scenario: bulk editor #SB_PRO_BE_1 #SB_PRO_BE_2 #SB_PRO_BE_3 #SB_PRO_BE_4 #SB_PRO_BE_5 #SB_PRO_BE_6 #SB_PRO_BE_7 #SB_PRO_BE_10 #SB_PRO_PL_29 #SB_PRO_SP_106
    And Add a new product with data
      | Title          | Description                         | Product type | Vendor | Tags    | Price | Compare at price | Cost per item | SKU | Barcode | Quantity | Weight |
      | Product_editor | This is the ultimate basic T-shirt. | Shirt        | Uniqlo | t-shirt | 30    | 40               | 50            | AOP | code    | 5        | 10     |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Color      | Red          |
      | Size       | S, M, L, XL  |
    And user navigate to "Products>All products" screen
    Then select all product with action
      | Action        |
      | Edit products |
    Then bulk editor information product
      | Title          | Title Update   | Product type | Product type update | Vendor | Vendor update | Tags    | Tags update | Option   | Price | Price update | Compare at price | Compare at price update | Cost per item | Cost per item update | Sku | Sku update | Weight | Weight update |
      | Product_editor | Product update | Shirt        | Shirt update        | Uniqlo | Uniqlo update | t-shirt | test        | Red / S  | 30    | 15           | 40               | 25                      | 50            | 35                   | AOP | AOP update | 10     | 20            |
      |                |                |              |                     |        |               |         |             | Red / M  | 30    | 15           |                  |                         |               |                      |     |            |        |               |
      |                |                |              |                     |        |               |         |             | Red / L  | 30    | 15           |                  |                         |               |                      |     |            |        |               |
      |                |                |              |                     |        |               |         |             | Red / XL | 30    | 15           |                  |                         |               |                      |     |            |        |               |
    And Open detail product of product "Product update"
    And Information of created product "Product update" display correctly
      | Product type | Description                         | Vendor        | Tags          | Option | Price | Compare at price | Cost per item | SKU        | Weight |
      | Shirt update | This is the ultimate basic T-shirt. | Uniqlo update | t-shirt, test | Red    | 15    | 25               | 35            | AOP update | 20     |
    Then Verify this product "Product update" on storefront
      | Product type | Description                         | Tags          | Option | Price      | Compare at price | Cost per item | SKU        | Weight |
      | Shirt update | This is the ultimate basic T-shirt. | t-shirt, test | Red    | $15.00 USD | $25.00 USD       | $35.00 USD    | AOP update | 20     |