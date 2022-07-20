Feature: Limit collection for PrintBase
  #limit_collection_pb
  Scenario: Delete all collections
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>Collections" screen
    And Delete all collection

  Scenario: Verify number of collection created when shop don't reach limit collection
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>Collections" screen
    And get number of collection created by API
    And Create collection with data
      | Title             | Collection type |
      | Name collection 1 | Manual          |
      | Name collection 2 | Manual          |
      | Name collection 3 | Manual          |
      | Name collection 4 | Manual          |
      | Name collection 5 | Manual          |
    Then verify number of collection created after create "5" collections by API
    Then verify list collection in dashboard
    And open shop on storefront
    And open collection list on Store front
    And verify number collections on Store front
    And quit browser




  Scenario: Create collection when shop reach limit collection
    Given user login to shopbase dashboard by API
    And user navigate to "Campaigns>Collections" screen
    And get number of collection created by API
    And verify button Create collection as "disabled"
    And quit browser





