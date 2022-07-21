#env = fulfillment_service
Feature: More filter inventory

  Background:Access inventory success
    Given clear all data
    Given user login to shopbase dashboard
    And user navigate to "Fulfillment>Dropship>Warehouse" screen
    And click button "More filters"

  Scenario Outline: Filter purchase in inventory
    And filter "<Label>" with condition "<Condition>" and value "<Value>"
    And click button "Done"
    Then Verify data search with condition of "<Title>"
    And close browser

    Examples:
      | Label            | Condition | Value | Title       |
      | Purchased        | >=        | 26    | PURCHASED   |
      | Purchased        | <=        | 26    | PURCHASED   |
      | Sold             | >=        | 26    | SOLD        |
      | Sold             | <=        | 26    | SOLD        |
      | Available stock  | >=        | 26    | AVAILABLE   |
      | Available stock  | <=        | 26    | AVAILABLE   |
      | Unfulfilled      | >=        | 26    | UNFULFILLED |
      | Unfulfilled      | <=        | 26    | UNFULFILLED |
      | Need to purchase | >=        | 26    | NEED TO     |
      | Need to purchase | <=        | 26    | NEED TO     |

  Scenario Outline: Search condition wrong
    Given filter "<titleFilter>" with condition "<condition>" and value "<value>"
    Then show message error "The value must greater than 1."
    And close browser

    Examples:
      | titleFilter | condition | value |
      | Purchased   | >=        | 0     |
      | Purchased   | <=        | 0     |
      | Purchased   | >=        | -123  |
      | Purchased   | <=        | -123  |

  Scenario Outline: Verify save filter
    Given filter "Purchased" with condition "<condition>" and value "<value>"
    And clear info filter
    And save info filter "Sold" with condition "<condition>" and value "<value>"
    And close browser

    Examples:
      | condition | value |
      | >=        | 26    |
      | <=        | 26    |

