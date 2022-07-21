Feature: Create new campaign custom option
  #pbase_new_campaign_editor_custom_PSD

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline:  Custom option with file PSD have effect #SB_PRB_ECP_722
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product    | Category       |
      | AOP Hoodie | All Over Print |
    And add new layer as "<KEY>"
      | KEY | Product    | Layer type | Layer name                | Front or back |
      | 01  | AOP Hoodie | Image      | effect text and image.psd | Front         |
      | 01  | AOP Hoodie | Text       |                           | Back          |
    And verify PSD detail when layer contains effect "true" with editor as "<KEY>"
      | KEY | Product    | Layer name            | Layer in PSD             | isShowEffect |
      | 01  | AOP Hoodie | effect text and image | Lorem Ipsum>true,Layer 0 | true         |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name  | Custom name        |
      | 01  | Text field | Lorem Ipsum | Custom text effect |
      | 01  | Image      | Layer 0     | Custom Image       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags   |
      | <Campaign name> | <Description> | <Tags> |
    And input product price for campaign editor as "<KEY>"
      | KEY | Product    | Variant                         | Sale price | Compare at price |
      | 01  | AOP Hoodie | AOP Hoodie - All over print - M | 30         | 40               |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And verify variant information of product or campaign details in dashboard as "<KEY>"
      | KEY | SKU                      | Price |
      | 01  | AOPHoodie-Alloverprint-M | 30    |
      | 01  | AOPHoodie-Alloverprint-S |       |
    And open product details on Storefront from product detail in dashboard
    Then verify product information from pod on storefront as "<KEY>"
      | KEY | Product name    | Color | Size | Style | Sale price | Compare at price |
      | 1   | <Campaign name> |       | M    |       | 30         | 40               |
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type  | Custom name        | Value Input   | Crop Image |
      | 01  | Text  | Custom text effect |               |            |
      | 01  | Image | Custom Image       | Campaign1.png | false      |
    And verify show button Preview your design on store front "false"
    And quit browser
    Examples:
      | KEY | Campaign name            | Description              | Tags |
      | 01  | CO text and image effect | CO text and image effect | test |

  Scenario Outline:  Custom option with file PSD full option #SB_PRB_ECP_722
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Front or back |
      | 01  | Unisex Tank | Image      | PSD.psd    | Front         |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type     | Layer name | Custom name    | Folder or Group | Thumbnail or Clipart names | Value clipart |
      | 01  | Text field     | Love       | Custom text    |                 |                            |               |
      | 01  | Image          | Image01    | Custom Image   |                 |                            |               |
      | 01  | Picture choice | hair_left  | Picture choice | Folder          | Thumbnail                  | Folder 01     |

    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   |
      | <Campaign name> | <Description> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type           | Custom name    | Value Input   | Crop Image |
      | 01  | Text           | Custom text    | Test text     |            |
      | 01  | Image          | Custom Image   | Campaign1.png | true       |
      | 01  | Picture choice | Picture choice | Campaign1     |            |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Campaign name      | Description        |
      | 01  | CO PSD full option | CO_PSD full option |

  Scenario Outline:  Create cam personalize with many bases and many PSD #SB_PRB_PPC_72
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product                                           | Category       |
      | New Multi Piece Hoodie,New Multi Piece Zip Hoodie | All Over Print |
    And add new layer as "<KEY>"
      | KEY | Product                    | Layer type | Layer name |
      | 01  | New Multi Piece Hoodie     | Image      | PSD 01.psd |
      | 01  | New Multi Piece Zip Hoodie | Image      | PSD 01.psd |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name                       | Custom name | Target layer |
      | 01  | Text field | your name>New Multi Piece Hoodie | Custom text | No           |

    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   |
      | <Campaign name> | <Description> |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type | Custom name | Value Input |
      | 01  | Text | Custom text | Test text   |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Campaign name | Description |
      | 01  | CO_many_PSD   | CO_many_PSD |
