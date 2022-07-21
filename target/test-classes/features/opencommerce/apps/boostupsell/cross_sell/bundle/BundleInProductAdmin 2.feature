Feature: Integrate bundle into product admin

#  env:us_bundle_in_product_admin


  Scenario: delete all bundle
    Given open page "/admin/apps/boost-upsell/cross-sell/bundle-offer/list"
    And wait page "Bundles" show
    And delete all offers on the table


  Scenario Outline: create bundle in product admin #SB_SF_CSB_1 #SB_SF_CSB_2 #SB_SF_CSB_3
    Given open page "/admin/products"
    And wait page "Products" show
    Then open product "<Product>" on Dashboard
    And click button to create bundle
    And input data to create offer bundle in product admin "<KEY>"
      | KEY | Products                                | Offer's name | Offer's message | isDiscount | Discount | Only show with current product |
      | 001 | product 1,product 2,product 3           | Offer 1      | Offer 1         | true       | 10       | false                          |
      | 002 | product 4                               | Offer 2      | Offer 2         | false      |          | true                           |
      | 003 | Slice Quick & Right,Slice Quick & Right |              | Offer 3         | true       | 20       | false                          |
      | 004 | product 1                               | Offer 4      | Offer 4         | true       | 20       | false                          |
    Then verify offer on modal list offer "<KEY>"
      | KEY | Offer's name                                                            | Status | Products/Collections                    | isShow |
      | 001 | Offer 1                                                                 | true   | product 6,product 1,product 2,product 3 | true   |
      | 002 | Offer 2                                                                 | true   | product 7,product 4                     | true   |
      | 003 | Bundle of Slice Quick & Right, Slice Quick & Right, Slice Quick & Right | true   | Slice Quick & Right                     | true   |
      | 004 | Offer 4                                                                 | true   | product 5,product 1                     | true   |
    Then open list "Bundles" from product admin and switch window
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name                                                            | Target                                  | Status |
      | 001 | Offer 1                                                                 | product 6,product 1,product 2,product 3 | Active |
      | 002 | Offer 2                                                                 | product 7,product 4                     | Active |
      | 003 | Bundle of Slice Quick & Right, Slice Quick & Right, Slice Quick & Right | Slice Quick & Right                     | Active |
      | 004 | Offer 4                                                                 | product 5,product 1                     | Active |
    And open shop on storefront
    Then verify Bundle show "<KEY>"
      | KEY | Target product      | isShow | Bundles list                                                                |
      | 001 | product 6           | true   | Offer 1>4>product 6,product 1,product 2,product 3>72.00                     |
      | 001 | product 1           | true   | Offer 1>4>product 6,product 1,product 2,product 3>72.00                     |
      | 002 | product 4           | false  |                                                                             |
      | 002 | product 7           | true   | Offer 2>2>product 7,product 4>50.00                                         |
      | 003 | Slice Quick & Right | true   | Offer 3>3>Slice Quick & Right,Slice Quick & Right,Slice Quick & Right>24.00 |
      | 004 | product 1           | true   | Offer 4>2>product 5,product 1>24.00                                         |
    And add all to cart bundle "<KEY>"
      | KEY | Bundles |
      | 001 |         |
      | 002 |         |
      | 003 |         |
      | 004 |         |
    Then verify order summary "<KEY>"
      | KEY | Products                                                        | Subtotal | Total price | DisCount Apply      |
      | 001 | product 6>10.00,product 1>20.00,product 2>35.00,product 3>15.00 | 80.00    | 72.00       | Offer Discount>8.00 |
      | 002 | product 7>10.00>product 4>40.00                                 | 50.00    | 50.00       |                     |
      | 003 | Slice Quick & Right>30.00                                       | 30.00    | 24.00       | Offer Discount>6.00 |
      | 004 | product 5>10.00,product 1>20.00                                 | 30.00    | 24.00       | Offer Discount>6.00 |
    Then close browser
    Examples:
      | KEY | Product             | Description       |
      | 001 | product 6           | Has discount      |
      | 002 | product 7           | Not has discount  |
      | 003 | Slice Quick & Right | Case bogo         |
      | 004 | product 5           | product unlisting |


  Scenario Outline: edit bundle in product admin #SB_SF_CSB_4 #SB_SF_CSB_5 #SB_SF_CSB_6
    Given open page "/admin/products"
    And wait page "Products" show
    Then open product "product 6" on Dashboard
    And click button "Edit bundle"
    And input data to edit offer bundle in product admin "<KEY>"
      | KEY | Offer's name | Offer's name edited | Status | Offer's message | isDiscount | Discount | Only show with current product | Products  |
      | 001 | Offer 1      | Offer edited 1      | true   | Offer edited 1  | false      |          |                                | product 3 |
    And open shop on storefront
    Then verify Bundle show "<KEY>"
      | KEY | Target product | isShow | Bundles list                               |
      | 001 | product 2      | false  |                                            |
      | 001 | product 6      | true   | Offer edited 1>2>product 6,product 3>25.00 |
      | 001 | product 3      | true   | Offer edited 1>2>product 6,product 3>25.00 |
    And add all to cart bundle "<KEY>"
      | KEY | Bundles |
      | 001 |         |
    Then verify order summary "<KEY>"
      | KEY | Products                        | Subtotal | Total price | DisCount Apply |
      | 001 | product 6>10.00,product 3>15.00 | 25.00    | 25.00       |                |
    And close browser
    Examples:
      | KEY |
      | 001 |