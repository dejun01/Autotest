Feature: Homepage - RichText

#  Theme inside v2
#  Env:
#  insidev2_RichText
#  staging_insidev2_richtext

  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove & add section RichText
    When remove all section "Rich Text"
    And verify show "Rich Text" section on storefront is "false"
    When add section "Rich Text"
    And save change setting
    Then close browser

  Scenario Outline: Remove block
    When remove block in "Rich Text" section "<KEY>"
      | KEY | Name block |
      | 1   | Heading    |
      | 2   | Text       |
      | 3   | Button     |
    And save change setting
    Given open shop on storefront
    Then verify not show block in "Rich Text" section on SF "<KEY>"
      | KEY | Name block | Name block SF |
      | 1   | Heading    | title         |
      | 2   | Text       | subtitle      |
      | 3   | Button     | action        |
    Then close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario: Add block
    And add block in "Rich Text" section
      | Block name |
      | Heading    |
      | Text       |
      | Button     |
    Then save change setting
    Then close browser

  Scenario Outline: Verify section and block RichText setting
    Then setting section Rich Text "<KEY>"
      | KEY | Heading position    | Text alignment |
      | 1   | In margin with text | Center         |
      | 2   | In margin with text | Left           |
      | 3   | Above the text      | Center         |
    Then setting block "Heading" in Rich Text section "<KEY>"
      | KEY | Heading            |
      | 1   | Rich Text shipping |
      | 2   |                    |
      | 3   | Rich Text          |
    Then setting block "Text" in Rich Text section "<KEY>"
      | KEY | Text                                                      |
      | 1   | Shipping costs vary depending on the shipping destination |
      | 2   |                                                           |
      | 3   | We provide worldwide shipping                             |
    Then setting block "Button" in Rich Text section "<KEY>"
      | KEY | Button type         | Label                 | Button link                 |
      | 1   | Text button         | View our impressive   | Collections>All Collections |
      | 2   | Text button         | View our impressive 1 |                             |
      | 3   | Display as a button |                       | Homepage                |
    Then save change setting
    Given open shop on storefront
    Then verify Rich Text section on storefront "<KEY>"
      | KEY | Heading position    | Text alignment | Heading            | Text                                                      | Button type         | Label                 | Button link                 |
      | 1   | In margin with text | Center         | Rich Text shipping | Shipping costs vary depending on the shipping destination | Text button         | View our impressive   | /collections |
      | 2   | In margin with text | Left           |                    |                                                           | Text button         | View our impressive 1 |                             |
      | 3   | Above the text      | Center         | Rich Text          | We provide worldwide shipping                             | Display as a button |                       | Products>Mug                |
    Then close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |