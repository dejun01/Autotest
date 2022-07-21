Feature: Boost Upsell StoreFront
#env: us_incart_offer


  Scenario: Delete all accessory offer
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    And user navigate to "Accessories" screen
    And delete all offers on the table

  Scenario Outline: Check Accessory on Storefront #SB_BUS_AC_3 #SB_BUS_AC_5 #SB_BUS_AC_7 #SB_BUS_AC_8 #SB_BUS_AC_9 #SB_BUS_AC_10 #SB_BUS_AC_11
    Given open page "/admin/apps/boost-upsell/cross-sell/accessories/list"
    And wait page "Accessories" show
    Then click button create accessories offer "<KEY>"
      | KEY | Is edit offer |
      | 1   | false         |
      | 2   | false         |
      | 3   | true          |
      | 4   | false         |
      | 5   | false         |
    Then input data to create offer "<KEY>"
      | KEY | Old offer  | Offer's name | Offer's message    | Target type          | Target products                                            | Accessory type       | Accessory products                                                       |
      | 1   |            | Accessory1   | Access Accessories | Specific products    | product 1 co bundle va quantity                            | Specific products    | product 4 co custom option nhieu variant va mot so out of stock          |
      | 2   |            | Accessory2   | Best deal for you  | Specific products    | product out of stock 1                                     | Specific products    | product khong variant va khong custom option                             |
      | 3   | Accessory2 | Accessory3   | Accessory3 message | Specific products    | product 2                                                  | Specific products    | product out of stock 2                                                   |
      | 4   |            | Accessory4   | Accessory4 message | Specific products    | product 1 co bundle va quantity                            | Specific collections | collection co product khong variant va khong custom option               |
      | 5   |            | Accessory5   | Accessory5 message | Specific collections | collection co product khong variant va khong custom option | Specific products    | product co custom option va khong validate,product 6,product 8,product 9 |
    Then click button Submit offer on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                                                     | Status |
      | 1   | Accessory1   | product 1 co bundle va quantity                            | Active |
      | 2   | Accessory2   | product out of stock 1                                     | Active |
      | 3   | Accessory3   | product 2                                                  | Active |
      | 4   | Accessory4   | product 1 co bundle va quantity                            | Active |
      | 5   | Accessory5   | collection co product khong variant va khong custom option | Active |
    And open shop on storefront
    And verify accessory work on sf "<KEY>"
      | KEY | Offer's message    | Recommend Products                                                          | isShow | Target product                               |
      | 1   | Access Accessories | product 4 co custom option nhieu variant va mot so out of stock             | true   | product 1 co bundle va quantity              |
      | 2   | Best deal for you  | product khong variant va khong custom option                                | true   | product out of stock 1                       |
      | 3   | Accessory3 message |                                                                             | false  | product 2                                    |
      | 4   | Accessory4 message | product 7, product khong variant va khong custom option                     | true   | product 1 co bundle va quantity              |
      | 5   | Accessory5 message | product co custom option va khong validate, product 6, product 8, product 9 | true   | product khong variant va khong custom option |
      | 5   | Accessory5 message | product co custom option va khong validate, product 6, product 8, product 9 | true   | product 7                                    |
    And add to cart accessory "<KEY>"
      | KEY | Accessories products                                            | Variant | Custom option | Is Valid |
      | 1   | product 4 co custom option nhieu variant va mot so out of stock | L,Black | option        | true     |
      | 2   | product khong variant va khong custom option                    |         |               | true     |
      | 4   | product khong variant va khong custom option                    |         |               | true     |
      | 5   | product co custom option va khong validate                      |         | option        | false    |
    And open cart page
    And verify on page cart "<KEY>"
      | KEY | Product target                                                  | Variant   | Price  | Custom option    |
      | 1   | product 4 co custom option nhieu variant va mot so out of stock | L / Black | $25.00 | option 1: option |
      | 2   | product khong variant va khong custom option                    |           | $10.00 |                  |
      | 4   | product khong variant va khong custom option                    |           | $10.00 |                  |
      | 5   | product co custom option va khong validate                      |           | $30.00 |                  |
    And quit browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |


  Scenario Outline: Check Accessory when inactive offer with "<KEY>" #SB_BUS_AC_13
    Given open page "/admin/apps/boost-upsell/cross-sell/accessories/list"
    And wait page "Accessories" show
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                                                     | Status |
      | 1   | Accessory5   | collection co product khong variant va khong custom option | Active |
    Then active or inactive offer with "<KEY>"
      | KEY | Action           | Offers | Status   |
      | 1   | Deactivate offer | all    | Inactive |
    And open shop on storefront
    And verify accessory work on sf "<KEY>"
      | KEY | Offer's message    | Recommend Products | isShow | Target product                               |
      | 1   | Accessory5 message |                    | false  | product khong variant va khong custom option |
      | 1   | Accessory5 message |                    | false  | product 7                                    |
    And close browser
    Examples:
      | KEY |
      | 1   |


