Feature: Unify payment

  Background: Navigate to buy domain page
    Given user login to shopbase dashboard by API
    And Get balance info by API
    And Charge "all" from balance with API
    And Get balance info by API
    And user navigate to "Online Store>Domains" screen
    When Buy new domain "@unify-payment@"

  Scenario: Verify sidebar Unify payment
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance   |
      | False        | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Available balance@ |
    Then Verify data of sidebar Top up your Balance
      | Total   | Available balance   | Top up amount | Select a payment method |
      | @Price@ | @Available balance@ | @Price@       | Visa ending in 4242     |
    Given Top up "$1" to balance on sidebar Make a payment
    Then Verify top up fail on sidebar Top up your Balance
      | Error                    |
      | Invalid amount to top-up |
    Given Top up "$5" to balance on sidebar Make a payment
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance |
      | False        | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Top up amount@   |
    Then Verify data of sidebar Top up your Balance
      | Total   | Available balance | Top up amount        | Select a payment method |
      | @Price@ | @Top up amount@   | @Insufficient money@ | Visa ending in 4242     |
    Given Top up "@Insufficient money@" to balance on sidebar Make a payment
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance |
      | True         | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Price@           |

  Scenario: Verify buy domain with Unify payment flow
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance   |
      | False        | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Available balance@ |
    Then Verify data of sidebar Top up your Balance
      | Total   | Available balance   | Top up amount | Select a payment method |
      | @Price@ | @Available balance@ | @Price@       | Visa ending in 4242     |
    Given Top up "@Price@" to balance on sidebar Make a payment
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance |
      | True         | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Price@           |
    Then Click to button Pay now
    Given Navigate to Balance page
    And Filter balance history by "Content" with value "Buy new domain"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content        | Amount   | Status  | Created date | Latest transaction date |
      | 1     | OUT  | @Shop name@ | Buy new domain | -@Price@ | Success | @Now@        | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content        | Detail               | Type | Amount   | Created date |
      | 1     | @Shop name@ | Buy new domain | Buy domain: @Domain@ | OUT  | -@Price@ | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content              | Amount   | Status | Date  |
      | 1     | OUT  | Buy domain: @Domain@ | -@Price@ | Paid   | @Now@ |

  Scenario: Verify Pay later domain with Unify payment flow
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance   |
      | False        | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Available balance@ |
    And Complete order by pay later
    Given Navigate to Balance page
    And Filter balance history by "Content" with value "Buy new domain"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content        | Amount   | Status | Created date | Latest transaction date |
      | 1     | OUT  | @Shop name@ | Buy new domain | -@Price@ | Open   | @Now@        | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content        | Detail               | Type | Amount   | Created date |
      | 1     | @Shop name@ | Buy new domain | Buy domain: @Domain@ | OUT  | -@Price@ | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content              | Amount   | Status  | Date  |
      | 1     | OUT  | Buy domain: @Domain@ | -@Price@ | Pending | @Now@ |
    Then Pay invoice open
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance   |
      | False        | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Available balance@ |
    Then Verify data of sidebar Top up your Balance
      | Total   | Available balance   | Top up amount | Select a payment method |
      | @Price@ | @Available balance@ | @Price@       | Visa ending in 4242     |
    Given Top up "@Price@" to balance on sidebar Make a payment
    Then Verify data of sidebar Make a payment
      | Enough money | Details                              | Price   | Total   | Available balance |
      | True         | Buy new domain - @Domain@ - (1 year) | @Price@ | @Price@ | @Price@           |
    Then Click to button Pay now
    Given Navigate to Balance page
    And Filter balance history by "Content" with value "Buy new domain"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content        | Amount   | Status  | Created date | Latest transaction date |
      | 1     | OUT  | @Shop name@ | Buy new domain | -@Price@ | Success | @Now@        | @Now@                   |
    And Verify invoice detail
      | Index | Shop        | Content        | Detail               | Type | Amount   | Created date |
      | 1     | @Shop name@ | Buy new domain | Buy domain: @Domain@ | OUT  | -@Price@ | @Now@        |
    And Verify invoice transactions
      | Index | Type | Content              | Amount   | Status | Date  |
      | 1     | OUT  | Buy domain: @Domain@ | -@Price@ | Paid   | @Now@ |