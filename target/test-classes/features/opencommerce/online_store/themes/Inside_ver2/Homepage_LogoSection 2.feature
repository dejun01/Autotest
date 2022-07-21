Feature: HomePage - Logo Section
#insidev2_logo


  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove and Add section Logo
    When remove all section "Logo"
    And verify show "Logo" section on storefront is "false"
    When add section "Logo"
    And save change setting


  Scenario Outline: Remove block Logo
    When remove block in "Logo" section "<KEY>"
      | KEY | Block name |
      | 1   | Logo       |
    And save change setting
    Given open shop on storefront
    Then verify not show block in "Logo" section on SF "<KEY>"
      | KEY | Name block |
      | 1   | Logo       |
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Logo" section
      | Block Name |
      | Logo       |
      | Logo       |
      | Logo       |
    And save change setting


  Scenario Outline: Verify section and block Logo setting
    Then setting section Logo "<KEY>"
      | KEY | Heading     | Text alignment |
      | 1   | Logo list   | Center         |
      | 2   | Logo list 1 | Left           |
      | 3   | Logo list 2 | Right          |
    Then setting block in section Logo "<KEY>"
      | KEY | Index block | Image           | Alt text | Image link                  |
      | 1   | 1           | front/logo2.jpg | Logo1    | Collections>All Collections |
      | 1   | 2           |                 | Logo2    |                             |
      | 1   | 3           | front/logo3.png | Logo3    |                             |
      | 2   | 1           | front/logo2.jpg | Logo4    | Collections>All Collections |
      | 3   | 1           | front/logo4.png | Logo5    |                             |
    And save change setting
    Given open shop on storefront
    Then verify Logo section on storefront as "<KEY>"
      | KEY | Index block | Heading     | Text alignment | Image           | Alt text | Image link   |
      | 1   | 1           | Logo list   | Center         | front/logo2.jpg | Logo1    | /collections |
      | 1   | 2           |             |                |                 | Logo2    |              |
      | 1   | 3           |             |                | front/logo3.png | Logo3    |              |
      | 2   | 1           | Logo list 1 | Left           | front/logo2.jpg | Logo4    | /collections |
      | 3   | 1           | Logo list 2 | Right          | front/logo4.png | Logo5    |              |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
