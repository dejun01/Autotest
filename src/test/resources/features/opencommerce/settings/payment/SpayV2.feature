Feature: Register flow for Spay V2
#env = spay_ver2
  Background: Navigate to Payment Setting page
    When user login to shopbase dashboard by API
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Spay Reseller register successfully for Hong Kong = Individual/sole proprietorship -> using Spay V2
    Given check status Spay account then reset Spay V2 account if needed
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    When select "I want ShopBase Payments" and Submit KYC Spay V2 register form for Stripe
      | Country   | Business type | Legal business name | Employer EIN/SSN | Address      | City     | ZIP Code | First Name | Last Name | Job title | Email                 | ID document | ID number   | Statement Descriptor | Phone number | Account number | Routing number |
      | Hong Kong | Corporation   | Bee                 | 1234             | 4 Harbour Rd | Wan Chai | 999077   | Shopbase   | Auto      |           | spayv2@mailtothis.com | yes         | 123-45-6789 | SpayV2               | 2025550191   | 000123-456     | 110-000        |
    Given login to hive sbase
    And approve Spay V2 account