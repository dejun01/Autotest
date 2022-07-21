Feature: Analytics app Upsell

#  envi:
#  staging_us_analytics
#  prod_us_analytics
#  prod_us_analytics_svtest_sfnext


  Scenario Outline: Verify analytic ShopBase with pre purchase offer #SB_SF_ANAA_1
    Given Description: "<Description>"
    Given get data analytic by API as "<KEY>"
      | KEY | Chart type   | Time        |
      | 1   | total-sales  | TODAY       |
      | 1   | total-orders | TODAY       |
      | 1   | total-sales  | YESTERDAY   |
      | 1   | total-orders | YESTERDAY   |
      | 1   | total-sales  | LAST 7 DAYS |
      | 1   | total-orders | LAST 7 DAYS |
      | 2   | total-sales  | TODAY       |
      | 2   | total-orders | TODAY       |
      | 2   | total-sales  | YESTERDAY   |
      | 2   | total-orders | YESTERDAY   |
      | 2   | total-sales  | LAST 7 DAYS |
      | 2   | total-orders | LAST 7 DAYS |
    Then open shop on storefront
    Then wait for app "Upsell" load
    And add products "<Products>" to cart
    Then add to cart product on pre-purchase popup "<KEY>"
      | KEY | Products                          |
      | 1   | Hats,Noty Foldable Camping Chairs |
      | 2   | Noty Foldable Camping Chairs      |
    Then open cart page
    And click button "Checkout"
    And checkout by Stripe successfully
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time        | Total  | Usell | Shopbase |
      | 1   | total-sales  | TODAY       | 139.99 | 111   | 28.99    |
      | 1   | total-orders | TODAY       | 1      | 1     | 1        |
      | 1   | total-sales  | YESTERDAY   | 0      | 0     | 0        |
      | 1   | total-orders | YESTERDAY   | 0      | 0     | 0        |
      | 1   | total-sales  | LAST 7 DAYS | 139.99 | 111   | 28.99    |
      | 1   | total-orders | LAST 7 DAYS | 1      | 1     | 1        |
      | 2   | total-sales  | TODAY       | 73     | 13    | 60       |
      | 2   | total-orders | TODAY       | 1      | 1     | 1        |
      | 2   | total-sales  | YESTERDAY   | 0      | 0     | 0        |
      | 2   | total-orders | YESTERDAY   | 0      | 0     | 0        |
      | 2   | total-sales  | LAST 7 DAYS | 73     | 13    | 60       |
      | 2   | total-orders | LAST 7 DAYS | 1      | 1     | 1        |
    Examples:
      | KEY | Products | Description                 |
      | 1   | Candles  | Pre-purchase not discount   |
      | 2   | Socks    | Pre-purchase have discounts |

  Scenario Outline: Verify analytic ShopBase with post purchase offer #SB_SF_ANAA_2
    Given Description: "<Description>"
    Given get data analytic by API as "<KEY>"
      | KEY | Chart type   | Time        |
      | 1   | total-sales  | TODAY       |
      | 1   | total-orders | TODAY       |
      | 1   | total-sales  | YESTERDAY   |
      | 1   | total-orders | YESTERDAY   |
      | 1   | total-sales  | LAST 7 DAYS |
      | 1   | total-orders | LAST 7 DAYS |
      | 2   | total-sales  | TODAY       |
      | 2   | total-orders | TODAY       |
      | 2   | total-sales  | YESTERDAY   |
      | 2   | total-orders | YESTERDAY   |
      | 2   | total-sales  | LAST 7 DAYS |
      | 2   | total-orders | LAST 7 DAYS |
    Then open shop on storefront
    And add products "<Products>" to cart
    Then open cart page
    And click button "Checkout"
    And checkout by Stripe successfully
    And order product in post purchase offer as "<KEY>"
      | KEY | Product name                       | Custom option | isShow | Offer's title | Offer's message |
      | 1   | Hats                               |               | true   |               |                 |
      | 2   | MC Vintage Marmalade Lantern Dress |               | true   |               |                 |
    Then verify order summary "<KEY>"
      | KEY | Products                                                         | Subtotal |
      | 1   | Real Clover Necklace>15.20,Hats>68                               | 83.2     |
      | 2   | Simple Cold Dresses>44.9,MC Vintage Marmalade Lantern Dress>44.9 | 89.8     |
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Usell | Shopbase |
      | 1   | total-sales  | TODAY | 88.2  | 68    | 20.2     |
      | 1   | total-orders | TODAY | 1     | 1     | 1        |
      | 2   | total-sales  | TODAY | 94.8  | 44.9  | 49.9     |
      | 2   | total-orders | TODAY | 1     | 1     | 1        |
    Examples:
      | KEY | Products             | Description                  |
      | 1   | Real Clover Necklace | Post-purchase has discount   |
      | 2   | Simple Cold Dresses  | Post-purchase hasnt discount |

  Scenario Outline: Verify analytic ShopBase with bundle offer #SB_SF_ANAA_3
    Given Description: "<Description>"
    Given get data analytic by API as "<KEY>"
      | KEY | Chart type   | Time        |
      | 1   | total-sales  | TODAY       |
      | 1   | total-orders | TODAY       |
      | 1   | total-sales  | YESTERDAY   |
      | 1   | total-orders | YESTERDAY   |
      | 1   | total-sales  | LAST 7 DAYS |
      | 1   | total-orders | LAST 7 DAYS |
      | 2   | total-sales  | TODAY       |
      | 2   | total-orders | TODAY       |
      | 2   | total-sales  | YESTERDAY   |
      | 2   | total-orders | YESTERDAY   |
      | 2   | total-sales  | LAST 7 DAYS |
      | 2   | total-orders | LAST 7 DAYS |
    Then open shop on storefront
    Then open page "<Products>"
    And add all to cart bundle "<KEY>"
      | KEY | Bundles |
      | 1   |         |
      | 2   |         |
    Then open cart page
    And click button "Checkout"
    And checkout by Stripe successfully
    Then verify order summary "<KEY>"
      | KEY | Products                                                    | Subtotal |
      | 1   | Pineapple Earrings>12.90,MC Velvet Party Dress>37.90        | 50.80    |
      | 2   | MC Waiting For The Sun One-piece Swimsuit>37.90,Earrings>16 | 53.90    |
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time        | Total | Usell | Shopbase |
      | 1   | total-sales  | TODAY       | 30.40 | 25.40 | 5        |
      | 1   | total-orders | TODAY       | 1     | 1     | 0        |
      | 1   | total-sales  | YESTERDAY   | 0     | 0     | 0        |
      | 1   | total-orders | YESTERDAY   | 0     | 0     | 0        |
      | 1   | total-sales  | LAST 7 DAYS | 30.40 | 25.40 | 5        |
      | 1   | total-orders | LAST 7 DAYS | 1     | 1     | 0        |
      | 2   | total-sales  | TODAY       | 58.90 | 53.90 | 5        |
      | 2   | total-orders | TODAY       | 1     | 1     | 0        |
      | 2   | total-sales  | YESTERDAY   | 0     | 0     | 0        |
      | 2   | total-orders | YESTERDAY   | 0     | 0     | 0        |
      | 2   | total-sales  | LAST 7 DAYS | 58.90 | 53.90 | 5        |
      | 2   | total-orders | LAST 7 DAYS | 1     | 1     | 0        |
    Examples:
      | KEY | Products                                  | Description           |
      | 1   | Pineapple Earrings                        | Bundle have discounts |
      | 2   | MC Waiting For The Sun One-piece Swimsuit | Bundle not discount   |


  Scenario Outline: verify analytics with in cart offer #SB_SF_ANAA_4
    Given get data analytic by API as "<KEY>"
      | KEY | Chart type   | Time        |
      | 1   | total-sales  | TODAY       |
      | 1   | total-orders | TODAY       |
      | 1   | total-sales  | YESTERDAY   |
      | 1   | total-orders | YESTERDAY   |
      | 1   | total-sales  | LAST 7 DAYS |
      | 1   | total-orders | LAST 7 DAYS |
    Then open shop on storefront
    And add products "<Products>" and switch to cart
    Then add recommend product of target product "<Products>" to cart
    Then open cart page
    And click button "Checkout"
    And checkout by Stripe successfully
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time        | Total | Usell | Shopbase |
      | 1   | total-sales  | TODAY       | 62.80 | 44.90 | 17.90    |
      | 1   | total-orders | TODAY       | 1     | 1     | 1        |
      | 1   | total-sales  | YESTERDAY   | 0     | 0     | 0        |
      | 1   | total-orders | YESTERDAY   | 0     | 0     | 0        |
      | 1   | total-sales  | LAST 7 DAYS | 62.80 | 44.90 | 17.90    |
      | 1   | total-orders | LAST 7 DAYS | 1     | 1     | 1        |
    Examples:
      | KEY | Products           |
      | 1   | Pineapple Earrings |