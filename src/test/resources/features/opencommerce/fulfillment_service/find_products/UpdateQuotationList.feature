#sbase_update_quotation_list
Feature: Update Quotation List

  Scenario: Created submitted request
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/request-product" screen by url
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002762748144.html |
    And verify quotation
      | Source url                                            | Status quotation  | Tab               |
      | https://www.aliexpress.com/item/1005002762748144.html | Processing        | Submitted request |
    And quit browser

  Scenario: Cancel submitted request
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/request-product" screen by url
    Then user input link and request quotation
      | Link                                                  |
      | https://www.aliexpress.com/item/1005002762687573.html |
    And cancel quotation after request
    And verify quotation
      | Source url                                            | Status quotation | Tab       |
      | https://www.aliexpress.com/item/1005002762687573.html | Canceled         | No result |
    And quit browser




