Feature: Quick view popup

# Env: us_quickview

# Product sold out: Portable Power Floss
# Product sold out 1 variant  : Ring Cat - Rose Gold
#  product hidden from listing: MC Tropical Trip Leaves Printing Bikini Set
#  product custom option: Bracelet with required, MoonStone Cat Necklace no required
# Offer bundle(Coasters,ThermoCup)
# offer pre-purchase(Target: All product, upsell: Mermaid Teardrop Necklace,Pineapple Earrings )
# offer handpicked(Target: All product, product: Bracelet, MoonStone Cat Necklace,MC Tropical Trip Leaves Printing Bikini Set,Outdoor Waterproof Socks,Slice Quick & Right  )



  Scenario Outline: Quick view work with bundle #SB_SF_QVA_7 #SB_SF_QVA_8
    Given open shop on storefront
    Then verify Bundle show "<KEY>"
      | KEY | Target product | isShow | Bundles list                                          |
      | 001 | ThermoCup      | true   | Frequently bought together>2>ThermoCup,Coasters>78.89 |
    And click quick view on product "Coasters"
    Then verify quick view show "<KEY>"
      | KEY | isShow | Title    | Price | Variant | isShowThumbNail | isShowBundle | isShowRecommendForYou | isAddToCart |
      | 001 | true   | Coasters | 32.90 |         | true            | true         | true                  | true        |
    And verify smart offer show "<KEY>"
      | KEY | isShow | Number product |
      | 001 | true   | 2              |
    Examples:
      | KEY |
      | 001 |


  Scenario Outline: Quick view work with product widget #SB_SF_QVA_6
    Given Description: "<Description>"
    Given open shop on storefront
    When open page "<Page>"
    Then add to cart product on widget as "<KEY>"
      | KEY | Widget name         | Number product | Product name           |
      | 001 | Handpicked products | 1              |                        |
      | 002 | Handpicked products | 2              |                        |
      | 003 | Handpicked products |                | Bracelet               |
      | 004 | Handpicked products |                | MoonStone Cat Necklace |
    Then verify quick view show "<KEY>"
      | KEY | isShow | isShowThumbNail | isShowBundle | isShowRecommendForYou | isAddToCart | Text validate            |
      | 001 | true   | true            | false        | true                  | true        |                          |
      | 002 | false  |                 |              |                       |             |                          |
      | 003 | true   | true            | false        | true                  | true        | Please finish this field |
      | 004 | true   | true            | false        | true                  | true        |                          |
    And verify smart offer show "<KEY>"
      | KEY | isShow |
      | 001 | true   |
      | 002 | true   |
      | 004 | true   |

    Examples:
      | KEY | Page                   | Description                                |
      | 001 | Mug                    |                                            |
      | 002 | Candles                |                                            |
      | 003 | Slice Quick & Right    | add to cart with custom option             |
      | 004 | Bracelet               | add to cart with custom option no required |


