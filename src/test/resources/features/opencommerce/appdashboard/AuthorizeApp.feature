#  sbase_applist
Feature: AuthorizeApp

  Background: Login dashboard and navigate to Apps screen
    Given user login to shopbase dashboard

  Scenario: Install app
    And user navigate to "Apps" screen
    And Delete "Migrate To ShopBase" app
    And Verify app "Migrate To ShopBase" installed is "false"
    When Install selected app "Migrate To ShopBase"
    Then Verify app "Migrate To ShopBase" installed is "true"