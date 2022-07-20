Feature: Duplicate with campaign custom option
#pbase_duplicate_editor_custom_option

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline:  Precondition create data test
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category       |
      | AOP Hoodie  | All Over Print |
      | Unisex Tank | Apparel        |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Update layer name | Front or back |
      | 01  | Unisex Tank | Image      | PSD.psd    |                   | Front         |
      | 01  | Unisex Tank | Text       |            | Test layer        | Front         |
      | 01  | AOP Hoodie  | Text       |            | Test layer        | Front         |
      | 01  | AOP Hoodie  | Image      | PSD.psd    |                   | Back          |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type     | Layer name | Custom name    | Folder or Group | Thumbnail or Clipart names | Value clipart |
      | 01  | Text field     | Test layer | Custom text    |                 |                            |               |
      | 01  | Image          | Image01    | Custom Image   |                 |                            |               |
      | 01  | Picture choice | hair_left  | Picture choice | Folder          | Thumbnail                  | Folder 01     |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And quit browser
    Examples:
      | KEY | Campaign name        | Description        | Tags |
      | 01  | Campaign personalize | CO_PSD full option | test |


  Scenario Outline: Duplicate keep artwork and custom option with cam personalize #SB_PRB_DKA_95 #SB_PRB_CL_106
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "Campaign personalize"
    And duplicate campaign editor
      | Campaign origin      | Campaign name   | Is keep artwork |
      | Campaign personalize | <Campaign name> | true            |
    And click button expand on Custom Option
    And verify layer after custom option as "<KEY>"
      | KEY | Product     | Layer name | Custom name  |
      | 01  | Unisex Tank | Test layer | Custom text  |
      | 01  | Unisex Tank | PSD        | Custom Image |
      | 01  | AOP Hoodie  | Test layer | Custom text  |
      | 01  | AOP Hoodie  | PSD        | Custom Image |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Update layer value | Font | Color | Location layer | Size layer | Rotate layer | Opacity layer | Front or back |
      | 01  | Unisex Tank | Text       | Add layer text     | 210  |       | 0>500          | 1300>300   | 96.44        | 80            | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type           | Custom name    | Value Input   |
      | 01  | Text           | Custom text    | Test text     |
      | 01  | Image          | Custom Image   | Campaign1.png |
      | 01  | Picture choice | Picture choice | Campaign1     |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Campaign name         | Description     | Tags        |
      | 01  | Duplicate_personalize | Cam personalize | ladies,test |


  Scenario Outline: Duplicate cancel keep artwork with cam personalize #SB_PRB_DKA_107
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And search campaign in dashboard with name "Campaign personalize"
    And duplicate campaign editor
      | Campaign origin      | Campaign name   | Is keep artwork |
      | Campaign personalize | <Campaign name> | false           |
    And verify list layer and custom option null
    And add more or remove products to campaign as "<KEY>"
      | KEY | Product    | Remove |
      | 01  | AOP Hoodie | true   |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name     | Layer value | Font | Color | Location layer | Size layer | Rotate layer | Opacity layer | Front or back |
      | 01  | Unisex Tank | Text       | Add layer text | Test value  | 210  |       | 0>500          | 1300>300   | 96.44        | 80            | Front         |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify product information in dashboard
      | Tags   | Description   |
      | <Tags> | <Description> |
    And quit browser
    Examples:
      | KEY | Campaign name      | Description     | Tags        |
      | 01  | Duplicate_not_keep | Cam personalize | ladies,test |
