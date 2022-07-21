Feature: Manage Campaign
#  pbase_manage_campaign
  Scenario: Delete all campaign
    When delete all campaigns by API

  Scenario Outline: Push product to store
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | Unisex Tank    | Apparel  |
      | 2   | Unisex T-shirt | Apparel  |
      | 3   | Ladies T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Front or back |
      | 1   | Unisex Tank    | Text       | Front         |
      | 2   | Unisex T-shirt | Text       | Front         |
      | 3   | Ladies T-shirt | Text       | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    Examples:
      | KEY | Testcase          | Campaign name     |
      | 1   | Create campaign 1 | Unisex Tank       |
      | 2   | Create campaign 2 | Unisex T-shirt    |
      | 3   | Create campaign 3 | Ladies T-shirt    |



  Scenario Outline: SB_PRB_MC_2 Check campaign unavailable #SB_PRB_MC_2 #SB_PRB_MC_1
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "<Campaign name>"
    And get link handle url of product on dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And implement action with campaign as "<KEY>"
      | KEY | Action name                 | Campaign name   |
      | 1   | Make 1 campaign unavailable | <Campaign name> |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name  | Campaign name   |
      | 1   | Unavailable | <Campaign name> |
    And open and verify products "<Campaign name>" not exist on Storefront
    And quit browser
    Examples:
      | KEY | Testcase                                 | Campaign name   |
      | 1   | change campaign available==> unavailable | Unisex Tank     |


  Scenario Outline: SB_PRB_MC_3 Check campaign available #SB_PRB_MC_3
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "<Campaign name>"
    And get link handle url of product on dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And implement action with campaign as "<KEY>"
      | KEY | Action name               | Campaign name   |
      | 1   | Make 1 campaign available | <Campaign name> |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name | Campaign name   |
      | 1   | Available  | <Campaign name> |
    And open and verify products "<Campaign name>" exist on Storefront
    And quit browser
    Examples:
      | KEY | Testcase                                 | Campaign name   |
      | 1   | change campaign unavailable==> available | Unisex Tank     |

  Scenario Outline: SB_PRB_MC_6 Check campaign delete by action #SB_PRB_MC_6
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "<Campaign name>"
    And get link handle url of product on dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And implement action with campaign as "<KEY>"
      | KEY | Action name               | Campaign name   |
      | 1   | Delete selected campaigns | <Campaign name> |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name | Campaign name   |
      | 1   | Delete     | <Campaign name> |
    And search campaign "<Campaign name>" in dashboard
    And verify product not exist in dashboard
    And open and verify products "<Campaign name>" not exist on Storefront
    And quit browser
    Examples:
      | KEY | Testcase                  | Campaign name   |
      | 1   | delete campaign by action | Unisex Tank     |


    Scenario Outline: Check campaign delete by button delete in campaign detail
      Given Description: "<Testcase>"
      Given user login to shopbase dashboard by API
      And user navigate to "Campaigns>All campaigns" screen
      And search campaign in dashboard with name "<Campaign name>"
      And get link handle url of product on dashboard
      And user navigate to "Campaigns>All campaigns" screen
      And search campaign in dashboard with name "<Campaign name>"
      And click to button "Delete campaign"
      And confirm delete campaign
      And search campaign "<Campaign name>" in dashboard
      And verify product not exist in dashboard
      And open and verify products "<Campaign name>" not exist on Storefront
      And quit browser
      Examples:
        | Testcase                           | Campaign name      |
        | delete campaign in campaign detail | Unisex T-shirt     |


    Scenario Outline: Check delete campaign by API
      Given Description: "<Testcase>"
      Given user login to shopbase dashboard by API
      And user navigate to "Campaigns>All campaigns" screen
      And search campaign in dashboard with name "<Campaign name>"
      And get link handle url of product on dashboard
      And user navigate to "Campaigns>All campaigns" screen
      And search campaign "<Campaign name>" in dashboard
      And delete campaign "<Campaign name>" by API
      And search campaign "<Campaign name>" in dashboard
      And verify product not exist in dashboard
      And open and verify products "<Campaign name>" not exist on Storefront
      And quit browser
      Examples:
        | Testcase                           | Campaign name      |
        | delete campaign by API             | Ladies T-shirt     |






      