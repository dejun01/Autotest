Feature: Disable SBFF apps in PlusBase store

  #env: disable_sbff_apps

  Scenario Outline: Disable SBFF apps in PlusBase store
    Given user login to plusbase dashboard
    Then user navigate to "Apps" screen
    And Verify disable app in Plusbase store as "<KEY>"
    |KEY |App                      | Noti|
    |1   | Ali Dropship connector  |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    |2   | CrossPanda              |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    |3   | PrintHub                |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    |4   | Migrate to ShopBase     |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    |5   | Import product via CSV  |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    |6   | Product personalization |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    |7   | Payment rotation        |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    |8   | Third-party apps        |This app cannot be installed because it is incompatible with your store. Please refer to our policy to see more details.|
    And quit browser
    Examples:<KEY>
    |KEY|
    | 1  |
    | 2  |
    | 3  |
    | 4  |
    | 5  |
    | 6  |
    | 7  |
    | 8  |
