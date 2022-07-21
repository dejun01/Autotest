Feature: Home Page

#  Verify: Featured collection, collection list, Customer testimonial, Featured video
# Theme Roller
#  Env:
# prod_sbase_roller_theme
# staging_sbase_roller_theme
#  dev_sbase_roller_theme


#  ============== Featured collection and collection list =========================
  Scenario Outline: featured collection and collection list #SB_OLS_THE_ROL_43 #SB_OLS_THE_ROL_44 #SB_OLS_THE_ROL_45 #SB_OLS_THE_ROL_46 #SB_OLS_THE_ROL_47 #SB_OLS_THE_ROL_52 #SB_OLS_THE_ROL_53 #SB_OLS_THE_ROL_54 #SB_OLS_THE_ROL_55
    Given user login to shopbase dashboard
    And close live chat
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    And select section "Featured Collection"
    Then change featured collection setting as "<KEY>"
      | KEY | Heading             | Collection | Collection layout | Show View more button | View more label |
      | 1   | Featured Collection | Fashion    | Slider            | true                  | Show more       |
      | 2   | Best seller         | Men        | Grid              | false                 | View more       |
    And select section "Collection list"
    When change collection list setting as "<KEY>"
      | KEY | Heading         | Collection | Image            | Alt text    |
      | 1   | All Collections | Fashion    | /front/logo2.jpg | collection1 |
      | 1   |                 | Winter     |                  |             |
      | 1   |                 | Spring     |                  |             |
      | 1   |                 | Summer     |                  |             |
      | 2   |                 | Men        |                  |             |
    And wait 5 second
    Then open shop on storefront
    And verify featured collection on store front "<KEY>"
      | KEY | Heading             | Collection | Collection layout | Show View more button | View more label |
      | 1   | Featured Collection | Fashion    | Slider            | true                  | Show more       |
      | 2   | Best seller         | Men        | Grid              | false                 |                 |
    And verify collection list on store front "<KEY>"
      | KEY | Heading         | Collection                   |
      | 1   | All Collections | Fashion,Winter,Spring,Summer |
      | 2   |                 | Men                          |
    Examples:
      | KEY |
      | 1   |
      | 2   |


# ============= Customer testimonial ====================================
  Scenario Outline: Verify content Customer Testimonial section #SB_OLS_THE_ROL_38
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    And change Customer Testimonial settings as "<KEY>"
      | KEY | Testimonial title    | Text animation | Background image | Alt text | Use image | Darken background image | Testimonial                 | Customer name | Store name | Type link   | Link   | Text alignment |
      | 1   | Test 1               | Fade down      | front/slide2.png | shopbase | true      | false                   | Test Customer Testimonial 1 | Name1         | Test1      | Collections | Winter | Right          |
      | 2   | Test 2               | Fade in        | front/slide2.png |          | false     | true                    | Test Customer Testimonial 2 | Name2         | Test2      | Collections | Summer | Centre         |
      | 3   | Customer Testimonial | Fade up        | front/slide2.png | shopbase | true      | false                   | Lorem ipsum dolor sit amet  | Jonh Doe      | Test1      | Collections | Summer | Left           |
      | 4   | Customer Testimonial | None           | front/slide2.png |          | false     | true                    | Lorem ipsum dolor sit amet  | Jonh Doe      | Test2      | Collections | Summer | Centre         |
    And wait 5 second
    When open shop on storefront
    Then verify content Customer Testimonial section as "<KEY>"
      | KEY | Testimonial title    | Text animation | Alt text | Use image | Darken background image | Testimonial                 | Customer name | Store name | Link   | Text alignment |
      | 1   | Test 1               | Fade down      | shopbase | true      | false                   | Test Customer Testimonial 1 | Name1         | Test1      | Winter | right          |
      | 2   | Test 2               | Fade in        |          | false     | true                    | Test Customer Testimonial 2 | Name2         | Test2      | Summer | center         |
      | 3   | Customer Testimonial | Fade up        | shopbase | true      | false                   | Lorem ipsum dolor sit amet  | Jonh Doe      | Test1      | Summer | left           |
      | 4   | Customer Testimonial | None           |          | false     | true                    | Lorem ipsum dolor sit amet  | Jonh Doe      | Test2      | Summer | center         |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |


  Scenario: Verify CSS Customer Testimonial section #SB_OLS_THE_ROL_38 #SB_OLS_THE_ROL_39 #SB_OLS_THE_ROL_40 #SB_OLS_THE_ROL_41 #SB_OLS_THE_ROL_42
    When open shop on storefront
    Then verify UI by css as "<KEY>"
      | KEY | Description         | Element                                                                                                     | CSS value                                                                                                                                                                                                                    |
      | 1   | Testimonial         | //div[@class='container testimonial-container']                                                             | padding>0;max-width>1170px;width>100%;margin-right>auto;margin-left>auto;box-sizing>border-box;font-family>"Muli",sans-serif;font-size>14px;font-weight>400;font-style>normal;line-height>1.5;color>#1a1a1a;text-align>left; |
      | 1   | Heading             | //div[@class='container testimonial-container']//p[@class='h3 text-align-center mb24']                      | margin-bottom>24px;text-align>center;font-size>1.71429em;font-family>"Montserrat",sans-serif;font-weight>400;font-style>normal;ext-transform>uppercase;margin-top>0;line-height>1.3;color>#1a1a1a;                           |
      | 1   | Block               | //div[@class='container testimonial-container']//div[@role='tabpanel']                                      | opacity>1;height>296px;position>relative;flex-basis>inherit;                                                                                                                                                                 |
      | 1   | Content             | //div[@class='testimonial-wrap w-100 text-align-center testimonial-wrap--fade-up testimonial-wrap--darken'] | opacity>1;transform>translateY(0);color>#fff;position>absolute;left>0;bottom>0;text-align>center;                                                                                                                            |
      | 1   | testimonial content | //div[@class='testimonial-content']                                                                         | padding>0 44px 32px;                                                                                                                                                                                                         |
      | 1   | Testimonial text    | //div[@class='testimonial-content']//div                                                                    | color>#fff;text-align>center;font-family>"Muli",sans-serif;font-size>14px;ine-height>1.5;font-style>normal;                                                                                                                  |
      | 1   | Customer            | //div[@class='testimonial-content']//p                                                                      | text-transform>uppercase;margin-top>0;margin-bottom>1rem;color>#fff;text-align>center;                                                                                                                                       |
      | 1   | Store               | //div[@class='testimonial-content']//a                                                                      | color>#fff;text-decoration>underline;text-align>center;                                                                                                                                                                      |


