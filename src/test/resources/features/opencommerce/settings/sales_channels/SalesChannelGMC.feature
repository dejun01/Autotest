@dashboardProduct @dashboard
Feature: Sales channel and Google
  #sales_channel_GMC

  Scenario: Verify feed Google #SB_SET_SC_GMC_1 #SB_SET_SC_GMC_2
    #Create feed API
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    And delete all feed in feed list
    And user navigate to "Products>All products" screen
    And Delete all products
    And Add a new product with data
      | Title         | Description                    | Image        | Product type | Vendor | Price | Compare at price | Cost per item | SKU      | Barcode  | Weight | Weight unit | Variant                 | SEO title     | SEO description         |
      | Sample Test 1 | Product for feed with image    | Shopbase.png | Shirt        | Uniqlo | 10    | 20               | 5             | A123456B | B654321A | 50     | g           | Color:Black, White, Red | Sample Test 1 | This is for test feed 1 |
      | Sample Test 2 | Product for feed without image |              | Shirt        | Uniqlo | 10    | 20               | 5             | A123456B | B654321A | 50     | g           | Size:S, M, L            | Sample Test 2 | This is for test feed 2 |
    And user navigate to "Products>Product feeds" screen
    Then Add feed "Google"
    And Update feed setting
      | Feed name     | Feed type    | Collection name | Feed setting                       | Input option for feed setting | Variant title                                                                   | Product title preference | Product description preference | Submit products as custom products | Default brand name | Google product category               | Default gender | Default age group |
      | Feed API test | All products |                 | Export all variations of a product |                               | Add all options like color, size, etc behind the title (example: t-shirt red S) | Default product title    | Default product description    | No                                 | Shopbase           | Animals & Pet Supplies > Pet Supplies | Male           | Adult             |
    Then click feed save button
    And wait 10000 second
    And quit browser

  Scenario: verify numbers feed Google #SB_SET_SC_GMC_3 #SB_SET_SC_GMC_13
    #Verify feed amount number
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    And verify the number of variant in manage product data
      | Feed type | Actual number of variant | Actual number of variant in product data |
      | API       | 0                        | 6                                        |
    Then verify feed variant
      | Title               | Description                 | Product type | Vendor | Price | Compare at price | Cost per item | SKU      | Barcode  | Quantity | Weight | Weight unit | Google product category               | Default gender | Default age group |
      | Sample Test 1 Black | Product for feed with image | Shirt        | Uniqlo | 10    | 20               | 5             | A123456B | B654321A | 100      | 50     | g           | Animals & Pet Supplies > Pet Supplies | male           | adult             |
    Then quit browser

  Scenario: Verify feed Klaviyo #SB_SET_SC_GMC_1 #SB_SET_SC_GMC_2
    #Create feed Klaviyo
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    Then Add feed "Klaviyo"
    Then Update feed setting
      | Feed name         | Feed type    | Collection name | Feed setting                       | Input option for feed setting | Variant title                                                                   | Product title preference | Product description preference | Submit products as custom products | Default brand name | Google product category | Default gender | Default age group |
      | Feed Klaviyo test | All products |                 | Export all variations of a product |                               | Add all options like color, size, etc behind the title (example: t-shirt red S) | Default product title    | Default product description    |                                    |                    |                         |                |                   |
    Then click feed save button
    Then wait 10000 second
    Then quit browser

  Scenario: verify numbers feed Klaviyo #SB_SET_SC_GMC_3 #SB_SET_SC_GMC_13
    #Verify feed amount number
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    And verify the number of variant in manage product data
      | Feed type | Actual number of variant | Actual number of variant in product data |
      | Klaviyo   | 3                        | 0                                        |
    Then quit browser

  Scenario: Verify feed Others #SB_SET_SC_GMC_1 #SB_SET_SC_GMC_2
    #Create feed Others
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    Then Add feed "Others"
    Then Update feed setting
      | Feed name        | Feed type    | Collection name | Feed setting                       | Input option for feed setting | Variant title                                                                   | Product title preference | Product description preference | Submit products as custom products | Default brand name | Google product category               | Default gender | Default age group |
      | Feed Others test | All products |                 | Export all variations of a product |                               | Add all options like color, size, etc behind the title (example: t-shirt red S) | Default product title    | Default product description    |                                    |                    | Animals & Pet Supplies > Pet Supplies |                |                   |
    Then click feed save button
    Then wait 10000 second
    Then quit browser

  Scenario: verify numbers feed Others #SB_SET_SC_GMC_3 #SB_SET_SC_GMC_13
    #Verify feed amount number
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    And verify the number of variant in manage product data
      | Feed type | Actual number of variant | Actual number of variant in product data |
      | Others    | 3                        | 0                                        |
    Then quit browser

  Scenario: Update manage product data #SB_SET_SC_GMC_6
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    Then open "Feed API test" in feed list
    And open manage product data
    Then update and verify manage product data detail
      | Title             | Description             | Product type | Brand (Vendor) | ID         | Item group ID | GTIN (Barcode) | MPN (SKU)   | Google Product Category | Condition | Age Group | Gender | Color     | Size System | Size Type | Pricing Measure | Unit Pricing Measure | Pricing Base Measure | Unit Pricing Base Measure | Product Identifiers Management  | Custom Label 0 | Custom Label 1 | Custom Label 2 | Custom Label 3 | Custom Label 4 | Ads label | Ads grouping | Shipping label | Shipping weight | Shipping weight unit |
      | Changed title (1) | Changed description (1) | selling      | Marvel         | abcXYZ1111 | Product0001   | ABCD12345678   | Test::12345 | Animals & Pet Supplies  | used      | kids      | unisex | Xanh biec | fr          | tall      | 100             | cm                   | 100                  | cm                        | Submit Brand Name and MPN (SKU) | custom 0       | custom 1       | custom 2       | custom 3       | custom 4       | Spiderman | Figure       | Shippable      | 10              | g                    |
    Then quit browser

  Scenario: Verify update feed setting
    Given user login to shopbase dashboard
    And user navigate to "Products>Product feeds" screen
    Then open "Feed API test" in feed list
    Then Update feed setting
      | Feed name             | Feed type    | Collection name | Feed setting                             | Input option for feed setting | Variant title                                    | Product title preference | Product description preference | Submit products as custom products | Default brand name | GMC method | Google product category               | Default gender | Default age group |
      | Feed API changed name | All products |                 | Export only first variation of a product |                               | Do not add variant title at the end of the title | Default product title    | SEO product description        | No                                 | Shopbase           | true       | Animals & Pet Supplies > Pet Supplies | Female         | Kids              |
    Then click feed save button
    And user navigate to "Products>Product feeds" screen
    And verify the number of variant in manage product data
      | Feed type | Actual number of variant | Actual number of variant in product data |
      | API       | 0                        | 2                                        |
    Then quit browser

  Scenario: Delete product sync into Manage product data
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen
    And Delete all products
    And user navigate to "Products>Product feeds" screen
    And verify the number of variant in manage product data
      | Feed type | Actual number of variant | Actual number of variant in product data |
      | API       | 0                        | 0                                        |