@Analytics
Feature: UTM for sales report
  #sbase_utm_sales_reports

  Background: Login to dashboard
    Given user login to shopbase dashboard

  Scenario: Verify UTM sales report with default source/medium and open directly #SB_ANA_SC_ANA_UTM_9 #SB_ANA_SC_ANA_UTM_10 #SB_ANA_SC_ANA_UTM_11
    Given clear all data
    Then get data UTM sales report init by API
      | Report by     | Value         | Product filter | Title                 | Shop Type | Report type |
      | source_medium | direct / none | product_title  | Sample Testing Part 2 | net_sales | product_utm |
    Then open storefront shop on a new tab
    When open product "sample-testing-part-2" and one page checkout without verify with "other" user
    And switch to the first tab
    And wait 50000 second
    And wait 50000 second
    And user navigate to "Analytics" screen
    Then verify data UTM sales report by API
      | Report by     | Value         | Product filter | Title                 | Shop Type | Report type | View product | Add to cart | Reached checkout | Orders | Net quantity | Net sales | Net profit |
      | source_medium | direct / none | product_title  | Sample Testing Part 2 | net_sales | product_utm | +1           | +1          | +1               | +1     | +1           | +10       | 0          |
    Then quit browser

  Scenario: Verify UTM sales report with default content and open via Google click #SB_ANA_SC_ANA_UTM_2 #SB_ANA_SC_ANA_UTM_3 #
    Given clear all data
    Then get data UTM sales report init by API
      | Report by  | Value  | Product filter | Title                 | Shop Type | Report type |
      | utm_source | Google | product_title  | Sample Testing Part 2 | net_sales | product_utm |
    Given open product with UTM and checkout
      | Product name          | Quantity | utm_source | utm_medium | utm_campaign | utm_content | utm_term | referrer                                                                                            |
      | sample-testing-part-2 | 1        |            |            |              |             |          | &gclid=Cj0KCQiA-K2MBhC-ARIsAMtLKRsDDE6xKxM-u6hMM9uaAvT_T-0HGF5bqVWccTHPcUD1lLHwVHik7EcaAvZ8EALw_wcB |
    And checkout by Stripe successfully on one page with "other" user
    And wait 50000 second
    And wait 50000 second
    And close browser
    Given user login to shopbase dashboard
    And user navigate to "Analytics" screen
    Then verify data UTM sales report by API
      | Report by  | Value  | Product filter | Title                 | Shop Type | Report type | View product | Add to cart | Reached checkout | Orders | Net quantity | Net sales | Net profit |
      | utm_source | Google | product_title  | Sample Testing Part 2 | net_sales | product_utm | +1           | +1          | +1               | +1     | +1           | +10       | 0          |
    Then quit browser

  Scenario: Verify UTM sales report with UTM content and open via FB click #SB_ANA_SC_ANA_UTM_4
    Given clear all data
    Then get data UTM sales report init by API
      | Report by     | Value                         | Product filter | Title                 | Shop Type | Report type |
      | source_medium | custom_source / custom_medium | product_title  | Sample Testing Part 2 | net_sales | product_utm |
    Given open product with UTM and checkout
      | Product name          | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | referrer                                                              |
      | sample-testing-part-2 | 1        | custom_source | custom_medium | custom_camp  | custom_content | custom_term | &fbclid=IwAR1OhLqzt8jGAWEOyb9BEdGylqf8NmGZlvyLvaQ2agyDiCK8_3ol0UZ5mAk |
    And checkout by Stripe successfully on one page with "other" user
    And wait 50000 second
    And wait 50000 second
    And close browser
    Given user login to shopbase dashboard
    And user navigate to "Analytics" screen
    Then verify data UTM sales report by API
      | Report by     | Value                         | Product filter | Title                 | Shop Type | Report type | View product | Add to cart | Reached checkout | Orders | Net quantity | Net sales | Net profit |
      | source_medium | custom_source / custom_medium | product_title  | Sample Testing Part 2 | net_sales | product_utm | +1           | +1          | +1               | +1     | +1           | +10       | 0          |
    Then quit browser

  Scenario: Verify add additional column #SB_ANA_SC_ANA_UTM_14
    Given clear all data
    And user navigate to "Analytics>Sales Reports" screen
    And choose report by "Source / Medium" on dashboard sales report
    Then verify when add a "UTM Campaign" column on dashboard sales report
    Then quit browser

  Scenario: Verify refund order for UTM sales report #SB_ANA_SC_ANA_UTM_23
    Given clear all data
    Then get data UTM sales report init by API
      | Report by     | Value                         | Product filter | Title                 | Shop Type | Report type |
      | source_medium | custom_source / custom_medium | product_title  | Sample Testing Part 2 | net_sales | product_utm |
    And user navigate to "Orders" screen
    Then cancel first order in Order dashboard
    And wait 50000 second
    And wait 50000 second
    Then verify data UTM sales report by API
      | Report by     | Value                         | Product filter | Title                 | Shop Type | Report type | View product | Add to cart | Reached checkout | Orders | Net quantity | Net sales | Net profit |
      | source_medium | custom_source / custom_medium | product_title  | Sample Testing Part 2 | net_sales | product_utm | 0            | 0           | 0                | 0      | 0            | -10       | 0          |
    Then quit browser