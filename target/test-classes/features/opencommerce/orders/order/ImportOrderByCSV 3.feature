Feature: Import Order By CSV
  #import_order_by_csv

  Scenario: Verify when import order success
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Import order sbase by csv
      | data                                                                 | error |
      | @OrderDate,@OrderName,,,Product,White,,,3,,,,,HN,OCG,,,United States |       |
    And Search and Verify info order after import in order list
      | ORDER DATE | PRODUCT NAME | SKU | ERROR |
      |            |              |     |       |
    And Search order import and verify info order import detail
      | title          | Timeline                            |
      | Imported order | imported this order using a csv file |
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Manage Orders>All orders" screen
    Then Search and verify order import had sync app printHub success
      | tab                        |
      | All                        |
      | Other Fulfillment Services |
    And close browser


  Scenario: Verify error when import file csv empty condition obligatory
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Import order sbase by csv
      | data                                                                             | error                                                                                   |
      | 10/10/2021 4:19:20 AM,@OrderName,,,Product,White,,,-6,,,,,HN,OCG,,,United States | Quantity must be a positive number.                                                     |
      | 123456,@OrderName,,,Green,White,,,,,,,5,,,,,HN,OCG,,,United States               | Order date must have mm/dd/yyyy or mm/dd/yyyy hh:mm:ss AM/PM format                     |
      | @OrderDate,@OrderName,,,Green,White,,,,,,,6,,,,,HN,OCG,,,United States           | Order name cannot be blank.                                                             |
      | @OrderDate,@OrderName,,,Green,White,,,,,,,6,,,,,HN,OCG,,,abc                     | Couldn't detect the state/region/province. See a list of valid states/regions/provinces |
