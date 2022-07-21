#environment = sbase_gateway_activation

Feature: Activating Braintree gateway

  Background: Navigate to Payment Setting page
    Given user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Full activating to removing gateway flow #SB_SET_PMS_BRT_1 #SB_SET_PMS_BRT_2 #SB_SET_PMS_BRT_3 #SB_SET_PMS_BRT_4 #SB_SET_PMS_BRT_5 #SB_SET_PMS_BRT_6 #SB_SET_PMS_BRT_7 #SB_SET_PMS_BRT_8
  # Activate Braintree gateway successfully
    Then activate "Braintree" gateway successfully in "Activate" mode
      | Merchant ID      | Public Key       | Private Key                      |
      | np5d9fjn7spj3n4q | fw74yzgnbm9d8pt9 | 80bdebaef6371d49286aad439a1ae9fc |
    Then verify "Braintree" gateway info after "Activating"
  # Edit Braintree gateway successfully without billing statement info in Edit mode
    Then activate "Braintree" gateway successfully in "Edit" mode
      | Merchant ID      | Public Key       | Private Key                      |
      | dpg582vkwd9rzmd9 | ff7yyx7z7g4sbyzv | 6f70abf187a0ec48d6624240bac7d680 |
    Then verify "Braintree" gateway info after "Editing"
      # Edit Braintree gateway successfully with billing statement info in Edit mode
    Then activate "Braintree" gateway successfully in "Edit" mode
      | Merchant ID      | Public Key       | Private Key                      |
      | np5d9fjn7spj3n4q | fw74yzgnbm9d8pt9 | 80bdebaef6371d49286aad439a1ae9fc |
    Then verify "Braintree" gateway info after "Editing"
  # Deactivate Braintree gateway successfully
    Then deactivate gateway "Braintree" by account name ""
    Then verify "Braintree" gateway info after "Deactivating"
  # Reactivate Braintree gateway successfully
    Then activate "Braintree" gateway successfully in "Reactivate" mode
      | Merchant ID      | Public Key       | Private Key                      |
      | dpg582vkwd9rzmd9 | ff7yyx7z7g4sbyzv | 6f70abf187a0ec48d6624240bac7d680 |
    Then verify "Braintree" gateway info after "Activating"
  #Remove account Braintree gateway successfully
    Then remove gateway account "Braintree" by account name ""
    And close browser

  Scenario: Activation Validation #SB_SET_PMS_BRT_9 #SB_SET_PMS_BRT_10 #SB_SET_PMS_BRT_11 #SB_SET_PMS_BRT_12 #SB_SET_PMS_BRT_13 #SB_SET_PMS_BRT_14 #SB_SET_PMS_BRT_15
    Then validate activation input for "Braintree" in "Activate" mode
      | Case                   | Account name             | Merchant ID       | Public Key        | Private Key                       | Message           | Notice Message                                           |
      | Account name is blank  |                          | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  | Field is required |                                                          |
      | Merchant ID is blank   | Braintree_Validate_input |                   | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  | Field is required |                                                          |
      | Public Key is blank    | Braintree_Validate_input | np5d9fjn7spj3n4q  |                   | 80bdebaef6371d49286aad439a1ae9fc  | Field is required |                                                          |
      | Secret Key is blank    | Braintree_Validate_input | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  |                                   | Field is required |                                                          |
      | Merchant ID is Invalid | Braintree_Validate_input | np5d9fjn7spj3n4q1 | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  |                   | The API credentials are invalid. Please check them again |
      | Public Key is invalid  | Braintree_Validate_input | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt91 | 80bdebaef6371d49286aad439a1ae9fc  |                   | The API credentials are invalid. Please check them again |
      | Private Key is invalid | Braintree_Validate_input | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 |                   | The API credentials are invalid. Please check them again |
    And close browser

