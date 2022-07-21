Feature: verify AppStore

  #sbase_apps_store
  Scenario: verify Home Appstore display
    Given open "Home" in App store
    And verify list Collection in sidebar menu
    And verify list category in sidebar menu
    When verify list collections display in home appstore
    And verify apps of each collection
    Then verify list categories display in home appstore


  Scenario: seach appp in app store
    Given open "Home" in App store
    When search app in App store
      | App name       | Result |
      | Boost Convert  | Match  |
      | afjhge rgerhbc | null   |

  Scenario Outline: verify App detail page
    Given open "Home" in App store
    And search app in App store
      | App name   | Result   |
      | <App name> | <Result> |
    When verify App's information of "<App name>"
    Examples:
      | App name      | Result |
      | CrossPanda | Match  |


  Scenario: verify Collection detail page
    Given open "Home" in App store
    When click see all of collection is "Made by ShopBase" in Homepage
    And verify "Collection"'s information is "Made by ShopBase" in listing page
    Then click category is "Fulfillment Management" in sidebar menu
    And verify "Category"'s information is "Fulfillment Management" in listing page


