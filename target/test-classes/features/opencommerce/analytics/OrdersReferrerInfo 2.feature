@Analytics
Feature: Improve Order's referrer info
  #sbase_orders_referrer_info

  Scenario: Tracking data with order converted after 1 sessions #SB_ANA_IMP_ORD_REF_4
    Given initialization data
    #1 - Create new order with UTM parameters in 1 session
    Given open product with UTM and checkout
      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | referrer |
      | KD14         | 1        | custom_source | custom_medium | custom_camp  | custom_content | custom_term |          |
    Then checkout by Stripe successfully on one page with "other" user
    #2 - Return to Shopbase and verify data
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    Then access to a recent order created and verify data "1"
      | session       | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | channel | referrer | first_page_visited                                                                                                                                                                          |
      | FIRST_SESSION | custom_source | custom_medium | custom_camp  | custom_content | custom_term | direct  | N/A      | https://au-order-referrer-info.stag.myshopbase.net/products/kd14?utm_source=custom_source&utm_medium=custom_medium&utm_campaign=custom_camp&utm_content=custom_content&utm_term=custom_term |
      | LAST_SESSION  | direct        | none          | not_set      | not_set        | not_set     | direct  | N/A      |                                                                                                                                                                                             |
    And close browser

  Scenario: Tracking data with order converted after 3 sessions #SB_ANA_IMP_ORD_REF_6
    Given initialization data
    #1 - Create new order with UTM parameters in 3 session
    Given open product with UTM and checkout
      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | referrer |
      | KD14         | 1        | custom_source | custom_medium | custom_camp  | custom_content | custom_term |          |
    When user navigate to checkout screen
    Then checkout by Stripe successfully on one page with "other" user
    #2 - Return to Shopbase and verify data
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    Then access to a recent order created and verify data "3"
      | session       | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | channel | referrer | first_page_visited                                                                                                                                                                          |
      | FIRST_SESSION | custom_source | custom_medium | custom_camp  | custom_content | custom_term | direct  | N/A      | https://au-order-referrer-info.stag.myshopbase.net/products/kd14?utm_source=custom_source&utm_medium=custom_medium&utm_campaign=custom_camp&utm_content=custom_content&utm_term=custom_term |
      | LAST_SESSION  | direct        | none          | not_set      | not_set        | not_set     | direct  | N/A      |                                                                                                                                                                                             |
    And close browser

  Scenario: Filter order by UTM (source, medium, campaign, term, content) #SB_ANA_IMP_ORD_REF_20
    Given initialization data
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    Then filter with UTM parameters and verify first order in list
      | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    |
      | custom_source | custom_medium | custom_camp  | custom_content | custom_term |

  Scenario: Tracking data with 2 orders consecutively with same browser
    Given initialization data
    #1 - Create the first order with UTM parameters
    Given open product with UTM and checkout
      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | referrer |
      | KD14         | 1        | custom_source | custom_medium | custom_camp  | custom_content | custom_term |          |
    Then checkout by Stripe successfully on one page with "other" user
    #2 - Create another order in the same browser
    Given open product with UTM and checkout
      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | referrer |
      | KD14         | 1        | custom_source | custom_medium | custom_camp  | custom_content | custom_term |          |
    Then checkout by Stripe successfully on one page with "other" user
    #3 - Return to Shopbase and verify data
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    Then access to a recent order created and verify data "1"
      | session       | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | channel | referrer | first_page_visited                                                                                                                                                                          |
      | FIRST_SESSION | custom_source | custom_medium | custom_camp  | custom_content | custom_term | direct  | N/A      | https://au-order-referrer-info.stag.myshopbase.net/products/kd14?utm_source=custom_source&utm_medium=custom_medium&utm_campaign=custom_camp&utm_content=custom_content&utm_term=custom_term |
      | LAST_SESSION  | direct        | none          | not_set      | not_set        | not_set     | direct  | N/A      |                                                                                                                                                                                             |
    And close browser