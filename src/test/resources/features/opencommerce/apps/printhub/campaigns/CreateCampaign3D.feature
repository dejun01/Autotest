Feature: As a merchant, I want to create campaign 3D for my store
  #shop: au-ph-campaign1.stag.myshopbase.net
  #acc: shopbase@beeketing.net, pass: 123456
  #environment: print_hub_campaign_3D

  Scenario: Delete all campaign
    When delete all campaigns by API

  Scenario Outline: AU_CCP_4.1: Push product to store with product 3D
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
#    And click to button "Create new campaign"
    And create campaign in Dashboard
    When add products to campaign as "<KEY>"
      | KEY | Product        | Category       |
      | 1   | Quilt          | Home & Living  |
      | 2   | AOP Zip Hoodie | All Over Print |
    And input data to create campaign as "<KEY>"
      | KEY | Product        | Is add more product | Artwork       | Sizes | Preview |
      | 1   | Quilt          | false               | Bulk1.jpg     |       | true    |
      | 2   | AOP Zip Hoodie | false               | Bulk1.jpg>all | S,M   | true    |
    And click to tab "Description" in Campaign
    And input data to create description for campaign as "<KEY>"
      | KEY | Title          | Description   | Is include product details | Tags       |
      | 1   | Quilt          |               |                            |            |
      | 2   | AOP Zip Hoodie | 3D Zip Hoodie | false                      | zip hoodie |
    And calculate number of variant for campaign as "<KEY>"
      | KEY |
      | 1   |
      | 2   |
    And click to tab "Pricing" in Campaign
    And input product price for campaign as "<KEY>"
      | KEY | Product        | Variant                     | Sale price | Compare at price |
      | 1   | Quilt          | All over print - Queen      | 50         | 60               |
      | 1   | Quilt          | All over print - Super King | 50         | 60               |
      | 2   | AOP Zip Hoodie | All over print - S          | 50         | 60               |
      | 2   | AOP Zip Hoodie | All over print - M          | 50         | 60               |
    And click to button "Launch"
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword        |
      | 1   | Quilt          |
      | 2   | AOP Zip Hoodie |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name  | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Quilt          | LIVE   | true                | true                     |
      | 2   | AOP Zip Hoodie | LIVE   | true                | false                    |
    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY | Campaign name  |
      | 1   | Quilt          |
      | 2   | AOP Zip Hoodie |
    And verify product information in dashboard as "<KEY>"
      | KEY | SKU                                 | Tags       | Is enable duplicate | Is enable bulk duplicate | Description   |
      | 1   | PH-AOP-Quilt-Alloverprint-Queen     |            | true                | true                     |               |
      | 1   | PH-AOP-Quilt-Alloverprint-SuperKing |            | true                | true                     |               |
      | 2   | PH-AOP-AOPZipHoodie-Alloverprint-S  | zip hoodie | true                | false                    | 3D Zip Hoodie |
      | 2   | PH-AOP-AOPZipHoodie-Alloverprint-M  | zip hoodie | true                | false                    | 3D Zip Hoodie |
    And verify total variant push to store as "<KEY>"
      | KEY |
      | 1   |
      | 2   |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 1   |
      | 2   |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name   | Size       | Sale price | Compare at price |
      | 1   | Quilt          | Queen      | 50         | 60               |
      | 1   | Quilt          | Super King | 50         | 60               |
      | 2   | AOP Zip Hoodie | S          | 50         | 60               |
      | 2   | AOP Zip Hoodie | M          | 50         | 60               |
    And quit browser
    Examples: <KEY>
      | KEY | Product name   | Testcase  |
      | 1   | Quilt          | 3D 1 side |
      | 2   | AOP Zip Hoodie | 3D 2 side |


