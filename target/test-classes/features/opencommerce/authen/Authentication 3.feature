@dashboardCommon @dashboard @authentication
Feature: Authentication
#env=
  Scenario Outline: Verify user sign in ShopBase dashboard #SB_AU_LGD_1 #SB_AU_LGD_2 #SB_AU_LGD_3 #SB_AU_LGD_4 #SB_AU_LGD_5 #SB_AU_LGD_6
    Given User sign in to ShopBase dashboard with email "<email>" and password "<password>"
    Then verify sign in status "<status>"
    Examples:
      | email                     | password   | status                               |
      |                           |            | Please enter your email and password |
      |                           | 123456     | Please enter your email and password |
      | shopbase@beeketing.net    |            | Please enter your email and password |
      | shopbase123@beeketing.net | 123456     | Email or password is not valid       |
      | shopbase@beeketing.net    | 123        | Email or password is not valid       |
      | shopbase@beeketing.net    | @password@ | Select a shop                        |
