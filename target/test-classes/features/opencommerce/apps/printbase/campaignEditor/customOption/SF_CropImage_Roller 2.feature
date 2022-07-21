Feature: Crop image SF
#pbase_crop_image

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline:  Precondition : create campaign
    Given  Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product                       | Category |
      | 01  | Unisex Tank,V-neck T-shirt    | Apparel  |
      | 02  | Unisex T-shirt,Ladies T-shirt | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product        | Layer type | Layer name | Front or back |
      | 01  | Unisex Tank    | Image      | 39.png     | Front         |
      | 02  | Unisex T-shirt | Image      | 39.png     | Front         |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name                          | Custom name  | Target layer |
      | 01  | Image      | 39                                  | Custom Image | Yes          |
      | 02  | Image      | 39>Unisex T-shirt;39>Ladies T-shirt | Custom Image | No           |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags |
      | <Campaign name> | Custom option | test |
    And click to button Launch campaign
    And quit browser
    Examples:
      | KEY | Campaign name              |
      | 01  | CO Image taret layer       |
      | 02  | CO Image with base product |


  Scenario Outline:  Check crop image #SB_PRB_CI_1 #SB_PRB_CI_2 #SB_PRB_CI_3 #SB_PRB_CI_4
    Given Description: "<Test case>"
    Given open shop on storefront
    And search and select the product "<Campaign name>"
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type  | Custom name  | Value Input |
      | 01  | Image | Custom Image | icon.png    |
      | 02  | Image | Custom Image | icon.png    |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Campaign name              |
      | 01  | CO Image taret layer       |
      | 02  | CO Image with base product |