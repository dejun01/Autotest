Feature: Claim On Hive
  #env = claim_on_hive

  Scenario: Verify display data claim in hive
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And search order by text order then select
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim |
      | Refund             | true           | T_Shirt>M            | 1        | Order not delivered | Input note>Test note | false                     |
    And user navigate to "Fulfillment" screen
    And Click to "Dropship" section at Settings screen
    And Click to "Claims" section at Settings screen
    And click "New" tab
    And Redirect on Claim detail
    And verify data of order in new claim
      | Is show | Preferred solution | Shipping infor                                           | Product name>Variant | Quantity | Status    | Tracking number  | Shipped on | Reason for claim    | Note      |
      | true    | Refund             | 1845 Johnny Lane, Los Angeles, Washington, United States | T_Shirt>M            | 1        | Fulfilled | @trackingnumber@ |            | Order not delivered | Test note |
    Given login to hive sbase
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | New         |
    And Acc claim detail
    Then Verify claim in claim detail in hive sbase
      | Product                                                                                                                                   | Tracking number  | Claim type          | Seller's note | SBCN SKU | Order quantity | Shipped date | Claim quantity |
      | CNYISHE Sexy Pu Leather Bodysuits Rompers Women Jumpsuits Off Shoulder Lace Up Skinny Bodysuit Streetwear Bandage Tops Overalls (Black/M) | @trackingnumber@ | order-not-delivered | Test note     |          | 5              | Apr 20, 2021 | 1              |
    When click on tracking nummber
    Then Redirect to link track
      | link track                     | Tracking number  |
      | https://t.17track.net/en#nums= | @trackingnumber@ |


  Scenario: Verify change status of claim to in review
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And search order by text order then select
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim |
      | Refund             | true           | T_Shirt>M            | 1        | Order not delivered | Input note>Test note | false                     |
    And user navigate to "Fulfillment" screen
    And Click to "Dropship" section at Settings screen
    And Click to "Claims" section at Settings screen
    And click "New" tab
    And Redirect on Claim detail
    And verify data of order in new claim
      | Is show | Preferred solution | Shipping infor                                           | Product name>Variant | Quantity | Status    | Tracking number  | Shipped on | Reason for claim    | Note      |
      | true    | Refund             | 1845 Johnny Lane, Los Angeles, Washington, United States | T_Shirt>M            | 1        | Fulfilled | @trackingnumber@ |            | Order not delivered | Test note |
    Given login to hive sbase
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | New         |
    And Acc claim detail
    And Change status in Sbase
      | Status    | Refund Amount |
      | In review |               |
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | In Review   |
    And close browser


  Scenario: Verify change status of claim to Rejected
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And search order by text order then select
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim |
      | Refund             | true           | T_Shirt>M            | 1        | Order not delivered | Input note>Test note | false                     |
    And user navigate to "Fulfillment" screen
    And Click to "Dropship" section at Settings screen
    And Click to "Claims" section at Settings screen
    And click "New" tab
    And Redirect on Claim detail
    And verify data of order in new claim
      | Is show | Preferred solution | Shipping infor                                           | Product name>Variant | Quantity | Status    | Tracking number  | Shipped on | Reason for claim    | Note      |
      | true    | Refund             | 1845 Johnny Lane, Los Angeles, Washington, United States | T_Shirt>M            | 1        | Fulfilled | @trackingnumber@ |            | Order not delivered | Test note |
    Given login to hive sbase
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | New         |
    And Acc claim detail
    And Change status in Sbase
      | Status   | Refund Amount |
      | Rejected |               |
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | Rejected    |


  Scenario: Verify change status of claim to Approved
    Given user login to shopbase dashboard
    And Navigate to Balance page
    And Get balance info
    And user navigate to "Orders" screen
    And search order by text order then select
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim |
      | Refund             | true           | T_Shirt>M            | 1        | Order not delivered | Input note>Test note | false                     |
    And user navigate to "Fulfillment" screen
    And Click to "Dropship" section at Settings screen
    And Click to "Claims" section at Settings screen
    And click "New" tab
    And Redirect on Claim detail
    And verify data of order in new claim
      | Is show | Preferred solution | Shipping infor                                           | Product name>Variant | Quantity | Status    | Tracking number  | Shipped on | Reason for claim    | Note      |
      | true    | Refund             | 1845 Johnny Lane, Los Angeles, Washington, United States | T_Shirt>M            | 1        | Fulfilled | @trackingnumber@ |            | Order not delivered | Test note |
    Given login to hive sbase
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | New         |
    And Acc claim detail
    And Change status in Sbase
      | Status   | Refund Amount |
      | Approved | 10            |
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | Approved    |
    Given redirect to shopbase dashboard
    When Navigate to Balance page
    Then Verify balance after approved


  Scenario: Verify claim on hive when cancel claim on sbase
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen
    And search order by text order then select
    And go to "File a claim" page from More action
    Then create new a claim then submit your claim
      | Preferred solution | Check lineitem | Product name>Variant | Quantity | Reason for claim    | Claim evidence       | Disable Submit your claim |
      | Refund             | true           | T_Shirt>M            | 1        | Order not delivered | Input note>Test note | false                     |
    And user navigate to "Fulfillment" screen
    And Click to "Dropship" section at Settings screen
    And Click to "Claims" section at Settings screen
    And Cancel claim
    Given login to hive sbase
    And Acc to page "Claim list"
    Then Search Claim and verify claim in claim list page in hive sbase
      | Claim ID  | Referred solution | Created At | Status Name |
      | @claimId@ | refund            | Created At | Canceled    |















