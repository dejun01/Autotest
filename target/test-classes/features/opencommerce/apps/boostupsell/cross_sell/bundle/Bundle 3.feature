Feature: Bundle SDK

#  enviromment:
#  staging_us_pre_purchase
#  prod_us_pre_purchase

#  Setting: Add to cart -> redirect to checkout page



  Scenario Outline: US_ATC_1.40 : Delete All Ofer
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    And user navigate to "Bundles" screen
    Then delete all offers on the table
    Then open shop on storefront
    Then verify Bundle show "<KEY>"
      | KEY | Target product | isShow |
      | 001 | Hats           | false  |
    Then close browser
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: CS_CO_1.2 Create Custom Cross sell #SB_SF_CSB_30 #SB_SF_CSB_29 #SB_SF_CSB_27 #SB_SF_CSB_26 #SB_SF_CSB_24 #SB_SF_CSB_23 #SB_SF_CSB_21 #SB_SF_CSB_17 #SB_SF_CSB_15 #SB_SF_CSB_10 #SB_SF_CSB_9 #SB_SF_CSB_7 #SB_SF_CSB_8
    Given Description: "<Testcase>"
    Given open page "/admin/apps/boost-upsell/cross-sell/bundle-offer/list"
    And wait page "Bundles" show
    Then click button Create offer
    And input data to create offer "<KEY>"
      | KEY | Offer's name | Offer's message       | Bundle products                            | isShowWithTarget | isDiscount | Discount value |
      | 001 | Offer 1      | Offer 1               | Hats,Mug                                   | false            | true       | 30             |
      | 002 | Offer 2      | Offer 2               | Mug,Mug,Mug                                | false            | true       | 10             |
      | 003 | Offer 3      | Offer BoGo            | Candles,Candles,Candles,Candles            | false            | true       | 10             |
      | 004 | Offer 4      | Offer 4               | Pineapple Earrings,Jeans,Spoons            | true             | false      |                |
      | 005 | Offer 5      | Offer custom option   | Bracelet,Noty Foldable Camping Chairs      | false            | false      |                |
      | 006 | Offer 6      | Offer custom option 2 | Custom option,MC White Lace Dress,Bracelet | false            | true       | 50             |
      | 007 | Offer 7      | Offer BoGo 2          | Bobo,Bobo,Bobo                             | false            | true       | 50             |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                                     | Status |
      | 001 | Offer 1      | Hats,Mug                                   | Active |
      | 002 | Offer 2      | Mug                                        | Active |
      | 003 | Offer 3      | Candles                                    | Active |
      | 004 | Offer 4      | Pineapple Earrings,Jeans,Spoons            | Active |
      | 005 | Offer 5      | Bracelet,Noty Foldable Camping Chairs      | Active |
      | 006 | Offer 6      | Custom option,MC White Lace Dress,Bracelet | Active |
      | 007 | Offer 7      | Bobo                                       | Active |
    And open shop on storefront
    Then verify Bundle show "<KEY>"
      | KEY | Target product               | isShow | Bundles list                                                             |
      | 001 | Hats                         | true   | Offer 1>2>Hats,Mug>76.52                                                 |
      | 002 | Mug                          | true   | Offer 2>3>Mug>65.66;Offer 1>2>Mug,Hats>76.52                             |
      | 003 | Candles                      | true   | Offer BoGo>4>Candles>86.36                                               |
      | 004 | Jeans                        | false  | Offer 4>3>Pineapple Earrings,Jeans,Spoons>174.89                         |
      | 004 | Spoons                       | false  | Offer 4>3>Pineapple Earrings,Jeans,Spoons>174.89                         |
      | 004 | Pineapple Earrings           | true   | Offer 4>3>Pineapple Earrings,Jeans,Spoons>174.89                         |
      | 005 | Noty Foldable Camping Chairs | true   | Offer custom option>2>Bracelet,Noty Foldable Camping Chairs>35.9         |
      | 006 | Custom option                | true   | Offer custom option 2>3>Custom option,MC White Lace Dress,Bracelet>61.35 |
      | 007 | Bobo                         | true   | Offer BoGo 2>3>Bobo>15.00                                                |

    And add all to cart bundle "<KEY>"
      | KEY | Bundles                   |
      | 001 | Hats>XL / Black,Mug>Black |
      | 003 | Candles>Pink Rose         |
      | 004 | Pineapple Earrings>Gold   |
      | 006 |                           |
      | 007 |                           |
    And input Customize option "<KEY>"
      | KEY | Product                 | Price | Text field | Image                  | Radio buttons | Picture choice | Message                                        |
      | 006 | Click all image product |       |            |                        |               |                |                                                |
      | 006 | Custom option           | 62.90 | Test       | /front/CustomOption.png| Option 1      | img2           | Please finish this field,Please upload a file. |
      | 006 | MC White Lace Dress     | 49.90 |            |                        |               |                |                                                |
      | 006 | Bracelet                | 9.90  | Test 1     |                        |               |                | Please finish this field                       |
      | 007 | Click all image product |       |            |                        |               |                |                                                |
      | 007 | Bobo                    | 10.00 | Test1      |                        |               |                | Please finish this field                       |
      | 007 | Bobo                    | 10.00 | Test2      |                        |               |                | Please finish this field                       |
      | 007 | Bobo                    | 10.00 | Test3      |                        |               |                | Please finish this field                       |
    Then go to checkout page "<KEY>"
      | KEY |
      | 001 |
      | 003 |
      | 004 |
      | 006 |
      | 007 |
    Then verify information order "<KEY>"
      | KEY | Product             | Variant         | Price  | Text field | Image            | Radio buttons | Picture choice |
      | 001 | Hats                | XL / Black      | 85.00  |            |                  |               |                |
      | 001 | Mug                 | Black           | 24.32  |            |                  |               |                |
      | 003 | Candles             | Pink Rose       | 23.99  |            |                  |               |                |
      | 003 | Candles             | Purple Lavendar | 71.97  |            |                  |               |                |
      | 004 | Pineapple Earrings  | Gold            | 12.90  |            |                  |               |                |
      | 004 | Jeans               | 45 / Blue       | 155.00 |            |                  |               |                |
      | 004 | Spoons              |                 | 6.99   |            |                  |               |                |
      | 006 | Custom option       |                 | 62.90  | Test       | CustomOption.png | Option 1      | img2           |
      | 006 | MC White Lace Dress |                 | 49.90  |            |                  |               |                |
      | 006 | Bracelet            |                 | 9.90   | Test 1     |                  |               |                |
      | 007 | Bobo                |                 | 10.00  | Test1      |                  |               |                |
      | 007 | Bobo                |                 | 10.00  | Test2      |                  |               |                |
      | 007 | Bobo                |                 | 10.00  | Test3      |                  |               |                |
    Then verify price order "<KEY>"
      | KEY | Subtotal | Total price | DisCount Apply       |
      | 001 | 109.32   | 76.52       | Offer Discount>32.80 |
      | 003 | 95.96    | 86.36       | Offer Discount>9.60  |
      | 004 | 174.89   | 174.89      |                      |
      | 006 | 122.70   | 61.35       | Offer Discount>61.35 |
      | 007 | 30.00    | 15.00       | Offer Discount>15.00 |

    Then close browser

    Examples:
      | KEY | Testcase                                           |
      | 001 | Create Custom Cross sell discount discount         |
      | 002 | Create Custom Cross sell discount percentage       |
      | 003 | Create offer BoGo with discount                    |
      | 004 | Create offer not discount                          |
      | 005 | Create offer with custom option                    |
      | 006 | Create offer with custom option                    |
      | 007 | Create offer A with custom option + bundle (A,A,A) |



