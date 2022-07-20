Feature: As a merchant, I want to duplicate campaign live for my store
  #shop: au-ph-duplicate-campaign.stag.myshopbase.net
  #acc: shopbase@beeketing.net, pass: 123456
  #environment: print_hub_duplicate_campaign

  Scenario: Delete all campaign
    When delete all campaigns by API

  Scenario Outline: Create campaign to implement duplicate campaign
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    When add products to campaign as "<KEY>"
      | KEY | Product       | Category      |
      | 1   | Unisex Hoodie | Apparel       |
      | 1   | Quilt         | Home & Living |
    And input data to create campaign as "<KEY>"
      | KEY | Product       | Category      | Artwork       | Design                           | Sizes | Colors      | Preview |
      | 1   | Unisex Hoodie | Apparel       | Campaign1.png | Center horizontally,Scale to fit | S,M   | White,Black | false   |
      | 1   | Quilt         | Home & Living | Bulk1.jpg     |                                  |       |             | true    |
    And click to tab "Description" in Campaign
    And input data to create description for campaign as "<KEY>"
      | KEY | Title            | Description      | Is include product details | Tags |
      | 1   | Multiple product | Multiple product | false                      |      |
    And click to tab "Pricing" in Campaign
    And input product price for campaign as "<KEY>"
      | KEY | Product | Variant                     | Sale price | Compare at price |
      | 1   | Quilt   | All over print - Queen      | 50         | 60               |
      | 1   | Quilt   | All over print - Super King | 50         | 60               |
    And click to button "Launch"
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword          |
      | 1   | Multiple product |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name    | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Multiple product | LIVE   | true                | false                    |
    And close browser
    Examples:
      | KEY |
      | 1   |


  Scenario Outline: AU_CP_2.1: Duplicate and edit this campaign of Print Base
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword          |
      | 1   | Multiple product |
    And implement action with campaign as "<KEY>"
      | KEY | Action name                 | Campaign name    |
      | 2   | Make 1 campaign unavailable | Multiple product |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name  | Campaign name    |
      | 2   | Unavailable | Multiple product |
    And duplicate campaign Phub as "<KEY>"
      | KEY | Campaign name    | Provide a name for your new campaign |
      | 1   | Multiple product | Campaign available                   |
      | 2   | Multiple product | Campaign unavailable                 |
    And input data to create campaign as "<KEY>"
      | KEY | Product       | Artwork             | Sizes selected | Colors selected | Preview |
      | 1   | Unisex Hoodie | Campaign1.png>front | S,M            | White,Black     | false   |
      | 1   | Quilt         | Bulk1.jpg           |                |                 | true    |
      | 2   | Unisex Hoodie | Campaign1.png>front | S,M            | White,Black     | false   |
      | 2   | Quilt         | Bulk1.jpg           |                |                 | true    |
    And click to tab "Pricing" in Campaign
    And click to button "Launch"
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword              |
      | 1   | Campaign available   |
      | 2   | Campaign unavailable |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name        | Status      | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Campaign available   | LIVE        | true                | false                    |
      | 2   | Campaign unavailable | UNAVAILABLE | true                | false                    |
    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY | Campaign name      |
      | 1   | Campaign available |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 1   |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name       | Size | Color | Style         | Sale price | Compare at price |
      | 1   | Campaign available | S    | White | Unisex Hoodie | 31.99      | 46.99            |
      | 1   | Campaign available | S    | Black | Unisex Hoodie | 32.49      | 47.49            |
    And quit browser
    Examples: <KEY>
      | KEY | Testcase                       |
      | 1   | Duplicate campaign available   |
      | 2   | Duplicate campaign unavailable |


  Scenario: Duplicate and edit detail this campaign #SB_PRO_IPST_13
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And search campaign in dashboard with name "Multiple product"
    And Open detail product of product "Multiple product"
    Then Edit Search engine listing preview with data
      | Page title                                                                                                         | Meta description                                                                                                                                                                                                                                                                                                                                           |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. |
    And duplicate campaign Phub
      | KEY | Campaign name    | Provide a name for your new campaign |
      | 1   | Multiple product | Duplicate campaign 1                 |
    And Edit detail campaign
      | Product       | Artwork             | Size add | Color remove |
      | Unisex Hoodie | Campaign1.png>front | L,XL     | Black        |
      | Quilt         | Bulk1.jpg           |          |              |
    And click to tab "Pricing" in Campaign
    And click to button "Launch"
    And search campaign in dashboard with name "Duplicate campaign 1"
    Then Edit detail campaign on dashboard
      | Product type | Collections | is check box |
      | shirt        | Collect 200 | true         |
    And duplicate campaign Phub
      | Campaign name        | Provide a name for your new campaign |
      | Duplicate campaign 1 | Duplicate from campaign 1            |
    And Edit detail campaign
      | Product       | Artwork             |
      | Unisex Hoodie | Campaign1.png>front |
      | Quilt         | Bulk1.jpg           |
    And click to tab "Pricing" in Campaign
    And click to button "Launch"
    And Verify campaign "Duplicate from campaign 1" have status "Unavailable"
    Then Verify detail campaign after duplicate
      | Product type | Collections | is check box |
      | shirt        | Collect 200 | false        |
    Then Verify data of Search engine listing preview
      | Page title                                                                                                         | Length page title            | Meta description                                                                                                                                                                                                                                                                                                                                           | Length meta description       |
      | Title tags in SEO are like the title of your book for the demographics of your two most important types of readers | 114/ 70 suggested characters | This meta description has an appropriate length and communicates the content of the page in a succinct and comprehensible way. It also makes users curious about the page and thus motivates them to click on this result on Google. The meta description of a web page is shown in the SERP of search engines and contains an overview of a page content. | 346/ 320 suggested characters |