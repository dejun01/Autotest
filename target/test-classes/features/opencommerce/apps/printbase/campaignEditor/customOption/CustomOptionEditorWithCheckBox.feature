Feature: Create new campaign custom option check box
  #env: pbase_flow_target_layer

  Scenario: delete all campaigns
    When delete all campaigns by API

  Scenario Outline: Custom option with checkbox with text no select target layer and uncheck value #SB_PRO_DFPP_777 #SB_PRO_DFPP_808 #SB_PRO_DFPP_809
    Given  Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product          | Category      |
      | 1   | Landscape Puzzle | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name   |
      | 1   | Landscape Puzzle | Text       | Text layer 1 |
    And add custom options for campaign
    Then select layer for every product
      | Product          | Layer type | Target layer | Layer                         |
      | Landscape Puzzle | Checkbox   | false        | Text layer 1>Landscape Puzzle |
    Then uncheck value target layer custom option
    And click to button "Save"
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags |
      | <Campaign name> | Custom option | test |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify custom option on store front
      | Type     | Custom name | Is check |
      | Checkbox | Options     | false    |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Testcase                               | Campaign name                     |
      | 1   | Create option checkbox with layer text | Custom_checkbox_with_text_uncheck |


  Scenario Outline: Custom option with checkbox with image no select target layer and uncheck value #SB_PRB_ECP_652 #SB_PRB_ECP_697
    Given  Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product          | Category      |
      | 1   | Landscape Puzzle | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name |
      | 1   | Landscape Puzzle | Image      | BD_42.png  |
    And add custom options for campaign
    Then select layer for every product
      | Product          | Layer type | Target layer | Layer                  |
      | Landscape Puzzle | Checkbox   | false        | BD_42>Landscape Puzzle |
    Then uncheck value target layer custom option
    And click to button "Save"
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags |
      | <Campaign name> | Custom option | test |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify custom option on store front
      | Type     | Custom name | Is check |
      | Checkbox | Options     | false    |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Testcase                                | Campaign name                      |
      | 1   | Create option checkbox with layer image | Custom_checkbox_with_image_uncheck |


  Scenario Outline: Custom option with checkbox with image select target layer and check value #SB_PRB_ECP_652 #SB_PRB_ECP_697
    Given  Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product          | Category      |
      | 1   | Landscape Puzzle | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name |
      | 1   | Landscape Puzzle | Image      | BD_42.png  |
    And add custom options for campaign
    Then select layer for every product
      | Product          | Layer type | Target layer | Layer                  |
      | Landscape Puzzle | Checkbox   | true         | BD_42>Landscape Puzzle |
    And click to button "Save"
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags |
      | <Campaign name> | Custom option | test |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify custom option on store front
      | Type     | Custom name | Is check |
      | Checkbox | Options     | true     |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Testcase                                | Campaign name                    |
      | 1   | Create option checkbox with layer image | Custom_checkbox_with_image_check |


  Scenario Outline: Custom option with checkbox with text select target layer and check value ##SB_PRB_ECP_673
    Given  Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product          | Category      |
      | 1   | Landscape Puzzle | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product          | Layer type | Layer name   |
      | 1   | Landscape Puzzle | Text       | Text layer 1 |
    And add custom options for campaign
    Then select layer for every product
      | Product          | Layer type | Target layer | Layer                         |
      | Landscape Puzzle | Checkbox   | true         | Text layer 1>Landscape Puzzle |
    And click to button "Save"
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           | Description   | Tags |
      | <Campaign name> | Custom option | test |
    And click to button Launch campaign
    And search product or campaign or orders "<Campaign name>" at list page in dashboard
    And open product details in dashboard or editor campaign "<Campaign name>"
    And open product details on Storefront from product detail in dashboard
    Then verify custom option on store front
      | Type     | Custom name | Is check |
      | Checkbox | Options     | true     |
    And verify show button Preview your design on store front "true"
    And quit browser
    Examples:
      | KEY | Testcase                               | Campaign name                   |
      | 1   | Create option checkbox with layer text | Custom_checkbox_with_text_check |