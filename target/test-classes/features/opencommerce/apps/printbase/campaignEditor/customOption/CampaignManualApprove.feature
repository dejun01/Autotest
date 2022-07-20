Feature: Create campaign manual design approve
  #pbase_create_camp_manual_approve

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: create campaign manual design incase reject #SB_PRB_RMC_149 #SB_PRB_RMC_150 #SB_PRB_UXP_18 #SB_PRB_UXP_3
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "1"
      | KEY | Product     | Layer type | Layer name | Layer value  | Front or back |
      | 1   | Unisex Tank | Text       |            | Test value 1 | Front         |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "1"
      | KEY | Layer type | Layer name   | Custom name | Value | Folder or Group | Thumbnail or Clipart names | Value clipart |
      | 1   | Text field | Text layer 1 | Option 1    |       |                 |                            |               |
    And add to Additional Materials as "1"
      | KEY | File Name  | File     | Applied For |
      | 1   | Material 1 | BD_2.png | Option 1    |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title         | Description        | Tags     |
      | Design reject | CO_PSD full option | campaign |
    And click to button Launch campaign
    Then verify result after implement action as "1"
      | KEY | Label name | Campaign name |
      | 1   | In Review  | Design reject |
    And quit browser

  Scenario: create campaign manual design incase approve #SB_PRB_RMC_149 #SB_PRB_RMC_150 #SB_PRB_UXP_18 #SB_PRB_UXP_3
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "2"
      | KEY | Product     | Layer type | Layer name | Layer value  | Front or back |
      | 2   | Unisex Tank | Text       |            | Test value 2 | Front         |
      | 2   | Unisex Tank | Image      | FB1.jpg    |              | Back          |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "2"
      | KEY | Layer type     | Layer name   | Custom name | Value           | Folder or Group | Thumbnail or Clipart names | Value clipart |
#      | 2   | Radio          |              | Option 1    | a > b           |                 |                            |               |
      | 2   | Text field     | Text layer 1 | Option 2    |                 |                 |                            |               |
      | 2   | Picture choice | FB1          | Option 3    |                 | Folder          | Thumbnail                  | Clipart A     |
#      | 2   | Droplist       |              | Option 4    | Linh A > Linh B |                 |                            |               |
      | 2   | Checkbox       | Text layer 1 | Option 5    |                 |                 |                            |               |
    And add to Additional Materials as "2"
      | KEY | File Name  | File     | Applied For |
      | 2   | Material 2 | BD_2.png | Option 2    |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title          | Description        | Tags     |
      | Design approve | CO_PSD full option | campaign |
    And click to button Launch campaign
    Then verify result after implement action as "2"
      | KEY | Label name | Campaign name  |
      | 2   | In Review  | Design approve |
    And quit browser


  Scenario: Check case design approve #SB_PRB_MC_RMC_3 #SB_PRB_MC_RMC_4 #SB_PRB_MC_RMC_11
    Given login to hive-pbase
    And redirect to campaign "Design approve" hive-pbase
    When action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard
    And user navigate to "Campaigns" screen
    Then verify result after implement action as "1"
      | KEY | Label name | Campaign name  |
      | 1   | Available  | Design approve |
    And open product details in dashboard or editor campaign "Design approve"
    And open product details on Storefront from product detail in dashboard
    Then verify show new manual design on store front
      | Type           | Custom name | Value Input | Checked | Selected |
#      | Radio          | Option 1    | a           | true    |          |
#      | Radio          | Option 1    | b           | false   |          |
      | Text           | Option 2    | Test text   |         |          |
      | Picture choice | Option 3    | BD_2        |         |          |
#      | Droplist       | Option 4    | Linh A      |         | true     |
#      | Droplist       | Option 4    | Linh B      |         | false    |
      | Checkbox       | Option 5    |             | true    |          |
    And quit browser

  Scenario: Check case design reject #SB_PRB_MC_RMC_11
    Given login to hive-pbase
    And redirect to campaign "Design reject" hive-pbase
    When action review custom art in campaign hive-pbase
      | Action        | Reject Reason |
      | Design reject | Image error   |
    And user login to shopbase dashboard
    And user navigate to "Campaigns" screen
    Then verify result after implement action as "1"
      | KEY | Label name | Campaign name | Reject reason |
      | 1   | Rejected   | Design reject | Image error   |
    And quit browser
