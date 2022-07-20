Feature: Scan product DMCA for PrintBase
#prod_scan_dmca_pbase
  Scenario: Delete all product live
    When delete all campaigns by API

  Scenario Outline: Create new campaign  #SB_PRO_PRO_DMCA_26
    Given user login to shopbase dashboard by API
    And user navigate to "Catalog" screen
    When add products to campaign as "<KEY>"
      | KEY | Product             | Category      |
      | 01  | Unisex T-shirt      | Apparel       |
      | 02  | Unisex Tank         | Apparel       |
      | 03  | Crewneck Sweatshirt | Apparel       |
      | 04  | Quilt               | Home & Living |
      | 05  | Portrait Puzzle     | Home & Living |
    And add new layer as "<KEY>"
      | KEY | Product             | Layer type | Layer name  | Front or back |
      | 01  | Unisex T-shirt      | Text       |             | Front         |
      | 02  | Unisex Tank         | Image      | Capture.PNG | Front         |
      | 03  | Crewneck Sweatshirt | Image      | Capture.PNG | Back          |
      | 04  | Quilt               | Text       |             |               |
      | 05  | Portrait Puzzle     | Image      | Capture.PNG |               |
    And click to button "Continue"
    And input infor to create description for campaign editor
      | Title           |
      | <Campaign name> |
    And click to button Launch campaign
    And quit browser

    Examples:
      | KEY | Campaign name  |
      | 01  | au_scam_camp01 |
      | 02  | au_scam_camp02 |
      | 03  | au_scam_camp03 |
      | 04  | au_scam_camp04 |
      | 05  | au_scam_camp05 |


  Scenario Outline: Scan product on PrintBase
    Given Description: "<Testcase>"
    Given login to hive sbase
    And user navigate to "TOS violation review>Product moderation list" on hive page
    And click to Expand filter options in hive sbase
# xu ly time
    And input Created form and to in hive
    And choose filter product
      | Platform type | Store ID |
      | PrintBase     | @shopID@ |
    And click to button "Filter"
    And verify list products in Product moderation list page
      | Total product |
      | 5             |
    And quit browser

    Examples:
      | Testcase                                         |
      | Scan product on PrintBase with title and variant |

