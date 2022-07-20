#sbase_create_new_claim

Feature: Create new claim

  Scenario: Precondition create new order
    Given open shop on storefront
    Then checkout successfully via stripe with cart "Women Dress 2021 New Chiffon:Sling Skirt>2;Product unmap:S"
      | Email                   | First Name | Last Name | Address          | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
      | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane | United States | Los Angeles | 90001    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    Then click on order name in unfulfilled orders list
    Then fulfill order in Order detail
      | Lineitem                                 | Fulfillment Service  |
      | Women Dress 2021 New Chiffon>Sling Skirt | PlusHub              |
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
    Given user navigate to "orders" screen by url
    And click on order name in unfulfilled orders list
    Then verify order detail ShopBase after fulfill via PlusHub
      | Status group                      | Count group | Lineitem                                 | Quantity lineitem | Cancel fulfillment | Add tracking | Fulfill with | Mark as fulfilled | Track shipment | Edit tracking |
      | Fulfilled by PlusHub              | 2           | Women Dress 2021 New Chiffon>Sling Skirt | 2                 | false              | false        | false        | false             | true           | true          |
      | Unfulfilled                       | 2           | Product unmap>S                          | 1                 | false              | false        | true         | true              | false          | false         |
    And close browser

  Scenario: Verify data claim after create claim Resend
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And get tracking number of order
    Then go to "File a claim" page from More action
    And create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant                     | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim | Result  |
      |                    | true           | Women Dress 2021 New Chiffon>Sling Skirt | 2        | Order not delivered | Input note>Test note | false                     | Success |
    And verify data of order in new claim
      | Is show | Preferred solution | Shipping infor                                           | Product name>Variant                     | Quantity | Status    | Tracking number  | Shipped on | Reason for claim    | Note      |
      | true    | Resend             | 1845 Johnny Lane, Los Angeles, California, United States | Women Dress 2021 New Chiffon>Sling Skirt | 2        | Fulfilled | @trackingnumber@ |            | Order not delivered | Test note |
    Given login to hive sbase
    Then Verify claim in claim detail in hive sbase
      | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product                      | Tracking number  | Claim type          | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
      | @claim     | @email      | @domain | #order     | Resend            | Women Dress 2021 New Chiffon | @trackingnumber@ | order-not-delivered | Test note     |          | 2              |              | 2              |
    And Change status claim in hive Sbase
      | Status   | Refund Amount | Msg                            |
      | Approved |               | has been successfully updated. |
    Then redirect to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then verify order detail after approved claim resend
      | Status group                       | Count group | Lineitem                                 | Quantity lineitem | Type group | Claim Id |
      | Processing by PlusHub | 3           | Women Dress 2021 New Chiffon>Sling Skirt | 2                 | Resend     |          |
    And close browser

  Scenario: Verify data when go to New claim page
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    And get tracking number of order
    Then go to "File a claim" page from More action
    And verify data of order in new claim
      | Is show | Preferred solution | Shipping infor                                           | Product name>Variant                     | Quantity | Status    | Tracking number  | Shipped on | Reason for claim |
      | true    | Resend             | 1845 Johnny Lane, Los Angeles, California, United States | Women Dress 2021 New Chiffon>Sling Skirt | 1        | Fulfilled | @trackingnumber@ |            |                  |
      | false   |                    |                                                          | Product unmap>S                          |          |           |                  |            |                  |
    And close browser

  Scenario: Verify vaild data when create claim not select lineitem
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then go to "File a claim" page from More action
    And create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant                     | Quantity | Reason for claim | Claim evidence | Disable Submit your claim | Result |
      |                    | false          | Women Dress 2021 New Chiffon>Sling Skirt |          |                  |                | true                      | Failed |
    And close browser

  Scenario: Verify valid data when create claim not upload evidence
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then go to "File a claim" page from More action
    And create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant                     | Quantity | Reason for claim     | Claim evidence | Disable Submit your claim | Result |
      |                    | true           | Women Dress 2021 New Chiffon>Sling Skirt | 1        | Product unacceptable |                | false                     | Failed |
    And verify message after submit your claim "Evidence is required for Product issues"
    And close browser

  Scenario: Verify valid data when create claim not reason for claim
    Given user login to shopbase dashboard
    Given user navigate to "Orders" screen
    And click on order name in unfulfilled orders list
    Then go to "File a claim" page from More action
    And create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant                     | Quantity | Reason for claim | Claim evidence | Disable Submit your claim | Result |
      |                    | true           | Women Dress 2021 New Chiffon>Sling Skirt | 1        |                  |                | false                     | Failed |
    And verify message after submit your claim "Claim must have reason"
    And close browser
