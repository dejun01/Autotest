Feature: Home Page

#  Theme bassy

#  Env: prod_sbase_homepage_bassy
#  prodtest_sbase_homepage_bassy
#  staging_sbase_homepage_bassy


  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"

#  =========================== Header ==================================
  Scenario Outline: Header #SB_OLS_THE_BAS_1
    And select section "Header"
    And change header settings for bassy as "<KEY>"
      | KEY | Logo            | Alt text   | Menu      | Enable search | Show announcement | Home page only | Text                      | Button link                 | Bar     | Text color |
      | 001 | front/logo2.jpg | Alt text 1 | Main menu | true          | false             | true           | Announce something here 1 | Collections>All Collections | #BD4A4A | #E0D483    |
      | 002 | front/logo3.png | Alt text 2 | Main menu | false         | true              | false          | Announce something here 2 | Products>All Products       | #E0D483 | #BD4A4A    |
    Given open shop on storefront
    Then verify header for bassy "<KEY>"
      | KEY | Logo            | Alt text   | Menu      | Enable search | Show announcement | Home page only | Text                      | Button link      | Bar                | Text color         |
      | 001 | front/logo2.jpg | Alt text 1 | Main menu | true          | false             | true           | Announce something here 1 | /collections     | rgb(189, 74, 74)   | rgb(224, 212, 131) |
      | 002 | front/logo3.png | Alt text 2 | Main menu | false         | true              | false          | Announce something here 2 | /collections/all | rgb(224, 212, 131) | rgb(189, 74, 74)   |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |

#  =========================== Footer ==================================
  Scenario Outline: Footer #SB_OLS_THE_BAS_8
    And select section "Footer"
    And change Footer settings for bassy as "<KEY>"
      | KEY | Footer menu | Show payment method icons | Custom text                       | Enable Social Profile | Facebook                  | Youtube                  | Pinterest                  | Image           | Alt text   |
      | 001 | Main menu   | true                      | 1-541-754-3010 shopbase@gmail.com | true                  | https://www.facebook.com/ | https://www.youtube.com/ | https://www.pinterest.com/ | front/logo2.jpg | Alt text 1 |
      | 001 |             |                           |                                   |                       |                           |                          |                            | front/logo3.png | Alt text 2 |
      | 001 |             |                           |                                   |                       |                           |                          |                            | front/logo2.jpg | Alt text 3 |
      | 002 | None        | false                     | shopbase@gmail.com                | false                 |                           |                          |                            | front/logo3.png | Alt text 4 |
    Given open shop on storefront
    Then verify Footer for Bassy "<KEY>"
      | KEY | Footer menu | Show payment method icons | Custom text                       | Enable Social Profile | Image           | Alt text   |
      | 001 | Main menu   | true                      | 1-541-754-3010 shopbase@gmail.com | true                  | front/logo2.jpg | Alt text 1 |
      | 002 | None        | false                     | shopbase@gmail.com                | false                 | front/logo3.png | Alt text 4 |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |

#  =========================== Slideshow ==================================
  Scenario Outline: Slideshow #SB_OLS_THE_BAS_2
    And select section "Slideshow"
    And change slideshow settings for bassy as "<KEY>"
      | KEY | Auto-rotate slides | Change slides every | Image             | Alt text | Image position | Text alignment | Heading      | Subheading                 | Button label    | Button link                 | Text color | Button label color | Button color |
      | 001 | true               | 5s                  | front/Banner.jpg  | slide 1  | Top right      | Left           | Slide Show 1 | Introduction about store 1 | View products 1 | Products>All Products       | #BD4A4A    | #A8E388            | #CC5CA2      |
      | 002 | false              |                     | front/Banner2.jpg | slide 2  | Bottom center  | Center         | Slide Show 2 | Introduction about store 2 | View products 2 | Collections>All Collections | #E0D483    | #F1A2A2            | #8BE5E7      |
      | 003 | true               |                     | front/Banner.jpg  | slide 3  | Bottom center  | Right          | Slide Show 3 | Introduction about store 3 | View products 3 | Collections>Best Selling    | #E0D483    | #F1A2A2            | #8BE5E7      |
    Given open shop on storefront
    Then verify slideShow for bassy "<KEY>"
      | KEY | Auto-rotate slides | Change slides every | Image             | Alt text | Image position | Text alignment | Heading      | Subheading                 | Button label    | Button link               | Text color         | Button label color | Button color       |
      | 001 | true               | 5s                  | front/Banner.jpg  | slide 1  | Top right      | left           | Slide Show 1 | Introduction about store 1 | View products 1 | /collections/all          | rgb(189, 74, 74)   | rgb(168, 227, 136) | rgb(204, 92, 162)  |
      | 002 | false              |                     | front/Banner2.jpg | slide 2  | Bottom center  | center         | Slide Show 2 | Introduction about store 2 | View products 2 | /collections              | rgb(224, 212, 131) | rgb(241, 162, 162) | rgb(139, 229, 231) |
      | 003 | true               |                     | front/Banner.jpg  | slide 3  | Bottom center  | Right          | Slide Show 3 | Introduction about store 3 | View products 3 | /collections/best-selling | rgb(224, 212, 131) | rgb(241, 162, 162) | rgb(139, 229, 231) |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |


#    ======================== Introduction text ====================================
  Scenario Outline: Introduction text #SB_OLS_THE_BAS_6
    And select section "Introduction text"
    And change introduction text settings for bassy as "<KEY>"
      | KEY | Heading                  | Body                                                                         |
      | 001 | Introduction about store | Describe a product, share announcements, or welcome customers to your store. |
      | 002 |                          |                                                                              |
    Given open shop on storefront
    Then verify introduction text for bassy "<KEY>"
      | KEY | Heading                  | Body                                                                         |
      | 001 | Introduction about store | Describe a product, share announcements, or welcome customers to your store. |
      | 002 |                          |                                                                              |
    Examples:
      | KEY |
      | 001 |
      | 002 |


#    ================================= Featured collection ==================================
  Scenario Outline: Featured collection #SB_OLS_THE_BAS_4
    And select section "Featured collection"
    And change featured collection setting as "<KEY>"
      | KEY | Heading                | Collection             |
      | 001 | Featured Collection    | collection 1           |
      | 002 | Unavailable collection | unavailable collection |
      | 003 | Not select collection  |                        |
    Given open shop on storefront
    And verify featured collection on store front "<KEY>"
      | KEY | Heading                | Collection   |
      | 001 | Featured Collection    | collection 1 |
      | 002 | Unavailable collection |              |
      | 003 | Not select collection  |              |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |


#    =============================== Video =================================
  Scenario Outline: Video #SB_OLS_THE_BAS_7
    And select section "Video"
    And change video setting for bassy as "<KEY>"
      | KEY | Settings       | Video link                                  |
      | 001 | Featured video | https://www.youtube.com/watch?v=O71GdeeND68 |
      | 002 | No link video  |                                             |
    Given open shop on storefront
    And verify video on store front for bassy "<KEY>"
      | KEY | Settings       | Video link  |
      | 001 | Featured video | O71GdeeND68 |
      | 002 | No link video  |             |
    Examples:
      | KEY |
      | 001 |
      | 002 |


#    ===================== Newsletter ====================================
  Scenario Outline: Newsletter #SB_OLS_THE_BAS_3
    And change Newsletter settings as "<KEY>"
      | KEY | Heading                     | Subheading                                                           |
      | 1   | Heading newsletter          | Subheading newsletter                                                |
      | 2   | Subscribe to our newsletter | A short sentence describing what someone will receive by subscribing |
    Given open shop on storefront
    And verify Newsletter settings as "<KEY>"
      | KEY | Heading                     | Subheading                                                           | Email                 | Messages                    |
      | 1   | Heading newsletter          | Subheading newsletter                                                | huong                 | Please enter an valid email |
      | 2   | Subscribe to our newsletter | A short sentence describing what someone will receive by subscribing | signup@mailtothis.com | Thank you for subscribing   |
    Examples:
      | KEY |
      | 001 |
      | 002 |


#    =================== Collection list =======================
  Scenario Outline: Collection list #SB_OLS_THE_BAS_5
    And select section "Collection list"
    When change collection list setting as "<KEY>"
      | KEY | Heading                | Collection             |
      | 1   | All Collections        | Fashion                |
      | 1   |                        | Winter                 |
      | 1   |                        | Spring                 |
      | 1   |                        | Summer                 |
      | 2   | Collection unavailable | Spring                 |
      | 2   | Collection unavailable | unavailable collection |
      | 3   |                        | Men                    |
    Given open shop on storefront
    And verify collection list on store front "<KEY>"
      | KEY | Heading                | Collection                   |
      | 1   | All Collections        | Fashion,Winter,Spring,Summer |
      | 2   | Collection unavailable | Spring                       |
      | 3   |                        | Men                          |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |