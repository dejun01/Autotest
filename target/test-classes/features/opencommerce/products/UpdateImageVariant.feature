Feature: Show specific images per variant
 # dev_sbase_dashboard_Update_img_variant
  Background: Login dashboard
    Given user login to shopbase dashboard by API

  Scenario: Update many image for a variant
    Given user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | S, M         |
      | Color      | Black, Pink  |
    When update images "Test.PNG,spring.JPG,red.PNG" for a variant
      | Size | Color |
      | S    | Black |
    Then select "3" images
    Then verify variant has many images
      | Size | Color |
      | S    | Black |
    When uncheck "1" variant image for a variant
      | Size | Color |
      | S    | Black |
    Then re-verify a variant images
      | Size | Color |
      | S    | Black |
    When delete "2" images
    Then re-verify a variant images
      | Size | Color |
      | S    | Black |

