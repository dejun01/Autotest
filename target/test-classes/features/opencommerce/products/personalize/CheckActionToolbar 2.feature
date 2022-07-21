@dashboardProduct @dashboard
  #  sbase_toolbar_personalize
Feature: Check action Tool bar of Personalize

  Background: Login dashboard
    When clear all data
    And Delete all products by API
    Given user login to shopbase dashboard
    Given user navigate to "Products>All products" screen
    And Add a new product with data
      | Title       | Description                                                            |
      | Product 001 | Made from 100% heavyweight cotton, this is the ultimate basic T-shirt. |


  Scenario: Verify action bar when all null
    And click button "Create Preview image" in product detail
    And verify action bar of editor personalize
      | Button toolbar                 |
      | Save>disable                   |
      | Next, create Print file>enable |
    And click button "Next, create Print file" on toolbar
    Then verify screen Printfile
    And verify action bar of editor personalize
      | Button toolbar              |
      | Save>disable                |
      | Next, create Preview>enable |
    And quit browser

  Scenario Outline: Verify action bar Preview
    And click button "Create Preview image" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
#  only image preview , no layer, no CO
    Then verify action bar of editor personalize
      | Button toolbar                 |
      | Save>disable                   |
      | Next, create Print file>Enable |
    Given add layer personalize as"<KEY>"
      | KEY | Layer type |
      | 01  | Text       |
# image preview , layer but no CO
    Then verify action bar of editor personalize
      | Button toolbar                 |
      | Save>disable                   |
      | Next, create Print file>Enable |
    Given setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name | Font    | Placeholder | Max length |
      | 01  | Text field | Text layer 1 | Custom text | Raleway | Input text  | 10         |
# image preview , layer , CO  error
    Then verify action bar of editor personalize
      | Button toolbar                  |
      | Save>Enable                    |
      | Next, create Print file>Enable |
    And quit browser
    Examples:
      | KEY |
      | 01  |

  Scenario Outline: Verify action bar Printfile
    And click button "Create Print file" in product detail
    When upload image for preview or printfile
      | Upload or Replace | Image         |
      | upload            | Campaign1.png |
#  only image preview , no layer, no CO
    And verify action bar of editor personalize
      | Button toolbar              |
      | Save>disable                |
      | Next, create Preview>Enable |
    And add layer personalize as"<KEY>"
      | KEY | Layer type | Layer name | Layer value | Font | Color | Location | Rotate | Opacity |
      | 01  | Text       |            | Test        | 20   |       | 30>40    | 30     | 100     |
      | 01  | Text       |            | Test        | 30   |       | 40>50    | 30     | 100     |
# image preview , layer but no CO
    And verify action bar of editor personalize
      | Button toolbar               |
      | Save>disable                 |
      | Next, create Preview>Enable |
    And setup custom option for campaign personalize in editor campaign as "<KEY>"
      | KEY | Layer type | Layer name   | Custom name | Font    | Placeholder | Max length |
      | 01  | Text field | Text layer 1 | Custom text | Raleway | Input text  | 10         |
# image preview , layer , CO  but have layer dont map with CO " Text layer 02"
    And verify action bar of editor personalize
      | Button toolbar              |
      | Save>Enable                 |
      | Next, create Preview>Enable |
    And quit browser
    Examples:
      | KEY |
      | 01  |