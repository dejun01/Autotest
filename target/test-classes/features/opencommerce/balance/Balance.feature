@dashboardBalance
Feature: Balance
# sbase_balance_dashboard

  Background: Login to shopbase dashboard
    Given user login to shopbase dashboard

  Scenario Outline: Manual top up #SB_BAL_OLD_BL_1 #SB_BAL_OLD_BL_2 #SB_BAL_OLD_BL_16 #SB_BAL_OLD_BL_17 #SB_BAL_OLD_BL_23 #SB_BAL_OLD_BL_24
    When Navigate to Balance page
    And Get balance info by API
    And Top up "$40" to balance
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +40                     | +0                  | +0                |
    And Filter balance history by "Content" with value "Manual top-up"
    Given Verify balance invoice
      | Index | Type | Shop name | Content       | Amount | Status  | Created date | Latest transaction date |
      | 1     | IN   |           | Manual top-up | $40.00 | Success | @Now@        | @Now@                   |
    And Verify invoice detail
      | Index | Shop | Content       | Detail             | Type | Amount | Created date |
      | 1     |      | Manual top-up | Topped up manually | IN   | $40.00 | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content       | Amount | Status | Date  |
      | 1     | IN   | Manual top-up | $40.00 | Paid   | @Now@ |
    And clear all data
    And quit browser

    Examples: Key
      | KEY |
      | 1   |

  Scenario Outline: Balance auto top up #SB_BAL_OLD_BL_4 #SB_BAL_OLD_BL_25 #SB_BAL_OLD_BL_26
    And Get balance info by API
    And Charge "all" from balance with API
    And Navigate to Balance page
    And Get balance info by API
    Then enable auto top up and disable top up
    Then refresh page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +20.60                  | +0                  | +0                |
    And Filter balance history by "Content" with value "Automatical top-up"
    Given Verify balance invoice
      | Index | Type | Shop name | Content            | Amount      | Status  | Created date | Latest transaction date |
      | 1     | IN   |           | Automatical top-up | @autoTopup@ | Success | @Now@        | @Now@                   |
      | 2     | IN   |           | Automatical top-up | @autoTopup@ | Success | @Now@        | @Now@                   |
    And Verify invoice detail
      | Index | Shop | Content            | Detail                  | Type | Amount      | Created date |
      | 1     |      | Automatical top-up | Topped up automatically | IN   | @autoTopup@ | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content            | Amount      | Status | Date  |
      | 1     | IN   | Automatical top-up | @autoTopup@ | Paid   | @Now@ |
    And clear all data
    And quit browser

    Examples: Key
      | KEY |
      | 1   |

  Scenario Outline: Balance transaction fee #SB_BAL_OLD_BL_5
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And get rate of transaction fee
    And Get balance info by API
    When open product "hello" and one page checkout without verify with "other" user
    Given user login to shopbase dashboard by API
    And user navigate to "Orders" screen
    And open order detail on admin shop
    And verify order status change from "Authorized" to "Paid" when capture payment
    When Navigate to Balance page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | @transFee@              | +0                  | +0                |
    And Filter balance history by "Content" with value "Transaction fee collecting"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content                    | Amount     | Status  | Created date | Latest transaction date |
      | 1     | OUT  | @Shop name@ | Transaction fee collecting | @transFee@ | Success | @NotNow@     | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content                    | Detail                                            | Type | Amount     | Created date |
      | 1     | @Shop name@ | Transaction fee collecting | Paid for transaction fee of the order @OrderName@ | OUT  | @transFee@ | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content                                                      | Amount     | Status | Date  |
      | 1     | OUT  | The payment for the transaction fee of the order @OrderName@ | @transFee@ | Paid   | @Now@ |
    And quit browser

    Examples: Key
      | KEY |
      | 1   |

  Scenario Outline: Refund transaction fee #SB_BAL_OLD_BL_20
    And Get balance info by API
    And user navigate to "Orders" screen
    And open order detail on admin shop
    And refund order with amount "$10"
    When Navigate to Balance page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +0.05                   | +0.05               | +0                |
    And Filter balance history by "Content" with value "Transaction fee collecting"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content                    | Amount | Status             | Created date | Latest transaction date |
      | 1     | OUT  | @Shop name@ | Transaction fee collecting | $0.07  | Partially Refunded | @NotNow@     | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content                    | Detail                                            | Type | Amount | Created date |
      | 1     | @Shop name@ | Transaction fee collecting | Paid for transaction fee of the order @OrderName@ | OUT  | $0.07  | @NotNow@     |
    And Verify invoice transactions
      | Index | Type | Content                                             | Amount | Status | Date  |
      | 1     | IN   | The refund transaction fee of the order @OrderName@ | $0.05  | Paid   | @Now@ |
    And Get balance info by API
    And user navigate to "Orders" screen
    And open order detail on admin shop
    And refund order with amount "$4.99"
    When Navigate to Balance page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +0.02                   | +0.02               | +0                |
    And Filter balance history by "Content" with value "Transaction fee collecting"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content                    | Amount | Status   | Created date | Latest transaction date |
      | 1     | OUT  | @Shop name@ | Transaction fee collecting | $0.07  | Refunded | @NotNow@     | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content                    | Detail                                            | Type | Amount | Created date |
      | 1     | @Shop name@ | Transaction fee collecting | Paid for transaction fee of the order @OrderName@ | OUT  | $0.07  | @NotNow@     |
    And Verify invoice transactions
      | Index | Type | Content                                             | Amount | Status | Date  |
      | 1     | IN   | The refund transaction fee of the order @OrderName@ | $0.02  | Paid   | @Now@ |
    And clear all data
    And quit browser

    Examples: Key
      | KEY |
      | 1   |


