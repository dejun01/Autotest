Feature: Homepage - PrintBase Footer

  # prod_pbase_roller_theme
# staging_pbase_roller_theme
#  dev_pbase_roller_theme

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme

  Scenario Outline: Store information footer PrintBase #SB_OLS_THE_ROL_24 #SB_OLS_THE_ROL_FOOT_LOGO_27 #SB_OLS_THE_ROL_FOOT_LOGO_26 #SB_OLS_THE_ROL_FOOT_LOGO_25 #SB_OLS_THE_ROL_FOOT_LOGO_23 #SB_OLS_THE_ROL_FOOT_LOGO_22 #SB_OLS_THE_ROL_FOOT_LOGO_16
    Then change footer settings "<KEY>"
      | KEY | Copyright text | Show payment method icons | Show social media footer icons | Type              | Content                         | Shop type | Desktop logo    | Mobile logo     | Size   |
      | 1   |                | true                      | false                          | Store information | Nguyễn Trãi, Thanh Xuân, Hà Nội | PrintBase | front/logo3.png | front/logo2.jpg | Medium |
      | 2   |                | false                     | true                           | Store information |                                 | Printbase | front/logo2.jpg | front/logo3.png | Large  |
      | 3   |                | true                      | true                           | Store information |                                 | Printbase | front/logo2.jpg | front/logo3.png | Small  |
    Then open shop on storefront
    And verify footer on store front "<KEY>"
      | KEY | Copyright text | Show payment method icons | Show social media footer icons | Type              | Content                         | Shop type | Desktop logo    | Mobile logo     | Size      |
      | 1   |                | true                      | false                          | Store information | Nguyễn Trãi, Thanh Xuân, Hà Nội | Printbase | front/logo3.png | front/logo2.jpg | is-medium |
      | 2   |                | false                     | true                           | Store information |                                 | Printbase | front/logo2.jpg | front/logo3.png | is-large  |
      | 3   |                | true                      | true                           | Store information |                                 | PrintBase | front/logo2.jpg | front/logo3.png | is-small  |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |