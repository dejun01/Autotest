@Analytics
Feature: Verify Analytics
#  sbase_analytics
  #Production isn't increase Session Converted
  @sbase_analytics
  Scenario Outline: Verify Analytics Step By Step #SB_ANA_V2_1 #SB_ANA_V2_2 #SB_ANA_V2_5 #SB_ANA_V2_6 #SB_ANA_V2_7 #SB_ANA_V2_12
    Given clear all data
    Given get data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  |
      | 1   | total-sales  | TODAY |
      | 1   | total-orders | TODAY |
      | 1   | actions      | TODAY |
    When open shop on storefront
    And open page "Wallet"
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 0           | 0                | 0                 |
    And add products "Wallet" to cart
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 1           | 0                | 0                 |
    When click to button checkout
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 1           | 1                | 0                 |
    And checkout by Stripe successfully
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 30    | 30       | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 1     | 1        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 1           | 1                | 1                 |
    When open shop on storefront
    And open page "Wallet"
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 30    | 30       | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 1     | 1        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 1           | 1                | 1                 |
    And add products "Wallet" to cart
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 30    | 30       | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 1     | 1        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 1           | 1                | 1                 |
    When click to button checkout
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 30    | 30       | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 1     | 1        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 1           | 1                | 1                 |
    And checkout by Stripe successfully
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 60    | 60       | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 2     | 2        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 1           | 1                | 1                 |
    Examples:
      | KEY |
      | 1   |

  Scenario Outline: Verify Analytics Cancel Order #SB_ANA_V2_14
    Given clear all data
    Given get data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  |
      | 1   | total-sales  | TODAY |
      | 1   | total-orders | TODAY |
      | 1   | actions      | TODAY |
    When open shop on storefront
    And add products "Wallet" to cart then checkout
    And checkout by Stripe successfully
    And verify thank you page
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Usell | Total | Shopbase | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 0     | 30    | 30       |              |             |                  |                   |
      | 1   | total-orders | TODAY | 0     | 1     | 1        |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0     | 0        | 1            | 1           | 1                | 1                 |
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then open cancel order popup then cancel fully order and restock items
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Usell | Total | Shopbase | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 0     | 0     | 0        |              |             |                  |                   |
      | 1   | total-orders | TODAY | 0     | 1     | 1        |              |             |                  |                   |
      | 1   | actions      | TODAY |       |       |          | 1            | 1           | 1                | 1                 |

    Examples:
      | KEY |
      | 1   |

  Scenario: Verify Total Sales and Total Orders in Homepage
    Given clear all data
    When user login to shopbase dashboard
    Then verify total sales and total orders in homepage