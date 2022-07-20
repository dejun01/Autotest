#sbase_request_quotation
Feature: Request Quotation

  Scenario: Verify request link invalid
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link | Case    |
      | abc  | Invalid |
    And verify result after request product
      | Type    | Message                    |
      | Invalid | Please enter the valid URL |
    And close browser

  Scenario: Verify request more than 1 link SB_SBFF_FindProducts_1
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005003245393949.html | shopbase@beeketing.net |
      | https://www.aliexpress.com/item/1005003536471630.html | shopbase@beeketing.net |
      | https://www.aliexpress.com/item/1005003637846610.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given search quotation in Request list quotation
      | Quotation ID                                          | Is show |
      | https://www.aliexpress.com/item/1005003245393949.html | false   |
      | https://www.aliexpress.com/item/1005003536471630.html | false   |
      | https://www.aliexpress.com/item/1005003637846610.html | false   |
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "All"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                                                                                                                              |
      | https://www.aliexpress.com/item/1005003245393949.html,https://www.aliexpress.com/item/1005003637846610.html,https://www.aliexpress.com/item/1005003536471630.html |
    And verify result after request product
      | Type  | Quotation ID | Tab | Link                                                                                                                                                              |
      | Valid | @Quotation@  | All | https://www.aliexpress.com/item/1005003245393949.html,https://www.aliexpress.com/item/1005003637846610.html,https://www.aliexpress.com/item/1005003536471630.html |
    And verify product is created on Odoo
      | Product name                                                                                                          | Count product |
      | Autumn Winter Vintage Black Women's Faux PU Leather Shorts 2021 New High Waist Lace Up Short Wide Leg Trousers Female | 1             |
      | Women Down Padded Jacket Winter Warm Mid-Length 2022 New Long Harajuku Long Loose Gloosy Parka Thicken Coat           | 1             |
      | 2021 New Parkas Women for Winter Waistcoat Fashion Korean Style Cotton-Padded Sleeveless Thick Warm Vest Jackets      | 1             |
    Then close browser

  Scenario: Verify request duplicate link SB_SBFF_FindProducts_1
    And delete SO on Odoo with
      | Product URL                                           | Email                  |
      | https://www.aliexpress.com/item/1005002181525126.html | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given search quotation in Request list quotation
      | Quotation ID                                          | Is show |
      | https://www.aliexpress.com/item/1005002181525126.html | false   |
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "All"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                                                                                        |
      | https://www.aliexpress.com/item/1005002181525126.html,https://www.aliexpress.com/item/1005002181525126.html |
    And verify result after request product
      | Type  | Quotation ID | Tab | Link                                                  |
      | Valid | @Quotation@  | All | https://www.aliexpress.com/item/1005002181525126.html |
    Then close browser
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                                           |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://www.aliexpress.com/item/1005002181525126.html |
    And verify product is created on Odoo
      | Product name                                                                                                                    | Count product |
      | Qian Han Zi runway fashion elegant summer long dress Women Long Sleeve Mesh Printed Ruffled Beach Holiday Slim Party Maxi Dress | 1             |
    Then close browser

  Scenario: Verify request link isn't AliExpress SB_SBFF_FindProducts_1
    And delete SO on Odoo with
      | Product URL                          | Email                  |
      | https://product-test1-quotation1.com | shopbase@beeketing.net |
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given search quotation in Request list quotation
      | Quotation ID                         | Is show |
      | https://product-test1-quotation1.com | false   |
    Given user navigate to "fulfillment/dropship" screen by url
    Given get count sale order in tab "All"
    Given user go to "Request products" page
    Then user input link and request quotation
      | Link                                 |
      | https://product-test1-quotation1.com |
    And verify result after request product
      | Type  | Quotation ID | Tab | Link                                 |
      | Valid | @Quotation@  | All | https://product-test1-quotation1.com |
    Then close browser
    And verify SO is created on Odoo
      | Quotation ID | Status quotation | Email                  | Product URL                          |
      | @Quotation@  | draft            | shopbase@beeketing.net | https://product-test1-quotation1.com |
    And verify product is created on Odoo
      | Product name                         | Count product |
      | https://product-test1-quotation1.com | 1             |
    Then close browser

  Scenario: Verify Contact information when input Email/Phone/Facebook valid
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given user go to "Request products" page
    Then user input data in Contact information then verify data
      | Email                   | Skype        | Phone       | Facebook             |
      | shopbase1@beeketing.net | shopbase2222 | 09485434444 | https://facebook.com |
      | shopbase@beeketing.net  | shopbase2222 | 09485434444 | https://facebook.com |
    Then close browser

  Scenario: Verify Contact information when input Email/Phone/Facebook invalid
    Given user login to shopbase dashboard
    Given user navigate to "fulfillment/dropship" screen by url
    Given user go to "Request products" page
    Then user input data in Contact information then verify data
      | Email              | Skype | Phone          | Facebook       | Msg                                |
      | shopbase@beeketing |       |                |                | Please enter a valid email address |
      |                    |       | 09485434444abc |                | Please enter the valid number      |
      |                    |       |                | 09485434444abc | Please enter the valid URL         |
    And close browser


