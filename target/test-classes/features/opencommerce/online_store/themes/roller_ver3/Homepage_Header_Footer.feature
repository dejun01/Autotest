Feature: Homepage - Header, Footer

#  sbase_roller_ver3
#  Env:
#  sbase_rollerver3_header

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme
    And open preview page "Home"

  Scenario Outline: Verify Header setting
    And setting Header on Theme editor "<KEY>"
      | KEY | Fixed (sticky) header | Enable search | Show cart | Cart icon | Show announcement bar | Announcement message | Show top bar | Show social media header icons | Phone number | Top bar menu | Desktop logo   | Mobile logo          | Size   | Main menu | Main menu top padding |
      | 1   | false                 | true          | true      | Bag       | false                 | Happy new year       | true         | true                           | 0987898787   | Main menu    | front/Logo.png |                      | Small  | Main menu | 0%                    |
      | 2   | true                  | true          | true      | Bag       | false                 | Happy new year       | true         | false                          | 0987898787   | None         | front/Logo.png | front/Mobilelogo.png | Medium | None      | 20%                   |
      | 3   | true                  | true          | true      | Bag       | true                  | Happy new year       | true         | false                          | @BLANK@      | None         | front/Logo.png | front/Mobilelogo.png | Large  | None      | 40%                   |
      | 4   | true                  | false         | false     | Cart      | false                 |                      | false        | false                          | @BLANK@      | None         | front/Logo.png | front/Mobilelogo.png | Small  | None      | 60%                   |
    Then save change setting
    Given open shop on storefront
    And verify Header on Storefront "<KEY>"
      | KEY | Fixed (sticky) header | Enable search | Show cart | Cart icon | Show announcement bar | Announcement message | Show top bar | Show social media header icons | Phone number | Top bar menu | Desktop logo   | Mobile logo          | Size      | Main menu | Main menu top padding |
      | 1   | false                 | true          | true      | Bag       | false                 | Happy new year       | true         | true                           | 0987898787   | Main menu    | front/Logo.png |                      | is-small  | Main menu | 0%                    |
      | 2   | true                  | true          | true      | Bag       | false                 | Happy new year       | true         | false                          | 0987898787   | None         | front/Logo.png | front/Mobilelogo.png | is-medium | None      | 20%                   |
      | 3   | true                  | true          | true      | Bag       | true                  | Happy new year       | true         | false                          | @BLANK@      | None         | front/Logo.png | front/Mobilelogo.png | is-large  | None      | 40%                   |
      | 4   | true                  | false         | false     | Cart      | false                 |                      | false        | false                          | @BLANK@      | None         | front/Logo.png | front/Mobilelogo.png | is-small  | None      | 60%                   |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Remove block
    When remove block in "Footer" section "<KEY>"
      | KEY | Block name |
      | 1   | Menu       |
      | 1   | Title      |
      | 1   | Page       |
    When remove block in "Payment Icons" section "<KEY>"
      | KEY | Block name |
      | 1   | Icon       |
    And save change setting
    Examples:
      | KEY |
      | 1   |

  Scenario: Add block
    When add block in "Footer" section
      | Block name |
      | Menu       |
      | Title      |
      | Page       |
    When add block in "Footer" section
      | Icon |
    And save change setting

  Scenario Outline: Verify Footer
    Then setting Footer "<KEY>"
      | KEY | Copyright text | Show social media footer icons | Desktop logo    | Size   |
      | 1   | Beeketing      | true                           | front/logo3.png | Medium |
      | 2   | abc            | false                          | front/logo2.jpg | Large  |
      | 3   | abc            | true                           | front/logo2.jpg | Small  |
    And setting block "Menu" in Footer "<KEY>"
      | KEY | Main menu |
      | 1   | Main menu |
      | 2   | None      |
      | 3   | Shop      |
    And setting block "Title" in Footer "<KEY>"
      | KEY | Heading        | Text                                                           |
      | 1   | Roller theme   |                                                                |
      | 2   |                | Better your life with high quality products on Roller theme. 2 |
      | 3   | Roller theme 2 | Better your life with high quality products on Roller theme. 3 |
    And setting block "Page" in Footer "<KEY>"
      | KEY | Content page |
      | 1   |              |
      | 2   | Contact Us   |
      | 3   | FAQs         |
    And setting Payment Icons "<KEY>"
      | KEY | Show payment method icons |
      | 1   | true                      |
      | 2   | false                     |
      | 3   | true                      |
    And setting block "Icon" in Footer "<KEY>"
      | KEY | Icon            |
      | 1   | front/logo3.png |
      | 2   | front/logo2.jpg |
      | 3   | front/logo3.png |
    And save change setting
    Given open shop on storefront
    Then verify Footer theme Roller on sf "<KEY>"
      | KEY | Copyright text | Show payment method icons | Show social media footer icons | Main menu | Heading        | Text                                                           | Content page | Desktop logo    | Mobile logo     | Size      |
      | 1   | Beeketing      | true                      | true                           | Main menu | Roller theme   |                                                                |              | front/logo3.png | front/logo2.jpg | is-medium |
      | 2   | abc            | false                     | false                          | None      |                | Better your life with high quality products on Roller theme. 2 | Contact Us   | front/logo2.jpg | front/logo3.png | is-large  |
      | 3   | abc            | true                      | true                           | Shop      | Roller theme 2 | Better your life with high quality products on Roller theme. 3 | FAQS         | front/logo2.jpg | front/logo3.png | is-small  |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |




