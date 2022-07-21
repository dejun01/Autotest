Feature: Account Security
  #prod_sbase_authorized_first_charge

  Background: Login to shopbase dashboard
    Given user login to shopbase dashboard

  Scenario: Change phone number successfully
    When Navigate to profile page
    And Change phone "0962521851"
    And Input current password
    And Input otp "140396"
    And Input otp "140396"
    Then Verify change phone number successfully


  Scenario: Change owner email fail
    When Navigate to profile page
    And Change email "anhtran+2510@beeketing.net"
    And Click button Save
    And Input password, input otp & verify status
      | Password          | Otp    | Msg password                                                | Msg otp                                                          |
      | 1234567           | 123456 | The password you've entered is incorrect. Please try again. | The OTP you've entered is incorrect or expired. Please try again |
      | 1234567           | 140396 | The password you've entered is incorrect. Please try again. |                                                                  |
      | @currentPassword@ | 123456 |                                                             | The OTP you've entered is incorrect or expired. Please try again |

#    And Input password "012345"
#    And Input otp "123456"
#    Then Click button Confirm

  Scenario: Update phone successful
    When Navigate to profile page
    And Change phone "0963834927"
    And Input current password
    And Input otp "140396"
    And Input otp "140396"
    Then Verify change phone number successfully

    
    