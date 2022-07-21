Feature: Homepage - cart goal
#  inside theme
#  Env: prod_inside_cartgoal
#  staging_inside_cartgoal
#  dev_inside_cartgoal

#  Products: Face Lifting 3 in 1>31.99, Cairbull Cycling Helmet>60

  Background:
    Given user login to shopbase dashboard
    When user navigate to "Online Store" screen
    And user click Customize on current theme
    And user navigate to "Setting" tab
    And select section "Cart Goal"

  Scenario Outline: cart goal #SB_OLS_THE_INS_22
    Then change setting cart goal "<KEY>"
      | KEY | Enable cart goal | Motivational message                           | Goal amount | Goal reached                                   |
      | 001 | true             | Buy {{variable}} more to enjoy FREE Shipping 1 | 100         | Congrats! You are eligible for FREE Shipping 1 |
      | 002 | true             | Buy {{variable}} more to enjoy FREE Shipping 2 | 50          | Congrats! You are eligible for FREE Shipping 2 |
      | 003 | false            | Buy {{variable}} more to enjoy FREE Shipping 3 | 70          | Congrats! You are eligible for FREE Shipping 3 |
    Given open shop on storefront
    Then verify cart goal work on sf "<KEY>"
      | KEY | Enable cart goal | Motivational message                     | Goal reached                                   | Goal amount | Products                | Total price |
      | 001 | true             | Buy $68.01 more to enjoy FREE Shipping 1 |                                                | 100         | Face Lifting 3 in 1     | 31.99       |
      | 002 | true             |                                          | Congrats! You are eligible for FREE Shipping 2 | 50          | Cairbull Cycling Helmet | 60          |
      | 003 | false            |                                          |                                                | 70          | Cairbull Cycling Helmet | 60          |
    And close browser
    Examples:
      | KEY |
      | 001 |
      | 002 |
      | 003 |