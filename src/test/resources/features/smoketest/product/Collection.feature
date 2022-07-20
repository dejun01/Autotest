@dashboardProduct @productCollection
Feature: ProductCollection
#prod_sbase_smoke_collection
  Background: Login dashboard
    Given user login to shopbase dashboard by API
    And user navigate to "Products>Collections" screen
    Given Delete all collection

  Scenario: Verify product added to manual collection #SB_PRO_PC_144
    And Create collection with data
      | Title            | Collection type | Conditions                                    |
      | Smart collection | Automated       | all conditions,Product title,contains,T-shirt |
    Then verify sort products display correctly
      | Sort type         | Number product |
      | Product title Z-A | 307            |
      | Oldest            | 307            |