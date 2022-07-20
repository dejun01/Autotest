Feature: Update Create Page - ShopBase
  #Add a new page
  #sbase_create_page

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store>Pages" screen

  Scenario Outline: Add new page for online store #SB_OLS_PG_12 #SB_OLS_PG_11 #SB_OLS_PG_10
    Then create new page and edit page after created "<KEY>"
      | KEY | Title      | Text   | Link                      | Video                                       | Template     | Status  | URL       |
      | 1   | New Page 1 | Note 1 | https://www.shopbase.com/ | https://www.youtube.com/watch?v=3-Is51CllcY | Page         | Visible | new-url-1 |
      | 2   | New Page 2 | Note 2 | https://www.shopbase.com/ | https://www.youtube.com/watch?v=-TPZqJa6iik | Page         | Hidden  | new-url-2 |
      | 3   | New Page 3 | Note 3 | https://www.shopbase.com/ | https://www.youtube.com/watch?v=65sGWKbUifw | Review page  | Visible | new-url-3 |
      | 4   | New Page 4 | Note 4 | https://www.shopbase.com/ | https://www.youtube.com/watch?v=iYKFZb4wg_8 | Contact page | Visible | new-url-4 |
      | 5   | New Page 5 | Note 5 | https://www.shopbase.com/ | https://www.youtube.com/watch?v=HmoTjjtQaAE | Review page  | Visible |           |
    And verify page created on dashboard "<KEY>"
      | KEY | Title      | Status  |
      | 1   | New Page 1 | Visible |
      | 2   | New Page 2 | Hidden  |
      | 3   | New Page 3 | Visible |
      | 4   | New Page 4 | Visible |
      | 5   | New Page 5 | Visible |
    And verify page created on storefront "<KEY>"
      | KEY | Title      | Link                      | Video                               | Template     | isVisible | URL       |
      | 1   | New Page 1 | https://www.shopbase.com/ | //www.youtube.com/embed/3-Is51CllcY | Page         | true      | new-url-1 |
      | 2   | New Page 2 | https://www.shopbase.com/ | //www.youtube.com/embed/-TPZqJa6iik | Page         | false     | new-url-2 |
      | 3   | New Page 3 | https://www.shopbase.com/ | //www.youtube.com/embed/65sGWKbUifw | Review page  | true      | new-url-3 |
      | 4   | New Page 4 | https://www.shopbase.com/ | //www.youtube.com/embed/iYKFZb4wg_8 | Contact page | true      | new-url-4 |
      | 5   | New Page 5 | https://www.shopbase.com/ | //www.youtube.com/embed/HmoTjjtQaAE | Review page  | true      |           |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |

  Scenario Outline: Edit page just created #SB_OLS_PG_9 #SB_OLS_PG_8 #SB_OLS_PG_7
    Then create new page and edit page after created "<KEY>"
      | KEY | Old title  | Title       | Text    | Link                               | Video                                       | Template     | Status  | URL        |
      | 1   | New Page 1 | New Page 7  | Note 7  | https://www.opencommercegroup.com/ | https://www.youtube.com/watch?v=i0TJ1T0cLCk | Page         | Hidden  | new-url-7  |
      | 2   | New Page 2 | New Page 8  | Note 8  | https://www.opencommercegroup.com/ | https://www.youtube.com/watch?v=3-Is51CllcY | Contact page | Visible | new-url-8  |
      | 3   | New Page 3 | New Page 9  | Note 9  | https://www.opencommercegroup.com/ | https://www.youtube.com/watch?v=-TPZqJa6iik | Page         | Visible | new-url-9  |
      | 4   | New Page 4 | New Page 10 | Note 10 | https://www.opencommercegroup.com/ | https://www.youtube.com/watch?v=65sGWKbUifw | Review page  | Visible | new-url-10 |
    And verify page created on dashboard "<KEY>"
      | KEY | Title       | Status  |
      | 1   | New Page 7  | Hidden  |
      | 2   | New Page 8  | Visible |
      | 3   | New Page 9  | Visible |
      | 4   | New Page 10 | Visible |
    And verify page created on storefront "<KEY>"
      | KEY | Title       | Link                               | Video                               | Template     | isVisible | URL        |
      | 1   | New Page 7  | https://www.opencommercegroup.com/ | //www.youtube.com/embed/i0TJ1T0cLCk | Page         | false     | new-url-7  |
      | 2   | New Page 8  | https://www.opencommercegroup.com/ | //www.youtube.com/embed/3-Is51CllcY | Contact page | true      | new-url-8  |
      | 3   | New Page 9  | https://www.opencommercegroup.com/ | //www.youtube.com/embed/-TPZqJa6iik | Page         | true      | new-url-9  |
      | 4   | New Page 10 | https://www.opencommercegroup.com/ | //www.youtube.com/embed/65sGWKbUifw | Review page  | true      | new-url-10 |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |


  Scenario Outline: Actions with page #SB_OLS_PG_13
    Then do actions with new page "<KEY>"
      | KEY | Title      | Action    |
      | 1   | New Page 7 | Unpublish |
      | 2   | New Page 8 | Publish   |
      | 3   | New Page 9 | Delete    |
      | 4   | All pages  | Delete    |
    And verify page created on dashboard "<KEY>"
      | KEY | Title      | Status  |
      | 1   | New Page 7 | Hidden  |
      | 2   | New Page 8 | Visible |
      | 3   | New Page 9 | Deleted |
      | 4   | No page    |         |
    And verify page created on storefront "<KEY>"
      | KEY | Title       | isVisible | URL        |
      | 1   | New Page 7  | false     | new-url-7  |
      | 2   | New Page 8  | true      | new-url-8  |
      | 3   | New Page 9  | false     | new-url-9  |
      | 4   | New Page 10 | false     | new-url-10 |
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |