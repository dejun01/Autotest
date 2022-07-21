Feature: Affiliate Request PrintBase

  #sbase_affiliate_request

  Scenario: Check send request and approve request #SB_PN_AR_6 #SB_PN_AR_1 #SB_PN_AR_2 #SB_PN_AR_3 #SB_PN_AR_4 #SB_PN_AR_5  #SB_PN_AR_8  #SB_PN_AR_9
    Given user sign up account partner
      | Email | Password |
      | test+ | 123456   |
    When switch to "Affiliate" tab in menu
    Then verify display Block PrintBase Refer Friends
    Then user send a request
      | Your name | Phone      | What platform are you doing POD business on? | How long have you been in POD business? | What is your daily order volume? | Messages                   |
      |           | 0331112222 | Other                                        | 1-3 years                               | 0-50                             | Can't be blank             |
      | Lisa      |            | PrintBase                                    | 3-6 months                              | 51-100                           | Can't be blank             |
      | Lisa      | BlackPink  | Teechip                                      | 1-3 years                               | 101-500                          | Enter a valid phone number |
      | Lisa      | 033##      | Merchize                                     | More than 3 years                       | 500-1000                         | Enter a valid phone number |
      | BlackPink | 0331112222 | Gearlaunch                                   | 1-3 years                               | More than 1000                   | Thank you for submitting   |
    Then login to hive sbase
    And admin approve a request
    And verify display PrintBase Affiliate Program is "true"

  Scenario: Check send request and reject request #SB_PN_AR_10
    Given user sign up account partner
      | Email     | Password |
      | autotest+ | 123456   |
    When switch to "Affiliate" tab in menu
    Then verify display Block PrintBase Refer Friends
    Then user send a request
      | Your name | Phone      | What platform are you doing POD business on? | How long have you been in POD business? | What is your daily order volume? | Messages                 |
      | Lisa      | 0331112222 | Gearlaunch                                   | 1-3 years                               | More than 1000                   | Thank you for submitting |
    Then login to hive sbase
    And admin reject a request
    And verify display PrintBase Affiliate Program is "false"
