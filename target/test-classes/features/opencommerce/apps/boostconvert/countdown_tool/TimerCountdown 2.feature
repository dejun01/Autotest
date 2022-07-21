Feature: Timer Countdown

#  Env:
#  prod_copt_product_countdown
#  staging_copt_product_countdown

#  Theme: Inside, Roller
#  Products:
  #  Pineapple Earrings
  #  Bags
  #  ThermoCup
  #  Bracelet : collection "Coasters"


  Scenario: Delete all Timer countdown screen #SB_SF_BC_57
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Countdown Tools>Timer countdown" screen
    Then delete all timer countdown

  Scenario Outline: Add and edit  timer countdown #SB_SF_BC_58 #SB_SF_BC_60 #SB_SF_BC_61
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Convert" on Apps list
    And user navigate to "Countdown Tools>Timer countdown" screen
    And turn off all timer countdown of BoostConvert exited
    And input timer settings of BoostConvert's timer countdown as "<KEY>"
      | KEY | is created | Status | Duration | Schedule time | Trigger                          | Trigger pages              | Display timer | Repeat countdown timer after it ends | Schedule time to show Timer countdown |
      | 1   | true       | true   | 2>Days   |               | Show for all products            |                            | D,H,M,S       | true                                 | false                                 |
      | 2   | true       | true   | 2>Hours  |               | Show for all products            |                            | D,M,S         | false                                | false                                 |
      | 3   | true       | true   | 2>Hours  |               | Show for some products I specify | Product>Pineapple Earrings | D,H,M         | false                                | false                                 |
      | 4   | true       | true   | 3>Days   |               | Show for some products I specify | Collection>Coasters        | D,H,M,S       | false                                | false                                 |
      | 5   | false      | false  | 5>Days   | @TODAY@       | Show for all products            |                            | D,H,M,S       | true                                 | true                                  |
    Then save timer countdown of BoostConvert
    Then open shop on storefront
    Then verify timer countdown is shown on product page as "<KEY>"
      | KEY | Product name       | is show | Display timer |
      | 1   | Bags               | true    | D,H,M,S       |
      | 2   | ThermoCup          | true    | M,S           |
      | 3   | Pineapple Earrings | true    | H,M           |
      | 3   | ThermoCup          | false   | H,M           |
      | 4   | Bracelet           | true    | D,H,M,S       |
      | 4   | ThermoCup          | false   | D,H,M,S       |
      | 5   | ThermoCup          | false   | D,H,M,S       |
      | 5   | Bags               | false   | D,H,M,S       |
      | 5   | ThermoCup          | false   | D,M,S         |

    Examples:
      | KEY | Testcase                                                                 |
      | 1   | Show in all products on prod page and under prod img on prod list(left)  |
      | 2   | Show in all products on prod page and under prod img on prod list(right) |
      | 3   | Show at selected product(s) on prod                                      |
      | 4   | Show at selected product(s) on Collections                               |
      | 5   | don't show Noti when turn off Noti                                       |
