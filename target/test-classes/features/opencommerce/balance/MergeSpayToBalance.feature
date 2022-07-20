@dashboardBalance
Feature: Merge Spay to Balance
# sbase_merge_spay_to_balance


  Scenario Outline: Convert Spay order to balance - Refund order #SB_BAL_MERGE_SPAY_3 #SB_BAL_MERGE_SPAY_4 #SB_BAL_MERGE_SPAY_5
    Given user login to shopbase dashboard
    When Navigate to Balance page
    And Top up "$10" to balance
    And Get balance info by API
    And open product "100usd" and one page checkout without verify with "other" user
    Given user login to shopbase dashboard by API
    When Navigate to Balance page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | -0.52                   | +0                  | +101.02           |
    And Filter balance history by "Content" with value "ShopBase Payments order collecting"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content                            | Amount  | Status  | Created date | Latest transaction date |
      | 1     | IN   | @Shop name@ | ShopBase Payments order collecting | $101.02 | Success | @Now@        | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content                            | Detail                                    | Type | Amount  | Created date |
      | 1     | @Shop name@ | ShopBase Payments order collecting | Collected profit of the order @OrderName@ | IN   | $101.02 | @Now@        |
    When Navigate to Balance page
    And Get balance info by API
    And user navigate to "Orders" screen
    And open order detail on admin shop
    And refund order with amount "$10"
    When Navigate to Balance page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +0.05                   | +0.05               | -10               |
    And Filter balance history by "Content" with value "ShopBase Payments order collecting"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content                            | Amount  | Status             | Created date | Latest transaction date |
      | 1     | IN   | @Shop name@ | ShopBase Payments order collecting | $101.02 | Partially Refunded | @NotNow@     | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content                            | Detail                                    | Type | Amount  | Created date |
      | 1     | @Shop name@ | ShopBase Payments order collecting | Collected profit of the order @OrderName@ | IN   | $101.02 | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content                      | Amount | Status | Date  |
      | 2     | OUT  | Refund the order @OrderName@ | $10.00 | Paid   | @Now@ |

    Examples: Key
      | KEY |
      | 1   |
