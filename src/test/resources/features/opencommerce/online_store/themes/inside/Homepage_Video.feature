Feature: Homepage - Video

#  Env:
#  staging_sbase_inside_video
#  prod_sbase_inside_video

#  Data test:
#  theme inside


  Scenario Outline: Featured Video #SB_OLS_THE_INS_11
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    And change video settings as "<KEY>"
      | KEY | Autoplay video | Video link                                                                   | Image           | Alt text | Heading   | Subheading   | Display solid text background | Full width section | Text position | Text alignment |
      | 1   | true           |                                                                              |                 |          |           |              | false                         | false              | Left          | Left           |
      | 2   | false          | https://www.youtube.com/watch?v=2GwhdLY2f3E&list=RD2GwhdLY2f3E&start_radio=1 | front/logo2.jpg | Video 1  | HEADING 2 | SUBHEADING 2 | true                          | true               | Right         | Left           |
      | 3   | false          | https://www.youtube.com/watch?v=O71GdeeND68                                  | front/logo3.png | Video 2  | HEADING 3 | SUBHEADING 3 | true                          | false              | Center        | Center         |
      | 4   | false          | https://www.youtube.com/watch?v=O71GdeeND68                                  | front/logo4.png | Video 3  | HEADING 4 | SUBHEADING 4 | true                          | true               | Left          | Right          |
    Then open shop on storefront
    And verify video on storefront "<KEY>"
      | KEY | Autoplay video | Video link  | Image           | Alt text | Heading   | Subheading   | Display solid text background | Full width section | Text position | Text alignment |
      | 1   | true           |             |                 |          |           |              | false                         | false              | Left          | Left           |
      | 2   | false          | 2GwhdLY2f3E | front/logo2.jpg | Video 1  | HEADING 2 | SUBHEADING 2 | true                          | true               | Right         | Left           |
      | 3   | false          | O71GdeeND68 | front/logo3.png | Video 2  | HEADING 3 | SUBHEADING 3 | true                          | false              | Center        | Center         |
      | 4   | false          | O71GdeeND68 | front/logo4.png | Video 3  | HEADING 4 | SUBHEADING 4 | true                          | true               | Left          | Right          |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |