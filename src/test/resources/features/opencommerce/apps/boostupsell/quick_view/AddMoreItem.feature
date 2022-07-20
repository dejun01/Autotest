Feature: Add more item

#  Env: us_quickview

#  product hidden from listing: MC Tropical Trip Leaves Printing Bikini Set
#  product custom option: Bracelet with required, MoonStone Cat Necklace no required
#  Offer bundle(Coasters,ThermoCup)
#  offer pre-purchase(Target: All product, upsell: Mermaid Teardrop Necklace,Pineapple Earrings )



  Scenario Outline: Add more item with product normal #SB_SF_QVA_9 #SB_SF_QVA_4
    Given Description: "<Description>"
    Given open shop on storefront
    Then add products "<Product>" to cart
    And open cart page
    Then verify button Add more item on "<Product>"
    Then verify Add more item work with "<Product>" as "<KEY>"
      | KEY | Price | Variant | Custom option | isRequired | isAddToCart |
      | 001 | 6.99  |         |               | false      | true        |
      | 002 | 62.90 | Size>M  |               | false      | true        |
    And close browser
    Examples:
      | KEY | Description              | Product                 |
      | 001 | Add product no variant   | Spoons                  |
      | 002 | Add product have variant | MC Vestido Summer Dress |

  Scenario Outline: Add more item with custom option #SB_SF_QVA_5
    Given Description: "<Description>"
    Given open shop on storefront
    Then add to cart product "<Product>" with custom option "<Custom Option>"
    And open cart page
    Then verify button Add more item on "<Product>"
    Then verify Add more item work with "<Product>" as "<KEY>"
      | KEY | Price | Variant           | Custom option                     | isRequired | isAddToCart |
      | 001 | 9.90  | Colour>Deep Black | Text: test custom option required | true       | true        |
      | 002 | 22.50 |                   | Text:                             | false      | true        |
    And close browser

    Examples:
      | KEY | Description                                 | Product                | Custom Option           |
      | 001 | Add product with custom option              | Bracelet               | Text:Test custom option |
      | 002 | Add product with custom option not required | MoonStone Cat Necklace | Text:text 1             |

  Scenario Outline: verify Add more item show when product added from bundle, pre-purchase
    Given Description: "<Description>"
    Given open shop on storefront
    Then verify offer pre-purchase work on site "<KEY>"
      | KEY | Target product      | isShow | Message Offer                              | Number product |
      | 001 | MC White Lace Dress | true   | Frequently bought with MC White Lace Dress | 2              |
    And add to cart product on pre-purchase popup "<KEY>"
      | KEY | Products                  |
      | 001 | Mermaid Teardrop Necklace |
    Then open page "ThermoCup"
    Then add all to cart bundle "<KEY>"
      | KEY | Product   |
      | 001 | ThermoCup |
    And open cart page
    Then verify button Add more item on "<Product>"
    And close browser
    Examples:
      | KEY | Description                                                     | Product                             |
      | 001 | Add more item show when product added from bundle, pre-purchase | ThermoCup,Mermaid Teardrop Necklace |

