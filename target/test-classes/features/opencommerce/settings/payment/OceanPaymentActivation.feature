Feature: Activating Ocean Payment gateway

#sbase_oceanpayment_activation
  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: remove account oceanpayment #SB_CHE_OCP_13
#    Then remove all account
    Then remove all account gateways

  Scenario: activate new account ocean payment #SB_CHE_OCP_10 #SB_CHE_OCP_12
    When "activate" ocean payment gateway successfully in "Activate" mode
      | Account name  | Account | Terminal | Secure Code |
      | OceanPayment- | 150260  | 15026001 | 12345678    |
    And open shop on storefront
    And go to payment method on checkout page
    Then verify "activate" ocean payment gateway successfully in storefront

  Scenario: deactivate account ocean payment #SB_CHE_OCP_11
    When "deactivate" ocean payment gateway successfully in "Deactivate" mode
      | Account name | Account | Terminal | Secure Code |
      | oceanpay     | 150260  | 15026001 | 12345678    |
    And open shop on storefront
    And go to payment method on checkout page
    Then verify "deactivate" ocean payment gateway successfully in storefront

  Scenario: activate account ocean payment in edit mode #SB_CHE_OCP_14
    When "activate" ocean payment gateway successfully in "Edit" mode
      | Account name | Account | Terminal | Secure Code |
      | oceanpay     | 150260  | 15026001 | 12345678    |
    And open shop on storefront
    And go to payment method on checkout page
    Then verify "activate" ocean payment gateway successfully in storefront

