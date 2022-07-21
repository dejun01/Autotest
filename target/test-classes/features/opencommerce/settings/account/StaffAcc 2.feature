Feature: Staff account

#  Env: sbase_create_page

  Scenario: Verify permission Themes #SB_SET_ACC_PMS_1
    Given grant permission "Themes" for staff by API
    Given staff login to shopbase dashboard by API
    Then user navigate to "Online Store" screen
    And Verify staff main permission
      | name permission | access |
      | Themes          | true   |
    Then quit browser

  Scenario: Verify permission Pages #SB_SET_ACC_PMS_2
    Given grant permission "Pages" for staff by API
    Given staff login to shopbase dashboard
    Then user navigate to "Online Store>Pages" screen
    And Verify staff main permission
      | name permission | access |
      | Pages           | true   |
    Then quit browser


  Scenario: Verify permission Navigation #SB_SET_ACC_PMS_3
    Given grant permission "Navigation" for staff by API
    Given staff login to shopbase dashboard
    Then user navigate to "Online Store>Navigation" screen
    And Verify staff main permission
      | name permission | access |
      | Navigation      | true   |
    Then quit browser

#  Scenario: Verify permission Preferences #SB_SET_ACC_PMS_4
#    Given grant permission "Preferences" for staff by API
#    Given staff login to shopbase dashboard
#    Then user navigate to "Online Store>Preferences" screen
#    And Verify staff main permission
#      | name permission | access |
#      | Preferences     | true   |
#    Then quit browser


  Scenario: Verify permission Domains #SB_SET_ACC_PMS_5
    Given grant permission "Domains" for staff by API
    Given staff login to shopbase dashboard
    Then user navigate to "Online Store>Domains" screen
    And Verify staff main permission
      | name permission | access |
      | Domains         | true   |
    Then quit browser


  Scenario: Verify permission Apps Usell #SB_SET_ACC_PMS_6
    Given grant permission "Apps,Products & Catalog" for staff by API
    Given staff login to shopbase dashboard
    Then user navigate to "Apps" screen
    And user navigate to "Apps" screen
    And select app "Boost Upsell" on Apps list
    And wait page "Welcome to Upsell" show
    Then quit browser


#  Scenario Outline: verify grant permission for account staff
#    Given grant permission "<Grant permission>" for staff by API
#    Given staff login to shopbase dashboard
#    And user navigate to "Online Store" screen
#    And verify show actions with theme "<KEY>"
#      | KEY | Action    | Theme name | isShowAction |
#      | 001 | Edit code | Inside     | false        |
#      | 002 | Edit code | Inside     | true         |
#    And do actions with theme on block More themes "<KEY>"
#      | KEY | Action    | Theme name |
#      | 002 | Edit code | Inside     |
#    Examples:
#      | KEY | Grant permission                        |
#      | 001 | Themes, View assigned staffs            |
#      | 002 | Themes, Edit code, View assigned staffs |