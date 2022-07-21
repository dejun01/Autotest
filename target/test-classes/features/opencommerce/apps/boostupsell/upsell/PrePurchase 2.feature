Feature: Pre purchase

#  Env: us_pre_purchase


  Background:
    Given clear all data

  Scenario Outline: Delete All Ofer #SB_SF_UPRP_18
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Upsell" screen
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product               | isShow |
      | 1   | Noty Foldable Camping Chairs | false  |
    Then close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Verify offer Pre purchase #SB_SF_UPRP_2 #SB_SF_UPRP_3 #SB_SF_UPRP_4 #SB_SF_UPRP_5 #SB_SF_UPRP_6 #SB_SF_UPRP_7 #SB_SF_UPRP_8 #SB_SF_UPRP_9 #SB_SF_UPRP_11 #SB_SF_UPRP_13 #SB_SF_UPRP_10 #SB_SF_UPRP_15 #SB_SF_UPRP_14 #SB_SF_UPRP_20
    Given Description: "<Description>"
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then click button Create offer
    And input data to create offer "<KEY>"
      | KEY | Offer type   | Offer's name | Offer's message | Offer's title | Target type          | Target products       | Recommend type                               | Recommend products                          | isDiscount | Discount value |
      | 001 | Pre-purchase | Offer 1      | Offer 1         |               | Specific products    | Mug                   | Specific products                            | Mug,Hats,Bracelet,Fruit Fork,Product unlist | true       | 30             |
      | 002 | Pre-purchase | Offer 2      | Offer 2         |               | Specific products    | Slicer,ThermoCup,Hats | Specific collections                         | dress                                       | true       | 20             |
      | 003 | Pre-purchase | Offer 4      | Offer 4         |               | Specific collections | neckale               | Specific collections                         | dress                                       | false      |                |
      | 004 | Pre-purchase | Offer 3      | Offer 3         |               | Specific collections | dress                 | Same collection with target products         |                                             | true       | 50             |
      | 005 | Pre-purchase | Offer 5      | Offer 5         |               | Specific products    | Candles               | Most relevant products using automated rules |                                             | true       | 10             |
      | 006 | Pre-purchase | Offer 6      | Offer 6         |               | All products         |                       | Most relevant products using automated rules |                                             | true       | 10             |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                | Offer type   | Status |
      | 001 | Offer 1      | Mug                   | pre-purchase | Active |
      | 002 | Offer 2      | Slicer,ThermoCup,Hats | pre-purchase | Active |
      | 003 | Offer 4      | neckale               | pre-purchase | Active |
      | 004 | Offer 3      | dress                 | pre-purchase | Active |
      | 005 | Offer 5      | Candles               | pre-purchase | Active |
      | 006 | Offer 6      | Any product           | pre-purchase | Active |
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product            | isShow | Message Offer | Number product | Recommend product                                                                                                                                                                                                                         |
      | 001 | Mug                       | true   | Offer 1       | 4              | Hats>59.5,Bracelet>6.93,Fruit Fork>4.89,Product unlist>7.00                                                                                                                                                                               |
      | 002 | Slicer                    | true   | Offer 2       | 11             | MC Vintage Marmalade Lantern Dress>35.92,Spring Floral Print Maxi Dress>27.19,MC Vintage Style Boho Print Maxi Dress>34,MC White Lace Dress>39.92,MC Velvet Party Dress>30.32,MC Vestido Summer Dress>50.32,MC Vintage Elvish Dress>35.92 |
      | 003 | Sea Turtle Necklace       | true   | Offer 4       | 11             | MC Vintage Marmalade Lantern Dress>44.90,Spring Floral Print Maxi Dress>33.99,MC Vintage Style Boho Print Maxi Dress>42.50,MC White Lace Dress>49.90                                                                                      |
      | 004 | MC White Lace Dress       | true   | Offer 3       | 4              | MC Vestido Summer Dress>31.45, MC Vintage Marmalade Lantern Dress>22.45, MC Yellow Beach Summer Dress>17.95, MC Velvet Party Dress>18.95                                                                                                  |
      | 005 | Candles                   | true   | Offer 5       | 12             |                                                                                                                                                                                                                                           |
      | 006 | MC Wave Halter Bikini Set | true   | Offer 6       | 12             |                                                                                                                                                                                                                                           |
    Then add to cart product on pre-purchase popup "<KEY>"
      | KEY | Products                           | isClickCheckoutButton |
      | 001 | Hats,Fruit Fork                    | true                  |
      | 002 | MC Vintage Marmalade Lantern Dress | true                  |
      | 003 | Spring Floral Print Maxi Dress     | true                  |
      | 004 | MC Velvet Party Dress              | true                  |
    Then open cart page
    Then click to button checkout
    Then verify order summary "<KEY>"
      | KEY | Products                                                  | Subtotal | Total price | DisCount Apply       |
      | 001 | Mug>Black>24.32,Fruit Fork>6.99,Hats>L / Black>85.00      | 116.31   | 88.71       | Offer Discount>27.60 |
      | 002 | MC Vintage Marmalade Lantern Dress>S>44.90                | 54.89    | 45.91       | Offer Discount>8.98  |
      | 003 | Spring Floral Print Maxi Dress>33.99                      | 56.49    | 56.49       |                      |
      | 004 | MC White Lace Dress>S>49.90,MC Velvet Party Dress>S>37.90 | 87.80    | 68.85       | Offer Discount>18.95 |
    And close browser
    Examples:
      | KEY | Description                                                                 |
      | 001 | Offer work with discount target product, recommend product + product unlist |
      | 002 | Offer work with target product, recommend collection                        |
      | 003 | Offer work with not discount, collections                                   |
      | 004 | Offer work with target collection,recommend same collection                 |
      | 005 | Offer work with  product, most relevent product                             |
      | 006 | Offer work with any product, most relevent product                          |


  Scenario Outline: Verify offer work when deactive and active offer #SB_SF_UPRP_17 #SB_SF_UPRP_19
    Given Description: "<Description>"
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then active or inactive offer with "<KEY>"
      | KEY | Action           | Offers  | Notices                              | Status   |
      | 001 | Deactivate offer | all     | Offers were deactivated successfully | Inactive |
      | 002 | Activate offer   | Offer 1 | Offer was activated successfully     | Active   |
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product | isShow | Message Offer | Number product | Recommend product                                           |
      | 001 | Mug            | false  | Offer 1       |                |                                                             |
      | 002 | Mug            | true   | Offer 1       | 4              | Hats>59.5,Bracelet>6.93,Fruit Fork>4.89,Product unlist>7.00 |
    Then close browser
    Examples:
      | KEY | Description    |
      | 001 | Inactive offer |
      | 002 | Active offer   |


  Scenario Outline: Verify discount offer when remove product on cart #SB_SF_UPRP_18 #SB_SF_UPRP_12
    Given Description: "<Description>"
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product | isShow | Message Offer | Number product | Recommend product         |
      | 001 | Mug            | true   | Offer 1       | 4              | Hats>59.5,Fruit Fork>4.89 |
      | 002 | Mug            | true   | Offer 1       | 4              | Hats>59.5,Fruit Fork>4.89 |
    Then add to cart product on pre-purchase popup "<KEY>"
      | KEY | Products        | isClickCheckoutButton |
      | 001 | Hats,Fruit Fork | false                 |
      | 002 | Hats,Fruit Fork | false                 |
    Then open cart page
    Then update product on cart page "<KEY>"
      | KEY | Product    | Variant | Quantity | Sutotal | Message discount                                                                                                                | Discount            |
      | 001 | Mug        | Black   | 0        | 91.99   |                                                                                                                                 |                     |
      | 002 | Hats       |         | 0        | 29.21   | Congrats! You have received a $2.10 discount. Only one discount can be applied so we have chosen the most valuable one for you. | Offer Discount,2.10 |
      | 002 | Fruit Fork |         | 2        | 36.20   | Congrats! You have received a $2.10 discount. Only one discount can be applied so we have chosen the most valuable one for you. | Offer Discount,2.10 |
    Then click to button checkout
    Then verify order summary "<KEY>"
      | KEY | Products                             | Subtotal | Total price | DisCount Apply            |
      | 001 | Fruit Fork>6.99,Hats>L / Black>85.00 | 91.99    | 91.99       |                           |
      | 002 | Mug>Black>24.32,Fruit Fork>13.98     | 38.30    | 36.20       | Offer Discount>2.10       |
    Then close browser
    Examples:
      | KEY | Description                                      |
      | 001 | Remove target product                            |
      | 002 | Remove upsell product and update quantity upsell |


  Scenario Outline: Add product custom with custom option #SB_SF_UPRP_1
    Given Description: "<Description>"
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product | isShow | Message Offer | Number product | Recommend product                                           |
      | 001 | Mug            | true   | Offer 1       | 4              | Hats>59.5,Bracelet>6.93,Fruit Fork>4.89,Product unlist>7.00 |
    Then add to cart product on pre-purchase popup "<KEY>"
      | KEY | Custom option                             | Variant    | Products | isClickCheckoutButton |
      | 001 | Bracelet>Text:test custom option required | Cloud Grey | Bracelet | true                  |
    Then verify order summary "<KEY>"
      | KEY | Products                                                                                 | Subtotal | Total price | DisCount Apply      |
      | 001 | Mug>Black>24.32,Bracelet>Text: test custom option required>9.90,Bracelet>Cloud Grey>9.90 | 34.22    | 31.25       | Offer Discount>2.97 |
    And checkout by Stripe successfully
    Then verify order summary "<KEY>"
      | KEY | Products                                                                                 | Subtotal | Total price | DisCount Apply      |
      | 001 | Mug>Black>24.32,Bracelet>Text: test custom option required>9.90,Bracelet>Cloud Grey>9.90 | 34.22    |             | Offer Discount>2.97 |
    Examples:
      | KEY | Description                                                   |
      | 001 | Add upsell product with product custom option, choose variant |


  Scenario Outline: Verify discount offer will take higher discount offer when add product in many popup pre-purchase #SB_SF_UPRP_14
    Given Description: "<Description>"
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then active or inactive offer with "<KEY>"
      | KEY | Action           | Offers  | Notices                              | Status   |
      | 001 | Deactivate offer | all     | Offers were deactivated successfully | Inactive |
      | 001 | Activate offer   | Offer 1 | Offer was activated successfully     | Active   |
      | 001 | Activate offer   | Offer 2 | Offer was activated successfully     | Active   |
    Given open shop on storefront
    Then add to cart product on many pre-purchase popup
      | Target products           | Recommend product       | DisCount Apply      |
      | /products/novelty-cat-mug | Fruit Fork              | Offer Discount>0.7  |
      | /products/hats            | MC Vintage Elvish Dress | Offer Discount>8.98 |
    Then verify order summary "<KEY>"
      | KEY | Products                                                           | Subtotal | Total price | DisCount Apply      |
      | 001 | Mug>24.32,Fruit Fork>6.99,Hats>85.00,MC Vintage Elvish Dress>44.90 | 161.21   | 152.23      | Offer Discount>8.98 |
    Examples:
      | KEY | Description                                    |
      | 001 | Add to cart product on many pre-purchase popup |


  Scenario Outline: Verify edit offer Pre purchase #SB_SF_UPRP_16
    Given Description: "<Description>"
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then active or inactive offer with "<KEY>"
      | KEY | Action           | Offers  | Notices                              | Status   |
      | 001 | Deactivate offer | all     | Offers were deactivated successfully | Inactive |
      | 001 | Activate offer   | Offer 1 | Offer was activated successfully     | Active   |
    Then open offer "<KEY>"
      | KEY | Offer's name |
      | 001 | Offer 1      |
    And input data to create offer "<KEY>"
      | KEY | Offer type   | Offer's name | Offer's message | Offer's title | Target type       | Target products | Recommend type    | Recommend products                              | isDiscount | Discount value |
      | 001 | Pre-purchase | Offer 1 edit | Offer 1 edit    |               | Specific products | Hats            | Specific products | Mug,Hats,Bracelet,Fruit Fork,Pineapple Earrings | true       | 30             |
    Then click button "Save" on app
    Then back to list upsell offers
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target | Offer type   | Status |
      | 001 | Offer 1 edit | Hats   | pre-purchase | Active |
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product | isShow | Message Offer | Number product | Recommend product                                               |
      | 001 | Hats           | true   | Offer 1 edit  | 4              | Mug>17.02,Bracelet>6.93,Fruit Fork>4.89,Pineapple Earrings>9.03 |
    Then add to cart product on pre-purchase popup "<KEY>"
      | KEY | Products       | isClickCheckoutButton |
      | 001 | Mug,Fruit Fork | true                  |
    Then open cart page
    Then click to button checkout
    Then verify order summary "<KEY>"
      | KEY | Products                                             | Subtotal | Total price | DisCount Apply      |
      | 001 | Hats>L / Black>85.00,Mug>Black>24.32,Fruit Fork>6.99 | 116.31   | 106.91      | Offer Discount>9.40 |
    And close browser
    Examples:
      | KEY | Description                                                     |
      | 001 | Edit Offer work with discount target product, recommend product |
