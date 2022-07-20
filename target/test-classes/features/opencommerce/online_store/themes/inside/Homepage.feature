Feature: HomePage - Inside Theme - header,footer section, Rich text, banner, Newsletter
#  environment=staging_sbase_inside_theme
  #prod_sbase_inside_theme
  #dev_sbase_inside_theme

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"

  Scenario Outline: Verify header section #SB_OLS_THE_INS_1 #SB_OLS_THE_INS_55 #SB_OLS_THE_INS_54
    When change header settings theme inside as "<KEY>"
      | KEY | Layout  | Full width section | Content alignment | Desktop logo    | Mobile logo     | Size   | Fixed header | Show announcement bar | Announcement message                      | Fixed position while scrolling | Main menu | Menu position | Enable Topbar menu | Topbar menu | Enable search | Show cart icon | Cart icon |
      | 1   | Inline  | true               | Left              | front/logo2.jpg | front/logo3.png | Small  | true         | true                  | Freeshipping all over the world! See more | true                           | Main menu | Left          | true               | Shop        | true          | true           | Bag       |
      | 2   | Rich    | false              | Center            | front/logo3.png | front/logo2.jpg | Medium | false        | false                 | Freeshipping all over the world! See more | false                          | Main menu | Center        | fasle              |             | false         | true           | Cart      |
      | 3   | Minimal | false              |                   | front/logo2.jpg | front/logo3.png | Large  |              | true                  | Discount code upto 50%! See more          | fasle                          | Main menu |               |                    |             | true          | false          | Bag       |

    Then open shop on storefront
    And verify header settings as "<KEY>"
      | KEY | Layout  | Fixed (sticky) header | Enable search | Show cart | Cart icon | Show announcement bar | Announcement message                      | Show top bar | Top bar menu | Desktop logo    | Mobile logo     | Size      | Main menu |
      | 1   | Inline  | true                  | true          | true      | Bag       | true                  | Freeshipping all over the world! See more | true         | Shop         | front/logo2.jpg | front/logo3.png | is-small  | Main menu |
      | 2   | Rich    | false                 | false         | true      | Cart      | false                 | Freeshipping all over the world! See more | fasle        |              | front/logo3.png | front/logo2.jpg | is-medium | Main menu |
      | 3   | Minimal |                       | true          | false     | Bag       | true                  | Discount code upto 50%! See more          |              |              | front/logo2.jpg | front/logo3.png | is-large  | Main menu |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify footer section section #SB_OLS_THE_INS_15 #SB_OLS_THE_INS_60 #SB_OLS_THE_INS_58 #SB_OLS_THE_INS_57
    And change footer settings as "<KEY>"
      | KEY | Layout  | Copyright text | Show payment method icons | Full width section | Type              | Content                                                     | Desktop logo    | Mobile logo     | Size  | Text   | Content alignment | Footer menu | Link                 |
      | 1   | Rich    | Beeketing      | true                      | true               | Title             | ShopBase Theme>Empower you to sell to anyone, from anywhere |                 |                 |       |        |                   |             | https://shopbase.com |
      | 1   | Rich    |                |                           |                    | Menu              | Main menu                                                   |                 |                 |       |        |                   |             |                      |
      | 1   | Rich    |                |                           |                    | Page              | About                                                       |                 |                 |       |        |                   |             |                      |
      | 1   | Rich    |                |                           |                    | Store information | Store 1>VietNam>shopbase@beeketing.net>0963456543>true      |                 |                 |       |        |                   |             |                      |
      | 2   | Rich    | Beeketing test | false                     | false              | Store information | Store 2>China>shopbase1@beeketing.net>0983456543>false      |                 |                 |       |        |                   |             |                      |
      | 3   | Minimal |                | true                      | true               |                   |                                                             | front/logo2.jpg | front/logo3.png | Large |        | Left              | Main menu   |                      |
      | 4   | Minimal | abc            | false                     | false              |                   |                                                             | front/logo2.jpg | front/logo3.png | Small | footer | Center            | None        |                      |

    Then open shop on storefront
    And verify footer on store front "<KEY>"
      | KEY | Layout  | Copyright text | Show payment method icons | Full width section | Type              | Content                                                     | Show social media footer icons | Desktop logo    | Mobile logo     | Size     | Text   | Content alignment | Footer menu |
      | 1   | Rich    | Beeketing      | true                      | true               | Title             | ShopBase Theme>Empower you to sell to anyone, from anywhere | true                           |                 |                 |          |        |                   |             |
      | 1   | Rich    |                |                           |                    | Menu              | Main menu                                                   |                                |                 |                 |          |        |                   |             |
      | 1   | Rich    |                |                           |                    | Page              | About                                                       |                                |                 |                 |          |        |                   |             |
      | 1   | Rich    |                |                           |                    | Store information | Store 1>VietNam>shopbase@beeketing.net>0963456543           |                                |                 |                 |          |        |                   |             |
      | 2   | Rich    | Beeketing test | false                     | false              | Store information | Store 2>China>shopbase1@beeketing.net>0983456543            | false                          |                 |                 |          |        |                   |             |
      | 3   | Minimal |                | true                      | true               |                   |                                                             | false                          | front/logo2.jpg | front/logo3.png | is-large |        | Left              | Main menu   |
      | 4   | Minimal | abc            | false                     | false              |                   |                                                             | false                          | front/logo2.jpg | front/logo3.png | is-small | footer | Center            | None        |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |

  Scenario Outline: verify Rich text #SB_OLS_THE_INS_14
    And change Rich text settings as "<KEY>"
      | KEY | Heading                                           | Text                                                                                              | Text alignment | Link label                         | Link                        | Button label                       | Button link                 | Heading position    |
      | 1   | Heading rich text up                              | Roller is next generation ecommerce at its best                                                   | Left           | View our impressive features here. | Collections>All Collections | View our impressive features here. | Collections>All Collections | In margin with text |
      | 2   | SEnergize Your Shop, With A Theme Built For Speed | An incredibly flexible and sophisticated theme that will make your shop as stunning as it is fast | Center         | @BLANK@                            |                             | @BLANK@                            |                             | Above the text      |
      | 3   | Rich text                                         |                                                                                                   | Right          | @BLANK@                            | Collections>All Collections | @BLANK@                            | Collections>All Collections | In margin with text |
    Then open shop on storefront
    And verify Rich Text settings as "<KEY>"
      | KEY | Heading                                           | Text                                                                                              | Text alignment | Link label                         | Link                        | Button label                       | Button link                 | Heading position    |
      | 1   | Heading rich text up                              | Roller is next generation ecommerce at its best                                                   | Left           | View our impressive features here. | Collections>All Collections | View our impressive features here. | Collections>All Collections | In margin with text |
      | 2   | SEnergize Your Shop, With A Theme Built For Speed | An incredibly flexible and sophisticated theme that will make your shop as stunning as it is fast | Center         |                                    |                             | @BLANK@                            |                             | Above the text      |
      | 3   | Rich text                                         |                                                                                                   | Right          |                                    | Collections>All Collections | @BLANK@                            | Collections>All Collections | In margin with text |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: verify banner #SB_OLS_THE_INS_8
    When change banner setting as "<KEY>"
      | KEY | Background image  | Enable parallax scrolling | Alt text   | Preheading      | Heading               | Subheading                | Text position | Text alignment | Image link                  | First button label | First button link           | Highlight first button label | Second button label | Second button link        | Highlight second button label | Headline    | Description    | Full width section |
      | 1   | front/Banner.jpg  | true                      | alt banner | Introducing The | roller shopbase theme | exclusively from shopbase | Left          | Left           | Collections>All Collections | Shop Women         | Collections>All Collections | true                         | Shop Men            | Products>All Products     | true                          | Banner      | Test           | true               |
      | 2   | front/Banner2.jpg | false                     |            | Introducing     | shopbase theme        | exclusively               | Centre        | Centre         | https://www.shopbase.com/   | Shop Women         | https://www.shopbase.com/   | false                        | Shop Men            | https://www.shopbase.com/ | false                         | Introducing | shopbase theme | false              |

    Then open shop on storefront
    And verify banner on storefront "<KEY>"
      | KEY | Background image | Enable parallax scrolling | Alt text   | Preheading      | Heading               | Subheading                | Text position | Text alignment | Image link                | First button label | First button link         | Highlight first button label | Second button label | Second button link        | Highlight second button label | Headline    | Description    | Full width section |
      | 1   | Banner.png       | true                      | alt banner | INTRODUCING THE | ROLLER SHOPBASE THEME | EXCLUSIVELY FROM SHOPBASE | left          | left           | /collections              | Shop Women         | /collections              | true                         | Shop Men            | /collections/all          | true                          | Banner      | Test           | true               |
      | 2   | Banner2.png      | false                     |            | INTRODUCING     | SHOPBASE THEME        | EXCLUSIVELY               | Centre        | Centre         | https://www.shopbase.com/ | Shop Women         | https://www.shopbase.com/ | false                        | Shop Men            | https://www.shopbase.com/ | false                         | Introducing | shopbase theme | false              |

    Examples:
      | KEY |
      | 1   |
      | 2   |