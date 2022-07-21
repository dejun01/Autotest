@dashboardCloneProduct @dashboard
Feature: Clone Product Prepare Data
#evn: clone_product
  Background:
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then Delete all products
    Then Import product by csv with file name
      | File name                  | Message                                                  |
      | import_with_5_products.csv | We're currently importing your products into your store. |
    And quit browser


  Scenario: Clone products from source shop to another shop with action Keep both products #SB_PRO_CP_114 #SB_PRO_CP_102
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And select "5" products
    And Import product to another shop
      | Action             |
      | Keep both products |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "10" products
    And quit browser


  Scenario: Clone products from source shop to another shop with action Skip the product #SB_PRO_CP_84 #SB_PRO_CP_101 #SB_PRO_CP_103 #SB_PRO_CP_113
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And select "5" products
    And Import product to another shop
      | Action           |
      | Skip the product |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "5" products
    And quit browser


  Scenario: Clone products from source shop to another shop with action Override the existing products #SB_PRO_CP_84 #SB_PRO_CP_115 #SB_PRO_CP_100 #SB_PRO_CP_104
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And select "5" products
    And Import product to another shop
      | Message                                                                                                                             | Total products | Action                         |
      | The selected store is of ShopBase type. Hence, only products that are created on ShopBase dashboard and on PrintHub will be copied. | 5 products     | Override the existing products |

  Scenario: Verify message when clone product with action Skip the product already exists #SB_PRO_CP_122
    Given user login to firstShop dashboard by API
    And user navigate to "Products>All products" screen
    And select "2" products
    And Import product to another shop
      | Action           |
      | Skip the product |
    And quit browser
    Given user login to secondShop dashboard by API
    And user navigate to "Products>All products" screen
    Then verify import products status is "Completed" with "5" products
    And verify detail message in import process
      | Message                                    |
      | Imported 0/2 products. Skipped 2 products.|
