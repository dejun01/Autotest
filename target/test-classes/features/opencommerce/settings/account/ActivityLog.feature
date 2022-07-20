Feature: Activity log on dashboard
# sbase_activity_log

  Background: user login to shopbase dashboard
    Given user login to shopbase dashboard

  Scenario: Balance Activity log #SB_SET_ACC_ACT_LOG_2 #SB_SET_ACC_ACT_LOG_3 #SB_SET_ACC_ACT_LOG_11
    When get total activity log by API
    When Navigate to Balance page
    And Update account payout "Payoneer account" with value "qa@beeeketing.net"
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then Verify Activity log
      | Module  | Activity               | Detail                |
      | Balance | Update balance setting | Update payout account |
    Then verify log detail
      | Attribute      | Value             |
      | payoneer_email | qa@beeeketing.net |
    When Navigate to Balance page
    And Update Auto recharge
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then Verify Activity log
      | Module  | Activity               | Detail                  |
      | Balance | Update balance setting | Update balance settings |
    Then verify log detail
      | Attribute  | Value |
      | auto_topup |       |
    When Navigate to Balance page
    And click to update card
    And update credit card
      | Card number      | Expired Date | CVV | Country |
      | 4242424242424242 | 11/22        | 113 | Vietnam |
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then Verify Activity log
      | Module  | Activity    | Detail                |
      | Balance | Update card | Update payment method |
    Then verify log detail
      | Attribute      | Value                      |
      | payment_method | card                       |
      | type           | credit_card ending in 4242 |
    And close browser

  Scenario: Account Activity log #SB_SET_ACC_ACT_LOG_4 #SB_SET_ACC_ACT_LOG_5
    When get total activity log by API
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And get current plan
    And go to Upgrade plan page
    And choose another plan
    Then Verify Activity log
      | Module  | Activity     | Detail       |
      | Account | Confirm plan | Confirm plan |
    Then verify log detail
      | Attribute | Value   |
      | period    | monthly |
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And Add account staff with email "staff@beeketing.net"
    Then delete all staff account
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then Verify Activity log
      | Module  | Activity  | Detail                                 |
      | Account | Add staff | Add staff account: staff@beeketing.net |
#    When user navigate to "Settings" screen
#    And Click to "Account" section at Settings screen
#    And close store and reopen
#    When user login to shopbase dashboard
#    When user navigate to "Settings" screen
#    And Click to "Account" section at Settings screen
#    Then Verify Activity log
#      | Module  | Activity    | Detail      |
#      | Account | Close store | Close store |
#    When user navigate to "Settings" screen
#    And Click to "Account" section at Settings screen
#    Then Verify Activity log
#      | Module  | Activity     | Detail       |
#      | Account | Reopen store | Reopen store |
    And close browser

  Scenario: Profile Activity log #SB_SET_ACC_ACT_LOG_9 #SB_SET_ACC_ACT_LOG_10
    When get total activity log by API
    When Navigate to profile page
    And Change first name and last name
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then Verify Activity log
      | Module  | Activity       | Detail          |
      | Profile | Update profile | Update settings |
    When Navigate to profile page
    And Change phone number
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    Then Verify Activity log
      | Module  | Activity       | Detail          |
      | Profile | Update profile | Update settings |
    Then verify log detail
      | Attribute | Value                  |
      | email     | shopbase@beeketing.net |
    And close browser
