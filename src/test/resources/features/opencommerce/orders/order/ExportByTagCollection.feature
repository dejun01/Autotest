Feature: Add tags to line items
#env = export_tag_collection

  Scenario: Export product by collection
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Test product) Zariamiky Turtleneck Long Sleeve Bodycon Dress"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone       | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given staff login to shopbase dashboard
    Then user navigate to "Orders" screen
    And enter filter tag and collection
      | Tag | Collection   | Option       |
      |     | Collection 1 | Current page |
    And staff login to email and open email with subject contains shopbase
    And verify that send mail to merchant with file name "order_export_yyyyMMdd"
    And close browser


  Scenario: Export product by tag
    Given clear all data
    Given staff login to shopbase dashboard
    Then user navigate to "Orders" screen
    And enter filter tag and collection
      | Tag      | Collection | Option       |
      | product1 |            | Current page |
    And staff login to email and open email with subject contains shopbase
    And verify that send mail to merchant with file name "order_export_yyyyMMdd"
    And close browser

  Scenario: Export product by tag and collection
    Given staff login to shopbase dashboard
    Then user navigate to "Orders" screen
    And enter filter tag and collection
      | Tag      | Collection   | Option       |
      | product1 | Collection 1 | Current page |
    And staff login to email and open email with subject contains shopbase
    And verify that send mail to merchant with file name "order_export_yyyyMMdd"
    And close browser

  Scenario: Export product by tag multi store
    Given staff login to shopbase dashboard
    Then user navigate to "Orders" screen
    And enter filter tag and collection
      | Tag      | Collection | Option       | Multi store                         |
      | product1 |            | Current page | au-demo-fulfill.stag.myshopbase.net |
    And staff login to email and open email with subject contains shopbase
    And verify that send mail to merchant with file name "order_export_yyyyMMdd"
    And close browser


