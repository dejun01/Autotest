Feature: Clipart Folder
#EVM: sbase_clipart_folder
#Pre-condition
  Scenario: Delete all folder
    And Delete all folders by API

#Create clipart folder
  Scenario Outline: Create clipart folder
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Library>Cliparts" screen
    And click to button "Create folder"
    And create info clipart folder as "<KEY>"
      | KEY | Folder           | Group         | Image         |
      | 01  |                  |               |               |
      | 02  | ClipartFolder001 | groupFolder00 | BD_2.png      |
      | 03  | ClipartFolder002 |               | phub/BD_8.png |
      | 04  | ClipartFolder003 | groupFolder00 | phub/BD_8.png |
      | 05  | ClipartFolder004 | groupFolder00 | phub/BD_8.png |
      | 06  | ClipartFolder005 | groupFolder00 | phub/BD_8.png |
      | 07  | ClipartFolder006 | groupFolder00 | phub/BD_8.png |
    And edit info clipart folder in dashboard as "<KEY>"
      | KEY | Current name | Name update | Thumbnail update | Image |
      | 04  | BD_8         | image       |                  |       |
      | 05  | BD_8         | image       | phub/BD_4.png    | image |
      | 06  | BD_8         | image       | phub/BD_4.png    | image |
      | 07  | BD_8         | image       | phub/BD_4.png    | image |
    And delete info clipart folder as "<KEY>"
      | KEY | ImageThumbnail |
      | 06  | image          |
    And click to button save change as "<KEY>"
      | KEY   |
      | <KEY> |
    Then verify information in clipart detail as "<KEY>"
      | KEY | Folder           | Group         | Image |
      | 02  | ClipartFolder001 | groupFolder00 | BD_2  |
      | 03  | ClipartFolder002 |               | BD_8  |
      | 04  | ClipartFolder003 | groupFolder00 | image |
      | 05  | ClipartFolder004 | groupFolder00 | image |
      | 06  | ClipartFolder005 | groupFolder00 | image |
      | 07  | ClipartFolder006 | groupFolder00 | image |
    And quit browser
    Examples:
      | KEY | Testcase                                          |
      | 01  | Create Group                                      |
      | 02  | Create clipart folder with all field valid        |
      | 03  | Create clipart folder with require field          |
      | 04  | Create clipart folder with change name image      |
      | 05  | Create clipart folder with upload image thumbnail |
      | 06  | Create clipart folder with delete image thumbnail |
      | 07  | Create clipart folder with change image Thumbnail |

#Edit clipart folder
  Scenario Outline: Edit clipart folder
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Library>Cliparts" screen
    And select any clipart folder
    And delete value in name field
    And click delete all image
    And create info clipart folder as "<KEY>"
      | KEY | Folder           | Group         | Image         |
      | 01  |                  |               |               |
      | 02  | ClipartFolder001 | groupFolder00 | phub/BD_3.png |
      | 03  | ClipartFolder002 |               | phub/BD_8.png |
      | 04  | ClipartFolder003 | groupFolder00 | phub/BD_8.png |
      | 05  | ClipartFolder004 | groupFolder00 | phub/BD_8.png |
      | 06  | ClipartFolder005 | groupFolder00 | phub/BD_8.png |
      | 07  | ClipartFolder006 | groupFolder00 | phub/BD_8.png |
    And edit info clipart folder in dashboard as "<KEY>"
      | KEY | Current name | Name update | Thumbnail update | Image |
      | 04  | BD_8         | image       | phub/BD_4.png    | image |
      | 05  | BD_8         | image       | phub/BD_4.png    | image |
      | 06  | BD_8         | image       | phub/BD_4.png    | image |
      | 07  | BD_8         | image       | phub/BD_4.png    | image |
    And delete info clipart folder as "<KEY>"
      | KEY | ImageThumbnail |
      | 06  | image          |
    And click to button save change as "<KEY>"
      | KEY   |
      | <KEY> |
    Then verify information in clipart detail as "<KEY>"
      | KEY | Folder           | Group         | Image |
      | 02  | ClipartFolder001 | groupFolder00 | BD_3  |
      | 03  | ClipartFolder002 |               | BD_8  |
      | 04  | ClipartFolder003 | groupFolder00 | image |
      | 05  | ClipartFolder004 | groupFolder00 | image |
      | 06  | ClipartFolder005 | groupFolder00 | image |
      | 07  | ClipartFolder006 | groupFolder00 | image |
    And quit browser
    Examples:
      | KEY | Testcase                                        |
      | 01  | Create Group                                    |
      | 02  | Edit clipart folder with all field valid        |
      | 03  | Edit clipart folder with require field          |
      | 04  | Edit clipart folder with change name image      |
      | 05  | Edit clipart folder with upload image thumbnail |
      | 06  | Edit clipart folder with delete image thumbnail |
      | 07  | Edit clipart folder with change image Thumbnail |

#List clipart folder
  Scenario Outline: List clipart folder
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And user navigate to "Library>Cliparts" screen
    And assign Group for clipart as "<KEY>"
      | KEY | Group1       | Group2           | Group3       |
      | 01  | Baseball new |                  |              |
      | 02  |              | Landscape Puzzle |              |
      | 03  |              |                  | Beverage Mug |
    And delete clipart folder as "<KEY>"
      | KEY | 04   | 05   |
      | 04  | true |      |
      | 05  |      | true |
    And quit browser
    Examples:
      | KEY | Testcase                                          |
      | 01  | Assign 1 folder                                   |
      | 02  | Assign all folder                                 |
      | 03  | Assign Group not success with click Cancel button |
      | 04  | Delete a folder                                   |
      | 05  | Delete folder not success when click cancel       |