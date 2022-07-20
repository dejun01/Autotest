Feature: Custom filter
  #custom_filter

  Scenario Outline: Enable and Disable filter on theme editor
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    When open preview page "Collection"
    And open section setting "Collection"
    And setting status filter "<KEY>"
      | KEY | Enable filtering       | Filter title         |
      | 1   | false                  |                      |
      | 2   | true                   | Filter test          |
    And save change setting
    Given open shop on storefront
    And open page "/collections/collection-custom-filter"
    Then verify show filter on storefront "<KEY>"
      | KEY | Status       | Filter title         |
      | 1   | false        |                      |
      | 2   | true         | Filter test          |
    Examples:
      | KEY |
      | 1   |
      | 2   |


  Scenario Outline: Remove and add option filter
    Given open page "/admin/menus"
    When setting option filter "<KEY>"
      | KEY | Action            |
      | 1   | Remove all        |
      | 2   | Add all           |
    Given open page "/collections/collection-custom-filter"
    Then verify show option in drawer filter "<KEY>"
      | KEY | Status       |
      | 1   | false        |
      | 2   | true         |
    Examples:
      | KEY |
      | 1   |
      | 2   |

  Scenario Outline: Filter on SF
    Given open page "/collections/collection-custom-filter"
    When click droplist Filter
    And filter option on SF "<KEY>"
      | KEY | availability | price from | price to   | product type  | brand    | color        | size     | style     | Clear all | Apply    |
      | 1   |              |            |            | type 1        |          |              |          |           | true      |          |
      | 2   |              |            |            | type 1        |          |              |          |           |           | true     |
      | 3   | true         | 40         | 70         | type 1        | vendor 2 | black        | s        |           |           | true     |
      | 4   | true         |            | 70         | type 1,type 2 |          |              | s        |           |           | true     |
      | 5   | false        | 45         |            |               |          | transparent  |          |           |           | true     |
    Then verify tag filter "<KEY>"
      | KEY | Tags                                                                                                     |
      | 1   |                                                                                                          |
      | 2   | product type: type 1                                                                                     |
      | 3   | availability: Available only,price: 40 - 70 USD,product type: type 1,Brand: vendor 2,color: black,size: s|
      | 4   | availability: Available only,price: Less than 70 USD,product type: type 2,product type: type 1,size: s   |
      | 5   | price: More than 45 USD,color: transparent                                                               |
    And verify filter results "<KEY>"
      | KEY | Result                                                                                                                |
      | 1   | Product many variant,Product not variant out stock,Product has a out stock variant,Product not variant,Other product  |
      | 2   | Product many variant,Product has a out stock variant                                                                  |
      | 3   | Product many variant                                                                                                  |
      | 4   | Product many variant,Product has a out stock variant                                                                  |
      | 5   | No matching products found                                                                                            |
    Examples:
      | KEY | Description                                                                     |
      | 1   | Filter when click button Clear all                                              |
      | 2   | Filter with 1 option                                                            |
      | 3   | Exact filter with many option, 1 result                                         |
      | 4   | Exact filter with many option, many result                                      |
      | 5   | Filter with no results                                                          |



