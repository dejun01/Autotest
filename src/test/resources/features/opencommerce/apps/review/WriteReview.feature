Feature: Delete review, Write review, Edit review, Import reviews

#  Env:
#  staging_sbase_review2
#  prod_sbase_review2
#  dev_sbase_review2

#  Theme: Inside, Roller
#  prodcuts: V-neck T-shirt, MC White Lace Dress, Mini Pallet Coasters
#  Setting: uncheck show sticky button in theme editor


  Scenario: Delete review
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    When user navigate to "All reviews" screen
    When user delete all reviews
    Then verify deleted all reviews
    And open page "/pages/all-reviews"
    And verify review deleted on SF

  Scenario Outline: Write reviews #SB_SF_RV_21
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user click Settings on menu
    And update Reviews settings as "<KEY>"
      | KEY | Auto-publish reviews | Minimum star | Auto-block reviews |
      | 1   | false                | 2 stars      |                    |
      | 2   | true                 | 4 stars      |                    |
      | 3   | true                 | 4 stars      | bad                |
    And open shop on storefront
    And search and select the product "<Product name>"
    When write reviews as "<KEY>"
      | KEY | Rating | Title        | Review                | Image                 | Your name | Your email      | Type           | Result  | Message                      |
      | 1   | 5      | Review 1     | Great product quality | front/imagereview.png | name1     | name1@gmail.com | Product review | Success | Thank you for your feedback! |
      | 2   | 1      | Review 2     | bad                   | front/imagereview.png | name1     | name1@gmail.com | Store review   | Success | Thank you for your feedback! |
      | 2   | 2      | Review 3     | bad                   |                       | name2     | name2@gmail.com | Product review | Success | Thank you for your feedback! |
      | 2   | 5      | Review 4     | Great product quality | front/imagereview.png | name4     | name4@gmail.com | Product review | Success | Thank you for your feedback! |
      | 3   | 5      | Review 4 bad | Low shipping          | front/imagereview.png | name4     | name4@gmail.com | Product review | Success | Thank you for your feedback! |
      | 3   | 5      | Review 5     | bad                   | front/imagereview.png | name5     | name5@gmail.com | Store review   | Success | Thank you for your feedback! |
      | 3   | 5      | Review 6     | Great product quality | front/imagereview.png | name6     | name6@gmail.com | Product review | Success | Thank you for your feedback! |
    Then verify reviews are show on SF as "<KEY>"
      | KEY | Title        | Show review on SF | Rating | Content               | Your name | Type            |
      | 1   | Review 1     | false             |        |                       |           | Product reviews |
      | 2   | Review 2     | false             | 1      | bad                   | name2     | Store reviews   |
      | 2   | Review 3     | false             | 2      |                       |           | Product reviews |
      | 2   | Review 4     | true              | 5      | Great product quality | name4     | Product reviews |
      | 3   | Review 4 bad | false             |        |                       |           | Product reviews |
      | 3   | Review 5     | false             |        |                       |           | Store reviews   |
      | 3   | Review 6     | true              | 5      | Great product quality | name6     | Product reviews |
    Given user open dashboard
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "All reviews" screen
    And verify list review on dashboard as "<KEY>"
      | KEY | Title        | Content               | Status      | Your name | Rating |
      | 1   | Review 1     | Great product quality | Unpublished | name1     | 5      |
      | 2   | Review 2     | bad                   | Unpublished | name1     | 1      |
      | 2   | Review 3     | bad                   | Unpublished | name2     | 2      |
      | 2   | Review 4     | Great product quality | Published   | name4     | 5      |
      | 3   | Review 4 bad | Low shipping          | Unpublished | name4     | 5      |
      | 3   | Review 5     | bad                   | Unpublished | name5     | 5      |
      | 3   | Review 6     | Great product quality | Published   | name6     | 5      |
    And close browser
    Examples:
      | KEY | Product name        |
      | 1   | V-neck T-shirt      |
      | 2   | V-neck T-shirt      |
      | 3   | MC White Lace Dress |

  Scenario Outline: Edit reviews #SB_SF_RV_15
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    When user navigate to "All reviews" screen
    And search and select reviews as "<KEY>"
      | KEY | Name                  |
      | 1   | Review 4              |
      | 2   | Title review 4 edited |
    And edit reviews as "<KEY>"
      | KEY | Name            | Rating | Title                 | Body                            | Review type    | Product             |
      | 1   | Review 4 edited | 4      | Title review 4 edited | Very good                       | Product review | MC White Lace Dress |
      | 2   | Review 4        | 5      | Title review 4        | Very good,Great product quality | Store review   |                     |
    Then open shop on storefront
    And search and select the product "MC White Lace Dress"
    And verify change review on Product page as "<KEY>"
      | KEY | Review type    | Name            | Rating | Title                 | Body                            |
      | 1   | Product review | Review 4 edited | 4      | Title review 4 edited | Very good                       |
      | 2   | Store review   | Review 4        | 5      | Title review 4        | Very good,Great product quality |
    Examples:
      | KEY |
      | 1   |
      | 2   |

  Scenario Outline: Write review with fields invalid #SB_SF_RV_22 #SB_SF_RV_23
    Given open shop on storefront
    And search and select the product "MC White Lace Dress"
    When write reviews as "<KEY>"
      | KEY | Rating | Title  | Review      | Image                       | Your name | Your email         | Type           | Result        | Message                                                                           |
      | 1   | 5      | Title1 |             | front/imagereview.png       |           | shopbase@gmail.com | Product review | require error | Can't be blank, please fill out this field                                        |
      | 2   | 5      | Title2 | Review body | front/imagereview.png       |           | shopbase@gmail.com | Product review | require error | Can't be blank, please fill out this field                                        |
      | 3   | 5      | Title3 | Review body | front/imagereview.png       | shopbase  |                    | Product review | require error | Can't be blank, please fill out this field                                        |
      | 4   | 5      |        | Review body | front/ReviewIMG_bigsize.jpg | shopbase  | shopbase@gmail.com | Product review | image error   | Your image files are too large. Please resize or upload images under 5 megabytes. |
    Examples:
      | KEY | Description        |
      | 1   | Review = blank     |
      | 2   | Your name = blank  |
      | 3   | Your email = blank |
      | 4   | image size > 5MB   |

  Scenario: Verify UI review on storefront
    When open shop on storefront
    Then verify UI by css as "<KEY>"
      | KEY | Description | Element                                                                     | CSS value                                                                                                         |
      | 1   | Rating      | //div[@class='rv-flex rv-j-content-center rv-widget__carousel-header-star'] | margin-bottom>16;justify-content>center;display>flex                                                              |
      | 1   | Star        | //div[@class='review-icon-symbols']                                         | width>20;height>20;margin-right>6                                                                                 |
      | 1   | Link        | //a[@class='rv-widget__carousel-header-link']                               | font-size>14;font-weight>500;letter-spacing>0;line-height>22;color>#D0021A;font-family>Arial;text-align>center    |
      | 1   | Content     | //div[@data-review='background-side']                                       | position>relative;border>1px solid rgba(0,0,0,0.08);padding>24;background-color>#4999E8                           |
      | 1   | Title       | //div[@class='rv-widget__body-heading']                                     | text-overflow>ellipsis;margin-bottom>8;font-size>16;font-weight>600;letter-spacing>0;line-height>22;color>#141414 |
      | 1   | Body        | //div[@class='rv-widget__body-post']                                        | margin-bottom>8;font-size>14;font-weight>400;letter-spacing>0;line-height>22;color>#606060                        |
      | 1   | Author      | //div[@class='rv-widget__review-author rv-flex']                            | margin-bottom>8;font-size>14;font-weight>400;letter-spacing>0;line-height>22;color>#606060                        |
      | 1   | Avatar      | //div[@class='rv-widget__review-author rv-flex']//img                       | width>40p;height>40;margin-right>12                                                                               |
      | 1   | Name        | //div[@class='rv-widget__author-info--name']                                | font-size>14;font-weight>600;letter-spacing>0;line-height>22;color>#606060                                        |
      | 1   | Date        | //div[@class='rv-widget__author-info--date']                                | font-size>12;font-weight>400;letter-spacing>0;line-height>18;color>#bcbcbc                                        |
    When search and select the product "V-neck T-shirt"
    Then verify UI by css as "<KEY>"
      | KEY | Description     | Element                                                                                        | CSS value                                                                                                                                 |
      | 1   | Widget          | //div[@class='rv-widget rv-widget--widget-card']                                               | padding>24px 0;background-color>transparent;border-top>1px solid #dadada                                                                  |
      | 1   | Overall ratting | //div[@class='app-flex app-items-center rv-widget__header-summary']                            | align-items>center;display>flex;font-family>Arial                                                                                         |
      | 1   | Title rating    | //div[@class='rv-widget__overall-text']                                                        | font-family>Arial;font-size>21px;font-weight>600;letter-spacing>0;line-height>28px;color>#141414;margin-right>4px                         |
      | 1   | Link            | //div[@class='rv-widget__overall-store']//a                                                    | font-size>14px;font-weight>400;letter-spacing>0;line-height>22px;color>#D0021A                                                            |
      | 1   | Rating          | //div[@class='rv-flex rv-a-item-center rv-flex-1 rv-widget__summary-proportion']               | margin>0 12px;max-width>240px;align-items>center                                                                                          |
      | 1   | Button          | //button[@class='rv-widget__btn rv-secondary']                                                 | width>auto;display>flex;justify-content>center;align-items>center;border>1px solid #D0021A;background-color>#fff                          |
      | 1   | Filter          | //div[@class='rv-widget__filter-widget rv-widget__filter-widget-new rv-flex rv-a-item-center'] | justify-content>space-between;padding>8px 0;margin>24px 0;border-top>1px solid #dadada;border-bottom>1px solid #dadada;align-items>center |
      | 1   | Rating          | //div[@class='app-mr8 app-flex']                                                               | margin-right>8px;display>flex                                                                                                             |
      | 1   | Star            | //div[@class='review-icon-symbols']                                                            | width>12;height>12;margin-right>4                                                                                                         |
      | 1   | Content         | //div[@class='rv-widget__post rv-widget__post-arrow']                                          | position>relative;background-color>#AAA9A9;padding>24px;display>flex;flex-direction>column;flex>1;                                        |
      | 1   | Title           | //div[@class='rv-widget__post-heading']                                                        | margin-bottom>0;font-size>15px;font-weight>600;letter-spacing>0;line-height>normal;color>#303030;margin>0 0 8px                           |
      | 1   | Body            | //div[@class='rv-widget__post-content']                                                        | color>#606060;font-size>14px;font-weight>400;letter-spacing>0;line-height>20px;margin>0                                                   |
      | 1   | Image           | //div[@class='rv-widget__post rv-widget__post-arrow']//img                                     | width>auto;height>auto;max-width>56px;max-height>56px;object-fit>contain;margin>0;padding>0                                               |
      | 1   | Author          | //div[@class='app-flex rv-widget__user']                                                       | margin-right>16;width>170                                                                                                                 |
      | 1   | Avatar          | //div[@class='rv-widget__avatar']//img                                                         | width>48px;height>48px;min-width>48px;min-height>48px;border-radius>50%;object-fit>contain                                                |
      | 1   | Name            | //div[@class='rv-widget__author']                                                              | color#606060;font-size14px;font-weight600;letter-spacing0;line-height20px;white-spacenowrap;text-overflowellipsis;overflow>hidden         |
      | 1   | Date            | //time[@class='rv-widget__date']                                                               | font-size>12px;font-weight>500;letter-spacing>0;line-height>18px;color>#bcbcbc                                                            |
    When click button write review
    Then verify UI by css as "<KEY>"
      | KEY | Description   | Element                                                                      | CSS value                                                                                                                                                                                                                                    |
      | 1   | Form          | //div[@class='rv-widget__form']                                              | padding>0;max-width>555px;margin>24px auto 32px;box-sizing>border-box;font-family>Arial                                                                                                                                                      |
      | 1   | Form heading  | //div[@class='rv-flex rv-a-item-center rv-widget__form-heading']             | justify-content>space-between;margin>0 0 24px;align-items>center;display>flex;box-sizing>border-box                                                                                                                                          |
      | 1   | Heading       | //div[@class='rv-widget__form-title']                                        | font-size>18px;font-weight>600;letter-spacing>0;line-height>24px;color>#141414                                                                                                                                                               |
      | 1   | Close icon    | //div[@class='rv-widget__form-close']                                        | font-size>12px;font-weight>600;letter-spacing>0;line-height>20px;position>relative;color>#bcbcbc;text-transform>uppercase;cursor>pointer;padding>0 18px 0 0                                                                                  |
      | 1   | Rating form   | //div[@class='rv-flex rv-a-item-center rv-wrap rv-widget__form-rating-star'] | border>1px solid #dadada;border-radius>6;padding>16                                                                                                                                                                                          |
      | 1   | Star          | //div[@class='rv-widget__form']//div[@class='review-icon-symbols']           | width>24;height>24;margin-right>4;cursor>pointer                                                                                                                                                                                             |
      | 1   | Feeling       | //div[@class='rv-widget__form-feeling rv-widget__form-feeling-5']            | color>#20a848;font-size>16;font-weight>600;letter-spacing>0;line-height>22;margin-left>12                                                                                                                                                    |
      | 1   | Group field   | //div[@class='rv-widget__group-field']                                       | margin-bottom>16px;font-family>Arial;box-sizing>border-box                                                                                                                                                                                   |
      | 1   | Label field   | //label[contains(@class,'rv-widget__form-label')]                            | font-size>13;font-weight>600;letter-spacing>0;line-height>20;display>block;color>#606060;margin>0 0 4;font-family>Arial;box-sizing>border-box                                                                                                |
      | 1   | Input field   | //input[@class='rv-widget__input']                                           | border>1px solid rgba(20,20,20,0.24);border-radius>2px;box-shadow>none;min-height>32px;padding>6px 8px;background-color>transparent;color>#363636;max-width>100%;width>100%;font-size>13px;font-weight>500;letter-spacing>0;line-height>20px |
      | 1   | Image field   | //div[@class='rv-widget__form']//li                                          | width>56px;height>56px;background>#fcfaf8;border>1px solid #dadada;border-radius>4px;position>relative                                                                                                                                       |
      | 1   | Radio button  | //div[@class='rv-widget__radio rv-flex']//span[@class='box']                 | color>#606060;position>relative;width>16;height>16;border-radius>50%;display>inline-block;margin-right>8                                                                                                                                     |
      | 1   | Submit button | //button[@class='rv-widget__btn rv-primary']                                 | width>100%;background-color>#D0021A;font-size>15px;font-weight>600;letter-spacing>0;line-height>22px;color>#fff;border-radius>2px;padding>9px 20px;outline>none;box-shadow>none;border>none;position>relative                                |
      | 1   | Label button  | //button[@class='rv-widget__btn rv-primary']//span                           | color>#fff;font-size>15px;font-weight>600;letter-spacing>0;line-height>22                                                                                                                                                                    |
    And add products "V-neck T-shirt" to cart
    Then click to button checkout
    Then verify UI by css as "<KEY>"
      | KEY | Description | Element                                                             | CSS value                                                                                                                                                                               |
      | 1   | Carousel    | //div[@class='rv-flex rv-direction-column rv-widget__listing-body'] | width>100%;height>100%;padding>0;flex-direction>column;display>flex;box-sizing>border-box;                                                                                              |
      | 1   | Content     | //div[@data-review='background-side']                               | border>1px solid rgba(0,0,0,0.08);background-color>transparent;padding>24;text-align>center                                                                                             |
      | 1   | Star        | //div[@class='review-icon-symbols']                                 | width>12;height>12;margin-right>2                                                                                                                                                       |
      | 1   | Title       | //div[@class='rv-widget__body-heading']                             | white-space>nowrap;overflow>hidden;text-overflow>ellipsis;margin-bottom>8px;font-size>16px;font-weight>600;letter-spacing>0;line-height>22px;color>#141414 !important;text-align>center |
      | 1   | Body        | //div[@class='rv-widget__body-post']                                | color>#606060;margin-bottom>8px;font-size>14px;font-weight>400;letter-spacing>0;line-height>22px                                                                                        |
      | 1   | Author      | //div[@class='rv-widget__review-author rv-text-center']             | margin-top>16;text-align>center                                                                                                                                                         |
      | 1   | Avatar      | //div[@class='rv-widget__review-author rv-text-center']/img         | margin>0 auto;margin-bottom>4;width>40;height>40;display>block                                                                                                                          |
      | 1   | Name        | //div[@class='rv-widget__author-info--name']                        | font-size>14px;font-weight>600;letter-spacing>0;line-height>22px;color>#606060 !important;text-align>center                                                                             |
      | 1   | Date        | //div[@class='rv-widget__author-info--date']                        | font-size>12px;font-weight>400;letter-spacing>0;line-height>18px;color>#bcbcbc;text-align>center                                                                                        |
    When open page "/pages/all-reviews"
    Then verify UI by css as "<KEY>"
      | KEY | Description     | Element                                                                                                                              | CSS value                                                                                                                            |
      | 1   | Widget          | //div[@class='rv-widget rv-widget--widget-card']                                                                                     | border-top>0;padding>24px 0;background-color>transparent;box-sizing>border-box                                                       |
      | 1   | Overall ratting | //div[@class='app-flex app-items-center rv-widget__header-summary']                                                                  | align-items>center;display>flex;font-family>Arial                                                                                    |
      | 1   | Title rating    | //div[@class='rv-widget__overall-text']                                                                                              | font-family>Arial;font-size>21px;font-weight>600;letter-spacing>0;line-height>28px;color>#141414;margin-right>4px                    |
      | 1   | Rating          | //div[@class='rv-flex rv-a-item-center rv-flex-1 rv-widget__summary-proportion']                                                     | margin>0 12px;max-width>240px;align-items>center                                                                                     |
      | 1   | Button          | //button[@class='rv-widget__btn rv-secondary']                                                                                       | width>auto;display>flex;justify-content>center;align-items>center;border>1px solid #D0021A;background-color>#fff                     |
      | 1   | Filter          | //div[@class='rv-widget__filter-widget rv-widget__filter-widget-new rv-flex rv-a-item-center rv-widget__filter-widget-new-checkout'] | justify-content>flex-end;padding>8px 0;margin>24px 0;border-top>1px solid #dadada;border-bottom>1px solid #dadada;align-items>center |
      | 1   | Rating          | //div[@class='app-mr8 app-flex']                                                                                                     | margin-right>8px;display>flex                                                                                                        |
      | 1   | Star            | //div[@class='app-mr8 app-flex']//div[@class='review-icon-symbols']                                                                  | width>12;height>12;margin-right>4                                                                                                    |
      | 1   | Content         | //div[@class='rv-widget__post rv-widget__post-arrow']                                                                                | position>relative;background-color>#AAA9A9;padding>24px;display>flex;flex-direction>column;flex>1;                                   |
      | 1   | Title           | //div[@class='rv-widget__post-heading']                                                                                              | margin-bottom>0;font-size>15px;font-weight>600;letter-spacing>0;line-height>normal;color>#303030;margin>0 0 8px                      |
      | 1   | Body            | //div[@class='rv-widget__post-content']                                                                                              | color>#606060;font-size>14px;font-weight>400;letter-spacing>0;line-height>20px;margin>0                                              |
      | 1   | Image           | //div[@class='rv-widget__post rv-widget__post-arrow']//img                                                                           | width>auto;height>auto;max-width>56px;max-height>56px;object-fit>contain;margin>0;padding>0                                          |
      | 1   | Author          | //div[@class='app-flex rv-widget__user']                                                                                             | margin-right>16;width>170                                                                                                            |
      | 1   | Avatar          | //div[@class='rv-widget__avatar']//img                                                                                               | width>48px;height>48px;min-width>48px;min-height>48px;border-radius>50%;object-fit>contain                                           |
      | 1   | Name            | //div[@class='rv-widget__author']                                                                                                    | color#606060;font-size14px;font-weight600;letter-spacing0;line-height20px;white-spacenowrap;text-overflowellipsis;overflow>hidden    |
      | 1   | Date            | //time[@class='rv-widget__date']                                                                                                     | font-size>12px;font-weight>500;letter-spacing>0;line-height>18px;color>#bcbcbc                                                       |

