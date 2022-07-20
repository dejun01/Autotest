Feature: Register flow for Spay Reseller
#env = spay_reseller_activation

  Background: Navigate to Payment Setting page
    Given clear all data
    Given reset Spay Reseller account
    When user login to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen

  Scenario: Spay Reseller register successfully for United States
    Then verify status New of Spay Reseller account
    When submit KYC form to register for Spay Reseller
    # This code is for feature switch revise_spay_nov_2021
    #When select "I want ShopBase Marketplace Payments" and submit KYC form to register for Spay Reseller
      | Country       | Legal business name | Employer Identification Number | Address                     | City        | ZIP   | State      | Business registration | First name | Last name | Job title | Email                       | ID number | ID front | ID back | Picture with ID | Product details | Dispute history |
      | United States | Clothes             | 1234                           | 10800 W Pico Blvd Suite 312 | Los Angeles | 90001 | California | yes                   | Shopbase   | Auto      | CEO       | spayReseller@mailtothis.com | 1234      | yes      | yes     | yes             | Clothes         | Yes             |
    Then verify status Under Review of Spay Reseller account
    Given login to hive sbase
    Then Approve Spay Reseller account
    And redirect to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then verify status Approved of Spay Reseller account
    And Enable Spay Reseller account
    Then verify Spay Reseller after activation
    And Deactivate Spay Reseller account
    Then verify status Deactivated of Spay Reseller account

  Scenario: Spay Reseller register successfully for VietNam
    Then verify status New of Spay Reseller account
    When submit KYC form to register for Spay Reseller
#    When select "I want ShopBase Marketplace Payments" and submit KYC form to register for Spay Reseller
      | Country | Legal business name | Employer Identification Number | Address         | City   | ZIP   | Business registration | First name | Last name | Job title | Email                       | ID number | ID front | ID back | Picture with ID | Product details | Dispute history |
      | Vietnam | Brodev              | 1234                           | 130 Trung Phung | Ha Noi | 10000 | yes                   | Shopbase   | Auto      | CEO       | spayReseller@mailtothis.com | 1234      | yes      | yes     | yes             | Brodev          | Yes             |
    Then verify status Under Review of Spay Reseller account
    Given login to hive sbase
    Then Approve Spay Reseller account
    And redirect to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then verify status Approved of Spay Reseller account
    And Enable Spay Reseller account
    Then verify Spay Reseller after activation
    And Deactivate Spay Reseller account
    Then verify status Deactivated of Spay Reseller account

  Scenario: Spay Reseller register successfully for Hong Kong with business type = Individual/sole proprietorship -> using SMP
    Then verify status New of Spay Reseller account
    When submit KYC form to register for Spay Reseller
#    When select "I want ShopBase Marketplace Payments" and submit KYC form to register for Spay Reseller
      | Country   | Business type                  | Address      | City     | ZIP    | State            | Business registration | First name | Last name | Email                       | ID number | ID front | ID back | Picture with ID | Product details | Dispute history |
      | Hong Kong | Individual/sole proprietorship | 4 Harbour Rd | Wan Chai | 999077 | Hong Kong Island | yes                   | Shopbase   | Auto      | spayReseller@mailtothis.com | 1234      | yes      | yes     | yes             | Brodev          | Yes             |
    Then verify status Under Review of Spay Reseller account
    Given login to hive sbase
    Then Approve Spay Reseller account
    And redirect to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then verify status Approved of Spay Reseller account
    And Enable Spay Reseller account
    Then verify Spay Reseller after activation
    And Deactivate Spay Reseller account
    Then verify status Deactivated of Spay Reseller account

  Scenario: Spay Reseller register successfully for United States then Reject
    Then verify status New of Spay Reseller account
    When submit KYC form to register for Spay Reseller
#    When select "I want ShopBase Marketplace Payments" and submit KYC form to register for Spay Reseller
      | Country       | Legal business name | Employer Identification Number | Address                     | City        | ZIP   | State      | Business registration | First name | Last name | Job title | Email                       | ID number | ID front | ID back | Picture with ID | Product details | Dispute history |
      | United States | Clothes             | 1234                           | 10800 W Pico Blvd Suite 312 | Los Angeles | 90001 | California | yes                   | Shopbase   | Auto      | CEO       | spayReseller@mailtothis.com | 1234      | yes      | yes     | yes             | Clothes         | Yes             |
    Then verify status Under Review of Spay Reseller account
    Given login to hive sbase
    Then Reject Spay Reseller account
    And redirect to shopbase dashboard
    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then verify status Rejected of Spay Reseller account