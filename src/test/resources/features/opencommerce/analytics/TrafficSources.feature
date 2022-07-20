Feature: Verify UTM Traffic Sources
  #sbase_traffic_source

  Scenario: Verify UTM analytics report by Channel
    Given clear all data
    Given user login to shopbase dashboard
    When user navigate to "Analytics>Traffic Sources" screen
    And get data visitors before
    When open product with UTM and checkout
      | Product name       | referrer                                                              | utm_source    | utm_medium    | utm_campaign | utm_term    | utm_content    | Quantity |
      | velvet-party-dress | &fbclid=IwAR3CEJWCmisqGWajKBML_10cRREQDHKWavNH7obKROrs6GSYcDE5FfCCF9U | custom_source | custom_medium | custom_camp  | custom_term | custom_content | 1        |
    And checkout by Stripe successfully on one page with "other" user
    And quit browser
    Given user login to shopbase dashboard
    When user navigate to "Analytics>Traffic Sources" screen
    And wait 50000 second
    And wait 50000 second
    Then verify UTM Channel value
      | Time  | Campaign name | Referrer name | Unit visitors |
      | Today | social        | Facebook      | 1             |

  Scenario: Verify UTM analytics report by Source / Medium
    Given clear all data
    Given user login to shopbase dashboard
    When user navigate to "Analytics>Traffic Sources" screen
    And  choice option report by "Source / Medium"
    And get data "Source / Medium" before
      | Value                          | Add Column |
      | custom_source / custom_medium1 |            |
    When open product with UTM and checkout
      | Product name       | referrer                                                              | utm_source    | utm_medium     | utm_campaign | utm_term     | utm_content    | Quantity |
      | velvet-party-dress | &fbclid=IwAR3CEJWCmisqGWajKBML_10cRREQDHKWavNH7obKROrs6GSYcDE5FfCCF9U | custom_source | custom_medium1 | custom_camp  | custom_term1 | custom_content | 1        |
    And checkout by Stripe successfully on one page with "other" user
    And quit browser
    Given user login to shopbase dashboard
    When user navigate to "Analytics>Traffic Sources" screen
    And  choice option report by "Source / Medium"
    And wait 50000 second
    And wait 50000 second
    And verify UTM value by "Source / Medium"
      | Filter by                 | Campaign Option                | Add Column | View product | Add to card | Reach checkOut | Orders | Conversion rate  | Net quantity | AOV   | AOI   | Total sales |
      | Source / Medium  and Term | custom_source / custom_medium1 |            | 1            | 1           | 1              | 1      | @conversionrate@ | 1            | @aov@ | @aoi@ | 57.00       |


  Scenario: Verify UTM analytics report by Source / Medium  and Add Column
    Given clear all data
    Given user login to shopbase dashboard
    When user navigate to "Analytics>Traffic Sources" screen
    And  choice option report by "Source / Medium"
    When add column "UTM Term"
    And get data "Source / Medium and Term" before
      | Value                         | Add Column   |
      | custom_source / custom_medium | custom_term2 |
      | custom_source / custom_medium | custom_term3 |
    When open product with UTM and checkout
      | Product name       | referrer                                                              | utm_source    | utm_medium    | utm_campaign | utm_term     | utm_content    | Quantity |
      | velvet-party-dress | &fbclid=IwAR3CEJWCmisqGWajKBML_10cRREQDHKWavNH7obKROrs6GSYcDE5FfCCF9U | custom_source | custom_medium | custom_camp  | custom_term2 | custom_content | 1        |
      | velvet-party-dress | &fbclid=IwAR3CEJWCmisqGWajKBML_10cRREQDHKWavNH7obKROrs6GSYcDE5FfCCF9U | custom_source | custom_medium | custom_camp  | custom_term3 | custom_content | 1        |
    And checkout by Stripe successfully on one page with "other" user
#    And quit browser
#    Given user login to shopbase dashboard
    Given user open dashboard
    When user navigate to "Analytics>Traffic Sources" screen
    And  choice option report by "Source / Medium"
    When add column "UTM Term"
    And wait 55000 second
    And wait 55000 second
    And verify UTM value by "Source / Medium"
      | Filter by                 | Campaign Option               | Add Column   | View product | Add to card | Reach checkOut | Orders | Conversion rate  | Net quantity | AOV   | AOI   | Total sales |
      | Source / Medium  and Term | custom_source / custom_medium | custom_term2 | 1            | 1           | 0              | 0      | @conversionrate@ | 0            | @aov@ | @aoi@ | 0.00        |
      | Source / Medium  and Term | custom_source / custom_medium | custom_term3 | 1            | 1           | 1              | 1      | @conversionrate@ | 2            | @aov@ | @aoi@ | 107.00      |
