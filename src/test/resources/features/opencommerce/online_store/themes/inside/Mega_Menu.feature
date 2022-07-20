Feature: Homepage - Mega Menu

#  Theme inside

#  Env
#  prod_inside_mega_menu
#  staging_inside_mega_menu
#  dev_inside_mega_menu

#  Data test
#  Collections: Best Selling
#  Products: Lancet Pen Lancing Device
#  Menu list: Main menu, Support


  Scenario Outline: mega menu #SB_OLS_THE_INS_71
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And select section "Header"
    Then change mega menu "<KEY>"
      | KEY | Enable mega menu | Link title     | Enable menu | Menu title | Link                               | Menu list | Image          | Image URL                   | Headline   |
      | 001 | false            | Shop           |             |            |                                    |           |                |                             |            |
      | 002 | true             | Shop           | false       | Menu 1     | Products>Lancet Pen Lancing Device | Main menu | front/Logo.png | Collections>Best Selling    | Headline 1 |
      | 003 | true             | Shop           | true        | Menu 1     | Products>Lancet Pen Lancing Device | Main menu | front/Logo.png | Collections>Best Selling    | Headline 1 |
      | 003 | true             | Shop           | true        | Menu 2     | Collections>All Collections        | Support   |                | Pages>All reviews           | Headline 2 |
    Given open shop on storefront
    Then verify mega menu on sf "<KEY>"
      | KEY | Enable mega menu | Link title     | Enable menu | Menu title | Link                                | Menu list | Image          | Image URL                 | Headline   |
      | 001 | false            | Shop           |             |            |                                     |           |                |                           |            |
      | 002 | true             | Shop           | false       |            |                                     |           |                |                           |            |
      | 003 | true             | Shop           | true        | Menu 1     | /products/lancet-pen-lancing-device | Main menu | front/Logo.png | /collections/best-selling | Headline 1 |
      | 003 | true             | Shop           | true        | Menu 2     | /collections                        | Support   |                | /pages/all-reviews        | Headline 2 |
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |