@dashboardProduct @dashboard
Feature: Smoke test product
#ENV: prod_sbase_smoke_product
  Background: Login dashboard
    Given user login to shopbase dashboard
    Then user navigate to "商品>全部商品" screen

  @dashboardProduct
  Scenario: create product
    Given Delete all products
    Given Add a new product with data
      | Title         | Description                                                            | Image url                                                               | Image     | Image added | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | https://img.btdmp.com/10209/10209304/products/162340294897d75990eb.jpeg | Logo1.jpg | 2 / 500     | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase追踪该商品的库存 | 100      | 50     |
    Then user navigate to "商品>全部商品" screen
    When Search product "@NameProduct@" on All product screen
    And Open detail product of product "@NameProduct@"
    And Information of created product "@NameProduct@" display correctly
      | Description                                                            | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight |
      | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     |
