Feature: Import product in catalog
#plusbase_dashboard

  Background: Access catalog page
    Given user login to shopbase dashboard
    When user navigate to "Catalog" screen

  Scenario: 1. Verify import 1 product
    Given search and move to product detail with product = "(Test-Manual)Black Dress for Women 2021 Summer"
    And click button import to store
    And verify product import with
      | Product     | Cost | Selling price | Compare at price | Profit margin |
      | Apple watch | 50   | 120           | 156              | 40            |
    And verify product in product tab on store
      | Product     | Variant                  | Selling price |
      | Apple watch | style 1 pink:black:white | 120:120:120   |
    And quit browser

  Scenario: 2. Verify import 1 product with variant
    Given search and move to product detail with product = "(Test-Manual)Black Dress for Women 2021 Summer"
    And click button import to store
    And verify product import with
      | Product     | Cost | Selling price | Compare at price | Profit margin | Price variant |
      | Apple watch | 50   | 120           | 156              | 40            | 180           |
    And verify product in product tab on store
      | Product     | Variant | Selling price |
      | Apple watch | black   | 180           |
    And quit browser

  Scenario: 3. Verify import multi product
    Given search and move to product detail with product = "Apple watch"
    And click button import to store
    When move to "Catalog" screen in plusbase
    Given search and move to product detail with product = "[Plusbase]Imar Copa - K100704"
    And click button import to store
    And verify product import with
      | Product                       | Cost | Selling price | Compare at price | Profit margin |
      | [Plusbase]Imar Copa - K100704 | 5    | 10            | 13               | 15            |
      | Apple watch                   | 50   | 120           | 156              | 40            |
    And verify import multi product with
      | Product                       |
      | Apple watch                   |
      | [Plusbase]Imar Copa - K100704 |
    When user navigate to "Products" screen
    And delete product name = "Apple watch"
    When user navigate to "Products" screen
    And delete product name = "[Plusbase]Imar Copa - K100704"

  Scenario Outline: 4. verify import product markup
    When move to "Import List" screen in plusbase
    Given Remove all product in Import list page
    When move to "Catalog" screen in plusbase
    And search and move to product detail with product = "<Product>"
    And get shipping and selling price current with
      | Color | Size |
      | White | L    |
    And click button import to store
    And Change shipping order with
      | Product   | Variant   | First item   | Selling price   |
      | <Product> | <Variant> | <First item> | <Selling price> |
    And close browser

    Examples:
      | Product                         | Variant | First item | Selling price |
      | Plus Size Cut Out - Adjusted= 0 | White/L |            |               |
      | Plus Size Cut Out - Adjusted= 0 | White/L |            | 40            |
      | Plus Size Cut Out - Adjusted= 0 | White/L | 20         | 40            |

