Feature: cashback printbase and shopbase
  #  sbase_partner_cashback

  Scenario Outline:  verify Cashback printbase
    Given get cashback statistics affiliate by api before
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    When checkout an order on storefront
      | Product name   | Quantity   |
      | <Product name> | <Quantity> |
    Then get cashback statistics "Printbase"
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    And verify cashback printbase
      | Action   | Quantity   | Base product   |
      | Checkout | <Quantity> | <Base product> |
    Then refund order on hive pbase
      | Order name   | Quantity | Withdraw seller balance | Send mail |
      | @order name@ | 1        | true                    | false     |
    And verify cashback printbase
      | Action | Quantity   | Base product   |
      | Refund | <Quantity> | <Base product> |
    Examples:
      | Product name         | Quantity | Base product |
      | PrintBase Quilt Q101 | 5        | Gold         |
      | PrintBase Quilt Q101 | 5        | Gold         |


  Scenario Outline: verify cashback shopbase
    Given get cashback statistics affiliate by api before
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    When login to dashboard
    And create a shop with name is "au-cashback-sb-"
#    And choose shop as "<KEY>"
#      | KEY | Shop name                    |
#      | 1   | au-cashback-sb-1637035789879 |
    And input store information as "<KEY>"
      | KEY | Store country | Your personal location | Phone number | Social profile             |
      | 1   | Vietnam       | Vietnam                | 0985676567   | https://www.google.com.vn/ |
    And select store type as "<KEY>"
      | KEY | Business type                        | Store type                                   | Category/Niche |
      | 1   | Dropshipping>I want a ShopBase store | I want to have a general dropshipping store. |                |
    And customize store as "<KEY>"
      | KEY | Type     | Import content | Store import | Answer question | Customize style | Color | Font | Type product |
      | 1   | ShopBase | No thanks      |              |                 | Yes             |       |      | Products     |
    When update end free trial of shop by API
    And user navigate to "Settings" screen
    And confirm a plan is "<Package>" in cycle is "<Cycle>"
    Then get cashback statistics "ShopBase"
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    And verify cashback statistics "ShopBase" as "<KEY>"
      | KEY | Package   | Cycle   |
      | 1   | <Package> | <Cycle> |
    Examples:
      | KEY | Package    | Cycle   |
      | 1   | Basic Base | Monthly |