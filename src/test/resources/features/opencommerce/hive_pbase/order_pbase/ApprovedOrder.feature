Feature: Approved order
#env = sbase_approved_order


  Scenario: Verify approved one order #SB_OPB_11
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T_1:L>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify fulfillment status = "Authorized" on order pbase list
    Given login to hive-pbase
    When user navigate "Customer Support" screen
    And Click to "PBase Order"
  #    Verify status of order before approved
    And Search order created and verify info order
      | condition  | Fulfillment Status | Payment Status | Approved At |
      | Order Name | UNFULFILLED        | authorized     |             |
#    Verify status of order after approved
    And Approved order in hive and verify info order in order detail
    And Click to "PBase Order"
    And Search order created and verify info order
      | condition  | Payment Status | Fulfillment Status | Approved At |
      | Order Name | paid           | PROCESSING         | date        |
    And  close browser

  Scenario: Verify approved multi order
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T_1:L>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given open shop on storefront
    Then checkout successfully via stripe with cart "T_1:M>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify fulfillment status = "Authorized" on order multi pbase list
    Given login to hive-pbase
    When user navigate "Customer Support" screen
    And Click to "PBase Order"
#    Verify status of order before approved
    And Search order created and verify info order
      | condition | Fulfillment Status | Payment Status | Approved At |
      | Domain    | UNFULFILLED        | authorized     |             |
    And Choose order approved
    And Approved multi order
    And Confirm approved
#    Verify status of order after approved
    And Click to "PBase Order"
    And Search order created and verify info order
      | condition | Payment Status | Fulfillment Status | Approved At |
      | Domain    | paid           | PROCESSING         | date        |
    And close browser

  Scenario: Verify display error if order after approved
    Given login to hive-pbase
    When user navigate "Customer Support" screen
    And Click to "PBase Order"
    And Approved multi order
    Then Display error "Action aborted. No items were selected."
    And close browser







