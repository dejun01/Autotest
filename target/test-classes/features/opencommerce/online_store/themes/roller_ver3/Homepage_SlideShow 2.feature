Feature: Homepage - SlideShow

#  sbase_roller_ver3
#  Env:
#  sbase_rollerver3_banner

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove and Add section banner
    When remove all section "Slideshow"
    Then open shop on storefront
    And verify show "Slideshow" section on storefront is "false"
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"
    When add section "Slideshow"
    And save change setting

  Scenario Outline: Remove block Slideshow
    When remove block in "Slideshow" section "<KEY>"
      | KEY | Name block |
      | 1   | Slide      |
    And save change setting
    Given open shop on storefront
    Then verify not show block in "Slideshow" section on SF "<KEY>"
      | KEY | Name block |
      | 1   | Slide      |
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Slideshow" section
      | Name block |
      | Slide      |
      | Slide      |
      | Slide      |
    And save change setting

  Scenario Outline: Verify section and block Slideshow setting
    And setting section Slideshow "<KEY>"
      | KEY | Text animation | Gallery transition | Ratio | Opacity | Change slides every | Enable parallax scrolling |
      | 1   | Fade in        | Fade               | 20:9  | 33%     | 6                   | true                      |
      | 2   | None           | Slide              | 3:1   | 0%      | 8                   | false                     |
      | 3   | Fade up        | Fade               | 4:1   | 66%     | 10                  | true                      |
      | 4   | Fade down      | Slide              | 16:9  | 55%     | 12                  | true                      |
    And setting block Slideshow "<KEY>"
      | KEY | Index block | Background image | Alt text | Preheading      | Heading   | Subheading                   | Text position | Text alignment | Image link                  | First button label | First button link           | Highlight first button label | Second button label | Second button link          | Highlight second button label |
      | 1   | 1           | front/logo2.jpg  | Alt 1    | Introducing The | Heading 1 | Exclusively From Shop Base 1 | Center        | Left           | Collections>All Collections | Shop Men           | Products>All Products       | true                         | Shop Women          | Collections>All Collections | true                          |
      | 1   | 2           | front/logo4.png  | Alt 2    |                 | Heading 3 | Exclusively From Shop Base 2 | Left          | Center         |                             |                    | Products>All Products       | true                         | Shop Women          | Collections>All Collections | false                         |
      | 1   | 3           | front/logo3.png  | Alt 3    | Introducing     |           |                              | Right         | Right          | Collections>All Collections | Shop Men           |                             | false                        |                     |                             | true                          |
      | 2   | 1           |                  |          | Introducing The |           | Exclusively From Shop Base 3 | Center        | Left           | Products>All Products       |                    | Collections>All Collections | false                        | Shop Women          | Collections>All Collections | false                         |
      | 3   | 1           | front/logo4.png  |          |                 | Heading 4 | Exclusively From Shop Base 4 | Left          | Center         |                             | Shop Men           | Products>All Products       | false                        |                     | Products>All Products       | true                          |
      | 4   | 1           | front/logo5.png  | Alt 5    | Introducing     |           |                              | Right         | Right          | Products>All Products       | Shop Men           |                             | true                         | Shop Women          |                             | true                          |
    Then save change setting
    Given open shop on storefront
    Then verify Slideshow on storefront "<KEY>"
      | KEY | Index block | Text animation | Gallery transition | Ratio | Opacity | Change slides every | Enable parallax scrolling | Background image | Alt text | Preheading      | Heading   | Subheading                   | Text position | Text alignment | Image link       | First button label | First button link | Highlight first button label | Second button label | Second button link | Highlight second button label |
      | 1   | 1           | fade-in        | fade               | 20:9  | 0.3     | 6                   | true                      | front/logo2.jpg  | Alt 1    | Introducing The | Heading 1 | Exclusively From Shop Base 1 | center        | left           | /collections     | Shop Men           | /collections/all  | true                         | Shop Women          | /collections       | true                          |
      | 1   | 2           |                |                    |       |         |                     |                           | front/logo4.png  | Alt 2    |                 | Heading 3 | Exclusively From Shop Base 2 | left          | center         |                  |                    | /collections/all  | true                         | Shop Women          | /collections       | false                         |
      | 1   | 3           |                |                    |       |         |                     |                           | front/logo3.png  | Alt 3    | Introducing     |           |                              | right         | right          | /collections     | Shop Men           |                   | false                        |                     |                    | true                          |
      | 2   | 1           | None           | slide              | 3:1   | 0       | 8                   | false                     |                  |          | Introducing The |           | Exclusively From Shop Base 3 | center        | left           | /collections/all |                    | /collections      | false                        | Shop Women          | /collections       | false                         |
      | 3   | 1           | fade-up        | fade               | 4:1   | 0.66    | 10                  | true                      | front/logo4.png  |          |                 | Heading 4 | Exclusively From Shop Base 4 | left          | center         |                  | Shop Men           | /collections/all  | false                        |                     | /collections/all   | true                          |
      | 4   | 1           | fade-down      | slide              | 16:9  | 0.55    | 12                  | true                      | front/logo5.png  | Alt 5    | Introducing     |           |                              | right         | right          | /collections/all | Shop Men           |                   | true                         | Shop Women          |                    | true                          |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |


