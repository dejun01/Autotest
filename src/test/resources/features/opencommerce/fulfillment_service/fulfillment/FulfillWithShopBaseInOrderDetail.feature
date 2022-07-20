Feature: fulfill order
  #env =sbase_fulfillment_service

  Scenario: Verify change status from unfulfill to awaiting stock
    Given open shop on storefront
    Then checkout successfully via stripe with cart "OUT_STOCK:M>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And close browser
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Verify product ready to fulfill
      | Tab              | product     | shipping method               | shipping fee | estimated shipping time |
      | Ready to fulfill | OUT_STOCK:M | Yun Express Standard shipping | $2.86        | 8-14 business days      |
    And Fulfill order
    Then Verify order detail after in fulfill
      | Tab                       | product     |
      | Awaiting stock by PlusHub | OUT_STOCK:M |
    And close browser


  Scenario: Verify change status from unfulfill to processing
    Given open shop on storefront
    Then checkout successfully via stripe with cart "IN_STOCK:S>3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    And close browser
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Verify product ready to fulfill
      | Tab              | product    | shipping method               | shipping fee | estimated shipping time |
      | Ready to fulfill | IN_STOCK:S | Yun Express Standard shipping | $2.86        | 8-14 business days      |
    And Fulfill order
    Then Verify order detail after in fulfill
      | Tab                   | product    |
      | Processing by PlusHub | IN_STOCK:S |
    And close browser

  Scenario: Verify change status from unfulfill to fulfilled
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Fulfilled:S>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Verify product ready to fulfill
      | Tab              | product     | shipping method               | shipping fee | estimated shipping time |
      | Ready to fulfill | Fulfilled:S | Yun Express Standard shipping | $2.86        | 8-14 business days      |
    And Fulfill order
    And Verify status of Delivery Order on Odoo
      | Order name | Owner  | Status   |
      | @ordername | @Owner | assigned |
    And validate DO on Odoo
      | Order name | Owner  |
      | @ordername | @Owner |
    Given redirect to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then Verify order detail after in fulfill
      | Tab                  | product     |
      | Fulfilled by PlusHub | Fulfilled:S |
    And close browser


  Scenario: Verify status when cancel fulfilled line items
    Given open shop on storefront
    Then checkout successfully via stripe with cart "IN_STOCK:S>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And Verify product ready to fulfill
      | Tab              | product    | shipping method               | shipping fee | estimated shipping time |
      | Ready to fulfill | IN_STOCK:S | Yun Express Standard shipping | $2.86        | 8-14 business days      |
    And Fulfill order
    Then Verify order detail after in fulfill
      | Tab                   | product    |
      | Processing by PlusHub | IN_STOCK:S |
    And Cancel fulfill line items
    Then Verify order detail after in fulfill
      | Tab         | product    |
      | Unfulfilled | IN_STOCK:S |
    And close browser


  Scenario: Verify order detail with very line items other status
    Given open shop on storefront
    Then checkout successfully via stripe with cart "IN_STOCK:M>2;Product unmap:S"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And Fulfill with PlusHub
    And verify data order in Fulfill with PlusHub page then Fulfill order
      | Status Fulfill      | Product name>Variant | Base cost>Quantity   | Shipping method               | Shipping fee | Estimated shipping time | Fulfill order |
      | Ready to fulfill    | IN_STOCK>M           | Base cost: $6.00 x 2 | Yun Express Standard shipping | $2.86        | 8-14 business days      | false         |
      | Need to map product | Product unmap>S      |                      |                               |              |                         | true          |
    Then Verify order detail after in fulfill
      | Tab                       | product         |
      | Unfulfilled               | Product unmap:S |
      | Awaiting stock by PlusHub | IN_STOCK:M      |
    And close browser
