Feature: Limit quota campaign when create/duplicate campaign/bulk duplicate 1 campaign printhub
## sbase_limit_quota_campaign
  #set quota product: 3
  Scenario: Delete all product live
    When delete all campaigns by API


  Scenario Outline: Create new campaign editor with base 2D, verify quota campaign #SB_PRB_QPC_70 #SB_PRB_QPC_71
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And get quota campaign by API
    And get quota product by API
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
    And verify quota campaign for Sbase by API after launch "1" campaign
    And quit browser
    Examples:
      | KEY |
      | 01  |


  Scenario Outline: Duplicate campaign #SB_PRB_QPC_72 #SB_PRB_QPC_73
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And get quota campaign by API
    And get quota product by API
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
    And verify quota campaign for Sbase by API after launch "1" campaign
    And quit browser
    Examples:
      | KEY |
      | 01  |

  Scenario Outline: Bulk duplicate 1 campaign
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And get quota campaign by API
    And get quota product by API
    Then bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name   | Artwork  |
      | 01  | Campaign origin | BD_2.png |
    And verify quota campaign for Sbase by API after launch "1" campaign
    And quit browser
    Examples:
      | KEY |
      | 01  |

  Scenario: Check create campaign when shopbase reach limit quota campaign #SB_PRB_QPC_88 #SB_PRB_QPC_98
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And get quota campaign by API
    And get quota product by API
    And click to button "Create new campaign"
    And verify message when shopbase reach limit quota campaign
    And close browser


  Scenario: Check duplicate campaign when shop reach limit quota campaign #SB_PRB_QPC_88 #SB_PRB_QPC_99
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And get quota campaign by API
    And get quota product by API
    And search campaign in dashboard with name "Campaign origin"
    And click icon Duplicate of campaign "Campaign origin"
    And verify message when shopbase reach limit quota campaign
    And close browser



  Scenario: Check Bulk duplicate when reach quota campaign ##SB_PRB_QPC_79 #SB_PRB_QPC_89 #SB_PRB_QPC_100
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And get quota campaign by API
    And get quota product by API
    And search campaign in dashboard with name "Campaign origin"
    And click icon Bulk duplicate of campaign "Campaign origin"
    And verify message when shopbase reach limit quota campaign
    And quit browser





