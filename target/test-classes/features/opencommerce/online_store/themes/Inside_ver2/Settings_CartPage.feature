Feature: Theme setting v2 - Cart page - Cart goal
#  Theme inside next

#  Env:
#  insidev2_cart_page



  Background:
    Given open page "/admin/themes"
    Then click Customize button on Current theme
    And open preview page "Cart"
    And navigate to tab "Settings"

  Scenario Outline: Cart setting
    And select setting section "Cart"
    Then setting cart page as "<KEY>"
      | KEY | Cart type            | Only show Checkout button in cart drawer |
      | 1   | Refresh current page | true                                     |
      | 2   | Go to Cart page      | false                                    |
      | 3   | Go to Checkout page  | true                                     |
      | 4   | Open cart drawer     | false                                    |
      | 5   | Open cart drawer     | true                                     |
    Given open shop on storefront
    Then search and select the product "Cairbull Cycling Helmet"
    And verify cart on storefront as "<KEY>"
      | KEY | Cart type   | Only show Checkout button in cart drawer |
      | 1   | Refresh     | true                                     |
      | 2   | Page        | false                                    |
      | 3   | Checkout    | true                                     |
      | 4   | Cart drawer | false                                    |
      | 5   | Cart drawer | true                                     |
    Then close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |

  Scenario Outline: Cart goal
    And select setting section "Cart Goal"
    Then setting cart goal "<KEY>"
      | KEY | Enable cart goal | Motivational message                           | Goal amount | Goal reached                                   |
      | 1   | true             | Buy {{variable}} more to enjoy FREE Shipping 1 | 100         | Congrats! You are eligible for FREE Shipping 1 |
      | 2   | true             | Buy {{variable}} more to enjoy FREE Shipping 2 | 30          | Congrats! You are eligible for FREE Shipping 2 |
      | 3   | false            | Buy {{variable}} more to enjoy FREE Shipping 3 | 70          | Congrats! You are eligible for FREE Shipping 3 |
    Given open page "/"
    Then verify cart goal work on sf "<KEY>"
      | KEY | Enable cart goal | Motivational message                     | Goal reached                                   | Goal amount | Products                | Total price |
      | 1   | true             | Buy $65.01 more to enjoy FREE Shipping 1 |                                                | 100         | Face Lifting 3 in 1     | 31.99       |
      | 2   | true             |                                          | Congrats! You are eligible for FREE Shipping 2 | 50          | Cairbull Cycling Helmet | 60          |
      | 3   | false            |                                          |                                                | 70          | Cairbull Cycling Helmet | 60          |
    And close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |


