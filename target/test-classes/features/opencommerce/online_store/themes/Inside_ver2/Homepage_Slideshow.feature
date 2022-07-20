Feature: Homepage - Slideshow v2

#  Theme inside
#  Env:
#  insidev2_slideshow

  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario Outline: Remove block Slide
    When remove block in "Slideshow" section "<KEY>"
      | KEY | Block name |
      | 1   | Slide      |
    And save change setting
    Given open shop on storefront
    Then verify not show block in "Slideshow" section on SF "<KEY>"
      | KEY | Block name |
      | 1   | Slide      |
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Slideshow" section
      | Block name |
      | Slide      |
    And save change setting

  Scenario Outline: Verify section and block Sideshow setting
    Then setting section Slideshow "<KEY>"
      | KEY | Layout | Full width on desktop | Ratio           | Change slides every | Opacity | Enable parallax scrolling |
      | 1   | Single | true                  | Fit first slide | 0%                  | 35%     | true                      |
      | 2   | Box    | false                 | 21:9            | 37.5%               |         | false                     |
      | 3   | Box    | true                  | 16:9            | 100%                |         | true                      |
      | 4   | Box    | false                 | 3:1             | 12.5%               |         | false                     |

    Then setting block in section Slideshow "<KEY>"
      | KEY | Index block | Image             | Alt text | Image URL          | Background color | Headline   | Text                                 | Text alignment | First button label | First button link | Second button label | Second button link |
      | 1   | 1           | front/slide2.png  | Alt 1    | Collections>Dress  |                  | Headline 1 | Promotion description appears here 1 | Center         | View all           | Collections>Dress | Shop more           | Products>Mug       |
      | 2   | 1           |                   |          | Collections>Bikini | #194d33          | Headline 2 | Promotion description appears here 3 | Right          | Shop               | Products>Mug      |                     |                    |
      | 3   | 1           | front/slide2.png  |          |                    | #5B6DE0          | Headline 4 | Promotion description appears here 4 | Center         | Shop now           |                   | View                |                    |
      | 4   | 1           | front/slide1.jpeg | Alt 5    | Products>Mug       |                  |            |                                      | Right          |                    |                   |                     |                    |
    And save change setting
    Given open shop on storefront
    Then verify Slideshow section on storefront "<KEY>"
      | KEY | Index block | Layout | Full width on desktop | Ratio           | Change slides every | Opacity | Enable parallax scrolling | Image       | Alt text | Image URL                 | Background color | Headline   | Text                                 | Text alignment | First button label | First button link         | Second button label | Second button link        |
      | 1   | 1           | Single | true                  | Fit first slide | 4sec                | 0.35    | true                      | slide2.png  | Alt 1    | /collections/dress        |                  | Headline 1 | Promotion description appears here 1 | Center         | View all           | /collections/dress        | Shop more           | /products/novelty-cat-mug |
      | 2   | 1           | Box    | false                 | 21:9            | 12sec               |         | false                     |             |          | /collections/bikini       | #194d33          | Headline 2 | Promotion description appears here 3 | Right          | Shop               | /products/novelty-cat-mug |                     |                           |
      | 3   | 1           | Box    | true                  | 16:9            | 12sec               |         | true                      | slide2.png  |          |                           | #5B6DE0          | Headline 4 | Promotion description appears here 4 | Center         | Shop now           |                           | View                |                           |
      | 4   | 1           | Box    | false                 | 3:1             | 12sec               |         | false                     | slide1.jpeg | Alt 5    | /products/novelty-cat-mug | #FFFFFF          |            |                                      | Right          |                    |                           |                     |                           |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |