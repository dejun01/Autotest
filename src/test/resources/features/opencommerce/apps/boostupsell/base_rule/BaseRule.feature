Feature: Base rule for Upsell offer
#envi: us_base_rule

  Scenario Outline: verify base rule offer work with pre-purchase
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    Then delete all offers on the table
    And user navigate to "Upsell" screen
    Then delete all offers on the table
    Then click button Create offer
    And input data to create rule base offer "<KEY>"
      | KEY | Offer type   | Offer's name                   | Offer's message                | Offer's title | Product target must match | Target rules                                  | Product recommend must match | Recommend rules                                                              | Discount value | isDiscount |
      | 001 | Pre-purchase | Pre-purchase offer base rule 1 | Pre-purchase offer base rule 1 |               | Any conditions            | Product Title>is equal to>Slice Quick & Right | Any conditions               | Product Title>contains>vintage                                               | 30             | true       |
      | 002 | Pre-purchase | Pre-purchase offer base rule 2 | Pre-purchase offer base rule 2 |               | Any conditions            | Product Type>is not equal to>Gadgets          | All conditions               | Collection Name>starts with>collection,Product Title>does not contains>Dress |                | false      |
    Then click button "Submit offer" on app
    And verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name                   | Target            | Offer type   | Status |
      | 001 | Pre-purchase offer base rule 1 | Specific by rules | pre-purchase | Active |
      | 002 | Pre-purchase offer base rule 2 | Specific by rules | pre-purchase | Active |
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product        | isShow | Message Offer                  | Number product | Recommend product                                                                                                                                   |
      | 001 | MC Velvet Party Dress | false  |                                |                |                                                                                                                                                     |
      | 001 | Slice Quick & Right   | true   | Pre-purchase offer base rule 1 | 4              | MC Vintage Elvish Dress>31.43,MC Vintage Marmalade Lantern Dress>31.43,MC Vintage Polkadot Dress>23.03,MC Vintage Style Boho Print Maxi Dress>29.75 |
      | 002 | Quick Fold            | false  |                                |                |                                                                                                                                                     |
      | 002 | Portable Power Floss  | true   | Pre-purchase offer base rule 2 | 2              | Nautical Anchor Bracelet>9.90,Real Clover Necklace>15.20                                                                                            |
    Then add to cart product on pre-purchase popup "<KEY>"
      | KEY | Products                                          | isClickCheckoutButton |
      | 001 | MC Vintage Elvish Dress,MC Vintage Polkadot Dress | true                  |
      | 002 | Nautical Anchor Bracelet                          | true                  |
    Then open cart page
    Then click to button checkout
    Then verify order summary "<KEY>"
      | KEY | Products                                                                                                                               | Subtotal | Total price | DisCount Apply       |
      | 001 | MC Velvet Party Dress>S>37.90,Slice Quick & Right>13.00,MC Vintage Elvish Dress>Mocha Pink / S>44.90,MC Vintage Polkadot Dress>S>32.90 | 128.70   | 105.36      | OFFER DISCOUNT>23.34 |
      | 002 | Portable Power Floss>30.00,Quick Fold>28.90,Nautical Anchor Bracelet>Deep Sea Blue>9.90                                                | 68.80    | 68.80       |                      |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |


  Scenario Outline: verify base rule offer work with post-purchase offer
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    Then delete all offers on the table
    And user navigate to "Upsell" screen
    Then delete all offers on the table
    Then click button Create offer
    And input data to create rule base offer "<KEY>"
      | KEY | Offer type    | Offer's name                    | Offer's title | Offer's message | Product target must match | Target rules                                  | Product recommend must match | Recommend rules                                                              | Discount value | isDiscount |
      | 001 | Post-purchase | Post-purchase offer base rule 1 | Title 01      | Message 01      | Any conditions            | Product Title>is equal to>Slice Quick & Right | Any conditions               | Product Title>contains>vintage                                               | 30             | true       |
      | 002 | Post-purchase | Post-purchase offer base rule 2 | Title 02      | Message 02      | Any conditions            | Product Type>is not equal to>Gadgets          | All conditions               | Collection Name>starts with>collection,Product Title>does not contains>Dress |                | false      |
    Then click button "Submit offer" on app
    And verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name                    | Target            | Offer type    | Status |
      | 001 | Post-purchase offer base rule 1 | Specific by rules | post-purchase | Active |
      | 002 | Post-purchase offer base rule 2 | Specific by rules | post-purchase | Active |
    Given open shop on storefront
    When add products "<Product>" to cart then checkout
    And checkout by Stripe successfully
    Then verify content of post-purchase offer is shown on storefront "<KEY>"
      | KEY | Offer's title | Offer's message | Number product | Recommend products                                                                                                          | Recommend type    | Recommend products in collection | isShow | Custom option | Add To Order Product     | isDiscount | Discount value | isRefresh |
      | 001 | Title 01      | Message 01      | 4              | MC Vintage Elvish Dress,MC Vintage Marmalade Lantern Dress,MC Vintage Polkadot Dress,MC Vintage Style Boho Print Maxi Dress | Specific by rules |                                  | true   |               | MC Vintage Elvish Dress  | true       | 30             |           |
      | 002 | Title 02      | Message 02      | 2              | Nautical Anchor Bracelet,Real Clover Necklace                                                                               | Specific by rules |                                  | true   |               | Nautical Anchor Bracelet | false      |                |           |
    And order product in post purchase offer as "<KEY>"
      | KEY | Product name             | Custom option | isShow | Offer's title | Offer's message |
      | 001 | MC Vintage Elvish Dress  |               | true   |               |                 |
      | 002 | Nautical Anchor Bracelet |               | true   |               |                 |
    Then verify order summary "<KEY>"
      | KEY | Products                                                 | Subtotal |
      | 001 | Slice Quick & Right>13.00,MC Vintage Elvish Dress>31.43  | 44.43    |
      | 002 | Portable Power Floss>30.00,Nautical Anchor Bracelet>9.90 | 39.90    |
    And close browser
    Examples:
      | KEY | Product              |
      | 001 | Slice Quick & Right  |
      | 002 | Portable Power Floss |


  Scenario Outline: verify base rule offer work with quantity discount
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    Then delete all offers on the table
    Then click button Create offer
    And input data to create rule base offer "<KEY>"
      | KEY | Offer type        | Offer's name                  | Offer's message | Target type       | Product target must match | Target rules                                                                 | Discount quantity                                              |
      | 001 | Quantity discount | Quantity discount base rule 1 | Message 01      | Specific by rules | Any conditions            | Product Title>contains>vintage                                               | 2>5>percentage_each,3>10>percentage_each,4>15>percentage_each  |
      | 002 | Quantity discount | Quantity discount base rule 2 | Message 02      | Specific by rules | All conditions            | Collection Name>starts with>collection,Product Title>does not contains>Dress | 2>10>percentage_each,3>15>percentage_each,4>20>percentage_each |
      | 003 | Quantity discount | Quantity discount base rule 3 | Message 03      | Specific by rules | Any conditions            | Product Vendor>is equal to>Sarah,Product Type>contains>Dresses               | 2>5>amount_each,4>6>amount_each,6>7>amount_each                |
      | 004 | Quantity discount | Quantity discount base rule 4 | Message 04      | Specific by rules | Any conditions            | Product Type>is not equal to>Gadgets                                         | 2>5>percentage_each,3>3>amount_each,4>10>amount_all            |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name                  | Target            | Status |
      | 001 | Quantity discount base rule 1 | Specific by rules | Active |
      | 002 | Quantity discount base rule 2 | Specific by rules | Active |
      | 003 | Quantity discount base rule 3 | Specific by rules | Active |
      | 004 | Quantity discount base rule 4 | Specific by rules | Active |
    Then open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                                    | isShowOffer | Message Offer | Discount                                                                      |
      | 001 | MC Velvet Party Dress                      | false       |               |                                                                               |
      | 001 | MC Vintage Elvish Dress                    | true        | Message 01    | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF                    |
      | 002 | MC White Lace Dress                        | false       |               |                                                                               |
      | 002 | Nautical Anchor Bracelet                   | true        | Message 02    | 2 items get 10% OFF,3 items get 15% OFF,4 items get 20% OFF                   |
      | 003 | Push Up Flower Print High Waist Bikini Set | true        | Message 03    | 2 items get $5.00 USD OFF,4 items get $6.00 USD OFF,6 items get $7.00 USD OFF |
      | 003 | Spring Floral Print Maxi Dress             | true        | Message 03    | 2 items get $5.00 USD OFF,4 items get $6.00 USD OFF,6 items get $7.00 USD OFF |
      | 004 | Quick Banana Slicer                        | false       |               |                                                                               |
      | 004 | MC Wave Halter Bikini Set                  | true        | Message 04    | 2 items get 5% OFF,3 items get $3.00 USD OFF,4 items get $10.00 USD           |
    Then add to cart quantity discount "<KEY>"
      | KEY | isAddToCart | Quantity | Custom option |
      | 001 | true        | 2        |               |
      | 002 | true        | 3        |               |
      | 003 | true        | 4        |               |
      | 004 | true        | 4        |               |
    Then verify detail on cart page "<KEY>"
      | KEY | Products                       | Message discount                                                                                                                      | Discount code         | Subtotal |
      | 001 | MC Vintage Elvish Dress        | Congrats! You have received a $4.49 USD discount. Only one discount can be applied so we have chosen the most valuable one for you.   | OFFER DISCOUNT>4.49   | 85.31    |
      | 002 | Nautical Anchor Bracelet       | Congrats! You have received a $4.46 USD discount. Only one discount can be applied so we have chosen the most valuable one for you.   | OFFER DISCOUNT>4.46   | 25.24    |
      | 003 | Spring Floral Print Maxi Dress | Congrats! You have received a $24.00 USD discount. Only one discount can be applied so we have chosen the most valuable one for you.  | OFFER DISCOUNT>24.00  | 111.96   |
      | 004 | MC Wave Halter Bikini Set      | Congrats! You have received a $119.60 USD discount. Only one discount can be applied so we have chosen the most valuable one for you. | OFFER DISCOUNT>119.60 | 40.00    |
    And click to button checkout
    Then verify order summary "<KEY>"
      | KEY | Products                              | Subtotal | Total price | DisCount Apply        |
      | 001 | MC Vintage Elvish Dress>89.80         | 89.80    | 85.31       | OFFER DISCOUNT>4.49   |
      | 002 | Nautical Anchor Bracelet>29.70        | 29.70    | 25.24       | OFFER DISCOUNT>4.46   |
      | 003 | Spring Floral Print Maxi Dress>135.96 | 135.96   | 111.96      | OFFER DISCOUNT>24.00  |
      | 004 | MC Wave Halter Bikini Set>159.60      | 159.60   | 40.00       | OFFER DISCOUNT>119.60 |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |
      | 004 |


  Scenario Outline: verify base rule work with accessory
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    And user navigate to "Accessories" screen
    Then delete all offers on the table
    Then click button Create offer
    And input data to create rule base offer "<KEY>"
      | KEY | Offer type | Offer's name          | Offer's message       | Offer's title | Product target must match | Target rules                                  | Product recommend must match | Recommend rules                                                              |
      | 001 | Accessory  | Accessory base rule 1 | Accessory base rule 1 |               | Any conditions            | Product Title>is equal to>Slice Quick & Right | Any conditions               | Product Title>contains>vintage                                               |
      | 002 | Accessory  | Accessory base rule 2 | Accessory base rule 2 |               | Any conditions            | Product Type>is not equal to>Gadgets          | All conditions               | Collection Name>starts with>collection,Product Title>does not contains>Dress |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name          | Target            | Status |
      | 001 | Accessory base rule 1 | Specific by rules | Active |
      | 002 | Accessory base rule 2 | Specific by rules | Active |
    Given open shop on storefront
    And verify accessory work on sf "<KEY>"
      | KEY | Offer's message       | Recommend Products                                                                                                          | isShow | Target product        |
      | 001 | Accessory base rule 1 | MC Vintage Elvish Dress,MC Vintage Marmalade Lantern Dress,MC Vintage Polkadot Dress,MC Vintage Style Boho Print Maxi Dress | true   | Slice Quick & Right   |
      | 001 |                       |                                                                                                                             | fasle  | MC Velvet Party Dress |
      | 002 | Accessory base rule 2 | Nautical Anchor Bracelet,Real Clover Necklace                                                                               | true   | Portable Power Floss  |
      | 002 |                       |                                                                                                                             | fasle  | Quick Fold            |
    Then close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |