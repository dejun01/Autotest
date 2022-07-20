Feature: Check filter order
  #sb_personalize_export_order
#  Scenario: Delete product before execute
#    When clear all data

#  Scenario: Verify filter order with status All items are generating #SB_PRB_test_23 #SB_PRB_test_24
#    Given user login to shopbase dashboard by API
#    And user navigate to "Orders>All orders" screen
#    And click to button "More filters"
#    Then click to "Print file status" and click radio "Any item is generating"
#    And click to button "Apply"
#    And verify orders in list order
#      | Order |
#      | #1001 |
#    And quit browser


  Scenario: Verify filter order with status All items are generated #SB_PRB_test_25 #SB_PRB_test_26
    Given user login to shopbase dashboard by API
    And user navigate to "Orders>All orders" screen
    And click to button "More filters"
    Then click to "Print file status" and click radio "All items are generated"
    And click to button "Apply"
    And verify orders in list order
      | Order |
      | #1002 |
      | #1001 |
    And quit browser

