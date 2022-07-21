#environment = sbase_gateway_activation

Feature: Activating Stripe gateway

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Full activating to removing gateway flow #SB_SET_PMS_STR_1 #SB_SET_PMS_STR_4 #SB_SET_PMS_STR_5 #SB_SET_PMS_STR_6 #SB_SET_PMS_STR_7 #SB_SET_PMS_STR_8 #SB_SET_PMS_STR_9
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
    Then remove gateway account "Stripe" by account name ""
    And close browser
#
  Scenario: Activation Validation #SB_SET_PMS_STR_17 #SB_SET_PMS_STR_18 #SB_SET_PMS_STR_19
    Then validate activation input for "Stripe" in "Activate" mode
      | Case                 | Account name          | Public Key                                                                                                  | Secret Key                                                                                                  | Message           | Notice Message |
      | Account name blank   |                       | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 | Field is required |                |
      | Public Key is blank  | Stripe_Validate_input |                                                                                                             | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 | Field is required |                |
      | Private Key is blank | Stripe_Validate_input | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL |                                                                                                             | Field is required |                |
    And close browser

#  Scenario: Activation Validation in Edit mode
#    Given activate "Stripe" gateway successfully in "Activate" mode
#      | Public Key                                                                                                  | Secret Key                                                                                                  |
#      | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |
#    Then validate activation input for "Stripe" in "Edit" mode
#      | Case                                            | Public Key                                                                                                  | Secret Key                                                                                                  | Statement descriptor    | Message                                             | Notice Message |
#      | Public Key is blank                             |                                                                                                             | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 |                         | Field is required                                   |                |
#      | Private Key is blank                            | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL |                                                                                                             |                         | Field is required                                   |                |
#      | Statement descriptor is less than 5 characters  | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 | abc                     | Statement descriptor only contains 5-22 characters. |                |
#      | Statement descriptor is more than 22 characters | pk_test_51H0MC6CcU7rfeHrk7sE9cyETJZgKI3RR2jWs9MqL2iJ67o8M2aj0SrH56PlYgkdmbhs1HmD2ODpbwzp72Af4NIbe002OIRtxHL | sk_test_51H0MC6CcU7rfeHrkYB0CZVbfsSfP99Q5hhnGj7hjxbXMn6d7v6hpRlE06JoRbLd2KMWqqitg7twTBCZAKpBorMd700NXQUNss7 | ABCDE123456789012345678 | Statement descriptor only contains 5-22 characters. |                |
#   Then remove gateway account "Stripe" by account name ""
#    And close browser

  Scenario: Activating European Stripe gateway
    Then activate "Stripe" gateway successfully in "Activate" mode and activate EU payment method
      | EU account name | Public Key                                                                                                  | Secret Key                                                                                                  |
      | Stripe-EU       | pk_test_51HVAkLLKE0DGm0HXwlJJuCIwQe3Plinyis0tRLVjA7XRzNAiRO10FjNMV7isZ6UQ4WzZBXX0XCYzkQFaOrMYOMva00eyIJTMFQ | sk_test_51HVAkLLKE0DGm0HXiZvY2HZ3xfOXxqKL4jk2PPGc6lzPm1nTXKcJi4AWUpHzgZLrmpt4FLKuikZEkD8EaUVViqXW00INiGwEvV |
    Then verify "Stripe" gateway info after "Activating"