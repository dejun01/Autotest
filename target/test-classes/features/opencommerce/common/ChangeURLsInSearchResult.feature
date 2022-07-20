@dashboardChangeURL @dashboard
Feature: Change URLs in search result

  Background: Login dashboard
    Given user login to shopbase dashboard

  @dashboardChangeURLsInSearchResultsProduct
  Scenario: Check urls product when search and filter by tab
    And user navigate to "Products>All products" screen
    Then Search "Dress" product on All product screen and filter by tab and verify url
      | Tab               | Paramester  |
      | Active products   | published   |
      | Inactive products | unpublished |
      | All               |             |

  @dashboardChangeURLsInSearchResultsCollection
  Scenario: Check urls collection when search and filter by tab
    And user navigate to "Products>Collections" screen
    Then Search "Fashion" collection on collections screen and filter by tab and verify url
      | Tab                   | Paramester            |
      | Manual collections    | manual-collections    |
      | Automated collections | automated-collections |
      | All                   |                       |

  @dashboardChangeURLsInSearchResultsDiscount
  Scenario: Check urls discount code when search and filter by tab
    And user navigate to "Discounts>Codes" screen
    Then Search "FIXED_AMOUNT" code on discount codes screen and filter by tab and verify url
      | Tab       | Paramester |
      | Active    | active     |
      | Scheduled | scheduled  |
      | Expired   | expired    |
      | All       |            |