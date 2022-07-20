Feature:Duplicate campaign draf
#   staging_pbase_duplicate_draf_editor

  Scenario Outline:  Precondition create data test
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category       |
      | Unisex Tank | Apparel        |
      | AOP Hoodie  | All Over Print |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value | Font | Color | Location | Rotate | Opacity | Front or back |
      | 01  | Unisex Tank | Text       | Test layer | Test value  | 300  |       | 300>1000 | 30     | 80      | Front         |
      | 01  | Unisex Tank | Image      | 39.png     |             |      |       |          |        |         | Back          |
      | 02  | Unisex Tank | Text       | Test layer | Test value  | 300  |       | 300>1000 | 30     | 80      | Front         |
      | 02  | Unisex Tank | Image      | 39.png     |             |      |       |          |        |         | Back          |
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name | Custom name  | Font    | Placeholder | Max length |
      | 02  | Text field | Test layer | Custom text  | Raleway | Input text  | 16         |
      | 02  | Image      | 39.png     | Custom Image |         |             |            |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.002     | 40               |
      | 02  | Unisex Tank | Unisex Tank - White - S | 30.002     | 40               |
    And click to button Launch campaign
    And quit browser
    Examples:
      | KEY | Campaign name        |
      | 02  | Campaign Draf        |
      |     | Campaign custom Draf |

  Scenario Outline: Duplicate keep artwork with cam draf  #SB_PRB_DKA_90
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And duplicate campaign editor
      | Campaign origin   | Campaign name        | Is keep artwork |
      | <Campaign origin> | <Campaign Duplicate> | true            |
    And verify layer and base products editor as "<KEY>"
      | KEY | Product     | Layer            | Font | Size | Location | Rotate | Opacity |
      | 01  | Unisex Tank | Test layer>Front |      | 300  | 300>1000 | 30     | 80      |
    And click to button "Continue"
    And verify config in Pricing tab of editor detail as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare price | isShow |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40            | true   |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign Duplicate>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign Duplicate>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-S | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name         | Style       | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign Duplicate> | Unisex Tank | White | S    | 30         | 40               |
    And quit browser
    Examples:
      | KEY | Campaign origin | Campaign Duplicate | Description          | Tags |
      | 01  | Campaign Draf   | Duplicate Draf     | Campaign 3D two side | test |


  Scenario Outline: Duplicate keep artwork and custom option with cam custom draf
    Given user login to shopbase dashboard
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name        | Is keep artwork |
      | <Campaign origin> | <Campaign Duplicate> | true            |
    And verify layer and base products editor as "<KEY>"
      | KEY | Product     | Layer            | Font | Size | Location | Rotate | Opacity |
      | 01  | Unisex Tank | Test layer>Front |      | 300  | 300>1000 | 30     | 80      |
    And verify layer after custom option as "<KEY>"
      | KEY | Product     | Layer name | Layer type | Custom name  |
      | 01  | Unisex Tank | Test layer | Text       | Custom text  |
      | 01  | Unisex Tank | 39.png     | Image      | Custom Image |
    And click to button "Continue"
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.002     | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign Duplicate>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign Duplicate>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-S | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name         | Style       | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign Duplicate> | Unisex Tank | White | S    | 30         | 40               |
    And quit browser
    Examples:
      | KEY | Campaign origin      | Campaign Duplicate    | Description          | Tags |
      | 01  | Campaign custom Draf | Duplicate custom Draf | Campaign custom Draf | test |


  Scenario Outline: Duplicate dont keep artwork and custom option with cam custom draf
    Given user login to shopbase dashboard
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "<Campaign origin>"
    And duplicate campaign editor
      | Campaign origin   | Campaign name        | Is keep artwork |
      | <Campaign origin> | <Campaign Duplicate> | false           |
    And verify list layer and custom option null with base "Unisex Tank"
    And add new layer as "<KEY>"
    And quit browser
    Examples:
      | Campaign origin      | Campaign Duplicate |
      | Campaign custom Draf | Dont keep artwork  |
