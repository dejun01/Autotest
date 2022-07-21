@dashboardBalance
Feature: Top-up Wire Transfer
# sbase_topup_wire_transfer
  Background: user login to shopbase dashboard
    Given user login to shopbase dashboard

  Scenario Outline: Create top up by Money transfer #SB_BAL_TOP_WT_1 #SB_BAL_TOP_WT_2 #SB_BAL_TOP_WT_3 #SB_BAL_TOP_WT_6 #SB_BAL_TOP_WT_7 #SB_BAL_TOP_WT_8 #SB_BAL_TOP_WT_9 #SB_BAL_TOP_WT_10 #SB_BAL_TOP_WT_12
    Given Navigate to Balance page
    And Get balance info by API
    Given Top up to balance by Money transfer as "<KEY>"
      | KEY | Amount | Preferred account | Account holder   | Account email    | Transaction ID   | Note               |
      | 1   | 5.00   | US Bank Account   | Test_Au_US_Bank  |                  |                  | Test_Note_US_Bank  |
#      | 2   | 6.00   | PayPal            |                  | qa@beeketing.net | Test_PP_ID     |                    |
      | 3   | 7.00   | Payoneer          | Test_Au_Payoneer | qa@beeketing.net |                  | Test_Note_Payoneer |
      | 4   | 8.00   | PingPong          | Test_Au_PingPong | qa@beeketing.net | Test_PingPong_ID |                    |
    Then Navigate to Balance page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +0                      | +0                  | +0                |
#      | 2   | +0                      | +0                  | +0                |
      | 3   | +0                      | +0                  | +0                |
      | 4   | +0                      | +0                  | +0                |
    And Filter balance history by "Content" with value "Manual top-up via wire transfer"
    Given Verify invoice Money transfer as "<KEY>"
      | KEY | Type | Content                         | Amount | Status    |
      | 1   | IN   | Manual top-up via wire transfer | $5.00  | In Review |
#      | 2   | IN   | Manual top-up via wire transfer | $6.00  | In Review |
      | 3   | IN   | Manual top-up via wire transfer | $7.00  | In Review |
      | 4   | IN   | Manual top-up via wire transfer | $8.00  | In Review |
    And Verify transactions Money transfer as "<KEY>"
      | KEY | Index | Type | Detail                                   | Content                       | Amount | Status  |
      | 1   | 1     | IN   | Topped up manually via Bank transfer     | Manual top-up with payment id | $5.00  | Pending |
#      | 2   | 1     | IN   | Topped up manually via Paypal transfer   | Manual top-up with payment id | $6.00  | Pending |
      | 3   | 1     | IN   | Topped up manually via Payoneer transfer | Manual top-up with payment id | $7.00  | Pending |
      | 4   | 1     | IN   | Topped up manually via PingPong transfer | Manual top-up with payment id | $8.00  | Pending |
    Then login to hive sbase
    And filter & verify top up request by top up code
    Then logout to hive sbase
    Then clear all data
    And close browser
    Examples: <Key>
      | KEY |
      | 1   |
#      | 2   |
      | 3   |
      | 4   |

  Scenario Outline: Approve, Refuse top up request #SB_BAL_TOP_WT_4 #SB_BAL_TOP_WT_5
    Given Navigate to Balance page
    And Get balance info by API
    Given Top up to balance by Money transfer as "<KEY>"
      | KEY | Amount | Preferred account | Account holder   | Account email    | Transaction ID   | Note            |
      | 1   | 6.50   | Payoneer          | Test_Au_Payoneer | qa@beeketing.net |                  | Approve request |
      | 2   | 8.40   | PingPong          | Test_Au_PingPong | qa@beeketing.net | Test_PingPong_ID | Refuse request  |
    Then login to hive sbase
    And filter & verify top up request by top up code
    Then Approve/ Refuse top up request as "<KEY>"
      | KEY | Action  |
      | 1   | Approve |
      | 2   | Refuse  |
    Then logout to hive sbase
    When user login to shopbase dashboard by API
    And Navigate to Balance page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +6.5                    | +0                  | +0                |
      | 2   | +0                      | +0                  | +0                |
    And Filter balance history by "Content" with value "Manual top-up via wire transfer"
    Given Verify invoice Money transfer as "<KEY>"
      | KEY | Type | Content                         | Amount | Status  |
      | 1   | IN   | Manual top-up via wire transfer | $6.50  | Success |
      | 2   | IN   | Manual top-up via wire transfer | $8.40  | Void    |

    And Verify transactions Money transfer as "<KEY>"
      | KEY | Index | Type | Detail                                   | Content                       | Amount | Status |
      | 1   | 1     | IN   | Topped up manually via Payoneer transfer | Manual top-up with payment id | $6.50  | Paid   |
      | 2   | 1     | IN   | Topped up manually via PingPong transfer | Manual top-up with payment id | $8.40  | Failed |

    Examples: <Key>
      | KEY |
      | 1   |
      | 2   |