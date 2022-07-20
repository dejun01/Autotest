Feature: Ignore OTP when add the first payment accounts
#sbase_ignore_otp

  Background:
    Given login to hive sbase
    And clear shop data as "shopId"
    Given user login to shopbase dashboard

  Scenario Outline: Ignore OTP when add the first payment accounts is Shopbase payment #SB_AU_OTP_IO_2
    Then user add your contact "<KEY>"
      | KEY | Store country | Personal location | Contact    | Social profile             |
      | 1   | Vietnam       | Vietnam           | 0966985236 | https://www.google.com.vn/ |
    And  User choose your business type is "<Business type>" and store type is "<Store type>"
    And user choose your product category "<KEY>"
      | KEY | Product category                     |
      | 1   | No thanks, I will decide later       |
    Then select store to clone "<KEY>"
      | KEY | isShow | Selected store | Action                            |
      | 1   | true   |                | No thanks, I don't want to import |
    And customized onboarding questions "<KEY>"
      | KEY | Country   | Store type       |
      | 1   | Viet nam  | General Dropship |
    When update end free trial of shop by API

    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    And Enable Spay Reseller account
    Then verify not show popup require OTP

    When activate "PayPal-live" gateway successfully in "Activate" mode
      | Client ID                                                                        | Client Secret Key                                                                |
      | AZhCTERpp9WhL7mOHY6RgUeTDuB-Vpjx9L5p2jJ5Y0j27jGGHK7RkGzbxP2FxjqmADhNu0wyrQRw5Gru | EBHwdCBDS2p-whUapwo0U6IyQ6QLsBtqFzKgSi-q_KjUCz8OJQwwxwOMMdn10Qpqzf0dZyEggDrZ-87E |
    Then verify not show popup require OTP

    When activate "Checkout.com-live" gateway successfully in "Activate" mode
      | Public Key                              | Private Key                             |
      | pk_702887b5-4975-46d4-8d0d-33d0638fe31c | sk_98f66a3f-a037-4265-b274-1924ec5844e3 |

    When Deactivate Spay Reseller account
    Then verify not show popup require OTP

    Then remove gateway account "PayPal" by account name ""
    Then verify not show popup require OTP

    Examples:
      | KEY | Business type   | Store type                                          |
      | 1   | Dropshipping    | I want a ShopBase store>I want to focus on a niche. |


  Scenario Outline: Ignore OTP when add the first payment accounts is PayPal #SB_AU_OTP_IO_4
    Then user add your contact "<KEY>"
      | KEY | Store country | Personal location | Contact    | Social profile             |
      | 1   | Vietnam       | Vietnam           | 0966985236 | https://www.google.com.vn/ |
    And  User choose your business type is "<Business type>" and store type is "<Store type>"
    And user choose your product category "<KEY>"
      | KEY | Product category                     |
      | 1   | No thanks, I will decide later       |
    Then select store to clone "<KEY>"
      | KEY | isShow | Selected store | Action                            |
      | 1   | true   |                | No thanks, I don't want to import |
    And customized onboarding questions "<KEY>"
      | KEY | Country   | Store type       |
      | 1   | Viet nam  | General Dropship |
    When update end free trial of shop by API

    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    When activate "PayPal-live" gateway successfully in "Activate" mode
      | Client ID                                                                        | Client Secret Key                                                                |
      | AZhCTERpp9WhL7mOHY6RgUeTDuB-Vpjx9L5p2jJ5Y0j27jGGHK7RkGzbxP2FxjqmADhNu0wyrQRw5Gru | EBHwdCBDS2p-whUapwo0U6IyQ6QLsBtqFzKgSi-q_KjUCz8OJQwwxwOMMdn10Qpqzf0dZyEggDrZ-87E |
    Then verify not show popup require OTP

    Then deactivate gateway "PayPal" by account name ""
    Then verify not show popup require OTP

    Then activate "PayPal-live" gateway successfully in "Reactivate" mode
      | Client ID                                                                        | Client Secret Key                                                                |
      | AZhCTERpp9WhL7mOHY6RgUeTDuB-Vpjx9L5p2jJ5Y0j27jGGHK7RkGzbxP2FxjqmADhNu0wyrQRw5Gru | EBHwdCBDS2p-whUapwo0U6IyQ6QLsBtqFzKgSi-q_KjUCz8OJQwwxwOMMdn10Qpqzf0dZyEggDrZ-87E |

    And Enable Spay Reseller account
    Then verify not show popup require OTP

    When activate "Brain-live" gateway successfully in "Activate" mode
      | Merchant ID      | Public Key       | Private Key                      |
      | kcrkxyvbxcrq8gsp | f32yq28z73y3tp25 | 2ec426b5bf837f50dcd1a0c3904b3681 |

    Examples:
      | KEY | Business type   | Store type                                          |
      | 1   | Dropshipping    | I want a ShopBase store>I want to focus on a niche. |

  Scenario Outline: Ignore OTP when add the first payment accounts is thrid-party gateway #SB_AU_OTP_IO_3
    Then user add your contact "<KEY>"
      | KEY | Store country | Personal location | Contact    | Social profile             |
      | 1   | Vietnam       | Vietnam           | 0966985236 | https://www.google.com.vn/ |
    And  User choose your business type is "<Business type>" and store type is "<Store type>"
    And user choose your product category "<KEY>"
      | KEY | Product category                     |
      | 1   | No thanks, I will decide later       |
    Then select store to clone "<KEY>"
      | KEY | isShow | Selected store | Action                            |
      | 1   | true   |                | No thanks, I don't want to import |
    And customized onboarding questions "<KEY>"
      | KEY | Country   | Store type       |
      | 1   | Viet nam  | General Dropship |
    When update end free trial of shop by API

    And user navigate to "Settings" screen
    And Click to "Payment providers" section at Settings screen
    Then activate "Unlimint-live" gateway successfully in "Activate" mode
      | Terminal Code | Terminal Password | Callback Secret |
      | 69601         | g4jPK9M0Tm8d      | 4SFAr9Ru01mb    |
    Then verify not show popup require OTP

    Then activate "Unlimint" gateway successfully in "Edit" mode
      | Terminal Code | Terminal Password | Callback Secret |
      | 69601         | g4jPK9M0Tm8d      | 4SFAr9Ru01mb    |
    Then verify not show popup require OTP

    Then deactivate gateway "Unlimint" by account name ""
    Then verify not show popup require OTP

    Then activate "Unlimint" gateway successfully in "Reactivate" mode
      | Terminal Code | Terminal Password | Callback Secret |
      | 23921         | Y9D5Ro7pgbI8      | 64kzmQR0c9IG    |
    Then verify not show popup require OTP

    Then remove gateway account "Unlimint" by account name ""
    Then verify not show popup require OTP

    When activate "PayPal-live" gateway successfully in "Activate" mode
      | Client ID                                                                        | Client Secret Key                                                                |
      | AZhCTERpp9WhL7mOHY6RgUeTDuB-Vpjx9L5p2jJ5Y0j27jGGHK7RkGzbxP2FxjqmADhNu0wyrQRw5Gru | EBHwdCBDS2p-whUapwo0U6IyQ6QLsBtqFzKgSi-q_KjUCz8OJQwwxwOMMdn10Qpqzf0dZyEggDrZ-87E |
    Then verify not show popup require OTP

    Examples:
      | KEY | Business type   | Store type                                          |
      | 1   | Dropshipping    | I want a ShopBase store>I want to focus on a niche. |