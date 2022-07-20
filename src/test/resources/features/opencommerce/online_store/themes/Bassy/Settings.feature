Feature: Settings tab
#  Cart, color, typography

#  Theme: bassy
#  Env: prod_sbase_settings_bassy



  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    And user click Customize on current theme


#    ======================= Setting Cart ===================================
  Scenario Outline: Setting cart #SB_OLS_THE_BAS_11 #SB_OLS_THE_BAS_12
    And choose preview page "Cart page"
    Then change setting cart page as "<KEY>"
      | KEY | Cart type        |
      | 1   | Go to Cart page  |
      | 2   | Open Cart drawer |
    Given open shop on storefront
    Then search and select the product "Cairbull Cycling Helmet"
    And verify cart on storefront as "<KEY>"
      | KEY | Cart type |
      | 1   | Page      |
      | 2   | Drawer    |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |


#    ===================== Color ==================================
  Scenario Outline: Color #SB_OLS_THE_BAS_13 #SB_OLS_THE_BAS_14 #SB_OLS_THE_BAS_15 #SB_OLS_THE_BAS_16
    When user change color setting as "<KEY>"
      | KEY | Header background | Header links | Page background | Page headings | Page body text | Page button label | Page button | Footer background | Footer text | Drawer button | Drawer text | Drawer background |
      | 1   | #F2EDC2           | #46A0EF      | #DCC164         | #8FE665       | #ECCACF        | #9F6642           | #B257D7     | #EAA1A1           | #52B7BF     | #3973BB       | #8AEC61     | #80DDC8           |
      | 2   | #DBF2AC           | #86C2F6      | #ACDC64         | #88EAEC       | #ECACB5        | #44DAC8           | #ECC7FC     | #E2D68E           | #889DF4     | #EC7EEE       | #3FE3CB     | #F9D08B           |
    Then open shop on storefront
    And open page "<Page>"
    And verify UI by css as "<KEY>"
      | KEY | Description       | Element                                                          | CSS value                |
      | 1   | Header background | //header                                                         | background-color>#f2edc2 |
      | 1   | Header links      | (//header//a[contains(@class, 'item--link')])[1]                 | color>#46a0ef            |
      | 1   | Page background   | //body                                                           | background-color>#dcc164 |
      | 1   | Page button label | (//button[descendant::span[normalize-space()='Add to cart']])[1] | color>#9f6642            |
      | 1   | Page button       | (//button[descendant::span[normalize-space()='Add to cart']])[1] | background-color>#b257d7 |
      | 1   | Footer background | //footer                                                         | background-color>#eaa1a1 |
      | 1   | Footer text       | //footer//div[contains(@class,'information')]                    | color>#52b7bf            |

      | 2   | Header background | //header                                                         | background-color>#dbf2ac |
      | 2   | Header links      | (//header//a[contains(@class, 'item--link')])[1]                 | color>#86c2f6            |
      | 2   | Page background   | //body                                                           | background-color>#acdc64 |
      | 2   | Page heading      | //h1                                                             | color>#88eaec            |
      | 2   | Page button label | (//button[descendant::span[normalize-space()='Add to cart']])[1] | color>#44dac8            |
      | 2   | Page button       | (//button[descendant::span[normalize-space()='Add to cart']])[1] | background-color>#44dac8 |
      | 2   | Footer background | //footer                                                         | background-color>#e2d68e |
      | 2   | Footer text       | //footer//div[contains(@class,'information')]                    | color>#889df4            |

    And add products "Running Shorts Mens" to cart
    And open mini cart or cart drawer
    And verify UI by css as "<KEY>"
      | KEY | Description       | Element                                                                 | CSS value                |
#      | 1   | Drawer button     | //div[contains(@class,'cart--drawer')]//button[normalize-space()='Checkout'] | background-color>#3973bb |
      | 1   | Drawer text       | //div[contains(@class,'cart--drawer')]//p[normalize-space()='Subtotal'] | color>#8aec61            |
      | 1   | Drawer background | //div[contains(@class,'cart--drawer')]                                  | background-color>#80ddc8 |

#      | 2   | Drawer button     | //div[contains(@class,'cart--drawer')]//button[normalize-space()='Checkout'] | background-color>#ec7eee |
      | 2   | Drawer text       | //div[contains(@class,'cart--drawer')]//p[normalize-space()='Subtotal'] | color>#3fe3cb            |
      | 2   | Drawer background | //div[contains(@class,'cart--drawer')]                                  | background-color>#f9d08b |

    Examples:
      | KEY | Page                |
      | 1   | /                   |
      | 2   | Running Shorts Mens |

  Scenario Outline: Change theme style #SB_OLS_THE_BAS_25 #SB_OLS_THE_BAS_26 #SB_OLS_THE_BAS_27 #SB_OLS_THE_BAS_28
    And user change theme style as "<KEY>"
      | KEY | Select style   |
      | 1   | Bassy New York |
      | 2   | Bassy Tokyo    |
      | 3   | Bassy Paris    |
      | 4   | Bassy Seoul    |
    Then verify color settings "<KEY>"
      | KEY | Background Header | Links Header | Background Page | Headings Page | Body text Page | Line color Page | Buttons label Page | Buttons Page | Links and accents Page | Product background Page | Sale tags Page | Form fields Page | Newsletter and tables background Page | Footer Background | Footer Text | Drawer Button | Drawer Text | Drawer Lines and borders | Drawer Background |
      | 1   | #ffffff           | #777777      | #FFFFFF         | #222222       | #777777        | #333333         | #FFFFFF            | #000000      | #222222                | #F3F3F3                 | #D5313C        | #999999          | #F3F3F3                               | #FFFFFF           | #444444     | #222222       | #222222     | #333333                  | #FFFFFF           |
      | 2   | #ffffff           | #777777      | #FFFFFF         | #222222       | #777777        | #333333         | #FFFFFF            | #000000      | #222222                | #ffffff                 | #D5313C        | #999999          | #F3F3F3                               | #FFFFFF           | #444444     | #bd9669       | #222222     | #333333                  | #dec09e           |
      | 3   | #ffffff           | #777777      | #FFFFFF         | #222222       | #777777        | #333333         | #FFFFFF            | #000000      | #222222                | #F3F3F3                 | #D5313C        | #999999          | #F3F3F3                               | #FFFFFF           | #444444     | #005b92       | #ffffff     | #333333                  | #063958           |
      | 4   | #ffffff           | #777777      | #FFFFFF         | #222222       | #777777        | #333333         | #FFFFFF            | #000000      | #222222                | #F3F3F3                 | #D5313C        | #999999          | #F3F3F3                               | #FFFFFF           | #444444     | #e0b723       | #ffffff     | #333333                  | #bb9200           |
    And verify typography settings as "<KEY>"
      | KEY | Heading font | Heading style | Heading base size | Font Accent text | Style Accent text | Capitalize text | Space letters | Use accent text for subheadings | Body text font | Body text style | Body text base size | Italicize product titles, collection titles, input fields, label fields, and dates. |
      | 1   | Montserrat   | Regular       | 32px              | Mulish           | Regular           | No              | No            | No                              | Montserrat     | Regular         | 16px                | No                                                                                  |
      | 2   | Montserrat   | Regular       | 32px              | Mulish           | Regular           | No              | No            | No                              | Montserrat     | Regular         | 16px                | No                                                                                  |
      | 3   | Roboto Mono  | Regular       | 32px              | Roboto Mono      | Regular           | No              | No            | No                              | Roboto Mono    | Regular         | 16px                | No                                                                                  |
      | 4   | Mulish       | Regular       | 32px              | Mulish           | Regular           | No              | No            | No                              | Mulish         | Regular         | 16px                | No                                                                                  |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |

  Scenario Outline: Verify typography #SB_OLS_THE_BAS_17
    And user change typography as "<KEY>"
      | KEY | Headings font | Font Accent text | Capitalize text | Space letters | Use accent text for subheadings | Body text font |
      | 1   | Montserrat    | Adamina          | true            | true          | true                            | Montserrat     |
      | 2   | Adamina       | Montserrat       | false           | true          | false                           | Adamina        |
    Then open shop on storefront
    And open page "<Page 1>"
    And verify UI by css as "<KEY>"
      | KEY | Description        | Section              | Element                                                                                             | CSS value                          |
      | 1   | Headings - font    |                      | //h1                                                                                                | font-family>Montserrat, sans-serif |
      | 1   | Headings - font    | Slideshow            | (//section[contains(@class,'slideshow')]//div[contains(@class,'slideshow__slide-caption ')]//h1)[1] | font-family>Montserrat, sans-serif |
      | 1   | Headings - font    | Introduction Text    | //h1                                                                                                | font-family>Montserrat, sans-serif |
      | 1   | Headings - font    | Video                | //section[@class='container']//h1                                                                   | font-family>Montserrat, sans-serif |
      | 1   | Headings - font    | Collection List      | //section[contains(@data-id,'collection_list')]//h1                                                 | font-family>Montserrat, sans-serif |
      | 1   | Headings - font    | Featured Collection  | //section[contains(@data-id,'featured_collection')]//h1                                             | font-family>Montserrat, sans-serif |
      | 1   | Headings - font    | Newsletter           | //section[contains(@class,'newsletter')]//h1                                                        | font-family>Montserrat, sans-serif |
      | 1   | Body text - font   | Introduction Text    | //section[@data-id='homepage_introduction_text']//h1                                                | font-family>Montserrat, sans-serif |
      | 1   | Body text - font   | Featured Collection  | (//section[contains(@data-id,'featured_collection')]//div[contains(@cla ss,'flex')]//a)[1]          | font-family>Montserrat, sans-serif |
      | 1   | Body text - font   | Newsletter           | //section[contains(@class,'newsletter')]//div[contains(@class,'text-align')]                        | font-family>Montserrat, sans-serif |
      | 1   | Body text - font   | Footer               | //footer[contains(@class,'footer')]//div[contains(@class,'white-space-pre-line')]                   | font-family>Montserrat, sans-serif |
      | 1   | Accent text - font | Menu                 | //h5                                                                                                | font-family>Adamina, serif         |
      | 1   | Accent text - font | Buttons              | //section[contains(@class,'newsletter')]//button                                                    | font-family>Adamina, serif         |
      | 1   | Accent text - font | Slideshow subheading | (//section[contains(@class,'slideshow')]//div[contains(@class,'slideshow__slide-caption ')]//p)[1]  | font-family>Adamina, serif         |
      | 1   | Capitalize text    | Menu                 | //h5                                                                                                | text-transform>uppercase           |
      | 1   | Capitalize text    | Buttons              | //section[contains(@class,'newsletter')]//button                                                    | text-transform>uppercase           |
      | 1   | Capitalize text    | Slideshow subheading | (//section[contains(@class,'slideshow')]//div[contains(@class,'slideshow__slide-caption ')]//p)[1]  | text-transform>uppercase           |

      | 2   | Headings - font    |                      | //h1                                                                                                | font-family>Adamina, serif         |
      | 2   | Headings - font    | Slideshow            | (//section[contains(@class,'slideshow')]//div[contains(@class,'slideshow__slide-caption ')]//h1)[1] | font-family>Adamina, serif         |
      | 2   | Headings - font    | Introduction Text    | //h1                                                                                                | font-family>Adamina, serif         |
      | 2   | Headings - font    | Video                | //section[@class='container']//h1                                                                   | font-family>Adamina, serif         |
      | 2   | Headings - font    | Collection List      | //section[contains(@data-id,'collection_list')]//h1                                                 | font-family>Adamina, serif         |
      | 2   | Headings - font    | Featured Collection  | //section[contains(@data-id,'featured_collection')]//h1                                             | font-family>Adamina, serif         |
      | 2   | Headings - font    | Newsletter           | //section[contains(@class,'newsletter')]//h1                                                        | font-family>Adamina, serif         |
      | 2   | Body text - font   | Introduction Text    | //section[@data-id='homepage_introduction_text']//h1                                                | font-family>Adamina, serif         |
      | 2   | Body text - font   | Featured Collection  | (//section[contains(@data-id,'featured_collection')]//div[contains(@class,'flex')]//a)[1]           | font-family>Adamina, serif         |
      | 2   | Body text - font   | Newsletter           | //section[contains(@class,'newsletter')]//div[contains(@class,'text-align')]                        | font-family>Adamina, serif         |
      | 2   | Body text - font   | Footer               | //footer[contains(@class,'footer')]//div[contains(@class,'white-space-pre-line')]                   | font-family>Adamina, serif         |
      | 2   | Accent text - font | Menu                 | //h5                                                                                                | font-family>Adamina, serif         |
      | 2   | Accent text - font | Buttons              | //section[contains(@class,'newsletter')]//button                                                    | font-family>Montserrat, sans-serif |
      | 2   | Accent text - font | Slideshow subheading | (//section[contains(@class,'slideshow')]//div[contains(@class,'slideshow__slide-caption ')]//p)[1]  | font-family>Adamina, serif         |
      | 2   | Capitalize text    | Menu                 | //h5                                                                                                | none                               |
      | 2   | Capitalize text    | Buttons              | //section[contains(@class,'newsletter')]//button                                                    | none                               |
      | 2   | Capitalize text    | Slideshow subheading | (//section[contains(@class,'slideshow')]//div[contains(@class,'slideshow__slide-caption ')]//p)[1]  | none                               |
    And open page "<Page 2>"
    And verify UI by css as "<KEY>"
      | KEY | Description      | Element                                      | CSS value                          |
      | 1   | Headings - font  | //div[@class='header-section']//h1           | font-family>Montserrat, sans-serif |
      | 1   | Headings - font  | //div[contains(@class,'collection')]//h1     | font-family>Montserrat, sans-serif |
      | 1   | Body text - font | (//div[contains(@class,'collection')]//a)[1] | font-family>Montserrat, sans-serif |
      | 2   | Headings - font  | //div[@class='header-section']//h1           | font-family>Adamina, serif         |
      | 2   | Headings - font  | //div[contains(@class,'collection')]//h1     | font-family>Adamina, serif         |
      | 2   | Body text - font | (//div[contains(@class,'collection')]//a)[1] | font-family>Adamina, serif         |
    And open page "<Page 3>"
    And verify UI by css as "<KEY>"
      | KEY | Description      | Element                                                                  | CSS value                          |
      | 1   | Headings - font  | //div[@class='header-section']//h1                                       | font-family>Montserrat, sans-serif |
      | 1   | Headings - font  | //div[@class='container']//h1                                            | font-family>Montserrat, sans-serif |
      | 1   | Body text - font | //div[@class='container']//span[@class='product__variant-label']         | font-family>Montserrat, sans-serif |
      | 1   | Body text - font | //div[@class='container']//div[contains(@class,'product-property-main')] | font-family>Montserrat, sans-serif |
      | 1   | Capitalize text  | //button[contains(@class,'is-secondary product__add-button')]            | text-transform>uppercase           |
      | 1   | Capitalize text  | //button[contains(@class,'is-primary product__add-button')]              | text-transform>uppercase           |

      | 2   | Headings - font  | //div[@class='header-section']//h1                                       | font-family>Adamina, serif         |
      | 2   | Headings - font  | //div[@class='container']//h1                                            | font-family>Adamina, serif         |
      | 2   | Body text - font | //div[@class='container']//span[@class='product__variant-label']         | font-family>Adamina, serif         |
      | 2   | Body text - font | //div[@class='container']//div[contains(@class,'product-property-main')] | font-family>Adamina, serif         |
      | 2   | Capitalize text  | //button[contains(@class,'is-secondary product__add-button')]            | none                               |
      | 2   | Capitalize text  | //button[contains(@class,'is-primary product__add-button')]              | none                               |
    And open page "<Page 4>"
    And verify UI by css as "<KEY>"
      | KEY | Description     | Element                                          | CSS value                          |
      | 1   | Headings - font | //div[@class='header-section']//h1               | font-family>Montserrat, sans-serif |
      | 1   | Headings - font | //div[@class='header-section']//h1               | font-family>Montserrat, sans-serif |
      | 1   | Headings - font | (//main[@class='flex-grow main-content']//h2)[1] | font-family>Montserrat, sans-serif |

      | 2   | Headings - font | //div[@class='header-section']//h1               | font-family>Adamina, serif         |
      | 2   | Headings - font | //div[@class='header-section']//h1               | font-family>Adamina, serif         |
      | 2   | Headings - font | (//main[@class='flex-grow main-content']//h2)[1] | font-family>Adamina, serif         |
    And open page "<Page 5>"
    And verify UI by css as "<KEY>"
      | KEY | Description      | Element                                      | CSS value                          |
      | 1   | Headings - font  | //div[@class='header-section']//h1           | font-family>Montserrat, sans-serif |
      | 1   | Headings - font  | //div[contains(@class,'collection')]//h1     | font-family>Montserrat, sans-serif |
      | 1   | Body text - font | (//div[contains(@class,'collection')]//a)[1] | font-family>Montserrat, sans-serif |
      | 2   | Headings - font  | //div[@class='header-section']//h1           | font-family>Adamina, serif         |
      | 2   | Headings - font  | //div[contains(@class,'collection')]//h1     | font-family>Adamina, serif         |
      | 2   | Body text - font | (//div[contains(@class,'collection')]//a)[1] | font-family>Adamina, serif         |

    Examples:
      | KEY | Page 1 | Page 2           | Page 3                                   | Page 4       | Page 5                    |
      | 1   | /      | /collections/all | Waterproof Small Fitness Running Gym Bag | /collections | /collections/best-selling |
      | 2   | /      | /collections/all | Waterproof Small Fitness Running Gym Bag | /collections | /collections/best-selling |





