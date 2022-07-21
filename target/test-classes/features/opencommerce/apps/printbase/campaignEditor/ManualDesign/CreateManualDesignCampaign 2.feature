Feature: Create manual design campaign
  #pbase_add_manual_design_campaign

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: Custom option with manual design #SB_PRB_MDC_1 #SB_PRB_MDC_8 #SB_PRB_MDC_38 #SB_PRB_MDC_15 #SB_PRB_MDC_16 #SB_PRB_MDC_48
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "1"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 1   | Unisex Tank | Text       |            | Test value  | Front         |
      | 1   | Unisex Tank | Image      | FB1.jpg    |             | Back          |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "1"
      | KEY | Layer type | Layer name   | Custom name | Value |
      | 1   | Radio      |              | Option 1    | a > b |
      | 1   | Text field | Text layer 1 | Option 2    |       |
      | 1   | Text area  | Text layer 1 | Option 3    |       |
      | 1   | Image      | FB1          | Option 4    |       |
    And add conditional logic as "1"
      | KEY | Option   | Condition     | Then show |
      | 1   | Option 1 | is equal to>a | Option 3  |
    And add to Additional Materials as "1"
      | KEY | File Name  | File       | Applied For |
      | 1   | Material 1 | BD_2.png   | Option 2    |
      | 1   | Material 2 | autumn.jpg | Option 3    |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title                | Description        | Tags | Photo Guide |
      | Manual Desgin Camp 1 | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And redirect to campaign "Manual Desgin Camp 1" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Manual Desgin Camp 1"
    And open product details on Storefront from product detail in dashboard
    Then verify show new manual design on store front
      | Type  | Custom name | Value Input   | Checked | Is show Photo guide |
      | Radio | Option 1    | a             | true    |                     |
      | Radio | Option 1    | b             | false   |                     |
      | Text  | Option 2    | Test text     |         |                     |
      | Text  | Option 3    | Test text     |         |                     |
      | Image | Option 4    | Campaign1.png |         | true                |
    And quit browser


  Scenario:  Custom option other with manual design 2 #SB_PRB_MDC_1 #SB_PRB_MDC_8 #SB_PRB_MDC_38 #SB_PRB_MDC_45 #SB_PRB_MDC_15 #SB_PRB_MDC_16
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
      | KEY | Layer type     | Layer name   | Custom name | Value     | Folder or Group | Thumbnail or Clipart names | Value clipart |
      | 1   | Radio          |              | Option 1    | a > b     |                 |                            |               |
      | 1   | Text field     | Text layer 1 | Option 2    |           |                 |                            |               |
      | 1   | Picture choice | BD_2         | Option 3    |           | Folder          | Thumbnail                  | Clipart A     |
      | 1   | Droplist       |              | Option 4    | Hoa > Nam |                 |                            |               |
      | 1   | Checkbox       | Text layer 1 | Option 5    |           |                 |                            |               |
    And add conditional logic as "1"
      | KEY | Option   | Condition         | Then show |
      | 1   | Option 1 | is not equal to>b | Option 2  |
    And add to Additional Materials as "1"
      | KEY | File Name  | File     | Applied For |
      | 1   | Material 1 | BD_2.png | Option 2    |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title                | Description        | Tags | Photo Guide |
      | Manual Design Camp 2 | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And redirect to campaign "Manual Design Camp 2" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Manual Design Camp 2"
    And open product details on Storefront from product detail in dashboard
    Then verify show new manual design on store front
      | Type           | Custom name | Value Input        | Checked | Selected |
      | Radio          | Option 1    | a                  | true    |          |
      | Radio          | Option 1    | b                  | false   |          |
      | Text           | Option 2    | Test text          |         |          |
      | Picture choice | Option 3    | customer_signature |         |          |
      | Droplist       | Option 4    | Hoa                |         | true     |
      | Droplist       | Option 4    | Nam                |         |          |
      | Checkbox       | Option 5    |                    | true    |          |
    And quit browser

  Scenario:  Custom option multi Image with manual design   #SB_PRB_MDC_1 #SB_PRB_MDC_8 #SB_PRB_MDC_38 #SB_PRB_MDC_45 #SB_PRB_MDC_15 #SB_PRB_MDC_16
    Given  user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "3"
      | KEY | Product     | Layer type | Layer name | Layer value | Front or back |
      | 3   | Unisex Tank | Image      | FB1.jpg    |             | Front         |
      | 3   | Unisex Tank | Image      | BD_2.png   |             | Back          |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "3"
      | KEY | Layer type | Layer name | Custom name | Value | Front           |
#      | 3   | Radio      |            | Option 1    | a > b |                 |
      | 3   | Image      | BD_2       | Option 2    |       | Source Sans Pro |
      | 3   | Image      | FB1        | Option 3    |       |                 |
      | 3   | Image      | BD_2       | Option 4    |       |                 |
    And add to Additional Materials as "3"
      | KEY | File Name  | File       | Applied For |
      | 3   | Material 3 | autumn.jpg | Option 2    |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title                    | Description        | Tags | Photo Guide |
      | Manual Desgin Camp Image | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And login to hive-pbase
    And redirect to campaign "Manual Desgin Camp Image" hive-pbase
    And action review custom art in campaign hive-pbase
      | Action         |
      | Design approve |
    And user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And open product details in dashboard or editor campaign "Manual Desgin Camp Image"
    And open product details on Storefront from product detail in dashboard
    Then verify show manual design on store front
      | Type  | Custom name | Value Input | Checked | Is show Photo guide |
#      | Radio | Option 1    | a           | true    |                     |
#      | Radio | Option 1    | b           | false   |                     |
      | Image | Option 2    | 3D_3.png    |         | true                |
      | Image | Option 3    | 3D_4.png    |         | false               |
      | Image | Option 4    | 3D_5.png    |         | false               |
    And quit browser








