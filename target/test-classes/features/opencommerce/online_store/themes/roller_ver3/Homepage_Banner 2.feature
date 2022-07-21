Feature: Homepage - Banner

#  sbase_roller_ver3
#  Env:
#  sbase_rollerver3_header

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove and Add section banner
    When remove all section "Banner"
    Then open shop on storefront
    And verify show "Banner" section on storefront is "false"
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"
    When add section "Banner"
    And save change setting

  Scenario Outline: Verify section and block Banner setting
    Then setting section Banner "<KEY>"
      | KEY | Image             | Alt text | Ratio | Opacity | Enable parallax scrolling | Preheading      | Heading               | Subheading                | Image link                  | Primary button label | Primary button link         | Highlight first button label | Secondary button label | Secondary button link       | Highlight second button label | Text position | Text alignment |
      | 1   | front/Banner.jpg  | banner1  | 20:9  | 33%     | true                      | Introducing The | roller shopbase theme | exclusively from shopbase | Collections>All Collections | Shop Women           | Collections>All Collections | true                         | Shop Men               | Products>All Products       | true                          | Left          | Centre         |
      | 2   | front/Banner2.jpg |          | 3:1   | 0%      | false                     | Introducing     |                       |                           | Products>All Products       |                      |                             | true                         | Shop Men               | Collections>All Collections | false                         | Right         | Left           |
      | 3   | front/Banner2.jpg | banner2  | 4:1   | 66%     | true                      |                 | shopbase theme        | exclusively               |                             | Shop Women           | Products>All Products       | false                        |                        |                             | false                         | Centre        | Center         |
    Then save change setting
    Given open shop on storefront
    Then verify banner section on storefront "<KEY>"
      | KEY | Image             | Alt text | Ratio | Opacity | Enable parallax scrolling | Preheading      | Heading               | Subheading                | Image link       | Primary button label | Primary button link | Highlight first button label | Secondary button label | Secondary button link | Highlight second button label | Text position | Text alignment |
      | 1   | front/Banner.jpg  | banner1  | 20:9  | 0.33    | true                      | Introducing The | roller shopbase theme | exclusively from shopbase | /collections     | Shop Women           | /collections        | true                         | Shop Men               | /collections/all      | true                          | left          | center         |
      | 2   | front/Banner2.jpg |          | 3:1   | 0       | false                     | Introducing     |                       |                           | /collections/all |                      |                     | true                         | Shop Men               | /collections          | false                         | right         | left           |
      | 3   | front/Banner2.jpg | banner2  | 4:1   | 0.66    | true                      |                 | shopbase theme        | exclusively               |                  | Shop Women           | /collections/all    | false                        |                        |                       | false                         | center        | center         |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |



