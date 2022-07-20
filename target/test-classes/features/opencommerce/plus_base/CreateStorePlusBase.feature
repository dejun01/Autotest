Feature: create store
# plbase_create_store

  Scenario: verify create store from ShopBase landing
    Given login to plusbase
    And create a shop with name "@hienplusbase99@"
    Then User verify introduction page and click button "Start now"
    And User add your contact
      | Country | Personnal location | Contact    | Socical profile |
      | Vietnam | Vietnam            | 0983240334 | shopbase.com    |
    And User choose your business type is "Dropshipping" and store type is "PlusBase"
    And User chooses plbase product category
    And User customize store with primary color is "<Primary color>" and font is "<Font>"
    Then Verify display "Welcome to PlusBase" logo

  Scenario: verify create store from PlBase landing
    Given login to plusbase with url
    And create a plusbase shop with name "@hienplusbase@"
    Then User verify introduction page and click button "Start now"
    And User add your contact
      | Country | Personnal location | Contact    | Socical profile |
      | Vietnam | Vietnam            | 0983240334 | shopbase.com    |
    And User chooses plbase product category
    And User customize store with primary color is "<Primary color>" and font is "<Font>"
    Then Verify display "Welcome to PlusBase" logo







