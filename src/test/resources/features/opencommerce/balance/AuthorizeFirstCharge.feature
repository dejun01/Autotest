Feature: Authorize_first_charge
  #staging_sbase_authorized_first_charge

  Background: Login to shopbase dashboard
    Given user login to shopbase dashboard

  Scenario: Manual topup
    When Navigate to Balance page
    And Top up "$40" to balance
    Then login to hive sbase
    And Navigate to first charge review in hive
    And Enter bin "424242" and user name
    And Approve card
    Then logout to hive sbase
    Then login to hive sbase
    And Navigate to first charge review in hive
    And Filter "424242" and user name
    And verify card status is "active"
    And Navigate to first charge review in hive
    And Enter bin "424242" and user name
    And clear status card
    Then refresh page
    Then logout to hive sbase
    When user login to shopbase dashboard by API
    And Navigate to Balance page
    And click to update card
    And update credit card
      | Card number      | Expired Date | CVV | Country |
      | 5454545454545454 | 12/33        | 111 | Vietnam |
    And Top up "$40" to balance
    Then login to hive sbase
    And Navigate to first charge review in hive
    And Enter bin "545454" and user name
    And Reject card
    Then logout to hive sbase
    Then login to hive sbase
    And Navigate to first charge review in hive
    And Filter "545454" and user name
    And verify card status is "rejected"
    Then logout to hive sbase

  Scenario: Auto topup
    And Get balance info by API
    And Charge "all" from balance with API
    And Navigate to Balance page
    And Get balance info by API
    Then enable auto top up and disable top up
    Then refresh page
    Then Verify balance amount as "<KEY>"
      | KEY | Total current available | Available to payout | Pending to review |
      | 1   | +20.60                  | +0                  | +0                |
    Then login to hive sbase
    And Navigate to first charge review in hive
    And Filter "545454" and user name
    And verify card status is "waiting_review"
    Then logout to hive sbase

  Scenario: Replace card
    When navigate to Balance page
    And click to update card
    And update credit card
      | Card number      | Expired Date | CVV | Country |
      | 4242424242424242 | 07/30        | 111 | Vietnam |