Feature: Verify webhook app/uninstalled

  @webhookApp
  Scenario: Install apps
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    Then Delete "Migrate To ShopBase" app
    And Verify app "Migrate To ShopBase" installed is "false"
    And Install selected app "Migrate To ShopBase"
    And Register webhook "app/uninstalled" with App's access token on address "https://webhook-site.bgroupltd.com/7c3d877d-ada3-422f-9850-734da78b2530"

  @webhookApp
  Scenario: Verify webhook app/uninstalled
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    Then Get number of webhook before on address "https://webhook-site.bgroupltd.com/token/7c3d877d-ada3-422f-9850-734da78b2530/requests"
    Then Delete "Migrate To ShopBase" app
    And Verify webhook on address "https://webhook-site.bgroupltd.com/token/7c3d877d-ada3-422f-9850-734da78b2530/requests"


