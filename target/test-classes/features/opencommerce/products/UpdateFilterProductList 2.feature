Feature: Update filter product list
  #env = update_filter_product_list

  Background: Login product list page
    Given user login to shopbase dashboard
    And user navigate to "Products>All products" screen


  Scenario: Verify button Edit current filter is disable when acc tab template
    When Acc to tab template filter
      | tab                                         |
      | All,Available products,Unavailable products |
    Then Verify button "Edit current filter" is disable
    And close browser

  Scenario: Verify when save current template success
    When Filter product by condition
      | Condition                 | SubCondition    |
      | ShopBase warehouse status | Mapped products |
    And "Save current filter" for new template with name "Mapped"
    Then Verify display tab new template
    And Verify display product by condition filter
      | ProductName | Status              |
      | Mapped      | Warehouse connected |
    And close browser

  Scenario: Verify when click on status "Connected to warehouse item" will link redirect product mapping page
    When Acc to tab template filter
      | tab    |
      | Mapped |
    And Click on status "Warehouse connected"
    Then Verify link redirect product mapping page
    And close browser

  Scenario: Verify edit name template filter success
    When Acc to tab template filter
      | tab    |
      | Mapped |
    And "Edit current filter" for new template with name "EditMapped"
    And "Edit" name template filter
    Then Verify display tab new template
    And Verify display product by condition filter
      | ProductName | Status              |
      | Mapped      | Warehouse connected |
    And close browser

  Scenario: Verify when delete template filter created
    When Acc to tab template filter
      | tab        |
      | EditMapped |
    And "Edit current filter" for new template with name "EditMapped"
    And "Delete" name template filter
    And user navigate to "Products>All products" screen
    Then Verify not display tab new template
    And close browser