Feature: Verify Select Mockup Popup
#   pbase_new_campaign_flow
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario: Verify show mockup not default on popup select mockup #create base product not tick default all mockup
    Given user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When click select mockup with Base as "01"
      | KEY | Product           | Category      |
      | 01  | AutoSquare Canvas | Home & Living |
    Then verify image on popup Select mockup as "01"
      | KEY | Image expected       | Percent Image |
      | 01  | /Prod_2D_mockup1.png | 1             |
      | 01  | /Prod_2D_mockup2.png | 1             |
      | 01  | /Prod_2D_mockup3.png | 1             |
      | 01  | /Prod_2D_mockup4.png | 1             |
    And quit browser

#  Scenario: Verify show mockup default on popup select mockup
#    Given user login to shopbase dashboard
#    And user navigate to "Catalog" screen
#    When click select mockup with Base as "01"
#      | KEY | Product           | Category      |
#      | 01  | AutoSquare Canvas | Home & Living |
#    Then verify image on popup Select mockup as "01"
#      | KEY | Image expected       | Percent Image |
#      | 01  | /Prod_2D_mockup2.png | 1             |
#      | 01  | /Prod_2D_mockup1.png | 1             |
#      | 01  | /Prod_2D_mockup3.png | 1             |
#      | 01  | /Prod_2D_mockup4.png | 1             |
#    And quit browser

  Scenario Outline: Setting mockup hive and verify mockup popup not tick all #SB_PRB_test_228 #SB_PRB_test_230 #SB_PRB_test_232 #create base product not tick all on select mockup
    Given Description: "<Testcase>"
    And login to hive-pbase
    And redirect to mockup camp "AutoSquare Canvas" hive-pbase
    Then Add or edit new mockup camp on hive-pbase as "<KEY>"
      | KEY | Checkbox name | Status checkbox | Mockup ordinal number | File Name                           |
      | 01  | Add           | true            |                       | phub/mockupcamp/Prod_2D_mockup5.png |
      | 02  | NotUse        | false           | 2                     |                                     |
      | 03  | NotUse        | true            | 2                     |                                     |
      | 03  | Delete        | true            |                       |                                     |
    And user login to shopbase dashboard
    And user navigate to "Catalog" screen
    When click select mockup with Base as "<KEY>"
      | KEY | Product           | Category      |
      | 01  | AutoSquare Canvas | Home & Living |
      | 02  | AutoSquare Canvas | Home & Living |
      | 03  | AutoSquare Canvas | Home & Living |
    Then verify image on popup Select mockup as "<KEY>"
      | KEY | Image expected       | Mockup Selected | Percent Image |
      | 01  | /Prod_2D_mockup1.png |                 | 1             |
      | 01  | /Prod_2D_mockup2.png | false           | 1             |
      | 01  | /Prod_2D_mockup3.png | false           | 1             |
      | 01  | /Prod_2D_mockup4.png | false           | 1             |
      | 01  | /Prod_2D_mockup5.png | false           | 1             |
      | 02  | /Prod_2D_mockup1.png |                 | 1             |
      | 02  | /Prod_2D_mockup7.png | false           | 1             |
      | 02  | /Prod_2D_mockup2.png | false           | 1             |
      | 02  | /Prod_2D_mockup3.png | false           | 1             |
      | 02  | /Prod_2D_mockup4.png | false           | 1             |
      | 03  | /Prod_2D_mockup1.png |                 | 1             |
      | 03  | /Prod_2D_mockup2.png | false           | 1             |
      | 03  | /Prod_2D_mockup3.png | false           | 1             |
      | 03  | /Prod_2D_mockup4.png | false           | 1             |
    And quit browser

    Examples:
      | KEY | Testcase                      |
      | 01  | Add new mockup on hive        |
      | 02  | Tick Not Use mockup on hive   |
      | 03  | Untick Not Use mockup on hive |


#  Scenario Outline: Setting mockup hive and verify mockup popup tick all #SB_PRB_test_229 #SB_PRB_test_231 #SB_PRB_test_233 #create base product tick all on select mockup
#    Given Description: "<Testcase>"
#    And login to hive-pbase
#    And redirect to mockup camp "AutoSquare Canvas" hive-pbase
#    When Add or edit new mockup camp on hive-pbase as "<KEY>"
#      | KEY | Checkbox name | Status checkbox | Mockup ordinal number | File Name                           |
#      | 01  | Add           | true            |                       | phub/mockupcamp/Prod_2D_mockup5.png |
#      | 02  | Not Use       | false           | 2                     |                                     |
#      | 03  | Delete        | true            |                       |                                     |
#    And user login to shopbase dashboard
#    And user navigate to "Catalog" screen
#    And click select mockup with Base as "<KEY>"
#      | KEY | Product           | Category      |
#      | 01  | AutoSquare Canvas | Home & Living |
#      | 02  | AutoSquare Canvas | Home & Living |
#    Then verify image on popup Select mockup as "<KEY>"
#      | KEY | Image expected       |  Percent Image |
#      | 01  | /Prod_2D_mockup1.png |  1             |
#      | 01  | /Prod_2D_mockup2.png |  1             |
#      | 01  | /Prod_2D_mockup3.png |  1             |
#      | 01  | /Prod_2D_mockup4.png |  1             |
#      | 01  | /Prod_2D_mockup5.png |  1             |
#      | 02  | /Prod_2D_mockup1.png |  1             |
#      | 02  | /Prod_2D_mockup2.png |  1             |
#      | 02  | /Prod_2D_mockup3.png |  1             |
#      | 02  | /Prod_2D_mockup4.png |  1             |
#      | 02  | /Prod_2D_mockup7.png |  1             |
#      | 03  | /Prod_2D_mockup1.png |  1             |
#      | 03  | /Prod_2D_mockup2.png |  1             |
#      | 03  | /Prod_2D_mockup3.png |  1             |
#      | 03  | /Prod_2D_mockup4.png |  1             |
#    And Add or edit new mockup camp on hive-pbase as "<KEY>"
#      | KEY | Checkbox name | Status checkbox | Mockup ordinal number |
#      | 02  | Not Use       | true            | 2                     |
#    And quit browser
#
#    Examples:
#      | KEY | Testcase               |
#      | 01  | Add new mockup on hive |
#      | 02  | Untick Not Use mockup on hive |
#      | 03  | Delete mockup on hive         |