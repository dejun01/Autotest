Feature: Create- Edit Blog - ShopBase
  #Add manage blog
  #env
  #staging_manage_blog
  #prod_manage_blog
  #prodtest_manage_blog


  Background:
    Given user login to shopbase dashboard by API
    When open page "/admin/blogs"

  Scenario: delete all blogs #SB_BL_2
    And delete all blog
    Then verify all blog deleted

  Scenario Outline: Verify create blog #SB_BL_DCB_1 #SB_BL_DCB_2 #SB_BL_3
    Then create new blog and edit blog after created "<KEY>"
      | KEY | Blog Title | Page title   | Meta description   | URL        | Comment                                               |
      | 1   |            | Page title 1 | Meta description 1 |            | Comments are disabled                                 |
      | 1   | Blog 1     |              |                    |            | Comments are disabled                                 |
      | 2   | Blog 2     | Page title 2 |                    |            | Comments are allowed, pending moderation              |
      | 3   | Blog 3     | Page title 3 | Meta description 3 | blog url 3 | Comments are allowed, and are automatically published |
    And verify blog created on dashboard "<KEY>"
      | KEY | Blog Title | Comment                                               |
      | 1   | Blog 1     | Comments are disabled                                 |
      | 2   | Blog 2     | Comments are allowed, pending moderation              |
      | 3   | Blog 3     | Comments are allowed, and are automatically published |
    And verify blog created on storefront "<KEY>"
      | KEY | Blog Title | URL        |
      | 1   | Blog 1     |            |
      | 2   | Blog 2     |            |
      | 3   | Blog 3     | blog url 3 |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Edit blog just created #SB_BL_1
    Then create new blog and edit blog after created "<KEY>"
      | KEY | Old Title | Blog Title | Page title   | Meta description   | URL        | Comment                                               |
      | 1   | Blog 1    | Blog 4     | Page title 4 | Meta description 4 | blog url 4 | Comments are allowed, pending moderation              |
      | 2   | Blog 2    | Blog 5     |              | Meta description 5 | blog url 5 | Comments are disabled                                 |
      | 3   | Blog 3    | Blog 6     |              |                    | blog url 6 | Comments are allowed, and are automatically published |
    And verify blog created on dashboard "<KEY>"
      | KEY | Blog Title | Comment                                               |
      | 1   | Blog 4     | Comments are allowed, pending moderation              |
      | 2   | Blog 5     | Comments are disabled                                 |
      | 3   | Blog 6     | Comments are allowed, and are automatically published |
    And verify blog created on storefront "<KEY>"
      | KEY | Blog Title | URL        |
      | 1   | Blog 4     | blog url 4 |
      | 2   | Blog 5     | blog url 5 |
      | 3   | Blog 6     | blog url 6 |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |





