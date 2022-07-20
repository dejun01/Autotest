Feature: Ccreate 2D campaigns for my store
  #environment: print_hub_campaign_2D

  Scenario: Delete all campaign
    When delete all campaigns by API
  @dashboardPushCampToStore
  Scenario Outline: Push camp to store
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
    And create campaign in Dashboard
    When add products to campaign as "<KEY>"
      | KEY | Product                         | Category |
      | 01  | Unisex Tank,Crewneck Sweatshirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value | Font | Front or back |
      | 01  | Unisex Tank | Text       |            | Test value  | 210  | Front         |
      | 01  | Unisex Tank | Image      | 39.png     |             |      | Back          |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40               |
      | 01  | Unisex Tank | Unisex Tank - White - M | 30.346     | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PH-AP-UnisexTank-White-S | 30    |
      | 01  | PH-AP-UnisexTank-White-M | 30.35 |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 01  |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Style       | Sale price | Compare at price |
      | 01  | <Campaign name> | White | M    | Unisex Tank | 30.35      | 40               |
      | 01  | <Campaign name> | White | S    | Unisex Tank | 30         | 40               |
    And quit browser
    Examples:
      | KEY | Testcase                | Campaign name | Description | Tags |
      | 01  | create cam 2D full side | 2D full       | 2D full     | test |

  Scenario Outline: Push campaign same name incase multi product
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
    And create campaign in Dashboard
    When add products to campaign as "<KEY>"
      | KEY | Product                         | Category |
      | 01  | Unisex Tank,Crewneck Sweatshirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value | Font | Color | Front or back |
      | 01  | Unisex Tank | Text       |            | Test value  | 210  |       | Front         |
      | 01  | Unisex Tank | Image      | 39.png     |             |      |       | Back          |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 01  | Unisex Tank | Unisex Tank - White - S | 30.00      | 40               |
      | 01  | Unisex Tank | Unisex Tank - White - M | 30.346     | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | PH-AP-UnisexTank-White-S | 30    |
      | 01  | PH-AP-UnisexTank-White-M | 30.35 |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 01  |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Style       | Sale price | Compare at price |
      | 01  | <Campaign name> | White | M    | Unisex Tank | 30.35      | 40               |
      | 01  | <Campaign name> | White | S    | Unisex Tank | 30         | 40               |
    And quit browser
    Examples:
      | KEY | Campaign name | Description | Tags |
      | 01  | Campaign 2    | 2D full     | test |

  Scenario Outline: Push campaign same name incase 1 base product
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Catalog" screen
    And create campaign in Dashboard
    When add products to campaign as "<KEY>"
      | KEY | Product                         | Category |
      | 02  | Unisex Tank                     | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value | Font | Color | Front or back |
      | 02  | Unisex Tank | Text       |            | Test value  | 210  |       | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product     | Variant                 | Sale price | Compare at price |
      | 02  | Unisex Tank | Unisex Tank - White - M | 30.346     | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 02  | PH-AP-UnisexTank-White-M | 30.35 |
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 02  |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Style       | Sale price | Compare at price |
      | 02  | <Campaign name> | White | M    |             | 30.35      | 40               |
    And quit browser
    Examples:
      | KEY | Campaign name | Description | Tags |
      | 02  | Campaign 2    | 2D full     | test |

  Scenario: Search campaign
    Given open shop on storefront
    And search campaing "<Campaign name>" on Store front
    And verify number campaign on Store front as "<KEY>"
      | KEY | Campaign name | Number |
      | 01  | Campaign 2    | 2      |
    And select campaign after search as "<KEY>"
      | KEY | Campaign name |
      | 01  | Campaign 2    |
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name | Color | Size | Style       | Sale price | Compare at price |
      | 01  | Campaign 2   | White | M    | Unisex Tank | 30.35      | 40               |
      | 01  | Campaign 2   | White | S    | Unisex Tank | 30         | 40               |
    And quit browser