Feature: Cross sell

  Scenario Outline: Verify Bundle offer
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    And user navigate to "Bundles" screen
    And wait page "Bundles" show
    Then delete all offers on the table
    Then click button Create offer
    And input data to create offer "<KEY>"
      | KEY | Offer's name | Offer's message | Bundle products | isShowWithTarget | isDiscount | Discount value |
      | 001 | Offer 1      | Offer 1         | Hats,Mug        | false            | true       | 30             |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target   | Status |
      | 001 | Offer 1      | Hats,Mug | Active |
    And open shop on storefront
    Then verify Bundle show "<KEY>"
      | KEY | Target product | isShow | Bundles list             |
      | 001 | Hats           | true   | Offer 1>2>Hats,Mug>76.52 |
    And add all to cart bundle "<KEY>"
      | KEY | Bundles                   |
      | 001 | Hats>XL / Black,Mug>Black |
    And checkout by Stripe successfully
    Then verify order summary "<KEY>"
      | KEY | Products                              | Subtotal | Total price | DisCount Apply       |
      | 001 | Hats>XL / Black>85.00,Mug>Black>24.32 | 109.32   | 83.51       | OFFER DISCOUNT>32.80 |
    Then close browser
    Examples:
      | KEY |
      | 001 |

  Scenario Outline: Verify quantity discount offer
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Cross-sell" screen
    And user navigate to "Quantity discounts" screen
    And wait page "Quantity discounts" show
    Then delete all offers on the table
    Then click button Create offer
    And input data to create offer quantity discount "<KEY>"
      | KEY | Offer's name | Offer's message    | Target type       | Target products | Discount quantity                                              |
      | 002 | Offer 2      | Buy More Save More | Specific products | Mug             | 2>10>percentage_each,3>15>percentage_each,4>20>percentage_each |
    Then click button "Submit offer" on app
    Then verify Upsell's offer created on the dashboard "<KEY>"
      | KEY | Offer's name | Target | Status |
      | 002 | Offer 2      | Mug    | Active |
    And open shop on storefront
    And verify offer quantity discount show as "<KEY>"
      | KEY | Product | isShowOffer | Message Offer      | Discount                                                    |
      | 002 | Mug     | true        | Buy More Save More | 2 items get 10% OFF,3 items get 15% OFF,4 items get 20% OFF |
    Then add to cart quantity discount "<KEY>"
      | KEY | isAddToCart | Quantity |
      | 002 | true        | 3        |
    And click to button checkout
    And checkout by Stripe successfully
    Then verify order summary "<KEY>"
      | KEY | Products        | Subtotal | Total price | DisCount Apply       |
      | 002 | Mug>Black>72.96 | 72.96    | 69.01       | OFFER DISCOUNT>10.94 |
    Examples:
      | KEY |
      | 002 |