#  Scenario: Export review in one page
#    Given user login to shopbase dashboard by API
#    And user navigate to "Apps" screen
#    And select app "Product Reviews" on Apps list
#    And user navigate to "All reviews" screen
#    When "Export" review in current page
#    Then verify content of file downloaded

  Scenario: Import reviews from CSV file with CSV file invalid
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Imports" screen
    And import reviews from CSV file invalid is "ReviewFileInvalid.csv"

  Scenario: Import reviews from CSV file with review invalid
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    And user navigate to "Imports" screen
    And import reviews from a spreadsheet into Product Reviews with cvs file is "Review_invalid.csv"
    And verify review show on Dashboard
      | Review         | Is show |
      | Name null      | false   |
      | Email null     | false   |
      | Email invalid  | false   |
      | Rating null    | false   |
      | Rating invalid | false   |
      | Body null      | false   |
      | Date null      | true    |
      | Review valid   | true    |

  Scenario: Import reviews from AliExpress
    Given user login to shopbase dashboard by API
    And user navigate to "Apps" screen
    And select app "Product Reviews" on Apps list
    When user navigate to "Imports" screen
    When import AliExpress reviews into product reviews
      | Product name         | URLs AliExpress                                  | Message                                     |
      |                      |                                                  | Product is invalid;Link is required         |
      |                      | https://www.aliexpress.com                       | Product is invalid;Invalid Ali Express link |
      | Mini Pallet Coasters |                                                  | Invalid Ali Express link                    |
      | Mini Pallet Coasters | https://www.aliexpress.com                       | Invalid Ali Express link                    |
      | Mini Pallet Coasters | https://www.aliexpress.com/item/32717103110.html | Success                                     |
    And setup AliExpress filter
      | Import reviews from star | Maximum number of reviews per link | Country | Translation to English |
      | 4 stars and above        | 20                                 | All     | true                   |
    Then confirm get reviews
    When open shop on storefront
    And search and select the product "Mini Pallet Coasters"
    Then verify review AliExpress import on productpage
      | Number of review | Minimum of star |
      | 20               | 4               |

#  Scenario: Export all review more than 50 review
#    Given user login to shopbase dashboard by API
#    And user navigate to "Apps" screen
#    And select app "Product Reviews" on Apps list
#    And user navigate to "All reviews" screen
#    When "Export" all review
#    And open yopmail as "shopbase1@yopmail.com"
#    Then verify send mail to merchant
#      | Title                                            | Message                         | User name       | File detail    |
#      | Hooray, au-review-write Reviews export is ready! | Finish exporting a review list. | au-review-write | Export_Reviews |