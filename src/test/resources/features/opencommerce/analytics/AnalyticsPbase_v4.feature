@Analytics
Feature: Analytics Pbase v4
#  pbase_analytics

  @sbase_analytics
  Scenario: Verify Analytics Step By Step
    Given get "printbase" data analytics new version by API
      | Source  |
      | sbase   |
      | usell   |
      | summary |
    And open product "mug0712" on SF
    Then wait 50000 second
    Then wait 50000 second
    Given verify "printbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | sbase   | +1           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |
      | usell   | +0           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |
      | summary | +1           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |

    And add product to cart without verify
    Then wait 50000 second
    Then wait 50000 second
    Given verify "printbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | sbase   | +0           | +1          | +0               | +0                | +0           | +0          | +0          | +0     |
      | usell   | +0           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |
      | summary | +0           | +1          | +0               | +0                | +0           | +0          | +0          | +0     |

    And go to checkout page and checkout without verify with "other" user
      | Product PPC | Action PPC |
      |             |            |
    Then wait 50000 second
#    Then wait 50000 second
    Given verify "printbase" data analytic new version by API
      | Source  | View Product | Add to Cart | Reached Checkout | Session Converted | Total Orders | Total Items | Total Sales | Profit |
      | sbase   | +0           | +0          | +1               | +1                | +1           | +1          | +0          | +14.07 |
      | usell   | +0           | +0          | +0               | +0                | +0           | +0          | +0          | +0     |
      | summary | +0           | +0          | +1               | +1                | +1           | +1          | +0          | +14.07 |
