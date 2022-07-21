Feature: Sizechart on Storefront
#  Testcase https://docs.google.com/spreadsheets/d/1E87R1t6FXmuyyxl3SqVThqzoRu9r6zd_rq9CYiJ3ovY/edit#gid=36796374
#  prod_pbase_size_chart_sfnext

  Scenario Outline: Verify display size chart of Pbase product on Storefront #SB_PRB_SZC_77 #SB_PRB_SZC_78 #SB_PRB_SZC_79 #SB_PRB_SZC_80 #SB_PRB_SZC_81 #SB_PRB_SZC_82 #SB_PRB_SZC_83 #SB_PRB_SZC_84 #SB_PRB_SZC_85 #SB_PRB_SZC_86 #SB_PRB_SZC_87 #SB_PRB_SZC_88 #SB_PRB_SZC_89 #SB_PRB_SZC_90 #SB_PRB_SZC_91 #SB_PRB_SZC_92 #SB_PRB_SZC_93 #SB_PRB_SZC_94 #SB_PRB_SZC_95 #SB_PRB_SZC_96 #SB_PRB_SZC_99
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    And verify show size chart when "<Testcase>" at product "<Campaign name>"
    Then verify data of size chart on storefront next as "<KEY>"
      | KEY | Product name    | Style          | Base product   | Unit | Size       | Chest width (Width) | Body length(Length) |
      | 1   | <Campaign name> | Unisex T-shirt | Unisex T-shirt |      | S          | 18                  | 28                  |
      | 1   |                 |                |                |      | M          | 20                  | 29                  |
      | 1   |                 | Quilt          |                |      | Single     | 51                  | 59                  |
      | 1   |                 |                |                |      | Twin       | 59                  | 79                  |
      | 1   |                 |                |                |      | Queen      | 70                  | 79                  |
      | 1   |                 |                |                |      | King       | 79                  | 90                  |
      | 1   |                 |                |                |      | Super King | 90                  | 110                 |
      | 1   |                 |                |                | Cm   | Single     | 129.5               | 149.9               |
      | 1   |                 |                |                |      | Twin       | 149.9               | 200.7               |
      | 1   |                 |                | Unisex T-shirt | Inch | S          | 18                  | 28                  |
      | 1   |                 |                |                |      | M          | 20                  | 29                  |
      | 1   |                 |                |                | Cm   | S          | 45.7                | 71.1                |
      | 1   |                 |                |                |      | XL         | 61                  | 78.7                |
      | 2   | <Campaign name> | Unisex T-shirt | Unisex T-shirt |      | S          | 18                  | 28                  |
      | 2   |                 | Quilt          |                |      | Single     | 51                  | 59                  |
      | 2   |                 |                |                | Cm   | Single     | 129.5               | 149.9               |
      | 2   |                 |                |                |      | Twin       | 149.9               | 200.7               |
      | 2   |                 |                | Unisex T-shirt | Inch | S          | 18                  | 28                  |
      | 2   |                 |                |                |      | M          | 20                  | 29                  |
      | 2   |                 |                |                | Cm   | S          | 45.7                | 71.1                |
      | 2   |                 |                |                |      | XL         | 61                  | 78.7                |
    And close browser

    Examples: <KEY>
      | KEY | Testcase                                    | Campaign name        |
      | 1   | Enable size chart                           | Multiple product     |
      | 2   | Enable size chart with campaign personalize | Campaign personalize |