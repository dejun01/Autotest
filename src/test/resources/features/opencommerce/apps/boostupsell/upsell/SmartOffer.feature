Feature: Smart Offer and Smart Bundle

#  Env: us_smart_offer


  Scenario Outline: Verify smart pre-purchase offer work when turn On and Off #SB_BUS_1 #SB_BUS_2 #SB_BUS_3 #SB_BUS_4 #SB_BUS_5
    Given Description: "<Description>"
    Then open page "/admin/apps/boost-upsell/settings"
    And wait page "Settings" show
    Then setting smart "<Smart Offer Type>" with "<Status>"
    And set up recommendation rules of smart "<Smart Offer Type>" as "<KEY>"
      | KEY | TargetRules                  | Products                                                   | is Discount | Discount |
      | 001 | is one of these products     | All products                                               | true        | 5        |
      | 002 | is one of these products     | Hats,Ring Cat,Candles                                      | false       |          |
      | 003 | is not one of these products | Hats,MC Wine Halter One-piece Swimsuit,MC White Lace Dress | true        | 15       |
    Then open shop on storefront
    Then verify smart offer work as "<KEY>"
      | KEY | Target product                                             | isShow | isAddToCart | Discount |
      | 001 | MC Wine Halter One-piece Swimsuit                          | true   | true        | 5        |
      | 002 | Socks                                                      | false  |             |          |
      | 002 | Hats,Ring Cat,Candles                                      | true   |             |          |
      | 003 | Hats,MC Wine Halter One-piece Swimsuit,MC White Lace Dress | false  |             |          |
      | 003 | Mug,Sea Turtle Necklace                                    | true   |             | 15       |
      | 004 | Mug,Sea Turtle Necklace                                    | false  |             |          |

    Examples:
      | Description                                             | Smart Offer Type   | Status | KEY |
      | Verify smart offer show when turn on with all product   | Smart Pre-purchase | on     | 001 |
      | Verify smart offer show when turn on with other product | Smart Pre-purchase | on     | 002 |
      | Verify smart offer show when turn on                    | Smart Pre-purchase | on     | 003 |
      | Verify smart offer show when turn off                   | Smart Pre-purchase | off    | 004 |


  Scenario Outline: Verify smart bundle work when turn On and Off #SB_BUS_6 #SB_BUS_7 #SB_BUS_8 #SB_BUS_9 #SB_BUS_10 #SB_BUS_11
    Given Description: "<Description>"
    Then open page "/admin/apps/boost-upsell/settings"
    And wait page "Settings" show
    Then setting smart "<Smart Offer Type>" with "<Status>"
    And set up recommendation rules of smart "<Smart Offer Type>" as "<KEY>"
      | KEY | is Discount | Discount |
      | 001 | true        | 10       |
      | 002 | false       | 10       |
    And open shop on storefront
    Then verify smart bundle work as "<KEY>"
      | KEY | TC                                               | Products                   | isShow | Message Offer              | is Discount | Discount | isAddToCart |
      | 001 | not show smart bundle when product out stock     | Portable Power Floss       | false  |                            |             |          |             |
      | 001 | not show smart bundle when product custom option | Bracelet                   | true   | Frequently bought together | true        | 10       |             |
      | 001 | show smart bundle                                | Hats                       | true   | Frequently bought together | true        | 10       |             |
      | 001 | show smart bundle                                | Summer Floral Print Blouse | true   | Frequently bought together | true        | 10       | true        |
      | 002 | show smart bundle not discount                   | Hats                       | true   | Frequently bought together | false       |          |             |
      | 002 | show smart bundle  not discount                  | Summer Floral Print Blouse | true   | Frequently bought together | false       |          | true        |
      | 003 | not show smart bundle when turn off              | Summer Floral Print Blouse | false  |                            |             |          |             |
      | 003 | not show smart bundle when turn off              | Hats                       | false  |                            |             |          |             |
    Then close browser
    Examples:
      | Description                                         | Smart Offer Type | Status | KEY |
      | Verify smart bundle show when turn on have discount | Smart Bundles    | on     | 001 |
      | Verify smart bundle show when turn on not discount  | Smart Bundles    | on     | 002 |
      | Verify smart bundle show when turn off              | Smart Bundles    | off    | 003 |
