  #plbase_claim_list
  Feature: Claim list

    Scenario: Precondition create new orders
      Given open plusbase on storefront
      Then checkout successfully via stripe with cart "(Test product) Women Two Piece Set Tracksuits O-neck:L>2"
        | Email                   | First Name | Last Name | Address          | Country       | City        | Zip code | State      | Phone      | Payment gateway | Payment method | Card number      | Cardholder name | Expired Date | CVV | Card type |
        | shopbase@mailtothis.com | Shop       | Base      | 1845 Johnny Lane | United States | Los Angeles | 90001    | California | 2025550147 | Stripe          | Credit Card    | 4242424242424242 | Shopbase        | 11/22        | 113 | Visa      |
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
      And close browser

    Scenario: Verify claim after cancel claim
      Given staff login to shopbase dashboard
      And user navigate to "fulfillment/dropship/claims" screen by url
      And click "Canceled" tab
      And Get count in tab "Canceled"
      And user navigate to "Orders" screen
      And search and select order on plusbase
      And get tracking number of order plusbase
      And go to "File a claim" page from More action in PlusBase
      Then create new a claim then submit your claim
        | Preferred solution | Check lineitem | Product name>Variant                                          | Quantity | Reason for claim | Claim evidence       | Disable Submit your claim | Result  |
        | Refund             | true           | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/L | 1        | Late delivery    | Input note>Test note | false                     | Success |
      And user navigate to "Claims" screen
      And Cancel claim
      And click "Canceled" tab
      Then Search and verify new claim in tab
        | Date | Order  | Preferred Resolution | Status   |
        | Date | @Order | Refund               | Canceled |
      And close browser

    Scenario: Verify claim after approved claim on hive_sb
      Given staff login to shopbase dashboard
      And user navigate to "fulfillment/dropship/claims" screen by url
      And click "Approved" tab
      And Get count in tab "Approved"
      And user navigate to "Orders" screen
      And search and select order on plusbase
      And get tracking number of order plusbase
      And go to "File a claim" page from More action in PlusBase
      Then create new a claim then submit your claim
        | Preferred solution | Check lineitem | Product name>Variant                                          | Quantity | Reason for claim | Claim evidence       | Disable Submit your claim | Result  |
        | Resend             | true           | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/L | 1        | Late delivery    | Input note>Test note | false                     | Success |
      Given login to hive sbase
      Then Verify claim in claim detail in hive sbase
        | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product                                                         | Tracking number  | Claim type    | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
        | @claim     | @email      | @domain | #order     | resend            | (Test product) Women Two Piece Set Tracksuits O-neck (Violet/L) | @trackingnumber@ | late-delivery | Test note     |          | 1              |              | 1              |
      And Change status claim in hive Sbase
        | Status   | Refund Amount | Msg                            |
        | Approved |               | has been successfully updated. |
      And redirect to plusbaseTemplate dashboard
      And user navigate to "fulfillment/dropship/claims" screen by url
      And click "Approved" tab
      Then Search and verify new claim in tab
        | Date | Order  | Preferred Resolution | Status   |
        | Date | @Order | Resend               | Approved |
      Then redirect to shopbase dashboard
      Given user navigate to "Orders" screen
      And search and select order on plusbase
      Then verify order detail after approved claim resend
        | Status group                       | Count group | Lineitem                                                      | Quantity lineitem | Type group | Claim Id |
        | Processing by ShopBase Fulfillment | 2           | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/L | 1                 | Resend     |          |
      And close browser
      And close browser

    Scenario: Verify claim after In review claim on hive_sb
      Given staff login to shopbase dashboard
      And user navigate to "fulfillment/dropship/claims" screen by url
      And click "In review" tab
      And Get count in tab "In review"
      And user navigate to "Orders" screen
      And search and select order on plusbase
      And get tracking number of order plusbase
      And go to "File a claim" page from More action in PlusBase
      Then create new a claim then submit your claim
        | Preferred solution | Check lineitem | Product name>Variant                                          | Quantity | Reason for claim | Claim evidence       | Disable Submit your claim | Result  |
        | Refund             | true           | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/L | 1        | Late delivery    | Input note>Test note | false                     | Success |
      Given login to hive sbase
      Then Verify claim in claim detail in hive sbase
        | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product                                                         | Tracking number  | Claim type    | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
        | @claim     | @email      | @domain | #order     | refund            | (Test product) Women Two Piece Set Tracksuits O-neck (Violet/L) | @trackingnumber@ | late-delivery | Test note     |          | 1              |              | 1              |
      And Change status claim in hive Sbase
        | Status    | Refund Amount | Msg                            |
        | In review |               | has been successfully updated. |
      And redirect to shopbase dashboard
      And user navigate to "fulfillment/dropship/claims" screen by url
      And click "In review" tab
      Then Search and verify new claim in tab
        | Date | Order  | Preferred Resolution | Status    |
        | Date | @Order | Refund               | In review |
      And close browser

    Scenario: Verify claim after Rejected claim on hive_sb
      Given staff login to shopbase dashboard
      And user navigate to "fulfillment/dropship/claims" screen by url
      And click "Rejected" tab
      And Get count in tab "Rejected"
      And user navigate to "Orders" screen
      And search and select order on plusbase
      And get tracking number of order plusbase
      And go to "File a claim" page from More action in PlusBase
      Then create new a claim then submit your claim
        | Preferred solution | Check lineitem | Product name>Variant                                          | Quantity | Reason for claim | Claim evidence       | Disable Submit your claim | Result  |
        | Refund             | true           | (Test product) Women Two Piece Set Tracksuits O-neck>Violet/L | 1        | Late delivery    | Input note>Test note | false                     | Success |
      Given login to hive sbase
      Then Verify claim in claim detail in hive sbase
        | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product                                                         | Tracking number  | Claim type    | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
        | @claim     | @email      | @domain | @order     | refund            | (Test product) Women Two Piece Set Tracksuits O-neck (Violet/L) | @trackingnumber@ | late-delivery | Test note     |          | 1              |              | 1              |
      And Change status claim in hive Sbase
        | Status   | Refund Amount | Msg                            |
        | Rejected |               | has been successfully updated. |
      And redirect to shopbase dashboard
      And user navigate to "fulfillment/dropship/claims" screen by url
      And click "Rejected" tab
      Then Search and verify new claim in tab
        | Date | Order  | Preferred Resolution | Status   |
        | Date | @Order | Refund               | Rejected |
      And close browser



