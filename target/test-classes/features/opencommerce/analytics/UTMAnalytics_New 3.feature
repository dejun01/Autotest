@Analytics
Feature: Verify UTM Analytics
#  sbase_utm_analytics

  Scenario: Verify new UTM analytics with direct checkout on SF #SB_ANA_UTM_3
    Then Get data new version UTM analytics by API before
      | Shop type | Report by     | UTM value     |
      | sbase     | source_medium | direct / none |
      | sbase     | utm_campaign  | not_set       |
      | sbase     | utm_content   | not_set       |
    When open product "1805" and one page checkout without verify with "one" user
    And wait 50000 second
    And wait 50000 second
    Then Verify new data UTM analytics by API
      | Shop type | Report by     | UTM value     | View product | Add to cart | Reached checkout | Orders | Net Quantity | Total Sales | Profit |
      | sbase     | source_medium | direct / none | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | utm_campaign  | not_set       | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | utm_content   | not_set       | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
    And quit browser

  Scenario: Verify UTM analyics with custom UTM value #SB_ANA_UTM_1
    Given clear all data
    Then Get data new version UTM analytics by API before
      | Shop type | Report by     | UTM value                     |
      | sbase     | utm_campaign  | custom_camp                   |
      | sbase     | source_medium | custom_source / custom_medium |
      | sbase     | utm_content   | custom_content                |
      | sbase     | utm_term      | custom_term                   |
    Given open product with UTM and checkout
      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | referrer |
      | 1805         | 1        | custom_source | custom_medium | custom_camp  | custom_content | custom_term |          |
    And checkout by Stripe successfully on one page with "other" user
    And wait 50000 second
    And wait 50000 second
    Then Verify new data UTM analytics by API
      | Shop type | Report by     | UTM value                     | View product | Add to cart | Reached checkout | Orders | Net Quantity | Total Sales | Profit |
      | sbase     | utm_campaign  | custom_camp                   | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | source_medium | custom_source / custom_medium | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | utm_content   | custom_content                | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | utm_term      | custom_term                   | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
    And quit browser

  Scenario: Verify UTM analyics with custom source UTM value #SB_ANA_UTM_2 #SB_ANA_UTM_5
    Given clear all data
    Then Get data new version UTM analytics by API before
      | Shop type | Report by     | UTM value                     |
      | sbase     | utm_campaign  | not_set                       |
      | sbase     | source_medium | custom_source / custom_medium |
      | sbase     | utm_content   | not_set                       |
      | sbase     | utm_term      | not_set                       |
    Given open product with UTM and checkout
      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content | utm_term | referrer |
      | 1805         | 1        | custom_source | custom_medium |              |             |          |          |
    And checkout by Stripe successfully on one page with "other" user
    And wait 50000 second
    And wait 50000 second
    Then Verify new data UTM analytics by API
      | Shop type | Report by     | UTM value                     | View product | Add to cart | Reached checkout | Orders | Net Quantity | Total Sales | Profit |
      | sbase     | utm_campaign  | not_set                       | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | source_medium | custom_source / custom_medium | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | utm_content   | not_set                       | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
      | sbase     | utm_term      | not_set                       | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
    And quit browser

#  Scenario: Verify abandoned checkout with custom UTM value #SB_ANA_UTM_7
#    Given clear all data
#    Then Get data new version UTM analytics by API before
#      | Shop type | Report by     | UTM value                     |
#      | sbase     | utm_source    | custom_source                 |
#      | sbase     | utm_medium    | custom_medium                 |
#      | sbase     | utm_campaign  | custom_camp                   |
#      | sbase     | source_medium | custom_source / custom_medium |
#    Given open product with UTM and checkout
#      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content | utm_term | referrer |
#      | 1805         | 1        | custom_source | custom_medium | custom_camp  |             |          |          |
#    And input Customer information
#      | Email                      | First Name | Last Name | Address       | City    | Country       | State | Zip code | Phone      |
#      | @mailinator.girl-viet.com@ | Emma       | Watson    | 1600 W Loop S | Houston | United States | Texas | 77027    | 2056289809 |
#    And quit browser
#    Given user login to shop dashboard
#    Then user navigate to "Orders>Abandoned checkouts" screen
#    And search then select abandoned checkout by customer email
#    When open recovery link in a new tab
#    And input card information of Stripe and complete order
#      | Payment method | Card number      | Cardholder name | Expired Date | CVV |
#      | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 |
#    And verify thank you page
#    And switch to the first tab
#    Then Verify new data UTM analytics by API
#      | Shop type | Report by     | UTM value                     | View product | Add to cart | Reached checkout | Orders | Net Quantity | Total Sales | Profit |
#      | sbase     | utm_source    | custom_source                 | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
#      | sbase     | utm_medium    | custom_medium                 | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
#      | sbase     | utm_campaign  | custom_camp                   | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
#      | sbase     | source_medium | custom_source / custom_medium | +1           | +1          | +1               | +1     | +1           | +40         | 0      |
#    And quit browser

  Scenario: Cancel order UTM #SB_ANA_UTM_6
    Given clear all data
    Then Get data new version UTM analytics by API before
      | Shop type | Report by    | UTM value     |
      | sbase     | utm_source   | custom_source |
      | sbase     | utm_medium   | custom_medium |
    Given user login to shop dashboard
    And user navigate to "Orders" screen
    Then cancel first order in Order dashboard
    And wait 50000 second
    And wait 50000 second
    Then Verify new data UTM analytics by API
      | Shop type | Report by    | UTM value     | View product | Add to cart | Reached checkout | Orders | Net Quantity | Total Sales | Profit |
      | sbase     | utm_source   | custom_source | 0            | 0           | 0                | 0      | 0            | -40         | 0      |
      | sbase     | utm_medium   | custom_medium | 0            | 0           | 0                | 0      | 0            | -40         | 0      |

