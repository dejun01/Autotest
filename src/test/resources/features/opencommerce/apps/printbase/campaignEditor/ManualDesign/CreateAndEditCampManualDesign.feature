Feature: Create manual design campaign
  #pbase_add_manual_design_campaign

  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: Create camp normal before switch manual design
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "01"
      | KEY | Product     | Layer type |
      | 01  | Unisex Tank | Text       |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title       | Description        | Tags |
      | Camp normal | CO_PSD full option | new  |
    And click to button Launch campaign
    And search product or campaign or orders "Camp normal" at list page in dashboard
    And quit browser

  Scenario: Create camp personalize before switch manual design
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "01"
      | KEY | Product     | Layer type | Layer name | Front or back |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Image      | FB1.jpg    | Back          |
    And add custom options for campaign
    And setup custom option for campaign personalize in editor campaign as "01"
      | KEY | Layer type | Layer name   | Custom name |
      | 01  | Text field | Text layer 1 | Option 1    |
      | 01  | Image      | FB1          | Option 2    |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title            | Description        | Tags | Photo Guide |
      | Camp personalize | CO_PSD full option | new  | Yes         |
    And click to button Launch campaign
    And search product or campaign or orders "Camp personalize" at list page in dashboard
    And quit browser

  Scenario Outline: Verify Material when create camp manual design  #SB_PRB_MDC_1 #SB_PRB_MDC_3 #SB_PRB_MDC_2 #SB_PRB_MDC_4 #SB_PRB_MDC_5 #SB_PRB_MDC_6 #SB_PRB_MDC_7 #SB_PRB_MDC_12 #SB_PRB_MDC_39 #SB_PRB_MDC_40 #SB_PRB_MDC_41 #SB_PRB_MDC_42 #SB_PRB_MDC_43 #SB_PRB_MDC_44 #SB_PRB_MDC_17
    Given Description: "<Testcase>"
    And user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign
      | Product     | Category |
      | Unisex Tank | Apparel  |
    And get position base product in editor
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Front or back |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Image      | FB1.jpg    | Back          |
      | 02  | Unisex Tank | Text       |            | Front         |
      | 02  | Unisex Tank | Text       |            | Front         |
      | 02  | Unisex Tank | Text       |            | Front         |
      | 02  | Unisex Tank | Image      | FB1.jpg    | Back          |
      | 03  | Unisex Tank | Text       |            | Front         |
      | 03  | Unisex Tank | Image      | FB1.jpg    | Front         |
      | 04  | Unisex Tank | Text       |            | Front         |
      | 04  | Unisex Tank | Image      | FB1.jpg    | Front         |
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "<KEY>"
      | KEY | Layer type     | Layer name   | Custom name | Value     | Folder or Group | Thumbnail or Clipart names | Value clipart |
      | 01  | Radio          |              | Option 1    | a > b     |                 |                            |               |
      | 02  | Radio          |              | Option 1    | a > b     |                 |                            |               |
      | 03  | Radio          |              | Option 1    | a > b     |                 |                            |               |
      | 03  | Text field     | Text layer 1 | Option 2    |           |                 |                            |               |
      | 03  | Text area      | Text layer 1 | Option 3    |           |                 |                            |               |
      | 03  | Image          | FB1          | Option 4    |           |                 |                            |               |
      | 04  | Radio          |              | Option 1    | a > b     |                 |                            |               |
      | 04  | Text field     | Text layer 1 | Option 2    |           |                 |                            |               |
      | 04  | Picture choice | FB1          | Option 3    |           | Folder          | Thumbnail                  | Clipart A     |
      | 04  | Droplist       |              | Option 4    | Hoa > Nam |                 |                            |               |
      | 04  | Checkbox       | Text layer 1 | Option 5    |           |                 |                            |               |
    And add to Additional Materials as "<KEY>"
      | KEY | File Name | File | Applied For |
      | 01  |           |      | Option 1    |
      | 02  |           |      | Option 1    |
      | 03  | Name 1    |      | Option 2    |
      | 04  | Name 2    |      |             |
    Then verify Custom Option after click any action as "<KEY>"
      | KEY | Custom Name | Action | Message                                                                              |
      | 01  | Option 1    | Delete | Please add an option to request our Custom Art service. If not, turn off the request |
      | 02  | Option 1    | Delete | Please add an option to request our Custom Art service. If not, turn off the request |
    And edit Additional Materials as "<KEY>"
      | KEY | File Name | File | Applied For |
      | 01  |           |      | A           |
    And delete material with quantity as "<KEY>"
      | KEY | Quantity Materials |
      | 02  | 1                  |
    And click to button "Continue"
    And verify message error in pricing screen "<KEY>"
      | KEY | Reason                           | Message Error                                                                             |
      | 01  | Dont Select CO                   | Incomplete required field(s) in Custom option. Please complete them on the previous step. |
      | 02  | Dont Select CO                   | Incomplete required field(s) in Custom option. Please complete them on the previous step. |
      | 03  | Dont Select Upload file Material | Miss required fields on Additional materials. Please complete them on the previous step   |
      | 04  | Dont Select Upload file Material | Miss required fields on Additional materials. Please complete them on the previous step   |
    And verify button Launch camp as "disabled"
    And quit browser

    Examples:
      | KEY | Testcase                                         |
      | 01  | Verify material when CO error |
      | 02  | Delete material                                  |
      | 03  | Verify material dont upload file and applied for |
      | 04  | Verify material dont upload file                 |


  Scenario: Switch camp normal to camp manual design   #SB_PRB_MDC_15 #SB_PRB_MDC_16 #SB_PRB_MDC_27 #SB_PRB_MDC_28 #SB_PRB_MDC_38
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Camp normal"
    And Edit campaign with data as "01"
      | KEY | Title                          | Description                                                            |
      | 01  | Camp normal switch camp manual | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And click Save changes on camp detail
    And click on Edit personalization btn
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "01"
      | KEY | Layer type | Layer name   | Custom name |
      | 01  | Text field | Text layer 1 | Option 1    |
    And add to Additional Materials as "01"
      | KEY | File Name  | File     | Applied For |
      | 01  | Material 1 | BD_2.png | Option 1    |
    And click Save change on Edit personalize
    And user navigate to "Campaigns" screen
    And verify result after implement action as "01"
      | KEY | Label name | Campaign name                  |
      | 01  | Available  | Camp normal switch camp manual |
    And quit browser


  Scenario: Switch camp personalize to camp manual design   #SB_PRB_MDC_15 #SB_PRB_MDC_16 #SB_PRB_MDC_27 #SB_PRB_MDC_28 #SB_PRB_MDC_38
    Given  user login to shopbase dashboard by API
    And user navigate to "Campaigns" screen
    And open product details in dashboard or editor campaign "Camp personalize"
    And Edit campaign with data as "01"
      | KEY | Title                               | Description                                                            |
      | 01  | Camp personalize switch camp manual | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |
    And click Save changes on camp detail
    And click on Edit personalization btn
    And add custom options for campaign
    And setup custom option for campaign manual design in editor campaign as "01"
      | KEY | Layer type | Layer name   | Custom name     |
      | 01  | Text field | Text layer 1 | Option manual 1 |
      | 01  | Image      | FB1          | Option manual 2 |
    And add to Additional Materials as "01"
      | KEY | File Name  | File     | Applied For     |
      | 01  | Material 1 | BD_2.png | Option manual 1 |
    And click Save change on Edit personalize
    And user navigate to "Campaigns" screen
    And verify result after implement action as "01"
      | KEY | Label name | Campaign name                       |
      | 01  | Available  | Camp personalize switch camp manual |
    And quit browser



