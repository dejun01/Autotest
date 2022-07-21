#environment = sbase_gateway_activation

Feature: Activating Stripe gateway

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Full activating to removing gateway flow
  # Activate stripe gateway successfully
    Then activate "Stripe" gateway successfully in "Activate" mode
      | Public Key                                                                                                  | Secret Key                                                                                                  |
      | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
    Then verify "Stripe" gateway info after "Activating"
  # Edit stripe gateway successfully in Edit mode
    Then activate "Stripe" gateway successfully in "Edit" mode
      | Public Key                                                                                                  | Secret Key                                                                                                  |
      | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
    Then verify "Stripe" gateway info after "Editing"
  # Deactivate stripe gateway successfully
    Then deactivate gateway "Stripe" by account name ""
    Then verify "Stripe" gateway info after "Deactivating"
  # Reactivate stripe gateway successfully
    Then activate "Stripe" gateway successfully in "Reactivate" mode
      | Public Key                                                                                                  | Secret Key                                                                                                  |
      | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
    Then verify "Stripe" gateway info after "Activating"
  #Remove account stripe gateway successfully
    Then remove gateway account by account name ""
    And close browser

  Scenario: Activation Validation
    Then validate activation input for "Stripe" in "Activate" mode
      | Case                 | Account name          | Public Key                                 | Secret Key                                                                                                  | Message           | Notice Message |
      | Account name blank   |                       | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI | Field is required |                |
      | Public Key is blank  | Stripe_Validate_input |                                            | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI | Field is required |                |
      | Private Key is blank | Stripe_Validate_input | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 |                                                                                                             | Field is required |                |
#      | Public Key is not exist      | Stripe_Validate_input | abc                                        | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI |                   | The API credentials are invalid. Please check them again |
#      | Private Key is not exist     | Stripe_Validate_input | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 | abc                                        |                   | The API credentials are invalid. Please check them again |
#      | Account stripe is not active | Stripe_Validate_input | pk_live_3ZgcM9bN8ereNY4EBCrCk01S00aF2igKZh | sk_live_P2hRJ1Y2VoiPgH74PghpHMP800sx8mIssl |                   | The API credentials are invalid. Please check them again |
    And close browser

  Scenario: Activation Validation in Edit mode
    Given activate "Stripe" gateway successfully in "Activate" mode
      | Public Key                                 | Secret Key                                                                                                  |
      | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI |
    Then validate activation input for "Stripe" in "Edit" mode
      | Case                                            | Public Key                                 | Secret Key                                                                                                  | Statement descriptor    | Message                                             | Notice Message |
      | Public Key is blank                             |                                            | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI |                         | Field is required                                   |                |
      | Private Key is blank                            | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 |                                                                                                             |                         | Field is required                                   |                |
#      | Public Key is not exist                         | abc                                        | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI |                         |                                                     | The API credentials are invalid. Please check them again |
#      | Private Key is not exist                        | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 | abc                                        |                         |                                                     | The API credentials are invalid. Please check them again |
      | Statement descriptor is less than 5 characters  | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI | abc                     | Statement descriptor only contains 5-22 characters. |                |
      | Statement descriptor is more than 22 characters | pk_test_e8GujJjEgZCK93bxkRmJSpli00zfSmBeF5 | sk_test_51GTg1iJJ26GWoaVzXAIb7TYLywlxm1XLQmrYSuTgg2T22t8RjJ4aoOfrPxX4oTxipdNn3QPogRwpPXfVyAzy1OHD005VfiacFI | ABCDE123456789012345678 | Statement descriptor only contains 5-22 characters. |                |
#      | Account stripe is not active                    | pk_live_3ZgcM9bN8ereNY4EBCrCk01S00aF2igKZh | sk_live_P2hRJ1Y2VoiPgH74PghpHMP800sx8mIssl | ABCDE123456789012345678 |                                                     | Your Stripe account cannot currently make live charges |
    Then remove gateway account by account name ""
    And close browser

