Feature: Anonymous request product
  #https://docs.google.com/spreadsheets/d/1aMmr_XCQ8j86r8y2R5PYXeeYNoANhhcaWcB0DNd1RnY/edit#gid=169581423
  #crosspanda_request_product
  Scenario Outline: verify request product successfully
    Given login to crosspanda dashboard
    When user navigate to "Request list" screen in CrossPanda
    And switch to tab "In process"
    And save quotation created in crosspanda "<KEY>"
      | KEY | Link                                         |
      | 2   | https://aliexpress.com/item/33039103021.html |
    And close browser
    And get quotation number as "<KEY>"
      | KEY |
      | 2   |
    And I should be able to see SO with name "<KEY>"
      | KEY | Email | ProductURL                                       |
      | 2   |       | https://www.aliexpress.com/item/33039103021.html |
    And That order "<KEY>" contains product
      | KEY | Email | ProductURL                                       |
      | 2   |       | https://www.aliexpress.com/item/33039103021.html |
    Examples:
      | KEY |
      | 2   |

#  Scenario: Verify UI Anonymous request product
#    Given open anonymous request product page
#    When verify UI anonymous request product page
#      | Choose your supplier         | Choose your monthly revenue                                                                        | Links request                                                                                                               | Contact                    |
#      | AliExpress,eBay,Amazon,Other | 0-$2,000;$2,000-$5,000;$5,000-$10,000;$10,000-$50,000;$50,000-$100,000;$100,000-$500,000;>$500,000 | https://aliexpress.com/item/123456.html?,https://www.amazon.com/products/123456,https://yourstore.com/products/123456.html? | Facebook,Skype,Phone,Other |
#    And close browser

  Scenario Outline: Anonymous request product
    Given Description: "<Testcase>"
    Given open anonymous request product page
    Then anonymous request product as "<KEY>"
      | KEY | Supplier | Revenue        | Links                                            | Note | Facebook                            | Skype        | Phone      | Other | Msg                                                             |
      | 1   |          |                |                                                  | abc  | https://www.facebook.com/crosspanda | crosspanda   | 4174472945 | other | Please select your monthly revenue,Please enter a product link. |
      | 2   | eBay     | $5,000-$10,000 | https://www.aliexpress.com/item/33039103021.html |      | https://www.facebook.com/crosspanda | crosspanda22 | 0978256601 |       |                                                                 |
    And verify signin page and login as "<KEY>"
      | KEY |
      | 2   |
    And verify quotation created in crosspanda "<KEY>"
      | KEY | Link                                             |
      | 2   | https://www.aliexpress.com/item/33039103021.html |
    And close browser

    Examples:
      | KEY | Testcase     |
      | 1   | Invalid data |
      | 2   | Valid data   |