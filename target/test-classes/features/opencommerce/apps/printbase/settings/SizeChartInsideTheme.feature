Feature: Sizechart Inside theme
#  Testcase https://docs.google.com/spreadsheets/d/1E87R1t6FXmuyyxl3SqVThqzoRu9r6zd_rq9CYiJ3ovY/edit#gid=36796374
#  print_on_demand_size_chart

#  Scenario: Delete all product live
#    When delete all campaigns by API

  Scenario Outline: Verify display size chart of Pbase product on Storefront #SB_PRB_SZC_101
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    And verify show size chart when "<Testcase>" at product "<Campaign name>"
    Then verify data of size chart on storefront next as "<KEY>"
      | KEY | Product name     | Style          | Base product   | Unit | Size       | Chest width (Width) | Body length(Length) |
      | 1   | Multiple product | Unisex T-shirt |                |      | S          | 18                  | 28                  |
      | 1   |                  |                |                |      | M          | 20                  | 29                  |
      | 1   |                  | Quilt          |                |      | Single     | 51                  | 59                  |
      | 1   |                  |                |                |      | Twin       | 59                  | 79                  |
      | 1   |                  |                |                |      | Queen      | 70                  | 79                  |
      | 1   |                  |                |                |      | King       | 79                  | 90                  |
      | 1   |                  |                |                |      | Super King | 90                  | 110                 |
      | 1   |                  |                |                | Cm   | Single     | 129.5               | 149.9               |
      | 1   |                  |                |                |      | Twin       | 149.9               | 200.7               |
      | 1   |                  |                | Unisex T-shirt | Inch | S          | 18                  | 28                  |
      | 1   |                  |                |                |      | M          | 20                  | 29                  |
      | 1   |                  |                |                | Cm   | S          | 45.7                | 71.1                |
      | 1   |                  |                |                |      | XL         | 61                  | 78.7                |
    And close browser

    Examples:
      | KEY | Testcase           | Campaign name    |
      | 1   | Enable size chart  | Multiple product |