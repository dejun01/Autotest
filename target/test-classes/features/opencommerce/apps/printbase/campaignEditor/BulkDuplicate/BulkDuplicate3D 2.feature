Feature: Create campaign 3D
  #print_on_demand_bulk_duplicate_campaign_3D
  Scenario: Delete all campaign
    When  delete all campaigns by API

  Scenario Outline: AU_CCP_4.1: Push product to store with product 3D
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product | Category      |
      | 1   | Quilt   | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product | Layer type |
      | 1   | Quilt   | Text       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product | Variant                         | Sale price | Compare at price |
      | 1   | Quilt   | Quilt - All over print - Single | 50         | 60               |
    And click to button Launch campaign
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY | Keyword         |
      | 1   | <Campaign name> |

    Then verify campaign created as "<KEY>"
      | KEY | Campaign name   | Status | Is enable duplicate | Is enable bulk duplicate |
      | 1   | <Campaign name> | LIVE   | true                | true                     |

    And quit browser
    Examples: <KEY>
      | KEY | Testcase  | Campaign name |
      | 1   | 3D 1 side | Quilt         |


  Scenario Outline: AU_CP_4.1_ Bulk duplicate campaign 3D #SB_PRB_BD_69
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And implement action with campaign as "<KEY>"
      | KEY | Action name                 | Campaign name |
      | 2   | Make 1 campaign unavailable | Quilt         |
    Then verify result after implement action as "<KEY>"
      | KEY | Label name  | Campaign name |
      | 2   | Unavailable | Quilt         |
    And bulk duplicate campaign as "<KEY>"
      | KEY | Campaign name | Artwork   |
      | 1   | Quilt         | Bulk1.jpg |
      | 2   | Quilt         | Bulk3.png |
    And search product or campaign or orders at list page in dashboard as "<KEY>"
      | KEY   | Keyword         |
      | <KEY> | <Campaign name> |
    Then verify campaign created as "<KEY>"
      | KEY | Campaign name | Status      | Is enable duplicate | Is enable bulk duplicate |
      | 1   | Bulk1         | LIVE        | true                | true                     |
      | 2   | Bulk3         | UNAVAILABLE | true                | true                     |

    And open product details in dashboard or editor campaign as "<KEY>"
      | KEY   | Campaign name   |
      | <KEY> | <Campaign name> |

    And verify product information in dashboard as "<KEY>"
      | KEY | SKU                              | Is enable duplicate | Is enable bulk duplicate |
      | 1   | PB-AOP-Quilt-Alloverprint-Single | true                | true                     |
      | 2   | PB-AOP-Quilt-Alloverprint-Queen  | true                | true                     |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 1   |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Size   | Get sale price | Get compare at price |
      | 1   | <Campaign name> | Single | 50             | 60                   |
    And quit browser
    Examples:
      | KEY | Campaign name | Testcase                               |
      | 1   | Bulk1         | Bulk duplicate 3D campaign available   |
      | 2   | Bulk3         | Bulk duplicate 3D campaign unavailable |
