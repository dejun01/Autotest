Feature: settings in theme Roller

  # prod_sbase_roller_theme_setting
# staging_sbase_roller_theme_setting
#  dev_sbase_roller_theme_setting

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme


#    =========== Cart setting ====================================
  Scenario Outline: Cart setting
    And choose preview page "Cart page"
    When change setting cart page as "<KEY>"
      | KEY | Cart type            | Only show Checkout button in cart drawer |
      | 1   | Go to Cart page      | false                                    |
      | 2   | Open Mini cart       | true                                     |
      | 3   | Refresh current page | true                                     |
      | 4   | Go to Checkout page  | false                                    |
    Then open shop on storefront
    And search and select the product "MC White Lace Dress"
    Then verify cart on storefront as "<KEY>"
      | KEY | Cart type | Only show Checkout button in cart drawer |
      | 1   | Page      | false                                    |
      | 2   | Mini Cart | true                                     |
      | 3   | Refresh   | true                                     |
      | 4   | Checkout  | false                                    |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |


#    ================== Currency setting ===========================
  Scenario Outline: Currency setting
    When user change currency setting in dashboard with enable is "<Enable>" and format is "<Format>" and supported is "<Supported currencies>" and currencies are "<Currencies>"
    Then open shop on storefront
    Then verify currency setting on storefront with enable is "<Enable>" and format is "<Format>" and supported is "<Supported currencies>" and currencies are "<Currencies>" and total are "<Total>"
    Examples:
      | Enable | Format                  | Supported currencies              | Currencies      | Total |
      | false  | Without currency ($10)  | All Roller's supported currencies |                 | 133   |
      | true   | Without currency ($10)  | All Roller's supported currencies |                 | 133   |
      | true   | With currency ($10 USD) | Some currencies:                  | USD CAD EUR JPY |       |


#    ============== Language setting =================================
  Scenario Outline: Language setting
    When user change language setting in dashboard with enable is "<Enable>"  and supported is "<Supported languages>" and languages are "<Languages>" and default language is "<Default language>"
    Then open shop on storefront
    Then verify language setting on storefront with enable is "<Enable>"  and supported is "<Supported languages>" and languages are "<Languages>" and default language is "<Default language>"
    Examples:
      | Enable | Supported languages              | Languages               | Default language |
      | false  | All Roller's supported languages |                         | en               |
      | true   | All Roller's supported languages |                         |                  |
      | true   | Some languages:                  | en=English fr=Francaise | english          |


#    =================== Color and typography =========================
  Scenario Outline: setting colors and typography #SB_OLS_THE_ROL_1 #SB_OLS_THE_ROL_2 #SB_OLS_THE_ROL_3 #SB_OLS_THE_ROL_4 #SB_OLS_THE_ROL_5 #SB_OLS_THE_ROL_6
