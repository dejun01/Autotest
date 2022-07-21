Feature: Update UI display line items and status mapping
  #env =

  Scenario: Verify search order by line item name
    Given open shop on storefront
    Then checkout successfully via stripe with cart ""
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search order by line item name and verify
      | ProductName | Expect  |
      | @Product1   | @Order1 |
    And close browser

  Scenario: Verify display info order when expand order not mapping
    Given open shop on storefront
    Then checkout successfully via stripe with cart "productNotMapp"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status = "Paid" and fulfillment status = "Unfulfilled" on order list plusbase
    And Expand order
    And Verify info order line items
      | ProductNameSB | VariantSB | SKUSB | QuantitySB | ProductNameMapping | VariantMapping | SKUMapping | QuantityMapping | FulfillmentStatus | TrackingNumber | DisplayButton |
      |               |           |       |            |                    |                |            |                 |                   |                | Map product   |
      |               |           |       |            |                    |                |            |                 |                   |                | Map product   |
    And close browser

  Scenario: Verify display info order after mapping
    Given open shop on storefront
    Then checkout successfully via stripe with cart "producMapped"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status = "Paid" and fulfillment status = "Unfulfilled" on order list plusbase
    And Expand order
    And Verify info order line items
      | ProductNameSB | VariantSB | SKUSB | QuantitySB | ProductNameMapping | VariantMapping | SKUMapping | QuantityMapping | FulfillmentStatus | TrackingNumber | DisplayButton                        |
      |               |           |       |            |                    |                |            |                 |                   |                | Chage mapping,Replace inventory item |
    And close browser


  Scenario: Verify display info order after change mapping
    Given open shop on storefront
    Then checkout successfully via stripe with cart "producMapped"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status = "Paid" and fulfillment status = "Unfulfilled" on order list plusbase
    And Expand order
    And Change mapping product
      | ProductNameMapping | Variant>quantity | Product Name sbase | VariantSB>quantitySB |
      |                    |                  |                    |                      |
    And Verify info order line items
      | ProductNameSB | VariantSB | SKUSB | QuantitySB | ProductNameMapping | VariantMapping | SKUMapping | QuantityMapping | FulfillmentStatus | TrackingNumber | DisplayButton                        |
      |               |           |       |            |                    |                |            |                 |                   |                | Chage mapping,Replace inventory item |
    And close browser

  Scenario: Verify display info order after replay inventory items if choose "Apply this change to all"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "2product"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And Expand order
    And Replace product for lineitem
      | ProductName | Variant>Quantity | Apply this change to all |
      |             |                  | true                     |
    And Verify info order line items
      | ProductNameSB | VariantSB | SKUSB | QuantitySB | ProductNameMapping | VariantMapping | SKUMapping | QuantityMapping | FulfillmentStatus | TrackingNumber | DisplayButton                        |
      |               |           |       |            |                    |                |            |                 |                   |                | Chage mapping,Replace inventory item |
      |               |           |       |            |                    |                |            |                 |                   |                | Chage mapping,Replace inventory item |
    And close browser

  Scenario: Verify display info order after replay inventory items if not choose "Apply this change to all"
    Given open shop on storefront
    Then checkout successfully via stripe with cart "2product"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    Then Search and verify payment status = "Paid" and fulfillment status = "Unfulfilled" on order list plusbase
    And Expand order
    And Replace product for lineitem
      | ProductName | Variant | Apply this change to all |
      |             |         | false                    |
    And Verify info order line items
      | ProductNameSB | VariantSB | SKUSB | QuantitySB | ProductNameMapping | VariantMapping | SKUMapping | QuantityMapping | FulfillmentStatus | TrackingNumber | DisplayButton                        |
      |               |           |       |            |                    |                |            |                 |                   |                | Chage mapping,Replace inventory item |
      |               |           |       |            |                    |                |            |                 |                   |                | Chage mapping,Replace inventory item |
    And close browser

  Scenario: Verify hold a order success and approve order in status on hold
    Given open plusbase on storefront
    Then checkout successfully via stripe with cart ""
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Hold" order
    Then Search and verify payment status = "Authorized" and Approve status = "on_hold" on order list
    And close browser

  Scenario: Verify hold bulk order success
    Given open plusbase on storefront
    Then checkout successfully via stripe with cart ""
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given open plusbase on storefront
    Then checkout successfully via stripe with cart "Casio watch diving watch men>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    And Choose multi order actions
    And "Hold" order
    Then Search multi orders and verify fulfillment status = "Authorized" and Approve status = "on_hold" on order list
    And close browser




