Feature: More Filter Order List
  #env =more_filter

  Scenario: verify display filter:tag, fulfillment service, tracking number,product mapping
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And More filter
    Then Verify display filter header
      | tag | fulfillment service  | tracking number | product mapping |
      | Tag | Fulfillment Services | Tracking number | Product mapping |


  Scenario Outline: verify sear ch by Fulfillment Service
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Filter by condition as "<KEY>"
      | KEY | condition            | value                | filter templates |
      | 1   | Fulfillment Services | PlusHub | filter_1         |
#      | 2   | Fulfillment Services | PrintHub               |
#      | 3   | Fulfillment Services | Ali Dropship Connector |
    Then verify order after filter by condition as "<KEY>"
      | KEY | tracking number | fulfillment status                         | order |
      | 1   |                 | Fulfillment Services: PlusHub | #1004 |
    And click on order name in unfulfilled orders list
    Then Verify display text "by PlusHub"
#      | 2   |                 |                    |       |
#      | 3   |                 |                    |       |
    Examples:<KEY>
      | KEY |
      | 1   |
#      | 2   |
#      | 3   |



  Scenario Outline: verify filter by product mapping
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Filter by condition as "<KEY>"
      | KEY | condition       | value | filter templates |
      | 1   | Product mapping | Yes   | filter_1         |
      | 2   | Product mapping | No    | filter_2         |
    Then verify order after filter by condition as "<KEY>"
      | KEY | tracking number | fulfillment status   | order |
      | 1   |                 | Product mapping: Yes | #1004 |
      | 2   |                 | Product mapping: No  | #1005 |
    Examples:<KEY>
      | KEY |
      | 1   |
      | 2   |


  Scenario Outline: verify filter by tracking number and Tag
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Filter by condition as "<KEY>"
      | KEY | condition       | value           | filter templates |
      | 4   | Tracking number | LT595725465CN11 | filter_3         |
      | 5   | Tag             | abc             | filter_4         |
    Then verify order after filter by condition as "<KEY>"
      | KEY | tracking number | fulfillment status               | order | Shipping carrier | Third party tracking                             |
      | 4   | LT595725465CN11 | Tracking number: LT595725465CN11 | #1001 | China Post       | https://​t.17track.net/en#nums={tracking_number} |
      | 5   |                 |                                  |       |                  |                                                  |
    Examples:<KEY>
      | KEY |
      | 4   |
      | 5   |


  Scenario Outline: verify filter by status
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Filter by condition as "<KEY>"
      | KEY | condition | value     | filter templates |
      | 1   | Status    | Open      | filter_5         |
      | 2   | Status    | Archived  | filter_6         |
      | 3   | Status    | Cancelled | filter_7         |
    Then verify order after filter by condition as "<KEY>"
      | KEY | tracking number | fulfillment status | order |
      | 1   |                 | Status: Open       | #1001 |
      | 2   |                 | Status: Archived   | #1002 |
      | 3   |                 | Status: Cancelled  | #1002 |
    Examples:<KEY>
      | KEY |
      | 1   |
      | 2   |
      | 3   |


  Scenario Outline: verify filter fail and save filter
    Given user login to shopbase dashboard
    When user navigate to "Orders" screen
    And Filter by condition as "<KEY>"
      | KEY | condition            | value    | filter templates |
      | 1   | Fulfillment Services | PrintHub | filter_8         |
      | 1   | Status               | Open     | filter_8         |
    Then Display text "Your 50 first orders won't be charged for the transaction fee within your 14-day free trial"
    And More filter
    And Filter by templates
    Then verify save info filter
    Examples:<KEY>
      | KEY |
      | 1   |


















