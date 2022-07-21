Feature: Smoke test for funtion create campaign custom option
  #prod_pbase_smoke_create_campaign_custom_option

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline:  Custom option with file PSD full option
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category       |
      | AOP Hoodie  | All Over Print |
      | Unisex Tank | Apparel        |
    And get position base product in editor
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 01  | Unisex Tank | Image      | PSD.psd    |             | Front         |
      | 01  | AOP Hoodie  | Image      | PSD.psd    |             | Front         |
      | 01  | AOP Hoodie  | Text       | Love       | Test value  | Back          |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name | Custom name  |
      | 01  | Text field | Love       | Custom text  |
      | 01  | Image      | Image01    | Custom Image |

    And add custom option for picture choice as "<KEY>"
      | KEY | Layer name | Custom name    | Image               |
      | 01  | hair_left  | Picture choice | image/Campaign1.png |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description        | Tags |
      | <Campaign name> | CO_PSD full option | test |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify show custom option on store front as "<KEY>"
      | KEY | Type           | Custom name    | Value Input   |
      | 01  | Text           | Custom text    | Test text     |
      | 01  | Image          | Custom Image   | Campaign1.png |
      | 01  | Picture choice | Picture choice | Campaign1     |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Campaign name      |
      | 01  | CO PSD full option |
