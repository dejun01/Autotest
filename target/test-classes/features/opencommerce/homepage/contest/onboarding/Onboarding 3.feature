Feature: onboarding in hompage

  #sbase_homepage_onboarding
  Scenario Outline: create and skips onboarding in shop ShopBase #SB_OBD_11 #SB_OBD_12 #SB_OBD_13 #SB_OBD_21 #SB_OBD_17 #SB_OBD_18
    Given login to hive sbase
    And clear shop data as "<Shop id>"
    When login to dashboard
    And choose shop as "<KEY>"
      | KEY | Shop name |
      | 1   | shop      |
    And input store information as "<KEY>"
      | KEY | Store country | Your personal location | Phone number | Social profile             |
      | 1   | Vietnam       | Vietnam                | 0985676567   | https://www.google.com.vn/ |
    And select store type as "<KEY>"
      | KEY | Business type | Store type | Category/Niche |
      | 1   | Others        |            |                |
    And customize store as "<KEY>"
      | KEY | Type     | Import content | Store import | Answer question | Customize style | Color | Font | Type product |
      | 1   | ShopBase | No thanks      |              |                 | Yes             |       |      | Products     |
    Then verify onboarding displayed in dashboard with name ""
    And verify skip all task in progress bar
#    And disable password in homepage
#    And confirm a plan from homepage
    Examples:
      | KEY | Shop id |
      | 1   |         |


  Scenario Outline: create and complete onboarding in shop ShopBase #SB_OBD_16 #SB_OBD_20 #SB_OBD_14
    Given login to hive sbase
    And clear shop data as "<Shop id>"
    When login to dashboard
    And choose shop as "<KEY>"
      | KEY | Shop name |
      | 1   | shop      |
    And input store information as "<KEY>"
      | KEY | Store country | Your personal location | Phone number | Social profile             |
      | 1   | Vietnam       | Vietnam                | 0985676567   | https://www.google.com.vn/ |
    And select store type as "<KEY>"
      | KEY | Business type | Store type | Category/Niche |
      | 1   | Others        |            |                |
    And customize store as "<KEY>"
      | KEY | Type     | Import content | Store import | Answer question | Customize style | Color | Font | Type product |
      | 1   | ShopBase | No thanks      |              |                 | Yes             |       |      | Products     |
    Then verify onboarding displayed in dashboard with name ""
    And Disable password
    And complete Onboarding steps
    Examples:
      | KEY | Shop id |
      | 1   |         |


