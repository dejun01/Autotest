@Analytics
Feature: Analytics by Customer
# analytics_by_customers

  Background: Login to dashboard
    Given user login to shopbase dashboard

  Scenario: Verify first time checkout #SB_ANA_CUSA_56 #SB_ANA_CUSA_62 #SB_ANA_CUSA_67
    Given get chart First-time vs returning customers data first time by API init
      | Filter by | Customer type 1 | Customer type 2 |
      | sales     | first_time      | returning       |
      | order     | first_time      | returning       |
    Then open storefront shop on a new tab
    When open product "sample-testing-part-2" and one page checkout without verify with "first-time" user
    And switch to the first tab
    And wait 50000 second
    And wait 50000 second
    And user navigate to "Analytics" screen
    Then verify data chart Returning customers rate
      | Filter by | Customer type 1 | Customer type 2 |
      | customer  | first_time      | returning       |
    Then verify data chart First-time vs returning customers
      | Filter by | Customer type 1 | Customer type 2 | First-time customers | Returning customers | Total sales | Total orders |
      | sales     | first_time      | returning       | +30                  | 0                   | +30         | 0            |
      | order     | first_time      | returning       | +1                   | 0                   | 0           | +1           |
    And quit browser

  Scenario: Verify returning time checkout #SB_ANA_CUSA_57 #SB_ANA_CUSA_63 #SB_ANA_CUSA_68
    Given get chart First-time vs returning customers data first time by API init
      | Filter by | Customer type 1 | Customer type 2 |
      | sales     | first_time      | returning       |
      | order     | first_time      | returning       |
    Then open storefront shop on a new tab
    When open product "sample-testing-part-2" and one page checkout without verify with "returning" user
    And switch to the first tab
    And wait 50000 second
    And wait 50000 second
    And user navigate to "Analytics" screen
    Then verify data chart Returning customers rate
      | Filter by | Customer type 1 | Customer type 2 |
      | customer  | first_time      | returning       |
    Then verify data chart First-time vs returning customers
      | Filter by | Customer type 1 | Customer type 2 | First-time customers | Returning customers | Total sales | Total orders |
      | sales     | first_time      | returning       | 0                    | +30                 | +30         | 0            |
      | order     | first_time      | returning       | 0                    | +1                  | 0           | +1           |
    And quit browser

  Scenario: Verify chart with cancel order #SB_ANA_CUSA_61
    Given clear all data
    Then get chart First-time vs returning customers data first time by API init
      | Filter by | Customer type 1 | Customer type 2 |
      | sales     | first_time      | returning       |
      | order     | first_time      | returning       |
    And user navigate to "Orders>All orders" screen
    Then cancel first order in Order dashboard
    And wait 50000 second
    And wait 50000 second
    Then verify data chart First-time vs returning customers
      | Filter by | Customer type 1 | Customer type 2 | First-time customers | Returning customers | Total sales | Total orders |
      | sales     | first_time      | returning       | 0                    | -30                 | -30         | 0            |
      | order     | first_time      | returning       | 0                    | 0                   | 0           | 0            |
    And quit browser