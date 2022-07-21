Feature: Logo builder in Preference

  #envi: staging_logo_builder

  Background:
    Given user login to shopbase dashboard by API
    And get logo list by api before apply logo

  Scenario Outline: user regenerate logo builder in Preference #SB_OLS_PFR_41 #SB_OLS_PFR_42 #SB_OLS_PFR_43 #SB_OLS_PFR_44 #SB_OLS_PFR_45 #SB_OLS_PFR_46 #SB_OLS_PFR_47 #SB_OLS_PFR_48 #SB_OLS_PFR_49 #SB_OLS_PFR_50 #SB_OLS_PFR_51 #SB_OLS_PFR_52 #SB_OLS_PFR_53 #SB_OLS_PFR_54 #SB_OLS_PFR_55 #SB_OLS_PFR_56
    When user navigate to "Online Store>Preferences" screen
    And user regenerate and add logo to the store in Dashboard
      | Text   | Desktop   | Mobile   | Email   | Checkout page   | Favicon   | Theme   |
      | <Text> | <Desktop> | <Mobile> | <Email> | <Checkout page> | <Favicon> | <Theme> |
    Then get logo list by api after apply logo
    And verify apply logo successfully by api
      | Desktop   | Mobile   | Email   | Checkout page   | Favicon   |
      | <Desktop> | <Mobile> | <Email> | <Checkout page> | <Favicon> |
    And get logo list after apply on storefront
    And verify apply logo successfully on storefront
    Examples:
      | Text       | Desktop | Mobile | Email | Checkout page | Favicon | Theme         |
      | Abc        | true    | true   | true  | true          | true    | All themes    |
      | Store name | true    | false  | false | false         | false   | Current theme |

  Scenario Outline: user regenerate logo builder in Theme editor #SB_OLS_PFR_57 #SB_OLS_PFR_58 #SB_OLS_PFR_59 #SB_OLS_PFR_60 #SB_OLS_PFR_61 #SB_OLS_PFR_62 #SB_OLS_PFR_63 #SB_OLS_PFR_64
    When user navigate to "Online Store" screen
    And user regenerate and add logo to the store in Theme editor
      | Text   | Desktop   | Mobile   | Email   | Checkout page   | Favicon   | All themes   |
      | <Text> | <Desktop> | <Mobile> | <Email> | <Checkout page> | <Favicon> | <All themes> |
    Then get logo list by api after apply logo
    And verify apply logo successfully by api
      | Desktop   | Mobile   | Email   | Checkout page   | Favicon   |
      | <Desktop> | <Mobile> | <Email> | <Checkout page> | <Favicon> |
    And get logo list after apply on storefront
    And verify apply logo successfully on storefront
    Examples:
      | Text       | Desktop | Mobile | Email | Checkout page | Favicon | All themes |
      | Abc        | true    | true   | true  | true          | true    | true       |
      | Store name | true    | true   | true  | true          | true    | false      |
