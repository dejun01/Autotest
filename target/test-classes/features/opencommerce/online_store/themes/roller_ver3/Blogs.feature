Feature: Blogs, Blog Post settings

#  Theme roller v3
#  Env:
# dev_sbase_roller_blog
# staging_sbase_roller_blog
# prodtest_sbase_roller_blog
# prod_sbase_roller_blog


  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen
    Then click Customize button on Current theme

  Scenario Outline: Verify blogs #SB_BL_23, #SB_BL_38
    And open preview page "Blogs"
    When change blogs settings roller  "<KEY>"
      | KEY | Layout  | Show feature image | Feature image height | Show date | Show author |
      | 1   | Grid    | true               | Small                | false     | false       |
      | 2   | Grid    | true               | Medium               | false     | true        |
      | 3   | Grid    | true               | Large                | true      | true        |
      | 4   | Collage | false              | Small                | true      | true        |

    And save change setting
    Then open page "/blogs/blog?sbase_debug=1"
    When verify blogs roller on storefront "<KEY>"
      | KEY | Layout   | Show feature image | Feature image height | Show date | Show author |
      | 1   | col-lg-3 | true               | Small                | 0         | false       |
      | 2   | col-lg-3 | true               | Medium               | 1         | true        |
      | 3   | col-lg-3 | true               | Large                | 3         | true        |
      | 4   | col-lg-6 | false              | Small                | 3         | true        |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |


  Scenario Outline: verify blog post #SB_BL_26, #SB_BL_28
    And open preview page "Blog post"
    When change blogs post settings roller "<KEY>"
      | KEY | Feature image height | Show date | Show author |
      | 1   | Adapt to image       | true      | false       |
      | 2   | Small                | false     | false       |
      | 3   | Medium               | false     | true        |
      | 4   | Large                | true      | true        |

    And save change setting
    Then open page "/blogs/blog/blog-test-1?sbase_debug=1"
    When verify blogs post roller on storefront "<KEY>"
      | KEY | Feature image height | Show date | Show author |
      | 1   | adapt                | On        | false       |
      | 2   | small                |           | false       |
      | 3   | medium               | By        | true        |
      | 4   | large                | on        | true        |

    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |