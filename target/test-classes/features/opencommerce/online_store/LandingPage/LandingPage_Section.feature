Feature: Landing page - Section Header,Footer,Hero, Rich text, FAQS, Custom HTML

  #environment=dev_landingpage_section
  #staging_landingpage_section
  #prod_landingpage_section
  #prodtest_landingpage_section
  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store>Landing Pages" screen
    And open landing editor "Landing Page Section"


  Scenario Outline: Verify Header Section
    And change Header section  "<KEY>"
      | KEY | Show logo | Desktop logo    | Mobile logo     | Button label | Button link                | Menu title   | Link                       | Type     | Custom color | Start color | End color | Direction  | Desktop logo size | Mobile logo size | Menu item color | Menu item font | Menu item size | Button color | Button label color | Button label font | Button label size | Submenu background color | Submenu item color | Border top color | Border top size | Border bottom color | Border bottom size | Desktop layout | Alignment | Max width | Custom width |
      | 1   | true      | front/logo2.jpg | front/logo3.png | Button 1     | https://shopbase.com/      | Homepage     | https://www.printbase.com/ | Custom   | #83EC65      |             |           | Vertical   | Small             | Small            | #666666         | Montserrat     | 32             | #44E46C      | #5A0B0B            | Arial             | 14                | #96A1B9                  | #ffebeb            | #ffebeb          | 14              | #ffebeb             | 10                 | Inline         | Left      | Default   |              |
      | 2   | false     | front/logo3.png | front/logo2.jpg | Button 2     | https://www.printbase.com/ |              | https://shopbase.com/      | Gradient |              | #44E46C     | #666666   | Horizontal | Medium            | Medium           | #44E46C         | Arial          | 35             | #666666      | #44E46C            | Baskerville       | 15                | #5A0B0B                  | #5A0B0B            | #44E46C          | 15              | #5A0B0B             | 20                 | Minimal        | Right     | Custom    | 100          |
      | 3   | true      | front/logo2.jpg | front/logo3.png | Button 3     |                            | Product page |                            | Custom   | #44E46C      |             |           | Diagonal   | Large             | Small            | #83EC65         | Baskerville    | 32             | #44E46C      | #96A1B9            | Montserrat        | 16                | #44E46C                  | #44E46C            | #9D9FB4          | 16              | #9D9FB4             | 30                 | Inline         | Center    | Default   |              |
    And open "Landing Page Section" on Storefront
    And verify Header section on SF "<KEY>"
      | KEY | Show logo | Desktop logo    | Mobile logo     | Button label | Button link                | Menu title   | Link                       | Type     | Custom color | Start color | End color | Direction  | Desktop logo size | Mobile logo size | Menu item color | Menu item font | Menu item size | Button color | Button label color | Button label font | Button label size | Submenu background color | Submenu item color | Border top color | Border top size | Border bottom color | Border bottom size | Desktop layout | Alignment | Max width | Custom width |
      | 1   | true      | front/logo2.jpg | front/logo3.png | Button 1     | https://shopbase.com/      | Homepage     | https://www.printbase.com/ | Custom   | #83EC65      |             |           | Vertical   | is-small          | Small            | #666666         | Montserrat     | 32             | #44E46C      | #5A0B0B            | Arial             | 14                | #96A1B9                  | #ffebeb            | #ffebeb          | 14              | #ffebeb             | 10                 | Inline         | start     | Default   |              |
      | 2   | false     | front/logo3.png | front/logo2.jpg | Button 2     | https://www.printbase.com/ |              | https://shopbase.com/      | Gradient |              | #44E46C     | #666666   | Horizontal | is-medium         | Medium           | #44E46C         | Arial          | 35             | #666666      | #44E46C            | Baskerville       | 15                | #5A0B0B                  | #5A0B0B            | #44E46C          | 15              | #5A0B0B             | 20                 | Minimal        | end       | Custom    | 100          |
      | 3   | true      | front/logo2.jpg | front/logo3.png | Button 3     |                            | Product page |                            | Custom   | #44E46C      |             |           | Diagonal   | is-large          | Small            | #83EC65         | Baskerville    | 32             | #44E46C      | #96A1B9            | Montserrat        | 16                | #44E46C                  | #44E46C            | #9D9FB4          | 16              | #9D9FB4             | 30                 | Inline         | center    | Default   |              |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify Hero section
    And change hero section  "<KEY>"
      | KEY | Title        | Body                                    | Button label | Button link            | Type Background | Custom color Background | Start color Background | End color Background | Direction Background | Image Background | Color overlay Background | Title color Typography | Title font Typography | Title size Typography | Body color Typography | Body font Typography | Body size Typography | Button color Typography | Button label color Typography | Button label font Typography | Button label size Typography | Border top color Separator | Border bottom color Separator | Position Layout | Alignment Layout | Min height Layout | Max width Layout |
      | 1   | HERO TITLE 1 | Enter description for your Hero here. 1 | Button 1     | https://shopbase.com/  | Custom          | #83EC65                 |                        |                      | Vertical             |                  |                          | #666666                | Montserrat            | 32                    | #44E46C               | Arial                | 18                   | #5A0B0B                 | #96A1B9                       | Baskerville                  | 24                           | #ffebeb                    | #9D9FB4                       | Left            | Left             | Default           | Default          |
      | 2   | HERO TITLE 2 | Enter description for your Hero here. 2 | Button 2     | https://printbase.com/ | Gradient        | #666666                 | #44E46C                | #666666              | Horizontal           |                  |                          | #44E46C                | Arial                 | 35                    | #666666               | Baskerville          | 19                   | #44E46C                 | #5A0B0B                       | Montserrat                   | 23                           | #5A0B0B                    | #ffebeb                       | Center          | Right            | Screen            | None             |
      | 3   | HERO TITLE 3 | Enter description for your Hero here. 3 | Button 3     |                        | Image           | #44E46C                 |                        |                      | Diagonal             | /front/logo2.jpg | #44E46C                  | #83EC65                | Baskerville           | 32                    | #44E46C               | Montserrat           | 20                   | #96A1B9                 | #44E46C                       | Arial                        | 24                           | #9D9FB4                    | #5A0B0B                       | Right           | Center           | Default           | Custom           |
    And open "Landing Page Section" on Storefront
    And verify show Hero on SF "<KEY>"
      | KEY | Title        | Body                                    | Button label | Button link           | Type Background | Custom color Background | Start color Background | End color Background | Direction Background | Image Background | Color overlay Background | Title color Typography | Title font Typography | Title size Typography | Body color Typography | Body font Typography | Body size Typography | Button color Typography | Button label color Typography | Button label font Typography | Button label size Typography | Border top color Separator | Border bottom color Separator | Position Layout | Alignment Layout | Min height Layout | Max width Layout |
      | 1   | HERO TITLE 1 | Enter description for your Hero here. 1 | Button 1     | https://shopbase.com/ | Custom          | #83EC65                 |                        |                      | Vertical             |                  |                          | #666666                | Montserrat            | 32                    | #44E46C               | Arial                | 18                   | #5A0B0B                 | #96A1B9                       | Baskerville                  | 24                           | #ffebeb                    | #9D9FB4                       | start           | left             | auto              | Default          |
      | 2   | HERO TITLE 2 | Enter description for your Hero here. 2 | Button 2     | https://printbase.com | Gradient        | #666666                 | #44E46C                | #666666              | Horizontal           |                  |                          | #44E46C                | Arial                 | 35                    | #666666               | Baskerville          | 19                   | #44E46C                 | #5A0B0B                       | Montserrat                   | 23                           | #5A0B0B                    | #ffebeb                       | center          | right            | 100vh             | None             |
      | 3   | HERO TITLE 3 | Enter description for your Hero here. 3 | Button 3     |                       | Image           | #44E46C                 |                        |                      | Diagonal             | /front/logo2.jpg | #44E46C                  | #83EC65                | Baskerville           | 32                    | #44E46C               | Montserrat           | 20                   | #96A1B9                 | #44E46C                       | Arial                        | 24                           | #9D9FB4                    | #5A0B0B                       | end             | center           | auto              | Custom           |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify Custom HTML
    And change Custom HTML section  "<KEY>"
      | KEY | HTML                        | Full width section |
      | 1   |                             | false              |
      | 2   | <p>This is a Heading</p>    | true               |
      | 3   | <p>This is a paragraph.</p> | false              |
    And open "Landing Page Section" on Storefront
    And verify show Custom HTML on SF "<KEY>"
      | KEY | HTML                | Full width section |
      | 1   |                     | false              |
      | 2   | This is a Heading   | true               |
      | 3   | This is a paragraph | false              |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify Rich Text section
    And change Rich Text section  "<KEY>"
      | KEY | Title             | Body                                         | Button label | Button link            | Type Background | Custom color Background | Start color Background | End color Background | Direction Background | Image Background | Color overlay Background | Title color Typography | Title font Typography | Title size Typography | Body color Typography | Body font Typography | Body size Typography | Type Button | Button color | Button label color | Button label font | Button label size | Link color | Link font   | Link size | Border top color Separator | Border top size | Border bottom color Separator | Border bottom size | Position Layout | Alignment Layout | Min height Layout | Max width Layout | Custom width |
      | 1   | RICH TEXT TITLE 1 | Enter description for your Rich text here. 1 | Button 1     | https://shopbase.com/  | Custom          | #83EC65                 |                        |                      | Vertical             |                  |                          | #666666                | Montserrat            | 32                    | #44E46C               | Arial                | 18                   | Button      | #5A0B0B      | #96A1B9            | Baskerville       | 24                | #96A1B9    | Montserrat  | 10        | #ffebeb                    | 5               | #9D9FB4                       | 10                 | Inline          | Left             | Default           | Default          |              |
      | 2   | RICH TEXT TITLE 2 |                                              | Button 2     | https://printbase.com/ | Gradient        | #666666                 | #44E46C                | #666666              | Horizontal           |                  |                          | #44E46C                | Arial                 | 35                    | #666666               | Baskerville          | 19                   | Link        | #44E46C      | #5A0B0B            | Montserrat        | 23                | #44E46C    | Arial       | 20        | #5A0B0B                    | 10              | #ffebeb                       | 15                 | Above           | Right            | Screen            | None             |              |
      | 3   |                   | Enter description for your Rich text here. 3 | Button 3     |                        | Image           | #44E46C                 |                        |                      | Diagonal             | /front/logo2.jpg | #44E46C                  | #83EC65                | Baskerville           | 32                    | #44E46C               | Montserrat           | 20                   | Button      | #96A1B9      | #44E46C            | Arial             | 24                | #5A0B0B    | Baskerville | 30        | #9D9FB4                    | 20              | #5A0B0B                       | 25                 | Inline          | Center           | Default           | Custom           | 100          |
    And open "Landing Page Section" on Storefront
    And verify Rich Text on SF "<KEY>"
      | KEY | Title             | Body                                         | Button label | Button link            | Type Background | Custom color Background | Start color Background | End color Background | Direction Background | Image Background | Color overlay Background | Title color Typography | Title font Typography | Title size Typography | Body color Typography | Body font Typography | Body size Typography | Type Button | Button color       | Button label color | Button label font | Button label size | Link color         | Link font   | Link size | Border top color Separator | Border top size Separator | Border bottom color Separator | Border bottom size Separator | Position Layout | Alignment Layout | Min height Layout | Max width Layout | Custom width |
      | 1   | RICH TEXT TITLE 1 | Enter description for your Rich text here. 1 | Button 1     | https://shopbase.com/  | Custom          | rgb(131, 236, 101)      |                        |                      | Vertical             |                  |                          | rgb(102, 102, 102)     | Montserrat            | 32                    | rgb(68, 228, 108)     | Arial                | 18                   | lp-btn      | rgb(90, 11, 11)    | rgb(150, 161, 185) | Baskerville       | 24                | rgb(150, 161, 185) | Montserrat  | 10        | rgb(255, 235, 235)         | 5                         | rgb(157, 159, 180)            | 10                           | Inline          | start            | auto              | Default          |              |
      | 2   | RICH TEXT TITLE 2 |                                              | Button 2     | https://printbase.com/ | Gradient        | rgb(102, 102, 102)      | rgb(68, 228, 108)      | rgb(102, 102, 102)   | Horizontal           |                  |                          | rgb(68, 228, 108)      | Arial                 | 35                    | rgb(102, 102, 102)    | Baskerville          | 19                   | lp-link     | rgb(68, 228, 108)  | rgb(90, 11, 11)    | Montserrat        | 23                | rgb(68, 228, 108)  | Arial       | 20        | rgb(90, 11, 11)            | 10                        | rgb(255, 235, 235)            | 15                           | Above           | end              | 100vh             | None             |              |
      | 3   |                   | Enter description for your Rich text here. 3 | Button 3     |                        | Image           | rgb(68, 228, 108)       |                        |                      | Diagonal             | /front/logo2.jpg | rgb(68, 228, 108)        | rgb(131, 236, 101)     | Baskerville           | 32                    | rgb(68, 228, 108)     | Montserrat           | 20                   | lp-btn      | rgb(150, 161, 185) | rgb(68, 228, 108)  | Arial             | 24                | rgb(90, 11, 11)    | Baskerville | 30        | rgb(157, 159, 180)         | 20                        | rgb(90, 11, 11)               | 20                           | Inline          | center           | auto              | Custom           | 100          |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify FAQs section
    And change FAQs section  "<KEY>"
      | KEY | Heading                      | Title                            | Body                                        | Open the first content by default | Type     | Type Background | Custom color Background | Start color Background | End color Background | Direction Background | Image Background | Color overlay Background | Heading color | Heading font | Heading size | Title color | Title font  | Title size | Body color | Body font   | Body size | Border top color | Border top size | Border top color | Border top size | Heading alignment | Min height | Max width | Custom width |
      | 1   | Frequently Asked Questions 1 | When will my product be shipped? | Your order will be delivered within 7 days. | true                              | Single   | Custom          | #83EC65                 |                        |                      | Vertical             |                  |                          | #666666       | Montserrat   | 32           | #44E46C     | Arial       | 18         | #5A0B0B    | Baskerville | 24        | #ffebeb          | 5               | #ffebeb          | 5               | Left              | Default    | Default   |              |
      | 2   | Frequently Asked Questions 2 |                                  | Your order will be delivered within 7 days. | false                             | Multiple | Gradient        | #666666                 | #44E46C                | #666666              | Horizontal           |                  |                          | #44E46C       | Arial        | 35           | #666666     | Baskerville | 19         | #44E46C    | Montserrat  | 23        | #5A0B0B          | 10              | #5A0B0B          | 10              | Center            | Screen     | None      |              |
      | 3   |                              | When will my product be shipped? |                                             | true                              | Single   | Image           | #44E46C                 |                        |                      | Diagonal             | /front/logo2.jpg | #44E46C                  | #83EC65       | Baskerville  | 32           | #44E46C     | Montserrat  | 20         | #96A1B9    | Arial       | 24        | #9D9FB4          | 15              | #9D9FB4          | 15              | Right             | Default    | Custom    | 15           |
    And open "Landing Page Section" on Storefront
    And verify FAQs on SF "<KEY>"
      | KEY | Heading                      | Title                            | Body                                        | Open the first content by default | Type     | Type Background | Custom color Background | Start color Background | End color Background | Direction Background | Image Background | Color overlay Background | Heading color | Heading font | Heading size | Title color | Title font  | Title size | Body color | Body font   | Body size | Border top color | Border top size | Heading alignment | Min height | Max width | Custom width |
      | 1   | Frequently Asked Questions 1 | When will my product be shipped? | Your order will be delivered within 7 days. | true                              | Single   | Custom          | #83EC65                 |                        |                      | Vertical             |                  |                          | #666666       | Montserrat   | 32           | #44E46C     | Arial       | 18         | #5A0B0B    | Baskerville | 24        | #ffebeb          | 5               | left              | auto       | Default   |              |
      | 2   | Frequently Asked Questions 2 |                                  | Your order will be delivered within 7 days. | false                             | Multiple | Gradient        | #666666                 | #44E46C                | #666666              | Horizontal           |                  |                          | #44E46C       | Arial        | 35           | #666666     | Baskerville | 19         | #44E46C    | Montserrat  | 23        | #5A0B0B          | 10              | center            | 100vh      | None      |              |
      | 3   |                              | When will my product be shipped? |                                             | true                              | Single   | Image           | #44E46C                 |                        |                      | Diagonal             | /front/logo2.jpg | #44E46C                  | #83EC65       | Baskerville  | 32           | #44E46C     | Montserrat  | 20         | #96A1B9    | Arial       | 24        | #9D9FB4          | 15              | right             | auto       | Custom    | 15           |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify Footer section
    And change Footer section  "<KEY>"
      | KEY | Show logo | Desktop logo    | Mobile logo     | Tagline        | Phone          | Email               | Facebook                          | Twitter                      | Youtube                      | Pinterest                          | Instagram                      | Blog                      | Type     | Custom color | Start color | End color | Direction  | Image Background | Color overlay Background | Desktop logo size | Mobile logo size | Title color | Title font  | Title size | Body color | Body font   | Body size | Border top color | Border top size | Border bottom color | Border bottom size | Min height | Max width | Custom width |
      | 1   | true      | front/logo2.jpg | front/logo3.png | www.domain.com | (+1) 1111 1111 | youremail@email.com | https://www.facebook.com/Shopbase | https://twitter.com/Shopbase |                              | https://www.pinterest.com/Shopbase |                                | https://blog.com/Shopbase | Custom   | #83EC65      |             |           | Vertical   |                  |                          | Small             | Small            | #666666     | Montserrat  | 32         | #44E46C    | Arial       | 14        | #96A1B9          | 14              | #ffebeb             | 10                 | Default    | Default   |              |
      | 2   | false     | front/logo3.png | front/logo2.jpg | www.domain.com | (+1) 1111 1111 |                     | https://www.facebook.com/Shopbase |                              | https://youtube.com/Shopbase |                                    | https://instagram.com/Shopbase |                           | Gradient |              | #44E46C     | #666666   | Horizontal |                  |                          | Medium            | Medium           | #44E46C     | Arial       | 35         | #666666    | Montserrat  | 15        | #5A0B0B          | 15              | #5A0B0B             | 20                 | Screen     | Custom    | 100          |
      | 3   | true      | front/logo2.jpg | front/logo3.png | www.domain.com |                | youremail@email.com | https://www.facebook.com/Shopbase | https://twitter.com/Shopbase |                              |                                    |                                |                           | Image    | #44E46C      |             |           | Diagonal   | /front/logo2.jpg | #44E46C                  | Large             | Small            | #83EC65     | Baskerville | 32         | #44E46C    | Baskerville | 16        | #44E46C          | 16              | #9D9FB4             | 30                 | Default    | Default   |              |
    And open "Landing Page Section" on Storefront
    And verify Footer on SF "<KEY>"
      | KEY | Show logo | Desktop logo    | Mobile logo     | Tagline        | Phone          | Email               | Facebook                          | Twitter                      | Youtube                      | Pinterest                          | Instagram                      | Blog                      | Type     | Custom color | Start color | End color | Direction  | Image Background | Color overlay Background | Desktop logo size | Mobile logo size | Title color | Title font  | Title size | Body color | Body font   | Body size | Border top color | Border top size | Border bottom color | Border bottom size | Min height | Max width | Custom width |
      | 1   | true      | front/logo2.jpg | front/logo3.png | www.domain.com | (+1) 1111 1111 | youremail@email.com | https://www.facebook.com/Shopbase | https://twitter.com/Shopbase |                              | https://www.pinterest.com/Shopbase |                                | https://blog.com/Shopbase | Custom   | #83EC65      |             |           | Vertical   |                  |                          | is-small          | is-small         | #666666     | Montserrat  | 32         | #44E46C    | Arial       | 14        | #96A1B9          | 14              | #ffebeb             | 10                 |            | Default   |              |
      | 2   | false     | front/logo3.png | front/logo2.jpg | www.domain.com | (+1) 1111 1111 |                     | https://www.facebook.com/Shopbase |                              | https://youtube.com/Shopbase |                                    | https://instagram.com/Shopbase |                           | Gradient |              | #44E46C     | #666666   | Horizontal |                  |                          | is-medium         | is-medium        | #44E46C     | Arial       | 35         | #666666    | Montserrat  | 15        | #5A0B0B          | 15              | #5A0B0B             | 20                 | 100vh      | Custom    | 100          |
      | 3   | true      | front/logo2.jpg | front/logo3.png | www.domain.com |                | youremail@email.com | https://www.facebook.com/Shopbase | https://twitter.com/Shopbase |                              |                                    |                                |                           | Image    | #44E46C      |             |           | Diagonal   | /front/logo2.jpg | #44E46C                  | is-large          |                  | #83EC65     | Baskerville | 32         | #44E46C    | Baskerville | 16        | #44E46C          | 16              | #9D9FB4             | 30                 |            | Default   |              |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |




