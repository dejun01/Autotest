Feature: Get tracking number for order fulfill via SBFF
#env: get_tracking_number
  Scenario: get tracking number for order fulfill with status awaiting stock on Fulfill order page
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "Kefira 2021 Turtleneck Knitted Sweater Women:M>3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with
      | Title popup | Header content | Detail | Price            | Flag avialable stock | Flag auto purchase |
      |             |                |        | @price shipping@ | false                | true               |
    And switch to "Awaiting stock" tab and search order after pay
    Then get tracking number of order on "Fulfill order" page
    Then verify "disable" btn Get tracking number
    And user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And verify data of order detail after get tracking number
    And close browser

  Scenario: get tracking number for order fulfill with status awaiting stock on order detail
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "Kefira 2021 Turtleneck Knitted Sweater Women:M>3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with
      | Title popup | Header content | Detail | Price            | Flag avialable stock | Flag auto purchase |
      |             |                |        | @price shipping@ | false                | true               |
    And user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    Then get tracking number of order on "Order detail" page
    And verify data of order detail after get tracking number
    And user navigate to "fulfillment/dropship/list" screen by url
    And switch to "Awaiting stock" tab and search order after pay
    Then verify "disable" btn Get tracking number
    And close browser

  Scenario: Verify cancel fulfillment after getting tracking number
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "Kefira 2021 Turtleneck Knitted Sweater Women:M>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with
      | Title popup | Header content | Detail | Price            | Flag avialable stock | Flag auto purchase |
      |             |                |        | @price shipping@ | false                | true               |
    And switch to "Awaiting stock" tab and search order after pay
    Then get tracking number of order on "Fulfill order" page
    And cancel fulfillment order in Fulfill order page
    And user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    And verify order detail after cancel fulfillment with status "Unfulfilled"
    And close browser

  Scenario: Validate DO OUT after getting tracking number
    Given open shop on storefront
    Then checkout of order fulfillment successfully via stripe with cart "Kefira 2021 Turtleneck Knitted Sweater Women:S>3"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90002    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/list" screen by url
    And fulfill order with
      | Title popup | Header content | Detail | Price            | Flag avialable stock | Flag auto purchase |
      |             |                |        | @price shipping@ | false                | true               |
    And switch to "Awaiting stock" tab and search order after pay
    Then get tracking number of order on "Fulfill order" page
    And user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    And verify data of order detail after get tracking number
    Given user navigate to "fulfillment/dropship/warehouse" screen by url
    And Acc page "Purchase orders"
    Then get purchase order after get tracking number
    Then Search purchase order and Verify info purchase order
      | Condition       | Tab |
      | PURCHASE NUMBER |     |
    And verify DO "in" created in odoo with "1" DO
    And validate DO on Odoo
      | Order name | Owner |
      | purchase   |       |
    And check availability DO out of order "@order"
    And Verify status of Delivery Order on Odoo
      | Order name   | Owner | Status   |
      | @ordernumber |       | assigned |
    And validate DO on Odoo
      | Order name   | Owner |
      | @ordernumber |       |
    And Verify status of Delivery Order on Odoo
      | Order name   | Owner | Status |
      | @ordernumber |       | done   |
    Given user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    Then verify order detail ShopBase after fulfill via PlusHub
      | Status group         | Count group | Lineitem                                       | Quantity lineitem | Cancel fulfillment | Add tracking | Fulfill with | Mark as fulfilled | Track shipment | Edit tracking | Tracking number  |
      | Fulfilled by PlusHub | 1           | Kefira 2021 Turtleneck Knitted Sweater Women>S | 3                 | false              | false        | false        | false             | true           | true          | @tracking number |
    And close browser

