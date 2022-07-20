Feature: Homepage - Manage Blog Posts

#  Theme inside v2
#  Env:
#  prod_manage_blog_post
# prodtest_manage_blog_post
# staging_manage_blog_post


  Scenario: Delete blog post #SB_BL_8
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Blog posts" screen
    And remove all blog post

  Scenario Outline: Verify create blog post #SB_BL_4 #SB_BL_5 #SB_BL_6
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Blog posts" screen
    When create blog posts "<KEY>"
      | KEY | Title                  | Content                   | Visibility       | Featured image          | Author | Blog        | Tags    | Message                    |
      | 1   |                        | This content blogs post   | Visible (as of ) |                         | QA OCG | Blog        |    |                            |
      | 1   | This title blog post   | This content blogs post   | Visible (as of ) |                         | QA OCG | Select blog |         |                            |
      | 1   | This title blog post 1 | This content blogs post 1 | Visible (as of ) | front/Galleryimage.jpeg | QA OCG | Blog        | blogs | Your blog post was updated |
      | 2   | This title blog post 2 | This content blogs post 2 | Hidden           | front/Galleryimage.jpeg | QA OCG | Blog        |         | Your blog post was updated |
    And back to blog posts page
    Then verify blogs post on SF "<KEY>"
      | KEY | URL                                              | Title                  | Content                   | Visibility       | Featured image          | Author | Blog | Tags    |
      | 1   | /blogs/blog/this-title-blog-post-1?sbase_debug=1 | This title blog post 1 | This content blogs post 1 | Visible (as of ) | front/Galleryimage.jpeg | QA OCG | Blog | blogs |
      | 2   | /blogs/blog/this-title-blog-post-2?sbase_debug=1 | This title blog post 2 | This content blogs post 2 | Hidden           | front/Galleryimage.jpeg | QA OCG | Blog |         |
    Examples:
      | KEY |
      | 1   |
      | 2   |

  Scenario: Update blog post #SB_BL_7
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Blog posts" screen
    And open blog post "This title blog post 2"
    And edit blog post
      | Title                         | Content                          | Visibility     | Featured image          | Author | Blog | Tags        |
      | This title blog post 2 edited | This content blogs post 2 edited | Visible (as of | front/Galleryimage.jpeg | QA OCG | Blog | tags |
    And back to blog posts page
    Then verify blog post edited on SF
      | URL                                              | Title                         | Content                          | Visibility | Featured image          | Author | Blog | Tags        |
      | /blogs/blog/this-title-blog-post-2?sbase_debug=1 | This title blog post 2 edited | This content blogs post 2 edited | Visible    | front/Galleryimage.jpeg | QA OCG | Blog | tags |


  Scenario Outline: Verify filter blog post on dashboard #SB_BL_10
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Blog posts" screen
    And choose filter blog posts in dashboard as "<KEY>"
      | KEY | Option      | value     |
      | 1   | Tagged with | blogs 1   |
      | 2   | Visibility  | Visible   |
      | 2   | Blog        | Blog      |
      | 3   | Visibility  | Hidden    |
      | 3   | Image       | Has image |
      | 3   | Author      | QA OCG    |
    And click done button
    Then verify list blog post after filter as "<KEY>"
      | KEY | Blog post show                                       | Blog post don't show                                 |
      | 1   | This title blog post 1                               | This title blog post 2 edited                        |
      | 2   | This title blog post 1,This title blog post 2 edited |                                                      |
      | 3   |                                                      | This title blog post 1,This title blog post 2 edited |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |

  Scenario Outline: Verify action to blog post on dashboard #SB_BL_11 #SB_BL_9
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Blog posts" screen
    And action to blog posts as "<KEY>"
      | KEY | Blog post                     | Action                        |
      | 1   | This title blog post 2 edited | Publish selected blog posts   |
      | 2   | This title blog post 2 edited | Unpublish selected blog posts |
      | 3   | This title blog post 1        | Add tags                      |
      | 4   | This title blog post 1        | Remove tags                   |
      | 5   | This title blog post 1        | Delete selected blog posts    |
    And add tags blog post as "<KEY>"
      | KEY | Action      | Tags        |
      | 3   | Add tags    | blog tags   |
      | 4   | Remove tags | tage edited |
    Then verify blogs post show on SF "<KEY>"
      | KEY | URL                                              | Blog post              | Show  |
      | 1   | /blogs/blog/this-title-blog-post-2?sbase_debug=1 | This title blog post 1 | false |
      | 2   | /blogs/blog/this-title-blog-post-2?sbase_debug=1 | This title blog post 1 | true  |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |





