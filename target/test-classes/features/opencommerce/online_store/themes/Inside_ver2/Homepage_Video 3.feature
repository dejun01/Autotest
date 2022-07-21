Feature: Homepage - Video slider v2

 # Env:
 #insidev2_videoslider

  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove section Video slider
    When remove all section "Video Slider"
    And save change setting
    And verify show "Video Slider" section on storefront is "false"

  Scenario: Add section Video slider
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"
    When add section "Video Slider"
    And save change setting

  Scenario Outline: Remove block Video slider
    When remove block in "Video Slider" section "<KEY>"
      | KEY | Block name |
      | 1   | Video      |
    And save change setting
    Given open shop on storefront
    Then verify not show block in "Video Slider" section on SF "<KEY>"
      | KEY | Block name |
      | 1   | Video      |
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Video Slider" section
      | Block Name |
      | Video      |
      | Video      |
    And save change setting

  Scenario Outline: Verify section and block Video slider setting
    Then setting section Video slider "<KEY>"
      | KEY | Full width video | Autoplay video |
      | 1   | true             | false          |
      | 2   | false            | true           |
    Then setting block in section Video slider "<KEY>"
      | KEY | Video link                                  | Thumbnail        | Alt text | Heading   | Sub heading   | Display soiled text background | Text position | Text alignment |
      | 1   | https://www.youtube.com/watch?v=xP3kSzB-IE8 | /front/logo2.jpg | Video 1  | HEADING 1 | SUB HEADING 1 | false                          | Left          | Right          |
      | 1   | https://www.youtube.com/watch?v=awnn-bqV_Tw | /front/logo3.png | Video 2  | HEADING 2 | SUB HEADING 2 | true                           | Center        | Center         |
      | 2   | https://www.youtube.com/watch?v=AQm5pzSd8ww | /front/logo4.png | Video 3  | HEADING 3 | SUB HEADING 3 | true                           | Right         | Left           |
    And save change setting

    Given open shop on storefront
    And verify section Video settings on storefront as "<KEY>"
      | KEY | Autoplay video | Full width section | Video link  | Image            | Alt text | Heading   | Subheading    | Display solid text background | Text position | Text alignment |
      | 1   | false          | true               | xP3kSzB-IE8 | /front/logo2.jpg | Video 1  | HEADING 1 | SUB HEADING 1 | false                         | Left          | Right          |
      | 1   | false          | true               | awnn-bqV_Tw | /front/logo3.png | Video 2  | HEADING 2 | SUB HEADING 2 | true                          | Center        | Center         |
      | 2   | true           | false              | AQm5pzSd8ww | /front/logo4.png | Video 3  | HEADING 3 | SUB HEADING 3 | true                          | Right         | Left           |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
