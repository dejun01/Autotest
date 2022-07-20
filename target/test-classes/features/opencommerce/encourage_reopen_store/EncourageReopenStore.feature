Feature: Encourage Reopen Store
  #sbase_encourage_reopen_store

  Scenario Outline: Check display offer when close store #SB_EROP_6 #SB_EROP_1 #SB_EROP_9
    Given login to hive sbase
    And clear shop data as "<Shop id>"
    Then login to dashboard
    And choose shop as "<KEY>"
      | KEY | Shop name           |
      | 1   | au-encourage-reopen |
    When complete Onboarding create shop
      | Country | Personal location | Phone       | Business type   | Store type              | Question                                                           | Answer                                | Question1                                             | Answer1 | Question2                         | Answer2 |
      | Vietnam | Vietnam           | 098 1111111 | Print On Demand | I want a ShopBase store | Which segment below best describes your main POD product category? | 2D (T-shirt, Hoodie, Mug, Doormat...) | Which platforms you are using/ used to use the most ? | Teechip | How did you know about PrintBase? | Other   |
    Then user navigate to "Settings" screen
    And navigate to "Account" section in Settings page
    And check not display offer when user is being free trial
    And check display offer when close store
    Examples:
      | KEY | Shop id |
      | 1   |         |

  Scenario: Check claim offer successfully #SB_EROP_2 #SB_EROP_1
    Given login to dashboard
    Then check claim offer successfully
    And Verify balance invoice
      | Index | Type | Shop name           | Content              | Amount | Status  | Created date | Latest transaction date |
      | 1     | IN   | au-encourage-reopen | Store Relaunch offer | $10.00 | Success | @Now@        | @Now@                   |
    Then check not display offer when store has claimed


