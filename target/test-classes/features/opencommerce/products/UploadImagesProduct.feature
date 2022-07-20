@dashboardProduct @dashboard
Feature: Show specific images per variant
#  dev_sbase_dashboard_Update_img_product
  Background: Login dashboard
    Given user login to shopbase dashboard by API
    Given clear all data

  @dashboardProductUpdateImages
  Scenario: Update many images for many variant
    Given user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title         | Description                                                            | Image     | Product type | Vendor | Tags             | Price | Compare at price | Cost per item | SKU      | Barcode  | Inventory policy                         | Quantity | Weight |
      | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Logo1.jpg | Shirt        | Uniqlo | tag1, tag2, tag3 | 10    | 20               | 5             | A123456B | B654321A | ShopBase tracks this product's inventory | 100      | 50     |
    #Given Add a new product with data
    #  | Title         | Description                                                            |
    #  | @NameProduct@ | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    Then Add a new product variant with multi option set
      | Option set | Option value |
      | Size       | S, M         |
      | Color      | Black, Pink  |
    When add images "Test.PNG,spring.JPG,red.PNG" for variant by group "S"
    Then select "3" images
    Then verify variants have many images by group "S"
    When uncheck "1" variant image by group "S"
    Then re-verify variants images by group "S"
    When delete "2" images
    Then re-verify variants images by group "S"

  Scenario: Update setting to show many images for a variant on storefront
    When navigate to "Online Store" tab
    And user click Customize on current theme
    And user select "Product" session
    When setting images list is "All photos"
    When user navigate to "Products>All products" screen
    And user navigate product detail page with name product is "Test"
    And user count number of photos by setting
    When go to store front
    Then user count number of photos on store front
    And verify number of photos

  Scenario Outline: Assign mockup
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product          | Category       |
      | 01  | AOP Hawaii Shirt | All Over Print |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name |
      | 01  | AOP Hawaii Shirt | Text       |            |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And view variant
    Then verify mockup assign for base product
      | Product          | Total mockup |
      | AOP Hawaii Shirt | 3            |
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 01  |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Style       | Sale price | Compare at price |
      | 01  | <Campaign name> | White | M    | Unisex Tank | 30.35      | 40               |
    Then user count number of photos on store front
    And verify number of photos

    Examples:
      | KEY | Campaign name        | Description          | Tags        |
      | 01  | Campaign 3D two side | Campaign 3D two side | ladies,test |






