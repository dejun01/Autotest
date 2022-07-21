Feature: Homepage - slideshow, collection list, feature collection
  # sbase_inside_theme2
  #staging_sbase_inside_theme2
  #prod_sbase_inside_theme2
  #dev_sbase_inside_theme2

  Background:
    Given user login to shopbase dashboard by API
    And user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"

  Scenario Outline: verify slideshow #SB_OLS_THE_INS_2 #SB_OLS_THE_INS_3
    When change slideshow settings as "<KEY>"
      | KEY | Layout | Ratio           | Enable parallax scrolling | Full width | Slideshow index | Background image  | Background color | Alt text | Heading                    | Text                              | Text alignment | Primary button label | Secondary button label |
      | 1   | Single | Fit first slide | true                      | true       | 1               | front/slide2.png  |                  | Alt 1    |                            |                                   | Center         | Women                |                        |
      | 2   | Box    | 21:9            | false                     | false      | 1               | front/slide1.jpeg | #194d33          | alt 2    | 350,000 Happy Customers    | From all of us here at Gadget Jam | Right          |                      | Men                    |
      | 3   | Box    | 16:9            | true                      | true       | 1               | front/slide1.jpeg | #5B6DE0          | alt 3    | The Black Sale is back !!! | Up to 50% off site wide           | Left           | Shoes                | Clothings              |
      | 4   | Box    | 3:1             | false                     | false      | 1               | front/slide2.png  | #194d33          | alt 4    | The big sale 11-11         | Up to 11% off site                | Center         | Women                | Men                    |
      | 5   | Box    | 4:1             | true                      | true       | 1               | front/slide1.jpeg | #5B6DE0          | alt 5    | The big sale 12-12         | All 12% off site                  | Right          | Kids                 | Unisex                 |
    And select section "Collection list"
    When change collection list setting as "<KEY>"
      | KEY | Layout  | Image styles | Heading         | Collection | Title position | Title alignment | Title background | Button label | Display as text link | Image            | Alt text    |
      | 1   | Default | Square       | All Collections | Fashion    | Inside images  | Left            | true             | View more    | true                 | /front/logo2.jpg | collection1 |
      | 1   |         |              |                 | Winter     |                |                 |                  |              |                      |                  |             |
      | 1   |         |              |                 | Spring     |                |                 |                  |              |                      |                  |             |
      | 1   |         |              |                 | Summer     |                |                 |                  |              |                      |                  |             |
      | 2   | Default | Landscape    |                 | Fashion    | Outside images | Center          | false            | View more    | false                |                  |             |
      | 3   | Default | Portraits    |                 | Men        | Outside images | Left            | true             |              | true                 |                  |             |
      | 4   | Mix     |              | All Collections | Fashion    | Outside images | Center          | true             | View all     | true                 |                  |             |
      | 4   |         |              |                 | Winter     |                |                 |                  |              | true                 |                  |             |
      | 4   |         |              |                 | Spring     |                |                 |                  |              | true                 |                  |             |
      | 4   |         |              |                 | Summer     |                |                 |                  |              | true                 |                  |             |
      | 5   | Mix     |              |                 | Summer     | Inside images  | Left            | false            | View all     | false                |                  |             |
      | 5   |         |              |                 | Fashion    |                |                 |                  |              |                      |                  |             |
      | 5   |         |              |                 | Winter     |                |                 |                  |              |                      |                  |             |
      | 5   |         |              |                 | Spring     |                |                 |                  |              |                      |                  |             |
    Then open shop on storefront
    And verify slideshow settings as "<KEY>"
      | KEY | Layout | Ratio           | Enable parallax scrolling | Full width | Slideshow index | Background image  | Background color | Alt text | Heading                    | Text                              | Text alignment | Primary button label | Secondary button label |
      | 1   | Single | Fit first slide | true                      | true       | 1               | front/slide2.png  |                  | Alt 1    |                            |                                   | Center         | Women                |                        |
      | 2   | Box    | 21:9            | false                     | false      | 1               | front/slide1.jpeg | #194d33          | alt 2    | 350,000 Happy Customers    | From all of us here at Gadget Jam | Right          |                      | Men                    |
      | 3   | Box    | 16:9            | true                      | true       | 1               | front/slide1.jpeg | #5B6DE0          | alt 3    | The Black Sale is back !!! | Up to 50% off site wide           | Left           | Shoes                | Clothings              |
      | 4   | Box    | 3:1             | false                     | false      | 1               | front/slide2.png  | #194d33          | alt 4    | The big sale 11-11         | Up to 11% off site                | Center         | Women                | Men                    |
      | 5   | Box    | 4:1             | true                      | true       | 1               | front/slide1.jpeg | #5B6DE0          | alt 5    | The big sale 12-12         | All 12% off site                  | Right          | Kids                 | Unisex                 |
    And verify collection list on store front "<KEY>"
      | KEY | Layout  | Image styles | Heading         | Collection                   | Title position | Title alignment | Title background | Button label | Display as text link | Image            | Alt text    |
      | 1   | Default | Square       | All Collections | Fashion,Winter,Spring,Summer | Inside images  | Left            | true             | View more    | true                 | /front/logo2.jpg | collection1 |
      | 2   | Default | Landscape    |                 | Fashion                      | Outside images | Center          | false            | View more    | false                |                  |             |
      | 3   | Default | Portraits    |                 | Men                          | Outside images | Left            | true             |              | true                 |                  |             |
      | 4   | Mix     |              | All Collections | Fashion,Winter,Spring,Summer | Outside images | Center          | true             | View all     | true                 |                  |             |
      | 5   | Mix     |              |                 | Summer,Fashion,Winter,Spring | Inside images  | Left            | false            | View all     | false                |                  |             |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |

  Scenario Outline: verify feature collection #SB_OLS_THE_INS_4
    And select section "Featured Collection"
    Then change featured collection setting as "<KEY>"
      | KEY | Collection layout | Heading             | Collection | Alignment | Button label | Display as text link |
      | 1   | Grid              | Featured Collection | Fashion    | Left      | Show more    | true                 |
      | 2   | Slider            | Best seller         | Men        | Center    | View all     | false                |
      | 3   | Mix               | Featured Collection | Fashion    | Left      | View more    | true                 |
    Then open shop on storefront
    And verify featured collection on store front "<KEY>"
      | KEY | Collection layout | Heading             | Collection | Alignment | View more label | Show View more button |
      | 1   | Grid              | Featured Collection | Fashion    | Left      | Show more       | true                  |
      | 2   | Slider            | Best seller         | Men        | Center    | View all        | false                 |
      | 3   | Mix               | Featured Collection | Fashion    | Left      | View more       | true                  |
    And verify add to cart product as "<KEY>"
      | KEY | Collection | Product name               |
      | 1   | Fashion    | Cow Leather Climbing Shoes |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |