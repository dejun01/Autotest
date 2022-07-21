Feature: Owner can add account staffs with other a plan corresponding
  - Staff can use function if decentralized

# sbase_add_account_staff

  Scenario: Delete all staff account
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    When delete all staff account

  Scenario:  Verify add staff account #SB_SET_AAS_1
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And verify add staff account
      | Account                 | Msg                          |
      |                         | Email can't be blank         |
      | trang                   | Email is invalid             |
      | shopbase2@beeketing.net |                              |
      | shopbase2@beeketing.net | Email has already been taken |


  Scenario: Verify remove account #SB_SET_AAS_7
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And verify remove staff account
      | Account                 | Password | Msg                                          |
      | shopbase2@beeketing.net | 123456   | Something went wrong, please try again later |
      | shopbase2@beeketing.net |          | Please enter your password.                  |


  Scenario: Verify send email after add staff account #SB_SET_AAS_3
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And verify add staff account
      | Account | Is full permission | Permission |
      | @EMAIL@ | true               |            |
    And Access email and verify permission
      | Subject              |
      | Create staff account |


  Scenario: Verify add staff account by plan #SB_SET_AAS_2
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And Click to "Account" section at Settings screen
    And get number of staff account of shop current plan
    And Add account staff and verify staff amount

