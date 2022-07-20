Feature: Remove section
#  theme Roller
#Env: prod_sbase_theme
# staging_sbase_theme
#  dev_sbase_theme_roller

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Homepage"

  Scenario: Remove section
    When verify remove sections
      | Section              | Heading |
      | Featured Collection  | true    |
      | Collection list      | true    |
      | Logo                 | true    |
      | Newsletter           | true    |
      | Banner               | true    |
      | Rich text            | true    |
      | Feature Product      | false   |
      | Image with text      | false   |
      | Customer testimonial | true    |

  Scenario Outline: Turn on section
    When turn on sections as "<KEY>"
      | KEY | Sections                                                                                                                   |
      | 1   | Featured Collection,Collection list,Logo,Newsletter,Banner,Rich text,Featured Product,Image with text,Customer testimonial |
    Examples:
      | KEY |
      | 1   |