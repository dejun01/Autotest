Feature: Fulfill with PlusHub
#sbase_fulfillment

  Scenario: TC_1 fulfill order has product variant available stock > quantity #SB_RLSBFF_RLSBFF-Warehouse_9 SB_RLSBFF_RLSBFF-Warehouse_9 SB_RLSBFF_RLSBFF-Warehouse_4 SB_RLSBFF_RLSBFF-Warehouse_2 SB_RLSBFF_RLSBFF_27 SB_RLSBFF_RLSBFF-Warehouse_15 SB_RLSBFF_RLSBFF_20
    Given open shop on storefront
    Then checkout successfully with cart "Bluetooth Wireless Car Mp3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And get balance before fulfillment
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory of product before fulfill
      | Product                    |
      | Bluetooth Wireless Car Mp3 |
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with for "Pay now"
      | Title popup    | Header content | Detail                                                 | Price            | Flag avialable stock | Flag auto purchase |
      | Make a payment | Summary        | Charge shipping fee PlusHub>Paid for shipping 1 orders | @price shipping@ | true                 | false              |
    And user navigate to "orders" screen by url
    And verify order detail after fulfillemnt
      | Fulfillment status | Group fulfilled       | Product                    | SKU    | Quantity | Unit price | Total   | Button cancel      | Email                   | First name | Last name |
      | Processing         | Processing by PlusHub | Bluetooth Wireless Car Mp3 | PD2021 | 1        | $120.00    | $120.00 | Cancel fulfillment | shopbase@mailtothis.com | Shop       | Base      |
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value in inventory after fulfill
      | Product                    | Quantity | Status     |
      | Bluetooth Wireless Car Mp3 | 1        | Processing |
    And verify balance after fulfillment
    And Verify status of Delivery Order on Odoo
      | Order name   | Customer | Status   |
      | @ordernumber |          | assigned |
    And user navigate to "orders" screen by url
    Then click on order name in unfulfilled orders list
    And cancel order fulfill with shopbase fulfillment
      | Button cancel      | Text confirm                                                                           | Product                    | Description                                                      | Button confirm | Status      | Timeline                                                                            |
      | Cancel fulfillment | Are you sure you want to cancel fulfillment request for the following product in order | Bluetooth Wireless Car Mp3 | You will see this product in Unfulfilled section in order detail | Confirm        | Unfulfilled | This order was unarchived,canceled fulfillment via PlusHub for 1 item. @order name@ |
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value in inventory after cancel fulfill
      | Product                    | Quantity | Status     |
      | Bluetooth Wireless Car Mp3 | 1        | Processing |
    And Verify status of Delivery Order on Odoo
      | Order name   | Customer | Status |
      | @ordernumber |          | cancel |
    And close browser

  Scenario: TC_2 fulfill order has product variant available stock < quantity, incoming = 0, auto purchase = false SB_RLSBFF_RLSBFF-Warehouse_10 SB_RLSBFF_RLSBFF-Warehouse_5 SB_RLSBFF_RLSBFF_19 SB_RLSBFF_RLSBFF_18
    Given open shop on storefront
    Then checkout successfully with cart "Portable Car Air>5"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory of product before fulfill
      | Product          |
      | Portable Car Air |
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with for "Pay now"
      | Title popup | Detail                                                                                                                                                                                  | Flag avialable stock | Flag auto purchase |
      | Review      | 0/1 line items will be fulfilled>1/1 line items can not be fulfilled due to insufficient inventory>You need to purchase the following amount of warehouse item to fulfill 1 items above | false                | false              |
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value in inventory after fulfill
      | Product          | Status      |
      | Portable Car Air | Unfulfilled |
    And verify DO "out" created in odoo with "0" DO
    And close browser

  Scenario: TC_3 fulfill order has product variant available stock < quantity, available stock + incoming >= quantity
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value "INCOMING" in warehouse
      | Product          |
      | Portable Car Air |
    Given open shop on storefront
    Then checkout successfully with cart "China Type Portable Car Air>4"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given redirect to shopbase dashboard
    And get balance before fulfillment
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory of product before fulfill
      | Product          |
      | Portable Car Air |
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with for "Pay now"
      | Title popup    | Header content | Detail                                                 | Price            | Flag avialable stock | Flag auto purchase |
      | Make a payment | Summary        | Charge shipping fee PlusHub>Paid for shipping 1 orders | @price shipping@ | true                 | false              |
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value in inventory after fulfill
      | Product          | Quantity | Status         |
      | Portable Car Air | 4        | Awaiting Stock |
    And user navigate to "orders" screen by url
    And verify order detail after fulfillemnt
      | Fulfillment status | Group fulfilled           | Product          | SKU    | Quantity | Unit price | Total   | Button cancel      | Email                   | First name | Last name |
      | Awaiting Stock     | Awaiting stock by PlusHub | Portable Car Air | PD2021 | 4        | $120.00    | $480.00 | Cancel fulfillment | shopbase@mailtothis.com | Shop       | Base      |
    And verify status DO in odo = "confirmed"
    And cancel order fulfill with shopbase fulfillment
      | Button cancel      | Text confirm                                                                           | Product          | Description                                                      | Button confirm | Status      | Timeline                                                                             |
      | Cancel fulfillment | Are you sure you want to cancel fulfillment request for the following product in order | Portable Car Air | You will see this product in Unfulfilled section in order detail | Confirm        | Unfulfilled | This order was unarchived,canceled fulfillment via PlusHub for 4 items. @order name@ |
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get and verify value in inventory after cancel fulfill
      | Product          | Quantity | Status         |
      | Portable Car Air | 4        | Awaiting Stock |
    And verify status DO in odo = "cancel"
    And close browser

  Scenario: TC_4 done DO OUT , variant incoming + avaible stock > quantity SB_RLSBFF_RLSBFF-Warehouse_13 SB_RLSBFF_RLSBFF_26 SB_ORD_FOD_80
    Given open shop on storefront
    Then checkout successfully with cart "Car Air Compressor Electric"
      | Email                   | First Name | Last Name | Address           | Country | City             | Zip code | State            | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | Canada  | British Columbia | A1A 1A1  | British Columbia | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory of product before fulfill
      | Product                     |
      | Car Air Compressor Electric |
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with for "Pay now"
      | Title popup    | Header content | Detail                                                 | Price            | Flag avialable stock | Flag auto purchase |
      | Make a payment | Summary        | Charge shipping fee PlusHub>Paid for shipping 1 orders | @price shipping@ | true                 | false              |
    When user navigate to "Warehouse" screen
    And get and verify value in inventory after fulfill
      | Product                     | Quantity | Status     |
      | Car Air Compressor Electric | 1        | Processing |
    And user navigate to "orders" screen by url
    And verify order detail after fulfillemnt
      | Fulfillment status | Group fulfilled       | Product                     | SKU    | Quantity | Unit price | Total   | Button cancel      | Email                   | First name | Last name |
      | Processing         | Processing by PlusHub | Car Air Compressor Electric | PD2021 | 1        | $120.00    | $120.00 | Cancel fulfillment | shopbase@mailtothis.com | Shop       | Base      |
    And validate DO on Odoo
      | Order name   | Owner |
      | @ordernumber |       |
    And Verify status of Delivery Order on Odoo
      | Order name   | Owner | Status |
      | @ordernumber |       | done   |
    Given user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    Then verify order detail ShopBase after fulfill via PlusHub
      | Status group         | Count group | Lineitem                                  | Quantity lineitem | Cancel fulfillment | Add tracking | Fulfill with | Mark as fulfilled | Track shipment | Edit tracking |
      | Fulfilled by PlusHub | 1           | Car Air Compressor Electric>Default Title | 1                 | false              | false        | false        | false             | true           | true          |
    And close browser

  Scenario: TC_5 verify data in Fulfilled, Processing, Awaiting stock, Cannot fulfill, Need mapping and To fulfill tab SB_RLSBFF_RLSBFF_8 SB_RLSBFF_RLSBFF_9 SB_RLSBFF_RLSBFF_10 SB_RLSBFF_RLSBFF_12 SB_RLSBFF_RLSBFF_13 SB_RLSBFF_RLSBFF_14 SB_RLSBFF_RLSBFF_16 SB_RLSBFF_RLSBFF_15 SB_RLSBFF_RLSBFF_11 SB_RLSBFF_RLSBFF_1 SB_RLSBFF_RLSBFF_17
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/list" screen by url
#    And verify info of "Fulfilled" tab with order
    And verify info of "Processing" tab with order
    And verify info of "Awaiting stock" tab with order
    And verify info of "Need mapping" tab with order
    And verify info of "To fulfill" tab with order

  Scenario Outline: Verify Balance when refund shipping fee PlusHub for user PayLater with variant available stock > quantity
    Given open shop on storefront
    Then checkout successfully with cart "Bluetooth Wireless Car Mp3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/warehouse" screen by url
    And get value inventory of product before fulfill
      | Product                    |
      | Bluetooth Wireless Car Mp3 |
    When user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with for "Pay later"
      | Title popup    | Header content | Detail                                                 | Price            | Flag avialable stock | Flag auto purchase |
      | Make a payment | Summary        | Charge shipping fee PlusHub>Paid for shipping 1 orders | @price shipping@ | true                 | false              |
    And user navigate to "orders" screen by url
    And user navigate to "orders" screen by url
    And verify order detail after fulfillemnt
      | Fulfillment status | Group fulfilled       | Product                    | SKU    | Quantity | Unit price | Total   | Button cancel      | Email                   | First name | Last name |
      | Processing         | Processing by PlusHub | Bluetooth Wireless Car Mp3 | PD2021 | 1        | $120.00    | $120.00 | Cancel fulfillment | shopbase@mailtothis.com | Shop       | Base      |
    And cancel order fulfill with shopbase fulfillment for user paylater
      | Button cancel      | Text confirm                                                                           | Product                    | Description                                                      | Button confirm |
      | Cancel fulfillment | Are you sure you want to cancel fulfillment request for the following product in order | Bluetooth Wireless Car Mp3 | You will see this product in Unfulfilled section in order detail | Confirm        |
    And Navigate to Balance page
    And Filter balance history by "Shop Domain" with value "@shop"
    Given Verify balance invoice
      | Index | Type | Shop name   | Content                     | Amount   | Status | Created date | Latest transaction date |
      | 1     | IN   | <Shop name> | Refund shipping fee PlusHub | <Amount> | Open   | <Now>        | <Now>                   |
    And Verify invoice detail
      | Index | Shop        | Content                     | Detail                                                       | Type | Amount   | Created date |
      | 1     | <Shop name> | Refund shipping fee PlusHub | Cancel fulfillment(s) for 1 line items of order <Order name> | IN   | <Amount> | <Now>        |
    And Verify invoice transactions
      | Index | Type | Content                                                      | Amount   | Status  | Date  |
      | 1     | IN   | Cancel fulfillment(s) for 1 line items of order <Order name> | <Amount> | Pending | <Now> |
    And clear all data
    And quit browser

    Examples:
      | Amount        | Shop name   | Order name  | Now   |
      | @shippingFee@ | @Shop name@ | @OrderName@ | @Now@ |