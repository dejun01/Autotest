Feature: As a merchant, I want to bulk Duplicate Campaign 2D for my store
#shop: au-ph-bulk-duplicate-campaign2D.stag.myshopbase.net
  #acc: shopbase@beeketing.net, pass: 123456
  #environment: print_hub_bulk_duplicate_campaigns2D

  Scenario: Delete all campaign
    When delete all campaigns by API
  Scenario Outline: AU_CCP_4.1: Push product to store
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And click to button "Create new campaign"
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | Ladies T-shirt | Apparel  |
      | 2   | Ladies T-shirt | Apparel  |
      | 3   | Ladies T-shirt | Apparel  |
    And input data to create campaign as "<KEY>"
      | KEY | Product         | Category  | Is add more product | Is remove product | Artwork             | Design              | Sizes   | Preview |
      | 1   | Ladies T-shirt  | Apparel   | false               |                   | Campaign1.png>front | Center horizontally | M,L,3XL | true    |
      | 1   | Long Sleeve Tee | Apparel   | true                |                   |                     |                     | S,L,XL  | false   |
      | 2   | Ladies T-shirt  | Apparel   | false               |                   | Campaign1.png>back  |                     |         |         |
      | 3   | Ladies T-shirt  | Apparel   | false               |                   | Campaign1.png>back  |                     |         |         |
      | 3   | Beverage Mug    | Drinkware | true                |                   | Campaign1.png       |                     |         |         |
    And click to tab "Description" in Campaign
    And input data to create description for campaign as "<KEY>"
      | KEY | Title                    | Description | Is include product details | Tags |
      | 1   | Ladies - Long Sleeve Tee | 2D          | true                       |      |
      | 2   | Product 2D back          | 2D 1 side   | true                       |      |
      | 3   | Product 2D back and Mug  | 2D          | true                       |      |
    And click to tab "Pricing" in Campaign
    And input product price for campaign as "<KEY>"
      | KEY | Product        | Variant     | Sale price | Compare at price |
      | 1   | Ladies T-shirt | White - 3XL | 30         | 40               |
      | 1   | Ladies T-shirt | White - M   | 30.4       | 40               |
    And click to button "Launch"
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword                  |
      | 1   | Ladies - Long Sleeve Tee |
      | 2   | Product 2D back          |
      | 3   | Product 2D back and Mug  |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name            | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Ladies - Long Sleeve Tee | LIVE   | true                | true                     |
      | 2   | Product 2D back          | LIVE   | true                | false                    |
      | 3   | Product 2D back and Mug  | LIVE   | true                | false                    |
    And quit browser
    Examples:
      | KEY | Testcase                                                             |
      | 1   | Create multiple product 2D                                           |
      | 2   | Dont bulk duplicate with 2D campaign only font                       |
      | 3   | Dont bulk duplicate with campaign created 2D campaign 1 font and mug |


  Scenario Outline: AU_CP_4.1_ Bulk duplicate campaign
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And close live chat
    And implement action with campaign as "<KEY>"
      | KEY | Action name                 | Campaign name            |
      | 2   | Make 1 campaign unavailable | Ladies - Long Sleeve Tee |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name  | Campaign name            |
      | 2   | Unavailable | Ladies - Long Sleeve Tee |
    And bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name            | Artwork       |
      | 1   | Ladies - Long Sleeve Tee | Campaign1.png |
      | 2   | Ladies - Long Sleeve Tee | BD_5.png      |
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword   |
      | 1   | Campaign1 |
      | 2   | BD_5      |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name | Status      | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Campaign1     | LIVE        | true                | true                     |
      | 2   | BD_5          | UNAVAILABLE | true                | true                     |
    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY | Campaign name |
      | 1   | Campaign1     |
      | 2   | BD_5          |
    And verify product information in dashboard as "<KEY>"
      | KEY | SKU                         | Is enable duplicate | Is enable bulk duplicate | Description |
      | 1   | PH-AP-LadiesT-shirt-White-M | true                | true                     | 2D          |
      | 2   | PH-AP-LadiesT-shirt-White-M | true                | true                     | 2D          |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 1   |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name | Size | Color | Style          | Sale price | Compare at price |
      | 1   | Campaign1    | M    | White | Ladies T-shirt | 30.4       | 40               |
    And quit browser
    Examples:
      | KEY | Testcase                               |
      | 1   | Bulk duplicate 2D campaign available   |
      | 2   | Bulk duplicate 2D campaign unavailable |


  Scenario Outline: Bulk duplicate and edit detail this campaign
    When delete all campaigns by API
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | Ladies T-shirt | Apparel  |
    And input data to create campaign as "<KEY>"
      | KEY | Product        | Category | Is add more product | Artwork             | Design              | Sizes   | Preview |
      | 1   | Ladies T-shirt | Apparel  | false               | Campaign1.png>front | Center horizontally | M,L,3XL | true    |
    And click to tab "Description" in Campaign
    And input data to create description for campaign as "<KEY>"
      | KEY | Title      | Description | Is include product details | Tags |
      | 1   | Campaign 1 | 2D          | true                       |      |
    And click to tab "Pricing" in Campaign
    And click to button "Launch"
    And bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name | Artwork  |
      | 1   | Campaign 1    | 3D_4.png |
    And implement action with campaign as "<KEY>"
      | KEY | Action name                 | Campaign name |
      | 1   | Make 1 campaign unavailable | 3D_4          |
    And search campaign in dashboard with name "3D_4"
    Then Edit detail campaign on dashboard
      | Tags remove | Description    | Product type | Collections | is check box | Variant                | Action          |
      | t-shirt     | Unisex T-shirt | shirt        | Collect 200 | true         | Ladies T-shirt,White,L | Delete variants |
    And user navigate to "All Campaigns" screen
    And bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name | Artwork  |
      | 1   | 3D_4          | 3D_3.png |
    And Verify campaign "3D_3" have status "Unavailable"
    Then Verify detail campaign after duplicate
      | Tags remove | Description    | Product type | Collections | is check box | Variant                |
      | t-shirt     | Unisex T-shirt | shirt        | Collect 200 | false        | Ladies T-shirt,White,L |
    Examples:
      | KEY | Testcase                   |
      | 1   | Create multiple product 2D |