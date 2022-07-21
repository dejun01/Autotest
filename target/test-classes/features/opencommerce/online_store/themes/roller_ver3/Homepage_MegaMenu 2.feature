Feature: Homepage - Mega Menu

#  sbase_roller_ver3
#  Env:
#  sbase_rollerver3_header

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario Outline: Remove block
    When remove block in "Header" section "<KEY>"
      | KEY | Block name |
      | 1   | Mega Menu  |
    And save change setting
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Header" section
      | Block name |
      | Mega Menu  |
    And save change setting

  Scenario Outline: Mega Menu
    Then Setting Mega Menu "<KEY>"
      | KEY | Link title | Text area Top content   | Image Top content | Image caption Top content   | Image link Top content      | Image Bottom content | Image caption Bottom content   | Image link Bottom content   | Text area Bottom content   | Menu 1    | Menu 1 link                 |
      | 001 | Shop       | Text area Top content 1 | front/logo2.jpg   | Image caption Top content 1 | Collections>All Collections |                      | Image caption Bottom content 1 | Collections>All Collections | Text area Bottom content 1 | None      | Collections>All Collections |
      | 002 | Shop       |                         |                   |                             |                             | front/logo2.jpg      | Image caption Bottom content 2 | Collections>All Collections |                            | Main Menu |                             |
    And save change setting
    Given open shop on storefront
    Then verify mega menu on sf "<KEY>"
      | KEY | Link title | Text area Top content   | Image Top content | Image caption Top content   | Image link Top content      | Image Bottom content | Image caption Bottom content   | Image link Bottom content   | Text area Bottom content   | Menu 1    | Menu 1 link                 |
      | 001 | shop       | Text area Top content 1 | front/logo2.jpg   | Image caption Top content 1 | Collections>All Collections |                      | Image caption Bottom content 1 | Collections>All Collections | Text area Bottom content 1 | None      | Collections>All Collections |
      | 002 | shop       |                         |                   |                             |                             | front/logo2.jpg      | Image caption Bottom content 2 | Collections>All Collections |                            | Main Menu |                             |
    Examples:
      | KEY |
      | 001 |
      | 002 |




