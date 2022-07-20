Feature: verify widget dashboard printhub

 #sbase_homepage_widget
  Scenario: verify widget show in dashboard printhub

    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And verify dashboard printhub screen
    Then verify widget in dashboard printhub

  Scenario: verify analytics in dashbboard printhub
    Given user login to shopbase dashboard
    And user navigate to "Apps" screen
    And select app "Print Hub" on Apps list
    And get data statistic and analytic printhub dashboard by api
      | Time          |
      | Today         |
      | Yesterday     |
      | Last 7 days   |
      | Last 30 days  |
      | Last 90 days  |
      | Last month    |
      | Week to date  |
      | Month to date |
    Then search item by keyword
      | Keyword   | Result |
      | Quilt     | Yes    |
      | not exist | No     |