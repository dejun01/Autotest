Feature: Add tags to line items
#env = sb_add_tag

  Scenario: Add tag to line item in order detail #SB_RLSBFF_ATLI_11 #SB_RLSBFF_ATLI_10 #SB_RLSBFF_ATLI_9 #SB_RLSBFF_ATLI_12
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "(Auto)Summer Backless Dress"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone       | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Add tag and verify to line items in order detail
      | Text input tag line item |
      | Demo                     |
    And user navigate to "fulfillment/dropship/list" screen by url
    And Search and verify tag line item product in fulfillment
      | Text input tag line item | Status filter |
      | Demo                     | Contains      |
    And Verify line item tags doesn't contain in fulfillment
      | Status filter   | Messenger |
      | Doesn't contain | No order  |
    And close browser

  Scenario: Add tag to line item in fulfillment  #SB_RLSBFF_ATLI_11 #SB_RLSBFF_ATLI_10 #SB_RLSBFF_ATLI_9 #SB_RLSBFF_ATLI_12
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "URBAN GIRL T-shirt"
      | Email                   | First Name | Last Name | Address                        | Country       | City        | Zip code | State      | Phone       | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane Milwaukee 123 | United States | Los Angeles | 90001    | California | 84389956985 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboardsbase_dashboard
    Then user navigate to "fulfillment/dropship/list" screen by url
    And Search order in fulfillment order
    And Verify status order
      | Status       | Lable filter |
      | Need mapping | Order Date:  |
    And Add tag to line item order have status need mapping
      | Text input tag line item |
      | Tag in warehouse         |
    And Search and verify tag line item product in fulfillment
      | Text input tag line item | Status filter |
      | Tag in warehouse         | Contains      |
    And Verify line item tags doesn't contain in fulfillment
      | Status filter   | Messenger                 |
      | Doesn't contain | Could not find any orders |
    Then user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Verify tag line item in order detail
    And close browser





