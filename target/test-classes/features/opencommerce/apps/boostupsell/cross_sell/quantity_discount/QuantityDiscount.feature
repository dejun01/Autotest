Feature: Quantity Discount SDK
  As a merchant, I want to set up a volume discount so that I can easily set up different price tiers and start making more sales
#  environment: staging_us_post_purchase
#us_post_purchase_svtest_sfnext

# Product sold out: Portable Power Floss
# Product sold out 1 variant  : Ring Cat - Rose Gold
#  product hidden from listing: MC Tropical Trip Leaves Printing Bikini Set
#  product custom option: Bracelet with required, MoonStone Cat Necklace no required

#  Settings: Add to cart -> redirect to Cart page


  Scenario Outline: US_QTD_1.0 : Delete All Ofer
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    And user navigate to "Quantity discounts" screen
    Then delete all offers on the table
    Then open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                   | isShowOffer | Message Offer                            | Discount                                  |
      | 001 | Mermaid Teardrop Necklace | false       | Enjoy Your Saving! Buy more to save more | 2 items get 10% OFF,3 items get $5.00 OFF |
    Then close browser
    Examples:
      | KEY |
      | 001 |

  Scenario Outline: Create quantity discount offer on store front #SB_SF_CSQD_30 #SB_SF_CSQD_21 #SB_SF_CSQD_20 #SB_SF_CSQD_16 #SB_SF_CSQD_7 #SB_SF_CSQD_10 #SB_SF_CSQD_11 #SB_SF_CSQD_12
    Given open page "/admin/apps/boost-upsell/cross-sell/quantity-offer/list"
    And wait page "Quantity discounts" show
    Then click button Create offer
    And input data to create offer quantity discount "<KEY>"
      | KEY | Offer's name | Offer's message                                 | Target type          | Target products                 | Discount quantity                                              |
      | 001 | Offer 1      | Enjoy Your Saving! Buy more to save more        | All products         |                                 | 2>5>percentage_each,3>10>percentage_each,4>15>percentage_each  |
      | 002 | Offer 2      | Buy More Save More                              | Specific products    | Mug                             | 2>10>percentage_each,3>15>percentage_each,4>20>percentage_each |
      | 003 | Offer 3      | Enjoy Your Saving! Buy more to save more        | Specific collections | dress                           | 2>5>amount_each,4>6>amount_each,6>7>amount_each                |
      | 004 | Offer 4      | Best deals today! Get more to get a lower price | Specific products    | Pineapple Earrings              | 2>5>percentage_each,3>3>amount_each,4>10>amount_all            |
      | 005 | Offer 5      | Offer custom option                             | Specific products    | Bracelet,MoonStone Cat Necklace | 2>10>percentage_each,3>5>amount_each                           |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                          | Status |
      | 001 | Offer 1      | Any product                     | Active |
      | 002 | Offer 2      | Mug                             | Active |
      | 003 | Offer 3      | dress                           | Active |
      | 004 | Offer 4      | Pineapple Earrings              | Active |
      | 005 | Offer 5      | Bracelet,MoonStone Cat Necklace | Active |
    And open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                   | isShowOffer | Message Offer                                   | Discount                                                          |
      | 001 | Mermaid Teardrop Necklace | true        | Enjoy Your Saving! Buy more to save more        | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |
      | 001 | Fruit Fork                | true        | Enjoy Your Saving! Buy more to save more        | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |
      | 002 | Mug                       | true        | Buy More Save More                              | 2 items get 10% OFF,3 items get 15% OFF,4 items get 20% OFF       |
      | 003 | Simple Cold Dresses       | true        | Enjoy Your Saving! Buy more to save more        | 2 items get $5.00 OFF,4 items get $6.00 OFF,6 items get $7.00 OFF |
      | 004 | Pineapple Earrings        | true        | Best deals today! Get more to get a lower price | 2 items get 5% OFF,3 items get $3.00 OFF,4 items get $10.00       |
      | 005 | Bracelet                  | true        | Offer custom option                             | 2 items get 10% OFF,3 items get $5.00 OFF                         |
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |
      | 004 |
      | 005 |

  Scenario Outline: Verify offer when add to cart on widget #SB_SF_CSQD_18 #SB_SF_CSQD_17
    Given Description: "<Description>"
    When open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                   | isShowOffer | Message Offer                                   | Discount                                                          |
      | 001 | Mermaid Teardrop Necklace | true        | Enjoy Your Saving! Buy more to save more        | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |
      | 001 | Fruit Fork                | true        | Enjoy Your Saving! Buy more to save more        | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |
      | 002 | Mug                       | true        | Buy More Save More                              | 2 items get 10% OFF,3 items get 15% OFF,4 items get 20% OFF       |
      | 003 | Simple Cold Dresses       | true        | Enjoy Your Saving! Buy more to save more        | 2 items get $5.00 OFF,4 items get $6.00 OFF,6 items get $7.00 OFF |
      | 003 | MC White Lace Dress       | true        | Enjoy Your Saving! Buy more to save more        | 2 items get $5.00 OFF,4 items get $6.00 OFF,6 items get $7.00 OFF |
      | 004 | Pineapple Earrings        | true        | Best deals today! Get more to get a lower price | 2 items get 5% OFF,3 items get $3.00 OFF,4 items get $10.00       |
      | 005 | MoonStone Cat Necklace    | true        | Offer custom option                             | 2 items get 10% OFF,3 items get $5.00 OFF                         |
      | 006 | Bracelet                  | true        | Offer custom option                             | 2 items get 10% OFF,3 items get $5.00 OFF                         |
      | 008 | Portable Power Floss      | false       |                                                 |                                                                   |
      | 008 | Candles                   | true        | Enjoy Your Saving! Buy more to save more        | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |

    Then add to cart quantity discount "<KEY>"
      | KEY | isAddToCart | Quantity | Custom option    |
      | 001 | true        | 2        |                  |
      | 002 | true        | 3        |                  |
      | 003 | false       | 3        |                  |
      | 004 | true        | 4        |                  |
      | 005 | true        | 3        | Text:custom text |
      | 006 | false       | 4        | Text:custom text |
      | 008 | false       | 1        |                  |

    Then verify detail on cart page "<KEY>"
      | KEY | Products            | Message discount                                                                                  | Discount code        | Subtotal |
      | 001 | Fruit Fork          | Wonderful! We have chosen the most valuable discount for you. Enjoy $0.70 off on the total bill.  | Offer Discount>0.7   | 13.28    |
      | 002 | Mug                 | Wonderful! We have chosen the most valuable discount for you. Enjoy $10.94 off on the total bill. | Offer Discount>10.94 | 62.02    |
      | 003 | Simple Cold Dresses | Wonderful! We have chosen the most valuable discount for you. Enjoy $15.00 off on the total bill. | Offer Discount>15    | 134.7    |
      | 004 | MC White Lace Dress | Wonderful! We have chosen the most valuable discount for you. Enjoy $11.60 off on the total bill. | Offer Discount>11.60 | 40       |
      | 005 | Pineapple Earrings  | Wonderful! We have chosen the most valuable discount for you. Enjoy $15.00 off on the total bill. | Offer Discount>15.00 | 52.50    |
      | 006 | Bracelet            | Wonderful! We have chosen the most valuable discount for you. Enjoy $20.00 off on the total bill. | Offer Discount>20.00 | 19.6     |
      | 008 | Candles             |                                                                                                   |                      | 23.99    |
    And click to button checkout
    Then verify order summary "<KEY>"
      | KEY | Products                                       | Subtotal | Total price | DisCount Apply       |
      | 001 | Fruit Fork>13.98                               | 13.98    | 13.28       | Offer Discount>0.7   |
      | 002 | Mug>Black>72.96                                | 72.96    | 62.02       | Offer Discount>10.94 |
      | 003 | MC White Lace Dress>S>149.70                   | 149.70   | 134.70      | Offer Discount>15    |
      | 004 | Pineapple Earrings>Gold>51.60                  | 51.60    | 40          | Offer Discount>11.60 |
      | 005 | MoonStone Cat Necklace>Text: custom text>67.50 | 67.50    | 52.50       | Offer Discount>15.00 |
      | 006 | Bracelet>Text: custom text>39.60               | 39.6     | 19.60       | Offer Discount>20.00 |
      | 008 | Candles>23.99                                  | 23.99    | 23.99       |                      |

    Then close browser
    Examples:
      | KEY | Description                                             |
      | 001 | any product show offer                                  |
      | 002 | Specific products                                       |
      | 003 | Specific collections                                    |
      | 004 | Specific products                                       |
      | 005 | Product custom option with button Add to cart on widget |
      | 006 | Product custom option with add to cart product          |
      | 008 | Check with product stock Add product quantity =1        |

  Scenario Outline: Verify offer work when deactive and active offer #SB_SF_CSQD_8
    Given Description: "<Description>"
    Given open page "/admin/apps/boost-upsell/cross-sell/quantity-offer/list"
    And wait page "Quantity discounts" show
    Then active or inactive offer with "<KEY>"
      | KEY | Action           | Offers  | Notices                               | Status   |
      | 001 | Deactivate offer | all     | Offers were deactivated susscessfully | Inactive |
      | 002 | Activate offer   | Offer 3 | Offer was activated susscessfully     | Active   |
    Given open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product                   | isShowOffer | Message Offer                            | Discount                                                          |
      | 001 | Mermaid Teardrop Necklace | fasle       | Enjoy Your Saving! Buy more to save more | 2 items get 5% OFF,3 items get 10% OFF,4 items get 15% OFF        |
      | 002 | MC White Lace Dress       | true        | Enjoy Your Saving! Buy more to save more | 2 items get $5.00 OFF,4 items get $6.00 OFF,6 items get $7.00 OFF |
    Examples:
      | KEY | Description    |
      | 001 | Deactive offer |
      | 002 | Active offer   |

  Scenario Outline: Verify button Add on offer #SB_SF_CSQD_9
    Given Description: "<Description>"
    Given open page "/admin/apps/boost-upsell/cross-sell/quantity-offer/list"
    And wait page "Quantity discounts" show
    And setting button Add to cart shown is "<Status>"
    Then open shop on storefront
    And search and select the product "MC Velvet Party Dress"
    Then verify button Add to cart showwn is "<Status>"
    Examples:
      | Status |
      | false  |
      | true   |





