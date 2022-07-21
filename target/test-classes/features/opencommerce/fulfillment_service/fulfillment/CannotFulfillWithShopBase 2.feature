Feature: Cannot fulfill
  #env = cannot_fulfill_sbff

  Scenario: Verify order cannot fulfill because Country is not supported
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And Acc to page "PlusHub"
    And move to "Cannot Fulfill" tab in fulfillment page and get count
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "prod_cannot_fulfill:S>3"
      | Email                   | First Name | Last Name | Address           | Country | City         | Zip code | State | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Vietnam | Santa Monica | 123      |       | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Given redirect to shopbase dashboard
    When user navigate to "Orders" screen
    And move to "Cannot Fulfill" tab in fulfillment page and verify count
    And Search by order and verify info order
      | ORDER   | LINE ITEM               | WAREHOUSE ITEM     | QUANTITY | BASE COST PER ITEM | ERROR                    |
      | @Order@ | prod_cannot_fulfill - S | CNYISHE Elegant... | 3        |                    | Country is not supported |
    And Click button "Edit address"
    And Edit address order
      | Address           | Country       | City         | State      | ZIP/Postal Code |
      | 1845  Johnny Lane | United States | Santa Monica | California | 90001           |
    And move to "To fulfill" tab in fulfillment page and get count
    And Search by order and verify info order
      | ORDER   | PRODUCT                 | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | prod_cannot_fulfill - S | CNYISHE Elegant... | 3        | $1.00              |       |
    And close browser

  Scenario: Verify order cannot fulfill because State is not supported
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And Acc to page "PlusHub"
    And move to "Cannot Fulfill" tab in fulfillment page and get count
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "prod_cannot_fulfill:S>3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90001    | Washington | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Given redirect to shopbase dashboard
    When user navigate to "Orders" screen
    And move to "Cannot Fulfill" tab in fulfillment page and verify count
    And Search by order and verify info order
      | ORDER   | LINE ITEM               | WAREHOUSE ITEM     | QUANTITY | BASE COST PER ITEM | ERROR                  |
      | @Order@ | prod_cannot_fulfill - S | CNYISHE Elegant... | 3        |                    | State is not supported |
    And Click button "Edit address"
    And Edit address order
      | Address           | Country       | City         | State      | ZIP/Postal Code |
      | 1845  Johnny Lane | United States | Santa Monica | California | 90001           |
    And move to "To fulfill" tab in fulfillment page and get count
    And Search by order and verify info order
      | ORDER   | PRODUCT                 | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | prod_cannot_fulfill - S | CNYISHE Elegant... | 3        | $1.00              |       |
    And close browser

  Scenario: Verify order cannot fulfill because Invalid shipping address
    Given user login to shopbase dashboard
    Then user navigate to "Orders" screen
    And Acc to page "PlusHub"
    And move to "Cannot Fulfill" tab in fulfillment page and get count
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "prod_cannot_fulfill:S>3"
      | Email                   | First Name | Last Name | Address              | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 143 Tran Phu Ha Dong | United States | Santa Monica | 90001    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Given redirect to shopbase dashboard
    When user navigate to "Orders" screen
    And move to "Cannot Fulfill" tab in fulfillment page and verify count
    And Search by order and verify info order
      | ORDER   | LINE ITEM               | WAREHOUSE ITEM     | QUANTITY | BASE COST PER ITEM | ERROR                    |
      | @Order@ | prod_cannot_fulfill - S | CNYISHE Elegant... | 3        |                    | Invalid shipping address |
    And Click button "Edit address"
    And Edit address order
      | Address           | Country       | City         | State      | ZIP/Postal Code |
      | 1845  Johnny Lane | United States | Santa Monica | California | 90001           |
    And move to "To fulfill" tab in fulfillment page and get count
    And Search by order and verify info order
      | ORDER   | PRODUCT                 | WAREHOUSE PRODUCT  | QUANTITY | BASE COST PER ITEM | ERROR |
      | @Order@ | prod_cannot_fulfill - S | CNYISHE Elegant... | 3        | $1.00              |       |
    And close browser