Feature: Delete product
##prod_sbase_delete_products

  Scenario: Delete all product
    And Delete all products by API

  Scenario Outline: Add product
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    Given Add a new product with data
      | Title          |
      | <Product name> |
    Examples:
      | Product name |
      | Product 1    |
      | Product 2    |
      | Product 3    |


  Scenario Outline: Delete product in product detail
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    When Search product "<Product name>" on All product screen
    And Open detail product of product "<Product name>"
    And get link handle url of product on dashboard
    And delete product "<Product name>"
    Then Verify data of product on product list screen
      | Title          | Is exist |
      | <Product name> | false    |
    And open and verify products "<Product name>" not exist on Storefront
    And quit browser
    Examples:
      | Product name |
      | Product 1    |


  Scenario Outline: Delete product by action in dashboard
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    When Search product "<Product name>" on All product screen
    And Open detail product of product "<Product name>"
    And get link handle url of product on dashboard
    And user navigate to "Products>All products" screen
    When add action to products
      | List product   | Action                   |
      | <Product name> | Delete selected products |
    Then Verify data of product on product list screen
      | Title          | Is exist |
      | <Product name> | false    |
    And open and verify products "<Product name>" not exist on Storefront
    And quit browser
    Examples:
      | Product name |
      | Product 2    |


  Scenario Outline: Delete product by API
    Given user login to shopbase dashboard by API
    Given user navigate to "Products>All products" screen
    When Search product "<Product name>" on All product screen
    And Open detail product of product "<Product name>"
    And get link handle url of product on dashboard
    And user navigate to "Products>All products" screen
    And Search product "<Product name>" on All product screen
    And delete product"<Product name>" by API
    Then Verify data of product on product list screen
      | Title          | Is exist |
      | <Product name> | false    |
    And open and verify products "<Product name>" not exist on Storefront
    And quit browser
    Examples:
      | Product name |
      | Product 3    |



