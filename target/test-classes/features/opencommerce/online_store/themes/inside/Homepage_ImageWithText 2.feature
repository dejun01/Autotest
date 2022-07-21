Feature: Homepage - Image with text

#  Theme inside
#  Env:
#  prod_inside_imagewithtext
#  staging_inside_imagewithtext
#  dev_inside_imagewithtext

#  Data test:
#  Collections: Best Selling, Sports
#  Products: Cairbull Cycling Helmet

  Scenario Outline: verify image with text theme inside #SB_OLS_THE_INS_7
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    Then change setting image with text "<KEY>"
      | KEY | Layout  | Full width section | Text alignment | Image           | Alt text | Image link                  | Headline   | Text                                 | Button label | Button link                 | Display as button |
      | 001 | Box     | true               | Center         | front/logo2.jpg | Alt 1    | Collections>All Collections | Headline 1 | Promotion description appears here 1 | View all     | Collections>All Collections | true              |
      | 001 |         |                    |                |                 | Alt 2    |                             | Headline 3 | Promotion description appears here 2 | View all     |                             | false             |
      | 001 |         |                    |                | front/logo3.png | Alt 3    |                             |            |                                      |              |                             |                   |
      | 002 | Mix     | false              | Left           | front/logo2.jpg |          | Collections>All Collections |            | Promotion description appears here 3 | Shop         | Products>All Products       | false             |
      | 003 | Default | true               | Left           | front/logo4.png | Alt 4    |                             | Headline 4 | Promotion description appears here 4 | Shop now     |                             |                   |
      | 004 | Grid    |                    | Center         | front/logo5.png | Alt 5    | Products>All Products       |            |                                      |              |                             |                   |
    Given open shop on storefront
    Then verify image with text on sf "<KEY>"
      | KEY | Layout  | Full width section | Text alignment | Image           | Alt text | Image link       | Headline   | Text                                 | Button label | Button link      | Display as button |
      | 001 | Box     | true               | Center         | front/logo2.jpg | Alt 1    | /collections     | Headline 1 | Promotion description appears here 1 | View all     | /collections     | true              |
      | 001 |         |                    |                |                 | Alt 2    |                  | Headline 3 | Promotion description appears here 2 | View all     |                  | false             |
      | 001 |         |                    |                | front/logo3.png | Alt 3    |                  |            |                                      |              |                  |                   |
      | 002 | Mix     | false              | Left           | front/logo2.jpg |          | /collections     |            | Promotion description appears here 3 | Shop         | /collections/all | false             |
      | 003 | Default | true               | Left           | front/logo4.png | Alt 4    |                  | Headline 4 | Promotion description appears here 4 | Shop now     |                  |                   |
      | 004 | Grid    |                    | Center         | front/logo5.png | Alt 5    | /collections/all |            |                                      |              |                  |                   |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |
      | 004 |