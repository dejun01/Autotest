Feature: Homepage - Banner

#  Theme inside v2
#  Env:
#  insidev2_banner

  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove and Add section banner
    When remove all section "Banner"
    And verify show "Banner" section on storefront is "false"
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"
    When add section "Banner"
    And save change setting

  Scenario Outline: Remove block
    When remove block in "Banner" section "<KEY>"
      | KEY | Block name |
      | 1   | Heading    |
      | 1   | Text       |
      | 1   | Button     |
    And save change setting
    Given open shop on storefront
    Then verify not show block in "Banner" section on SF "<KEY>"
      | KEY | Name block | Name block SF |
      | 1   | Heading    | title         |
      | 1   | Text       | subtitle      |
      | 1   | Button     | action        |
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Banner" section
      | Block name |
      | Heading    |
      | Text       |
      | Button     |
    And save change setting

  Scenario Outline: Verify section and block Banner setting
    Then setting section Banner "<KEY>"
      | KEY | Image             | Image link               | Alt text | Opacity | Text position | Text alignment | Full width section |
      | 1   | front/Banner.jpg  | Policies>Shipping policy | banner1  | 33%     | Left          | Center         | true               |
      | 2   |                   |                          |          | 0%      | Right         | Left           | true               |
      | 3   | front/Banner2.jpg |                          | banner2  | 66%     | Center        | Right          | false              |
    Then setting block "Heading" in Banner section "<KEY>"
      | KEY | Heading         |
      | 1   | Banner shipping |
      | 2   |                 |
      | 3   | Banner          |
    Then setting block "Text" in Banner section "<KEY>"
      | KEY | Text                                                      |
      | 1   | Shipping costs vary depending on the shipping destination |
      | 2   | We provide worldwide shipping                             |
      | 3   | Shipping costs vary depending on the shipping destination |
    Then setting block "Button" in Banner section "<KEY>"
      | KEY | Primary button label | Primary button link | Secondary button label | Secondary button link |
      | 1   | Show collection      | Collections>Dress   | View product           | Products>Mug          |
      | 2   | View collection      |                     | Show product           |                       |
      | 3   |                      |                     | View product           | Products>Mug          |
    Then save change setting
    Given open shop on storefront
    Then verify banner section on storefront "<KEY>"
      | KEY | Image       | Image link                | Alt text | Opacity | Text position | Text alignment | Full width section | Heading         | Text                                                      | Primary button label | Primary button link | Secondary button label | Secondary button link     |
      | 1   | Banner.jpg  | /policies/shipping-policy | banner1  | 0.33    | left          | center         | true               | Banner shipping | Shipping costs vary depending on the shipping destination | Show collection      | /collections/dress  | View product           | /products/novelty-cat-mug |
      | 2   |             |                           |          | 0       | right         | left           | true               |                 | We provide worldwide shipping                             | View collection      | /                   | Show product           | /                         |
      | 3   | Banner2.jpg |                           | banner2  | 0.66    | center        | right          | false              | Banner          | Shipping costs vary depending on the shipping destination |                      | /                   | View product           | /products/novelty-cat-mug |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |



