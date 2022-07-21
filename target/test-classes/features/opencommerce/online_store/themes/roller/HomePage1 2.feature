Feature: Home Page 1

#  verify: Header, Slideshow, Logo, Newsletter, Banner, Rich text, footer

#  Env:
# prod_sbase_roller_theme1
# staging_sbase_roller_theme1
#  dev_sbase_roller_theme1


  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"


#    =============== Header and slide show ================================
  Scenario Outline: Pre-condition - Reset setting to default #SB_OLS_THE_ROL_84 #SB_OLS_THE_ROL_85 #SB_OLS_THE_ROL_86 #SB_OLS_THE_ROL_87
    And user change theme style as "<KEY>"
      | KEY | Select style   |
      | 1   | Roller Egypt   |
      | 2   | Roller Russia  |
      | 3   | Roller Italy   |
      | 4   | Roller Denmark |
    Then verify color settings on Dashboard as "<KEY>"
      | KEY | Background | Top bar background | Menu background | Announcement bar background | Product description background | Newsletter section background | Mini cart background | Footer background | Announcement message text | Logo text | Page headings | Page content text | Footer headings | Footer text | Sale tag | Price   | Compare at price | Top menu | Main menu | Links   | Footer link | Buttons | Buttons label | Banner buttons | Banner buttons label | Mini cart button | Mini cart button label |
      | 1   | #ffffff    | #f4f4f4            | #ffffff         | #d2ab2d                     | #f4f4f4                        | #f4f4f4                       | #ffffff              | #ffffff           | #ffffff                   | #1a1a1a   | #1a1a1a       | #1a1a1a           | #1a1a1a         | #1a1a1a     | #a62b0d  | #d2ab2d | #1a1a1a          | #1a1a1a  | #1a1a1a   | #1a1a1a | #999999     | #d2ab2d | #ffffff       | #d2ab2d        | #ffffff              | #d2ab2d          | #ffffff                |
      | 2   | #ffffff    | #f7f7f7            | #ffffff         | #856d47                     | #f7f7f7                        | #f4f4f4                       | #f4f4f4              | #ffffff           | #ffffff                   | #1a1a1a   | #856d47       | #212121           | #212121         | #212121     | #a62b0d  | #a68859 | #999999          | #212121  | #212121   | #b8a07a | #999999     | #a68859 | #ffffff       | #a68859        | #ffffff              | #a68859          | #ffffff                |
      | 3   | #fafafa    | #f4f4f4            | #ffffff         | #252e5a                     | #ffffff                        | #ffffff                       | #fafafa              | #ffffff           | #ffffff                   | #252e5a   | #252e5a       | #252e5a           | #252e5a         | #252e5a     | #8e250b  | #252e5a | #999999          | #1a1a1a  | #252e5a   | #252e5a | #4b5bb4     | #252e5a | #ffffff       | #252e5a        | #ffffff              | #252e5a          | #ffffff                |
      | 4   | #ffffff    | #f4f4f4            | #ffffff         | #285f8a                     | #f4f4f4                        | #f4f4f4                       | #ffffff              | #ffffff           | #ffffff                   | #1a1a1a   | #1a1a1a       | #1a1a1a           | #1a1a1a         | #1a1a1a     | #a62b0d  | #285f8a | #999999          | #1a1a1a  | #1a1a1a   | #1a1a1a | #999999     | #285f8a | #ffffff       | #285f8a        | #ffffff              | #285f8a          | #ffffff                |
    And verify typography settings as "<KEY>"
      | KEY | Heading font | Heading style | Heading base size | Capitalize headings | Body text font | Body text style | Body text base size | Button text font | Button text style | Button text base size | Capitalize buttons text |
      | 1   | Baskerville  | Regular       | 48px              | Yes                 | Baskerville    | Regular         | 14px                | Baskerville      | Regular           | 12px                  | Yes                     |
      | 2   | Cabin        | Regular       | 48px              | Yes                 | Cabin          | Regular         | 14px                | Cabin            | Regular           | 12px                  | Yes                     |
      | 3   | Verdana      | Regular       | 48px              | Yes                 | Verdana        | Regular         | 14px                | Verdana          | Regular           | 12px                  | Yes                     |
      | 4   | Montserrat   | Regular       | 48px              | Yes                 | Mulish         | Regular         | 14px                | Montserrat       | Regular           | 12px                  | Yes                     |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |


  Scenario Outline: Verify Header and Slideshow #SB_OLS_THE_ROL_7 #SB_OLS_THE_ROL_8 #SB_OLS_THE_ROL_9 #SB_OLS_THE_ROL_10 #SB_OLS_THE_ROL_11 #SB_OLS_THE_ROL_12 #SB_OLS_THE_ROL_13 #SB_OLS_THE_ROL_14 #SB_OLS_THE_ROL_15 #SB_OLS_THE_ROL_16 #SB_OLS_THE_ROL_17 #SB_OLS_THE_ROL_25 #SB_OLS_THE_ROL_26 #SB_OLS_THE_ROL_27 #SB_OLS_THE_ROL_28 #SB_OLS_THE_ROL_29 #SB_OLS_THE_ROL_30
    And change slideshow settings as "<KEY>"
      | KEY | Text animation | Gallery transition | Slideshow index | Enable parallax scrolling | Background image  | Alt text    | Preheading                                            | Heading                    | Subheading                                             | Text position | Text alignment | Image link | First button label | First button link | Highlight first button label | Second button label | Second button link | Highlight second button label |
      | 1   | Fade up        | Fade               | 1               | true                      | front/slide2.png  | Alt 1       | More Than                                             | 350,000 Happy Customers    | From all of us here at Gadget Jam, thank you very much | Centre        | Centre         |            | Clothes            |                   | false                        | Shoes               |                    | true                          |
      | 2   |                |                    | 1               | false                     | front/slide1.jpeg | slide alt 2 | AUTHENTIC POP CULTURE T-SHIRTS, MUGS, GIFTS, AND MORE | The Black Sale is back !!! | Up to 50% off site wide                                | Right         | Right          |            | Clothes            |                   | true                         | Shoes               |                    | true                          |
      | 3   |                |                    | 1               | true                      | front/slide1.jpeg | slide alt 3 | AUTHENTIC POP CULTURE T-SHIRTS, MUGS, GIFTS, AND MORE | The Black Sale is back !!! | Up to 50% off site wide                                | Left          | Right          |            | Clothes            |                   | true                         | Shoes               |                    | true                          |
    And change header settings as "<KEY>"
      | KEY | Fixed (sticky) header | Enable search | Show cart | Cart icon | Show announcement bar | Announcement message | Show top bar | Show social media header icons | Phone number | Top bar menu | Desktop logo   | Mobile logo          | Size   | Main menu |
      | 1   | N                     | Y             | Y         | Bag       | N                     | Happy new year       | Y            | Y                              | 0987898787   | Main menu    | front/Logo.png |                      | Small  | Main menu |
      | 2   | Y                     | Y             | Y         | Bag       | N                     | Happy new year       | Y            | N                              | 0987898787   | None         | front/Logo.png | front/Mobilelogo.png | Medium | None      |
      | 3   | Y                     | Y             | Y         | Bag       | Y                     | Happy new year       | Y            | N                              | @BLANK@      | None         | front/Logo.png | front/Mobilelogo.png | Large  | None      |
      | 4   | Y                     | N             | N         | Cart      | N                     |                      | N            | N                              | @BLANK@      | None         | front/Logo.png | front/Mobilelogo.png |        | None      |
    Then open shop on storefront
    And verify header settings as "<KEY>"
      | KEY | Fixed (sticky) header | Enable search | Show cart | Cart icon | Show announcement bar | Announcement message | Show top bar | Show social media header icons | Phone number | Top bar menu | Desktop logo   | Mobile logo          | Size      | Main menu |
      | 1   | N                     | Y             | Y         | Bag       | N                     | Happy new year       | Y            | Y                              | 0987898787   | Main menu    | front/Logo.png |                      | is-small  | Main menu |
      | 2   | Y                     | Y             | Y         | Bag       | N                     | Happy new year       | Y            | N                              | 0987898787   | None         | front/Logo.png | front/Mobilelogo.png | is-medium | None      |
      | 3   | Y                     | Y             | Y         | Bag       | Y                     | Happy new year       | Y            | N                              |              | None         | front/Logo.png | front/Mobilelogo.png | is-large  | None      |
      | 4   | Y                     | N             | N         | Cart      | N                     |                      | N            | N                              |              | None         | front/Logo.png | front/Mobilelogo.png |           | None      |
    And verify slideshow settings as "<KEY>"
      | KEY | Slideshow index | Enable parallax scrolling | Background image  | Alt text    | Preheading                                            | Heading                    | Subheading                                             | Text position | Text alignment | First button label | First button link | Highlight first button label | Second button label | Second button link | Highlight second button label |
      | 1   | 1               | true                      | front/slide2.png  | Alt 1       | More Than                                             | 350,000 Happy Customers    | From all of us here at Gadget Jam, thank you very much | Centre        | Centre         | Clothes            |                   | false                        | Shoes               |                    | true                          |
      | 2   | 1               | false                     | front/slide1.jpeg | slide alt 2 | AUTHENTIC POP CULTURE T-SHIRTS, MUGS, GIFTS, AND MORE | The Black Sale is back !!! | Up to 50% off site wide                                | Right         | Right          | Clothes            |                   | true                         | Shoes               |                    | true                          |
      | 3   | 1               | true                      | front/slide1.jpeg | slide alt 3 | AUTHENTIC POP CULTURE T-SHIRTS, MUGS, GIFTS, AND MORE | The Black Sale is back !!! | Up to 50% off site wide                                | Left          | Right          | Clothes            |                   | true                         | Shoes               |                    | true                          |
    And verify UI by css as "<KEY>"
      | KEY | Description      | Element                                       | CSS value                                                        |
      | 1   | Header           | //h2                                          | font-weight>400;font-family>Montserrat;font-style>normal         |
      | 1   | Header SildeShow | //section[@data-id ="homepage_slideshow"]//h3 | font-size>48;color>rgba(255, 255, 255, 1);font-family>Montserrat |
    Examples:
      | KEY | Page                         |
      | 1   | /?sbase_debug=1&render_csr=1 |
      | 2   |                              |
      | 3   |                              |
      | 4   |                              |


#    =================== Rich text and footer ===============================
  Scenario Outline: Rich text & footer #SB_OLS_THE_ROL_36 #SB_OLS_THE_ROL_37 #SB_OLS_THE_ROL_18 #SB_OLS_THE_ROL_19 #SB_OLS_THE_ROL_20 #SB_OLS_THE_ROL_21 #SB_OLS_THE_ROL_22 #SB_OLS_THE_ROL_23 #SB_OLS_THE_ROL_FOOT_LOGO_11 #SB_OLS_THE_ROL_FOOT_LOGO_10 #SB_OLS_THE_ROL_FOOT_LOGO_8 #SB_OLS_THE_ROL_FOOT_LOGO_7 #SB_OLS_THE_ROL_FOOT_LOGO_1
    And change Rich text settings as "<KEY>"
      | KEY | Heading                                           | Text                                                                                              | Text alignment | Link label                         | Link        |
      | 1   | Heading rich text up                              | Roller is next generation ecommerce at its best                                                   | Left           | View our impressive features here. | Collections |
      | 2   | SEnergize Your Shop, With A Theme Built For Speed | An incredibly flexible and sophisticated theme that will make your shop as stunning as it is fast | Centre         | @BLANK@                            |             |
      | 3   | Rich text                                         | Rich text body                                                                                    | Right          | @BLANK@                            | Collections |
      | 4   |                                                   |                                                                                                   | Left           | View more                          |             |
    Then change footer settings as "<KEY>"
      | KEY | Copyright text | Show payment method icons | Show social media footer icons | Type  | Content                                                     | Desktop logo    | Mobile logo     | Size   |
      | 1   | Beeketing      | true                      | true                           | Title | ShopBase Theme>Empower you to sell to anyone, from anywhere | front/logo3.png | front/logo2.jpg | Medium |
      | 1   |                |                           |                                | Menu  | Main menu                                                   |                 |                 |        |
      | 1   |                |                           |                                | Page  | About Us                                                    |                 |                 |        |
      | 2   | abc            | false                     | false                          | Page  | About Us                                                    | front/logo2.jpg | front/logo3.png | Large  |
      | 3   | abc            | true                      | true                           | Page  | About Us                                                    | front/logo2.jpg | front/logo3.png | Small  |
    Then open shop on storefront
    And verify Rich Text settings as "<KEY>"
      | KEY | Heading                                           | Text                                                                                              | Text alignment | Link label                         | Link        |
      | 1   | Heading rich text up                              | Roller is next generation ecommerce at its best                                                   | Left           | View our impressive features here. | Collections |
      | 2   | SEnergize Your Shop, With A Theme Built For Speed | An incredibly flexible and sophisticated theme that will make your shop as stunning as it is fast | Center         |                                    |             |
      | 3   | Rich text                                         | Rich text body                                                                                    | Right          |                                    | Collections |
      | 4   |                                                   |                                                                                                   | Left           | View more                          |             |
    And verify footer on store front "<KEY>"
      | KEY | Copyright text | Show payment method icons | Show social media footer icons | Type  | Content                                                     | Desktop logo    | Mobile logo     | Size      |
      | 1   | Beeketing      | true                      | true                           | Title | ShopBase Theme>Empower you to sell to anyone, from anywhere | front/logo3.png | front/logo2.jpg | is-medium |
      | 1   |                |                           |                                | Menu  | Main menu                                                   |                 |                 |           |
      | 1   |                |                           |                                | Page  | About Us                                                    |                 |                 |           |
      | 2   | abc            | false                     | false                          | Page  | About Us                                                    | front/logo2.jpg | front/logo3.png | is-large  |
      | 3   | abc            | true                      | true                           | Page  | About Us                                                    | front/logo2.jpg | front/logo3.png | is-small  |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |


#    =============== Logo,  Newsletter and Banner ================================
  Scenario Outline: Logo , Newsletter, Banner #SB_OLS_THE_ROL_51 #SB_OLS_THE_ROL_63 #SB_OLS_THE_ROL_64 #SB_OLS_THE_ROL_50 #SB_OLS_THE_ROL_49 #SB_OLS_THE_ROL_48 #SB_OLS_THE_INS_TBN_2
    And change logo list setting as "<KEY>"
      | KEY | Heading   | Image           | Alt text | Link                |
      | 1   | Logo list | front/logo2.jpg | logo1    | Collections>Fashion |
      | 1   | Logo list | front/logo3.png | logo2    | Home page           |
      | 1   | Logo list | front/logo5.png |          |                     |
    And change Newsletter settings as "<KEY>"
      | KEY | Heading                     | Subheading                                                           |
      | 1   | Heading newsletter          | Subheading newsletter                                                |
      | 2   | Subscribe to our newsletter | A short sentence describing what someone will receive by subscribing |
    When change banner setting as "<KEY>"
      | KEY | Background image  | Enable parallax scrolling | Alt text   | Preheading      | Heading               | Subheading                | Text position | Text alignment | Image link                  | First button label | First button link           | Highlight first button label | Second button label | Second button link        | Highlight second button label |
      | 1   | front/Banner.jpg  | true                      | alt banner | Introducing The | roller shopbase theme | exclusively from shopbase | Left          | Left           | Collections>All Collections | Shop Women         | Collections>All Collections | true                         | Shop Men            | Products>All Products     | true                          |
      | 2   | front/Banner2.jpg | false                     |            | Introducing     | shopbase theme        | exclusively               | Centre        | Centre         | https://www.shopbase.com/   | Shop Women         | https://www.shopbase.com/   | false                        | Shop Men            | https://www.shopbase.com/ | false                         |
    And wait 5 second
    Then open shop on storefront
    And verify logo list on store front "<KEY>"
      | KEY | Heading   | Image           | Alt text | Link                 |
      | 1   | Logo list | front/logo2.jpg | logo1    | /collections/fashion |
      | 1   | Logo list | front/logo3.png | logo2    | /                    |
      | 1   | Logo list | front/logo5.png |          | /                    |
    And verify Newsletter settings as "<KEY>"
      | KEY | Heading                     | Subheading                                                           | Email                 | Messages                  |
      | 1   | Heading newsletter          | Subheading newsletter                                                | huong                 |                           |
      | 2   | Subscribe to our newsletter | A short sentence describing what someone will receive by subscribing | signup@mailtothis.com | Thank you for subscribing |
    When verify banner on storefront "<KEY>"
      | KEY | Background image | Enable parallax scrolling | Alt text   | Preheading      | Heading               | Subheading                | Text position | Text alignment | Image link                 | First button label | First button link          | Highlight first button label | Second button label | Second button link        | Highlight second button label |
      | 1   | Banner.png       | true                      | alt banner | INTRODUCING THE | ROLLER SHOPBASE THEME | EXCLUSIVELY FROM SHOPBASE | left          | left           | Collection>All Collections | SHOP WOMEN         | Collection>All Collections | true                         | SHOP MEN            | Products>All Products     | true                          |
      | 2   | Banner2.png      | false                     |            | INTRODUCING     | SHOPBASE THEME        | EXCLUSIVELY               | Centre        | Centre         | https://www.shopbase.com/  | SHOP WOMEN         | https://www.shopbase.com/  | false                        | SHOP MEN            | https://www.shopbase.com/ | false                         |
    Examples:
      | KEY |
      | 1   |
      | 2   |