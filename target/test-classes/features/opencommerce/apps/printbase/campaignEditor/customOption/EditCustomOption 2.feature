Feature: Edit Custom option
#pbase_edit_custom_option

  Scenario Outline: Create campaign #SB_PRB_UXP_1 #SB_PRB_UXP_2 #SB_PRB_UXP_5 #SB_PRB_UXP_4 #SB_PRB_UXP_6 #SB_PRB_UXP_7 #SB_PRB_UXP_8 #SB_PRB_UXP_17
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And delete all campaigns by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product     | Category |
      | 01  | Unisex Tank | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Layer name | Front or back |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Text       |            | Front         |
      | 01  | Unisex Tank | Image      | 39.png     | Front         |
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name  | Font    | Placeholder | Max length | Default value |
      | 01  | Text field | Text layer 2 | Custom text  | Raleway | Input text  | 10         | text          |
      | 01  | Text area  | Text layer 3 | Custom area  | Raleway | Input text  | 10         | area          |
      | 01  | Image      | 39           | Custom Image |         |             |            |               |
    And click button expand on Custom Option
    Then setup custom option
      | Layer type | Layer name   | Custom name | Value | Font      |
      | Radio      | Text layer 1 | Radio       | 1,2   | Open Sans |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    And close browser
    Examples:
      | KEY | Testcase                | Campaign name               |
      | 01  | Create camp personalize | Campaign sample personalize |


  Scenario Outline: Edit custom option after create campaign #SB_PRB_ff_1 #SB_PRB_ff_3 #SB_PRB_ff_4 #SB_PRB_ff_5 #SB_PRB_ff_6 #SB_PRB_ff_15 #SB_PRB_ff_16 #SB_PRB_ff_17 #SB_PRB_ff_18 #SB_PRB_ff_20 #SB_PRB_ff_13 #SB_PRB_ff_11
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    And search product or campaign or orders "Campaign sample personalize" at list page in dashboard
    And open camp detail
      | Title                       |
      | Campaign sample personalize |
    And click on Edit personalization btn
    And edit custom option
      | Custom option | Custom name       | Font        | Placeholder | Max length | Default value |
      | Custom text   | Edit custom text  | Roboto Mono | Input text  | 16         | Thuy Linh     |
      | Custom area   | Edit custom area  | Roboto Mono | Input text  | 16         | Nguyen Linh   |
      | Custom Image  | Edit custom image |             |             |            |               |
    And set conditional logic
      | Custom option | Value | Custom name                       |
      | Radio         | 1,2   | Edit custom text,Edit custom area |
    And click Save change on Edit personalize
    And click to button "Confirm"
    And click Save change on Edit personalize
    And open product details on Storefront from product detail in dashboard as "<KEY>"
      | KEY |
      | 01  |
    And verify information campaign on Storefront as "<KEY>"
      | KEY | Type  | Custom name       | Value | Is check                     | Phone number     |
      | 01  | Radio | Radio             | 1     | Edit custom text>Thuy Linh   | 1-(408) 899-8879 |
      | 01  | Radio | Radio             | 2     | Edit custom area>Nguyen Linh |                  |
      | 01  | Image | Edit custom image |       |                              |                  |
    And quit browser
    Examples:
      | KEY | Testcase           |
      | 01  | Edit custom option |


  Scenario Outline: edit customize layer #SB_PRB_UXP_9 #SB_PRB_UXP_10 #SB_PRB_UXP_11 #SB_PRB_UXP_12 #SB_PRB_UXP_16 #SB_PRB_UXP_20
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And delete all campaigns by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product     | Category |
      | 01  | Unisex Tank | Apparel  |
    And add new layer as "<KEY>"
      | KEY | Product     | Layer type | Front or back |
      | 01  | Unisex Tank | Text       | Front         |
      | 01  | Unisex Tank | Text       | Front         |
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name | Font    | Placeholder | Max length | Default value |
      | 01  | Text field | Text layer 1 | Custom text | Raleway | Input text  | 10         | text          |
      | 01  | Text area  | Text layer 2 | Custom area | Raleway | Input text  | 10         | text          |
    And click button expand on Custom Option
    And edit custom option
      | Custom option | Custom name      | Font   | Placeholder | Action       |
      | Custom text   | Edit custom text |        |             | Cancel       |
      | Custom text   |                  |        | Test        | Back         |
      | Custom text   |                  | Roboto |             | Close expand |
      | Custom text   |                  |        | Text update | Continue     |
    And edit customize group or custom option
      | Name        | Action | Mesage target layer                                 |
      | Custom area | Delete |                                                     |
      | Custom text | Clone  | This layer is targeted for the other custom options |
    And quit browser
    Examples:
      | KEY | Testcase           |
      | 01  | Edit custom option |