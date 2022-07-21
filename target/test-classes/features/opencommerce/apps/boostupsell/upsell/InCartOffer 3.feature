Feature: In-Cart offer

# Env: us_incart_offer
#  Setting: without currency


  Scenario Outline: verify recommend product by in cart offers #SB_BUS_ICO_11 #SB_BUS_ICO_7 #SB_BUS_ICO_6 #SB_BUS_ICO_5 #SB_BUS_ICO_3 #SB_BUS_ICO_2 #SB_BUS_ICO_1
    Given open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Then create offer and click button "Submit offer" "<KEY>"
      | KEY | Offer type | Offer's name | Offer's message | Offer's title | Target type          | Target products                          | Recommend type            | Recommend products                                                | isDiscount | Discount value | Recommend variant with |
      | 001 | In-cart    | Offer 1      |                 |               | All products         |                                          | Specific products         | MC Vestido Summer Dress,MC Velvet Party Dress                     |            |                | highest-price          |
      | 002 | In-cart    | Offer 2      |                 |               | Specific products    | Portable Power Floss,Power Floss+ System | Specific products         | MC Vestido Summer Dress,MC Velvet Party Dress,Slice Quick & Right |            |                | lowest-price           |
      | 006 | In-cart    | Offer 10     |                 |               | Specific collections | collection camp                          | Specific by base category | Home & Living,Apparel                                             |            |                | lowest-price           |
      | 007 | In-cart    | Offer 11     |                 |               | Specific collections | collection camp                          | Specific by base category | Home & Living,Apparel                                             |            |                | highest-price          |
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                                   | Offer type | Status |
      | 001 | Offer 1      | Any product                              | in-cart    | Active |
      | 002 | Offer 2      | Portable Power Floss,Power Floss+ System | in-cart    | Active |
      | 006 | Offer 10     | collection camp                          | in-cart    | Active |
      | 007 | Offer 11     | collection camp                          | in-cart    | Active |
    Given add products in pages "<Product pages>" and switch to Cart
    And verify in-cart offer work on SF "<KEY>"
      | KEY | Target product       | Target variant                                            | Recommend product       | Recommend variant                                  | Price  | Recommend type            | isAddToCart |
      | 001 | Slice Quick & Right  |                                                           | MC Vestido Summer Dress | L                                                  | $63.90 | Specific products         | true        |
      | 001 | Slice Quick & Right  |                                                           | MC Velvet Party Dress   | M                                                  | $39.00 | Specific products         | false       |
      | 002 | Power Floss+ System  |                                                           | MC Velvet Party Dress   | S                                                  | $37.90 | Specific products         | false       |
      | 002 | Portable Power Floss |                                                           | Slice Quick & Right     |                                                    | $13.00 | Specific products         | true        |
      | 002 | Portable Power Floss |                                                           | MC Velvet Party Dress   | S                                                  | $37.90 | Specific products         | false       |
      | 002 | Power Floss+ System  |                                                           | MC Vestido Summer Dress | M                                                  | $61.90 | Specific products         | false       |
      | 002 | Slice Quick & Right  |                                                           |                         |                                                    |        | Specific products         | false       |
      | 006 | camp 1               | Fruit of the Loom Ladies Heavy Cotton T-Shirt / White / S | camp 1                  | Long Sleeve Baby One Piece / Patriot Blue / 12~18M | $21.00 | Specific by base category | true        |
      | 006 | camp 1               | Long Sleeve Baby One Piece / Patriot Blue / 12~18M        |                         |                                                    |        | Specific by base category | false       |
      | 007 | camp 1               | Fruit of the Loom Ladies Heavy Cotton T-Shirt / White / S | camp 1                  | Long Sleeve Baby One Piece / Patriot Blue / 3~6M   | $27.99 | Specific by base category | true        |
      | 007 | camp 1               | Long Sleeve Baby One Piece / Patriot Blue / 3~6M          |                         |                                                    |        | Specific by base category | false       |
    And close browser
    Examples:
      | KEY | Product pages                                               |
      | 001 | /products/slice-quick-right                                 |
      | 002 | /products/portable-power-floss,/products/power-floss-system |
      | 006 | /products/camp-1                                            |
      | 007 | /products/camp-1                                            |


  Scenario Outline:  verify recommend product by in cart offers
    Given open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Then create offer by API "<KEY>"
      | KEY | Offer type | Offer's name | Offer's message | Offer's title | Target type       | Target products      | Recommend type    | Recommend products                             | isDiscount | Discount value | Recommend variant with |
      | 003 | In-cart    | Offer 3 1    |                 |               | Specific products | Portable Power Floss | Specific products | MC Vestido Summer Dress                        |            |                | highest-price          |
      | 003 | In-cart    | Offer 4  1   |                 |               | Specific products | Power Floss+ System  | Specific products | MC Vestido Summer Dress                        |            |                | highest-price          |
      | 004 | In-cart    | Offer 5      |                 |               | Specific products | Portable Power Floss | Specific products | Silver Plated Cute Cat Ring                    |            |                | lowest-price           |
      | 004 | In-cart    | Offer 6      |                 |               | Specific products | Power Floss+ System  | Specific products | Silver Plated Cute Cat Ring,Pineapple Earrings |            |                | lowest-price           |
      | 005 | In-cart    | Offer 8      |                 |               | Specific products | Power Floss+ System  | Specific products | Portable Power Floss                           |            |                | lowest-price           |
      | 005 | In-cart    | Offer 9      |                 |               | Specific products | Power Floss+ System  | Specific products | MC Vestido Summer Dress                        |            |                | lowest-price           |
    Given add products in pages "<Product pages>" and switch to Cart
    And verify in-cart offer work on SF "<KEY>"
      | KEY | Target product              | Target variant | Recommend product           | Recommend variant | Price  | Recommend type    | isAddToCart |
      | 003 | Power Floss+ System         |                |                             |                   |        | Specific products | false       |
      | 003 | Portable Power Floss        |                | MC Vestido Summer Dress     | L                 | $63.90 | Specific products | true        |
      | 003 | Power Floss+ System         |                |                             |                   |        | Specific products | false       |
      | 003 | Portable Power Floss        |                |                             |                   |        | Specific products | false       |
      | 004 | Power Floss+ System         |                | Pineapple Earrings          | Silver            | $11.90 | Specific products | false       |
      | 004 | Portable Power Floss        |                | Silver Plated Cute Cat Ring | Rose Gold         | $6.99  | Specific products | true        |
      | 004 | Power Floss+ System         |                | Pineapple Earrings          | Silver            | $11.90 | Specific products | false       |
      | 004 | Portable Power Floss        |                |                             |                   |        | Specific products | false       |
      | 004 | Silver Plated Cute Cat Ring | Rose Gold      |                             |                   |        | Specific products | false       |
      | 005 | Power Floss+ System         |                | MC Vestido Summer Dress     | M                 | $61.90 | Specific products | true        |
      | 005 | Power Floss+ System         |                | Portable Power Floss        |                   | $30.00 | Specific products | true        |
      | 005 | Portable Power Floss        |                |                             |                   |        | Specific products | false       |
    And close browser
    Examples:
      | KEY | Product pages                                               |
      | 003 | /products/portable-power-floss,/products/power-floss-system |
      | 004 | /products/portable-power-floss,/products/power-floss-system |
      | 005 | /products/power-floss-system                                |


  Scenario Outline: verify offers work after inactive #SB_BUS_ICO_11
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Then create offer by API "<KEY>"
      | KEY | Offer type | Offer's name | Offer's message | Offer's title | Target type       | Target products                          | Recommend type    | Recommend products                                                | isDiscount | Discount value | Recommend variant with |
      | 001 | In-cart    | Offer 12     |                 |               | All products      |                                          | Specific products | MC Vestido Summer Dress,MC Velvet Party Dress                     |            |                | highest-price          |
      | 002 | In-cart    | Offer 13     |                 |               | Specific products | Portable Power Floss,Power Floss+ System | Specific products | MC Vestido Summer Dress,MC Velvet Party Dress,Slice Quick & Right |            |                | lowest-price           |
    Then refresh page
    And wait page "Upsell offers" show
    Then active or inactive offer with "<KEY>"
      | KEY | Action           | Offers   | Notices                            | Status   |
      | 001 | Deactivate offer | Offer 12 | Offer was deactivated successfully | Inactive |
      | 002 | Deactivate offer | Offer 13 | Offer was deactivated successfully | Inactive |
    Given add products in pages "<Product pages>" and switch to Cart
    Then verify in-cart offer work on SF "<KEY>"
      | KEY | Target product       | Target variant | Recommend variant | Price | Image | Recommend product | isAddToCart |
      | 001 | Slice Quick & Right  |                |                   |       |       |                   |             |
      | 002 | Portable Power Floss |                |                   |       |       |                   |             |
      | 002 | Power Floss+ System  |                |                   |       |       |                   |             |
    And close browser
    Examples:
      | KEY | Product pages                                               |
      | 001 | /products/slice-quick-right                                 |
      | 002 | /products/portable-power-floss,/products/power-floss-system |


  Scenario Outline: verify click area in in cart #SB_BUS_ICO_9 #SB_BUS_ICO_10
    Given Description: "<Description>"
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Then create offer by API "<KEY>"
      | KEY | Offer type | Offer's name | Offer's message | Offer's title | Target type  | Target products | Recommend type    | Recommend products        | isDiscount | Discount value | Recommend variant with |
      | 001 | In-cart    | Offer 1      |                 |               | All products |                 | Specific products | MC Vestido Summer Dress   |            |                | highest-price          |
      | 002 | In-cart    | Offer 2      |                 |               | All products |                 | Specific products | product has custom option |            |                | lowest-price           |
      | 003 | In-cart    | Offer 3      |                 |               | All products |                 | Specific products | Portable Power Floss      |            |                | highest-price          |
    Given add products in pages "<Product pages>" and switch to Cart
    Then check show popup when click area in In-cart SF "<KEY>"
      | KEY | Target product      | Target variant | Recommend product         | Recommend variant | hasCustomOption | isClickOnProductImage | isClickOnProductTitle | isAddToCart |
      | 001 | Slice Quick & Right |                | MC Vestido Summer Dress   | L                 | false           | true                  | true                  | true        |
      | 002 | Slice Quick & Right |                | product has custom option |                   | true            | true                  | true                  | true        |
      | 003 | Slice Quick & Right |                | Portable Power Floss      |                   | false           | true                  | true                  | true        |
    Then close browser
    Examples:
      | KEY | Product pages               | Description                                         |
      | 001 | /products/slice-quick-right | Recommend product has variants                      |
      | 002 | /products/slice-quick-right | Recommend product has custom options                |
      | 003 | /products/slice-quick-right | Recommend product has not variant and custom option |
