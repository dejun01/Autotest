Feature: Quantity discount in product admin
#  env: us_quantity_discount_in_product_admin

#  Data test
#  products: Slice Quick & Right>10.00, Quick Fold>30.00, MC Vestido Summer Dress>20.00



  Scenario: delete all quantity discount
    Given open page "/admin/apps/boost-upsell/cross-sell/quantity-offer/list"
    And wait page "Quantity discounts" show
    And delete all offers on the table


  Scenario Outline: create quantity discount #SB_SF_CSQD_1 #SB_SF_CSQD_2
    Given open page "/admin/products"
    And wait page "Products" show
    Then open product "<Product>" on Dashboard
    And click button to create quantity discount in product admin
    And input data to create offer quantity discount "<KEY>"
      | KEY | Offer's name | Offer's message | Discount quantity                                             |
      | 001 | Offer 1      | Message 1       | 2>5>percentage_each,3>10>percentage_each,4>15>percentage_each |
      | 002 | Offer 2      | Message 2       | 3>5>amount_each,4>6>amount_each,5>7>amount_each               |
      | 003 | Offer 3      | Message 3       | 2>5>percentage_each,3>3>amount_each,4>10>amount_all           |
    And click "Add quantity discounts" button on popup
    Then verify offer on modal list offer "<KEY>"
      | KEY | Offer's name | Status | isShow |
      | 001 | Offer 1      | true   | true   |
      | 002 | Offer 2      | true   | true   |
      | 003 | Offer 3      | true   | true   |
    Then open list "Quantity discounts" from product admin and switch window
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                  | Status |
      | 001 | Offer 1      | Slice Quick & Right     | Active |
      | 002 | Offer 2      | MC Vestido Summer Dress | Active |
      | 003 | Offer 3      | Quick Fold              | Active |
    And open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                 | isShowOffer | Message Offer | Discount                                                          |
      | 001 | Slice Quick & Right     | true        | Message 1     | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |
      | 002 | MC Vestido Summer Dress | true        | Message 2     | 3 items get $5.00 OFF,4 items get $6.00 OFF,5 items get $7.00 OFF |
      | 003 | Quick Fold              | true        | Message 3     | 2 items get 5% OFF,3 items get $3.00 OFF,4 items get $10.00       |
    Then add to cart quantity discount "<KEY>"
      | KEY | isAddToCart | Quantity | Custom option |
      | 001 | true        | 2        |               |
      | 002 | true        | 3        |               |
      | 003 | true        | 4        |               |
    Then verify detail on cart page "<KEY>"
      | KEY | Products                | Message discount                                                                                  | Discount code        | Subtotal |
      | 001 | Slice Quick & Right     | Wonderful! We have chosen the most valuable discount for you. Enjoy $1.00 off on the total bill.  | OFFER DISCOUNT>1.00  | 19.00    |
      | 002 | MC Vestido Summer Dress | Wonderful! We have chosen the most valuable discount for you. Enjoy $15.00 off on the total bill. | OFFER DISCOUNT>15.00 | 45.00    |
      | 003 | Quick Fold              | Wonderful! We have chosen the most valuable discount for you. Enjoy $80.00 off on the total bill. | OFFER DISCOUNT>80.00 | 40.00    |
    And click to button checkout
    Then verify order summary "<KEY>"
      | KEY | Products                      | Subtotal | Total price | DisCount Apply       |
      | 001 | Slice Quick & Right>20.00     | 20.00    | 19.00       | OFFER DISCOUNT>1.00  |
      | 002 | MC Vestido Summer Dress>60.00 | 60.00    | 45.00       | OFFER DISCOUNT>15.00 |
      | 003 | Quick Fold>120.00             | 120.00   | 40.00       | OFFER DISCOUNT>80.00 |
    Then close browser
    Examples:
      | KEY | Product                 |
      | 001 | Slice Quick & Right     |
      | 002 | MC Vestido Summer Dress |
      | 003 | Quick Fold              |


  Scenario Outline: Edit quantity discount #SB_SF_CSQD_3
    Given open page "/admin/products"
    And wait page "Products" show
    Then open product "<Product>" on Dashboard
    And click button "Edit discounts"
    Then select quantity discount "<Offer name>"
    And input data to create offer quantity discount "<KEY>"
      | KEY | Offer's name   | Offer's message  | Target type | Target products | Discount quantity                               |
      | 001 | Offer edited 1 | Message edited 1 |             |                 | 3>5>amount_each,4>6>amount_each,5>7>amount_each |
    And click "Save changes" button on popup
    Then verify offer on modal list offer "<KEY>"
      | KEY | Offer's name   | Status | isShow |
      | 001 | Offer edited 1 | true   | true   |
    Then open list "Quantity discounts" from product admin and switch window
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name   | Target              | Status |
      | 001 | Offer edited 1 | Slice Quick & Right | Active |
    And open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product             | isShowOffer | Message Offer    | Discount                                                          |
      | 001 | Slice Quick & Right | true        | Message edited 1 | 3 items get $5.00 OFF,4 items get $6.00 OFF,5 items get $7.00 OFF |
    Examples:
      | KEY | Product             | Offer name |
      | 001 | Slice Quick & Right | Offer 1    |


  Scenario Outline: delete quantity discount #SB_SF_CSQD_4
    Given open page "/admin/products"
    And wait page "Products" show
    Then open product "<Product>" on Dashboard
    And click button "Edit discounts"
    Then select quantity discount "<Offer name>"
    And delete offer on modal
    Then verify offer on modal list offer "<KEY>"
      | KEY | Offer's name | isShow |
      | 001 | Offer 2      | false  |
    And open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                 | isShowOffer |
      | 001 | MC Vestido Summer Dress | false       |
    Examples:
      | KEY | Product                 | Offer name |
      | 001 | MC Vestido Summer Dress | Offer 2    |


  Scenario Outline: verify quantity work after inactive offer
    Given open page "/admin/products"
    And wait page "Products" show
    Then open product "<Product>" on Dashboard
    And click button "Edit discounts"
    And active or inactive offer "<KEY>"
      | KEY | Offer   | Status   |
      | 001 | Offer 3 | Inactive |
    And open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product    | isShowOffer |
      | 001 | Quick Fold | false       |
    Examples:
      | KEY | Product    |
      | 001 | Quick Fold |


  Scenario Outline: create offer in app #SB_SF_CSQD_5 #SB_SF_CSQD_6
    Given open page "/admin/apps/boost-upsell/cross-sell/quantity-offer/list"
    And wait page "Quantity discounts" show
    Then click button Create offer
    And input data to create offer quantity discount "<KEY>"
      | KEY | Offer's name | Offer's message | Target type       | Target products         | Discount quantity                                              |
      | 001 | Offer 4      | Message 4       | All products      |                         | 2>5>percentage_each,3>10>percentage_each,4>15>percentage_each  |
      | 002 | Offer 5      | Message 5       | Specific products | MC Vestido Summer Dress | 2>10>percentage_each,3>15>percentage_each,4>20>percentage_each |
      | 003 | Offer 6      | Message 6       | Specific products | Slice Quick & Right     | 3>5>amount_each,4>6>amount_each,5>7>amount_each                |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                  | Status |
      | 001 | Offer 4      | Any product             | Active |
      | 002 | Offer 5      | MC Vestido Summer Dress | Active |
      | 003 | Offer 6      | Slice Quick & Right     | Active |
    Then user navigate to "Products" screen
    Then open product "Slice Quick & Right" on Dashboard
    And click button "Edit discounts"
    And verify offer on modal list offer "<KEY>"
      | KEY | Offer's name | isShow | Status |
      | 001 | Offer 4      | false  |        |
      | 002 | Offer 5      | false  |        |
      | 003 | Offer 6      | true   | true   |
    And open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                 | isShowOffer | Message Offer | Discount                                                          |
      | 001 | Slice Quick & Right     | true        | Message 4     | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |
      | 002 | MC Vestido Summer Dress | true        | Message 5     | 2 items get 10% OFF,3 items get 15% OFF,4 items get 20% OFF       |
      | 003 | Slice Quick & Right     | true        | Message 6     | 3 items get $5.00 OFF,4 items get $6.00 OFF,5 items get $7.00 OFF |
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |
