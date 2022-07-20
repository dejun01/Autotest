Feature: Clipart folder validate
#pbase_clipart_folder
  Scenario Outline: Validate clipart folder when create clipart
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Library>Cliparts" screen
    And click to button "Create folder"
    And create info clipart folder as "<KEY>"
      | KEY | Folder        | Group         | Image         |
      | 01  |               | groupFolder00 | phub/BD_8.png |
      | 02  | ClipartFolder | groupFolder00 |               |
      | 03  |               | groupFolder00 |               |
      | 04  | ClipartFolder | groupFolder00 | phub/BD_8.png |
    And click to button save change as "<KEY>"
      | KEY   |
      | <KEY> |
    Then verify display message ERROR as "<KEY>"
      | KEY | Folder                       | Clipart                                      | Image |
      | 01  | Please fill the folder name. |                                              |       |
      | 02  |                              | Please upload your cliparts for this folder. |       |
      | 03  | Please fill the folder name. | Please upload your cliparts for this folder. |       |
    And quit browser
    Examples:
      | KEY | Testcase                      |
#      | 01  | Create clipart folder with name field empty |
#      | 02  | Create clipart folder with not upload image |
#      | 03  | Create clipart folder with only Group       |
      | 04  | Create clipart folder success |

#Edit clipart folder
  Scenario Outline: Validate clipart folder when edit clipart
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard
    And user navigate to "Library>Cliparts" screen
    And select any clipart folder
    And delete value in name field
    And click delete all image
    And create info clipart folder as "<KEY>"
      | KEY | Folder        | Group         | Image         |
      | 01  |               | groupFolder00 | phub/BD_8.png |
      | 02  | ClipartFolder | groupFolder00 |               |
      | 03  |               | groupFolder00 |               |
      | 04  | ClipartFolder | groupFolder00 | phub/BD_8.png |
    And edit info clipart folder in dashboard as "<KEY>"
      | KEY | Current name |
      | 04  | BD_8         |
    And click to button save change as "<KEY>"
      | KEY   |
      | <KEY> |
    Then verify display message ERROR as "<KEY>"
      | KEY | Folder                       | Clipart                                      | Image                         |
      | 01  | Please fill the folder name. |                                              |                               |
      | 02  |                              | Please upload your cliparts for this folder. |                               |
      | 03  | Please fill the folder name. | Please upload your cliparts for this folder. |                               |
      | 04  |                              |                                              | Please fill the clipart name. |
    And quit browser
    Examples:
      | KEY | Testcase                                  |
      | 01  | Edit clipart folder with name field empty |
      | 02  | Edit clipart folder with not upload image |
      | 03  | Edit clipart folder with only Group       |
#      | 04  | Edit clipart folder with name image empty |