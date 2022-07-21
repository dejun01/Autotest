Feature: Post purchase SDK

#  environment: us_post_purchase


  Scenario Outline: Delete All Ofer
    Given clear all data
    Given open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Given add products in pages "<Product pages>" to cart then click checkout button
    And checkout by Stripe successfully
    Then verify content of post-purchase offer is shown on storefront "<KEY>"
      | KEY | isShow |
      | 001 | false  |
    When close browser
    Examples:
      | KEY | Product pages                                    |
      | 001 | /products/waiting-for-the-sun-one-piece-swimsuit |


  Scenario Outline: create offer Post purchase #SB_SF_UPOP_1 #SB_SF_UPOP_2 #SB_SF_UPOP_3 #SB_SF_UPOP_4 #SB_SF_UPOP_5 #SB_SF_UPOP_6 #SB_SF_UPOP_7 #SB_SF_UPOP_8 #SB_SF_UPOP_9 #SB_SF_UPOP_10 #SB_SF_UPOP_11 #SB_SF_UPOP_12 #SB_SF_UPOP_14 #SB_SF_UPOP_17 #SB_SF_UPOP_18
    Given clear all data
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Given Description: "<Description>"
    Then click button Create offer
    And input data to create offer "<KEY>"
      | KEY | Offer type    | Offer's name | Offer's message | Offer's title | Target type          | Target products                                                  | Recommend type                               | Recommend products                                  | isDiscount | Discount value |
      | 001 | Post-purchase | Offer 01     | Message 01      | Title 01      | Specific products    | Noty Foldable Camping Chairs,Hats                                | Specific collections                         | dress                                               | true       | 20             |
      | 002 | Post-purchase | Offer 02     | Message 02      | Title 02      | Specific products    | Black shoes,Hats                                                 | Specific products                            | Quick Fold,Spoons,Fruit Fork,Candles,Product unlist | false      |                |
      | 003 | Post-purchase | Offer 03     | Message 03      | Title 03      | Specific products    | Real Clover Necklace,MC Tropical Trip Leaves Printing Bikini Set | Same collection with target products         |                                                     | false      |                |
      | 004 | Post-purchase | Offer 04     | Message 04      | Title 04      | Specific collections | Collection only product                                          | Most relevant products using automated rules |                                                     | true       | 20             |
      | 005 | Post-purchase | Offer 05     | Message 05      | Title 05      | All products         |                                                                  | Specific products                            | Slice Quick & Right,ThermoCup,Slicer,Bracelet       | true       | 40             |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                                                           | Offer type    | Status |
      | 001 | Offer 01     | Noty Foldable Camping Chairs,Hats                                | post-purchase | Active |
      | 002 | Offer 02     | Black shoes,Hats                                                 | post-purchase | Active |
      | 003 | Offer 03     | Real Clover Necklace,MC Tropical Trip Leaves Printing Bikini Set | post-purchase | Active |
      | 004 | Offer 04     | Collection only product                                          | post-purchase | Active |
      | 005 | Offer 05     | Any product                                                      | post-purchase | Active |
    Given add products in pages "<Product pages>" to cart then click checkout button
    And checkout by Stripe successfully
    Then verify content of post-purchase offer is shown on storefront "<KEY>"
      | KEY   | Offer's title | Offer's message | Number product | Recommend products                                  | Recommend type                               | Recommend products in collection | isShow | Custom option | Add To Order Product    | isDiscount | Discount value | isRefresh |
      | 001   | Title 01      | Message 01      | 11             |                                                     | Specific collections                         | dress                            | true   |               | Simple Cold Dresses     | true       | 20             |           |
      | 002   | Title 02      | Message 02      | 5              | Quick Fold,Spoons,Fruit Fork,Candles,Product unlist | Specific products                            |                                  | true   |               | Quick Fold              | false      |                | true      |
      | 003   | Title 03      | Message 03      |                |                                                     | Same collection with target products         | neckale                          | true   |               | MC Vintage Elvish Dress | false      |                |           |
      | 003-1 | Title 03      | Message 03      |                |                                                     | Same collection with target products         | bikini                           | true   |               | MC Vintage Elvish Dress | false      |                |           |
      | 004   | Title 04      | Message 04      |                |                                                     | Most relevant products using automated rules |                                  | true   |               |                         | true       | 20             |           |
      | 005   | Title 05      | Message 05      | 4              | Slice Quick & Right,ThermoCup,Slicer,Bracelet       | Specific products                            |                                  | true   |               | Slice Quick & Right     | true       | 40             |           |
    And order product in post purchase offer as "<KEY>"
      | KEY   | Product name              | Custom option                     | isShow | Offer's title | Offer's message |
      | 001   | Simple Cold Dresses       |                                   | true   |               |                 |
      | 002   |                           |                                   | true   |               |                 |
      | 002   | MC Velvet Party Dress     |                                   | true   | Title 01      | Message 01      |
      | 003   | Mermaid Teardrop Necklace |                                   | true   |               |                 |
      | 003-1 |                           |                                   | true   |               |                 |
      | 004   |                           |                                   | true   |               |                 |
      | 005   | Bracelet:Cloud Grey       | Text: test custom option required | true   |               |                 |
    Then verify order summary "<KEY>"
      | KEY   | Products                                                                 | Subtotal |
      | 001   | Noty Foldable Camping Chairs>26.00,Simple Cold Dresses>35.92             | 61.92    |
      | 002   | Hats>85,MC Velvet Party Dress>30.32                                      | 115.32   |
      | 003   | Mermaid Teardrop Necklace>19.90,Real Clover Necklace>15.20               | 35.10    |
      | 003-1 | MC Tropical Trip Leaves Printing Bikini Set>34.9                         | 34.90    |
      | 004   | MC Wave Halter Bikini Set>39.90                                          | 39.90    |
      | 005   | Bracelet>Text: test custom option required>5.94,Bracelet>Cloud Grey>5.94 | 43.84    |
    And close browser
    Examples:
      | KEY   | Description                                           | Product pages                                      |
      | 001   | offer when percent target, upsell prod                | /products/noty-foldable-camping-chairs             |
      | 002   | offer when target product, upsell product             | /products/hats                                     |
      | 003   | offer when target product, upsell collection          | /products/real-clover-necklace                     |
      | 003-1 | offer when target product, upsell collection          | /products/tropical-trip-leaves-printing-bikini-set |
      | 004   | offer when target collection, upsell relevant product | /products/wave-halter-bikini-set                   |
      | 005   | offer when discount, target all, upsell product       | /products/waiting-for-the-sun-one-piece-swimsuit   |


  Scenario Outline: Verify icon close, No thanks offer post purchase #SB_SF_UPOP_15 #SB_SF_UPOP_16
    Given clear all data
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Given Description: "<Description>"
    Then setting priority offer on the dashboard "<KEY>"
      | KEY | Offer's name | Priority |
      | 002 | Offer 03     | 1        |
      | 002 | Offer 05     | 1        |
      | 003 | Offer 03     | 1        |
      | 003 | Offer 05     | 2        |
    Given add products in pages "<Product pages>" to cart then click checkout button
    And checkout by Stripe successfully
    Then verify content of post-purchase offer is shown on storefront "<KEY>"
      | KEY | isShow | Offer's title | Close | No thanks | showPopup | Offer's title after closing |
      | 001 | true   | Title 05      | true  |           | false     |                             |
      | 002 | true   | Title 05      |       | true      | true      | Title 03                    |
      | 003 | true   | Title 03      |       | true      | true      | Title 05                    |
    When close browser
    Examples:
      | KEY | Product pages                  | Description                                                             |
      | 001 | /products/real-clover-necklace | Close offer post purchase                                               |
      | 002 | /products/real-clover-necklace | No thanks offer post purchase when the priority of offer is the same    |
      | 003 | /products/real-clover-necklace | No thanks  offer post purchase when the priority of offer is difference |


  Scenario Outline: edit offer Post purchase  #SB_SF_UPOP_13
    Given clear all data
    Then open page "/admin/apps/boost-upsell/up-sell/list"
    And wait page "Upsell offers" show
    Given Description: "<Description>"
    Then active or inactive offer with "<KEY>"
      | KEY | Action           | Offers   | Notices                              | Status   |
      | 001 | Deactivate offer | all      | Offers were deactivated successfully | Inactive |
      | 001 | Activate offer   | Offer 01 | Offer was activated successfully     | Active   |
    Then open offer "<KEY>"
      | KEY | Offer's name |
      | 001 | Offer 01     |
    And input data to create offer "<KEY>"
      | KEY | Offer type    | Offer's name  | Offer's message | Offer's title | Target type       | Target products | Recommend type    | Recommend products | isDiscount | Discount value |
      | 001 | Post-purchase | Offer 01 edit | Message 01      | Title 01      | Specific products | Mug             | Specific products | Hats               | true       | 20             |
    Then click button "Save" on app
    Then back to list upsell offers
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name  | Target | Offer type    | Status |
      | 001 | Offer 01 edit | Mug    | post-purchase | Active |
    Given add products in pages "<Product pages>" to cart then click checkout button
    And checkout by Stripe successfully
    Then verify content of post-purchase offer is shown on storefront "<KEY>"
      | KEY | Offer's title | Offer's message | Number product | Recommend products | Recommend type    | Recommend products in collection | isShow | Custom option | Add To Order Product | isDiscount | Discount value | isRefresh |
      | 001 | Title 01      | Message 01      | 1              | Hats               | Specific products |                                  | true   |               | Hats                 | true       | 20             |           |
    And order product in post purchase offer as "<KEY>"
      | KEY | Product name | Custom option | isShow | Offer's title | Offer's message |
      | 001 | Hats         |               | true   |               |                 |
    Then verify order summary "<KEY>"
      | KEY | Products             | Subtotal |
      | 001 | Mug>24.32,Hats>68.00 | 92.32    |
    And close browser
    Examples:
      | KEY | Description                                 | Product pages             |
      | 001 | edit offer when percent target, upsell prod | /products/novelty-cat-mug |
