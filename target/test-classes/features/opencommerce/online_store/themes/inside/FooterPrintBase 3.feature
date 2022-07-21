Feature: HomePage - Footer PrintBase Inside Theme
#  environment=staging_pbase_inside_theme
  #prod_pbase_inside_theme

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme

  Scenario Outline: Store information footer #SB_OLS_THE_INS_33 #SB_OLS_THE_INS_34 #SB_OLS_THE_INS_35 #SB_OLS_THE_INS_37 #SB_OLS_THE_INS_36
    Then change footer settings "<KEY>"
      | KEY | Copyright text | Show payment method icons | Type              | Content                         | Heading      | Desktop logo    | Size   | Show social media footer icons | Hide content heading | Shop type |
      | 1   |                | true                      | Store information | Nguyễn Trãi, Thanh Xuân, Hà Nội | CONTACT INFO | front/logo2.jpg | Small  | true                           | false                | PrintBase |
      | 2   |                | false                     | Store information |                                 |              | front/logo3.png | Medium | false                          | false                | Printbase |
      | 3   |                | true                      | Store information | Ha Noi                          | CONTACT INFO | front/logo2.jpg | Large  | true                           | true                 | Printbase |
    Then open shop on storefront
    And verify footer on store front "<KEY>"
      | KEY | Copyright text | Show payment method icons | Type              | Content                         | Heading      | Desktop logo    | Size      | Show social media footer icons | Hide content heading | Shop type |
      | 1   |                | true                      | Store information | Nguyễn Trãi, Thanh Xuân, Hà Nội | CONTACT INFO | front/logo2.jpg | is-small  | true                           | true                 | PrintBase |
      | 2   |                | false                     | Store information |                                 |              | front/logo3.png | is-medium | false                          | true                 | Printbase |
      | 3   |                | true                      | Store information | Ha Noi                          | CONTACT INFO | front/logo2.jpg | is-large  | true                           | false                | Printbase |
    Then close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
