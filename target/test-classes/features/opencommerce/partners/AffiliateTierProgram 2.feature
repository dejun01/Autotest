Feature: Affiliate tier program

  ##sbase_affiliate_tier

  Scenario: verify affiliate tier program in partner, user is in tier #SB_PN_AT_12
    Given user sign in account partners
    When switch to "Affiliate>Affiliate program" tab in menu
    And  verify Affiliate program page display
    When switch to "Affiliate>Manage referrals" tab in menu
    And  verify Manage referrals page display

  Scenario: verify Affiliate program page #SB_PN_AT_2 #SB_PN_AT_4 #SB_PN_AT_17
    Given user sign in account partners
    And switch to "Affiliate>Affiliate program" tab in menu
    When change custom link refer people
      | Code                         | Message                                                                                      |
      |                              | Field cannot be emptied                                                                      |
      | autStagfbbbbbbbbbbbbbbbbbbbb | The parameter exceeds 20 characters. Please limit your characters.                           |
      | autStagrthg rhtghrthrthrt    | The characters must include numbers or lower case letters [a-z] or upper case letters [A-Z]. |
      | uniqueCode                   | Success                                                                                      |
      | fpr                          | Success                                                                                      |
    Then verify benefit and cashback rate displayed

  Scenario: verify statistics affiliate tier program after sign up via affiliate link #SB_PN_AT_11
    Given get statistics affiliate by api before
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    When sign up an account shopbase with refer link  is "true"
      | Email    | Password | Shopname                | Firstname | Lastname | Country | Personal location | Phone       | Social file  | Revenue   | Business Type   | Store type               | Question                                                           | Answer                                | Question1                                             | Answer1 | Question2                         | Answer2 |
      | autotest | 123456   | shop-aut-can-be-deleted | auto      | test     | Vietnam | Vietnam           | 098 1111111 | shopbase.com | $500,000+ | Print On Demand | I want a PrintBase store | Which segment below best describes your main POD product category? | 2D (T-shirt, Hoodie, Mug, Doormat...) | Which platforms you are using/ used to use the most ? | Teechip | How did you know about PrintBase? | Other   |
    When user navigate to "Settings" screen
    And checkout an order on storefront
      | Product name                     | Quantity | Size |
      | PrintBase multiple 2D products 1 | 5        | S    |
    And get statistics affiliate by api after
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    Then verify statistics affiliate by api
    And verify new user became a ref of merchant

  Scenario: verify statistics affiliate tier program after sign up and add invitation code #SB_PN_AT_6 #SB_PN_AT_8
    When sign up an account shopbase with refer link  is "false"
      | Email    | Password | Shopname                | Firstname | Lastname | Country | Personal location | Phone       | Social file  | Revenue   | Business Type   | Store type               | Question                                                           | Answer                                | Question1                                             | Answer1 | Question2                         | Answer2 |
      | autotest | 123456   | shop-aut-can-be-deleted | huong     | le       | Vietnam | Vietnam           | 098 1111111 | shopbase.com | $500,000+ | Print On Demand | I want a PrintBase store | Which segment below best describes your main POD product category? | 2D (T-shirt, Hoodie, Mug, Doormat...) | Which platforms you are using/ used to use the most ? | Teechip | How did you know about PrintBase? | Other   |
    When user navigate to "Settings" screen
    Then Add invitaion code
      | Promoter | Message                             |
      | code     | All changes were successfully saved |
    And verify new user became a ref of merchant

  Scenario: verify shopbase affiliate program display #SB_PN_AT_10
    Given user sign in account partners
    When switch to "Affiliate>Affiliate program" tab in menu
    And verify ShopBase Affiliate Program display

  Scenario: verify number of clicks statistic #SB_PN_AT_5
    Given get number of click by api
    When open referrals unique link
      | Link                       | Sub-id |
      | https://www.printbase.com/ |        |
      | https://www.shopbase.com/  |        |
      | https://www.printbase.com/ | abc    |
      | https://www.shopbase.com/  | abc    |
    Then verify statistic number of click by api

  Scenario: verify manage link tab in your referal page
    Given user sign in account partners
    And switch to "Affiliate>Manage referrals" tab in menu
#    When add new a sub id affiliate link
#      | Link                       | Sub-id  | Message                                    |
#      | https://www.printbase.com/ |         | Can't be blank, please fill out this field |
#      | https://www.printbase.com/ | 123 ABC | Sub-id invalid                             |
#      | https://www.printbase.com/ | abc     | Fpt code of user is exist.                 |
#      | https://www.printbase.com/ | id      | success                                    |
#    Then delete a link which has sub id
    And search link by keyword
      | Keyword   | Result |
      | A         | Yes    |
      | not exist | No     |






