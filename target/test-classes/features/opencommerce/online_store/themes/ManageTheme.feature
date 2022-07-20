Feature: Manage theme
#  Check create theme, publish, duplicate, remove theme
#  env: prod_sbase_manage_theme
#  staging_sbase_manage_theme
#  dev_sbase_manage_theme

  Background:
    Given user login to shopbase dashboard by API
    When user navigate to "Online Store" screen

  Scenario: Remove all theme #SB_OLS_THE_5
    Then remove all themes

  Scenario Outline: verify create and publish theme #SB_OLS_THE_2 #SB_OLS_THE_ROL_93 #SB_OLS_THE_ROL_92  #SB_OLS_THE_ROL_91 #SB_OLS_THE_12 #SB_OLS_THE_11 #SB_OLS_THE_10
    Then click "Explore more templates" button on dashboard
    And create new theme "<KEY>"
      | KEY | Theme  |
      | 001 | Bassy  |
      | 002 | Roller |
      | 003 | Inside |
    And verify created theme "<KEY>"
      | KEY | Theme name | Last saved | Base theme |
      | 001 | Bassy      | Just now   | Bassy      |
      | 002 | Roller     | Just now   | Roller     |
      | 003 | Inside     | Just now   | Inside     |
    When do actions with theme on block More themes "<KEY>"
      | KEY | Action  | Theme name |
      | 001 | Publish | Bassy      |
      | 002 | Publish | Roller     |
      | 003 | Publish | Inside     |
    Then verify current theme ID on dashboard "<KEY>"
      | KEY | Theme name |
      | 001 | Bassy      |
      | 002 | Roller     |
      | 003 | Inside     |
    Then verify sections in theme editor "<KEY>"
      | KEY | Theme name | Page Preview     | Sections                                                                                                                                                                                                                                           |
      | 001 | Bassy      | Homepage         | Header,Slideshow,Introduction text,Featured collection,Collection list,Newsletter,Best sellers,Recently viewed & featured products,Cart recommendations,Footer                                                                                     |
      | 001 | Bassy      | Product page     | Header,Featured collection,Collection list,Newsletter,Best sellers,Recently viewed & featured products,Cart recommendations,Footer                                                                                     |
      | 002 | Roller     | Homepage         | Header,Slideshow,Rich text,Image with text,Featured Collection,Customer testimonial,Collection list,Video,Newsletter,Best sellers,Recently viewed & featured products,Cart recommendations,Footer                                                  |
      | 002 | Roller     | Product page     | Header,Product,Collection list,Products from the same collections,Reviews,Who bought this also bought,Best sellers,Handpicked products,Recently viewed & featured products,Cart recommendations,Footer                                             |
      | 002 | Roller     | Collection pages | Header,Collection,Best sellers,Recently viewed & featured products,Cart recommendations,Footer                                                                                                                                                     |
      | 003 | Inside     | Home             | Announcement Bar,Header,Slideshow,Collection List,Featured Collection,Rich Text,Image With Text,Banner,Customer testimonial,Best Sellers,Recently Viewed & Featured Products,Cart Recommendations,Footer Menu,Footer Content,Footer                |
      | 003 | Inside     | Product          | Announcement Bar,Header,Product,Collection List,Products From The Same Collections,Reviews,Who Bought This Also Bought,Best Sellers,Handpicked Products,Recently Viewed & Featured Products,Cart Recommendations,Footer Menu,Footer Content,Footer |
      | 003 | Inside     | Collection       | Announcement Bar,Header,Collection,Best Sellers,Recently Viewed & Featured Products,Cart Recommendations,Footer Menu,Footer Content,Footer                                                                                                         |
    Given open shop on storefront
    Then verify theme on sf "<KEY>"
      | KEY | Base theme |
      | 001 | Bassy      |
      | 002 | Roller     |
      | 003 | Inside     |
    And add products "<Products>" to cart
    Then go to cart page
    And click button "Checkout"
    And verify order summary "<KEY>"
      | KEY | Products        | Subtotal | Total price | DisCount Apply |
      | 001 | product 1>20.00 | 20.00    | 20.00       |                |
      | 002 | product 2>35.00 | 35.00    | 35.00       |                |
      | 003 | product 3>15.00 | 15.00    | 15.00       |                |
    Then close browser
    Examples:
      | KEY | Products  |
      | 001 | product 1 |
      | 002 | product 2 |
      | 003 | product 3 |


  Scenario Outline: Duplicate theme #SB_OLS_THE_4
    Then do actions with theme on block More themes "<KEY>"
      | KEY | Action    | Theme name |
      | 001 | Duplicate | Bassy      |
      | 002 | Duplicate | Roller     |
      | 003 | Duplicate | Inside     |
    And verify created theme "<KEY>"
      | KEY | Theme name     | Last saved |
      | 001 | Copy of Bassy  |            |
      | 002 | Copy of Roller |            |
      | 003 | Copy of Inside |            |
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |


  Scenario Outline: Rename theme #SB_OLS_THE_3
    Then do actions with theme on block More themes "<KEY>"
      | KEY | Action | Theme name     | New name   |
      | 001 | Rename | Copy of Bassy  | Bassy new  |
      | 002 | Rename | Copy of Roller | Roller new |
      | 003 | Rename | Copy of Inside | Inside new |
    And verify created theme "<KEY>"
      | KEY | Theme name | Last saved |
      | 001 | Bassy new  | Just now   |
      | 002 | Roller new | Just now   |
      | 003 | Inside new | Just now   |
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |


  Scenario Outline: verify copy theme #SB_OLS_THE_6 #SB_OLS_THE_8 #SB_OLS_THE_13
    When do actions with theme on block More themes "<KEY>"
      | KEY | Action     | Shop name     | Shop domain | Theme id | Theme name | Message                    |
      | 001 | Copy theme |               |             |          | Roller new | Your theme has been copied |
      | 002 | Copy theme |               |             |          | Bassy new  | Your theme has been copied |
      | 003 | Copy theme |               |             |          | Inside new | Your theme has been copied |
      | 004 | Copy theme |               |             | 0000321  |            | Cannot copy theme          |
      | 005 | Copy theme |               |             |          |            | Theme ID is required       |
      | 006 | Copy theme | shopnamecopy1 | shopdomain1 |          | Roller     | Your theme has been copied |
      | 007 | Copy theme | shopnamecopy2 | shopdomain2 |          | Bassy      | Your theme has been copied |
      | 008 | Copy theme | shopnamecopy3 | shopdomain3 |          | Inside     | Your theme has been copied |
      | 009 | Copy theme | shopnamecopy4 | shopdomain4 |          | Roller     | Cannot copy theme          |
    And verify created theme "<KEY>"
      | KEY | Theme name | Last saved |
      | 001 | Roller new | Just now   |
      | 002 | Bassy new  | Just now   |
      | 003 | Inside new | Just now   |
      | 006 | Roller     | Just now   |
      | 007 | Bassy      | Just now   |
      | 008 | Inside     | Just now   |
    Examples:
      | KEY | Description                                                   |
      | 001 | Copy id Roller theme in the same shopbase                     |
      | 002 | Copy id Bassy theme in the same shopbase                      |
      | 003 | Copy id Inside theme in the same shopbase                     |
      | 004 | Copy id theme does not exist                                  |
      | 005 | Id theme empty                                                |
      | 006 | Copy id Roller theme to another shop, same account, shopbase  |
      | 007 | Copy id Bassy theme to another shop, same account, shopbase   |
      | 008 | Copy id Inside theme to another shop, same account, printbase |
      | 009 | Copy id Roller theme to another shop, another account         |

#    Scenario: test
#      Given open page "/admin/theme-editor-v2/3007730"
#      Then test