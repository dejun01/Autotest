Feature: Limit quota campaign when reach bulk duplicate campaign per hourly
  #pbase_reach_bulkduplicate_limit_quota_campaign
  Scenario: Delete all campaign
    When  delete all campaigns by API

  Scenario Outline: Create new campaign, verify quota campaign
    Given user login to shopbase dashboard by API
    And get quota campaign by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product     | Category |
      | 01  | Unisex Tank | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type |
      | 01  | Unisex Tank | Text       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | Campaign origin |
    And click to button Launch campaign
    And verify quota campaign by API after launch "1" campaign
    And quit browser
    Examples:
      | KEY |
      | 01  |


  Scenario Outline: Bulk duplicate 1 campaign
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And get quota campaign by API
    Then bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name   | Artwork  |
      | 01  | Campaign origin | BD_2.png |
    And verify quota campaign by API after launch "1" campaign
    And quit browser
    Examples:
      | KEY |
      | 01  |


  Scenario: Check Bulk duplicate when reach quota campaign per hourly #SB_PRB_QPC_67
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "Campaign origin"
    And click icon Bulk duplicate of campaign "Campaign origin"
    And verify message when reach limit quota campaign per hourly
    And quit browser
