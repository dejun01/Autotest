Feature: HomePage - Logo Section, Featured Promotion

#prod_inside_logosection
#staging_inside_logosection
#dev_inside_logosection
  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"

  Scenario Outline: Verify Logo Section #SB_OLS_THE_INS_12
    And change Logo settings as "<KEY>"
      | KEY | Heading     | Text alignment | Image           | Alt text      | Image link                  |
      | 1   | Logo list   | Center         | front/logo2.jpg | This alt text | Collections>All Collections |
      | 1   |             |                |                 | This alt text |                             |
      | 1   |             |                | front/logo3.png | This alt text |                             |
      | 2   | Logo list 1 | Left           | front/logo2.jpg | This alt text | https://www.shopbase.com/   |
      | 3   | Logo list 2 | Right          | front/logo4.png | This alt text |                             |
    Given open shop on storefront
    Then verify Logo settings on storefront as "<KEY>"
      | KEY | Heading     | Text alignment | Image           | Alt text      | Link                      |
      | 1   | Logo list   | Center         | front/logo2.jpg | This alt text | /collections              |
      | 1   |             |                |                 | This alt text |                           |
      | 1   |             |                | front/logo3.png | This alt text |                           |
      | 2   | Logo list 1 | Left           | front/logo2.jpg | This alt text | https://www.shopbase.com/ |
      | 3   | Logo list 2 | Right          | front/logo4.png | This alt text |                           |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify Featured Promotion #SB_OLS_THE_INS_10
    And change Featured Promotion settings as "<KEY>"
      | KEY | Full width section | Image           | Alt text        | Text        |
      | 1   | true               | front/logo2.jpg | This alt text 1 | This text 1 |
      | 1   |                    |                 | This alt text 2 | This text 2 |
      | 1   |                    | front/logo4.png | This alt text 3 | This text 3 |
      | 2   | false              | front/logo3.png | This alt text 4 | This text 4 |
    Given open shop on storefront
    Then verify Featured Promotion on storefront as "<KEY>"
      | KEY | Full width section | Image           | Alt text        | Text        |
      | 1   | true               | front/logo2.jpg | This alt text 1 | This text 1 |
      | 1   |                    |                 | This alt text 2 | This text 2 |
      | 1   |                    | front/logo4.png | This alt text 3 | This text 3 |
      | 2   | false              | front/logo3.png | This alt text 4 | This text 4 |

    Examples:
      | KEY |
      | 1   |
      | 2   |