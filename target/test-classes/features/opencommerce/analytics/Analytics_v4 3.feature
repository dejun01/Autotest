@Analytics
Feature: Analytics sbase v4
#  sbase_analytics

  @sbase_analytics
  Scenario: Verify Analytics Step By Step #SB_ANA_V2_1 #SB_ANA_V2_2 #SB_ANA_V2_5 #SB_ANA_V2_6 #SB_ANA_V2_7 #SB_ANA_V2_12
    Given get "shopbase" data analytics new version by API
      | Source  |
      | sbase   |
      | usell   |
      | summary |
    And open product "abc" on SF
    Then wait 50000 second
    Then wait 50000 second
    Given verify "shopbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | sbase   | +1           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |
      | usell   | +0           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |
      | summary | +1           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |

    And add product to cart without verify
    Then wait 50000 second
    Then wait 50000 second
    Given verify "shopbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | sbase   | +0           | +1          | +0               | +0                | +0           | +0          | +0          | +0     |
      | usell   | +0           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |
      | summary | +0           | +1          | +0               | +0                | +0           | +0          | +0          | +0     |

    And go to checkout page and checkout without verify with "other" user
      | Product PPC | Action PPC   |
      | ppc         | Add to order |
    Then wait 50000 second
    Then wait 50000 second
    Then wait 20000 second
    Given verify "shopbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | sbase   | +0           | +0          | +1               | +1                | +1           | +1          | +55         | +0     |
      | usell   | +1           | +1          | +0               | +1                | +1           | +1          | +30         | +0     |
      | summary | +0           | +0          | +1               | +1                | +1           | +2          | +85         | +0     |


  Scenario: Verify Analytics Cancel Order #SB_ANA_V2_14
    Given get "shopbase" data analytics new version by API
      | Source  |
      | summary |
    And open product "abc" on SF
    And add product to cart without verify
    And go to checkout page and checkout without verify with "other" user
      | Product PPC | Action PPC |
      |             |            |
    Then wait 50000 second
    Then wait 50000 second
    Given verify "shopbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | summary | +1           | +1          | +1               | +1                | +1           | +1          | +65         | +0     |
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then open cancel order popup then cancel fully order and restock items
    Then wait 50000 second
    Then wait 50000 second
    Given verify "shopbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | summary | +0           | +0          | +0               | +0                | +0           | +0          | -65         | +0     |
    Then quit browser

  Scenario: Verify Total Sales and Total Orders in Homepage
    Given clear all data
    When user login to shopbase dashboard
    Then verify total sales and total orders in homepage