@Analytics
Feature: UTM Analytics for pbase
#  pbase_utm_analytics

  Scenario: Verify UTM analyics pbase with custom UTM value
    Given clear all data
    Then Get data new version UTM analytics by API before
      | Shop type | Report by     | UTM value                     |
      | pbase     | utm_campaign  | custom_camp                   |
      | pbase     | source_medium | custom_source / custom_medium |
      | pbase     | utm_content   | custom_content                |
      | pbase     | utm_term      | custom_term                   |
    Given open product with UTM and checkout
      | Product name | Quantity | utm_source    | utm_medium    | utm_campaign | utm_content    | utm_term    | referrer |
      | mug0911      | 1        | custom_source | custom_medium | custom_camp  | custom_content | custom_term |          |
    And checkout by Stripe successfully on one page with "other" user
    And wait 50000 second
    And wait 50000 second
    And wait 25000 second
    Then Verify new data UTM analytics by API
      | Shop type | Report by     | UTM value                     | View product | Add to cart | Reached checkout | Orders | Net Quantity | Total Sales | Profit |
      | pbase     | utm_campaign  | custom_camp                   | +1           | +1          | +1               | +1     | +1           | +0          | +14.13 |
      | pbase     | source_medium | custom_source / custom_medium | +1           | +1          | +1               | +1     | +1           | +0          | +14.13 |
      | pbase     | utm_content   | custom_content                | +1           | +1          | +1               | +1     | +1           | +0          | +14.13 |
      | pbase     | utm_term      | custom_term                   | +1           | +1          | +1               | +1     | +1           | +0          | +14.13 |
    And quit browser