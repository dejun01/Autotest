Feature: Filter and custom filter
#fulfillment_service

  Background: Access order list page
    Given user login to shopbase dashboard
    And user navigate to "Orders" screen

#  Scenario: 1. Verify filter normal in order list
#    And filter "Payment Status" with condition "Refunded"
#    And click button "Done"
#    And verify result filter normal with
#      | Title          | Condition |
#      | Payment status | Refunded  |
#    And save filter normal with "Add current filter"
#    And close browser


  Scenario: 2. Verify add, update and delete filter in order list
    Given verify tab filter default in order list
#    And verify filter template in popup filter
    And add filter template with name = "Filter payment status"
      | Title          | Condition |
      | Payment status | Refunded  |
    And edit filter template with name = "Filter payment status" to "Filter template 1"
    And delete filter template with name = "Filter template 1"
    And close browser

  Scenario Outline: 3. Verify filter in order list
    Given filter "<Label>" with condition "<Value>"
    Then verify filter "<Label>" with condition "<Value>" and title "<Title>"
    And close browser

    Examples:
      | Label              | Value     | Title              |
      | Payment status     | Refunded  | Payment Status     |
      | Fulfillment status | Fulfilled | Fulfillment Status |
      | Payment status     | Pending   | Payment Status     |




