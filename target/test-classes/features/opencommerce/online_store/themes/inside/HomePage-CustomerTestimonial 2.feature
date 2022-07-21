Feature: Homepage - customer testimonial

#  env: prod_inside_homepage2
#  staging_inside_homepage2

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"


  Scenario Outline: Verify content Customer Testimonial section #SB_OLS_THE_INS_9
    And change Customer Testimonial settings inside theme as "<KEY>"
      | KEY | Section Headline       | Show Next/ Back navigation | Show indicator | Text alignment | Background image | Alt text | Darken background image | Testimonial                 | Customer name               |
      | 1   | Customer Testimonial 1 | false                      | false          | Center         | front/slide2.png | shopbase | false                   | Test Customer Testimonial 1 | Test Customer Testimonial 1 |
      | 2   | Customer Testimonial 2 | false                      | true           | Right          | front/slide2.png |          | true                    | Test Customer Testimonial 2 | Test Customer Testimonial 2 |
    When open shop on storefront
    Then verify content Customer Testimonial section inside theme as "<KEY>"
      | KEY | Section Headline       | Show Next/ Back navigation | Show indicator | Text alignment | Background image | Alt text | Darken background image | Testimonial                 | Customer name               |
      | 1   | Customer Testimonial 1 | false                      | false          | Center         | front/slide2.png | shopbase | false                   | Test Customer Testimonial 1 | Test Customer Testimonial 1 |
      | 2   | Customer Testimonial 2 | false                      | true           | Right          | front/slide2.png |          | true                    | Test Customer Testimonial 2 | Test Customer Testimonial 2 |
    Examples:
      | KEY |
      | 1   |
      | 2   |