Feature: Homepage - Image with text v2

#  Theme inside
#  Env:
#  insidev2_imagewithtext

  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove section Image With Text
    When remove all section "Image With Text"
    And verify show "Image With Text" section on storefront is "false"


  Scenario Outline: Add section Image With Text and Remove blocks
    When add section "Image With Text"
    And save change setting
    When remove block in "Image With Text" section "<KEY>"
      | KEY | Block name      |
      | 1   | Image With Text |
    And save change setting
    Given open shop on storefront
    Then verify not show block in "Image With Text" section on SF "<KEY>"
      | KEY | Name block      |
      | 1   | Image With Text |
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Image With Text" section
      | Block name      |
      | Image With Text |
      | Image With Text |
      | Image With Text |
    And save change setting

  Scenario Outline: Verify section and block Image with text setting
    Then setting section Image With Text "<KEY>"
      | KEY | Layout  | Full width section | Text alignment |
      | 1   | Box     |                    | Center         |
      | 2   | Mix     | false              | Left           |
      | 3   | Default | true               | Left           |
      | 4   | Grid    | true               | Center         |
    Then setting block in section Image With Text "<KEY>"
      | KEY | Index block | Image           | Alt text | Image link        | Heading   | Text                                 | Button label | Button link       | Button type |
      | 1   | 1           | front/logo2.jpg | Alt 1    | Collections>Dress | Heading 1 | Promotion description appears here 1 | View all     | Collections>Dress | Button      |
      | 1   | 2           | front/logo4.png | Alt 2    |                   | Heading 3 | Promotion description appears here 2 | View all     |                   | Text link   |
      | 1   | 3           | front/logo3.png | Alt 3    |                   |           |                                      |              |                   | Button      |
      | 2   | 1           |                 |          | Collections>Dress |           | Promotion description appears here 3 | Shop         | Products>Mug      | Text link   |
      | 3   | 1           | front/logo4.png |          |                   | Heading 4 | Promotion description appears here 4 | Shop now     |                   | Text link   |
      | 4   | 1           | front/logo5.png | Alt 5    | Products>Mug      |           |                                      |              |                   | Text link   |
    And save change setting

    Given open shop on storefront
    Then verify Image With Text section on storefront "<KEY>"
      | KEY | Index block | Layout  | Full width section | Text alignment | Image     | Alt text | Image link                | Heading   | Text                                 | Button label | Button link               | Button type |
      | 1   | 1           | Box     |                    | Center         | logo2.jpg | Alt 1    | /collections/dress        | Heading 1 | Promotion description appears here 1 | View all     | /collections/dress        | true        |
      | 1   | 2           |         |                    |                | logo4.png | Alt 2    |                           | Heading 3 | Promotion description appears here 2 | View all     |                           | false       |
      | 1   | 3           |         |                    |                | logo3.png | Alt 3    |                           |           |                                      |              |                           | false       |
      | 2   | 1           | Mix     | false              | Left           |           |          | /collections/dress        |           | Promotion description appears here 3 | Shop         | /products/novelty-cat-mug | false       |
      | 3   | 1           | Default | true               | Left           | logo4.png |          |                           | Heading 4 | Promotion description appears here 4 | Shop now     |                           | false       |
      | 4   | 1           | Grid    |                    | Center         | logo5.png | Alt 5    | /products/novelty-cat-mug |           |                                      |              |                           | false       |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |

