Feature: Scan product DMCA for ShopBase
#prod_scan_dmca_sbase
  Scenario: Delete all product of shop
    And Delete multi products by API

  Scenario Outline: Scan product on shopbase #SB_PRO_PRO_DMCA_25
    Given Description: "<Testcase>"
    Given user login to shopbase dashboard by API
    And user navigate to "Products>All products" screen
    And Add a new product with data
      | Title  | Price |
      | AXZ 01 | 12000 |
      | AXZ 02 | 12000 |
      | AXZ 03 | 12000 |
      | AXZ 04 | 12000 |
      | AXZ 05 | 12000 |
    And quit browser
    Given login to hive sbase
    And user navigate to "TOS violation review>Product moderation list" on hive page
    And click to Expand filter options in hive sbase
# xu ly time
    And input Created form and to in hive
    And choose filter product
      | Platform type | Store ID |
      | ShopBase      | @shopID@ |
    And click to button "Filter"
    And verify list products in Product moderation list page
      | List product             | Total product |
      | A 05,A 04,A 03,A 02,A 01 | 5             |
    And quit browser
    Examples:
      | Testcase                                        |
      | Scan product on ShopBase with title and variant |