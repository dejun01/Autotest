Feature: As a merchant, I want to duplicate campaign draft for my store
  #shop: au-ph-duplicate-campaign-draf.stag.myshopbase.net
  #acc: shopbase@beeketing.net, pass: 123456
  #environment: print_hub_duplicate_campaign_draf

  Scenario: Delete all campaign
    When delete all campaigns by API

  Scenario Outline: Create campaign to implement duplicate campaign
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category |
      | 1   | Ladies T-shirt | Apparel  |
      | 1   | Unisex Hoodie  | Apparel  |
    And input data to create campaign as "<KEY>"
      | KEY | Product        | Category | Artwork             | Design              | Sizes | Preview |
      | 1   | Ladies T-shirt | Apparel  | Campaign1.png>front | Center horizontally | M,L   | true    |
      | 1   | Unisex Hoodie  | Apparel  |                     |                     | S,L   | false   |
    And click to tab "Description" in Campaign
    And input data to create description for campaign as "<KEY>"
      | KEY | Title           | Description | Is include product details | Tags |
      | 1   | Ladies - Hoodie | 2D          | true                       |      |
    And click to tab "Pricing" in Campaign
    And input product price for campaign as "<KEY>"
      | KEY | Product        | Variant   | Sale price | Compare at price |
      | 1   | Ladies T-shirt | White - M | 50         | 60               |
      | 1   | Ladies T-shirt | White - L | 50         | 60               |
    And click to button "<Action>"
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name   | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Ladies - Hoodie | DRAFT  | true                | false                    |
    And close browser
    Examples:
      | KEY | Action     |
      | 1   | Save draft |


  Scenario Outline: AU_CP_2.1: Duplicate and edit this campaign of Print Base
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And duplicate campaign Phub as "<KEY>"
      | KEY | Campaign name   |
      | 1   | Ladies - Hoodie |
    And input data to create campaign as "<KEY>"
      | KEY | Product        | Artwork       | Sizes selected | Preview |
      | 1   | Ladies T-shirt | Campaign1.png | M,L            | true    |
    And click to tab "Pricing" in Campaign
    And click to button "Launch"
#    And search product or campaign or orders at list page in dashboard as "<KEY>"
#      | KEY | Keyword                 |
#      | 1   | Copy of Ladies - Hoodie |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name           | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Copy of Ladies - Hoodie | LIVE   | true                | true                     |
    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY | Campaign name           |
      | 1   | Copy of Ladies - Hoodie |
    And verify product information in dashboard as "<KEY>"
      | KEY | SKU                         | Is enable duplicate | Is enable bulk duplicate | Description |
      | 1   | PH-AP-LadiesT-shirt-White-M | true                | true                     | 2D          |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 1   |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name            | Size | Color | Style          | Sale price | Compare at price |
      | 1   | Copy of Ladies - Hoodie | M    | White | Ladies T-shirt | 50         | 60               |
      | 1   | Copy of Ladies - Hoodie | L    | White | Ladies T-shirt | 50         | 60               |
    And quit browser
    Examples: <KEY>
      | KEY | Product name            | Testcase                 |
      | 1   | Copy of Ladies - Hoodie | Duplicate campaign draft |

