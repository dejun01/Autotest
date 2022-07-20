@otp
Feature: New OTP when change phone/email/password
  #new_change_profile_otp

  Scenario: Choose another way when change phone/email/password
    Given user login to shopbase dashboard by API
    When Navigate to profile page
    And get profile data
    And Change phone number
    And resend OTP 5 times
    And verify label send OTP to current "phone"
    Then close popup OTP
    And Change phone number
    And verify warning and choose another way message is "true"
    And choose send OTP by "Get a code sent to your email address"
    And verify label send OTP to current "email"
    And Access email and verify
      | Subject                             |
      | Profile setting update confirmation |


