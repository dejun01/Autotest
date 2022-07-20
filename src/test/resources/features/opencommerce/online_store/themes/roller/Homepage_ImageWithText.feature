Feature: Homepage - Image with text - Theme Roller

#  Theme roller
#  Env:
#  prod_roller_imagewithtext
#  staging_roller_imagewithtext
#  dev_roller_imagewithtext

#  Data test:
#  Collections: Best Selling, Sports
#  Products: Cairbull Cycling Helmet

  Scenario Outline: verify image with text theme roller #SB_OLS_THE_ROL_32
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    Then change setting image with text Roller "<KEY>"
      | KEY | Layout                       | Images per row | Promotions animation | Text alignment | Image           | Alt text | Headline   | Text                                 | Button label | Button link                 | Display as button |
      | 001 | Image with text above        | 3              | Fade in              |                | front/logo2.jpg | Alt 1    | Headline 1 | Promotion description appears here 1 | View all     | Products>All Products       | true              |
      | 001 |                              |                |                      |                |                 | Alt 2    | Headline 3 | Promotion description appears here 2 | View all     |                             | false             |
      | 001 |                              |                |                      |                | front/logo3.png | Alt 3    |            |                                      |              |                             |                   |
      | 002 | Image with text above        | 2              | Fade down            |                | front/logo2.jpg |          |            | Promotion description appears here 3 | Shop         | Collections>All Collections | false             |
      | 003 | Image with text above        | 4              | Fade up              |                | front/logo4.png | Alt 4    | Headline 4 | Promotion description appears here 4 | Shop now     |                             |                   |
      | 004 | Image with text on the right |                |                      | Left           | front/logo5.png | Alt 5    | Headline 5 | Promotion description appears here 5 | View all     | Products>All Products       | true              |
      | 005 | Image with text on the right |                |                      | Centre         | front/logo2.jpg | Alt 5    | Headline 6 | Promotion description appears here 6 | Shop now     | Collections>Best Selling    | false             |
      | 006 | Image with text on the right |                |                      | Right          | front/logo5.png | Alt 5    | Headline 7 | Promotion description appears here 7 | View all     |                             |                   |
    Given open shop on storefront
    Then verify image with text on sf "<KEY>"
      | KEY | Layout                       | Images per row | Promotions animation | Text alignment | Image           | Alt text | Headline   | Text                                 | Button label | Button link               | Display as button |
      | 001 | Image with text above        | 3              | fade-in              |                | front/logo2.jpg | Alt 1    | Headline 1 | Promotion description appears here 1 | View all     | /collections/all          | true              |
      | 002 | Image with text above        | 2              | fade-down            |                | front/logo2.jpg |          |            | Promotion description appears here 3 | Shop         | /collections              | false             |
      | 003 | Image with text above        | 4              | fade-up              |                | front/logo4.png | Alt 4    | Headline 4 | Promotion description appears here 4 | Shop now     |                           |                   |
      | 004 | Image with text on the right |                |                      | Left           | front/logo5.png | Alt 5    | Headline 5 | Promotion description appears here 5 | View all     | /collections/all          | true              |
      | 005 | Image with text on the right |                |                      | Center         | front/logo2.jpg | Alt 5    | Headline 6 | Promotion description appears here 6 | Shop now     | /collections/best-selling | false             |
      | 006 | Image with text on the right |                |                      | Right          | front/logo5.png | Alt 5    | Headline 7 | Promotion description appears here 7 | View all     |                           |                   |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |
      | 004 |
      | 005 |
      | 006 |