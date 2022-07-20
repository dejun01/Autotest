Feature: Order on store merchant
  #env =fulfill_plusbase

  Scenario: Verify info in orders list of merchant
    Given user login to plusbase dashboard
    And   user navigate to "Orders" screen
    Then Verify display info order list
      | Info Action                                                         | Table Name                                                   | Name Tab                                     |
      | Payment Status,Fulfillment Status,More filters,Current store,Export | ORDER,DATE,CUSTOMER,PAYMENT STATUS,FULFILLMENT STATUS,PROFIT | All,Open,Unfulfilled and partially fulfilled |
    And close browser

  Scenario: Verify status order on merchant store when hold order SB_PLB_OPLM_1 SB_PLB_OPLM_2
    Given open plusbase on storefront
    Then checkout successfully with cart "(Test product) Women Two Piece Set Tracksuits O-neck:S>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and fulfillment status = "Unfulfilled" on order list plusbase
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Hold" order
    And redirect to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and fulfillment status = "Unfulfilled" on order list plusbase
    And close browser

  Scenario: verify status order on merchant store  when approve order then fulfill order #SB_PLB_OPLM_3 #SB_PLB_OPLM_4 #SB_PLB_OPLM_5 #SB_PLB_OPLM_6
    Given  open plusbase on storefront
    Then checkout successfully with cart "(Test product) Women Two Piece Set Tracksuits O-neck:S>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    Given user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Paid" and fulfillment status = "Unfulfilled" on order list plusbase
    And redirect to shopbase dashboard
    And user navigate to "ops/orders" screen by url
    And search and select order on plusbase
    And fulfill order in Order detail
      | Lineitem                                                  | Fulfillment Service  | Plusbase |
      | (Test product) (Dont update) Product Automation 1>Beige/S | ShopBase Fulfillment | yes      |
    And redirect to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Paid" and fulfillment status = "Processing" on order list plusbase
    And search and select order on plusbase
    Then verify order detail ShopBase after fulfill via ShopBase Fulfillment
      | Status group | Count group | Lineitem                                                      | Quantity lineitem | Cancel fulfillment | Add tracking | Fulfill with | Mark as fulfilled | Track shipment | Edit tracking | Plusbase |
      | Processing   | 1           | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/S | 1                 | false              | false        | false        | false             | false          | false         | yes      |
    And close browser

  Scenario: verify data order on merchant store when order is shipped #SB_PLB_OPLM_6
    Given  open plusbase on storefront
    Then checkout successfully with cart "(Test product) Women Two Piece Set Tracksuits O-neck:L>1"
      | Email                   | First Name | Last Name | Address           | Country       | City         | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 100 Wilshire Blvd | United States | Santa Monica | 90401    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given staff login to shopbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Authorized" and Approve status = "unapproved" on order list
    And Choose order actions
    And "Approve order" order
    And search and select order on plusbase
    And fulfill order in Order detail
      | Lineitem                                                      | Fulfillment Service  | Plusbase |
      | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/L | ShopBase Fulfillment | yes      |
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
    And wait 3 second
    And user login to plusbase dashboard
    And user navigate "Orders" screen
    Then Search and verify payment status = "Paid" and fulfillment status = "Fulfilled" on order list plusbase
    And search and select order on plusbase
    Then verify order detail ShopBase after fulfill via ShopBase Fulfillment
      | Status group | Count group | Lineitem                                                      | Quantity lineitem | Cancel fulfillment | Add tracking | Fulfill with | Mark as fulfilled | Track shipment | Edit tracking | Plusbase |
      | Fulfilled    | 1           | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/L | 1                 | false              | false        | false        | false             | true           | false         | yes      |
    And close browser