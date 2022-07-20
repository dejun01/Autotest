Feature: Smoke test for funtion create new campaign editor
#   prod_pbase_smoke_create_campaign
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Create new campaign editor
    Given Description: "<Testcase>"
#    Given user login to shopbase dashboard
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 01  | Unisex Tank | Text       | Test layer | Test value  | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PB-AP-UnisexTank-White-S | 30    |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> | White | S    | 30         | 40               |
    And quit browser
    Examples:
      | KEY | Testcase                | Campaign name   | Description     | Tags |
      | 01  | create cam 2D full side | Campaign editor | Campaign editor | test |


  Scenario Outline: Add CO from campaign non personalization #SB_PRB_ff_14 #SB_PRB_ff_13
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Campaigns>All campaigns" screen
    And search campaign in dashboard with name "<Campaign name>"
    And click on Edit personalization btn
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name |
      | 01  | Text field | Text layer 1 | Custom text |
    And click on Save Change btn
    And open product details on Storefront from product detail in dashboard
    And verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Sale price | Compare at price |
      | 01  | <Campaign name> | White | S    | 30         | 40               |
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type | Custom name | Value Input |
      | 01  | Text | Custom text | Test text   |
    And verify show button Preview your design on store front "true"
    Examples:
      | KEY | Testcase                                 | Campaign name   |
      | 01  | Add CO from campaign non personalization | Campaign editor |


