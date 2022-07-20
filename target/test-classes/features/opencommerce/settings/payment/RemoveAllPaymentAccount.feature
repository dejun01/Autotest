#environment = sbase_gateway_activation

Feature: Remove all account #SB_SET_PMS_PP_27

  Scenario: Remove all account
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then remove all account
