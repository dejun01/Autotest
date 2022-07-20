Feature: Handpicked Products

#  Env: us_pre_purchase



  Scenario Outline: Delete handpicked
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Product widgets" screen
    And open page Customize of widget "Handpicked products"
    Then delete all offers on the table
    And click to button "Design"
    And reset widget's settings to default as "<KEY>"
      | KEY |
      | 1   |
    Then open shop on storefront
    And verify widget's settings as "<KEY>"
      | KEY | Place widget | Widget name         | Is show |
      | 1   | Fruit Fork   | Handpicked products | false   |
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: Verify handpicked widget #SB_BUS_PW_3
    Given open page "/admin/apps/boost-upsell/cross-sell/product-widgets"
    And wait page "Product widgets" show
    And open page Customize of widget "Handpicked products"
    When create a new handpicked product as "<KEY>"
      | KEY | Offer name   | Target products                                                      | Recommend products                                                                     | Status |
      | 0   | Offer name 0 | Specific products/collections>Select product>Jeans,Slicer,Fruit Fork | Pineapple Earrings,Bags,Bracelet,Mug,Real Clover Necklace,Hats,Jeans,Slicer,Fruit Fork | on     |
      | 2   | Offer name 2 | Specific products/collections>Select collection>dress                | Bags,Bracelet,Mug,Spoons,Hats,Candles,Brown shoes,ThermoCup                            | on     |
      | 3   | Offer name 3 | All products                                                         | Coasters,Black shoes,Slicer,Bracelet                                                   | on     |
    And click to button "Design"
    And customize widget as "<KEY>"
      | KEY | Header                      | Font header            | Font product name            | Font product price         | Max product per slide | Is on add to cart button | Call to action text        |
      | 1   | Handpicked Product Widget   | 26px>bold,italic,right | 13px>bold,right              | 17px>bold,italic,underline | 4                     | false                    | 18px>bold,underline,italic |
      | 2   | Handpicked Product Widget 2 | 28px>bold,left         | 13px>italic,center,underline | 17px>italic                | 6                     | true                     | 18px>bold,underline        |
      | 3   | Handpicked Product Widget 3 | 28px>bold,center       | 13px>italic,left             | 17px>italic,bold           | 2                     | true                     | 19px>italic                |
    Then open shop on storefront
    And verify widget's settings as "<KEY>"
      | KEY | Place widget                                        | Widget name                 | Is show | Font header            | Font product name            | Font product price         | Number of products | Max product per slide | Is on add to cart button | Call to action text        | Recommended products                                                                                        |
      | 0   | Fruit Fork                                          | Handpicked products         | true    | 25px>center            | 14px>left                    | 16px>left                  | 9                  | 6                     | true                     | 16px                       | Pineapple Earrings,Bags,Bracelet,Mug,Real Clover Necklace,Hats,Jeans,Slicer,Fruit Fork                      |
      | 1   | /products/stainless-steel-fruit-fork/?sbase_debug=1 | Handpicked Product Widget   | true    | 26px>bold,italic,right | 13px>bold,right              | 17px>bold,italic,underline | 9                  | 4                     | false                    | 18px>bold,underline,italic | Pineapple Earrings,Bags,Bracelet,Mug,Real Clover Necklace,Hats,Jeans,Slicer,Fruit Fork                      |
      | 1   | Mug                                                 | Handpicked Product Widget   | false   |                        |                              |                            |                    |                       |                          |                            |                                                                                                             |
      | 1   |                                                     | Handpicked Product Widget   | false   |                        |                              |                            |                    |                       |                          |                            |                                                                                                             |

      | 2   | Simple Cold Dresses                                 | Handpicked Product Widget 2 | true    | 28px>bold,left         | 13px>italic,center,underline | 17px>italic                | 8                  | 6                     | true                     | 18px>bold,underline        | Bags,Bracelet,Mug,Spoons,Hats,Candles,Brown shoes,ThermoCup                                                 |
      | 2   | Coasters                                            | Handpicked Product Widget 2 | false   |                        |                              |                            |                    |                       |                          |                            |                                                                                                             |

      | 3   | Mug                                                 | Handpicked Product Widget 3 | true    | 28px>bold,center       | 13px>italic,left             | 17px>italic,bold           | 4                  | 2                     | true                     | 19px>italic                | Coasters,Black shoes,Slicer,Bracelet                                                                        |
      | 3   | Fruit Fork                                          | Handpicked Product Widget 3 | true    | 28px>bold,center       | 13px>italic,left             | 17px>italic,bold           | 11                 | 2                     | true                     | 19px>italic                | Coasters,Black shoes,Slicer,Bracelet,Pineapple Earrings,Bags,Mug,Real Clover Necklace,Hats,Jeans,Fruit Fork |
      | 3   | Simple Cold Dresses                                 | Handpicked Product Widget 3 | true    | 28px>bold,center       | 13px>italic,left             | 17px>italic,bold           | 11                 | 2                     | true                     | 19px>italic                | Coasters,Black shoes,Slicer,Bags,Bracelet,Mug,Spoons,Hats,Candles,Brown shoes,ThermoCup                     |
    And close browser
    Examples:
      | KEY |
      | 0   |
      | 1   |
      | 2   |
      | 3   |


