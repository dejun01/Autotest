@dashboardCommon @dashboard @authentication
Feature: Switch HongKong domain
#sbase_switch_HongKong_domain
  Scenario: Verify switch HongKong domain on sign up page
    When open page & verify show button switch to HongKong domain
      | Page    | Param url | Is enabled switch domain |
      | sign-up |           | true                     |
    And get current domain url and verify domain
      | Domain location |
      | US              |
    And click to switch domain
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    And verify show elements on sign up page
    And click to sign in from sign up page
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    And verify show elements on sign in page
    Then quit browser

  Scenario: Verify switch HongKong domain on sign up partner page
    When open page & verify show button switch to HongKong domain
      | Page                 | Param url | Is enabled switch domain |
      | sign-up?partner=true |           | true                     |
    And get current domain url and verify domain
      | Domain location |
      | US              |
    And click to switch domain
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    And click to sign in from sign up page
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    And verify show elements on sign in page
    Then quit browser

  Scenario: Verify switch HongKong domain on sign in page
    When open page & verify show button switch to HongKong domain
      | Page    | Param url | Is enabled switch domain |
      | sign-in |           | true                     |
    And get current domain url and verify domain
      | Domain location |
      | US              |
    And click to switch domain
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    And login to dashboard
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    And verify domain in list shop changed to HongKong domain
    Then quit browser

  Scenario: Verify switch HongKong domain on affiliate page
    When open page & verify show button switch to HongKong domain
      | Page                 | Param url | Is enabled switch domain |
      | sign-in?partner=true |           | true                     |
    And login to dashboard
    And get current domain url and verify domain
      | Domain location |
      | US              |
    Then verify show button switch domain "HongKong" on user menu bar
    And click to switch domain on user menu bar
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    Then verify show button switch domain "US" on user menu bar
    And verify elements on affiliate page
    Then quit browser

  Scenario: Verify switch HongKong domain on dashboard
    Given user login to shopbase dashboard
    And user navigate to "Orders>All orders" screen
    Then verify show button switch domain "HongKong" on user menu bar
    And click to switch domain on user menu bar
    And get current domain url and verify domain
      | Domain location |
      | HongKong        |
    Then verify show button switch domain "US" on user menu bar
    And verify elements on order page





