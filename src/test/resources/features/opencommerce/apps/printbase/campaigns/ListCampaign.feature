Feature: Verify campaign list
#pbase_dashboard_campaign_list
  Scenario: Verify filter campaign

    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>All campaigns" screen
    Then verify total campaigns by filter condition
      | Filter by  | Value   | Total product |
      | Collection | t-shirt | 4 campaigns   |
