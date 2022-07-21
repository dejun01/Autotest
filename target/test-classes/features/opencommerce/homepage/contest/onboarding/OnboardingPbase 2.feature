Feature:  onboarding homeapage in shop printbase

  #sbase_homepage_onboarding_pbase
  Scenario Outline: verify onboarding in shop printbase #SB_OBD_15
    When login to dashboard
    And create a shop with name is "shop-printbase-"
    And input store information as "<KEY>"
      | KEY | Store country | Your personal location | Phone number | Social profile             |
      | 1   | Vietnam       | Vietnam                | 0985676567   | https://www.google.com.vn/ |
    And select store type as "<KEY>"
      | KEY | Business type                            | Store type | Category/Niche |
      | 1   | Print On Demand>I want a PrintBase store |            |                |
    And customize store as "<KEY>"
      | KEY | Type      | Import content | Customize style | Type product |
      | 1   | PrintBase | No thanks      | No              | Products     |
    Then verify onboarding displayed in dashboard with name "shop-printbase-"
#    And verify skip all tasks in other way
    And verify skip all task in progress bar
    Examples:
      | KEY |
      | 1   |