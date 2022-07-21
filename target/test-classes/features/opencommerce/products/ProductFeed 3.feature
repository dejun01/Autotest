@dashboardProduct @dashboard
Feature: Product Feed

  Background: Login dashboard
    Given user login to shopbase dashboard by API
    Then user navigate to "Products>Collections" screen
    And Delete all collection
    Then user navigate to "Products>Product feeds" screen
    And Delete product feed with title
      | Title                |
      | Products feed        |
      | Product feed male    |
      | Product feed female  |
      | Product feed unisex  |
      | Product feed default |
    Given Delete all products by API


  @dashboardProductFeed
  Scenario: Add new product feed
    Then user navigate to "Products>Product feeds" screen
    Then verify total product of the "Product feed all"


  @dashboardProductFeed
  Scenario: Add product feed and verify total of smart collection
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title                    | Description                                                            | Image     | Image added | Product type | Vendor | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight | Error message                     |
      | @product add collection@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | 1 / 500     | Shirt        | Uniqlo | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | Product was created successfully! |
    Then user navigate to "Products>Collections" screen
    And Create collection with data
      | Title                  | Collection type | Conditions                                                   | Message                          |
      | @Auto collection feed@ | Automated       | all conditions,Product title,contains,product add collection | Created collection successfully! |
    Then user navigate to "Products>Product feeds" screen
    And Create a new product feed with data
      | Feed name     | All products or just some of them? | Collection name      | Export mode                        | Variant title                                    | Google product category |
      | Products feed | Products from selected collections | Auto collection feed | Export all variations of a product | Do not add variant title at the end of the title |                         |
    Then verify total product of the "Products feed"


  @dashboardProductFeed
  Scenario: Add product feed and verify total of manual collection
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title                         | Description                                                            | Image     | Product type | Vendor | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight | Error message                     |
      | product add manual collection | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | Product was created successfully! |
    Then user navigate to "Products>Collections" screen
    And Create collection with data
      | Title                  | Collection type | Message                          |
      | Manual collection feed | Manual          | Created collection successfully! |
    Given user navigate to "Products>Collections" screen
    And add product to collection
      | Product name                  | Collection type | Collection title       | Message                     |
      | product add manual collection | Manual          | Manual collection feed | Select product successfully |
    Then user navigate to "Products>Product feeds" screen
    And Create a new product feed with data
      | Feed name     | All products or just some of them? | Collection name        | Export mode                        | Variant title                                    | Google product category |
      | Products feed | Products from selected collections | Manual collection feed | Export all variations of a product | Do not add variant title at the end of the title |                         |
    Then verify total product of the "Products feed"


  @dashboardProductFeed
  Scenario: Add product feed and verify total of empty collection
    Then user navigate to "Products>Collections" screen
    And Create collection with data
      | Title                   | Collection type | Message                          |
      | Manual collection empty | Manual          | Created collection successfully! |
    Then user navigate to "Products>Product feeds" screen
    And Create a new product feed with data
      | Feed name     | All products or just some of them? | Collection name         | Export mode                        | Variant title                                    | Google product category |
      | Products feed | Products from selected collections | Manual collection empty | Export all variations of a product | Do not add variant title at the end of the title |                         |
    Then verify total product of the "Products feed"


  @dashboardProductFeed
  Scenario: Add product feed and verify detail product
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title        | Description                                                            | Image     | Product type | Vendor | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight | Error message                     |
      | product name | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     | Product was created successfully! |
    Then user navigate to "Products>Collections" screen
    And Create collection with data
      | Title                | Collection type | Conditions                                            | Message                          |
      | Auto collection feed | Automated       | all conditions,Product title,is equal to,product name | Created collection successfully! |
    Then user navigate to "Products>Product feeds" screen
    And Create a new product feed with data
      | Feed name     | All products or just some of them? | Collection name      | Export mode                        | Variant title                                    | Google product category |
      | Products feed | Products from selected collections | Auto collection feed | Export all variations of a product | Do not add variant title at the end of the title |                         |
    Then verify detail value product of the "Products feed"
      | Title        | Description                                                            | Product type | Vendor | Price | Compare at price | Barcode  | Weight |
      | product name | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | 10    | 20               | B654321A | 50     |


  @dashboardProductFeed
  Scenario: Add product feed and verify gender is male,female,unisex
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title          | Tags             | Image     |
      | product gender | feed-gender-male | Logo1.jpg |
    Then user navigate to "Products>Collections" screen
    And Create collection with data
      | Title                  | Collection type | Conditions                                              |
      | Auto collection gender | Automated       | all conditions,Product tag,is equal to,feed-gender-male |
    Then user navigate to "Products>Product feeds" screen
    And Create a new product feed with data
      | Feed name         | All products or just some of them? | Collection name        | Export mode                        | Variant title                                    | Google product category |
      | Product feed male | Products from selected collections | Auto collection gender | Export all variations of a product | Do not add variant title at the end of the title |                         |
    Then verify detail value product of the "Product feed male"
      | Title          | Price | gender |
      | product gender | 0     | male   |

    Then user navigate to "Products>All products" screen
    And edit tags product on dashboard
      | Title          | Tags               |
      | product gender | feed-gender-female |
    Then user navigate to "Products>Collections" screen
    And edit collection
      | Title                  | Value conditions   |
      | Auto collection gender | feed-gender-female |
    Then user navigate to "Products>Product feeds" screen
    And edit product feed
      | Feed name         | New feed name       |
      | Product feed male | Product feed female |
    Then verify detail value product of the "Product feed female"
      | Title          | gender |
      | product gender | female |

    Then user navigate to "Products>All products" screen
    And edit tags product on dashboard
      | Title          | Tags               |
      | product gender | feed-gender-unisex |
    Then user navigate to "Products>Collections" screen
    And edit collection
      | Title                  | Value conditions   |
      | Auto collection gender | feed-gender-unisex |
    Then user navigate to "Products>Product feeds" screen
    And edit product feed
      | Feed name           | New feed name       |
      | Product feed female | Product feed unisex |
    Then verify detail value product of the "Product feed unisex"
      | Title          | gender |
      | product gender | unisex |


  @dashboardProductFeed
  Scenario: Add product feed and verify Defaul Gender and Default Age Group
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title           | Image     | Error message                     |
      | product default | Logo1.jpg | Product was created successfully! |
    Then user navigate to "Products>Collections" screen
    And Create collection with data
      | Title                   | Collection type | Conditions                                               | Message                          |
      | Auto collection default | Automated       | all conditions,Product title,is equal to,product default | Created collection successfully! |
    Then user navigate to "Products>Product feeds" screen
    And Create a new product feed with data
      | Feed name            | All products or just some of them? | Collection name         | Export mode                        | Variant title                                    | Google product category |
      | Product feed default | Products from selected collections | Auto collection default | Export all variations of a product | Do not add variant title at the end of the title |                         |
    Then verify detail value product of the "Product feed default"
      | Title           | Price | gender | age_group |
      | product default | 0     |        |           |