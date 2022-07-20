Feature: Preferences
# enviroment: sbase_password_page

  Scenario: Remove fb Pixel
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And Remove all fb Pixels
      | Title                            |
      | Facebook Pixel & Conversions API |

  Scenario Outline: Input and validate field Facebook Pixel/Access Token
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store>Preferences" screen
    And Input and validate fb Pixel as "<KEY>"
      | KEY | Conversion ID   | Token                                            | Error msg                | Title                            |
      | 1   |                 | EAAFZC6BUOi4QBACnqBeqhRXCVRAZCmZBjBDiMjHigPqFiZC |                          | Facebook Pixel & Conversions API |
      | 1   | 421054948881435 |                                                  |                          | Facebook Pixel & Conversions API |
      | 1   | 421054948881435 | EAAFZC6BUOi4QBACnqBeqhRXCVRAZCmZBjBDiMjHigPqFiZD |                          | Facebook Pixel & Conversions API |
      | 2   |                 | EAAFZC6BUOi4QBACnqBeqhRXCVRAZCmZBjBDiMjHigPqFiZE | Please fill in Pixel ID. | Facebook Pixel & Conversions API |
      | 2   | 368301857708638 |                                                  |                          | Facebook Pixel & Conversions API |
      | 2   | 368301857708638 | EAAFZC6BUOi4QBACnqBeqhRXCVRAZCmZBjBDiMjHigPqFiZF |                          | Facebook Pixel & Conversions API |
    Examples:
      | KEY |
      | 1   |
      | 2   |

  Scenario Outline: Password page setting #SB_SF_PWP_7 #SB_SF_PWP_6 #SB_SF_PWP_2 #SB_SF_PWP_1
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Preferences" screen
    And setting password protection with "<KEY>"
      | KEY | EnablePassword | InputPassword |
      | 001 | false          | abcd          |
      | 002 | false          | abcd14        |
    And user navigate to "Online Store>Themes" screen
    And verify block password on Theme "<KEY>"
      | KEY | ShowPasswordOnTheme |
      | 001 | false               |
      | 002 | false               |
    When open store from "<KEY>"
      | KEY | OpenStoreFront |
      | 001 | dashboard      |
      | 002 | shop           |
    Then verify and input password"<KEY>"
      | KEY | ShowPasswordOnSF | InputPW |
      | 001 | false            |         |
      | 002 | false            |         |
    Examples:
      | KEY |
      | 001 |
      | 002 |


  Scenario Outline: Verify password page and checkout #SB_SF_PWP_12 #SB_SF_PWP_11 #SB_SF_PWP_8 #SB_SF_PWP_6 #SB_SF_PWP_3
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Preferences" screen
    And setting password protection with "<KEY>"
      | KEY | EnablePassword | InputPassword |
      | 001 | true           | abcd1         |
      | 002 | true           | abcd12        |
    And user navigate to "Online Store>Themes" screen
    And verify block password on Theme "<KEY>"
      | KEY | ShowPasswordOnTheme |
      | 001 | true                |
      | 002 | true                |
    When open store from "<KEY>"
      | KEY | OpenStoreFront |
      | 001 | dashboard      |
      | 002 | shop           |
    Then verify and input password"<KEY>"
      | KEY | ShowPasswordOnSF | InputPW | ShowMessage | Message                               |
      | 001 | false            |         | false       |                                       |
      | 002 | true             |         | true        | Password incorrect, please try again. |
      | 002 | true             | xyzk    | true        | Password incorrect, please try again. |
      | 002 | true             | abcd12  | false       |                                       |
    And add to cart "Test 1"
    And click button checkout
    And checkout by Stripe successfully
    Then quit browser
    Examples:
      | KEY |
      | 001 |
      | 002 |