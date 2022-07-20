Feature: Replace product in Fulfillment
#sbase_fulfillment

  Background: Access shop chechout
    Given open shop on storefront

  Scenario: 1. replace variant in a product of order SB_RLSBFF_RLSBFF-Warehouse_11
    Then checkout of order fulfillment successfully via stripe with cart "Thin Strap Bralette Sexy"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory before replace with
      | Product                  | Variant          | Variant replace  |
      | Thin Strap Bralette Sexy | White / One Size | Black / One Size |
    And user navigate to "fulfillment/dropship/list" screen by url
    And search first order name
    And replace the order in list order with
      | Product                  | Variant                   | Variant replace           | Replace all |
      | Thin Strap Bralette Sexy | Color:White,Size:One Size | Color:Black,Size:One Size | false       |
    And search first order name
    And verify replace the order in list order with
      | Product            | Variant          |
      | Thin Strap Bral... | Black / One Size |
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value inventory after replace with
      | Product                  | Variant          | Variant replace  | Quantity |
      | Thin Strap Bralette Sexy | White / One Size | Black / One Size | 1        |
    And user navigate to "fulfillment/dropship/list" screen by url
    And search first order name
    And fulfill order replace
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value inventory after fulfill with
      | Product                  | Variant          | Variant replace  | Quantity |
      | Thin Strap Bralette Sexy | White / One Size | Black / One Size | 1        |
    And get value inventory of product before fulfill
      | Product                  |
      | Thin Strap Bralette Sexy |
    And validate DO on Odoo
      | Order name   | Owner |
      | @ordernumber |       |
    And Verify status of Delivery Order on Odoo
      | Order name   | Owner | Status |
      | @ordernumber |       | done   |
    And get and verify value in inventory after fulfill
      | Product                  | Quantity | Status    |
      | Thin Strap Bralette Sexy | 1        | Fulfilled |
    And quit browser

  Scenario: 2. replace product of order
    Then checkout of order fulfillment successfully via stripe with cart "Thin Strap Bralette Sexy>2"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory before replace with
      | Product                  | Product replace         |
      | Thin Strap Bralette Sexy | 12V 150PSI Rechargeable |
    And user navigate to "fulfillment/dropship/list" screen by url
    And search first order name
    And replace the order in list order with
      | Product                  | Variant                   | Variant replace                                 | Product replace         |
      | Thin Strap Bralette Sexy | Color:White,Size:One Size | Ships From:Russian Federation,Color Name:SIlver | 12V 150PSI Rechargeable |
    And search first order name
    And verify replace the order in list order with
      | Product            | Variant                     |
      | 12V 150PSI Rech... | Russian Federation / SIlver |
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value inventory after replace with
      | Product                  | Product replace         | Quantity |
      | Thin Strap Bralette Sexy | 12V 150PSI Rechargeable | 2        |
    And get value inventory of product before fulfill
      | Product                 |
      | 12V 150PSI Rechargeable |
    And user navigate to "fulfillment/dropship/list" screen by url
    And search first order name
    And fulfill order replace
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And validate DO on Odoo
      | Order name   | Owner |
      | @ordernumber |       |
    And Verify status of Delivery Order on Odoo
      | Order name   | Owner | Status |
      | @ordernumber |       | done   |
    And get and verify value in inventory after fulfill
      | Product                 | Quantity | Status    |
      | 12V 150PSI Rechargeable | 2        | Fulfilled |
    And quit browser

  Scenario: 3. replace variant in product of all order SB_RLSBFF_RLSBFF-Warehouse_12
    Then checkout of order fulfillment successfully via stripe with cart "Sinbeauty Summer Women"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Then checkout of order fulfillment successfully via stripe with cart "Sinbeauty Summer Women"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory before replace with
      | Product                | Variant                    | Variant replace            |
      | Sinbeauty Summer Women | Watermelon red / S / China | Watermelon red / M / China |
    When user navigate to "fulfillment/dropship/list" screen by url
    And search first order name
    And replace the order in list order with
      | Product                | Variant                                      | Variant replace                              | Replace all |
      | Sinbeauty Summer Women | Color:Watermelon red,Size:S,Ships From:China | Color:Watermelon red,Size:M,Ships From:China | true        |
    And search first order name
    And verify replace the order in list order with
      | Product            | Variant                    |
      | Sinbeauty Summe... | Watermelon red / M / China |
    And search second order name
    And verify replace the order in list order with
      | Product            | Variant                    |
      | Sinbeauty Summe... | Watermelon red / M / China |
    When user navigate to "Warehouse" screen
    And get and verify value inventory after replace with
      | Product                | Variant                    | Variant replace            | Quantity |
      | Sinbeauty Summer Women | Watermelon red / S / China | Watermelon red / M / China | 2        |
    And quit browser

  Scenario: 4. replace product of all order
    Then checkout of order fulfillment successfully via stripe with cart "Yitimoky Cardigan Women"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Then checkout of order fulfillment successfully via stripe with cart "Yitimoky Cardigan Women"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type | Discount |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |          |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/Warehouse" screen by url
    And get value inventory before replace with
      | Product                 | Product replace         |
      | Yitimoky Cardigan Women | 12V 150PSI Rechargeable |
    When user navigate to "fulfillment/dropship/list" screen by url
    And search first order name
    And replace the order in list order with
      | Product                 | Variant                  | Product replace         | Variant replace                                 | Replace all |
      | Yitimoky Cardigan Women | Color:Blue,Size:One Size | 12V 150PSI Rechargeable | Ships From:Russian Federation,Color Name:SIlver | true        |
    And search first order name
    And verify replace the order in list order with
      | Product            | Variant                     |
      | 12V 150PSI Rech... | Russian Federation / SIlver |
    And search second order name
    And verify replace the order in list order with
      | Product            | Variant                     |
      | 12V 150PSI Rech... | Russian Federation / SIlver |
    When user navigate to "Warehouse" screen
    And get and verify value inventory after replace with
      | Product                 | Product replace         | Quantity |
      | Yitimoky Cardigan Women | 12V 150PSI Rechargeable | 2        |
    And close browser