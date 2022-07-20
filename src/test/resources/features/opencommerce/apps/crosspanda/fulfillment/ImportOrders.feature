Feature: Import Order
#crosspanda_import_order

  Scenario Outline: Import with file correctly
    Given Description: "<Testcase>"
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file as "<KEY>"
      | KEY | Product identity | CSV file                   | Template   | Message error                                                                                                                   |
      | 1   | Product name     | xpanda_orders_corectly.csv | CrossPanda | Import CSV file successfully. We're importing orders in the background and will send the result to email autoqa1@crosspanda.com |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "Ready to fulfill"
    Then verify order are imported as "<KEY>"
      | KEY |
      | 1   |
    And delete all order imported in CrossPanda as "<KEY>"
      | KEY |
      | 1   |
    Examples:
      | KEY | Testcase               |
      | 1   | Import orders corectly |


  Scenario Outline: Import with file without collumn
    Given Description: "<Testcase>"
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file without collumn or collumn blank as "<KEY>"
      | KEY | Product identity | CSV file                                       | Message error                                                                                                                   |
      | 1   | Product name     | xpanda_orders_without_collum_designID.csv      | Import CSV file successfully. We're importing orders in the background and will send the result to email autoqa1@crosspanda.com |
      | 2   | Product name     | xpanda_orders_without_collum_designCode.csv    | Import CSV file successfully. We're importing orders in the background and will send the result to email autoqa1@crosspanda.com |
      | 3   | Product name     | xpanda_orders_without_collum_artwork_front.csv | Import CSV file successfully. We're importing orders in the background and will send the result to email autoqa1@crosspanda.com |
      | 4   | Product name     | xpanda_orders_without_collum_mockup.csv        | Import CSV file successfully. We're importing orders in the background and will send the result to email autoqa1@crosspanda.com |
    And user navigate to "My orders" screen in CrossPanda
    And switch to tab "To order"
    Then verify order are imported as "<KEY>"
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
    And delete all order imported in CrossPanda as "<KEY>"
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
    Examples:
      | KEY | Testcase                                   |
      | 1   | Import orders without collum design ID     |
      | 2   | Import orders without collum design code   |
      | 3   | Import orders without collum artwork front |
      | 4   | Import orders without collum mockup        |


  Scenario Outline: Import order fail
    Given Description: "<Testcase>"
    Given login to crosspanda dashboard
    And close smart bar in CrossPanda
    And user navigate to "My orders" screen in CrossPanda
    And import order to CrossPanda by CSV file without collumn or collumn blank as "<KEY>"
      | KEY | Product identity | CSV file                                 | Template   | Message error                                                                                                                                                                                                                                                                                                                                              |
      | 1   | Product name     | xpanda_orders_with_orderID_blank.csv     | CrossPanda | Line 1: ORDER ID can't be blank                                                                                                                                                                                                                                                                                                                            |
      | 2   | Product name     | xpanda_orders_with_productName_blank.csv | CrossPanda | Line 1: PRODUCT NAME can't be blank                                                                                                                                                                                                                                                                                                                        |
      | 3   | Product name     | xpanda_orders_collumns_blank.csv         | CrossPanda | Line 1: QUANTITY can't be blank,Line 1: SHIPPING NAME can't be blank,Line 1: ADDRESS 1 can't be blank,Line 1: CITY can't be blank,Line 1: ZIP/POSTAL CODE can't be blank,Line 1: STATE/PROVINCE/REGION can't be blank,Line 1: COUNTRY can't be blank,Line 2: QUANTITY can't be blank,Line 2: SHIPPING NAME can't be blank,Line 2: ADDRESS 1 can't be blank |
      | 4   | Product name     | xpanda_order_with_quantity_invalid.csv   | CrossPanda | Line 1: QUANTITY is invalid                                                                                                                                                                                                                                                                                                                                |
    Examples:
      | KEY | Testcase                              |
      | 1   | Import orders with order id blank     |
      | 2   | Import orders with product name blank |
      | 3   | Import orders with collumns blank     |
      | 4   | Import orders with quantity invalid   |