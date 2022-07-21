Feature: List Custom Art Campaign
  #staging_pbase_add_manual_design_campaign
  # prod_pbase_add_manual_design_campaign

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: Verify campaign status in reiview on campaign list #SB_PRB_MC_RCA_1 #SB_PRB_MC_RCA_2
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "1"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 1   | Unisex Tank | Text       |            | Test value  | Front         |
      | 1   | Unisex Tank | Image      | BD_2.png   |             | Back          |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "1"
      | KEY | Layer type | Layer name   | Custom name | Value |
      | 1   | Text field | Text layer 1 | Option 1    |       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title                | Description        | Tags | Photo Guide |
      | Manual Desgin Camp 1 | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And open campaign list on hive
    And filter campaign on custom art list as "1"
      | KEY | Is filter shop |
      | 1   | true           |
    Then verify campaign on custom art list as "1"
      | KEY | Campaign name        | Is filter shop | Is filter email |
      | 1   | Manual Desgin Camp 1 | true           | true            |
    And quit browser


  Scenario Outline: Verify filer campaing on campaign list #SB_PRB_MC_RCA_5 #SB_PRB_MC_RCA_6 #SB_PRB_MC_RCA_10 #SB_PRB_MC_RCA_11 #SB_PRB_MC_RCA_12 #SB_PRB_MC_RCA_13
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "1"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 1   | Unisex Tank | Text       |            | Test value  | Front         |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "1"
      | KEY | Layer type | Layer name   | Custom name | Value |
      | 1   | Text field | Text layer 1 | Option 1    |       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title             | Description        | Tags | Photo Guide |
      | Campaign_manual_2 | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And open campaign list on hive
    Then filter campaign on custom art list as "<KEY>"
      | KEY | Campaign name     | Is filter shop | Is filter email |
      | 1   | Campaign_manual_2 |                |                 |
      | 2   |                   | true           |                 |
      | 3   |                   |                | true            |
    And verify campaign on custom art list as "<KEY>"
      | KEY | Campaign name     | Is filter shop | Is filter email |
      | 1   | Campaign_manual_2 |                |                 |
      | 2   |                   | true           |                 |
      | 3   |                   |                | true            |
    And quit browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |


  Scenario Outline: Verify approve campaign on campaign list #SB_PRB_MC_RCA_25 #SB_PRB_MC_RCA_26 #SB_PRB_MC_RCA_27 #SB_PRB_MC_RCA_30
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 1   | Unisex Tank | Text       |            | Test value  | Front         |
      | 2   | Unisex Tank | Text       |            | Test value  | Front         |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name | Value |
      | 1   | Text area  | Text layer 1 | Option 1    |       |
      | 2   | Text area  | Text layer 1 | Option 1    |       |
    And click to button "Continue"
    And input infor to create description for campaign editor As "<KEY>"
      | KEY | Title             | Description        | Tags | Photo Guide |
      | 1   | Campaign_manual_3 | CO_PSD full option | new  | Yes         |
      | 2   | Campaign_manual_4 | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And open campaign list on hive
    And filter campaign on custom art list as "1"
      | KEY | Is filter shop |
      | 1   | true           |
    And action review custom art in campaign list as "<KEY>"
      | KEY | Campaign name     | Action          | Reject Reason |
      | 1   | Campaign_manual_3 | Design approves |               |
      | 2   | Campaign_manual_4 | Design reject   | Rejected      |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Campaign_manual_3"
    And open product details on Storefront from product detail in dashboard
    Then verify show new manual design on store front
      | Type | Custom name | Value Input | Checked | Is show Photo guide |
      | Area | Option 1    | Test text   |         |                     |
    And quit browser
    Examples:
      | KEY |
      | 1   |
















