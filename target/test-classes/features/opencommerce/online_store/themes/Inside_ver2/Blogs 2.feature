Feature: Homepage - Blogs

#  Theme inside v2
#  Env:
#  prod_inside_blog
# prodtest_inside_blog
# staging_inside_blog

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme

  Scenario Outline: Verify blogs #SB_BL_39 #SB_BL_29
    And open preview page "Blogs"
    When change blogs settings  "<KEY>"
      | KEY | Container type    | Show sidebar | Placement    | Layout | Posts to show | Posts per row |
      | 1   | Full width        | true         | Left column  | List   | 20%           | 50%           |
      | 2   | Use container box | false        | Right column | Grid   | 10%           | 0%            |
    And save change setting
    Then open page "/blogs/blog?sbase_debug=1"
    When verify blogs on storefront  "<KEY>"
      | KEY | Container type | Show sidebar | Placement | Layout | Posts to show | Posts per row |
      | 1   | true           | true         | true      | List   | 4             | 3             |
      | 2   | false          | false        | false     | Grid   | 3             | 6             |
    Examples:
      | KEY |
      | 1   |
      | 2   |

  Scenario Outline: Verify blog post #SB_BL_34 #SB_BL_32
    And open preview page "Blog post"
    When change blog post settings  "<KEY>"
      | KEY | Container type    | Show sidebar | Placement    | Design   | Show author | Show date | Show tags article content | Show social | Show related article | Show comments | Content alignment | Show tags related articles | Show published date | Show excerpt | Show read more link |
      | 1   | Full width        | true         | Left column  | Layout 1 | false       | true      | true                      | false       | true                 | false         | Left              | false                      | true                | false        | true                |
      | 2   | Use container box | false        | Right column | Layout 2 | true        | false     | false                     | true        | false                | true          | Center            | true                       | false               | true         | false               |
    And save change setting
    Then open page "/blogs/blog/in-defense-of-non-interactive-media?sbase_debug=1"
    When verify blogs post on storefront  "<KEY>"
      | KEY | Container type | Show sidebar | Placement | Design   | Show author | Show date | Show tags article content | Show social | Show related article | Show comments | Content alignment | Show tags related articles | Show published date | Show excerpt | Show read more link |
      | 1   | true           | true         | true      | Layout 1 | false       | true      | true                      | false       | true                 | false         | Left              | false                      | 2                   | false        | true                |
      | 2   | false          | false        | false     | Layout 2 | true        | false     | false                     | true        | false                | true          | Center            | true                       | 1                   | true         | false               |
    Examples:
      | KEY |
      | 1   |
      | 2   |



