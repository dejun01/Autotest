@dashboardCustomTracking
Feature: Custom Tracking
# sbase_custom_tracking

  Scenario: Pinterest Custom Tracking
    When open sale channel "Pinterest" tracking
    Then login to Pinterest
    And navigate to conversion page on Pinterest
#    And add event type on Pinterest
    And get data event on Pinterest
    When open storefront shop on a new tab
    When open product "TrackingProduct" and one page checkout without verify with "one" user
    And verify tracking increase on Pinterest
      | Init | PageVisit | AddToCart | Checkout |
      | 1    | 1         | 1         | 1        |
    And quit browser
