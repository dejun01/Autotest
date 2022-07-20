Feature: Homepage - NewsletterV2

 # Env:
 #insidev2_newsletter


  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario: Remove and Add section Newsletter
    When remove all section "Newsletter"
    And save change setting
    And verify show "Newsletter" section on storefront is "false"
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Home"
    When add section "Newsletter"
    And save change setting

  Scenario Outline: Verify section and block Newsletter setting
    And open section setting "Newsletter"
    And change Newsletter settings section as "<KEY>"
      | KEY | Text alignment | Full width on desktop | Image                 |
      | 1   | Left           | true                  | /front/Newsletter.png |
      | 2   | Center         | false                 |                       |
    And setting block "Heading" in Newsletter section "<KEY>"
      | KEY | Heading                     |
      | 1   | Subscribe to our newsletter |
      | 2   | Heading newsletter          |
    And setting block "Text" in Newsletter section "<KEY>"
      | KEY | Text                        |
      | 1   | A short sentence describing |
      | 2   | Subheading newsletter       |
    And save change setting

    Given open shop on storefront
    And verify Newsletter settings as "<KEY>"
      | KEY | Heading                     | Subheading                  | Text alignment | Background image      | Full width section | Email                 | randomEmail | Messages                  |
      | 1   | Subscribe to our newsletter | A short sentence describing | Left           | /front/Newsletter.png | true               |                       | false       |                           |
      | 1   |                             |                             |                |                       |                    | shopbase              | false       |                           |
      | 1   |                             |                             |                |                       |                    | signup@mailtothis.com | false       | Thank you for subscribing |
      | 2   | Heading newsletter          | Subheading newsletter       | Center         |                       | false              |                       | true        | Thank you for subscribing |

    Given user login to shopbase dashboard by API
    When user navigate to "Customers" screen
    Then verify email subscribed on dashboard "<KEY>"
      | KEY | Email                 |
      | 1   | signup@mailtothis.com |
      | 2   |                       |
    Examples:
      | KEY |
      | 1   |
      | 2   |