#    When user change color setting as "<KEY>"
#      | KEY | Background | Top bar background | Menu background | Announcement bar background | Newsletter section background | Product description background | Mini cart background | Footer background | Announcement message text | Logo text | Page headings | Page content text | Footer headings | Footer text | Price   | Compare at price | Top menu | Main menu | Links   | Footer link | Buttons | Buttons label | Banner buttons | Banner buttons label | Mini cart button | Mini cart button label |
#      | 1   | #fafafa    | #f4f4f4            | #ffffff         | #252e5a                     | #f4f4f4                       | #f7f7f7                        | #fafafa              | #ffffff           | #ffffff                   | #252e5a   | #252e5a       | #252e5a           | #252e5a         | #252e5a     | #252e5a | #999999          | #1a1a1a  | #252e5a   | #252e5a | #4b5bb4     | #252e5a | #ffffff       | #252e5a        | #ffffff              | #252e5a          | #ffffff                |
    And user change typography as "<KEY>"
      | KEY | Headings font | Capitalize headings | Body text font | Button text font | Capitalize buttons text |
      | 1   | Candara       | true                | Montserrat     | Candara          | true                    |
      | 2   | Arial         | false               | Futura         | Alice            | false                   |
    Then open shop on storefront
    And open page "<Page 1>"
    And verify UI by css as "<KEY>"
      | KEY | Description             | Section              | Element                                                                           | CSS value                |
      | 1   | Headings - font         |                      | //h2                                                                              | font-family>Candara      |
      | 1   | Capitalize headings     |                      | //h2                                                                              | text-transform>uppercase |
      | 1   | Body text - font        | Customer Testimonial | //div[@role='tabpanel'][1]//div[@class='testimonial-content']                     | font-family>Montserrat   |
      | 1   | Body text - font        | Featured Collection  | //div[contains(@class,'VueCarousel-slide')][1]//span[@itemprop='name']            | font-family>Montserrat   |
      | 1   | Body text - font        | Collection List      | //a[contains(@class,'collection-wrap')][1]//p[contains(@class,'collection-name')] | font-family>Montserrat   |
      | 1   | Body text - font        | Rich Text            | //div[contains(@class,'small-text')]                                              | font-family>Montserrat   |
      | 1   | Body text - font        | Newsletter           | //div[contains(@class,'announce-text')]                                           | font-family>Montserrat   |
      | 1   | Body text - font        | Footer               | //div[@data-name='Footer']//p                                                     | font-family>Montserrat   |
      | 1   | Body text - font        | Banner               | //section[@data-name='Banner']//p                                                 | font-family>Montserrat   |
      | 1   | Body text - font        | Featured Product     | //div[@class='product__details'][1]//a[not(contains(@class,'product__name'))]     | font-family>Montserrat   |
      | 1   | Button text - font      | Newsletter           | //section[@data-name='Newsletter']//button                                        | font-family>Candara      |
      | 1   | Button text - font      | Featured Product     | //div[@class='product__details'][1]//button[contains(@class,'btn-primary')]       | font-family>Candara      |
      | 1   | Button text - font      | Slideshow            | //section[@data-name='Slideshow']//a                                              | font-family>Candara      |
      | 1   | Button text - font      | Banner               | //section[@data-name='Banner']//a[contains(@class,'btn')]                         | font-family>Candara      |
      | 1   | Capitalize buttons text | Newsletter           | //section[@data-name='Newsletter']//button                                        | text-transform>uppercase |
      | 1   | Capitalize buttons text | Featured Product     | //div[@class='product__details'][1]//button[contains(@class,'btn-primary')]       | text-transform>uppercase |
      | 1   | Capitalize buttons text | Slideshow            | //section[@data-name='Slideshow']//a                                              | text-transform>uppercase |
      | 1   | Capitalize buttons text | Banner               | //section[@data-name='Banner']//a[contains(@class,'btn')][1]                      | text-transform>uppercase |

      | 2   | Headings - font         |                      | //h2                                                                              | font-family>Arial        |
      | 2   | Capitalize headings     |                      | //h2                                                                              | text-transform>          |
      | 2   | Body text - font        | Customer Testimonial | //div[@role='tabpanel'][1]//div[@class='testimonial-content']                     | font-family>Futura       |
      | 2   | Body text - font        | Featured Collection  | //div[contains(@class,'VueCarousel-slide')][1]//span[@itemprop='name']            | font-family>Futura       |
      | 2   | Body text - font        | Collection List      | //a[contains(@class,'collection-wrap')][1]//p[contains(@class,'collection-name')] | font-family>Futura       |
      | 2   | Body text - font        | Rich Text            | //div[contains(@class,'small-text')]                                              | font-family>Futura       |
      | 2   | Body text - font        | Newsletter           | //div[contains(@class,'announce-text')]                                           | font-family>Futura       |
      | 2   | Body text - font        | Footer               | //div[@data-name='Footer']//p                                                     | font-family>Futura       |
      | 2   | Body text - font        | Banner               | //section[@data-name='Banner']//p                                                 | font-family>Futura       |
      | 2   | Body text - font        | Featured Product     | //div[@class='product__details'][1]//a[not(contains(@class,'product__name'))]     | font-family>Futura       |
      | 2   | Button text - font      | Newsletter           | //section[@data-name='Newsletter']//button                                        | font-family>Alice        |
      | 2   | Button text - font      | Featured Product     | //div[@class='product__details'][1]//button[contains(@class,'btn-primary')]       | font-family>Alice        |
      | 2   | Button text - font      | Slideshow            | //section[@data-name='Slideshow']//a                                              | font-family>Alice        |
      | 2   | Button text - font      | Banner               | //section[@data-name='Banner']//a[contains(@class,'btn')]                         | font-family>Alice        |
      | 2   | Capitalize buttons text | Newsletter           | //section[@data-name='Newsletter']//button                                        | text-transform>          |
      | 2   | Capitalize buttons text | Featured Product     | //div[@class='product__details'][1]//button[contains(@class,'btn-primary')]       | text-transform>          |
      | 2   | Capitalize buttons text | Slideshow            | //section[@data-name='Slideshow']//a                                              | text-transform>          |
      | 2   | Capitalize buttons text | Banner               | //section[@data-name='Banner']//a[contains(@class,'btn')][1]                      | text-transform>          |
    And open page "<Page 2>"
    And verify UI by css as "<KEY>"
      | KEY | Description         | Element                                                                                            | CSS value                |
      | 1   | Headings - font     | //div[@data-name='Collection']//h1                                                                 | font-family>Candara      |
      | 1   | Capitalize headings | //div[@data-name='Collection']//h1                                                                 | text-transform>uppercase |
      | 1   | Body text - font    | //div[contains(@class,'mb24')][1]//span[@itemprop='name']                                          | font-family>Montserrat   |
      | 1   | Body text - font    | //div[contains(@class,'mb24')][1]//span[contains(@class,'money')]                                  | font-family>Montserrat   |
      | 2   | Headings - font     | //div[@data-name='Collection']//h1                                                                 | font-family>Arial        |
      | 2   | Capitalize headings | //div[@data-name='Collection']//h1                                                                 | text-transform>          |
      | 2   | Body text - font    | //div[contains(@class,'mb24')][1]//div[contains(@class,'product-details text-align-center')]//span | font-family>Futura       |
    And open page "<Page 3>"
    And verify UI by css as "<KEY>"
      | KEY | Description             | Element                                                                | CSS value                |
      | 1   | Headings - font         | //div[@id='detail-contents']//h1                                       | font-family>Candara      |
      | 1   | Capitalize headings     | //div[@id='detail-contents']//h1                                       | text-transform>uppercase |
      | 1   | Body text - font        | //div[@class='product__description no-padding']//span[not(parent::h4)] | font-family>Montserrat   |
      | 1   | Body text - font        | //*[contains(@class,'product__price')]                                 | font-family>Montserrat   |
      | 1   | Body text - font        | //div[@id='roller-section-footer']//div[@class='toggle_content']//div  | font-family>Montserrat   |
      | 1   | Button text - font      | //button[@id='add-to-cart']                                            | font-family>Candara      |
      | 1   | Capitalize buttons text | //button[@id='add-to-cart']                                            | text-transform>uppercase |

      | 2   | Headings - font         | //div[@id='detail-contents']//h1                                       | font-family>Arial        |
      | 2   | Capitalize headings     | //div[@id='detail-contents']//h1                                       | text-transform >         |
      | 2   | Body text - font        | //div[@class='product__description no-padding']//span[not(parent::h4)] | font-family>Futura       |
      | 2   | Body text - font        | //*[contains(@class,'product__price')]                                 | font-family>Futura       |
      | 2   | Body text - font        | //div[@id='roller-section-footer']//div[@class='toggle_content']//div  | font-family>Futura       |
      | 2   | Button text - font      | //button[@id='add-to-cart']                                            | font-family>Alice        |
      | 2   | Capitalize buttons text | //button[@id='add-to-cart']                                            | text-transform>          |
    And open page "<Page 4>"
    And verify UI by css as "<KEY>"
      | KEY | Description         | Element                                       | CSS value                |
      | 1   | Headings - font     | //div[@id='list-collections']//h1             | font-family>Candara      |
      | 1   | Headings - font     | //div[contains(@class,'mb32')][1]//h5         | font-family>Candara      |
      | 1   | Headings - font     | //h6                                          | font-family>Candara      |
      | 1   | Capitalize headings | //div[@id='list-collections']//h1             | text-transform>uppercase |
      | 1   | Capitalize headings | //h6                                          | text-transform>uppercase |
      | 1   | Capitalize headings | //div[contains(@class,'mb32')][1]//h5         | text-transform>uppercase |
      | 1   | Body text - font    | //div[@id='roller-section-footer']//p         | font-family>Montserrat   |
      | 1   | Body text - font    | //span[contains(@class,'breadcrumb_link')][1] | font-family>Montserrat   |

      | 2   | Headings - font     | //div[@id='list-collections']//h1             | font-family>Arial        |
      | 2   | Headings - font     | //div[contains(@class,'mb32')][1]//h5         | font-family>Arial        |
      | 2   | Headings - font     | //h6                                          | font-family>Arial        |
      | 2   | Capitalize headings | //div[@id='list-collections']//h1             | text-transform>          |
      | 2   | Capitalize headings | //h6                                          | text-transform>          |
      | 2   | Capitalize headings | //div[contains(@class,'mb32')][1]//h5         | text-transform>          |
      | 2   | Body text - font    | //div[@id='roller-section-footer']//p         | font-family>Futura       |
      | 2   | Body text - font    | //span[contains(@class,'breadcrumb_link')][1] | font-family>Futura       |
    And open page "<Page 5>"
    And verify UI by css as "<KEY>"
      | KEY | Description         | Element                                                           | CSS value                |
      | 1   | Headings - font     | //div[@id='collection']//h1                                       | font-family>Candara      |
      | 1   | Headings - font     | //h6                                                              | font-family>Candara      |
      | 1   | Capitalize headings | //div[@id='collection']//h1                                       | text-transform>uppercase |
      | 1   | Capitalize headings | //h6                                                              | text-transform>uppercase |
      | 1   | Body text - font    | //div[contains(@class,'mb24')][1]//span[@itemprop='name']         | font-family>Montserrat   |
      | 1   | Body text - font    | //div[contains(@class,'mb24')][1]//span[contains(@class,'money')] | font-family>Montserrat   |
      | 1   | Body text - font    | //span[contains(@class,'breadcrumb_link')][1]                     | font-family>Montserrat   |
      | 1   | Body text - font    | //option[@value='name:desc']                                      | font-family>Montserrat   |
      | 1   | Body text - font    | //div[normalize-space()='Sort by']                                | font-family>Montserrat   |

      | 2   | Headings - font     | //div[@id='collection']//h1                                       | font-family>Arial        |
      | 2   | Headings - font     | //h6                                                              | font-family>Arial        |
      | 2   | Capitalize headings | //div[@id='collection']//h1                                       | text-transform>          |
      | 2   | Capitalize headings | //h6                                                              | text-transform>          |
      | 2   | Body text - font    | //div[contains(@class,'mb24')][1]//span[@itemprop='name']         | font-family>Futura       |
      | 2   | Body text - font    | //div[contains(@class,'mb24')][1]//span[contains(@class,'money')] | font-family>Futura       |
      | 2   | Body text - font    | //span[contains(@class,'breadcrumb_link')][1]                     | font-family>Futura       |
      | 2   | Body text - font    | //option[@value='name:desc']                                      | font-family>Futura       |
      | 2   | Body text - font    | //div[normalize-space()='Sort by']                                | font-family>Futura       |

    Examples:
      | KEY | Page 1 | Page 2           | Page 3              | Page 4       | Page 5              |
      | 1   | /      | /collections/all | MC White Lace Dress | /collections | /collections/summer |
      | 2   | /      | /collections/all | MC White Lace Dress | /collections | /collections/summer |




