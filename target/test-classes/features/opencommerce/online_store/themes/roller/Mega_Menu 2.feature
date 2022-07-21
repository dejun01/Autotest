Feature: Homepage - Mega Menu

#  Theme inside

#  Env
#  prod_roller_mega_menu
#  staging_roller_mega_menu
#  dev_roller_mega_menu

#  Data test
#  Collections: Best Selling
#  Products: Lancet Pen Lancing Device
#  Menu list: Main menu, Support


  Scenario Outline: mega menu #SB_OLS_THE_ROL_90
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"
    And select section "Mega Menu 1"
    Then change mega menu Roller "<KEY>"
      | KEY | Link title | Text area Top content   | Image Top content | Image caption Top content   | Image link Top content      |  Image Bottom content | Image caption Bottom content   | Image link Bottom content   | Text area Bottom content   |
      | 001 | Shop       | Text area Top content 1 | front/logo2.jpg   | Image caption Top content 1 | Collections>All Collections |                       | Image caption Bottom content 1 | Collections>All Collections | Text area Bottom content 1 |
      | 002 | Shop       |                         |                   |                             |                             |  front/logo2.jpg      | Image caption Bottom content 2 | Collections>All Collections |                            |
    Given open shop on storefront
    Then verify mega menu on sf "<KEY>"
      | KEY | Link title | Text area Top content   | Image Top content | Image caption Top content   | Image link Top content      |  Image Bottom content | Image caption Bottom content   | Image link Bottom content   | Text area Bottom content   |
      | 001 | shop       | Text area Top content 1 | front/logo2.jpg   | Image caption Top content 1 | Collections>All Collections |                       | Image caption Bottom content 1 | Collections>All Collections | Text area Bottom content 1 |
      | 002 | shop       |                         |                   |                             |                             |  front/logo2.jpg      | Image caption Bottom content 2 | Collections>All Collections |                            |
   Examples:
      | KEY |
      | 001 |
      | 002 |
