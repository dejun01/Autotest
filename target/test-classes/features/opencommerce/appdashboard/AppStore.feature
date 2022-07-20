Feature: AppStore
#  sbase_applist
  Background: open app store when not login
    Given open app store page

  Scenario: Verify action search app #SB_SF_ANAA_7
    When search app and verify result
      | Value search        | Result   |
      | tester              | No data  |
      | Smartarget FAQ      | Has data |
      | Migrate To ShopBase | Has data |
    When select appname "Migrate To ShopBase"
