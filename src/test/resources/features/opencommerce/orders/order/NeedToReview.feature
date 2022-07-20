Feature: Need to review
  #env = sbase_fulfillment_service

  Scenario: Verify display data when search orders
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Shoes:S>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Search and verify fulfillment status = "Unfulfilled" on order list
    And click on order fulfill with
    And Fulfill with PlusHub
    And Search and verify display info tab
      | order       | product | country       | error | action |
      | OrderNumber | Shoes   | United States |       |        |
    And close browser


  Scenario: Verify display error edit address
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Shoes:M,White>5"
      | Email                   | First Name | Last Name | Address           | Country | City         | Zip code | State | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Vietnam | Santa Monica | 90401    |       | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Search and verify fulfillment status = "Unfulfilled" on order list
    And click on order fulfill with
    And Fulfill with PlusHub
    And Search and verify display info tab
      | order       | product | country | error                    | action       |
      | OrderNumber | Shoes   | Vietnam | Country is not supported | Edit address |
    And Edit address order
      | Address           | Country       | City         | State      | ZIP/Postal Code |
      | 1845  Johnny Lane | United States | Santa Monica | Washington | 90001           |
    And Search and verify display info tab
      | order       | product | country       | error | action |
      | OrderNumber | Shoes   | United States |       |        |
    And close browser


  Scenario: Verify display error need mapping product
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Shoes:L>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Search and verify fulfillment status = "Unfulfilled" on order list
    And click on order fulfill with
    And Fulfill with PlusHub
    And Search and verify display info tab
      | order       | product | country       | error                | action      |
      | OrderNumber | Shoes   | United States | Need product mapping | Map product |
    And Mapping product with info
      | Product Name odoo                                | Variant>quantity   | Product Name sbase | VariantSB>quantitySB |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>L;Color>Black | Shoes              | Size>L;Color>White   |
    And user navigate to "Fulfillment" screen
    And move to "Dropship" screen
    And move to "Warehouse" screen
    And Acc page "Inventory"
    And Choose product odoo mapping has info
      | Name Product oDoo                                | Attribute>Value    |
      | CNYISHE Sexy Streetwear Halter Bandage Two Piece | Size>L;Color>Black |
    And Select store mapping
    And Removed product mapping
    And close browser















