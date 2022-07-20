Feature: Claim list
  #env =sbase_claim

#  Scenario: Verify change solution of claim on hive_sb #SB_USC_4
#    Given user login to shopbase dashboard
#    And user navigate to "Orders" screen
#    And search order by text order then select
#    And go to "File a claim" page from More action
#    Then create new a claim then submit your claim
#      | Preferred solution | Check lineitem | Product name>Variant        | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim | Result  |
#      | Resend             | true           | Korean Chic Dress>S / White | 2        | Order not delivered | Input note>Test note | false                     | Success |
#    Given login to hive sbase
#    When Navigate claim hive detail
#    And Change solution claim in hive Sbase
#      | Solution  |
#      | Refund    |
#    Given user login to shopbase dashboard
#    When user navigate to "Fulfillment Services>Shopbase Fulfillment>Claims" screen
#    And Redirect on Claim detail
#    Then verify Preferred solution "Refund" claim

  Scenario: Verify change solution of claim after Approved/Rejected on hive_sb #SB_USC_5
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And search order by text order then select
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant        | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim | Result  |
      | Resend             | true           | Korean Chic Dress>S / White | 2        | Order not delivered | Input note>Test note | false                     | Success |
    Given login to hive sbase
    When Navigate claim hive detail
    Then verify Preferred solution before approved
    And Change status claim in hive Sbase
      | Status    | Refund Amount |
      | Approved  |               |
    Then verify Preferred solution after approved

  Scenario: Verify claim after approved claim on hive_sb
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And click "Approved" tab
    And Get count in tab "Approved"
    And user navigate to "orders" screen by url
    And search order by text order then select
    And get tracking number of order
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant        | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim | Result  |
      | Refund             | true           | Korean Chic Dress>S / White | 2        | Order not delivered | Input note>Test note | false                     | Success |
    Given login to hive sbase
    Then Verify claim in claim detail in hive sbase
      | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product           | Tracking number  | Claim type          | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
      | @claim     | @email      | @domain | @order     | Refund            | Korean Chic Dress | @trackingnumber@ | order-not-delivered | Test note     |          | 2              |              | 2              |
    And Change status claim in hive Sbase
      | Status   | Refund Amount | Msg                            |
      | Approved | 10            | has been successfully updated. |
    And redirect to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And click "Approved" tab
    Then Search and verify new claim in tab
      | Date | Order | Preferred Resolution | Status   |
      | Date | Order | Refund               | Approved |
    And close browser

  Scenario: Verify claim after In review claim on hive_sb
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And click "In review" tab
    And Get count in tab "In review"
    And user navigate to "Orders" screen
    And search order by text order then select
    And get tracking number of order
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant        | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim | Result  |
      | Refund             | true           | Korean Chic Dress>S / White | 2        | Order not delivered | Input note>Test note | false                     | Success |
    Given login to hive sbase
    Then Verify claim in claim detail in hive sbase
      | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product           | Tracking number  | Claim type          | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
      | @claim     | @email      | @domain | @order     | Refund            | Korean Chic Dress | @trackingnumber@ | order-not-delivered | Test note     |          | 2              |              | 2              |
    And Change status claim in hive Sbase
      | Status    | Refund Amount | Msg                            |
      | In review |               | has been successfully updated. |
    And redirect to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And click "In review" tab
    Then Search and verify new claim in tab
      | Date | Order | Preferred Resolution | Status    |
      | Date | Order | Refund               | In review |
    And close browser

  Scenario: Verify claim after Rejected claim on hive_sb
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And click "Rejected" tab
    And Get count in tab "Rejected"
    And user navigate to "Orders" screen
    And search order by text order then select
    And get tracking number of order
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant        | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim | Result  |
      | Refund             | true           | Korean Chic Dress>S / White | 2        | Order not delivered | Input note>Test note | false                     | Success |
    Given login to hive sbase
    Then Verify claim in claim detail in hive sbase
      | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product           | Tracking number  | Claim type          | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
      | @claim     | @email      | @domain | @order     | Refund            | Korean Chic Dress | @trackingnumber@ | order-not-delivered | Test note     |          | 2              |              | 2              |
    And Change status claim in hive Sbase
      | Status   | Refund Amount | Msg                            |
      | Rejected |               | has been successfully updated. |
    And redirect to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And click "Rejected" tab
    Then Search and verify new claim in tab
      | Date | Order | Preferred Resolution | Status   |
      | Date | Order | Refund               | Rejected |
    And close browser

  Scenario: Verify claim after cancel claim
    Given user login to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And click "Canceled" tab
    And Get count in tab "Canceled"
    And user navigate to "Orders" screen
    And search order by text order then select
    And get tracking number of order
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant        | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim | Result  |
      | Refund             | true           | Korean Chic Dress>S / White | 2        | Order not delivered | Input note>Test note | false                     | Success |
    Given login to hive sbase
    Then Verify claim in claim detail in hive sbase
      | Claim Name | Owner Email | Domain  | Order Name | Referred solution | Product           | Tracking number  | Claim type          | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
      | @claim     | @email      | @domain | @order     | Refund            | Korean Chic Dress | @trackingnumber@ | order-not-delivered | Test note     |          | 2              |              | 2              |
    And redirect to shopbase dashboard
    And user navigate to "fulfillment/dropship/claims" screen by url
    And Cancel claim
    And click "Canceled" tab
    Then Search and verify new claim in tab
      | Date | Order | Preferred Resolution | Status   |
      | Date | Order | Refund               | Canceled |
    And close browser