#  Scenario: Activation Validation in Edit mode
#    Given activate "Braintree" gateway successfully in "Activate" mode
#      | Merchant ID      | Public Key       | Private Key                      |
#      | np5d9fjn7spj3n4q | fw74yzgnbm9d8pt9 | 80bdebaef6371d49286aad439a1ae9fc |
#    Then validate activation input for "Braintree" in "Edit" mode
#      | Case                                                        | Merchant ID       | Public Key        | Private Key                       | Statement descriptor    | Phone number | Message                                                                                                                                                                                                         | Notice Message                                           |
#      | Merchant ID is blank                                        |                   | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  |                         |              | Field is required                                                                                                                                                                                               |                                                          |
#      | Public Key is blank                                         | np5d9fjn7spj3n4q  |                   | 80bdebaef6371d49286aad439a1ae9fc  |                         |              | Field is required                                                                                                                                                                                               |                                                          |
#      | Secret Key is blank                                         | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  |                                   |                         |              | Field is required                                                                                                                                                                                               |                                                          |
#      | Merchant ID is Invalid                                      | np5d9fjn7spj3n4q1 | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  |                         |              |                                                                                                                                                                                                                 | The API credentials are invalid. Please check them again |
#      | Public Key is invalid                                       | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt91 | 80bdebaef6371d49286aad439a1ae9fc  |                         |              |                                                                                                                                                                                                                 | The API credentials are invalid. Please check them again |
#      | Private Key is invalid                                      | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 |                         |              |                                                                                                                                                                                                                 | The API credentials are invalid. Please check them again |
#      | Company name/DBA section characters < 3                     | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  | 12*ab                   | 2025550147   | Company name/DBA section must be either 3, 7 or 12 characters and the product descriptor can be up to 18, 14, or 9 characters respectively (with an * in between for a total descriptor name of 22 characters). | The API credentials are invalid. Please check them again |
#      | 3 < Company name/DBA section characters < 7                 | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  | 12345*ab                | 2025550147   | Company name/DBA section must be either 3, 7 or 12 characters and the product descriptor can be up to 18, 14, or 9 characters respectively (with an * in between for a total descriptor name of 22 characters). | The API credentials are invalid. Please check them again |
#      | 7 < Company name/DBA section characters < 12                | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc  | 12345678*ab             | 2025550147   | Company name/DBA section must be either 3, 7 or 12 characters and the product descriptor can be up to 18, 14, or 9 characters respectively (with an * in between for a total descriptor name of 22 characters). | The API credentials are invalid. Please check them again |
#      | Company name/DBA section characters > 12                    | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 | 1234567890123*ab        | 2025550147   | Company name/DBA section must be either 3, 7 or 12 characters and the product descriptor can be up to 18, 14, or 9 characters respectively (with an * in between for a total descriptor name of 22 characters). | The API credentials are invalid. Please check them again |
#      | Company name/DBA section characters = 3, length = 23        | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 | 123*1234567890123456789 | 2025550147   | Company name/DBA section must be either 3, 7 or 12 characters and the product descriptor can be up to 18, 14, or 9 characters respectively (with an * in between for a total descriptor name of 22 characters). | The API credentials are invalid. Please check them again |
#      | Company name/DBA section characters = 7, length = 23        | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 | 1234567*123456789012345 | 2025550147   | Company name/DBA section must be either 3, 7 or 12 characters and the product descriptor can be up to 18, 14, or 9 characters respectively (with an * in between for a total descriptor name of 22 characters). | The API credentials are invalid. Please check them again |
#      | Company name/DBA section characters = 12, length = 23       | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 | 123456789012*1234567890 | 2025550147   | Company name/DBA section must be either 3, 7 or 12 characters and the product descriptor can be up to 18, 14, or 9 characters respectively (with an * in between for a total descriptor name of 22 characters). | The API credentials are invalid. Please check them again |
#      | Phone number < 10 digits                                    | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 | 123456789012*123456789  | 202555014    | Phone must contain exactly 10 digits, and can only contain numbers, dashes and parentheses.                                                                                                                     |  |
#      | Phone number > 10 digits                                    | np5d9fjn7spj3n4q  | fw74yzgnbm9d8pt9  | 80bdebaef6371d49286aad439a1ae9fc1 | 123456789012*123456789  | 20255501471  | Phone must contain exactly 10 digits, and can only contain numbers, dashes and parentheses.                                                                                                                     |  |
#    Then remove gateway account "Braintree" by account name ""
#    And close browser
