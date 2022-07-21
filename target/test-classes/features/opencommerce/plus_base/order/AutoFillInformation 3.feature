Feature: Auto fill phone, first name, last name
#  evn: auto_fill_info

  Scenario: Verify information when order dont enter phone
    Given open plusbase on storefront
    Then checkout successfully via stripe with cart "(Auto) Dresses Womens Spring Autumn"
      | Email                   | Last Name | Address           | Country       | City         | Zip code | State      | Phone | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | TT        | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California |       | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given admin login to shopTemplate dashboard
    Then user navigate to "Orders" screen
    And Search and verify fulfillment status = "Unfulfilled"
    And Select order fulfillment status = unfulfill
    And "Approve order" order
    And Select the order in order list to view detail
    Then Click button fulfill and fulfill order in fulfillment
    And Verify status order
      | Status     | Lable filter       |
      | Processing | Time since created |
    And verify DO "out" created in odoo with "1" DO
    And Verify status of Delivery Order on Odoo
      | Order name   | Customer | Status   |
      | @ordernumber |          | assigned |
    And validate DO on Odoo
      | Order name   | Owner |
      | @ordernumber |       |
    And Verify status of Delivery Order on Odoo
      | Order name   | Owner | Status |
      | @ordernumber |       | done   |
    And redirect to shopTemplate dashboard
    Then user navigate to "Orders" screen
    And Search and verify payment status = "Fulfilled" on order list
    And Select the order in order list to view detail
    Then Verify tracking number order in order detail
    And close browser
