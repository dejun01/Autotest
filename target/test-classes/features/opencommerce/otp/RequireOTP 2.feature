@otp @paymentProvider
Feature: Show OTP popup when activate payment provider

  Scenario: Require OTP when add a new account payment provider
    Given user login to shopbase dashboard
    When user navigate to "Settings" screen
    And navigate to "Payment providers" section in Settings page
    When choose payment providers
      | payment provider type       | payment provider name |
      | Choose third-party provider | Stripe                |
    And fill keys to activate payment provider
      | account name | label public key/client id | label secret key | public key/client id | secret key |
      | Stripe-1     | * Public Key               | * Secret Key     | Stripe-1             | Stripe-1   |
    Then verify popup require OTP is displayed
    When user navigate to "Settings" screen
    And navigate to "Payment providers" section in Settings page
    When choose payment providers is Paypal
    And fill keys to activate payment provider
      | account name | label public key/client id | label secret key    | public key/client id | secret key |
      | Paypal-1     | * Client ID                | * Client Secret Key | Paypal-1             | Paypal-1   |
    Then verify not show popup require OTP
    When user navigate to "Settings" screen
    And navigate to "Payment providers" section in Settings page
    When choose payment providers
      | payment provider type       | payment provider name |
      | Choose alternative provider | asiabill              |
    And fill information alternative provider
      | account name | merchant no | gateway no | sign key   |
      | account name | asiabill-1  | asiabill-1 | asiabill-1 |
    Then verify not show popup require OTP








