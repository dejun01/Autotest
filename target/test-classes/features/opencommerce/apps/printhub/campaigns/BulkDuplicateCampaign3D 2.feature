Feature: As a merchant, I want to bulk Duplicate Campaign 2D for my store
  #shop: au-ph-bulk-duplicate-50-campaign.stag.myshopbase.net
  #acc: shopbase@beeketing.net, pass: 123456
  #environment: print_hub_bulk_duplicate_campaigns3D

  Scenario Outline: Verify actions on campaign list
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And implement action with campaign as "<KEY>"
      | KEY | Action name               | Campaign name |
      | 1   | Delete selected campaigns |               |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name | Campaign name |
      | 1   | Delete     |               |
    Then close browser
    Examples: <KEY>
      | KEY | Testcase             |
      | 1   | Delete all campaigns |

  Scenario Outline: AU_CCP_4.1: Push product to store with product 3D
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And click to button "Create new campaign"
    When add products to campaign as "<KEY>"
      | KEY | Product | Category      |
      | 1   | Quilt   | Home & Living |

    And input data to create campaign as "<KEY>"
      | KEY | Product | Artwork   | Preview |
      | 1   | Quilt   | Bulk1.jpg | true    |
    And click to tab "Description" in Campaign
    And input data to create description for campaign as "<KEY>"
      | KEY | Title |
      | 1   | Quilt |
    And calculate number of variant for campaign as "<KEY>"
      | KEY |
      | 1   |
    And click to tab "Pricing" in Campaign
    And input product price for campaign as "<KEY>"
      | KEY | Product | Variant                 | Sale price | Compare at price |
      | 1   | Quilt   | All over print - Single | 50         | 60               |

    And click to button "Launch"
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword |
      | 1   | Quilt   |

    Then verify campaign created as "<KEY>"
      | KEY | Campaign name | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Quilt         | LIVE   | true                | true                     |
    And quit browser
    Examples: <KEY>
      | KEY | Testcase  |
      | 1   | 3D 1 side |


  Scenario Outline: AU_CP_4.1_ Bulk duplicate campaign 3D
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "All Campaigns" screen
    And implement action with campaign as "<KEY>"
      | KEY | Action name                  | Campaign name |
      | 2   | Make 1 campaign unavailable | Quilt         |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name  | Campaign name |
      | 2   | Unavailable | Quilt         |
    And bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name | Artwork   |
      | 1   | Quilt         | Bulk1.jpg |
      | 2   | Quilt         | Bulk3.png |
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword |
      | 1   | Bulk1   |
      | 2   | Bulk3   |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name | Status      | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Bulk1         | LIVE        | true                | true                     |
      | 2   | Bulk3         | UNAVAILABLE | true                | true                     |

    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY | Campaign name |
      | 1   | Bulk1         |
      | 2   | Bulk3         |

    And verify product information in dashboard as "<KEY>"
      | KEY | SKU                              | Is enable duplicate | Is enable bulk duplicate |
      | 1   | PH-AOP-Quilt-Alloverprint-Single | true                | true                     |
      | 2   | PH-AOP-Quilt-Alloverprint-Queen  | true                | true                     |

    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 1   |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name | Size   | Sale price | Compare at price |
      | 1   | Bulk1        | Single | 50         | 60               |
    And quit browser
    Examples:
      | KEY | Product name | Testcase                               |
      | 1   | Bulk1        | Bulk duplicate 3D campaign available   |
      | 2   |              | Bulk duplicate 3D campaign unavailable |
