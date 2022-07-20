#environment = sbase_gateway_activation

Feature: Activating Unlimint gateway

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Full activating to removing gateway flow #SB_SET_PMS_UNL_1 #SB_SET_PMS_UNL_2 #SB_SET_PMS_UNL_3 #SB_SET_PMS_UNL_4 #SB_SET_PMS_UNL_5 #SB_SET_PMS_UNL_6 #SB_SET_PMS_UNL_7 #SB_SET_PMS_UNL_8
  # Activate Unlimint gateway successfully
    Then activate "Unlimint" gateway successfully in "Activate" mode
      | Terminal Code | Terminal Password | Callback Secret |
      | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    |
    Then verify "Unlimint" gateway info after "Activating"
  # Edit Unlimint gateway successfully in Edit mode
    Then activate "Unlimint" gateway successfully in "Edit" mode
      | Terminal Code | Terminal Password | Callback Secret |
      | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    |
    Then verify "Unlimint" gateway info after "Editing"
  # Deactivate Unlimint gateway successfully
    Then deactivate gateway "Unlimint" by account name ""
    Then verify "Unlimint" gateway info after "Deactivating"
  # Reactivate Unlimint gateway successfully
    Then activate "Unlimint" gateway successfully in "Reactivate" mode
      | Terminal Code | Terminal Password | Callback Secret |
      | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    |
    Then verify "Unlimint" gateway info after "Activating"
  #Remove account Unlimint gateway successfully
    Then remove gateway account "Unlimint" by account name ""
    And close browser

  Scenario: Activation Validation #SB_SET_PMS_UNL_9 #SB_SET_PMS_UNL_10 #SB_SET_PMS_UNL_11 #SB_SET_PMS_UNL_13 #SB_SET_PMS_UNL_14 #SB_SET_PMS_UNL_16 #SB_SET_PMS_UNL_17 #SB_SET_PMS_UNL_18
    Then validate activation input for "Unlimint" in "Activate" mode
      | Case                         | Account name            | Terminal Code | Terminal Password | Callback Secret | Message           | Notice Message                                           |
      | Account name blank           |                         | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    | Field is required |                                                          |
      | Terminal Code is blank       | Unlimint_Validate_input |               | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    | Field is required |                                                          |
      | Terminal Password is blank   | Unlimint_Validate_input | 23921         |                   | 64kzmQR0c9IG    | Field is required |                                                          |
      | Callback Secret is blank     | Unlimint_Validate_input | 23921         | Y9D5Ro7pgbI8      |                 | Field is required |                                                          |
      | Terminal Code is invalid     | Unlimint_Validate_input | 23920         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    |                   | The API credentials are invalid. Please check them again |
      | Terminal Password is invalid | Unlimint_Validate_input | 23921         | Y9D5Ro7pgbI0      | 64kzmQR0c9IG    |                   | The API credentials are invalid. Please check them again |
#      | Callback Secret is invalid   | Cardpay_Validate_input | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9I0    |                   | The API credentials are invalid. Please check them again |
    And close browser

#  Scenario: Activation Validation in Edit mode
#    Given activate "Unlimint" gateway successfully in "Activate" mode
#      | Terminal Code | Terminal Password | Callback Secret |
#      | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    |
#    Then validate activation input for "Cardpay" in "Edit" mode
#      | Case                         | Terminal Code | Terminal Password | Callback Secret | Message           | Notice Message                                           |
#      | Terminal Code is blank       |               | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    | Field is required |                                                          |
#      | Terminal Password is blank   | 23921         |                   | 64kzmQR0c9IG    | Field is required |                                                          |
#      | Callback Secret is blank     | 23921         | Y9D5Ro7pgbI8      |                 | Field is required |                                                          |
#      | Terminal Code is invalid     | 23920         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    |                   | The API credentials are invalid. Please check them again |
#      | Terminal Password is invalid | 23921         | Y9D5Ro7pgbI0      | 64kzmQR0c9IG    |                   | The API credentials are invalid. Please check them again |
##      | Callback Secret is invalid   | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9I0    |                   | The API credentials are invalid. Please check them again |
#    Then remove gateway account "Unlimint" by account name ""
#    And close browser

