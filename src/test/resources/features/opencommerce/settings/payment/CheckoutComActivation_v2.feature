#environment = sbase_gateway_activation

Feature: Activating Checkout.com gateway

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Full activating to removing gateway flow #SB_SET_PMS_CC_20 #SB_SET_PMS_CC_23 #SB_SET_PMS_CC_24 #SB_SET_PMS_CC_25 #SB_SET_PMS_CC_26 #SB_SET_PMS_CC_27
  # Activate Checkout.com gateway successfully
    Then activate "Checkout.com" gateway successfully in "Activate" mode
      | Public Key                                   | Private Key                                  |
      | pk_test_87488bcc-5023-402b-b363-c3962453987c | sk_test_d5989751-9342-4abc-99f0-0ef483eb9fb8 |
    Then verify "Checkout.com" gateway info after "Activating"
  # Edit Checkout.com gateway successfully in Edit mode
    Then activate "Checkout.com" gateway successfully in "Edit" mode
      | Public Key                                   | Private Key                                  |
      | pk_test_c6bc4191-04ae-4e37-8d9b-d7b20f5d8dee | sk_test_64990054-2020-4494-b644-26a4d0effe5a |
    Then verify "Checkout.com" gateway info after "Editing"
  # Deactivate Checkout.com gateway successfully
    Then deactivate gateway "Checkout.com" by account name ""
    Then verify "Checkout.com" gateway info after "Deactivating"
  # Reactivate Checkout.com gateway successfully
    Then activate "Checkout.com" gateway successfully in "Reactivate" mode
      | Public Key                                   | Private Key                                  |
      | pk_test_87488bcc-5023-402b-b363-c3962453987c | sk_test_d5989751-9342-4abc-99f0-0ef483eb9fb8 |
    Then verify "Checkout.com" gateway info after "Activating"
  #Remove account Checkout.com gateway successfully
    Then remove gateway account "Checkout.com" by account name ""
    And close browser
