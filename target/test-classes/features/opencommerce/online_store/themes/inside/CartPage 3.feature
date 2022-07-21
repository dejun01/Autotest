Feature: Cart Page
#  Theme inside

#  Env:
#  prod_sbase_inside_cart_page
#  staging_sbase_inside_cart_page
#  dev_sbase_inside_cart_page

#  Data test:
#  Product: Cairbull Cycling Helmet


  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And choose preview page "Cart page"


  Scenario Outline: Cart setting #SB_OLS_THE_INS_20
    Then change setting cart page as "<KEY>"
      | KEY | Cart type            | Only show Checkout button in cart drawer |
      | 1   | Open Cart drawer     | false                                    |
      | 2   | Open Cart drawer     | true                                     |
      | 3   | Refresh current page | true                                     |
      | 4   | Go to Cart page      | false                                    |
      | 5   | Go to Checkout page  | true                                     |
    Given open shop on storefront
    Then search and select the product "Cairbull Cycling Helmet"
    And verify cart on storefront as "<KEY>"
      | KEY | Cart type   | Only show Checkout button in cart drawer |
      | 1   | Cart drawer | false                                    |
      | 2   | Cart drawer | true                                     |
      | 3   | Refresh     | true                                     |
      | 4   | Page        | false                                    |
      | 5   | Checkout    | true                                     |
    Then close browser
    Examples:
      | KEY |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |




