Feature: Pre purchase

  Scenario Outline: Verify offer Pre-purchase
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Upsell" screen
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Then click button Create offer
    And input data to create offer "<KEY>"
      | KEY | Offer type   | Offer's name | Offer's message | Offer's title | Target type       | Target products | Recommend type    | Recommend products           | isDiscount | Discount value |
      | 001 | Pre-purchase | Offer 1      | Offer 1         |               | Specific products | Mug             | Specific products | Mug,Hats,Bracelet,Fruit Fork | true       | 30             |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target | Offer type   | Status |
      | 001 | Offer 1      | Mug    | pre-purchase | Active |
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product | isShow | Message Offer | Number product | Recommend product                                               |
      | 001 | Mug            | true   | Offer 1       | 4              | Hats>59.5,Bracelet>6.93,Fruit Fork>4.89,Pineapple Earrings>9.03 |
    Then add to cart product on pre-purchase popup "<KEY>"
      | KEY | Products        | isClickCheckoutButton |
      | 001 | Hats,Fruit Fork | true                  |
    Then open cart page
    Then click to button checkout
    And checkout by Stripe successfully
    Then verify order summary "<KEY>"
      | KEY | Products                                             | Subtotal | Total price | DisCount Apply       |
      | 001 | Mug>Black>24.32,Fruit Fork>6.99,Hats>L / Black>85.00 | 116.31   | 95.70       | OFFER DISCOUNT>27.60 |
    And close browser
    Examples:
      | KEY | Description        |
      | 001 | Offer pre-purchase |


  Scenario Outline: create offer Postpurchase
    Given clear all data
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Upsell" screen
    And wait page "Upsell offers" show
    Then delete all offers on the table
    Given Description: "<Description>"
    Then click button Create offer
    And input data to create offer "<KEY>"
      | KEY | Offer type    | Offer's name | Offer's message | Offer's title | Target type       | Target products                   | Recommend type       | Recommend products | isDiscount | Discount value |
      | 001 | Post-purchase | Offer 01     | Message 01      | Title 01      | Specific products | Noty Foldable Camping Chairs,Hats | Specific collections | dress              | true       | 20             |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target                            | Offer type    | Status |
      | 001 | Offer 01     | Noty Foldable Camping Chairs,Hats | post-purchase | Active |
    Given open shop on storefront
    When add products "<Product>" to cart then checkout
    And checkout by Stripe successfully
    Then verify content of post-purchase offer is shown on storefront "<KEY>"
      | KEY | Offer's title | Offer's message | Number product | Recommend products | Recommend type       | Recommend products in collection | isShow | Custom option | Add To Order Product | isDiscount | Discount value | isRefresh |
      | 001 | Title 01      | Message 01      | 11             |                    | Specific collections | dress                            | true   |               | Simple Cold Dresses  | true       | 20             |           |
    And order product in post purchase offer as "<KEY>"
      | KEY | Product name        | Custom option | isShow | Offer's title | Offer's message |
      | 001 | Simple Cold Dresses |               | true   |               |                 |

    Then verify order summary "<KEY>"
      | KEY | Products                                                     | Subtotal |
      | 001 | Noty Foldable Camping Chairs>26.00,Simple Cold Dresses>35.92 | 61.92    |

    Then verify status order status is "paid"
    And close browser
    Examples:
      | KEY | Description                            | Product                      |
      | 001 | offer when percent target, upsell prod | Noty Foldable Camping Chairs |

