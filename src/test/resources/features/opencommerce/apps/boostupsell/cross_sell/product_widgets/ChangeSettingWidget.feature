Feature: Change settings widget

#   Env: us_post_purchase


  Scenario: Turn off all widget
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And user navigate to "Product widgets" screen
    When turn "off" all product widgets

  Scenario Outline: Verify product widget is not shown #SB_BUS_PW_2
    Given Description: "<Testcase>"
    Given open page "/admin/apps/boost-upsell/cross-sell/product-widgets"
    And wait page "Product widgets" show
    And open page Customize of widget "<Widget>"
    And reset widget's settings to default as "<KEY>"
      | KEY |
      | 001 |
      | 002 |
      | 003 |
      | 004 |
      | 005 |
    Then open shop on storefront
    And verify widget's settings as "<KEY>"
      | KEY | Place widget       | Product added | Widget name                         | Is show |
      | 001 |                    |               | Best sellers                        | false   |
      | 001 | Ring Cat           |               | Best sellers                        | false   |
      | 001 |                    | Ring Cat      | Best sellers                        | false   |
      | 002 |                    |               | Recently viewed & featured products | false   |
      | 002 | Bracelet           |               | Recently viewed & featured products | false   |
      | 002 |                    | Slicer        | Recently viewed & featured products | false   |
      | 003 | Ring Cat           |               | Who bought this also bought         | false   |
      | 004 |                    | Ring Cat      | Cart recommendations                | false   |
      | 004 | /collections/dress |               | Cart recommendations                | false   |
    Examples:
      | KEY | Widget                              | Testcase                                             |
      | 001 | Best sellers                        | Customize Store best sellers                         |
      | 002 | Recently viewed & featured products | Customize Recently viewed & featured recommendations |
      | 003 | Who bought this also bought         | Customize Who bought this also bought                |
      | 004 | Cart recommendations                | Customize Cart recommendations                       |


  Scenario: Turn on all widget
    Given open page "/admin/apps/boost-upsell/cross-sell/product-widgets"
    And wait page "Product widgets" show
    When turn "on" all product widgets


  Scenario Outline: Customize all widget #SB_BUS_PW_4 #SB_BUS_PW_5 #SB_BUS_PW_6 #SB_BUS_PW_7
    Given Description: "<Testcase>"
    Given open page "/admin/apps/boost-upsell/cross-sell/product-widgets"
    And wait page "Product widgets" show
    And open page Customize of widget "<Widget>"
    And reset widget's settings to default as "<KEY>"
      | KEY |
      | 000 |
      | 001 |
      | 002 |
      | 003 |
      | 004 |
    And open page Customize of widget "<Widget>"
    And customize widget as "<KEY>"
      | KEY | Header               | Font header                       | Font product name       | Font product price         | Number of products | Max product per slide | Is on add to cart button | Call to action text        | Place widget              |
      | 001 | Store Best sellers   | 26px>bold,italic,right            | 13px>bold,right         | 17px>bold,italic,underline | 11                 | 4                     | false                    | 18px>bold,underline,italic | Home page,Cart page       |
      | 002 | Recently viewed      | 26px>bold,italic,center           | 15px>bold,italic,center | 20px>underline,bold,center | 8                  | 2                     | true                     | 17px>bold,underline        |                           |
      | 003 | Bought also bought   | 27px>bold,italic,underline,center | 16px>bold,italic,left   | 20px>bold,right            | 12                 | 6                     | true                     | 17px>bold,italic           |                           |
      | 004 | Cart recommendations | 27px>bold,italic,center           | 16px>bold,italic,right  | 20px>underline,bold,left   | 10                 | 4                     | true                     | 17px>italic,underline      | Collection page,Cart page |
    Then open shop on storefront
    And verify widget's settings as "<KEY>"
      | KEY | Place widget            | Product added           | Widget name          | Is show | Font header                       | Font product name       | Font product price         | Number of products | Max product per slide | Is on add to cart button | Call to action text        |
      | 000 |                         |                         | Best sellers         |         | 25px>center                       | 14px>left               | 16px>left                  | 12                 | 6                     | true                     | 16px                       |
      | 000 | MC Vestido Summer Dress |                         | Best sellers         | false   |                                   |                         |                            |                    |                       |                          |                            |
      | 000 |                         | MC Vestido Summer Dress | Best sellers         | false   |                                   |                         |                            |                    |                       |                          |                            |
      | 001 |                         |                         | Store Best sellers   |         | 26px>bold,italic,right            | 13px>bold,right         | 17px>bold,italic,underline | 11                 | 4                     | false                    | 18px>bold,underline,italic |
      | 001 |                         | MC Vestido Summer Dress | Store Best sellers   |         | 26px>bold,italic,right            | 13px>bold,right         | 17px>bold,italic,underline | 11                 | 4                     | false                    | 18px>bold,underline,italic |
      | 002 | /?sbase_debug=1         |                         | Recently viewed      |         | 26px>bold,italic,center           | 15px>bold,italic,center | 20px>underline,bold        | 8                  | 2                     | true                     | 17px>bold,underline        |
      | 002 | Sea Turtle Necklace     |                         | Recently viewed      |         | 26px>bold,italic,center           | 15px>bold,italic,center | 20px>underline,bold        | 8                  | 2                     | true                     | 17px>bold,underline        |
      | 002 |                         | Candles                 | Recently viewed      |         | 26px>bold,italic,center           | 15px>bold,italic,center | 20px>underline,bold        | 8                  | 2                     | true                     | 17px>bold,underline        |
      | 003 | MC Vestido Summer Dress |                         | Bought also bought   |         | 27px>bold,italic,underline,center | 16px>bold,italic,left   | 20px>bold                  | 12                 | 6                     | true                     | 17px>bold,italic           |
      | 004 |                         | MC Vestido Summer Dress | Cart recommendations |         | 27px>bold,italic,center           | 16px>bold,italic,right  | 20px>underline,bold,left   | 10                 | 4                     | true                     | 17px>italic,underline      |
      | 004 | /collections/dress      |                         | Cart recommendations |         | 27px>bold,italic,center           | 16px>bold,italic,right  | 20px>underline,bold,left   | 10                 | 4                     | true                     | 17px>italic,underline      |
    Examples:
      | KEY | Widget                              | Testcase                                             |
      | 000 | Best sellers                        | Verify default of Store best seller                  |
      | 001 | Best sellers                        | Customize Store best sellers                         |
      | 002 | Recently viewed & featured products | Customize Recently viewed & featured recommendations |
      | 003 | Who bought this also bought         | Customize Who bought this also bought                |
      | 004 | Cart recommendations                | Customize Cart recommendations                       |