#    ==================== Featured video ===============================
  Scenario Outline: Featured Video #SB_OLS_THE_ROL_65 #SB_OLS_THE_ROL_66 #SB_OLS_THE_ROL_67 #SB_OLS_THE_ROL_68 #SB_OLS_THE_ROL_69
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    And change video settings as "<KEY>"
      | KEY | Autoplay video | Video link                                                                   | Image           | Preheading   | Heading   | Subheading   | First button label | First button link           | Highlight first button | Second button label | Second button link          | Highlight Second button | Display solid text background | Text position | Text alignment | Enable background transparency |
      | 1   | true           |                                                                              |                 |              |           |              | First button 1     | Collections>All Collections | false                  | First button 1      | Collections>All Collections | false                   | false                         | Left          | Left           | false                          |
      | 2   | true           | https://www.youtube.com/watch?v=2GwhdLY2f3E&list=RD2GwhdLY2f3E&start_radio=1 | front/logo2.jpg | PREHEADING 2 | HEADING 2 | SUBHEADING 2 | First button 2     | Collections>All Collections | true                   | Second button 2     | Collections>All Collections | true                    | true                          | Centre        | Centre         | true                           |
      | 3   | false          | https://www.youtube.com/watch?v=O71GdeeND68                                  | front/logo3.png | PREHEADING 3 | HEADING 3 | SUBHEADING 3 | First button 3     | Products>All Products       | true                   | Second button 3     | https://www.shopbase.com/   | true                    | true                          | Right         | Right          | true                           |
      | 4   | false          | https://www.youtube.com/watch?v=O71GdeeND68                                  | front/logo4.png | PREHEADING 4 | HEADING 4 | SUBHEADING 4 | First button 4     | https://www.shopbase.com/   | false                  | Second button 4     | Products>All Products       | false                   | false                         | Left          | Left           | false                          |
    Then open shop on storefront
    And verify video on storefront "<KEY>"
      | KEY | Autoplay video | Video link  | Image           | Preheading   | Heading   | Subheading   | First button label | First button link         | Highlight first button | Second button label | Second button link        | Highlight Second button | Display solid text background | Text position | Text alignment | Enable background transparency |
      | 1   | true           |             |                 |              |           |              | First button 1     | collections               | false                  | First button 1      | collections               | false                   | false                         | Left          | Left           | false                          |
      | 2   | true           | 2GwhdLY2f3E | front/logo2.jpg | PREHEADING 2 | HEADING 2 | SUBHEADING 2 | First button 2     | collections               | true                   | First button 2      | collections               | true                    | true                          | Centre        | Centre         | true                           |
      | 3   | false          | O71GdeeND68 | front/logo3.png | PREHEADING 3 | HEADING 3 | SUBHEADING 3 | First button 3     | collections/all           | true                   | First button 3      | https://www.shopbase.com/ | true                    | true                          | Right         | Right          | true                           |
      | 4   | false          | O71GdeeND68 | front/logo4.png | PREHEADING 4 | HEADING 4 | SUBHEADING 4 | First button 4     | https://www.shopbase.com/ | false                  | First button 4      | collections/all           | false                   | false                         | Left          | Left           | false                          |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |