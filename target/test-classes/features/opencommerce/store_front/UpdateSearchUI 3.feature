Feature: Update Search UI

#us_incart_offer

  Scenario: Verify search page
    Given open page "/search"
    And verify search keyword
      | Keyword                                  | Click search icon | Search result                                                   | Match full keyword |
      | no_product                               | true              |                                                                 | false              |
      | product 2                                | true              | product 2                                                       | true               |
      | product 3 co custom option requite la no | false             | product 2                                                       | false              |
      | product 3 co custom option requite la no | true              | product 3 co custom option requite la no                        | false              |
      | product_tag                              | true              | product 4 co custom option nhieu variant va mot so out of stock | false              |


