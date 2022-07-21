Feature: Affiliate smoke test

  #prod_sbase_smoketest_sbase_affiliate
  Scenario: Sign in and check statistics of Affiliate
    Given user sign in account partners
      | Email                   | Password |
      | shopbase4@beeketing.net | 123456   |
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
    When sign up an account shopbase with refer link
      | Email    | Password | Shopname                | Firstname | Lastname | Social file  | Business Type   | Store type               | Product Category               | Question                                                  | Answer                                                |
      | huongle+ | 123456   | shop-aut-can-be-deleted | huong     | le       | shopbase.com | Print-on-demand | I want a PrintBase store | No thanks, I will decide later | How would you describe your experience of selling online? | I'm a newbie and have no experience in selling online |
    And checkout an order on storefront
      | Product name                     | Quantity |
      | PrintBase multiple 2D products 1 | 5        |
    And get statistics affiliate by api after
      | Time       |
      | last_month |
      | last_week  |
      | this_week  |
      | this_month |
      | this_year  |
    Then verify statistics affiliate by api