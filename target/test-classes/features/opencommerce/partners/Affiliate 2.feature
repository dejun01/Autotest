Feature: Affiliate auto

#sbase_affiliate

  Scenario Outline: sign in account shopbase on partner link, which isn't partner #SB_PN_AT_31
    Given user signup ShopBase
      | Domain   | Email   | Password   |
      | <Domain> | <Email> | <Password> |
    When login to shopbase partner
      | Email   | Password   |
      | <Email> | <Password> |
    Examples:
      | Domain                  | Email    | Password        |
      | shop-aut-can-be-deleted | huongle+ | I^oTUW8%mW$}PUL |

  Scenario: Sign in and check statistics of Affiliate #SB_PN_AT_30 #SB_PN_AT_29 #SB_PN_AT_28
    Given user sign in account partners
    And switch to "Affiliate" tab in menu
    And change custom link refer people
      | Code                         | Message                                                                                      |
      |                              | Field cannot be emptied                                                                      |
      | autStagfbbbbbbbbbbbbbbbbbbbb | The parameter exceeds 20 characters. Please limit your characters.                           |
      | autStagrthg rhtghrthrthrt    | The characters must include numbers or lower case letters [a-z] or upper case letters [A-Z]. |
      | referCode                    | Success                                                                                      |
      | fpr                          | Success                                                                                      |
    And get statistics affiliate by api before
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    When sign up an account shopbase with refer link  is "true"
      | Email     | Password | Shopname                | Firstname | Lastname | Country | Personal location | Phone       | Social file  | Revenue   | Business Type   | Store type               | Question                                                                          | Answer                                | Question1                                             | Answer1 | Question2                         | Answer2 |
      | autotest+ | 123456   | shop-aut-can-be-deleted | huong     | le       | Vietnam | Vietnam           | 098 1111111 | shopbase.com | $500,000+ | Print On Demand | I want a PrintBase store | Which segment below best describes your main POD product category for this store? | 2D (T-shirt, Hoodie, Mug, Doormat...) | Which platforms you are using/ used to use the most ? | Teechip | How did you know about PrintBase? | Other   |
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
