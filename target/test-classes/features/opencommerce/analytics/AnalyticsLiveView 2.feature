@Analytics
Feature: Verify Analytics Live View
#  sbase_analytics_live_view
  Background: Login to dashboard
    Given user login to shopbase dashboard

  Scenario: Verify Visitor Right now, Total sessions, Total sales, Total orders #SB_ANA_RANA_1 #SB_ANA_RANA_2 #SB_ANA_RANA_3 #SB_ANA_RANA_4 #SB_ANA_RANA_5 #SB_ANA_RANA_6
    And user navigate to "Analytics>Live View" screen
    When get data visitors, sessions, sales, orders
    When get Live view conversion rate by API
    And open storefront shop on a new tab
    And switch to the first tab
    And verify data Live View increase
      | Visitors right now | Total sessions | Total sales | Total orders | View Product | Add to Cart | Reach Checkout | Order Count |
      | 1                  | 1              | 0           | 0            | 0            | 0           | 0              | 0           |
    And open product "realtime2" on SF
    And switch to the first tab
    And verify data Live View increase
      | Visitors right now | Total sessions | Total sales | Total orders | View Product | Add to Cart | Reach Checkout | Order Count |
      | 0                  | 0              | 0           | 0            | 1            | 0           | 0              | 0           |
    And add product to cart without verify
    And switch to the first tab
    And verify data Live View increase
      | Visitors right now | Total sessions | Total sales | Total orders | View Product | Add to Cart | Reach Checkout | Order Count |
      | 0                  | 0              | 0           | 0            | 0            | 1           | 0              | 0           |
    And go to checkout page and checkout without verify with "other" user
      | Product PPC | Action PPC |
      |             |            |
    And switch to the first tab
    And verify data Live View increase
      | Visitors right now | Total sessions | Total sales  | Total orders | View Product | Add to Cart | Reach Checkout | Order Count |
      | 0                  | 0              | @totalSales@ | 1            | 0            | 0           | 1              | 1           |
    And switch to the first tab
    And user navigate to "Orders" screen
    And open order detail on admin shop
    And refund order with amount "$10"
    And user navigate to "Analytics>Live View" screen
    And verify data Live View increase
      | Visitors right now | Total sessions | Total sales | Total orders | View Product | Add to Cart | Reach Checkout | Order Count |
      | 0                  | 0              | -10         | 0            | 0            | 0           | 0              | 0           |
    And quit browser

