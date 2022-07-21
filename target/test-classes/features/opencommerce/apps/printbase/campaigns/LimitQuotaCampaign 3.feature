Feature: Limit quota campaign when create/duplicate campaign/bulk duplicate 1 campaign
## pbase_limit_quota_campaign
  #set quota campaign: 3
  Scenario: Delete all product live
    When delete all campaigns by API


  Scenario Outline: Create new campaign, verify quota campaign #SB_PRB_QPC_47 #SB_PRB_QPC_48
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


  Scenario Outline: Duplicate campaign, verify quota campaign #SB_PRB_QPC_49 #SB_PRB_QPC_50
    Given user login to shopbase dashboard by API
    And get quota campaign by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "Campaign origin"
    And duplicate campaign editor
      | Campaign origin | Is keep artwork |
      | Campaign origin | true            |
    And verify layer and base products editor as "<KEY>"
      | KEY | Product     | Layer              |
      | 01  | Unisex Tank | Text layer 1>Front |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title        |
      | Duplicate 01 |
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

  Scenario: Check create campaign when shop reach limit quota campaign #SB_PRB_QPC_65 #SB_PRB_QPC_101
    Given  user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And get quota campaign by API
    And click to button "Create new campaign"
    And verify message when reach limit quota campaign
    And close browser


  Scenario: Check duplicate campaign when shop reach limit quota campaign #SB_PRB_QPC_65 #SB_PRB_QPC_102
    Given  user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And get quota campaign by API
    And search campaign in dashboard with name "Campaign origin"
    And click icon Duplicate of campaign "Campaign origin"
    And verify message when reach limit quota campaign
    And close browser
  


  Scenario: Check Bulk duplicate when reach quota campaign #SB_PRB_QPC_66 #SB_PRB_QPC_103 #SB_PRB_QPC_104
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And get quota campaign by API
    And search campaign in dashboard with name "Campaign origin"
    And click icon Bulk duplicate of campaign "Campaign origin"
    And verify message when reach limit quota campaign
    And quit browser

