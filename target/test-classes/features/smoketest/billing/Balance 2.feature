@dashboardBalance
Feature: Balance
#prod_sbase_smoke_balance
  @Pine
  Scenario Outline: Balance dashboard
    Given user login to shopbase dashboard
    When Navigate to Balance page
    And Get balance info by API
    And Top up "$40" to balance
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +40                     | +0                  | +0                |

    Examples: Key
      | KEY |
      | 1   |