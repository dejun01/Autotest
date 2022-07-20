Feature: As a merchant, I want to manage Payment method of my store
 # "environment": "staging_print_hub_payment_method"
  Scenario Outline: Verify status of ShopBase Balance
    Given Description: "<Testcase>"
    And call api to create next payment or charge payment Print Hub
    And user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And call api to create next payment or charge payment Print Hub
    And user navigate to "Payments" screen
    And change status ShopBase Balance as "<KEY>"
      | KEY | Status     | Message                         |
      | 1   | Activate   | Activate successfully           |
      | 2   | Deactivate | Shopbase Balance is deactivated |
    And verify UI after change status as "<KEY>"
      | KEY | Is enable Next paymenet | Is enable Pay now |
      | 1   | true                    | true              |
      | 2   | false                   | false             |
    And close browser
    Examples: <Key>
      | KEY | Testcase                 |
      | 1   | Enable ShopBase Balance  |
      | 2   | Disable ShopBase Balance |
