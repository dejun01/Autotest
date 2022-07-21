Feature: Create new campaign custom option radio and droplist
  #env: pbase_flow_target_layer

  Scenario: delete all campaigns
    When delete all campaigns by API

  Scenario Outline: Custom option with radio #SB_PRB_ECP_669 #SB_PRO_DFPP_775 #SB_PRO_DFPP_810 #SB_PRO_DFPP_818
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
      | Product          | Layer type | Layer                         |
      | Landscape Puzzle | Radio      | Text layer 1>Landscape Puzzle |
    And input value radio
      | Value | Default | Add more value | Delete value |
      | A     |         |                |              |
      | B     | true    | Yes            |              |
      | C     |         |                | Yes          |
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
      | Type  | Custom name | Value | Is check |
      | Radio | Radio       | A     | false    |
      | Radio | Radio       | B     | true     |
    And quit browser
    Examples:
      | KEY | Testcase                            | Campaign name |
      | 1   | Create option radio with layer text | Custom_radio  |


  Scenario Outline: Custom option with droplist #SB_PRB_ECP_671 #SB_PRO_DFPP_776 #SB_PRO_DFPP_811 #SB_PRO_DFPP_812 #SB_PRO_DFPP_820
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
      | Product          | Layer type | Layer                         |
      | Landscape Puzzle | Droplist   | Text layer 1>Landscape Puzzle |
    And input value radio
      | Value | Default | Add more value | Delete value |
      | A     |         |                |              |
      | B     | true    | Yes            |              |
      | C     |         |                | Yes          |
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
      | Type     | Custom name | Value | Is selected |
      | Droplist | Options     | A     |             |
      | Droplist | Options     | B     | true        |
    And quit browser
    Examples:
      | KEY | Testcase                               | Campaign name   |
      | 1   | Create option droplist with layer text | Custom_droplist |