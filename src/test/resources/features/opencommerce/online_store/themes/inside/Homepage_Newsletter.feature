Feature: Homepage - Newsletter

#  Theme inside

#  Env:
#   dev_sbase_inside_newsletter
#   staging_sbase_inside_newsletter
#   prod_sbase_inside_newsletter

#  Data test

  Scenario Outline: verify Newsletter section #SB_OLS_THE_INS_13
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    And change Newsletter settings as "<KEY>"
      | KEY | Heading                     | Subheading                  | Full width section | Text alignment | Background image     |
      | 1   | Heading newsletter          | Subheading newsletter       | true               | Left           | front/Newsletter.png |
      | 2   | Subscribe to our newsletter | A short sentence describing | false              | Center         |                      |
    Given open shop on storefront
    And verify Newsletter settings as "<KEY>"
      | KEY | Heading                     | Subheading                  | Text alignment | Background image     | Full width section | Email                 | randomEmail | Messages                  |
      | 1   | Heading newsletter          | Subheading newsletter       | Left           | front/Newsletter.png | true               |                       | false       |                           |
      | 1   |                             |                             |                |                      |                    | shopbase              | false       |                           |
      | 1   |                             |                             |                |                      |                    | signup@mailtothis.com | false       | Thank you for subscribing |
      | 2   | Subscribe to our newsletter | A short sentence describing | Center         |                      | false              |                       | true        | Thank you for subscribing |
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