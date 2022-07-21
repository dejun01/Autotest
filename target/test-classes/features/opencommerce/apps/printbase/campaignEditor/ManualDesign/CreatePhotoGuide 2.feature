Feature: Add photo guide to manual design camp
  #pbase_add_photo_guide

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: Add photo guide with case Text #SB_PRB_MC_UMN_5 #SB_PRB_MC_UMN_13 #SB_PRB_MC_UMN_22 #SB_PRB_MC_UMN_2 #SB_PRB_MC_UMN_27 #SB_PRB_MC_UMN_30 #SB_PRB_MC_UMN_36 #SB_PRB_MC_UMN_30
    Given  user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "1"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 1   | Unisex Tank | Image      | FB1.jpg    |             | Back          |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "1"
      | KEY | Layer type | Layer name | Custom name |
      | 1   | Image      | FB1        | Option 1    |
    And add to Additional Materials as "1"
      | KEY | File Name  | File     | Applied For |
      | 1   | Material 1 | BD_2.png | Option 1    |
    And click to button "Continue"
    And add photo guide as "1"
      | KEY | Title        | Type | Content            |
      | 1   | Photo Guide1 | Text | Photo Guide is new |
    And input infor to create description for campaign editor
      | Title                | Description        | Tags | Photo Guide |
      | Camp photoguide text | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And verify result after implement action as "1"
      | KEY | Label name | Campaign name        |
      | 1   | In Review  | Camp photoguide text |
    And login to hive-pbase
    And redirect to campaign "Camp photoguide text" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Camp photoguide text"
    And open product details on Storefront from product detail in dashboard
    Then verify show PhotoGuide on store front after execute as "1"
      | KEY | Custom Name | Type | Content            |
      | 1   | Option 1    | Text | Photo Guide is new |
    And quit browser


  Scenario: Add photo guide with case Image  #SB_PRB_MC_UMN_5 #SB_PRB_MC_UMN_13 #SB_PRB_MC_UMN_22 #SB_PRB_MC_UMN_2 #SB_PRB_MC_UMN_27 #SB_PRB_MC_UMN_30 #SB_PRB_MC_UMN_36 #SB_PRB_MC_UMN_30
    Given  user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "1"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 1   | Unisex Tank | Image      | FB1.jpg    |             | Back          |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "1"
      | KEY | Layer type | Layer name | Custom name |
      | 1   | Image      | FB1        | Option 2    |
    And add to Additional Materials as "1"
      | KEY | File Name  | File     | Applied For |
      | 1   | Material 1 | BD_2.png | Option 2    |
    And click to button "Continue"
    And add photo guide as "1"
      | KEY | Title        | Type  | Content  |
      | 1   | Photo Guide2 | Image | BD_2.png |
    And input infor to create description for campaign editor
      | Title                 | Description        | Tags | Photo Guide |
      | Camp photoguide image | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And verify result after implement action as "1"
      | KEY | Label name | Campaign name         |
      | 1   | In Review  | Camp photoguide image |
    And login to hive-pbase
    And redirect to campaign "Camp photoguide image" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Camp photoguide image"
    And open product details on Storefront from product detail in dashboard
    Then verify show PhotoGuide on store front after execute as "1"
      | KEY | Custom Name | Type  |
      | 1   | Option 2    | Image |
    And quit browser

