Feature: Disable SBFF apps in Plusbase store

  #env: disable_sbff_apps_plusbase
  Scenario: Disabe SBFF apps in Plusbase store
    Given user login to plusbase dashboard
    Then user navigate to "Apps" screen
    And user go to ShopBase App Store and search app

