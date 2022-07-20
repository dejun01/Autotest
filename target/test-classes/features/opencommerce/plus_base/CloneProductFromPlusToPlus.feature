Feature: Clone products from shop PlusBase to Shop PlusBase
#clone_product_plus_plus

  Scenario: Delete all product in des shop and clone product #SB_PLB_CPP_1 #SB_PLB_CPP_2 #SB_PLB_CPP_3
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then Delete all products
    And quit browser
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And search product PlBase "Test product"
    And select "2" products
    And Import product to another shop
      | Action             |
      | Keep both products |
    And quit browser


  Scenario: Clone products from source shop to another shop with action Skip the product #SB_PLB_CPP_9 #SB_PLB_CPP_10 #SB_PLB_CPP_11 #SB_PLB_CPP_12 #SB_PLB_CPP_13 #SB_PLB_CPP_14
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And search product PlBase "Test product"
    And select "2" products
    And Import product to another shop
      | Action           |
      | Skip the product |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "2" products
    And search product PlBase "Test product 1" then select product
    And open product details on Storefront from product detail in dashboard
    Then verify image product page "01"
      | KEY | Image expected                           | Image actual |
      | 01  | /phub/watermask/plus_SpatialMark.png     | Watermark    |
    And quit browser


  Scenario: Clone products from source shop to another shop with action Override the existing products #SB_PLB_CPP_15 #SB_PLB_CPP_16 #SB_PLB_CPP_17 #SB_PLB_CPP_18 #SB_PLB_CPP_19 #SB_PLB_CPP_20
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And search product PlBase "Test product"
    And select "2" products
    And Import product to another shop
      | Action                         |
      | Override the existing products |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "2" products
    And search product PlBase "Test product 1" then select product
    And open product details on Storefront from product detail in dashboard
    Then verify image product page "01"
      | KEY | Image expected                           | Image actual |
      | 01  | /phub/watermask/plus_SpatialMark.png     | Watermark    |
    And quit browser

  Scenario: Clone products from source shop to another shop with action Keep the products #SB_PLB_CPP_4 #SB_PLB_CPP_5 #SB_PLB_CPP_6 #SB_PLB_CPP_7 #SB_PLB_CPP_8
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And search product PlBase "Test product"
    And select "2" products
    And Import product to another shop
      | Action             |
      | Keep both products |
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "4" products
    And search product PlBase "Test product 1" then select product
    And open product details on Storefront from product detail in dashboard
    Then verify image product page "01"
      | KEY | Image expected                           | Image actual |
      | 01  | /phub/watermask/plus_SpatialMark.png     | Watermark    |
    And quit browser


  Scenario: Clone products from source shop to another shop with products variant combo #SB_PLB_CPP_4 #SB_PLB_CPP_9 #SB_PLB_CPP_15
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And search product PlBase "Test variant combo"
    And select "1" products
    And Import product to another shop
      | Action             |
      | Keep both products |
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "5" products
    And search product PlBase "Test variant combo" then select product
    And verify product information in dashboard
    And verify variant information of product or campaign details in dashboard as "<string>"
    And verify product information in product detail of PlusBase as "01"
      | KEY | Color                          |
      | 01  | Set of 2 (Black + Green)       |
      | 01  | Set of 2 (Black + Red)         |
      | 01  | Set of 2 (Green + Red)         |
      | 01  | Set of 3 (Black + Green + Red) |
      | 01  | Black                          |
      | 01  | Green                          |
      | 01  | Red                            |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "01"
      | KEY | Product name       | Color                          |
      | 01  | Test variant combo | Black                          |
      | 01  | Test variant combo | Green                          |
      | 01  | Test variant combo | Red                            |
      | 01  | Test variant combo | Set of 2 (Black + Green)       |
      | 01  | Test variant combo | Set of 2 (Black + Red)         |
      | 01  | Test variant combo | Set of 2 (Green + Red)         |
      | 01  | Test variant combo | Set of 3 (Black + Green + Red) |
    And quit browser












