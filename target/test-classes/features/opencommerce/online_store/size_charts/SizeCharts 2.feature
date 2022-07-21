Feature: Product size chart
#env: sbase_dashboard_size_chart_product
  Background: Login dashboard
    Given user login to shopbase dashboard by API
    And Delete all products by API
    And user navigate to "Online Store>Size charts" screen
    And Delete all size chart


  Scenario: Assign size chart to product #SB_OLS_SC_37 #SB_OLS_SC_38 #SB_OLS_SC_39 #SB_OLS_SC_40 #SB_OLS_SC_41 #SB_OLS_SC_42 #SB_OLS_SC_44 #SB_OLS_SC_45 #SB_OLS_SC_46 #SB_OLS_SC_49 #SB_OLS_SC_50 #SB_OLS_SC_48
    Given user navigate to "Products>All products" screen
    And Delete all products
    And Add a new product with data
      | Title      | Description                                                            | Product type | Vendor |
      | product_01 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo |
    And view product on store front
    When verify show size chart on store front
      | Show size chart |
      | false           |
    And user navigate to "Online Store>Size charts" screen
    And Add a new size chart with data
      | Style   | Images    | Message                                                                                |
      |         | BD_2.png  | The image you are choosing is too large. Please choose another one that is under 2 MB. |
      | chart_1 | Logo1.jpg |                                                                                        |
    And Assign size chart to product
      | Conditions                                          |
      | All conditions,Product title,is equal to,product_01 |
    Then Switch to the behind tab
    And verify show size chart on store front
      | Show size chart |
      | false           |
    And user navigate to "Online Store>Size charts" screen
    When Enable size chart widget
    And Switch to the behind tab
    Then verify show size chart on store front
      | Show size chart |
      | true            |


  Scenario: Add and remove size chart to product #SB_OLS_SC_51 #SB_OLS_SC_52 #SB_OLS_SC_53 #SB_OLS_SC_54
    Given Add a new size chart with data
      | Style   | Images    | Description                       |
      | chart_2 | Logo1.jpg | This is size chart of the product |
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title      | Description                                                            | Product type | Vendor |
      | product_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo |
    And user navigate to "Online Store>Size charts" screen
    When Enable size chart widget
    And user navigate to "Products>All products" screen
    And Search product "product_02" on All product screen
    And add or remove tags to product "product_02" with action "Add tags"
    Then redirect to product detail on Storefront
    And verify show size chart on store front
      | Show size chart | Title   | Description                       |
      | true            | chart_2 | This is size chart of the product |
    And user navigate to "Products>All products" screen
    When Search product "product_02" on All product screen
    And add or remove tags to product "product_02" with action "Remove tags"
    And Switch to the behind tab
    Then verify show size chart on store front
      | Show size chart |
      | false           |
    And quit browser


  Scenario: Add tag of base product pbase for product shopbase
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title      | Description                                                            | Product type | Vendor | Tags                       |
      | product_02 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. | Shirt        | Uniqlo | sc-v-neck,sc-unisex-tshirt |
    And user navigate to "Online Store>Size charts" screen
    When Enable size chart widget
    Given open shop on storefront
    And search and select the product "product_02"
    And verify show size chart on store front
      | Show size chart | List value size chart         |
      | true            | V-neck T-shirt,Unisex T-shirt |
    And quit browser