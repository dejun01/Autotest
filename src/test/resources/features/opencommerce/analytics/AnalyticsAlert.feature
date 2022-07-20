@Analytics
Feature: Verify Analytics Alert
#  sbase_analytics
  #Production isn't increase Session Converted
  @sbase_analytics
  Scenario Outline: Verify Analytics Total orders = Session converted
    Given clear all data
    Given get data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  |
      | 1   | total-sales  | TODAY |
      | 1   | total-orders | TODAY |
      | 1   | actions      | TODAY |
    Given verify data analytic by API as "<KEY>"
      | KEY | Chart type   | Time  | Total | Shopbase | Usell | View product | Add to cart | Reached checkout | Session converted |
      | 1   | total-sales  | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | total-orders | TODAY | 0     | 0        | 0     |              |             |                  |                   |
      | 1   | actions      | TODAY | 0     | 0        | 0     | 1            | 0           | 0                | 0                 |
    Examples:
      | KEY |
      | 1   |
