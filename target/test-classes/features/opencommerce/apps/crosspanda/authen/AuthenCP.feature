Feature: Authen CrossPanda
#crosspanda_authen

  Scenario: Sign in
    Given open sign in crosspanda page
    When  sign in to CrossPanda
      | Email                 | Pass        | Message                                           |
      |                       | 123456      | Please enter an email address                     |
      | hana.crosspanda.com   | 123456      | Please enter a valid email address                |
      | doe@crosspanda.com    | 123456      | This email address does not exist, do you want to |
      | hana@crosspanda.com   |             | Please enter the password                         |
      | tratra@crosspanda.com | 123456hgfff | The password is incorrect                         |
      | tratra@crosspanda.com | 123456      |                                                   |
    Then close browser

  Scenario: AU-SUCP-1.1 Valid data when sign up account
    Given open sign up crosspanda page
    When validate data Sign up page in CrossPanda
      | Email                 | Password | Message                                                  |
      |                       | 123456   | Please enter an email address                            |
      | hana.crosspanda.com   | 123456   | Please enter a valid email address                       |
      | $^@&.crosspanda.com   | 123456   | Please enter a valid email address                       |
      | tratra@crosspanda.com | 123456   | This email address has already been used, do you want to |
      | hana1@crosspanda.com  |          | Please enter a password                                  |
      | hana2@crosspanda.com  | 123      | Password must be at least 5 characters.                  |
#      | dream@crosspanda.com  | 123abc   |                                                          |
#    Then verify sign up succesfully
#      | Email                | Password |
#      | dream@crosspanda.com | 123abc   |
#    And remove account
#      | Email                |
#      | dream@crosspanda.com |
    And close browser

  Scenario: Forgot password and reset password
    Given open sign in crosspanda page
    And go to "Forgot password?" page
    Then validate data forgot password page in CrossPanda
      | Email                 | Message                                           |
      |                       | Please enter an email address                     |
      | joes@crosspanda.com   | This email address does not exist, do you want to |
      | hana.crosspanda.com   | Please enter a valid email address                |
      | tratra@crosspanda.com |                                                   |
    And close browser